package pjrsolutions.ibuy.webServices;


import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pjrsolutions.ibuy.business.usuario.Configuracion_Usuario;
import pjrsolutions.ibuy.business.usuario.Modificar_Usuario;
import pjrsolutions.ibuy.business.usuario.Registro_Usuario;
import pjrsolutions.ibuy.domain.Compra;
import pjrsolutions.ibuy.domain.Persona;
import pjrsolutions.ibuy.webServices.base.WebServiceCliente;

public class WebServiceUsuario extends WebServiceCliente {

    private static final String URL_GET_PAGINA = "http://http://192.168.1.7/webservice/webServiceUsuario.php?accion=registrar&cedula=%s&nombre=%s&apellidos=%s&correo=%s&clave=%s";
    Context context;
    String respuesta="";
    Registro_Usuario registro_usuario;
    Modificar_Usuario modificar_usuario;
    int proceso;

    private static Map<String, String> URLS = (new HashMap<String, String>());


    public WebServiceUsuario(Registro_Usuario registro_usuario){
        super();
        this.registro_usuario=registro_usuario;
    }
    public WebServiceUsuario(Modificar_Usuario modificar_usuario){
        super();
        this.modificar_usuario=modificar_usuario;
    }

    public String insertarUsuario(String... params){
        this.putUrl("registrar",WebServiceUsuario.URL_GET_PAGINA);
        this.setUrl(String.format(this.getUrls().get("registrar"), (Object[])params));
        System.out.println(this.getUrl());
        this.trabajo();
        return respuesta;

    }
    public String modificarUsuario(String... params){
        this.proceso=3;
        String URL_MODIFICAR="http://192.168.1.7/webservice/webServiceUsuario.php?accion=modificar&cedula=%s&nombre=%s&apellidos=%s&correo=%s&clave=%s";
        this.putUrl("modificar",URL_MODIFICAR);
        this.setUrl(String.format(this.getUrls().get("modificar"), (Object[])params));
        System.out.println(this.getUrl());
        this.trabajo();
        return respuesta;
    }
    public String mostrarUsuario(String... params){
        this.proceso=2;
        String URL_MOSTRAR="http://192.168.1.7/webservice/webServiceUsuario.php?accion=mostrar&usuario=%s";
        this.putUrl("mostrar",URL_MOSTRAR);
        this.setUrl(String.format(this.getUrls().get("mostrar"), (Object[])params));
        System.out.println(this.getUrl());
        this.trabajo();
        return respuesta;
    }


    @Override
    protected String doInBackground(String... params) {
       if(params[0]=="registrar"){
               String[] valores = new String[params.length - 1];

               for (int x = 1; x < params.length; x ++) {

                   valores[x - 1] = params[x];

                   System.out.println(valores[x - 1]);

               }
               this.setTipo(params[0]);

               if (params[0].equals("registrar")) {

                   insertarUsuario(valores);

               }

       }
        if(params[0]=="mostrar"){
            String[] valores = new String[params.length - 1];

            for (int x = 1; x < params.length; x ++) {

                valores[x - 1] = params[x];

                System.out.println(valores[x - 1]);

            }
            this.setTipo(params[0]);

            if (params[0].equals("mostrar")) {

                mostrarUsuario(valores);

            }

        }
        if(params[0]=="modificar"){
            String[] valores = new String[params.length - 1];

            for (int x = 1; x < params.length; x ++) {

                valores[x - 1] = params[x];

                System.out.println(valores[x - 1]);

            }
            this.setTipo(params[0]);

            if (params[0].equals("modificar")) {

                modificarUsuario(valores);

            }

        }
       return null;
    }
    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }
    @Override
    protected void onProgressUpdate(String... params) {
        if (params[0].equals("exito")) {

            try {
                this.accionExito();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (params[0].equals("error")) {

            this.accionError();

        }
    }

    @Override
    protected void onPreExecute() {
       // super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String valor) {
      /* Toast mensaje = Toast.makeText(this.context, valor, Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
        mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
        mensaje.show(); //muestra el mensaje
        //System.out.println(valor+"nadadad");*/
    }

    private void accionExito () throws JSONException {
        if (proceso==1){
            Toast mensaje = Toast.makeText(this.registro_usuario.getContext(), "Exito!!", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
        }
        if(proceso==2){

            System.out.println("Exito: " + this.getRespuesta());
            JSONObject object = new JSONObject(this.getRespuesta());
            modificar_usuario.refreshHeroList(object.getJSONArray("usuario"));
        }if(proceso==3){
            Toast mensaje = Toast.makeText(this.modificar_usuario.getContext(), "Exito!!", Toast.LENGTH_LONG);//desplega un mensaje informndo que el campo esta vacio
            mensaje.setGravity(Gravity.CENTER, 0, 300);//se la da una posicion para ver el mensaje.
            mensaje.show(); //muestra el mensaje
            this.modificar_usuario.getFragmentManager().popBackStack();
        }
        else{
            System.out.println("Exito: " + this.getRespuesta());
        }

    }

    private void accionError () {

        System.out.println("Error");

    }
}
