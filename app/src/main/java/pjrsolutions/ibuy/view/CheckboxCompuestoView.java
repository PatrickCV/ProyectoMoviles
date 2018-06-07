package pjrsolutions.ibuy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.events.clickCheckboxCompuesto.ClickCheckboxCompuestoEvent;
import pjrsolutions.ibuy.events.clickCheckboxCompuesto.ClickCheckboxCompuestoListener;

public class CheckboxCompuestoView extends LinearLayout {
	
	private static final int elementoId = R.layout.checkbox_compuesto_view;
	
	private String text;
	private Object valor;
	
	private View rootView; // Estado de la vista.
	private CheckBox cbCheckbox;
	private View vComponente;
	
	private CheckBox.OnCheckedChangeListener checkedChangeListener;
	private ClickCheckboxCompuestoListener clickCheckboxCompuestoListener;
	private ArrayList<ClickCheckboxCompuestoListener> clickCheckboxCompuestoListeners;
	
	public CheckboxCompuestoView (Context context) {
		
		super(context);
		
		this.text = "Texto por defecto";
		
		this.clickCheckboxCompuestoListeners = new ArrayList<ClickCheckboxCompuestoListener>();
		
		this.initView(context);
		
	}
	
	public CheckboxCompuestoView (Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CheckboxCompuestoView, 0, 0);
		
		try {
			
			this.text = a.getString(R.styleable.CheckboxCompuestoView_CCV_text);
			
			if (this.text == null || this.text.equals("")) {
				
				this.text = "Texto por defecto";
				
			}
			
		} catch (Exception e) {
			
			a.recycle();
			
		}
		
		this.clickCheckboxCompuestoListeners = new ArrayList<ClickCheckboxCompuestoListener>();
		
		this.initView(context);
		
	}
	
	private void initView (Context context) {
		
		// Capturar vistas.
		this.rootView = LinearLayout.inflate(context, CheckboxCompuestoView.elementoId, this);
		this.cbCheckbox = (CheckBox)this.rootView.findViewById(R.id.cbCheckboxCheckboxCompuestoView);
		this.vComponente = (View)this.getChildAt(1);
		
		this.cbCheckbox.setText(this.text);
		
		this.eventosCheck();
		this.eventosClickCheckboxCompuesto();
		
		this.cbCheckbox.setOnCheckedChangeListener(this.checkedChangeListener);
		
	}
	
	private void eventosCheck () {
		
		this.checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged (CompoundButton v, boolean isChecked) {
				
				if (v.getId() == R.id.cbCheckboxCheckboxCompuestoView) {
					
					notifyClickCheckboxCompuestoListeners();
					
				}
				
			}
			
		};
		
	}
	
	private void eventosClickCheckboxCompuesto () {
		
		this.clickCheckboxCompuestoListeners.add(new ClickCheckboxCompuestoListener() {
			
			@Override
			public void clickCheckboxCompuesto (ClickCheckboxCompuestoEvent event) {
				
				CheckboxCompuestoView checkbox = event.getCheckboxCompuestoView();
				
				checkbox.setSelected(checkbox.getCbCheckbox().isChecked());
				
			}
			
		});
		
	}
	
	private void notifyClickCheckboxCompuestoListeners () {
		
		ClickCheckboxCompuestoEvent event;
		
		if (this.clickCheckboxCompuestoListener != null) {
			
			event = new ClickCheckboxCompuestoEvent(this);
			
			this.clickCheckboxCompuestoListener.clickCheckboxCompuesto(event);
			
		}
		
		for (ClickCheckboxCompuestoListener listener : this.clickCheckboxCompuestoListeners) {
			
			event = new ClickCheckboxCompuestoEvent(this);
			
			listener.clickCheckboxCompuesto(event);
			
		}
		
	}
	
	/*
		Sets, Gets y Otros.
	*/
	
	@Override
	public void addView(View child) {
		
		if (this.cbCheckbox != null) {
			
			CheckboxCompuestoView.setEnableR(child, false);
			
		}
		
		if (this.vComponente != null) {
			
			return;
			
		}
		
		this.vComponente = child;
		
		super.addView(this.vComponente);
		
	}
	
	@Override
	public void addView(View child, ViewGroup.LayoutParams params) {
		
		if (this.cbCheckbox != null) {
			
			CheckboxCompuestoView.setEnableR(child, false);
			
		}
		
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
			
			v.setEnabled(enable); // Habilitar/deshabilitar.
			
			for (int x = 0; x < ((ViewGroup)v).getChildCount(); x ++) {
				
				// Llamado recursivo.
				setEnableR(((ViewGroup)v).getChildAt(x), enable);
				
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
		
		this.setChecked(selected);
		
		if (this.vComponente != null) {
			
			// Habilita o deshabilita recursivamente la vista.
			CheckboxCompuestoView.setEnableR(this.vComponente, selected);
			
		}
		
	}
	
	@Override
	public void setEnabled (boolean enabled) {
		
		if (this.vComponente != null) {
			
			// Habilita o deshabilita recursivamente la vista.
			CheckboxCompuestoView.setEnableR(this.vComponente, this.cbCheckbox.isChecked() && enabled);
			
		}
		
		this.cbCheckbox.setEnabled(enabled);
		
	}
	
	public void setText (CharSequence text) {
		
		this.text = text.toString();
		
		this.cbCheckbox.setText(text);
		
	}
	
	public CharSequence getText () {
		
		return this.text;
		
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
	
	public void setCbCheckbox (CheckBox cbCheckbox) {
		
		this.cbCheckbox = cbCheckbox;
		
	}
	
	public CheckBox getCbCheckbox () {
		
		return this.cbCheckbox;
		
	}
	
	public void setChecked (boolean checked) {
		
		this.cbCheckbox.setChecked(checked);
		
	}
	
	public boolean isChecked () {
		
		return this.cbCheckbox.isChecked();
		
	}
	
	public void setClickCheckboxCompuestoListener (ClickCheckboxCompuestoListener clickCheckboxCompuestoListener) {
		
		this.clickCheckboxCompuestoListener = clickCheckboxCompuestoListener;
		
	}
	
	public void addClickCheckboxCompuestoListener (ClickCheckboxCompuestoListener clickCheckboxCompuestoListener) {
		
		this.clickCheckboxCompuestoListeners.add(clickCheckboxCompuestoListener);
		
	}
	
}

