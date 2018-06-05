package pjrsolutions.ibuy.webServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pjrsolutions.ibuy.business.historialCompras.HistorialCompras;
import pjrsolutions.ibuy.domain.Compra;
import pjrsolutions.ibuy.webServices.base.WebServiceCliente;

public class WebServiceCompras extends WebServiceCliente {
	
	private static final String URL_GET_PAGINA = "http://patrickconejo14.000webhostapp.com/webServiceCompras/getPagina(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)";
	
	private static Map<String, String> URLS = (new HashMap<String, String>());
	
	private HistorialCompras historialCompras;
	
	public WebServiceCompras (HistorialCompras historialCompras) {
		
		super();
		
		this.putUrl("getPagina", WebServiceCompras.URL_GET_PAGINA);
		
		this.historialCompras = historialCompras;
		
	}
	
	@Override
	protected String doInBackground (String... params) {
		
		if (params.length == 0) {
			
			return null;
			
		} else {
			
			String[] valores = new String[params.length - 1];
			
			for (int x = 1; x < params.length; x ++) {
				
				valores[x - 1] = params[x];
				
				System.out.println(valores[x - 1]);
				
			}
			
			this.setTipo(params[0]);
			
			if (params[0].equals("getPagina")) {
				
				this.getPagina(valores);
				
			}
			
		}
		
		return null;
		
	}
	
	@Override
	protected void onProgressUpdate (String[] values) {
		
		String valor = values[0];
		
		if (valor.equals("animacion")) {
			
			this.historialCompras.getListaCompras().changeAtendiendoFinDeLista();
			
		} else if (valor.equals("exito")) {
			
			this.accionExito();
			
		} else if (valor.equals("error")) {
			
			this.accionError();
			
		}
		
	}
	
	@Override
	protected void onPreExecute () {
	
	}
	
	@Override
	protected void onPostExecute (String valor) {
	
	}
	
	private void getPagina (String... params) {
		
		this.setUrl(String.format(this.getUrls().get("getPagina"), (Object[])params));
		
		this.publishProgress("animacion");
		this.trabajo();
		this.publishProgress("animacion");
		
	}
	
	private void accionExito () {
		
		System.out.println("Exito: " + this.getRespuesta());
		
		ArrayList<Compra> compras = Compra.parseJSON(this.getRespuesta());
		
		for (Compra compra : compras) {
			
			this.historialCompras.getListaCompras().add(compra);
			
		}
		
	}
	
	private void accionError () {
		
		System.out.println("Error");
		
	}
	
}