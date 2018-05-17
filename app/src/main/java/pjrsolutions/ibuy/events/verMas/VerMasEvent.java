package pjrsolutions.ibuy.events.verMas;

import java.util.EventObject;

import pjrsolutions.ibuy.view.CompraView;

/**
 Evento de hacer click en ver mas en una CompraView.
 */
public class VerMasEvent extends EventObject {
	
	private CompraView compraView; // CompraView relacionada.
	
	/**
	 Constructor. Genera el evento. Recibe una CompraView.
	 */
	public VerMasEvent (CompraView compraView) {
		
		super(compraView);
		
		this.compraView = compraView;
		
	}
	
	/**
	 Sets & Gets.
	 */
	
	public CompraView getCompraView() {
		
		return this.compraView;
		
	}
}