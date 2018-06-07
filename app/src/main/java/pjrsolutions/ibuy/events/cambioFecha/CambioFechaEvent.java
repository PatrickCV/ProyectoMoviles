package pjrsolutions.ibuy.events.cambioFecha;

import java.util.EventObject;

import pjrsolutions.ibuy.view.FechaView;

public class CambioFechaEvent extends EventObject {
	
	private FechaView fechaView;
	
	public CambioFechaEvent (FechaView fechaView) {
		
		super(fechaView);
		
		this.fechaView = fechaView;
		
	}
	
	public FechaView getFechaView () {
		
		return this.fechaView;
		
	}
	
}
