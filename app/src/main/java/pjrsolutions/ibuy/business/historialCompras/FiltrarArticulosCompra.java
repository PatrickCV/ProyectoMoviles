package pjrsolutions.ibuy.business.historialCompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.conf.FiltrarArticulosCompraConf;
import pjrsolutions.ibuy.events.clickGrupoRadioCompuesto.ClickGrupoRadioCompuestoEvent;
import pjrsolutions.ibuy.events.clickGrupoRadioCompuesto.ClickGrupoRadioCompuestoListener;
import pjrsolutions.ibuy.view.GrupoRadioCompuestoView;
import pjrsolutions.ibuy.view.RadioCompuestoView;

public class FiltrarArticulosCompra extends FragmentoAbstracto {
	
	private FiltrarArticulosCompraConf filtrarArticulosCompraConf;
	
	private GrupoRadioCompuestoView grcOrdenarPor;
	private RadioCompuestoView rcNombre;
	private RadioCompuestoView rcPrecio;
	private RadioCompuestoView rcCantidad;
	private GrupoRadioCompuestoView grcOrden;
	private RadioCompuestoView rcAscendente;
	private RadioCompuestoView rcDescendente;
	private Button btnOk;
	
	private View.OnClickListener clickListener;
	private ClickGrupoRadioCompuestoListener clickGrupoRadioCompuestoListener;
	
	public FiltrarArticulosCompra () {
	
	}
	
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState) {
		
		this.getActivity().setTitle(R.string.ttlFiltrarArticulosCompra); // Cambiar el titulo.
		
		this.setHasOptionsMenu(true); // Permitir menu.
		
		this.filtrarArticulosCompraConf = new FiltrarArticulosCompraConf(this.getSharedPrederences(FiltrarArticulosCompraConf.CONF_FILE));
		this.filtrarArticulosCompraConf.cargarConfiguracion();
		
		// Capturar las vistas.
		View view = inflater.inflate(R.layout.fragment_filtrar_articulos_compra, container, false);
		
		this.grcOrdenarPor = (GrupoRadioCompuestoView)view.findViewById(R.id.grcOrdenarPorFiltrarArticulosCompra);
		this.rcNombre = (RadioCompuestoView)view.findViewById(R.id.rcNombreFiltrarArticulosCompra);
		this.rcPrecio = (RadioCompuestoView)view.findViewById(R.id.rcPrecioFiltrarArticulosCompra);
		this.rcCantidad = (RadioCompuestoView)view.findViewById(R.id.rcCantidadFiltrarArticulosCompra);
		this.grcOrden = (GrupoRadioCompuestoView)view.findViewById(R.id.grcOrdenFiltrarArticulosCompra);
		this.rcDescendente = (RadioCompuestoView)view.findViewById(R.id.rcDescendenteFiltrarArticulosCompra);
		this.rcAscendente = (RadioCompuestoView)view.findViewById(R.id.rcAscendenteFiltrarArticulosCompra);
		this.btnOk = (Button)view.findViewById(R.id.btnOkFiltrarArticulosCompra);
		
		this.grcOrdenarPor.setSelected(this.filtrarArticulosCompraConf.getOrdenarPor());
		this.grcOrden.setSelected(this.filtrarArticulosCompraConf.getOrden());
		
		this.eventosClick();
		this.eventosGrupoRadioCompuesto();
		
		this.btnOk.setOnClickListener(this.clickListener);
		this.grcOrdenarPor.setClickGrupoRadioCompuestoListener(this.clickGrupoRadioCompuestoListener);
		this.grcOrden.setClickGrupoRadioCompuestoListener(this.clickGrupoRadioCompuestoListener);
		
		return view;
		
	}
	
	private void eventosClick () {
		
		this.clickListener = new View.OnClickListener() {
			
			@Override
			public void onClick (View v) {
				
				if (v.getId() == R.id.btnOkFiltrarArticulosCompra) {
					
					filtrarArticulosCompraConf.guardarConfiguracion();
					
				}
				
			}
			
		};
		
	}
	
	private void eventosGrupoRadioCompuesto () {
		
		this.clickGrupoRadioCompuestoListener = new ClickGrupoRadioCompuestoListener() {
			
			@Override
			public void clickGrupoRadioCompuesto (ClickGrupoRadioCompuestoEvent event) {
				
				GrupoRadioCompuestoView grupo = event.getGrupoRadioCompuestoView();
				
				int pos = grupo.getIndiceRadioCompuestoView(grupo.getRadioCompuestoViewSeleccionado());
				
				if (grupo.getId() == R.id.grcOrdenarPorFiltrarArticulosCompra) { // Ordenar por.
					
					filtrarArticulosCompraConf.setOrdenarPor(pos);
					
					// Debug: recibiendo.
					Toast.makeText(getContext(), String.valueOf(filtrarArticulosCompraConf.getOrdenarPor()), Toast.LENGTH_SHORT).show();
					
				} else if (grupo.getId() == R.id.grcOrdenFiltrarArticulosCompra) { // Orden.
					
					filtrarArticulosCompraConf.setOrden(pos);
					
					// Debug: recibiendo.
					Toast.makeText(getContext(), String.valueOf(filtrarArticulosCompraConf.getOrden()), Toast.LENGTH_SHORT).show();
					
				}
				
			}
			
		};
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.filtrar_compras, menu);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		
		if (id == R.id.opMenuPrincipalMenuFiltrarArticulosCompra) { // Menu principal.
		
		
		
		} else if (id == R.id.opSalirMenuFiltrarArticulosCompra) { // Salir.
		
		
		
		}
		
		return super.onOptionsItemSelected(item);
		
	}
}
