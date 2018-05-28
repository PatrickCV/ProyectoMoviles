package pjrsolutions.ibuy.view.base;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.events.finDeLista.FinDeListaEvent;
import pjrsolutions.ibuy.events.finDeLista.FinDeListaListener;

public abstract class ListaDinamicaAbstracta<T> extends ListaAbstracta<T> {
	
	private ScrollView scScroll;
	private ProgressBar pbProgreso;
	
	private ViewTreeObserver.OnScrollChangedListener scrollChangeListener;
	private FinDeListaListener finDeListaListener;
	private ArrayList<FinDeListaListener> finDeListaListeners;
	
	private boolean atendiendoFinDeLista; // Se esta atendiendo o no la peticion al llegar al final.
	
	public ListaDinamicaAbstracta (Context context) {
		
		super(context);
		
		this.initUI(context);
		
		this.finDeListaListeners = new ArrayList<FinDeListaListener>();
		this.atendiendoFinDeLista = false;
		
	}
	
	public ListaDinamicaAbstracta (Context context, ArrayList<T> objetos) {
		
		super(context, objetos);
		
		this.initUI(context);
		
		this.finDeListaListeners = new ArrayList<FinDeListaListener>();
		this.atendiendoFinDeLista = false;
		
	}
	
	public ListaDinamicaAbstracta (Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
		this.initUI(context);
		
		this.finDeListaListeners = new ArrayList<FinDeListaListener>();
		this.atendiendoFinDeLista = false;
		
	}
	
	/**
		Inicia los componentes visuales a partir de un layout.
	*/
	@Override
	protected void initUI(Context context) {
		
		// Capturar vistas.
		this.setRootView(LinearLayout.inflate(context, R.layout.lista_dinamica_abstracta, this));
		this.scScroll  = (ScrollView)this.getRootView().findViewById(R.id.scScrollListaDinamicaAbstracta);
		this.setLlAreaObjetos((LinearLayout)this.getRootView().findViewById(R.id.llAreaObjetosListaDinamicaAbstracta));
		
		this.pbProgreso = (ProgressBar) this.getRootView().findViewById(R.id.pbProgresoListaDinamicaAbstracta);
		
		// Definir eventos.
		this.eventosScroll();
		
		// Asignar eventos.
		this.scScroll.getViewTreeObserver().addOnScrollChangedListener(this.scrollChangeListener);
		
	}
	
	/**
		Eventos de hacer scroll.
	*/
	private void eventosScroll () {
		
		this.scrollChangeListener = new ViewTreeObserver.OnScrollChangedListener() {
			
			@Override
			public void onScrollChanged() {
				
				int scrollY = getScScroll().getScrollY();
				
				if (scrollY % 2 == 0) { // Cada 2 dp.
					
					// Se llego al final y no se esta atendiendo el evento.
					if (enElFinal() && !isAtendiendoFinDeLista()) {
						
						notifyFinDeListaListeners(); // Notificar listeners.
						
					}
					
				}
				
			}
			
		};
		
	}
	
	/**
		Notifica a todos los FinDeListaListeners subscritos
		de un FinDeListaEvent.
	*/
	private void notifyFinDeListaListeners () {
		
		FinDeListaEvent event;
		
		if (this.finDeListaListener != null) {
			
			event = new FinDeListaEvent(this);
			
			this.finDeListaListener.finDeLista(event);
			
		}
		
		for (FinDeListaListener listener : this.finDeListaListeners) {
			
			event = new FinDeListaEvent(this);
			
			listener.finDeLista(event);
			
		}
		
	}
	
	/**
		Verifica si se llego al final de la lista.
	*/
	private boolean enElFinal () {
		
		// Obtener el rectangulo del contenedor.
		Rect rect = new Rect();
		this.scScroll.getHitRect(rect);
		
		// this.pbProgreso esta dentro del rectangulo visible del contenedor.
		
		return this.pbProgreso.getLocalVisibleRect(rect);
		
	}
	
	/**
		Inicia la animacion de carga.
	*/
	public void iniciarAnimacionCarga () {
		
		this.pbProgreso.setVisibility(View.VISIBLE);
		
	}
	
	/**
		Detiene la animacion de carga.
		Tambien se puede llamar luego de crear la lista en caso de
		que no se requiera cargar mas elementos, para que asi no se
		muestre la animacion de carga.
	*/
	public void detenerAnimacionCarga () {
		
		this.pbProgreso.setVisibility(View.INVISIBLE);
		
	}
	
	public void changeAtendiendoFinDeLista () {
		
		if (this.atendiendoFinDeLista) { // Esta siendo atendido.
			
			this.detenerAnimacionCarga();
			
		} else { // No se esta siendo atendido.
			
			this.iniciarAnimacionCarga();
			
		}
		
		// Cambiar estado.
		this.atendiendoFinDeLista = !this.atendiendoFinDeLista;
		
	}
	
	public boolean isAtendiendoFinDeLista () {
		
		return this.atendiendoFinDeLista;
		
	}
	
	public void setFinDeListaListener (FinDeListaListener finDeListaListener) {
		
		this.finDeListaListener = finDeListaListener;
		
	}
	
	public void addFinDeListaListener (FinDeListaListener finDeListaListener) {
		
		this.finDeListaListeners.add(finDeListaListener);
		
	}
	
	public void setScScroll (ScrollView scScroll) {
		
		this.scScroll = scScroll;
		
	}
	
	public ScrollView getScScroll () {
		
		return this.scScroll;
		
	}
	
}