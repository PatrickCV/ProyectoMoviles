package pjrsolutions.ibuy.events.clickCheckboxCompuesto;

import java.util.EventObject;

import pjrsolutions.ibuy.view.CheckboxCompuestoView;

public class ClickCheckboxCompuestoEvent extends EventObject {
	
	private CheckboxCompuestoView checkboxCompuestoView;
	
	public ClickCheckboxCompuestoEvent(CheckboxCompuestoView checkboxCompuestoView) {
		
		super(checkboxCompuestoView);
		
		this.checkboxCompuestoView = checkboxCompuestoView;
		
	}
	
	public CheckboxCompuestoView getCheckboxCompuestoView () {
		
		return this.checkboxCompuestoView;
		
	}
	
}
