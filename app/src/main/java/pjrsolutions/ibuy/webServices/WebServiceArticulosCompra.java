package pjrsolutions.ibuy.webServices;

import java.util.ArrayList;

import pjrsolutions.ibuy.business.historialCompras.InfoCompra;
import pjrsolutions.ibuy.domain.ArticuloCompra;
import pjrsolutions.ibuy.webServices.base.WebServiceCliente;

public class WebServiceArticulosCompra extends WebServiceCliente {
	
	private static final String URL_GET_PAGINA = "http://patrickconejo14.000webhostapp.com/webServiceArticulosCompra/getPagina(%s,%s,%s,%s)";
	
	private InfoCompra infoCompra;
	
	public WebServiceArticulosCompra(InfoCompra infoCompra) {
		
		super();
		
		this.infoCompra = infoCompra;
		
	}
	
	@Override
	protected String doInBackground (String... params) {
		
		if (params.length == 0) {
			
			return null;
			
		} else {
			
			if (this.infoCompra.getListaArticulosCompraViews().isAtendiendoFinDeLista()) return null;
			
			this.getPagina(params);
			
		}
		
		return null;
		
	}
	
	@Override
	protected void onProgressUpdate (String... params) {
		
		String valor = params[0];
		
		if (valor.equals("animacion")) {
			
			this.infoCompra.getListaArticulosCompraViews().changeAtendiendoFinDeLista();
			
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
	
	private void getPagina (String... params) {
		
		this.setUrl(String.format(WebServiceArticulosCompra.URL_GET_PAGINA, (Object[])params));
		
		this.publishProgress("animacion");
		this.trabajo();
		this.publishProgress("animacion");
		
	}
	
	private void accionExito () {
		
		System.out.println("Exito: " + this.getRespuesta());
		
		ArrayList<ArticuloCompra> articulosCompra = ArticuloCompra.parseJSON(this.getRespuesta());
		
		for (ArticuloCompra articuloCompra : articulosCompra) {
			
			this.infoCompra.getListaArticulosCompraViews().add(articuloCompra);
			
		}
		
	}
	
	private void accionError () {
		
		System.out.println("Error");
		
	}
	
}
