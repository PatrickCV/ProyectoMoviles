package pjrsolutions.ibuy.business.usuario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;

import pjrsolutions.ibuy.domain.Persona;
import pjrsolutions.ibuy.webServices.WebServiceUsuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class Registro_Usuario extends FragmentoAbstracto implements View.OnClickListener {
    private EditText cedula;
    private EditText nombre;
    private EditText apellidos;
    private EditText correo;
    private EditText clave;
    private EditText clave2;
    private Button btnRegistro;
    private Button btnCancelar;
    private ProgressBar progess;
    WebServiceUsuario webUser ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = (View) inflater.inflate(R.layout.fragment_registro__usuario,container,false);
        this.getActivity().setTitle("Registro"); // Cambiar el titulo.
        this.cedula = (EditText) vista.findViewById(R.id.cedula);
        this.nombre =(EditText) vista.findViewById(R.id.nombre);
        this.apellidos =(EditText) vista.findViewById(R.id.apellidos);
        this.correo=(EditText) vista.findViewById(R.id.correo);
        this.clave=(EditText) vista.findViewById(R.id.password);
        this.clave2=(EditText) vista.findViewById(R.id.password2);
        this.btnCancelar=(Button) vista.findViewById(R.id.btnCancelarR);
        this.btnRegistro=(Button) vista.findViewById(R.id.btnRegistrarU);
        this.cedula.setOnClickListener(this);
        this.nombre.setOnClickListener(this);
        this.apellidos.setOnClickListener(this);
        this.correo.setOnClickListener(this);
        this.clave.setOnClickListener(this);
        this.clave2.setOnClickListener(this);
        this.btnRegistro.setOnClickListener(this);
        this.btnCancelar.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnRegistrarU:{
                if (validarInsert()) { //si la bandera es cumple lo anterior y no es false
                    Persona nuevousuario = new Persona();
                    nuevousuario.setCedula((cedula.getText().toString()));
                    String cadenaNombre=nombre.getText().toString().replace(" ","_");
                    nuevousuario.setNombre(cadenaNombre);
                    String cadenaApellido=apellidos.getText().toString().replace(" ","_");
                    nuevousuario.setApellidos(cadenaApellido);
                    nuevousuario.setCorreo(correo.getText().toString());
                    nuevousuario.setClave(clave.getText().toString());
                    webUser = new WebServiceUsuario(this);
                    webUser.execute("registrar",nuevousuario.getCedula(),nuevousuario.getNombre(),nuevousuario.getApellidos(),nuevousuario.getCorreo(),nuevousuario.getClave());
                }

                break;
            }
            case R.id.btnCancelarR:{
                    getFragmentManager().popBackStack();
                break;
            }
            default:
                break;
        }

    }

    private Boolean validarInsert(){
        Boolean pasa = true;//se crea una variable para validacion cumple con los campos.
        if (TextUtils.isEmpty(this.cedula.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar la cédula", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
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
        if (TextUtils.isEmpty(clave.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false;//pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar una contraseña", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show();//muestra el mensaje
        }
        if (TextUtils.isEmpty(clave2.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false;//pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar  una contraseña", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show();//muestra el mensaje
        }
        if (!TextUtils.equals(clave.getText().toString(), clave2.getText().toString())) {//consulta si el campo  pass no coinciden
            pasa = false;//pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "La contraseñas deben ser iguales", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show();//muestra el mensaje
        }
        return pasa;
    }
}
