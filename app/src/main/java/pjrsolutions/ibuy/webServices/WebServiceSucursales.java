package pjrsolutions.ibuy.webServices;

import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import pjrsolutions.ibuy.business.historialCompras.FiltrarCompras;
import pjrsolutions.ibuy.webServices.base.WebServiceCliente;

public class WebServiceSucursales extends WebServiceCliente {
	
	private static final String URL_GET_PAGINA = "http://patrickconejo14.000webhostapp.com/webServiceSucursales/getSucursales()";
	
	private FiltrarCompras filtrarCompras;
	
	public WebServiceSucursales(FiltrarCompras filtrarCompras) {
		
		super();
		
		this.filtrarCompras = filtrarCompras;
		
	}
	
	@Override
	protected String doInBackground (String... params) {
		
		this.cargarSucursales();
		
		return null;
		
	}
	
	@Override
	protected void onProgressUpdate (String... params) {
		
		String valor = params[0];
		
		if (valor.equals("exito")) {
			
			String[] sucursales = {};
			
			try {
				
				JSONObject json = new JSONObject(this.getRespuesta());
				JSONArray jsonSucursales = json.getJSONArray("sucursales");
				
				sucursales = new String[jsonSucursales.length()];
				
				for (int x = 0; x < jsonSucursales.length(); x ++) {
					
					sucursales[x] = jsonSucursales.getString(x);
					
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.filtrarCompras.getContext(), android.R.layout.simple_spinner_item, sucursales);
			this.filtrarCompras.getSpCambiarSucursalEspecifica().setAdapter(adapter);
			
		} else if (valor.equals("error")) {
			
			this.accionError();
			
		}
	
	}
	
	@Override
	protected void onPreExecute () {}
	
	@Override
	protected void onPostExecute (String valor) {}
	
	private void cargarSucursales () {
		
		this.setUrl(WebServiceSucursales.URL_GET_PAGINA);
		
		this.trabajo();
		
	}
	
	private void accionError () {
		
		System.out.println("Error");
	
	}
	
}
