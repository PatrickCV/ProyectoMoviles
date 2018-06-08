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
import pjrsolutions.ibuy.webServices.WebServiceLogin;

/**
 	Esta clase atiende los eventos generados en el fragmento del Login.
 	Controla el acceso a la aplicacion.
*/
public class Login extends FragmentoAbstracto implements View.OnClickListener {
	
	private WebServiceLogin webServiceLogin;
	
	private EditText etUsuario;
	private EditText etPassword;
	private Button btnEntrar;
	private Button btnRegistrar;
	
	public Login () {
	
	}
	
	/**
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
		this.btnRegistrar = (Button)view.findViewById(R.id.btnRegistrarLogin);
		
		// Asignar escuchas.
		this.btnEntrar.setOnClickListener(this);
		this.btnRegistrar.setOnClickListener(this);
		
		return view;
		
	}
	
	/**
		Eventos de hacer click.
	*/
	@Override
	public void onClick (View v) {
		
		if (v.getId() == R.id.btnEntrarLogin) { // Entrar.
			
			String usuario = etUsuario.getText().toString().trim();
			usuario = usuario.replace("@", "__ARROBA__");
			usuario = usuario.replace(".", "__DOT__");
			String password = etPassword.getText().toString().trim();
			
			if (usuario.equals("") || password.equals("")) {
				
				Toast.makeText(getContext(), "Debe completar los campos", Toast.LENGTH_SHORT).show();
				
			} else {
				
				this.webServiceLogin = new WebServiceLogin(this);
				this.webServiceLogin.execute(usuario, password);
				
			}
			
		} else if (v.getId() == R.id.btnRegistrarLogin) { // Registrar.
		
//			this.nuevoFragmento(new Registrar_Usuario());
		
		}
	
	}
	
	public void cambiarEstadoBtnEntrar () {
		
		this.btnEntrar.setEnabled(!this.btnEntrar.isEnabled());
		
	}
	
}
