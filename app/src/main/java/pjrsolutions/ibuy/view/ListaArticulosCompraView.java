package pjrsolutions.ibuy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.historialCompras.InfoCompra;
import pjrsolutions.ibuy.domain.ArticuloCompra;
import pjrsolutions.ibuy.view.base.ListaDinamicaAbstracta;

public class ListaArticulosCompraView extends ListaDinamicaAbstracta<ArticuloCompra> {
	
	private InfoCompra infoCompra;
	
	public ListaArticulosCompraView (Context context) {
		
		super(context);
		
	}
	
	public ListaArticulosCompraView (Context context, ArrayList<ArticuloCompra> articuloCompras) {
		
		super(context, articuloCompras);
		
	}
	
	public ListaArticulosCompraView (Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
	}
	
	@Override
	public void agregarObjeto (ArticuloCompra articuloCompra) {
		
		// Crea un ArticuloCompraView.
		ArticuloCompraView articuloCompraView = new ArticuloCompraView(this.getContext(), articuloCompra);
		
		// Agregar el ArticuloCompraView a this.llAreaObjetos
		this.getLlAreaObjetos().addView(articuloCompraView);
		
	}
	
	@Override
	public void agregarEspacio () {
		
		LinearLayout espacio = new LinearLayout(this.getContext());
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
		params.setMargins(10, 5, 10, 5);
		
		espacio.setLayoutParams(params);
		
		espacio.setBackgroundResource(R.color.green);
		
		this.getLlAreaObjetos().addView(espacio);
		
	}
	
	public void setInfoCompra (InfoCompra infoCompra) {
		
		this.infoCompra = infoCompra;
		
	}
	
	public InfoCompra getInfoCompra () {
		
		return this.infoCompra;
		
	}
	
}
