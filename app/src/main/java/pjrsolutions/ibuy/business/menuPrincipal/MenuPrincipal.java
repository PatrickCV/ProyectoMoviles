package pjrsolutions.ibuy.business.menuPrincipal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.business.historialCompras.HistorialCompras;

public class MenuPrincipal extends FragmentoAbstracto {
	
	private Button btnNuevaCompra;
	private Button btnHistorialCompras;
	private Button btnConfUsuario;
	private Button btnSalir;
	
	private View estadoVista; // Guarda estado de la vista.
	
	private OnClickListener clickListener; // Escucha de clicks.
	
	public MenuPrincipal () {
	
	}
	
	/*
		Crea la vista, una vez.
	*/
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		
		// Cambiar titulo.
		this.getActivity().setTitle("iBuy");
		
		if (this.estadoVista == null) { // No se ha creado la vista.
			
			this.estadoVista = this.crearVista(inflater, container, savedInstanceState);
			
		}
		
		return this.estadoVista;
		
	}
	
	/*
		Crea la vista.
	*/
	private View crearVista (LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		
		// Capturar las vistas.
		View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);
		this.btnNuevaCompra = (Button)view.findViewById(R.id.btnNuevaCompraMenuPrincipal);
		this.btnHistorialCompras = (Button)view.findViewById(R.id.btnHistorialComprasMenuPrincipal);
		this.btnConfUsuario = (Button)view.findViewById(R.id.btnConfUsuarioMenuPrincipal);
		this.btnSalir = (Button)view.findViewById(R.id.btnSalirMenuPrincipal);
		
		// Inicializar eventos.
		this.eventosClick();
		
		// Asignar eventos.
		this.btnNuevaCompra.setOnClickListener(this.clickListener);
		this.btnHistorialCompras.setOnClickListener(this.clickListener);
		this.btnConfUsuario.setOnClickListener(this.clickListener);
		this.btnSalir.setOnClickListener(this.clickListener);

//		this.listaCompras = ListaCompras.crearListaCompras(this.compras);
		
		return view;
		
	}
	
	/*
		Eventos de hacer click.
	*/
	private void eventosClick () {
	
		this.clickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (v.getId() == R.id.btnNuevaCompraMenuPrincipal) { // Nueva Compra.
				
				
				
				} else if (v.getId() == R.id.btnHistorialComprasMenuPrincipal) { // Historial Compras.
				
					nuevoFragmento(new HistorialCompras());
				
				} else if (v.getId() == R.id.btnConfUsuarioMenuPrincipal) { // Configuracion Usuario.
				
				
				
				} else if (v.getId() == R.id.btnSalirMenuPrincipal) { // Salir.
				
				
				
				}
				
			}
			
		};
	
	}
	
}
