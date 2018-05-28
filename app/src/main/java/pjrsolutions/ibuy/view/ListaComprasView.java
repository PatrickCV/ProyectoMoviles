package pjrsolutions.ibuy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.historialCompras.HistorialCompras;
import pjrsolutions.ibuy.business.historialCompras.InfoCompra;
import pjrsolutions.ibuy.domain.Compra;
import pjrsolutions.ibuy.events.verMas.VerMasEvent;
import pjrsolutions.ibuy.events.verMas.VerMasListener;
import pjrsolutions.ibuy.view.base.ListaAbstracta;
import pjrsolutions.ibuy.view.base.ListaDinamicaAbstracta;

/**
	Clase que hereda de ListaAbstracta.
	Representa una lista de Compra.
*/
public class ListaComprasView extends ListaDinamicaAbstracta<Compra> {
	
	private HistorialCompras historialCompras;
	
	/**
	 Constructor programativo basico.
	 */
	public ListaComprasView (Context context) {
		
		super(context);
		
	}
	
	/**
	 Constructor programativo que recibe una lista de Compra.
	 */
	public ListaComprasView (Context context, ArrayList<Compra> objetos) {
		
		super(context, objetos);
		
	}
	
	/**
	 Constructor para la paleta.
	 */
	public ListaComprasView (Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
	}
	
	/**
	 Agrega un nuevo elemento.
	 */
	@Override
	public void agregarObjeto (Compra compra) {
		
		// Crea una CompraView.
		CompraView compraView = new CompraView(this.getContext(), compra);
		
		// Asigna un escucha VerMasListener.
		compraView.setVerMasListener(new VerMasListener() {
			
			@Override
			public void verMas (VerMasEvent event) {
				
				Compra compra = event.getCompraView().getCompra();
				
				// Abre un fragmento InfoCompra.
				historialCompras.nuevoFragmento(InfoCompra.crearInfoCompra(compra));
				
			}
			
		});
		
		// Agregar la CompraView a this.llAreaObjetos
		this.getLlAreaObjetos().addView(compraView);
		
	}
	
	/**
	 Agrega un espacio entre los elementos.
	 */
	@Override
	public void agregarEspacio () {
		
		LinearLayout espacio = new LinearLayout(this.getContext());
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
		params.setMargins(10, 5, 10, 5);
		
		espacio.setLayoutParams(params);
		
		espacio.setBackgroundResource(R.color.green);
		
		this.getLlAreaObjetos().addView(espacio);
		
	}
	
	/**
	 Sets & Gets.
	 */
	
	public void setHistorialCompras (HistorialCompras historialCompras) {
		
		this.historialCompras = historialCompras;
		
	}
	
	public HistorialCompras getHistorialCompras () {
		
		return historialCompras;
		
	}
	
}