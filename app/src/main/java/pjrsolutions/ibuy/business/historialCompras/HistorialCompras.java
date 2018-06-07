package pjrsolutions.ibuy.business.historialCompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.conf.FiltrarComprasConf;
import pjrsolutions.ibuy.domain.Compra;
import pjrsolutions.ibuy.domain.Sesion;
import pjrsolutions.ibuy.events.finDeLista.FinDeListaEvent;
import pjrsolutions.ibuy.events.finDeLista.FinDeListaListener;
import pjrsolutions.ibuy.view.ListaComprasView;
import pjrsolutions.ibuy.webServices.WebServiceCompras;

/**

*/
public class HistorialCompras extends FragmentoAbstracto {
	
	private FiltrarComprasConf filtrarComprasConf;
	
	int n = 0; // TODO: Eliminar esto despues.

	private ArrayList<Compra> compras; // Compras.

	private View estadoVista; // Guarda estado de la vista.
	private ListaComprasView listaCompras; // View donde se cargan las compras.

	private AbsListView.OnScrollChangeListener scrollListener; // Escucha de scroll.
	private View.OnClickListener clickListener; // Escucha de clicks.
	private FinDeListaListener finDeListaListener; // Escucha de llegar al fin de lista.

	/**

	*/
	public HistorialCompras() {

		this.compras = new ArrayList<Compra>();
		
	}

	/**
		Crea la vista, una vez.
	*/
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		this.getActivity().setTitle(R.string.ttlHistorialCompras); // Cambiar el titulo.

		this.setHasOptionsMenu(true); // Permitir menu.
		
		this.filtrarComprasConf = new FiltrarComprasConf(this.getSharedPrederences(FiltrarComprasConf.CONF_FILE));
		this.filtrarComprasConf.cargarConfiguracion();

//		System.out.println("========= " + this.filtrarComprasConf.getOrden());
//		System.out.println("========= " + this.filtrarComprasConf.getFechaEspecifica());
//		System.out.println("========= " + this.filtrarComprasConf.getFecha_rc());
		
		if (this.estadoVista == null) { // No se ha creado la vista.

			this.estadoVista = this.crearVista(inflater, container, savedInstanceState);

		}
		
		if (this.listaCompras.hayObjetos()) {
			
			this.listaCompras.limpiar();
			
		}
		
		this.llamarWebservice();

//		this.listaCompras.changeAtendiendoFinDeLista();
//
//		for (int x = 0; x < 20; x ++) {
//
//			n ++;
//
//			this.listaCompras.add(new Compra(n, "Sucursal " + n, "16/05/2018, 18:34 pm", 50000));
//
//		}
//
//		this.listaCompras.changeAtendiendoFinDeLista();

		return this.estadoVista;

	}

	/**
		Crea la vista.
	*/
	private View crearVista (LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		// Capturar las vistas.
		View view = inflater.inflate(R.layout.fragment_historial_compras, container, false);
		this.listaCompras = (ListaComprasView)view.findViewById(R.id.lista);
		
		this.listaCompras.setObjetos(this.compras); // Asignar la lista de compras.
		this.listaCompras.setHistorialCompras(this); // Asignar this.

		// Inicializando eventos.
		this.eventosFinDeLista();
		
		// Asignando eventos.
		this.listaCompras.setFinDeListaListener(this.finDeListaListener);
		
		return view;

	}

	/**
		Crear el menu.
	*/
	@Override
	public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {

		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.historial_compras, menu);

	}

	/**
		Eventos del menu.
	*/
	@Override
	public boolean onOptionsItemSelected (MenuItem item) {

		int id = item.getItemId();

		if (id == R.id.opFiltrarMenuHistorialCompras) { // Filtar.

			this.nuevoFragmento(new FiltrarCompras());

		} else if (id == R.id.opMenuPrincipalMenuHistorialCompras) { // Menu principal.



		} else if (id == R.id.opSalirMenuHistorialCompras) { // Salir.



		}

		return super.onOptionsItemSelected(item);

	}

	/**
		Eventos de llegar al final de una lista.
	*/
	private void eventosFinDeLista () {

		this.finDeListaListener = new FinDeListaListener() {

			@Override
			public void finDeLista(FinDeListaEvent event) {

				System.out.println("========= Llego al final de la lista");

				/*
					Llamar web service aqui.
				*/
				
				llamarWebservice();

//				event.getLista().changeAtendiendoFinDeLista();
//				System.out.println("En el final");
//
//				for (int x = 0; x < 10; x ++) {
//
//					n ++;
//
//					Compra compra = new Compra(n, "Sucursal " + n, "16/05/2018, 18:34 pm", 50000);
//
//					((ListaComprasView)event.getLista()).add(compra);
//
//				}
//
//				event.getLista().changeAtendiendoFinDeLista();

			}

		};

	}
	
	private void llamarWebservice () {
		
		// Llamando webservice.
		WebServiceCompras webServiceCompras = new WebServiceCompras(this);
//		webServiceCompras.execute("getPagina", "patrickconejo14__ARROBA__gmail__DOT__com","0","5","---","---","25-05-2018","29-05-2018","5000.5","monto","desc");
		webServiceCompras.execute(
				"getPagina",
				Sesion.usuario.getCorreoFormateado(),
				String.valueOf(this.getIdRef()),
				"10",
				this.filtrarComprasConf.getSucursal(),
				this.filtrarComprasConf.getFechaEspecificaFormateada(),
				this.filtrarComprasConf.getDesdeFechaFormateada(),
				this.filtrarComprasConf.getHastaFechaFormateada(),
				String.valueOf(this.filtrarComprasConf.getMontoMinimo()),
				"fecha",
				this.filtrarComprasConf.getOrden());
		
	}
	
	private int getIdRef () {
		
		if (this.listaCompras.getObjetos().size() == 0) {
			
			return 0;
			
		}
		
		return this.listaCompras.getObjetos().get(this.listaCompras.getObjetos().size() -1).getCodigo();
		
	}
	
	public ListaComprasView getListaCompras () {
		
		return this.listaCompras;
		
	}
	
}
