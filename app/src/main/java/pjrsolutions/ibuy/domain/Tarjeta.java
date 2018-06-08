package pjrsolutions.ibuy.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tarjeta {
    private String id;
    private String numeroTarjeta;
    private String nombreTitular;
    private String fechaVencimiento;
    private String codigoSeguridad;
    private String direccion;
    private String codigoPostal;
    private String usuario;
    private ArrayList<Tarjeta> tarjetas;

    public Tarjeta(){
        super();
        this.setId(null);
        this.setNumeroTarjeta(null);
        this.setNombreTitular(null);
        this.setFechaVencimiento(null);
        this.setCodigoSeguridad(null);
        this.setDireccion(null);
        this.setCodigoPostal(null);
        this.setUsuario(null);
    }

    public Tarjeta(String id,String numeroTarjeta,String nombreTitular, String fechaVencimiento, String codigoSeguridad, String direccion, String codigoPostal){
        this.setId(id);
        this.setNumeroTarjeta(numeroTarjeta);
        this.setNombreTitular(nombreTitular);
        this.setFechaVencimiento(fechaVencimiento);
        this.setCodigoSeguridad(codigoSeguridad);
        this.setDireccion(direccion);
        this.setCodigoPostal(codigoPostal);
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    public String getNumeroTarjeta(){
        return this.numeroTarjeta;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }
    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public Tarjeta (JSONObject json) {

        try {

            this.numeroTarjeta = json.getString("numero");
           /* this.sucursal = json.getString("sucursal");
            this.fecha = json.getString("fecha");
            this.monto = json.getDouble("monto");*/
            this.tarjetas = new ArrayList<Tarjeta>();

        } catch (JSONException e) {

            System.out.println("No se pudo parsear Compra de JSON");

        }

    }

    public static ArrayList<Tarjeta> parseJSON (String strJSON) {

        ArrayList<Tarjeta> tarjetas = new ArrayList<Tarjeta>();

        try {

            JSONObject json = new JSONObject(strJSON);
            JSONArray jsonTarjeta = json.getJSONArray("tarjetas");

            JSONObject jsonCompra;

            for (int x = 0; x < jsonTarjeta.length(); x ++) {

                JSONObject object = new JSONObject(jsonTarjeta.get(x).toString());

                tarjetas.add(new Tarjeta(object));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return tarjetas;

    }
    public ArrayList<Tarjeta> getArticulos () {

        return this.tarjetas;
    }
}
