package pjrsolutions.ibuy.business.historialCompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.conf.FiltrarComprasConf;
import pjrsolutions.ibuy.events.cambioFecha.CambioFechaEvent;
import pjrsolutions.ibuy.events.cambioFecha.CambioFechaListener;
import pjrsolutions.ibuy.events.clickCheckboxCompuesto.ClickCheckboxCompuestoEvent;
import pjrsolutions.ibuy.events.clickCheckboxCompuesto.ClickCheckboxCompuestoListener;
import pjrsolutions.ibuy.events.clickGrupoRadioCompuesto.ClickGrupoRadioCompuestoEvent;
import pjrsolutions.ibuy.events.clickGrupoRadioCompuesto.ClickGrupoRadioCompuestoListener;
import pjrsolutions.ibuy.events.clickRadioCompuesto.ClickRadioCompuestoEvent;
import pjrsolutions.ibuy.events.clickRadioCompuesto.ClickRadioCompuestoListener;
import pjrsolutions.ibuy.view.CheckboxCompuestoView;
import pjrsolutions.ibuy.view.FechaView;
import pjrsolutions.ibuy.view.GrupoRadioCompuestoView;
import pjrsolutions.ibuy.view.RadioCompuestoView;
import pjrsolutions.ibuy.webServices.WebServiceSucursales;

public class FiltrarCompras extends FragmentoAbstracto {
	
	private FiltrarComprasConf filtrarComprasConf;
	
	private GrupoRadioCompuestoView grcOrden;
	private RadioCompuestoView rcDescendente;
	private RadioCompuestoView rctnAscendente;
	private GrupoRadioCompuestoView grcFecha;
	private RadioCompuestoView rcTodasFechas;
	private RadioCompuestoView rcFechaEspecifica;
	private FechaView fechaFechaEspecifica;
	private RadioCompuestoView rcRangoFechas;
	private CheckboxCompuestoView ccDesdeFecha;
	private FechaView fechaDesdeFecha;
	private CheckboxCompuestoView ccHastaFecha;
	private FechaView fechaHastaFecha;
	private GrupoRadioCompuestoView grcSucursal;
	private RadioCompuestoView rcTodasSucursales;
	private RadioCompuestoView rcSucursalEspecifica;
	private Spinner spCambiarSucursalEspecifica;
	private EditText etMontoMinimo;
	private Button btnOk;
	
	private View.OnClickListener clickListener;
	private ClickGrupoRadioCompuestoListener clickGrupoRadioCompuestoListener;
	private ClickRadioCompuestoListener clickRadioCompuestoListener;
	private ClickCheckboxCompuestoListener clickCheckboxCompuestoListener;
	private CambioFechaListener cambioFechaListener;
	private Spinner.OnItemSelectedListener onItemSelectedListener;
	
	public FiltrarCompras () {
	
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState) {
		
		this.getActivity().setTitle(R.string.ttlFiltrarCompras); // Cambiar el titulo.
		
		this.setHasOptionsMenu(true); // Permitir menu.
		
		this.filtrarComprasConf = new FiltrarComprasConf(this.getSharedPrederences(FiltrarComprasConf.CONF_FILE));
		this.filtrarComprasConf.cargarConfiguracion();
		
		// Capturar las vistas.
		View view = inflater.inflate(R.layout.fragment_filtrar_compras, container, false);
		
		this.grcOrden = (GrupoRadioCompuestoView)view.findViewById(R.id.grcOrdenFiltrarCompras);
		this.rcDescendente = (RadioCompuestoView)view.findViewById(R.id.rcDescendenteFiltrarCompras);
		this.rctnAscendente = (RadioCompuestoView)view.findViewById(R.id.rcAscendenteFiltrarCompras);
		
		this.grcFecha = (GrupoRadioCompuestoView)view.findViewById(R.id.grcFechaFiltrarCompras);
		this.rcTodasFechas = (RadioCompuestoView)view.findViewById(R.id.rcTodasFechasFiltrarCompras);
		this.rcFechaEspecifica = (RadioCompuestoView)view.findViewById(R.id.rcFechaEspecificaFiltrarCompras);
		this.fechaFechaEspecifica = (FechaView)view.findViewById(R.id.fechaFechaEspecificaFiltrarCompras);
		this.rcRangoFechas = (RadioCompuestoView)view.findViewById(R.id.rcRangoFechasFiltrarCompras);
		this.ccDesdeFecha = (CheckboxCompuestoView)view.findViewById(R.id.ccDesdeFechaFiltrarCompras);
		this.fechaDesdeFecha = (FechaView)view.findViewById(R.id.fechaDesdeFiltrarCompras);
		this.ccHastaFecha = (CheckboxCompuestoView)view.findViewById(R.id.ccHastaFechaFiltrarCompras);
		this.fechaHastaFecha = (FechaView)view.findViewById(R.id.fechaHastaFiltrarCompras);
		
		this.grcSucursal = (GrupoRadioCompuestoView)view.findViewById(R.id.grcSucursalFiltrarCompras);
		this.rcTodasSucursales = (RadioCompuestoView)view.findViewById(R.id.rcTodasSucursalesFiltrarCompras);
		this.rcSucursalEspecifica = (RadioCompuestoView)view.findViewById(R.id.rcSucursalEspecificaFiltrarCompras);
		this.spCambiarSucursalEspecifica = (Spinner)view.findViewById(R.id.spCambiarSucursalEspecificaFiltrarCompras);
		
		this.etMontoMinimo = (EditText)view.findViewById(R.id.etMontoMinimoFiltrarCompras);
		
		this.btnOk = (Button) view.findViewById(R.id.btnOkFiltrarCompras);
		
		this.grcOrden.setValor("desc");
		this.grcOrden.setSelected(this.filtrarComprasConf.getOrden_rc());
		this.rcDescendente.setValor("desc");
		this.rctnAscendente.setValor("asc");
		
		this.grcFecha.setValor("---");
		this.grcFecha.setSelected(this.filtrarComprasConf.getFecha_rc());
		this.rcTodasFechas.setValor("---");
		this.rcFechaEspecifica.setValor(this.fechaFechaEspecifica.toString());
		this.rcRangoFechas.setValor(this.filtrarComprasConf.getFechaEspecifica() + "#" + this.filtrarComprasConf.getHastaFecha());
		this.ccDesdeFecha.setValor("---");
		this.ccDesdeFecha.getCbCheckbox().setChecked(this.filtrarComprasConf.isDesde_cc());
		this.ccHastaFecha.setValor("---");
		this.ccHastaFecha.getCbCheckbox().setChecked(this.filtrarComprasConf.isHasta_cc());
		this.fechaFechaEspecifica.setFecha(this.filtrarComprasConf.getFechaEspecifica());
		this.fechaDesdeFecha.setFecha(this.filtrarComprasConf.getDesdeFecha());
		this.fechaHastaFecha.setFecha(this.filtrarComprasConf.getHastaFecha());
		
		this.grcSucursal.setValor("---");
		this.grcSucursal.setSelected(this.filtrarComprasConf.getSucursal_rc());
		this.rcTodasSucursales.setValor("---");

//		String[] sucursales = {"Sucursal", "Del Norte", "Otra", "Sucursal", "Del Norte", "Otra", "Sucursal", "Del Norte", "Otra", "Sucursal", "Del Norte", "Otra", "Sucursal", "Del Norte", "Otra", "Una Sucursal con un Nombre Muy Muy Largo", "Del Norte", "Otra", "Sucursal", "Del Norte", "Otra"};
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, sucursales);
//		this.spCambiarSucursalEspecifica.setAdapter(adapter);
		
		WebServiceSucursales ws = new WebServiceSucursales(this);
		ws.execute();
		
		this.etMontoMinimo.setText(String.valueOf(this.filtrarComprasConf.getMontoMinimo()));
		
		// Inicializar eventos.
		this.eventosClick();
		this.eventosGrupoRadioCompuesto();
		this.eventosRadioCompuesto();
		this.eventosCheckboxCompuesto();
		this.eventosCambioFecha();
		this.eventosSpinner();
		
		// Asignar escuchas.
		this.grcOrden.addClickGrupoRadioCompuestoListener(this.clickGrupoRadioCompuestoListener);
		this.grcFecha.addClickGrupoRadioCompuestoListener(this.clickGrupoRadioCompuestoListener);
		this.grcSucursal.addClickGrupoRadioCompuestoListener(this.clickGrupoRadioCompuestoListener);
		
		this.rcDescendente.setClickRadioCompuestoListener(this.clickRadioCompuestoListener);
		this.rctnAscendente.setClickRadioCompuestoListener(this.clickRadioCompuestoListener);
		this.rcTodasFechas.setClickRadioCompuestoListener(this.clickRadioCompuestoListener);
		this.rcFechaEspecifica.setClickRadioCompuestoListener(this.clickRadioCompuestoListener);
		this.rcRangoFechas.setClickRadioCompuestoListener(this.clickRadioCompuestoListener);
		this.rcTodasSucursales.setClickRadioCompuestoListener(this.clickRadioCompuestoListener);
		this.rcSucursalEspecifica.setClickRadioCompuestoListener(this.clickRadioCompuestoListener);
		
		this.fechaFechaEspecifica.addCambioFechaListener(this.cambioFechaListener);
		this.fechaDesdeFecha.addCambioFechaListener(this.cambioFechaListener);
		this.fechaHastaFecha.addCambioFechaListener(this.cambioFechaListener);
		
		this.ccDesdeFecha.addClickCheckboxCompuestoListener(this.clickCheckboxCompuestoListener);
		this.ccHastaFecha.addClickCheckboxCompuestoListener(this.clickCheckboxCompuestoListener);
		
		this.btnOk.setOnClickListener(this.clickListener);
		
		return view;
		
	}
	
	/**
	 Eventos al hacer click.
	 */
	private void eventosClick () {
		
		this.clickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (v.getId() == R.id.btnOkFiltrarCompras) {
					
					Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
					
					float montoMinimo = 0.0f;
					
					if (!etMontoMinimo.getText().toString().equals("")) {
						
						montoMinimo = Float.valueOf(etMontoMinimo.getText().toString());
						
					}
					
					filtrarComprasConf.setMontoMinimo(montoMinimo);
					filtrarComprasConf.guardarConfiguracion();
					
				}
				
			}
			
		};
		
	}
	
	private void eventosGrupoRadioCompuesto () {
		
		this.clickGrupoRadioCompuestoListener = new ClickGrupoRadioCompuestoListener() {
			
			@Override
			public void clickGrupoRadioCompuesto (ClickGrupoRadioCompuestoEvent event) {
				
				GrupoRadioCompuestoView grupo = event.getGrupoRadioCompuestoView();
				
				RadioCompuestoView rc = grupo.getRadioCompuestoViewSeleccionado();
				
				int pos = grupo.getIndiceRadioCompuestoView(rc);
				
				if (grupo.getId() == R.id.grcOrdenFiltrarCompras) { // Orden.
					
					filtrarComprasConf.setOrden_rc(pos);
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getOrden(), Toast.LENGTH_SHORT).show();
					
				} else if (grupo.getId() == R.id.grcFechaFiltrarCompras) { // Fecha.
					
					filtrarComprasConf.setFecha_rc(pos);
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getFechaEspecifica() + ", " + filtrarComprasConf.getDesdeFecha() + "#" + filtrarComprasConf.getHastaFecha(), Toast.LENGTH_SHORT).show();
					
				} else if (grupo.getId() == R.id.grcSucursalFiltrarCompras) { // Sucursal.
					
					filtrarComprasConf.setSucursal_rc(pos);
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getSucursal(), Toast.LENGTH_SHORT).show();
					
				}
				
			}
			
		};
		
	}
	
	private void eventosRadioCompuesto () {
		
		this.clickRadioCompuestoListener = new ClickRadioCompuestoListener() {
			
			@Override
			public void clickRadioCompuesto (ClickRadioCompuestoEvent event) {
				
				RadioCompuestoView v = event.getRadioCompuestoView();
				
				if (v.getId() == R.id.rcDescendenteFiltrarCompras ||
						v.getId() == R.id.rcAscendenteFiltrarCompras) { // Orden.
					
					filtrarComprasConf.setOrden(v.getValor().toString());
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getOrden(), Toast.LENGTH_SHORT).show();
					
				} else if (v.getId() == R.id.rcTodasFechasFiltrarCompras) { // Todas fechas.
					
					filtrarComprasConf.setFechaEspecifica("---");
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getFechaEspecifica(), Toast.LENGTH_SHORT).show();
					
				} else if (v.getId() == R.id.rcFechaEspecificaFiltrarCompras) { // Fecha especifica.
					
					filtrarComprasConf.setFechaEspecifica(fechaFechaEspecifica.toString());
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getFechaEspecifica(), Toast.LENGTH_SHORT).show();
					
				} else if (v.getId() == R.id.rcRangoFechasFiltrarCompras) { // Rango.
					
					filtrarComprasConf.setFechaEspecifica("---");
					
					System.out.println("============= " + fechaDesdeFecha.toString());
					
					if (ccDesdeFecha.isChecked()) {
						
						filtrarComprasConf.setDesdeFecha(fechaDesdeFecha.toString());
						
					} else {
						
						filtrarComprasConf.setDesdeFecha("---");
						
					}
					
					if (ccHastaFecha.isChecked()) {
						
						filtrarComprasConf.setHastaFecha(fechaHastaFecha.toString());
						
					} else {
						
						filtrarComprasConf.setHastaFecha("---");
						
					}
					
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getDesdeFecha() + "#" + filtrarComprasConf.getHastaFecha(), Toast.LENGTH_SHORT).show();
					
				} else if (v.getId() == R.id.rcTodasSucursalesFiltrarCompras) { // Todas sucursales.
					
					spCambiarSucursalEspecifica.setOnItemSelectedListener(null);
					filtrarComprasConf.setSucursal("---");
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getSucursal(), Toast.LENGTH_SHORT).show();
					
				} else if (v.getId() == R.id.rcSucursalEspecificaFiltrarCompras) { // Sucursal especifica.
					
					spCambiarSucursalEspecifica.setOnItemSelectedListener(onItemSelectedListener);
					filtrarComprasConf.setSucursal(spCambiarSucursalEspecifica.getSelectedItem().toString());
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getSucursal(), Toast.LENGTH_SHORT).show();
					
				}
				
			}
			
		};
		
	}
	
	private void eventosCheckboxCompuesto () {
		
		this.clickCheckboxCompuestoListener = new ClickCheckboxCompuestoListener() {
			
			@Override
			public void clickCheckboxCompuesto (ClickCheckboxCompuestoEvent event) {
				
				CheckboxCompuestoView v = event.getCheckboxCompuestoView();
				
				if (v.getId() == R.id.ccDesdeFechaFiltrarCompras) { // Desde.
					
					filtrarComprasConf.setDesde_cc(ccDesdeFecha.isChecked());
					
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getDesdeFecha(), Toast.LENGTH_SHORT).show();
					
				} else if (v.getId() == R.id.ccHastaFechaFiltrarCompras) { // Hasta.
					
					filtrarComprasConf.setHasta_cc(ccHastaFecha.isChecked());
					
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getHastaFecha(), Toast.LENGTH_SHORT).show();
					
				}
				
			}
			
		};
		
	}
	
	private void eventosCambioFecha () {
		
		this.cambioFechaListener = new CambioFechaListener() {
			
			@Override
			public void cambioFecha (CambioFechaEvent event) {
				
				FechaView v = event.getFechaView();
				
				if (v.getId() == R.id.fechaFechaEspecificaFiltrarCompras) { // Especifica.
					
					filtrarComprasConf.setFechaEspecifica(v.toString());
					
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getDesdeFecha(), Toast.LENGTH_SHORT).show();
					
				} else if (v.getId() == R.id.fechaDesdeFiltrarCompras) { // Desde.
					
					filtrarComprasConf.setDesdeFecha(v.toString());
					
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getDesdeFecha(), Toast.LENGTH_SHORT).show();
					
				} else if (v.getId() == R.id.fechaHastaFiltrarCompras) { // Hasta.
					
					filtrarComprasConf.setHastaFecha(v.toString());
					
					// Debug: recibiendo.
					Toast.makeText(getContext(), filtrarComprasConf.getHastaFecha(), Toast.LENGTH_SHORT).show();
					
				}
				
			}
			
		};
		
	}
	
	private void eventosSpinner () {
		
		this.onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
				
				filtrarComprasConf.setSucursal(parent.getItemAtPosition(position).toString());
				
				// Debug: recibiendo.
				Toast.makeText(getContext(), filtrarComprasConf.getSucursal(), Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void onNothingSelected (AdapterView<?> parent) {}
			
		};
		
	}
	
	/**
	 Crear el menu.
	 */
	@Override
	public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
		
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.filtrar_compras, menu);
		
	}
	
	/*
		Eventos del menu.
	*/
	@Override
	public boolean onOptionsItemSelected (MenuItem item) {
		
		int id = item.getItemId();
		
		if (id == R.id.opMenuPrincipalMenuFiltrarCompras) { // Menu principal.
		
		} else if (id == R.id.opSalirMenuFiltrarCompras) { // Salir.
		
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	public Spinner getSpCambiarSucursalEspecifica () {
		
		return this.spCambiarSucursalEspecifica;
		
	}
}
