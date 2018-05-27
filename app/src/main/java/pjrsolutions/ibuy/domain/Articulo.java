package pjrsolutions.ibuy.domain;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Articulo {
    private TextView nombreArticulo;
    private Spinner cantidadArticulos;
    private TextView precioArticulo;
    private Button cancelar;


    public Articulo(TextView nombreArticulo, Spinner cantidadArticulos, TextView precioArticulo, Button cancelar) {
        this.nombreArticulo = nombreArticulo;
        this.cantidadArticulos = cantidadArticulos;
        this.precioArticulo = precioArticulo;
        this.cancelar = cancelar;
    }

    public TextView getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(TextView nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public Spinner getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(Spinner cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public TextView getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(TextView precioArticulo) {
        this.precioArticulo = precioArticulo;
    }

    public Button getCancelar() {
        return cancelar;
    }

    public void setCancelar(Button cancelar) {
        this.cancelar = cancelar;
    }
}
