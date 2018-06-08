package pjrsolutions.ibuy.webServices;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.ActividadAbstracta;
import pjrsolutions.ibuy.business.usuario.Lista_Tarjeta;
import pjrsolutions.ibuy.business.usuario.Modificar_Tarjeta;
import pjrsolutions.ibuy.business.usuario.Registrar_Tarjeta;
import pjrsolutions.ibuy.domain.Compra;
import pjrsolutions.ibuy.domain.Tarjeta;
import pjrsolutions.ibuy.webServices.base.WebServiceCliente;

public class WebServiceTarjeta extends WebServiceCliente {
    private static final String URL_PAGINA = "http://172.20.10.3/webservice/webServiceTarjeta.php?accion=registrar&numeroTarjeta=%s&usuario=%s&fecha=%s&codigo=%s&nombre=%s&direccion=%s&codPostal=%s";
    String respuesta="";
    int proceso=0;
    Lista_Tarjeta lista_tarjeta;
    Registrar_Tarjeta registrar_tarjeta;
    Modificar_Tarjeta modificar_tarjeta;

    private static Map<String, String> URLS = (new HashMap<String, String>());


    public WebServiceTarjeta(Lista_Tarjeta lista_tarjeta){
        super();
        this.lista_tarjeta=lista_tarjeta;
    }
    public WebServiceTarjeta(Registrar_Tarjeta registrar_tarjeta){
        super();
        this.registrar_tarjeta=registrar_tarjeta;
    }
    public WebServiceTarjeta(Modificar_Tarjeta modificar_tarjeta){
        super();
        this.modificar_tarjeta=modificar_tarjeta;
    }

    public String insertarTarjeta(String... params){
        this.proceso=1;
        this.putUrl("registrar",WebServiceTarjeta.URL_PAGINA);
        this.setUrl(String.format(this.getUrls().get("registrar"), (Object[])params));
        System.out.println(this.getUrl());
        this.trabajo();
        return respuesta;

    }
    public String modificarTarjeta(String... params){
        this.proceso=3;
        String URL_Modificar = "http://172.20.10.3/webservice/webServiceTarjeta.php?accion=modificar&id=%s&fecha=%s&nombre=%s&direccion=%s&codPostal=%s";
        this.putUrl("modificar",URL_Modificar);
        this.setUrl(String.format(this.getUrls().get("modificar"), (Object[])params));
        System.out.println(this.getUrl());
        this.trabajo();
        return respuesta;
    }
    public String mostrarLista(String... params){
        this.proceso=2;
        String URL_MOSTRAR="http://172.20.10.3/webservice/webServiceTarjeta.php?accion=mostrarTarjetaLista&usuario=%s";
        this.putUrl("mostrarTarjetaLista",URL_MOSTRAR);
        this.setUrl(String.format(this.getUrls().get("mostrarTarjetaLista"), (Object[])params));
        System.out.println(this.getUrl());
        this.trabajo();
        return respuesta;
    }
    public String eiminarTarjeta(String... params){
        this.proceso=4;
        String URL_ELIMINAR="http://172.20.10.3/webservice/webServiceTarjeta.php?accion=eliminar&id=%s";
        this.putUrl("eliminar",URL_ELIMINAR);
        this.setUrl(String.format(this.getUrls().get("eliminar"), (Object[])params));
        System.out.println(this.getUrl());
        this.trabajo();
        return respuesta;
    }



    @Override
    protected String doInBackground(String... params) {

        if(params[0]=="registrar"){
            System.out.println("llego");
            String[] valores = new String[params.length - 1];

            for (int x = 1; x < params.length; x ++) {

                valores[x - 1] = params[x];

                System.out.println(valores[x - 1]);

            }
            this.setTipo(params[0]);

            if (params[0].equals("registrar")) {
                insertarTarjeta(valores);


            }

        }
        if(params[0]=="mostrarLista"){
            String[] valores = new String[params.length - 1];

            for (int x = 1; x < params.length; x ++) {

                valores[x - 1] = params[x];

                System.out.println(valores[x - 1]);

            }
            this.setTipo(params[0]);

            if (params[0].equals("mostrarLista")) {

                mostrarLista(valores);

            }

        }
        if(params[0]=="modificar"){
            System.out.println("llego");
            String[] valores = new String[params.length - 1];

            for (int x = 1; x < params.length; x ++) {

                valores[x - 1] = params[x];

                System.out.println(valores[x - 1]);

            }
            this.setTipo(params[0]);

            if (params[0].equals("modificar")) {
                modificarTarjeta(valores);

            }

        }
        if(params[0]=="eliminar"){
            System.out.println("llego");
            String[] valores = new String[params.length - 1];

            for (int x = 1; x < params.length; x ++) {

                valores[x - 1] = params[x];

                System.out.println(valores[x - 1]);

            }
            this.setTipo(params[0]);

            if (params[0].equals("eliminar")) {
                eiminarTarjeta(valores);

            }

        }
        return null;
    }
    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }
    @Override
    protected void onProgressUpdate(String[] valor) {
         if (valor[0].equals("exito")) {

             try {
                 this.accionExito();
             } catch (JSONException e) {
                 e.printStackTrace();
             }

         } else if (valor[0].equals("error")) {

            this.accionError();

        }
    }

    @Override
    protected void onPreExecute() {
        // super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String valor) {

    }

    private void accionExito () throws JSONException {

        if(proceso==2){
            Toast mensaje = Toast.makeText(this.lista_tarjeta.getContext(), "Exito!!", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
            JSONObject object = new JSONObject(this.getRespuesta());
            lista_tarjeta.refreshHeroList(object.getJSONArray("tarjetas"));
        }else{
            System.out.println("Exito: " + this.getRespuesta());
        }



    }

    private void accionError () {

        System.out.println("Error");

    }
}
