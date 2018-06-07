package pjrsolutions.ibuy.events.clickRadioCompuesto;

import java.util.EventObject;

import pjrsolutions.ibuy.view.RadioCompuestoView;

public class ClickRadioCompuestoEvent extends EventObject {
	
	private RadioCompuestoView radioCompuestoView;
	
	public ClickRadioCompuestoEvent(RadioCompuestoView radioCompuestoView) {
		
		super(radioCompuestoView);
		
		this.radioCompuestoView = radioCompuestoView;
		
	}
	
	public RadioCompuestoView getRadioCompuestoView () {
		
		return this.radioCompuestoView;
		
	}
	
}
