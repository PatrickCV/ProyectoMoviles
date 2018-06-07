package pjrsolutions.ibuy.conf;

import android.content.SharedPreferences;

import pjrsolutions.ibuy.conf.base.ConfiguracionAbstracta;

public class FiltrarArticulosCompraConf extends ConfiguracionAbstracta {
	
	public static final String CONF_FILE = "filtrar_articulos_compra_conf";
	public static final String ORDENAR_POR = "ordenarPor";
	public static final String ORDEN = "orden";
	
	private int ordenarPor;
	private int orden;
	
	public FiltrarArticulosCompraConf(SharedPreferences sharedPreferences) {
		
		super(sharedPreferences);
		
		this.ordenarPor = this.orden = 0;
		
	}
	
	@Override
	public void guardarConfiguracion () {
		
		SharedPreferences.Editor editor = this.getSharedPreferences().edit();
		
		editor.putInt(FiltrarArticulosCompraConf.ORDENAR_POR, this.getOrdenarPor());
		editor.putInt(FiltrarArticulosCompraConf.ORDEN, this.getOrden());
		
		editor.commit();
	
	}
	
	@Override
	public void cargarConfiguracion () {
	
		this.setOrdenarPor(this.getSharedPreferences().getInt(FiltrarArticulosCompraConf.ORDENAR_POR, 0));
		this.setOrden(this.getSharedPreferences().getInt(FiltrarArticulosCompraConf.ORDEN, 0));
	
	}
	
	public int getOrdenarPor () {
		
		return this.ordenarPor;
		
	}
	
	public void setOrdenarPor (int ordenarPor) {
		
		this.ordenarPor = ordenarPor;
		
	}
	
	public int getOrden () {
		
		return this.orden;
		
	}
	
	public void setOrden (int orden) {
		
		this.orden = orden;
		
	}
	
}
