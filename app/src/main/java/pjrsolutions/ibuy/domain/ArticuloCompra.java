package pjrsolutions.ibuy.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticuloCompra {
	
	private int id;
	private String nombre;
	private float precio;
	private int cantidad;
	
	public ArticuloCompra(String nombre, float precio, int cantidad) {
		
		this.setNombre(nombre);
		this.setPrecio(precio);
		this.setCantidad(cantidad);
		
	}
	
	public ArticuloCompra (JSONObject json) {
		
		try {
			
			this.id = json.getInt("id");
			this.nombre = json.getString("nombre");
			this.precio = (float)json.getDouble("precio");
			this.cantidad = json.getInt("cantidad");
			
		} catch (JSONException e) {
			
			e.printStackTrace();
			
			System.out.println("No se pudo parsear ArticuloCompra de JSON");
			
		}
		
	}
	
	public static ArrayList<ArticuloCompra> parseJSON (String strJSON) {
		
		ArrayList<ArticuloCompra> articulosCompra = new ArrayList<ArticuloCompra>();
		
		try {
			
			JSONObject json = new JSONObject(strJSON);
			JSONArray jsonArticulosCompra = json.getJSONArray("articulos");
			
			JSONObject jsonArticuloCompra;
			
			for (int x = 0; x < jsonArticulosCompra.length(); x ++) {
				
				jsonArticuloCompra = new JSONObject(jsonArticulosCompra.get(x).toString());
				
				articulosCompra.add(new ArticuloCompra(jsonArticuloCompra));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return articulosCompra;
		
	}
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public float getPrecio() {
		
		return precio;
		
	}
	
	public void setPrecio(float precio) {
		
		this.precio = precio;
		
	}
	
	public int getCantidad() {
		
		return cantidad;
		
	}
	
	public void setCantidad(int cantidad) {
		
		this.cantidad = cantidad;
		
	}
	
	public int getId () {
		
		return id;
		
	}
	
	public void setId(int id) {
		
		this.id = id;
		
	}

}
