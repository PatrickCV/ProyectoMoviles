package pjrsolutions.ibuy.webServices;

import android.widget.Toast;

import pjrsolutions.ibuy.business.login.Login;
import pjrsolutions.ibuy.business.menuPrincipal.MenuPrincipal;
import pjrsolutions.ibuy.domain.Sesion;
import pjrsolutions.ibuy.domain.Usuario;
import pjrsolutions.ibuy.webServices.base.WebServiceCliente;

public class WebServiceLogin extends WebServiceCliente {
	
	private static final String URL_GET_PAGINA = "http://patrickconejo14.000webhostapp.com/webServiceLogin/verificarLogin(%s,%s)";
	
	private Login login;
	
	public WebServiceLogin (Login login) {
		
		super();
		
		this.login = login;
		
	}
	
	@Override
	protected String doInBackground (String... params) {
		
		if (params.length == 0) {
			
			return null;
			
		} else {
			
			this.verificarLogin(params);
			
		}
		
		return null;
		
	}
	
	@Override
	protected void onProgressUpdate (String... params) {
		
		String valor = params[0];
		
		if (valor.equals("cambiarEstadoBtnEntrar")) {
			
			this.login.cambiarEstadoBtnEntrar();
			
		} else if (valor.equals("login")) {
			
			this.login.nuevoFragmento(new MenuPrincipal());
			
		}  else if (valor.equals("noLogin")) {
			
			Toast.makeText(this.login.getContext(), "Usuario o pasword incorrecto", Toast.LENGTH_SHORT).show();
			
		} else if (valor.equals("exito")) {
			
			this.accionExito();
			
		} else if (valor.equals("error")) {
			
			this.accionError();
			
		}
		
	}
	
	@Override
	protected void onPreExecute () {}
	
	@Override
	protected void onPostExecute (String valor) {}
	
	private void verificarLogin (String... params) {
		
		this.publishProgress("cambiarEstadoBtnEntrar");
		
		this.setUrl(String.format(WebServiceLogin.URL_GET_PAGINA, (Object[])params));
		
		this.trabajo();
		
	}
	
	private void accionExito () {
		
		System.out.println("Exito: " + this.getRespuesta());
		
		this.publishProgress("cambiarEstadoBtnEntrar");
		
		Usuario usuario = Usuario.parseJSON(this.getRespuesta()).get(0);
		
		if (usuario.getCedula().equals("null")) { // Logueo erroneo.
			
			this.publishProgress("noLogin");
			
		} else { // Logueo correcto.
			
			Sesion.usuario = usuario;
			
			this.publishProgress("login");
			
		}
		
	}
	
	private void accionError () {
		
		System.out.println("Error");
		
		this.publishProgress("cambiarEstadoBtnEntrar");
		
	}
	
}
