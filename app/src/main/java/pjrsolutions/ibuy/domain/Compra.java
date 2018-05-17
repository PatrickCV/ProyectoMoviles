package pjrsolutions.ibuy.domain;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.json.JsonObject;

public class Compra {
	
	private int codigo;
	private String sucursal;
	private String fecha;
	private double monto;
	private ArrayList<ArticuloCompra> articulos;
	
	public Compra (int codigo, String sucursal, String fecha, double monto){
		
		this.codigo = codigo;
		this.sucursal = sucursal;
		this.fecha = fecha;
		this.monto = monto;
		this.articulos = new ArrayList<ArticuloCompra>();
		
	}
	
	public Compra (JSONObject json) {
		
		try {
			
			this.codigo = json.getInt("codigo");
			this.sucursal = json.getString("sucursal");
			this.fecha = json.getString("fecha");
			this.monto = json.getDouble("monto");
			
		} catch (JSONException e) {
			
			System.out.println("No se pudo parsear Compra de JSON");
			
		}
		
	}
	
	public void setCodigo (int codigo) {
		
		this.codigo = codigo;
		
	}
	
	public int getCodigo () {
		
		return codigo;
		
	}
	
	public void setSucursal (String sucursal) {
		
		this.sucursal = sucursal;
		
	}
	
	public String getSucursal () {
		
		return sucursal;
		
	}
	
	public void setFecha (String fecha) {
		
		this.fecha = fecha;
		
	}
	
	public String getFecha () {
		
		return fecha;
		
	}
	
	public void setMonto (double monto) {
		
		this.monto = monto;
		
	}
	
	public double getMonto () {
		
		return monto;
		
	}
	
	public void addArticulo (ArticuloCompra articulo) {
		
		this.articulos.add(articulo);
		
	}
	
	public ArrayList<ArticuloCompra> getArticulos () {
		
		return this.articulos;
		
	}

}
