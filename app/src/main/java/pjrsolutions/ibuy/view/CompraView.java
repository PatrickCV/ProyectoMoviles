package pjrsolutions.ibuy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.domain.Compra;
import pjrsolutions.ibuy.events.verMas.VerMasEvent;
import pjrsolutions.ibuy.events.verMas.VerMasListener;

/**
 Clase que representa una Compra.
 */
public class CompraView extends LinearLayout {
	
	private static final int elementoId = R.layout.compra_view;
	
	private Compra compra; // Compra relacionada.
	
	private View rootView; // Estado de la vista.
	private TextView lblFechaHora; // Fecha de la compra.
	private ImageButton btnVerMas; // Ver mas.
	private TextView lblSucursal; // Nombre de la sucursal.
	private TextView lblMonto; // Monto de compra
	
	private OnClickListener clickListener; // Escucha de clicks.
	private VerMasListener verMasListener; // Escucha de VerMasEvent.
	private ArrayList<VerMasListener> verMasListeners; // Escuchas de VerMasEvent.
	
	/**
	 Constructor programativo basico.
	 */
	public CompraView (Context context) {
		
		super(context);
		
		this.initUI(context);
		
		this.compra = null;
		this.verMasListeners = new ArrayList<VerMasListener>();
		
	}
	
	/**
	 Constructor programativo que recibe una compra..
	 */
	public CompraView (Context context, Compra compra) {
		
		super(context);
		
		this.compra = compra;
		this.verMasListeners = new ArrayList<VerMasListener>();
		
		this.initUI(context);
		
	}
	
	/**
	 Constructor de paleta.
	 */
	public CompraView (Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
		this.compra = null;
		this.verMasListeners = new ArrayList<VerMasListener>();
		
		this.initUI(context);
		
	}
	
	/**
	 Inicia los componentes visuales a partir de un layout.
	 */
	private void initUI (Context context) {
		
		this.rootView = LinearLayout.inflate(context, CompraView.elementoId, this);
		
		this.lblFechaHora = (TextView)this.rootView.findViewById(R.id.lblFechaHoraCompraView);
		this.btnVerMas = (ImageButton)this.rootView.findViewById(R.id.btnVerMasCompraView);
		this.lblSucursal = (TextView)this.rootView.findViewById(R.id.lblSucursalCompraView);
		this.lblMonto = (TextView)this.rootView.findViewById(R.id.lblMontoCompraView);
		
		this.lblFechaHora.setText(this.compra.getFecha());
		this.lblSucursal.setText(this.compra.getSucursal());
		this.lblMonto.setText(String.valueOf(this.compra.getMonto()));
		
		this.eventosClick();
		
		this.btnVerMas.setOnClickListener(this.clickListener);
		
	}
	
	/**
	 Eventos de hacer click.
	 */
	private void eventosClick () {
		
		this.clickListener = new OnClickListener() {
			
			@Override
			public void onClick (View v) {
				
				if (v.getId() == R.id.btnVerMasCompraView) {
					
					notifyVerMasListeners(); // Notificar escuchas de VerMasEvent.
					
				}
				
			}
			
		};
		
	}
	
	/*
		Notificar escuchas de VerMasEvent.
	*/
	private void notifyVerMasListeners () {
		
		VerMasEvent event;
		
		if (this.verMasListener != null) {
			
			event = new VerMasEvent(this);
			
			this.verMasListener.verMas(event);
			
		}
		
		for (VerMasListener listener : this.verMasListeners) {
			
			event = new VerMasEvent(this);
			
			listener.verMas(event);
			
		}
		
	}
	
	/**
	 Gets & Sets.
	 */
	
	public void setCompra (Compra compra) {
		
		this.compra = compra;
		
	}
	
	public Compra getCompra () {
		
		return compra;
		
	}
	
	public void setVerMasListener (VerMasListener verMasListener) {
		
		this.verMasListener = verMasListener;
		
	}
	
	public void addVerMasListener (VerMasListener verMasListener) {
		
		this.verMasListeners.add(verMasListener);
		
	}
	
}
