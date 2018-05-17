package pjrsolutions.ibuy.business.historialCompras;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.domain.ArticuloCompra;
import pjrsolutions.ibuy.domain.Compra;

public class ArticulosCompraArrayAdapter extends BaseAdapter {
	
	private Compra compra;
	private LayoutInflater inflater;
	
	public ArticulosCompraArrayAdapter(Activity contexto, Compra compra) {
		
		this.compra = compra;
		this.inflater = LayoutInflater.from(contexto);
		
	}
	
	static private class ViewHolder {
		
		protected TextView lblNombre;
		protected TextView lblPrecio;
		protected TextView lblCantidad;
		protected TextView lblTotal;
		
	}
	
	@Override
	public int getCount () {
		
		return this.compra.getArticulos().size();
		
	}
	
	@Override
	public Object getItem (int posicion) {
		
		return this.compra.getArticulos().get(posicion);
		
	}
	
	@Override
	public long getItemId (int posicion) {
		
		return posicion;
		
	}
	
	@Override
	public View getView (int posicion, View convertView, ViewGroup parent) {
		
		final ViewHolder viewHolder;
		
		if (convertView == null) {
		
			convertView = this.inflater.inflate(R.layout.elemento_articulo_compra, null);
			viewHolder = new ViewHolder();

			viewHolder.lblNombre = (TextView)convertView.findViewById(R.id.lblNombreArticuloElementoArticuloCompra);
			viewHolder.lblPrecio = (TextView)convertView.findViewById(R.id.lblPrecioElementoArticuloCompra);
			viewHolder.lblCantidad = (TextView)convertView.findViewById(R.id.lblCantidadElementoArticuloCompra);
			viewHolder.lblTotal = (TextView)convertView.findViewById(R.id.lblTotalElementoArticuloCompra);

			convertView.setTag(viewHolder);
		
		} else {
			
			viewHolder = (ViewHolder)convertView.getTag();
			
		}
		
		float precio = this.compra.getArticulos().get(posicion).getPrecio();
		int cantidad = this.compra.getArticulos().get(posicion).getCantidad();
		float total = precio * cantidad;
		
		viewHolder.lblNombre.setText(this.compra.getArticulos().get(posicion).getNombre());
		viewHolder.lblPrecio.setText(String.valueOf(precio));
		viewHolder.lblCantidad.setText(String.valueOf(cantidad));
		viewHolder.lblTotal.setText(String.valueOf(total));
		
		return convertView;
		
	}
	
	/**
		Agrega un articulo nuevo a this.articulos y a InfoCompra.lvArticulosCompra.
	*/
	public void add (ArticuloCompra articulo) {
		
		this.compra.getArticulos().add(articulo);
		this.notifyDataSetChanged();
		
	}

}
