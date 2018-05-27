package pjrsolutions.ibuy.business.Compra;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.business.menuPrincipal.MenuPrincipal;
import pjrsolutions.ibuy.domain.Articulo;
import pjrsolutions.ibuy.domain.ArticuloCompra;

/**
 * A simple {@link Fragment} subclass.
 */
public class NuevaCompra extends FragmentoAbstracto implements View.OnClickListener,AdapterView.OnItemClickListener {

    private ArrayList<ArticuloCompra> articulos;

    private LinearLayout contenedor;

    private Button btnPagar;
    private Button btnNuevoA;
    private Button btnCancelar;


    public NuevaCompra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = (View) inflater.inflate(R.layout.fragment_nueva_compra, container, false);
        this.btnPagar = (Button) vista.findViewById(R.id.btnPagar);
        this.btnNuevoA = (Button) vista.findViewById(R.id.btnRegistrarArticulo);
        this.btnCancelar = (Button) vista.findViewById(R.id.btnCancelarLista);
        contenedor = (LinearLayout) vista.findViewById(R.id.contenedorArticulos);

        this.articulos = new ArrayList<ArticuloCompra>();
        llenarLista();

        crearFilaArticulo();

        this.btnPagar.setOnClickListener(this);
        this.btnNuevoA.setOnClickListener(this);
        this.btnCancelar.setOnClickListener(this);

        //lvAnimales.setOnItemClickListener(this);

        return vista;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegistrarArticulo) {
            nuevoFragmento(new Nuevo_Articulo());
            /*Intent intent = new Intent(this.getContext(),Lector_QR.class); //(this, Lector_QR.class);
            startActivity(intent);*/
            //((FragmentoActividadAbsPrincipal)getActivity()).agregueFragmentoAPila(new LoginFragment());
        }

        if (v.getId() == R.id.btnPagar) {
            nuevoFragmento(new MenuPrincipal());
        }

        if (v.getId() == R.id.btnCancelarLista) {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position,
                            long ID) {
        // Al hacer click sobre uno de los items del ListView mostramos los
        // datos en los TextView.
       // tvNombre.setText(articulos.get(position).getNombre());
       // tvNumCelda.setText(String.valueOf(position));

    }

    public void llenarLista(){
        this.articulos.add(new ArticuloCompra("pasta colgate muy grande",2000,3));
        this.articulos.add(new ArticuloCompra("pasta colgate muy grande",2000,3));
        this.articulos.add(new ArticuloCompra("pasta colgate muymuymuy grande",2000,3));
        this.articulos.add(new ArticuloCompra("pasta colgate muy grande",2000,3));
        this.articulos.add(new ArticuloCompra("pasta colgate muymuy grande",2000,3));
        this.articulos.add(new ArticuloCompra("pasta colgate muy grande",2000,3));
        this.articulos.add(new ArticuloCompra("pasta colgate muymuy grande",2000,3));
        this.articulos.add(new ArticuloCompra("pasta colgate muymuymuy grande",2000,3));
        this.articulos.add(new ArticuloCompra("pasta colgate muymuymuymuymuy grande",2000,3));
    }

    public void crearFilaArticulo(){
        for(int i = 0; i < 34; i++){
            LinearLayout fila = new LinearLayout(this.getContext());
            fila.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            fila.setGravity(Gravity.LEFT);
            fila.setOrientation(LinearLayout.HORIZONTAL);
            String nombre = "Nombre medianoaa \n aaa al articulo ";
            Articulo articulo = new Articulo(crearNombreArticulo(nombre+i), crearNumeroArticulos(3), crearTotalArticulo("1300,32"), crearBotonCancelar());

            fila.addView(articulo.getNombreArticulo());
            fila.addView(articulo.getCantidadArticulos());
            fila.addView(articulo.getPrecioArticulo());
            fila.addView(articulo.getCancelar());

            contenedor.addView(fila);
        }
    }

    public Button crearBotonCancelar(){
        Button cancelar = new Button(this.getContext());
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams
                ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int)RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.rightMargin = 0;
        cancelar.setText("X");
        cancelar.setWidth(60);
        cancelar.setLayoutParams(params);
        cancelar.setPadding(5, 5, 5, 5);
        return cancelar;
    }

    public TextView crearTotalArticulo(String total){
        TextView txtTotal= new TextView(this.getContext());
        txtTotal.setText(String.valueOf(total));
        txtTotal.setGravity(Gravity.LEFT);
        txtTotal.setPadding(40, 10, 40, 20);
        return txtTotal;
    }

    public Spinner crearNumeroArticulos(int cantidad){
        Spinner articulos = new Spinner(this.getContext());
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this.getContext(), R.array.numeros , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        articulos.setAdapter(spinner_adapter);
        articulos.setSelection(cantidad);
        return articulos;
    }

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
