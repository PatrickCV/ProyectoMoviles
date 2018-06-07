package pjrsolutions.ibuy.conf.base;

import android.content.SharedPreferences;

public abstract class ConfiguracionAbstracta {
	
	private SharedPreferences sharedPreferences;
	
	public ConfiguracionAbstracta (SharedPreferences sharedPreferences) {
		
		this.sharedPreferences = sharedPreferences;
		
	}
	
	public abstract void guardarConfiguracion ();
	
	public abstract void cargarConfiguracion ();
	
	public void setSharedPreferences (SharedPreferences sharedPreferences) {
		
		this.sharedPreferences = sharedPreferences;
		
	}
	
	public SharedPreferences getSharedPreferences () {
		
		return this.sharedPreferences;
		
	}
	
}