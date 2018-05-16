package pjrsolutions.ibuy;

import android.os.Bundle;

import pjrsolutions.ibuy.business.base.ActividadAbstracta;
import pjrsolutions.ibuy.business.login.Login;

public class MainActivity extends ActividadAbstracta {
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			
			this.nuevoFragmento(new Login());
		
		}
		
	}
	
}
