package pjrsolutions.ibuy.business.historialCompras;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.domain.ArticuloCompra;
import pjrsolutions.ibuy.domain.Compra;

public class InfoCompra extends FragmentoAbstracto {
	
	private Compra compra;
	
	private TextView lblFecha;
	private TextView lblMonto;
	private TextView lblSucursal;
	private Button btnFiltrar;
	private ListView lvArticulos;
	
	private ArticulosCompraArrayAdapter adapterLvArticulos;
	
	private AbsListView.OnScrollListener scrollListener;
	
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
		this.lvArticulos = (ListView)view.findViewById(R.id.lvArticulosInfoCompra);
		
		// Inicializar eventos.
		this.eventosScroll();
		
		// Asignar escuchas.
		this.lvArticulos.setOnScrollListener(this.scrollListener);
		
		// Asignar datos de compra.
		this.lblFecha.setText(this.compra.getFecha());
		this.lblMonto.setText(String.valueOf(this.compra.getMonto()));
		this.lblSucursal.setText(this.compra.getSucursal());
		
		// Asignar adaptador a this.lvArticulos.
		this.adapterLvArticulos = new ArticulosCompraArrayAdapter(this.getActivity(), this.compra);
		this.lvArticulos.setAdapter(this.adapterLvArticulos);
		
		/*
			Llamar web service aqui.
		*/
		this.adapterLvArticulos.add(new ArticuloCompra("Articulo con un nombre muy, pero muy muy largo", 1200.50f, 5));
		this.adapterLvArticulos.add(new ArticuloCompra("Articulo", 12020.50f, 555));
		this.adapterLvArticulos.add(new ArticuloCompra("Articulo Nuevo", 1200.50f, 5));
		this.adapterLvArticulos.add(new ArticuloCompra("Articulo Viejo", 1200.50f, 5));
		this.adapterLvArticulos.add(new ArticuloCompra("Otro Articulo", 1200.50f, 5));
		this.adapterLvArticulos.add(new ArticuloCompra("Articulo Mas", 1200.50f, 5));

//		Toast.makeText(getContext(), "Codigo: " + compra.getCodigo(), Toast.LENGTH_SHORT).show();
		
		return view;
		
	}
	
	/*
			Eventos al hacer scroll.
		*/
	private void eventosScroll () {
		
		this.scrollListener = new AbsListView.OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				
				if (view.getId() == R.id.lvArticulosInfoCompra) { // this.lvArticulos.
					
					if (compra.getArticulos().size() == 0) {
						
						return;
						
					}
					
					int posUltimoVisible = lvArticulos.getLastVisiblePosition(); // Posicion de la ultima compra visible.
					int cantidadArticulos = compra.getArticulos().size();
					
					if (posUltimoVisible == (cantidadArticulos - 1)) { // Scroll a ultima posicion.
						
						System.out.println("Ultima posicion");

						/*
							Llamar web service aqui.
						*/
						adapterLvArticulos.add(new ArticuloCompra("Otro Articulo", 1200.50f, 5));
						adapterLvArticulos.add(new ArticuloCompra("Otro Articulo", 1200.50f, 5));
						adapterLvArticulos.add(new ArticuloCompra("Otro Articulo", 1200.50f, 5));
						adapterLvArticulos.add(new ArticuloCompra("Otro Articulo", 1200.50f, 5));
						adapterLvArticulos.add(new ArticuloCompra("Otro Articulo", 1200.50f, 5));
						adapterLvArticulos.add(new ArticuloCompra("Otro Articulo", 1200.50f, 5));
						
					}
					
				}
				
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
