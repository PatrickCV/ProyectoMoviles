package pjrsolutions.ibuy.business.base;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pjrsolutions.ibuy.R;

public class ActividadAbstracta extends AppCompatActivity {
	
	private boolean puedeCambiarFragmento = true;
	
	/*
		Retorna true si puede cambiar.
	*/
	public boolean puedeCambiarFragmento () {
		
		if (this.getSupportFragmentManager().getBackStackEntryCount() == 1) {
			
			return false;
			
		}
		
		return this.puedeCambiarFragmento;
		
	}
	
	/*
		Cambia el estado de this.puedeCambiarFragmento.
	*/
	public void puedeCambiarFragmento (boolean puedeCambiarFragmento) {
		
		this.puedeCambiarFragmento = puedeCambiarFragmento;
		
	}
	
	/*
		Agrega nuevo fragmento. Recibe un fragmento.
	*/
	public void agregar (FragmentoAbstracto fragmento) {
		
		// Initializa un fragment transaction.
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		// Reemplaza el fragmento antiguo con el nuevo.
		ft.replace(R.id.contenedor, fragmento);
		
		// Hace una animacion.
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		
		// Limpia la memoria del boton de ir atras.
		ft.addToBackStack(String.valueOf(fragmento));
		ft.commitAllowingStateLoss();
		
	}
	
	/*
		Genera nuevo fragmento.
	*/
	public void nuevoFragmento (FragmentoAbstracto fragmento) {
		
		(this.getSupportFragmentManager().beginTransaction()
				.replace(R.id.contenedor, fragmento))
				.addToBackStack(null)
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				.commit();
		
	}
	
	/*
		Accion de ir atras con el boton.
	*/
	@Override
	public void onBackPressed () {
		
		// Verifica si se puede ir atras.
		if (this.puedeCambiarFragmento()) {
			
			this.getSupportFragmentManager().popBackStack(); // Ir hacia atras.
			
		} else { // No puede ir atras.
			
			this.moveTaskToBack(true); // minimizar.
			
		}
		
	}

}
