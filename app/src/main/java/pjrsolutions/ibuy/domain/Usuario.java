package pjrsolutions.ibuy.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuario {
	
	private String cedula;
	private String nombre;
	private String apellidos;
	private String correo;
	
	public Usuario (String cedula, String nombre, String apellidos,
					String correo) {
		
		this.setCedula(cedula);
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setCorreo(correo);
		
	}
	
	public Usuario () {
		
		this.setCedula("");
		this.setNombre("");
		this.setApellidos("");
		this.setCorreo("");
		
	}
	
	public Usuario (JSONObject json) {
		
		try {
			
			this.cedula = json.getString("cedula");
			this.nombre = json.getString("nombre");
			this.apellidos = json.getString("apellidos");
			this.correo = json.getString("correo");
			
		} catch (JSONException e) {
			
			e.printStackTrace();
			
			System.out.println("No se pudo parsear Compra de JSON");
			
		}
		
	}
	
	public static ArrayList<Usuario> parseJSON (String strJSON) {
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			
			JSONObject json = new JSONObject(strJSON);
			JSONArray jsonUsuarios = json.getJSONArray("usuario");
			
			JSONObject jsonUsuario;
			
			for (int x = 0; x < jsonUsuarios.length(); x ++) {
				
				jsonUsuario = new JSONObject(jsonUsuarios.get(x).toString());
				
				usuarios.add(new Usuario(jsonUsuario));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return usuarios;
		
	}
	
	public String getCedula () {
		
		return this.cedula;
		
	}
	
	public void setCedula (String cedula) {
		
		this.cedula = cedula;
		
	}
	
	public String getNombre () {
		
		return this.nombre;
		
	}
	
	public void setNombre (String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public String getApellidos () {
		
		return this.apellidos;
		
	}
	
	public void setApellidos (String apellidos) {
		
		this.apellidos = apellidos;
		
	}
	
	public String getCorreo() {
		
		return this.correo;
		
	}
	
	public String getCorreoFormateado () {
		
		String correoF = "";
		
		correoF = this.correo.replace("@", "__ARROBA__");
		correoF = correoF.replace(".", "__DOT__");
		
		return correoF;
		
	}
	
	public void setCorreo (String correo) {
		
		this.correo = correo;
		
	}
	
}