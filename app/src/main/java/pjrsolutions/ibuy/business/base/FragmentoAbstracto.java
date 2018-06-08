package pjrsolutions.ibuy.business.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import pjrsolutions.ibuy.datos.BaseDatosSQLite;

public class FragmentoAbstracto extends Fragment {
	public BaseDatosSQLite BD = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		BD = new BaseDatosSQLite(getActivity(), null);

	}
	
	public void nuevoFragmento (FragmentoAbstracto fragmento) {
	
		((ActividadAbstracta)this.getActivity()).nuevoFragmento(fragmento);

	}
	
	public SharedPreferences getSharedPrederences (String archivo) {
		
		return ((ActividadAbstracta)getActivity()).getSharedPrederences(archivo);
		
	}

}
