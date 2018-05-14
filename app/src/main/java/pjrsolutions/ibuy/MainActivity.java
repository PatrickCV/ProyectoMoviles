package pjrsolutions.ibuy;

import android.os.Bundle;

import pjrsolutions.ibuy.business.base.ActividadAbstracta;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.business.pruebas.FragmentoPrueba;

public class MainActivity extends ActividadAbstracta {
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
		
			this.nuevoFragmento(new FragmentoPrueba());
		
		}
		
	}
	
}
