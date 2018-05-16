package pjrsolutions.ibuy.business.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.business.menuPrincipal.MenuPrincipal;

/**
 	Esta clase atiende los eventos generados en el fragmento del Login.
 	Controla el acceso a la aplicacion.
*/
public class Login extends FragmentoAbstracto {
	
	private EditText etUsuario;
	private EditText etPassword;
	private Button btnEntrar;
	
	private OnClickListener clickListener; // Escucha de clicks.
	
	public Login () {
	
	}
	
	/*
		Crea la vista.
	*/
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		
		// Cambiar titulo.
		this.getActivity().setTitle("iBuy");
		
		// Capturar las vistas.
		View view = (View) inflater.inflate(R.layout.fragment_login, container, false);
		this.etUsuario = (EditText)view.findViewById(R.id.etUsuarioLogin);
		this.etPassword = (EditText)view.findViewById(R.id.etPasswordLogin);
		this.btnEntrar = (Button)view.findViewById(R.id.btnEntrarLogin);
		
		// Definir eventos.
		this.eventosClick();
		
		// Asignar escuchas.
		this.btnEntrar.setOnClickListener(this.clickListener);
		
		return view;
		
	}
	
	/*
		Eventos de hacer click.
	*/
	private void eventosClick () {
	
		this.clickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				if (v.getId() == R.id.btnEntrarLogin) { // Entrar.
				
					String usuario = String.valueOf(etUsuario.getText()).trim();
					String password = String.valueOf(etPassword.getText()).trim();
					
					if (logueo(usuario, password)) {
						
						// Cambiar fragmento.
						nuevoFragmento(new MenuPrincipal());
						
					}
				
				}
			
			}
			
		};
	
	}
	
	/*
		Comprueba el usuario y el password.
	*/
	private boolean logueo (String usuario, String password) {
		
		ArrayList<String> mensajes = new ArrayList<String>();
		boolean todoBien = true;
		
		// Debug.
//		Toast.makeText(getContext(), "Usuario: " + usuario + ", Pass: " + password, Toast.LENGTH_SHORT).show();
		
		if (usuario.equals("")) { // Usuario vacio.
			
//			todoBien = false;
			mensajes.add("# Debe ingresar el usuario");
			
		}
		
		if (password.equals("")) { // Password vacio.
			
//			todoBien = false;
			mensajes.add("# Debe ingresar el password");
			
		}
		
		if (todoBien) { // Todo va bien.
					
			/*
				Hacer llamado a web service aqui.
			*/
			boolean usuarioExiste = true;
			boolean logueoCorrecto = true;
			
			if (usuarioExiste) { // Usuario existe.
				
				if (!logueoCorrecto) {
					
					todoBien = false;
					mensajes.add("# El usuario no existe");
					
				}
				
			} else { // Usuario no existe.
				
				todoBien = false;
				mensajes.add("# Password incorrecta");
				
			}
			
		}
		
		if (todoBien) { // Todo bien.
			
			// Dejar pasar.
//			Toast.makeText(getContext(), "Super seguridad :v", Toast.LENGTH_SHORT).show();
			
			return true;
			
		} else { // Hay problemas.
			
			StringBuilder mensaje = new StringBuilder();
			
			for (int x = 0; x < mensajes.size(); x ++) {
				
				mensaje.append(mensajes.get(x) + "\n");
				
			}
			
			Toast.makeText(getContext(), mensaje.toString(), Toast.LENGTH_SHORT).show();
			
		}
		
		return false;
		
	}
	
}
