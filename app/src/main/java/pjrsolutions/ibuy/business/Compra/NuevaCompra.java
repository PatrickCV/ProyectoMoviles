package pjrsolutions.ibuy.business.Compra;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.net.ssl.SSLSession;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.business.menuPrincipal.MenuPrincipal;
import pjrsolutions.ibuy.datos.BaseDatosSQLite;
import pjrsolutions.ibuy.domain.Articulo;
import pjrsolutions.ibuy.domain.ArticuloCompra;
import pjrsolutions.ibuy.domain.Sesion;
import pjrsolutions.ibuy.webServices.WebServiceCompras;
import pjrsolutions.ibuy.webServices.WebServiceRealizarCompra;

/**
 * A simple {@link Fragment} subclass.
 */
public class NuevaCompra extends FragmentoAbstracto implements View.OnClickListener {

    private ArrayList<ArticuloCompra> articulos;

    private LinearLayout contenedor;

    private Button btnPagar;
    private Button btnNuevoA;
    private Button btnCancelar;

    private View vista;

    public NuevaCompra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.vista = (View) inflater.inflate(R.layout.fragment_nueva_compra, container, false);
        this.btnPagar = (Button) vista.findViewById(R.id.btnPagar);
        this.btnNuevoA = (Button) vista.findViewById(R.id.btnRegistrarArticulo);
        this.btnCancelar = (Button) vista.findViewById(R.id.btnCancelarLista);
        contenedor = (LinearLayout) vista.findViewById(R.id.contenedorArticulos);

        this.articulos = new ArrayList<ArticuloCompra>();
        BD = new BaseDatosSQLite(this.getActivity(), null);

        llenarLista();

        this.btnPagar.setOnClickListener(this);
        this.btnNuevoA.setOnClickListener(this);
        this.btnCancelar.setOnClickListener(this);

        //lvAnimales.setOnItemClickListener(this);

        return this.vista;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegistrarArticulo) {
            nuevoFragmento(new Nuevo_Articulo());
        }

        if (v.getId() == R.id.btnPagar) {
            llamadoWebService();
        }

        if (v.getId() == R.id.btnCancelarLista) {
            Cursor dato = (Cursor) BD.getData();
            dato.moveToFirst();
            if(dato != null && dato.getCount() > 0) {
                for (int i = 0; i < dato.getCount(); i++) {
                    BD.deleteArticle(dato.getString(1));
                    dato.moveToNext();
                }
                nuevoFragmento(new MenuPrincipal());
            }
        }
    }

    public void llamadoWebService(){
        Cursor dato = (Cursor) BD.getData();
        dato.moveToFirst();
        if(dato.getCount() > 0) {
            for (int i = 0; i < dato.getCount(); i++) {
                WebServiceRealizarCompra webServiceCompras = new WebServiceRealizarCompra(this);
                webServiceCompras.execute("realizarCompra",Sesion.usuario.getCedula(),dato.getString(3) + "-" + dato.getString(7),"1000200030004000",String.valueOf(Float.parseFloat(dato.getString(4)) * Float.parseFloat(dato.getString(3))),dato.getString(5));
                //webServiceCompras.execute("realizarCompra",Sesion.usuario.getCedula(),articulo_cantidad,"1000200030004000","10000");
                dato.moveToNext();
            }


        }
    }

    public void llenarLista(){
        Cursor dato = (Cursor) BD.getData();
        dato.moveToFirst();
        if(dato.getCount() > 0) {
            String nombre = "";
            int cantidad = 0;
            float precio = 0;
            for (int i = 0; i < dato.getCount(); i++) {
                nombre = dato.getString(1);
                cantidad = Integer.parseInt(dato.getString(3));
                precio = Float.parseFloat(dato.getString(4));
                this.articulos.add(new ArticuloCompra(nombre, precio, cantidad));
                dato.moveToNext();
            }
            crearFilaArticulo();
        }else
            nuevoFragmento(new Nuevo_Articulo());
    }

    public void crearFilaArticulo(){
        for(int i = 0; i < this.articulos.size(); i++){
            LinearLayout fila = new LinearLayout(this.getContext());
            fila.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            fila.setGravity(Gravity.LEFT);
            fila.setOrientation(LinearLayout.HORIZONTAL);

            Articulo articulo = new Articulo(crearNombreArticulo(this.articulos.get(i).getNombre()), crearNumeroArticulos(this.articulos.get(i).getCantidad(),this.articulos.get(i).getNombre()), crearTotalArticulo(String.valueOf(this.articulos.get(i).getPrecio() * this.articulos.get(i).getCantidad())), crearBotonCancelar(this.articulos.get(i).getNombre()));
            fila.addView(articulo.getNombreArticulo());
            fila.addView(articulo.getCantidadArticulos());
            fila.addView(articulo.getPrecioArticulo());
            fila.addView(articulo.getCancelar());

            contenedor.addView(fila);
        }
    }

    public Button crearBotonCancelar(final String nombreEliminar){
        Button cancelar = new Button(this.getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int)RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.rightMargin = 0;
        cancelar.setText("X");
        cancelar.setWidth(60);
        cancelar.setLayoutParams(params);
        cancelar.setPadding(5, 5, 5, 5);
        cancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(BD.deleteArticle(nombreEliminar)==1){
                    Toast.makeText(getActivity(), "El artículo se eliminó correctamente", Toast.LENGTH_SHORT).show();
                    llenarLista();
                }else
                    Toast.makeText(getActivity(), BD.deleteArticle(nombreEliminar), Toast.LENGTH_SHORT).show();
            }
        });
        return cancelar;
    }

    public TextView crearTotalArticulo(String total){
        TextView txtTotal= new TextView(this.getContext());
        txtTotal.setText(String.valueOf(total));
        txtTotal.setGravity(Gravity.LEFT);
        txtTotal.setPadding(40, 10, 40, 20);
        return txtTotal;
    }

    public Spinner crearNumeroArticulos(int cantidad, final String nombreA){
        final Spinner articulos = new Spinner(this.getContext());
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this.getContext(), R.array.numeros , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        articulos.setAdapter(spinner_adapter);
        articulos.setSelection(cantidad-1);
        articulos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor dato = (Cursor) BD.getData();
                dato.moveToFirst();
                String cantidadEscogida = articulos.getSelectedItem().toString();
                for (int i = 0; i < dato.getCount(); i++) {
                    if (nombreA.equals(dato.getString(1).toString())) {
                        String nombre = dato.getString(1);
                        Float precio = Float.parseFloat(dato.getString(4));
                        ArticuloCompra articulo = new ArticuloCompra(nombre, precio, Integer.parseInt(cantidadEscogida));
                        if (BD.updateArticle(articulo, dato.getString(2), dato.getString(5), dato.getString(6), dato.getString(7)))
                            Toast.makeText(getActivity(), "El artículo se modificó correctamente", Toast.LENGTH_SHORT).show();
                    } else
                        dato.moveToNext();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return articulos;
    }

    /**/

    public TextView crearNombreArticulo(String nombre){
        TextView txtNombre = new TextView(this.getContext());
        txtNombre.setText(nombre);
        txtNombre.setHeight(60);
        txtNombre.setMaxLines(3);
        txtNombre.setGravity(Gravity.LEFT);
        txtNombre.setPadding(10, 10, 50, 20);
        return txtNombre;
    }
}
