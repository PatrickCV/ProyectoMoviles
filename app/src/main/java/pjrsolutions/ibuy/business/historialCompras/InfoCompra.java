package pjrsolutions.ibuy.business.historialCompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.domain.ArticuloCompra;
import pjrsolutions.ibuy.domain.Compra;
import pjrsolutions.ibuy.events.finDeLista.FinDeListaEvent;
import pjrsolutions.ibuy.events.finDeLista.FinDeListaListener;
import pjrsolutions.ibuy.view.ListaArticulosCompraView;

public class InfoCompra extends FragmentoAbstracto {
	
	int n = 0; // TODO: Eliminar esto despues.
	
	private Compra compra;
	
	private TextView lblFecha;
	private TextView lblMonto;
	private TextView lblSucursal;
	private ListaArticulosCompraView listaArticulosCompraViews; // View donde se cargan los articulos.
	
	private FinDeListaListener finDeListaListener; // Escucha de llegar al fin de lista.
	
	public InfoCompra() {
	
	}
	
	public static InfoCompra crearInfoCompra (Compra compra) {
		
		InfoCompra infoCompra = new InfoCompra();
		
		infoCompra.compra = compra;
		
		return infoCompra;
		
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState) {
		
		this.getActivity().setTitle(R.string.ttlInfoCompra); // Cambiar el titulo.
		
		this.setHasOptionsMenu(true); // Permitir menu.
		
		// Capturar las vistas.
		View view = inflater.inflate(R.layout.fragment_info_compra, container, false);
		this.lblFecha = (TextView)view.findViewById(R.id.lblFechaHoraInfoCompra);
		this.lblMonto = (TextView)view.findViewById(R.id.lblMontoInfoCompra);
		this.lblSucursal = (TextView)view.findViewById(R.id.lblSucursalInfoCompra);
		this.listaArticulosCompraViews = (ListaArticulosCompraView)view.findViewById(R.id.ListaArticulosCompra);
		
		// Asignar datos de compra.
		this.lblFecha.setText(this.compra.getFecha());
		this.lblMonto.setText(String.valueOf(this.compra.getMonto()));
		this.lblSucursal.setText(this.compra.getSucursal());
		
		this.listaArticulosCompraViews.setObjetos(this.compra.getArticulos());
		this.listaArticulosCompraViews.setInfoCompra(this);
		
		// Inicializar eventos.
		this.eventosFinDeLista();
		
		// Asignar escuchas.
		this.listaArticulosCompraViews.setFinDeListaListener(this.finDeListaListener);
		
		/*
			Llamar web service aqui.
		*/
		this.listaArticulosCompraViews.changeAtendiendoFinDeLista();
		
		for (int x = 0; x < 15; x ++) {
			
			n ++;
			
			this.listaArticulosCompraViews.add(new ArticuloCompra("Articulo " + n, 5000.0f, 3));
			
		}
		
		this.listaArticulosCompraViews.changeAtendiendoFinDeLista();
		
		return view;
		
	}
	
	private void eventosFinDeLista () {
		
		this.finDeListaListener = new FinDeListaListener() {
			
			@Override
			public void finDeLista (FinDeListaEvent event) {
				
//				System.out.println("========= Llego al final de la lista");
				
				/*
					Llamar web service aqui.
				*/
				
				event.getLista().changeAtendiendoFinDeLista();
				System.out.println("En el final");
				
				for (int x = 0; x < 5; x ++) {
					
					n ++;
					
					ArticuloCompra articuloCompra = new ArticuloCompra("Articulo " + n, 5000.0f, 3);
					
					((ListaArticulosCompraView)event.getLista()).add(articuloCompra);
					
				}
				
				event.getLista().changeAtendiendoFinDeLista();
				
			}
			
		};
		
	}
	
	/*
		Crear el menu.
	*/
	@Override
	public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
		
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.info_compra, menu);
		
	}
	
	/*
		Eventos del menu.
	*/
	@Override
	public boolean onOptionsItemSelected (MenuItem item) {
		
		int id = item.getItemId();
		
		if (id == R.id.opFiltrarMenuInfoCompra) { // Filtar.
		
//			this.nuevoFragmento(new FiltrarArticulosCompra());
		
		} else if (id == R.id.opMenuPrincipalMenuInfoCompra) { // Menu principal.
		
		
		
		} else if (id == R.id.opSalirMenuInfoCompra) { // Salir.
		
		
		
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
}

