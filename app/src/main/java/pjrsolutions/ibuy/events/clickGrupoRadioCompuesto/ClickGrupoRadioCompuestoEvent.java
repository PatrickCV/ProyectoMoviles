package pjrsolutions.ibuy.events.clickGrupoRadioCompuesto;

import java.util.EventObject;

import pjrsolutions.ibuy.view.GrupoRadioCompuestoView;

public class ClickGrupoRadioCompuestoEvent extends EventObject {
	
	private GrupoRadioCompuestoView grupoRadioCompuestoView;
	
	public ClickGrupoRadioCompuestoEvent (GrupoRadioCompuestoView grupoRadioCompuestoView) {
		
		super(grupoRadioCompuestoView);
		
		this.grupoRadioCompuestoView = grupoRadioCompuestoView;
		
	}
	
	public GrupoRadioCompuestoView getGrupoRadioCompuestoView () {
		
		return this.grupoRadioCompuestoView;
		
	}
	
}
