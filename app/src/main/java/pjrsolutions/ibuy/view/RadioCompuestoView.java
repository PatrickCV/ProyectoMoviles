package pjrsolutions.ibuy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.events.clickRadioCompuesto.ClickRadioCompuestoEvent;
import pjrsolutions.ibuy.events.clickRadioCompuesto.ClickRadioCompuestoListener;

public class RadioCompuestoView extends LinearLayout {
	
	private static final int elementoId = R.layout.radio_compuesto_view;
	
	private String text;
	private Object valor;
	
	private View rootView; // Estado de la vista.
	private RadioButton rbRadio;
	private View vComponente;
	
	private RadioButton.OnClickListener clickListener;
	private ClickRadioCompuestoListener clickRadioCompuestoListener;
	private ArrayList<ClickRadioCompuestoListener> clickRadioCompuestoListeners;
	
	public RadioCompuestoView (Context context) {
		
		super(context);
		
		this.text = "Texto por defecto";
		this.valor = "-nada-";
		
		this.clickRadioCompuestoListeners = new ArrayList<ClickRadioCompuestoListener>();
		
		this.initView(context);
		
	}
	
	public RadioCompuestoView (Context context, AttributeSet attrs) {
		
		super(context, attrs);
		
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RadioCompuestoView, 0, 0);
		
		try {
			
			this.text = a.getString(R.styleable.RadioCompuestoView_RCV_text);
			
			if (this.text == null || this.text.equals("")) {
				
				this.text = "Texto por defecto";
				
			}
			
		} catch (Exception e) {
			
			a.recycle();
			
		}
		
		this.valor = "-nada-";
		
		this.clickRadioCompuestoListeners = new ArrayList<ClickRadioCompuestoListener>();
		
		this.initView(context);
		
	}
	
	private void initView (Context context) {
		
		// Capturar vistas.
		this.rootView = LinearLayout.inflate(context, RadioCompuestoView.elementoId, this);
		this.rbRadio = (RadioButton)this.rootView.findViewById(R.id.rbRadioRadioCompuestoView);
		this.vComponente = (View)this.getChildAt(1);
		
		this.rbRadio.setText(this.text);
		
		// Definir eventos.
		this.eventosClick();
		
		// Asignar eventos.
		this.rbRadio.setOnClickListener(this.clickListener);
		
	}
	
	private void eventosClick () {
		
		this.clickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (v.getId() == R.id.rbRadioRadioCompuestoView) {
					
					notifyClickRadioCompuestoViewListeners();
					
				}
				
			}
			
		};
		
	}
	
	private void notifyClickRadioCompuestoViewListeners () {
		
		ClickRadioCompuestoEvent event;
		
		if (this.clickRadioCompuestoListener != null) {
			
			event = new ClickRadioCompuestoEvent(this);
			
			this.clickRadioCompuestoListener.clickRadioCompuesto(event);
			
		}
		
		for (ClickRadioCompuestoListener listener : this.clickRadioCompuestoListeners) {
			
			event = new ClickRadioCompuestoEvent(this);
			
			listener.clickRadioCompuesto(event);
			
		}
		
	}
	
	/*
		Sets, Gets y Otros.
	*/
	
	@Override
	public void addView(View child) {
		
		if (this.vComponente != null) {
			
			return;
			
		}
		
		this.vComponente = child;
		
		super.addView(this.vComponente);
		
	}
	
	@Override
	public void addView(View child, ViewGroup.LayoutParams params) {
		
		if (this.vComponente != null) {
			
			return;
			
		}
		
		this.vComponente = child;
		
		super.addView(this.vComponente, params);
		
	}
	
	public void setvComponente (View vComponente) {
		
		this.vComponente = vComponente;
		
	}
	
	public View getvComponente () {
		
		return this.vComponente;
		
	}
	
	/**
	 * Habilita o deshabilita recursivamente una vista,
	 * tanto si es layout o no.
	 *
	 * @param v: Vista.
	 * @param enable: habilitar/deshabilitar.
	 */
	private static void setEnableR (View v, boolean enable) {
		
		// Si cae es porque intenta obtener los hijos de una vista que no es layout.
		// Condicion de parada.
		try {
			
			if (v.getClass() == CheckboxCompuestoView.class) {
				
				((CheckboxCompuestoView)v).setEnabled(enable);
				
			} else {
				
				v.setEnabled(enable); // Habilitar/deshabilitar.
				
				for (int x = 0; x < ((ViewGroup)v).getChildCount(); x ++) {
					
					// Llamado recursivo.
					setEnableR(((ViewGroup)v).getChildAt(x), enable);
					
				}
				
			}
			
		} catch (Exception e) {
		
		}
		
	}
	
	/**
	 * Selecciona/deselecciona this.vComponente, si existe.
	 *
	 * @param selected: seleccionar/deseleccionar.
	 */
	@Override
	public void setSelected (boolean selected) {
		
		this.rbRadio.setChecked(selected);
		
		if (this.vComponente != null) {
			
			// Habilita o deshabilita recursivamente la vista.
			RadioCompuestoView.setEnableR(this.vComponente, selected);
			
		}
		
	}
	
	@Override
	public boolean isSelected () {
		
		return this.rbRadio.isSelected();
		
	}
	
	public void setText (CharSequence text) {
		
		this.text = text.toString();
		
		this.rbRadio.setText(text);
		
	}
	
	public CharSequence getText () {
		
		return this.text;
		
	}
	
	public void setClickRadioCompuestoListener (ClickRadioCompuestoListener clickRadioCompuestoListener) {
		
		this.clickRadioCompuestoListener = clickRadioCompuestoListener;
		
	}
	
	public void addClickRadioCompuestoViewListener (ClickRadioCompuestoListener clickRadioCompuestoListener) {
		
		this.clickRadioCompuestoListeners.add(clickRadioCompuestoListener);
		
	}
	
	public void setValor (Object valor) {
		
		this.valor = valor;
		
		if (this.vComponente != null) {
			
			this.vComponente.setTag(this.valor);
			
		}
		
	}
	
	public Object getValor () {
		
		return this.valor;
		
	}
	
}
