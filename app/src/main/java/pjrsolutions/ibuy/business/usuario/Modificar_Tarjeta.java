package pjrsolutions.ibuy.business.usuario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;

import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.domain.Tarjeta;
import pjrsolutions.ibuy.webServices.WebServiceTarjeta;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Modificar_Tarjeta extends FragmentoAbstracto implements View.OnClickListener {
    private EditText numeroTarjeta;
    private EditText codigoSeguridad;
    private Spinner mes;
    private Spinner year;
    private EditText nombre;
    private EditText direccion;
    private EditText codgioPostal;
    private Button modificarTarjeta;
    private ProgressBar progess;
    WebServiceTarjeta webUser ;
    private  String id;
    public Modificar_Tarjeta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = (View) inflater.inflate(R.layout.fragment_modificar__tarjeta, container, false);
        String numero = getArguments() != null ? getArguments().getString("numero") : "vacio";
        String id = getArguments() != null ? getArguments().getString("id") : "vacio";
        String nombre = getArguments() != null ? getArguments().getString("nombre") : "vacio";
        String fecha = getArguments() != null ? getArguments().getString("fecha") : "vacio";
        String direccion = getArguments() != null ? getArguments().getString("direccion") : "vacio";
        String codPostal = getArguments() != null ? getArguments().getString("codPostal") : "vacio";
        System.out.println(numero);
        this.nombre= (EditText) vista.findViewById(R.id.nombreModificar);
        this.mes = (Spinner) vista.findViewById(R.id.mesModificar);
        this.year = (Spinner) vista.findViewById(R.id.yearModificar);
        this.direccion =(EditText) vista.findViewById(R.id.direccionModificar);
        this.codgioPostal=(EditText) vista.findViewById(R.id.codPostalModificar);
        this.modificarTarjeta =(Button) vista.findViewById(R.id.modificarTarjeta);
        String[] separar=fecha.split("-");
        this.year.setSelection(adivinarPosicionAnio(separar[0]));
        this.mes.setSelection(adivinarPosicionMes(separar[1]));
        this.nombre.setText(nombre);
        this.direccion.setText(direccion);
        this.codgioPostal.setText(codPostal);
        this.id=id;
        this.nombre.setOnClickListener(this);
        this.direccion.setOnClickListener(this);
        this.codgioPostal.setOnClickListener(this);
        this.modificarTarjeta.setOnClickListener(this);

        return vista;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.modificarTarjeta){
            if(validarModificar()){
                String id=this.id;
                Tarjeta tarjeta = new Tarjeta();
                String fecha;
                fecha="01-"+mes.getSelectedItem().toString()+"-"+year.getSelectedItem().toString();
                tarjeta.setFechaVencimiento(fecha);
                String name = nombre.getText().toString();
                String direction=direccion.getText().toString();
                String cadenaNombre=name.replace(" ","_");
                String cadenaDireccion=direction.replace(" ","_");
                tarjeta.setNombreTitular(cadenaNombre);
                tarjeta.setDireccion(cadenaDireccion);
                tarjeta.setCodigoPostal(codgioPostal.getText().toString());
                webUser = new WebServiceTarjeta(this);
                webUser.execute("modificar",id,tarjeta.getFechaVencimiento(),tarjeta.getNombreTitular(),tarjeta.getDireccion(),tarjeta.getCodigoPostal());
            }
        }
    }

    private Boolean validarModificar(){
        Boolean pasa = true;//se crea una variable para validacion cumple con los campos
        if (TextUtils.isEmpty(mes.getSelectedItem().toString())) {//consulta si el campo del usuario esta vacio
            pasa = false; //pasa la variable de validacion a false.
            Toast mensaje = Toast.makeText(getContext(), "Debe seleccionar el mes vencimiento", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if (TextUtils.isEmpty(year.getSelectedItem().toString())) {//consulta si el campo del usuario esta vacio
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
    private int adivinarPosicionMes(String mes){
        int posicion=0;
        switch (mes){
            case "01":{
                posicion=0;
                break;
            }
            case "02":{
                posicion=1;
                break;
            }
            case "03":{
                posicion=2;
                break;
            }
            case "04":{
                posicion=3;
                break;
            }
            case "05":{
                posicion=4;
                break;
            }
            case "06":{
                posicion=5;
                break;
            }
            case "07":{
                posicion=6;
                break;
            }
            case "08":{
                posicion=7;
                break;
            }
            case "09":{
                posicion=8;
                break;
            }
            case "10":{
                posicion=9;
                break;
            }
            case "11":{
                posicion=10;
                break;
            }
            case "12":{
                posicion=11;
                break;
            }
            default:
                break;
        }
        return  posicion;
    }
    private int adivinarPosicionAnio(String year){
        int posicion=0;
        switch (year){
            case "2018":{
                posicion=0;
                break;
            }
            case "2019":{
                posicion=1;
                break;
            }
            case "2020":{
                posicion=2;
                break;
            }
            case "2021":{
                posicion=3;
                break;
            }
            case "2022":{
                posicion=4;
                break;
            }
            case "2023":{
                posicion=5;
                break;
            }
            case "2024":{
                posicion=6;
                break;
            }
            case "2025":{
                posicion=7;
                break;
            }
            case "2026":{
                posicion=8;
                break;
            }
            case "2027":{
                posicion=9;
                break;
            }
            case "2028":{
                posicion=10;
                break;
            }
            case "2029":{
                posicion=11;
                break;
            }
            case "2030":{
                posicion=12;
                break;
            }
            default:
                break;
        }
        return  posicion;
    }
}
