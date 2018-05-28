package pjrsolutions.ibuy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.domain.ArticuloCompra;

public class ArticuloCompraView extends LinearLayout {
	
	private static final int elementoId = R.layout.elemento_articulo_compra;
	
	private ArticuloCompra articuloCompra; // Articulo relacionado.
	
	private View rootView; // Estado de la vista.
	private TextView etNombre; // Nombre del articulo.
	private TextView etPrecio; // Precio del articulo.
	private TextView etCantidad; // Cantidad del articulo.
	private TextView etTotal; // Total del articulo.
	
	/**
	 Constructor programativo basico.
	 */
	public ArticuloCompraView (Context context) {
		
		super(context);
		
		this.articuloCompra = null;
		
		this.initUI(context);
		
	}
	
	/**
	 Constructor programativo que recibe una compra.
	 */
	public ArticuloCompraView (Context context, ArticuloCompra articuloCompra) {
		
		super(context);
		
		this.articuloCompra = articuloCompra;
		
		this.initUI(context);
		
	}
	
	/**
	 Constructor de paleta.
	 */
	public ArticuloCompraView (Context context, @Nullable AttributeSet attrs) {
		
		super(context, attrs);
		
		this.articuloCompra = null;
		
		this.initUI(context);
		
	}
	
	private void initUI (Context context) {
		
		this.rootView = LinearLayout.inflate(context, ArticuloCompraView.elementoId, this);
		
		this.etNombre = (TextView) this.rootView.findViewById(R.id.lblNombreArticuloElementoArticuloCompra);
		this.etPrecio = (TextView)this.rootView.findViewById(R.id.lblPrecioElementoArticuloCompra);
		this.etCantidad = (TextView)this.rootView.findViewById(R.id.lblCantidadElementoArticuloCompra);
		this.etTotal = (TextView)this.rootView.findViewById(R.id.lblTotalElementoArticuloCompra);
		
		this.etNombre.setText(this.articuloCompra.getNombre());
		this.etPrecio.setText(String.valueOf(this.articuloCompra.getPrecio()));
		this.etCantidad.setText(String.valueOf(this.articuloCompra.getCantidad()));
		this.etTotal.setText(String.valueOf(this.articuloCompra.getPrecio() * this.articuloCompra.getCantidad()));
		
	}
	
	public void setArticuloCompra (ArticuloCompra articuloCompra) {
		
		this.articuloCompra = articuloCompra;
		
	}
	
	public ArticuloCompra getArticuloCompra () {
		
		return this.articuloCompra;
		
	}
	
}