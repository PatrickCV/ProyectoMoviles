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

/**
	Clase abstracta generica.
	Sirve como base para generar listas de objetos.
	Posee un evento que se activa al llegar al final de la lista.
*/
public abstract class ListaAbstracta<T> extends LinearLayout {
	
	private ArrayList<T> objetos;
	
	private View rootView;
	private LinearLayout llAreaObjetos;
	
	/**
	 Constructor programatico basico.
	 */
	public ListaAbstracta (Context context) {
		
		super(context);
		
		this.initUI(context);
		
		this.objetos = new ArrayList<T>();
	
	}
	
	/**
	 Contructor programatico. Recibe una lista de objetos.
	 */
	public ListaAbstracta (Context context, ArrayList<T> objetos) {
		
		super(context);
		
		System.out.println("=== PASO ===");
		
		this.initUI(context);
		
		this.objetos = objetos;
	
	}
	
	/**
	 Constructor para paleta.
	 */
	public ListaAbstracta (Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
		this.initUI(context);
		
		this.objetos = new ArrayList<T>();
	
	}
	
	/**
	 Inicia los componentes visuales a partir de un layout.
	 */
	protected void initUI (Context context) {
		
		// Capturar vistas.
		this.rootView = LinearLayout.inflate(context, R.layout.lista_abstracta, this);
		this.llAreaObjetos = (LinearLayout)this.rootView.findViewById(R.id.llAreaObjetosListaAbstracta);
		
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
	 Sets, Gets, & Otros.
	 */
	
	public void setObjetos (ArrayList<T> objetos) {
		
		this.objetos = objetos;
		
	}
	
	public ArrayList<T> getObjetos () {
		
		return this.objetos;
		
	}
	
	public void setRootView (View rootView) {
		
		this.rootView = rootView;
		
	}
	
	@Override
	public View getRootView () {
		
		return this.rootView;
		
	}
	
	public void add (T objeto) {
		
		this.objetos.add(objeto); // Agregar el objeto a la lista de objetos.
		
		this.agregarObjeto(objeto); // Agregar la vista relacionada al objeto.
		
		this.agregarEspacio(); // Agregar un espacio.
		
	}
	
	public boolean hayObjetos () {
		
		return this.objetos.size() != 0;
		
	}
	
	public void setLlAreaObjetos (LinearLayout llAreaObjetos) {
		
		this.llAreaObjetos = llAreaObjetos;
		
	}
	
	public LinearLayout getLlAreaObjetos () {
		
		return this.llAreaObjetos;
		
	}

}
