package pjrsolutions.ibuy.events.finDeLista;

import java.util.EventObject;

import pjrsolutions.ibuy.view.base.ListaAbstracta;

/**
 Evento de llegar al final de una ListaAbstracta.
 */
public class FinDeListaEvent extends EventObject {
	
	private ListaAbstracta lista; // ListaAbstracta relacionada.
	
	/**
	 Constructor. Genera el evento. Recibe una ListaAbstracta.
	 */
	public FinDeListaEvent (ListaAbstracta lista) {
		
		super(lista);
		
		this.lista = lista;
		
	}
	
	/**
	 Sets & Gets.
	 */
	
	public ListaAbstracta getLista () {
		
		return this.lista;
		
	}
	
}
