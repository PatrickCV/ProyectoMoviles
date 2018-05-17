package pjrsolutions.ibuy.domain;

public class ArticuloCompra {
	
	private String nombre;
	private float precio;
	private int cantidad;
	
	public ArticuloCompra(String nombre, float precio, int cantidad) {
		
		this.setNombre(nombre);
		this.setPrecio(precio);
		this.setCantidad(cantidad);
		
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

}
