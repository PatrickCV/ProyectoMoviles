package pjrsolutions.ibuy.conf;

import android.content.SharedPreferences;

import pjrsolutions.ibuy.conf.base.ConfiguracionAbstracta;

public class FiltrarComprasConf extends ConfiguracionAbstracta {
	
	public static final String CONF_FILE = "filtrar_compras_conf";
	public static final String ORDEN_RC = "orden_rc";
	public static final String FECHA_RC = "fecha_rc";
	public static final String SUCURSAL_RC = "sucursal_rc";
	public static final String DESDE_CC = "desde_cc";
	public static final String HASTA_CC = "hasta_cc";
	public static final String ORDEN = "orden";
	public static final String FECHA_ESPECIFICA = "fechaEspecifica";
	public static final String DESDE_FECHA = "desdeFecha";
	public static final String HASTA_FECHA = "hastaFecha";
	public static final String SUCURSAL = "Sucursal";
	public static final String MONTO_MINIMO = "montoMinimo";
	
	private int orden_rc;
	private int fecha_rc;
	private int sucursal_rc;
	private boolean desde_cc;
	private boolean hasta_cc;
	
	private String orden;
	private String fechaEspecifica;
	private String desdeFecha;
	private String hastaFecha;
	private String sucursal;
	private float montoMinimo;
	
	public FiltrarComprasConf (SharedPreferences sharedPreferences) {
		
		super(sharedPreferences);
		
		this.orden_rc = this.fecha_rc = this.sucursal_rc = 0;
		
		this.desde_cc = this.hasta_cc = false;
		
		this.orden =
				this.fechaEspecifica =
						this.desdeFecha =
								this.hastaFecha =
										this.sucursal = "";
		this.montoMinimo = 0.0f;
		
	}
	
	@Override
	public void guardarConfiguracion () {
		
		SharedPreferences.Editor editor = this.getSharedPreferences().edit();
		
		editor.putInt(FiltrarComprasConf.ORDEN_RC, this.getOrden_rc());
		editor.putInt(FiltrarComprasConf.FECHA_RC, this.getFecha_rc());
		editor.putInt(FiltrarComprasConf.SUCURSAL_RC, this.getSucursal_rc());
		editor.putBoolean(FiltrarComprasConf.DESDE_CC, this.isDesde_cc());
		editor.putBoolean(FiltrarComprasConf.HASTA_CC, this.isHasta_cc());
		
		editor.putString(FiltrarComprasConf.ORDEN, this.getOrden());
		editor.putString(FiltrarComprasConf.FECHA_ESPECIFICA, this.getFechaEspecifica());
		editor.putString(FiltrarComprasConf.DESDE_FECHA, this.getDesdeFecha());
		editor.putString(FiltrarComprasConf.HASTA_FECHA, this.getHastaFecha());
		editor.putString(FiltrarComprasConf.SUCURSAL, this.getSucursal());
		editor.putFloat(FiltrarComprasConf.MONTO_MINIMO, this.getMontoMinimo());
		
		editor.commit();
		
	}
	
	@Override
	public void cargarConfiguracion () {
		
		this.setOrden_rc(this.getSharedPreferences().getInt(FiltrarComprasConf.ORDEN_RC, 0));
		this.setFecha_rc(this.getSharedPreferences().getInt(FiltrarComprasConf.FECHA_RC, 0));
		this.setSucursal_rc(this.getSharedPreferences().getInt(FiltrarComprasConf.SUCURSAL_RC, 0));
		this.setDesde_cc(this.getSharedPreferences().getBoolean(FiltrarComprasConf.DESDE_CC, false));
		this.setHasta_cc(this.getSharedPreferences().getBoolean(FiltrarComprasConf.HASTA_CC, false));
		
		this.setOrden(this.getSharedPreferences().getString(FiltrarComprasConf.ORDEN, "desc"));
		this.setFechaEspecifica(this.getSharedPreferences().getString(FiltrarComprasConf.FECHA_ESPECIFICA, "---"));
		this.setDesdeFecha(this.getSharedPreferences().getString(FiltrarComprasConf.DESDE_FECHA, "---"));
		this.setHastaFecha(this.getSharedPreferences().getString(FiltrarComprasConf.HASTA_FECHA, "---"));
		this.setSucursal(this.getSharedPreferences().getString(FiltrarComprasConf.SUCURSAL, "---"));
		this.setMontoMinimo(this.getSharedPreferences().getFloat(FiltrarComprasConf.MONTO_MINIMO, 0.0f));
		
	}
	
	public String getOrden () {
		
		return this.orden;
		
	}
	
	public void setOrden (String orden) {
		
		this.orden = orden;
		
	}
	
	public String getFechaEspecifica () {
		
		return this.fechaEspecifica;
		
	}
	
	public String getFechaEspecificaFormateada () {
		
		return this.fechaEspecifica.replace("/", "-");
		
	}
	
	public void setFechaEspecifica (String fechaEspecifica) {
		
		this.fechaEspecifica = fechaEspecifica;
		
	}
	
	public String getDesdeFecha () {
		
		return this.desdeFecha;
		
	}
	
	public String getDesdeFechaFormateada () {
		
		return this.desdeFecha.replace("/", "-");
		
	}
	
	public void setDesdeFecha (String desdeFecha) {
		
		this.desdeFecha = desdeFecha;
		
	}
	
	public String getHastaFecha () {
		
		return this.hastaFecha;
		
	}
	
	public String getHastaFechaFormateada () {
		
		return this.hastaFecha.replace("/", "-");
		
	}
	
	public void setHastaFecha (String hastaFecha) {
		
		this.hastaFecha = hastaFecha;
		
	}
	
	public String getSucursal () {
		
		return this.sucursal;
		
	}
	
	public void setSucursal (String sucursal) {
		
		this.sucursal = sucursal;
		
	}
	
	public float getMontoMinimo () {
		
		return this.montoMinimo;
		
	}
	
	public void setMontoMinimo (float montoMinimo) {
		
		this.montoMinimo = montoMinimo;
		
	}
	
	public int getOrden_rc () {
		
		return this.orden_rc;
		
	}
	
	public void setOrden_rc (int orden_rc) {
		
		this.orden_rc = orden_rc;
		
	}
	
	public int getFecha_rc () {
		
		return this.fecha_rc;
		
	}
	
	public void setFecha_rc (int fecha_rc) {
		
		this.fecha_rc = fecha_rc;
		
	}
	
	public int getSucursal_rc () {
		
		return this.sucursal_rc;
		
	}
	
	public void setSucursal_rc (int sucursal_rc) {
		
		this.sucursal_rc = sucursal_rc;
		
	}
	
	public boolean isDesde_cc () {
		
		return this.desde_cc;
		
	}
	
	public void setDesde_cc (boolean desde_cc) {
		
		this.desde_cc = desde_cc;
		
	}
	
	public boolean isHasta_cc () {
		
		return this.hasta_cc;
		
	}
	
	public void setHasta_cc (boolean hasta_cc) {
		
		this.hasta_cc = hasta_cc;
		
	}
	
}