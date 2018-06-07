package pjrsolutions.ibuy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.events.clickGrupoRadioCompuesto.ClickGrupoRadioCompuestoEvent;
import pjrsolutions.ibuy.events.clickGrupoRadioCompuesto.ClickGrupoRadioCompuestoListener;
import pjrsolutions.ibuy.events.clickRadioCompuesto.ClickRadioCompuestoEvent;
import pjrsolutions.ibuy.events.clickRadioCompuesto.ClickRadioCompuestoListener;

public class GrupoRadioCompuestoView extends LinearLayout {
	
	private static final int elementoId = R.layout.grupo_radio_compuesto_view;
	
	private View rootView; // Estado de la vista.
	
	private Object valor;
	
	private ArrayList<RadioCompuestoView> radioCompuestoViews;
	
	private int radioCompuestoViewSeleccionado;
	
	private ClickGrupoRadioCompuestoListener clickGrupoRadioCompuestoListener;
	private ArrayList<ClickGrupoRadioCompuestoListener> clickGrupoRadioCompuestoListeners;
	
	/**
	 
	 */
	public GrupoRadioCompuestoView (Context context) {
		
		super(context);
		
		this.valor = "-nada-";
		this.radioCompuestoViews = new ArrayList<RadioCompuestoView>();
		this.radioCompuestoViewSeleccionado = 0;
		this.clickGrupoRadioCompuestoListeners = new ArrayList<ClickGrupoRadioCompuestoListener>();
		
		this.initView(context);
		
	}
	
	/**
	 
	 */
	public GrupoRadioCompuestoView (Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
		this.valor = "-nada-";
		this.radioCompuestoViews = new ArrayList<RadioCompuestoView>();
		this.radioCompuestoViewSeleccionado = 0;
		this.clickGrupoRadioCompuestoListeners = new ArrayList<ClickGrupoRadioCompuestoListener>();
		
		this.initView(context);
		
	}
	
	/**
	 
	 */
	private void initView (Context context) {
		
		this.rootView = LinearLayout.inflate(context, GrupoRadioCompuestoView.elementoId, this);
		
		this.eventosClickGrupoRadioCompuestos();
		
		this.setOrientation(LinearLayout.VERTICAL);
		
	}
	
	/**
	 
	 */
	private void eventosClickGrupoRadioCompuestos () {
		
		this.clickGrupoRadioCompuestoListeners.add(new ClickGrupoRadioCompuestoListener() {
			
			@Override
			public void clickGrupoRadioCompuesto (ClickGrupoRadioCompuestoEvent event) {
				
				GrupoRadioCompuestoView grupo = event.getGrupoRadioCompuestoView();
				
				grupo.switchearRadioCompuestos();
				
				// Debug.
				RadioCompuestoView radio = grupo.getRadioCompuestoViewSeleccionado();
				
				grupo.setValor(radio.getValor());

//				Toast.makeText(getContext(), "PASO " + radio.getText(), Toast.LENGTH_SHORT).show();
			
			}
			
		});
		
	}
	
	/**
	 
	 */
	private void notifyClickGrupoRadioCompuestoListeners () {
		
		ClickGrupoRadioCompuestoEvent event;
		
		if (this.clickGrupoRadioCompuestoListener != null) {
			
			event = new ClickGrupoRadioCompuestoEvent(this);
			
			this.clickGrupoRadioCompuestoListener.clickGrupoRadioCompuesto(event);
			
		}
		
		for (ClickGrupoRadioCompuestoListener listener : this.clickGrupoRadioCompuestoListeners) {
			
			event = new ClickGrupoRadioCompuestoEvent(this);
			
			listener.clickGrupoRadioCompuesto(event);
			
		}
		
	}
	
	public void setClickGrupoRadioCompuestoListener (ClickGrupoRadioCompuestoListener clickGrupoRadioCompuestoListener) {
		
		this.clickGrupoRadioCompuestoListener = clickGrupoRadioCompuestoListener;
		
	}
	
	public void addClickGrupoRadioCompuestoListener (ClickGrupoRadioCompuestoListener clickGrupoRadioCompuestoListener) {
		
		this.clickGrupoRadioCompuestoListeners.add(clickGrupoRadioCompuestoListener);
		
	}
	
	/**
	 * Genera un ClickRadioCompuestoListener.
	 */
	private ClickRadioCompuestoListener generarClickRadioCompuestoListener () {
		
		return new ClickRadioCompuestoListener() {
			
			@Override
			public void clickRadioCompuesto (ClickRadioCompuestoEvent event) {
				
				// Debug.
//				Toast.makeText(getContext(), event.getRadioCompuestoView().getText(), Toast.LENGTH_SHORT).show();
				
				radioCompuestoViewSeleccionado = getIndiceRadioCompuestoView(event.getRadioCompuestoView());
				
				// Debug.
//				Toast.makeText(getContext(), String.valueOf(radioCompuestoViewSeleccionado), Toast.LENGTH_SHORT).show();
				
				notifyClickGrupoRadioCompuestoListeners();
				
			}
			
		};
		
	}
	
	/**
	 * Selecciona un RadioCompuestoView y deselecciona
	 */
	private void switchearRadioCompuestos () {
		
		RadioCompuestoView radioSeleccionado = this.getRadioCompuestoViewSeleccionado();
		
		for (RadioCompuestoView radio : this.radioCompuestoViews) {
			
			radio.setSelected(radio == radioSeleccionado);
			
		}
		
	}
	
	/**
	 * Retorna el indice de un radio.
	 *
	 * @param radio: radio.
	 */
	public int getIndiceRadioCompuestoView (RadioCompuestoView radio) {
		
		for (int x = 0; x < this.radioCompuestoViews.size(); x ++) {
			
			if (radio == this.radioCompuestoViews.get(x)) {
				
				return x;
				
			}
			
		}
		
		return -1;
		
	}
	
	/**
	 Agrega un nuevo RadioCompuestoView. Programativo.
	 */
	@Override
	public void addView (View radio) {
		
		if (radio.getClass() == RadioCompuestoView.class) {
			
			this.radioCompuestoViews.add((RadioCompuestoView)radio);
			
			((RadioCompuestoView)radio).addClickRadioCompuestoViewListener(generarClickRadioCompuestoListener());
			
			((RadioCompuestoView)radio).setSelected(this.radioCompuestoViews.size() == 1);
			
			if (this.radioCompuestoViews.size() == 1) {
				
				this.setValor(((RadioCompuestoView)radio).getValor());
				
			}
			
		}
		
		super.addView(radio);
		
	}
	
	/**
	 Agrega un nuevo RadioCompuestoView. Para la paleta.
	 */
	@Override
	public void addView (View radio, ViewGroup.LayoutParams params) {
		
		if (radio.getClass() == RadioCompuestoView.class) {
			
			this.radioCompuestoViews.add((RadioCompuestoView)radio);
			
			((RadioCompuestoView)radio).addClickRadioCompuestoViewListener(generarClickRadioCompuestoListener());
			
			((RadioCompuestoView)radio).setSelected(this.radioCompuestoViews.size() == 1);
			
		}
		
		super.addView(radio, params);
		
	}
	
	public RadioCompuestoView getRadioCompuestoViewSeleccionado () {
		
		return this.radioCompuestoViews.get(this.radioCompuestoViewSeleccionado);
		
	}
	
	public void setValor (Object valor) {
		
		this.valor = valor;
		
	}
	
	public Object getValor () {
		
		return this.getRadioCompuestoViewSeleccionado().getTag();
		
	}
	
	public void setSelected (int pos) {
		
		for (int x = 0; x < this.radioCompuestoViews.size(); x ++) {
			
			this.radioCompuestoViews.get(x).setSelected(x == pos);
			
		}
		
	}
	
}
