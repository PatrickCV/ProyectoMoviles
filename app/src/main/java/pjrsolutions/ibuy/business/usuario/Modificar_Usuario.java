package pjrsolutions.ibuy.business.usuario;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;

import pjrsolutions.ibuy.domain.Persona;
import pjrsolutions.ibuy.webServices.WebServiceUsuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class Modificar_Usuario extends FragmentoAbstracto implements View.OnClickListener {
    private EditText nombre;
    private EditText apellidos;
    private EditText correo;
    private EditText claveActual;
    private EditText clave;
    private EditText clave2;
    private Button btnModificar;
    private Button btnCancelarM;
    private Button cambiarContra;
    private ProgressBar progess;
    private ConstraintLayout layout;
    WebServiceUsuario webUser ;
    List<Persona> usuarioDato;
    boolean cambio=false;

    public Modificar_Usuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = (View) inflater.inflate(R.layout.fragment_modificar__usuario,container,false);
        this.getActivity().setTitle("Registro"); // Cambiar el titulo.
        this.nombre =(EditText) vista.findViewById(R.id.nombre);
        this.apellidos =(EditText) vista.findViewById(R.id.apellidos);
        this.correo=(EditText) vista.findViewById(R.id.correo);
        this.claveActual=(EditText) vista.findViewById(R.id.passActual);
        this.clave=(EditText) vista.findViewById(R.id.passNueva);
        this.clave2=(EditText) vista.findViewById(R.id.passConfirmar);
        this.btnCancelarM=(Button) vista.findViewById(R.id.btnCancelarM);
        this.btnModificar=(Button) vista.findViewById(R.id.btnRegistrarU);
        this.cambiarContra=(Button) vista.findViewById(R.id.cambiarContra);
        this.layout =(ConstraintLayout) vista.findViewById(R.id.layoutContrasena);
        this.nombre.setOnClickListener(this);
        this.apellidos.setOnClickListener(this);
        this.correo.setOnClickListener(this);
        this.clave.setOnClickListener(this);
        this.clave2.setOnClickListener(this);

        this.btnCancelarM.setOnClickListener(this);
        this.cambiarContra.setOnClickListener(this);
        this.btnModificar.setOnClickListener(this);
        usuarioDato = new ArrayList<>();
        readElements();
        return vista;
    }

    public void refreshHeroList(JSONArray heroes) throws JSONException {
            this.usuarioDato.clear();
            System.out.println(heroes.length()+"tammm");
            JSONObject obj = heroes.getJSONObject(0);
            this.usuarioDato.add(new Persona(obj.getString("id"),
                    obj.getString("nombre"),
                    obj.getString("apellidos"),
                    obj.getString("correo"),
                    obj.getString("password")
            ));

        llenarCampos();
    }

    public void llenarCampos(){
        System.out.println(this.usuarioDato.size()+"tammmm");
        if(this.usuarioDato.size()==1){
            final Persona persona = this.usuarioDato.get(0);
            this.nombre.setText(persona.getNombre());
            this.apellidos.setText(persona.getApellidos());
            this.correo.setText(persona.getCorreo());

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cambiarContra:{
                if(layout.getVisibility()==View.GONE){
                    animar(true);
                    layout.setVisibility(View.VISIBLE);
                    cambiarContra.setText("No Cambiar Contraseña");
                    this.cambio=true;
                }else{
                    cambiarContra.setText("Cambiar  Contraseña");
                    animar(false);
                    layout.setVisibility(View.GONE);
                    this.cambio=false;
                }
                break;
            }
            case R.id.btnRegistrarU:{
                if(validarModificar()){
                    if (validarModificar()) { //si la bandera es cumple lo anterior y no es false
                        final Persona persona = this.usuarioDato.get(0);
                        Persona nuevousuario = new Persona();
                        nuevousuario.setCedula(persona.getCedula());
                        String cadenaNombre=nombre.getText().toString().replace(" ","_");
                        nuevousuario.setNombre(cadenaNombre);
                        String cadenaApellidos=apellidos.getText().toString().replace(" ","_");
                        nuevousuario.setApellidos(cadenaApellidos);
                        nuevousuario.setCorreo(correo.getText().toString());
                        if(this.cambio){
                            nuevousuario.setClave(clave.getText().toString());
                        }else{
                            nuevousuario.setClave(persona.getClave());
                        }
                        webUser = new WebServiceUsuario(this);
                        webUser.execute("modificar",nuevousuario.getCedula(),nuevousuario.getNombre(),nuevousuario.getApellidos(),nuevousuario.getCorreo(),nuevousuario.getClave());
                    }
                }
                break;
            }
            case R.id.btnCancelarM:{
                getFragmentManager().popBackStack();
                break;
            }
        }
    }

    public void animar(Boolean bandera){
        AnimationSet set = new AnimationSet(true);
        Animation animation =null;
        if(bandera){
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
        }else{
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,1.0f);
        }
        animation.setDuration(500);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set,0.25f);
        layout.setLayoutAnimation(controller);
        layout.startAnimation(animation);
    }

    private void  readElements(){
        webUser = new WebServiceUsuario(this);
        webUser.execute("mostrar","702360852");
    }

    private Boolean validarModificar(){
        Boolean pasa = true;//se crea una variable para validacion cumple con los campos.
        if (TextUtils.isEmpty(nombre.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar un nombre", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if (TextUtils.isEmpty(apellidos.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar el apellido", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if (TextUtils.isEmpty(correo.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar un correo", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }

            final Persona persona = this.usuarioDato.get(0);

            if (this.cambio&&TextUtils.isEmpty(clave.getText().toString())) {//consulta si el campo del usuario esta vacio
                pasa = false;//pasa la variable de validacion a false.
                Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar una contraseña", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
                mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
                mensaje.show();//muestra el mensaje
            }
            if (this.cambio&&TextUtils.isEmpty(clave2.getText().toString())) {//consulta si el campo del usuario esta vacio
                pasa = false;//pasa la variable de validacion a false.
                Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar  una contraseña", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
                mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
                mensaje.show();//muestra el mensaje
            }
            if (this.cambio&&!TextUtils.equals(clave.getText().toString(), clave2.getText().toString())) {//consulta si el campo  pass no coinciden
                pasa = false;//pasa la variable de validacion a false.
                Toast mensaje = Toast.makeText(getContext(), "Contraseña de confirmación deben ser iguales", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
                mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
                mensaje.show();//muestra el mensaje
            }
            if (this.cambio&&!TextUtils.equals(claveActual.getText().toString(), persona.getClave())) {//consulta si el campo  pass no coinciden
                pasa = false;//pasa la variable de validacion a false.
                Toast mensaje = Toast.makeText(getContext(), "La contraseñas no concide con la actual", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
                mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
                mensaje.show();//muestra el mensaje
            }
        return pasa;
    }
}
