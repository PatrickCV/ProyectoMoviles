package pjrsolutions.ibuy.view.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.events.finDeLista.FinDeListaEvent;
import pjrsolutions.ibuy.events.finDeLista.FinDeListaListener;

/**
	Clase abstracta generica.
	Sirve como base para generar listas de objetos.
	Posee un evento que se activa al llegar al final de la lista.
*/
public abstract class ListaAbstracta<T> extends LinearLayout {
	
	private ArrayList<T> objetos;
	
	private View rootView;
	private ScrollView scScroll;
	private LinearLayout llAreaObjetos;
	private ProgressBar pbProgreso;
	
	private OnScrollChangeListener scrollChangeListener;
	private FinDeListaListener finDeListaListener;
	private ArrayList<FinDeListaListener> finDeListaListeners;
	
	private boolean atendiendoFinDeLista; // Se esta atendiendo o no la peticion al llegar al final.
	
	/**
		Constructor programatico basico.
	*/
	public ListaAbstracta (Context context) {
		
		super(context);
		
		this.initUI(context);
		
		this.objetos = new ArrayList<T>();
		this.finDeListaListeners = new ArrayList<FinDeListaListener>();
		this.atendiendoFinDeLista = false;
		
	}
	
	/**
		Contructor programatico. Recibe una lista de objetos.
	*/
	public ListaAbstracta (Context context, ArrayList<T> objetos) {
		
		super(context);
		
		this.initUI(context);
		
		this.objetos = objetos;
		this.finDeListaListeners = new ArrayList<FinDeListaListener>();
		this.atendiendoFinDeLista = false;
		
	}
	
	/**
		Constructor para paleta.
	*/
	public ListaAbstracta (Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
		this.initUI(context);
		
		this.objetos = new ArrayList<T>();
		this.finDeListaListeners = new ArrayList<FinDeListaListener>();
		this.atendiendoFinDeLista = false;
		
	}
	
	/**
		Inicia los componentes visuales a partir de un layout.
	*/
	@SuppressLint("NewApi")
	private void initUI (Context context) {
		
		// Capturar vistas.
		this.rootView = LinearLayout.inflate(context, R.layout.lista_abstracta, this);
		this.scScroll = (ScrollView)this.rootView.findViewById(R.id.scScrollListaAbstracta);
		this.llAreaObjetos = (LinearLayout) this.rootView.findViewById(R.id.llAreaObjetosListaAbstracta);
		this.pbProgreso = (ProgressBar) this.rootView.findViewById(R.id.pbProgresoListaAbstracta);

		// Definir eventos.
		this.eventosScroll();
		
		// Asignar eventos.
		this.scScroll.setOnScrollChangeListener(this.scrollChangeListener);
		
	}
	
	/**
		Eventos de hacer scroll.
	*/
	@SuppressLint("NewApi")
	private void eventosScroll () {
		
		this.scrollChangeListener = new OnScrollChangeListener() {
			
			@Override
			public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
				
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
		if (this.pbProgreso.getLocalVisibleRect(rect)) {
			
			return true;
			
		}
		
		return false;
		
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
	
	/**
		Agrega un nuevo elemento.
	*/
	public abstract void agregarObjeto (T objeto);
	
	/**
		Agrega un espacio entre los elementos.
	*/
	public abstract void agregarEspacio ();
	
	/**
		Sets, Gets, Is's & Adds.
	*/
	
	public void setObjetos (ArrayList<T> objetos) {
		
		this.objetos = objetos;
		
	}
	
	public ArrayList<T> getObjetos () {
		
		return this.objetos;
		
	}
	
	@Override
	public View getRootView () {
		
		return this.rootView;
		
	}
	
	public void setFinDeListaListener (FinDeListaListener finDeListaListener) {
		
		this.finDeListaListener = finDeListaListener;
		
	}
	
	public void addFinDeListaListener (FinDeListaListener finDeListaListener) {
		
		this.finDeListaListeners.add(finDeListaListener);
		
	}
	
	public void add (T objeto) {
		
		this.objetos.add(objeto); // Agregar el objeto a la lista de objetos.
		
		this.agregarObjeto(objeto); // Agregar la vista relacionada al objeto.
		
		this.agregarEspacio(); // Agregar un espacio.
		
	}
	
	public boolean hayObjetos () {
		
		return this.objetos.size() != 0;
		
	}
	
	public ScrollView getScScroll () {
		
		return this.scScroll;
		
	}
	
	public LinearLayout getLlAreaObjetos () {
		
		return this.llAreaObjetos;
		
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
	
}