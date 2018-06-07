package pjrsolutions.ibuy.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.events.cambioFecha.CambioFechaEvent;
import pjrsolutions.ibuy.events.cambioFecha.CambioFechaListener;

public class FechaView extends LinearLayout {
	
	private int _year;
	private int _month;
	private int _day;
	
	private boolean activo;
	
	private View rootView;
	private TextView tvFecha;
	private ImageButton btnFecha;
	
	private OnClickListener clickListener;
	private DatePickerDialog.OnDateSetListener dateSetListener;
	private CambioFechaListener cambioFechaListener;
	private ArrayList<CambioFechaListener> cambioFechaListeners;
	
	public FechaView(Context context) {
		
		super(context);
		
		this.setFechaActual();
		this.activo = true;
		this.cambioFechaListeners = new ArrayList<CambioFechaListener>();
		this.initUI(context);
		
	}
	
	public FechaView(Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
		this.setFechaActual();
		this.activo = true;
		this.cambioFechaListeners = new ArrayList<CambioFechaListener>();
		this.initUI(context);
		
	}
	
	private void initUI (Context context) {
		
		this.rootView = LinearLayout.inflate(context, R.layout.fecha, this);
		
		this.tvFecha = (TextView)this.rootView.findViewById(R.id.tvFechaFecha);
		this.btnFecha = (ImageButton) this.rootView.findViewById(R.id.btnFechaFecha);
		
		this.dibujarFecha();
		
		this.eventosClick();
		this.eventosFecha();
		
		this.btnFecha.setOnClickListener(this.clickListener);
		this.tvFecha.setOnClickListener(this.clickListener);
		
	}
	
	private void setFechaActual() {
		
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		
		this._year = calendar.get(Calendar.YEAR);
		this._month = calendar.get(Calendar.MONTH);
		this._day = calendar.get(Calendar.DAY_OF_MONTH);
		
	}
	
	private void dibujarFecha () {
		
		this.tvFecha.setText(this.toString());
		
	}
	
	private void mostrarDialogo() {
		
		DatePickerDialog dialog = new DatePickerDialog(this.getContext(),
				this.dateSetListener, this._year, this._month, this._day);
		
		dialog.show();
		
	}
	
	private void eventosClick() {
		
		this.clickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (v.getId() == R.id.btnFechaFecha ||
						v.getId() == R.id.tvFechaFecha) {
					
					mostrarDialogo();
					
				}
				
			}
			
		};
		
	}
	
	private void notifyCambioFechaListeners () {
		
		CambioFechaEvent event;
		
		if (this.cambioFechaListener != null) {
			
			event = new CambioFechaEvent(this);
			
			this.cambioFechaListener.cambioFecha(event);
			
		}
		
		for (CambioFechaListener listener : this.cambioFechaListeners) {
			
			event = new CambioFechaEvent(this);
			
			listener.cambioFecha(event);
			
		}
		
	}
	
	private void eventosFecha() {
		
		this.dateSetListener = new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//				_year = year;
//				_month = month;
//				_day = dayOfMonth;
				
				setFecha(year, month, dayOfMonth);
				
				notifyCambioFechaListeners();
				
			}
			
		};
		
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		
		this.tvFecha.setEnabled(enabled);
		this.btnFecha.setEnabled(enabled);
		
	}
	
	@Override
	public void setSelected(boolean selected) {
		
		this.tvFecha.setEnabled(selected);
		this.btnFecha.setEnabled(selected);
		
	}
	
	public void changeActivo() { // todo: posiblemente, quitarlo.
		
		this.activo = !this.activo;
		
		this.tvFecha.setEnabled(this.activo);
		this.btnFecha.setEnabled(this.activo);
		
	}
	
	@Override
	public String toString () {
		
		String fecha = "%s/%s/%s";
		String day = String.valueOf(this._day);
		String month = String.valueOf(this._month + 1);
		String year = String.valueOf(this._year);
		
		if (this._day < 10) {
			
			day = "0" + day;
			
		}
		
		if (this._month < 10) {
			
			month = "0" + month;
			
		}
		
		return String.format(fecha, day, month, year);
		
	}
	
	public void setCambioFechaListener (CambioFechaListener cambioFechaListener) {
		
		this.cambioFechaListener = cambioFechaListener;
		
	}
	
	public void addCambioFechaListener (CambioFechaListener cambioFechaListener) {
		
		this.cambioFechaListeners.add(cambioFechaListener);
		
	}
	
	public void setFecha (int y, int m, int d) {
		
		this._year = y;
		this._month = m;
		this._day = d;
		
		this.dibujarFecha();
		
	}
	
	public void setFecha (String fecha) {
		
		if (fecha.equals("---")) return;
		
		String[] f = fecha.split("/");
		
		this._day = Integer.valueOf(f[0]);
		this._month = Integer.valueOf(f[1]) - 1;
		this._year = Integer.valueOf(f[2]);
		
		this.dibujarFecha();
		
	}
	
}
