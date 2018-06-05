package pjrsolutions.ibuy.events.finDeLista;

import java.util.EventObject;

import pjrsolutions.ibuy.view.base.ListaAbstracta;
import pjrsolutions.ibuy.view.base.ListaDinamicaAbstracta;

/**
 Evento de llegar al final de una ListaAbstracta.
 */
public class FinDeListaEvent extends EventObject {
	
	private ListaDinamicaAbstracta lista; // ListaAbstracta relacionada.
	
	/**
		Constructor. Genera el evento. Recibe una ListaAbstracta.
	*/
	public FinDeListaEvent (ListaDinamicaAbstracta lista) {
		
		super(lista);
		
		this.lista = lista;
		
	}
	
	/**
		Sets & Gets.
	*/
	
	public ListaDinamicaAbstracta getLista () {
		
		return this.lista;
		
	}
	
}
