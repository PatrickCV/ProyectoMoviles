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
import android.widget.Spinner;
import android.widget.Toast;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;

import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.domain.Tarjeta;
import pjrsolutions.ibuy.webServices.WebServiceTarjeta;
import pjrsolutions.ibuy.webServices.WebServiceUsuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class Registrar_Tarjeta extends FragmentoAbstracto implements View.OnClickListener{
    private EditText numeroTarjeta;
    private EditText codigoSeguridad;
    private Spinner mes;
    private Spinner anio;
    private EditText nombre;
    private EditText direccion;
    private EditText codgioPostal;
    private Button agregarTarjeta;
    private ProgressBar progess;
    WebServiceTarjeta webUser ;
    View view;

    public Registrar_Tarjeta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =(View )inflater.inflate( R.layout.fragment_registrar__tarjeta, container, false);
        this.getActivity().setTitle("Método de Pago"); // Cambiar el titulo.
        this.numeroTarjeta = (EditText) view.findViewById(R.id.numeroTarjeta);
        this.codigoSeguridad =(EditText) view.findViewById(R.id.codSeguridad);
        this.mes =(Spinner) view.findViewById(R.id.mesTarjeta);
        this.anio=(Spinner) view.findViewById(R.id.yearTarjeta);
        this.nombre=(EditText) view.findViewById(R.id.nombreCompleto);
        this.codgioPostal =(EditText) view.findViewById(R.id.codPostal);
        this.agregarTarjeta =(Button) view.findViewById(R.id.agregarTarjeta);
        this.direccion =(EditText) view.findViewById(R.id.direccion);

        this.numeroTarjeta.setOnClickListener(this);
        this.codigoSeguridad.setOnClickListener(this);
        //this.mes.setOnClickListener(this);
        //this.anio.setOnClickListener(this);
        this.nombre.setOnClickListener(this);
        this.codgioPostal.setOnClickListener(this);
        this.direccion.setOnClickListener(this);

        this.agregarTarjeta.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.agregarTarjeta){
            if(validarInsert()){
                String usuario="702360852";
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setNumeroTarjeta(numeroTarjeta.getText().toString());
                tarjeta.setCodigoSeguridad(codgioPostal.getText().toString());
                String fecha;
                fecha="01-"+mes.getSelectedItem().toString()+"-"+anio.getSelectedItem().toString();
                tarjeta.setFechaVencimiento(fecha);
                String name = nombre.getText().toString();
                String direction=direccion.getText().toString();
                String cadenaNombre=name.replace(" ","_");
                String cadenaDireccion=direction.replace(" ","_");
                tarjeta.setNombreTitular(cadenaNombre);
                tarjeta.setDireccion(cadenaDireccion);
                tarjeta.setCodigoPostal(codgioPostal.getText().toString());
                webUser = new WebServiceTarjeta(this);
                webUser.execute("registrar",tarjeta.getNumeroTarjeta(),usuario,tarjeta.getFechaVencimiento(),tarjeta.getCodigoSeguridad(),tarjeta.getNombreTitular(),tarjeta.getDireccion(),tarjeta.getCodigoPostal());
            }
        }
    }

    private Boolean validarInsert(){
        Boolean pasa = true;//se crea una variable para validacion cumple con los campos.
        if (TextUtils.isEmpty(this.numeroTarjeta.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar el número de tarjeta", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if (this.numeroTarjeta.getText().toString().length()<16) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Número de tarjeta incorrecto", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if (TextUtils.isEmpty(codigoSeguridad.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar el código seguridad", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if (codigoSeguridad.getText().toString().length()<3) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Código seguridad incorrecto", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if (TextUtils.isEmpty(mes.getSelectedItem().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Debe seleccionar el mes vencimiento", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if (TextUtils.isEmpty(anio.getSelectedItem().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Debe seleccionar el año vencimiento", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if (TextUtils.isEmpty(nombre.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false;//pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar  el nombre Titular", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show();//muestra el mensaje
        }
        if (TextUtils.isEmpty(codgioPostal.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false;//pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar  el código Postal", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show();//muestra el mensaje
        }
        if (codgioPostal.getText().toString().length()<5) {//consulta si el campo del usuario esta vacio
            pasa = false;//pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Código Postal incorrecto", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show();//muestra el mensaje
        }
        if (TextUtils.isEmpty(direccion.getText().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false;//pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Tiene que ingresar  la dirección", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show();//muestra el mensaje
        }

        return pasa;
    }
}
