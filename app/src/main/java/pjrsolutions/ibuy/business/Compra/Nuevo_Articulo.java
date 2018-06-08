package pjrsolutions.ibuy.business.Compra;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.datos.BaseDatosSQLite;
import pjrsolutions.ibuy.domain.Articulo;
import pjrsolutions.ibuy.domain.ArticuloCompra;


/**
 * A simple {@link Fragment} subclass.
 */
public class Nuevo_Articulo extends FragmentoAbstracto implements View.OnClickListener {

    private Button escaner;
    private Button guardar;
    private Button cancelar;

    private TextView txtDescripcion;
    private TextView txtPrecio;
    private Spinner numeros;
    private TextView txtNombre;

    private String sucursal;
    private String idArticulo;

    View vista;

    public Nuevo_Articulo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = (View) inflater.inflate(R.layout.fragment_nuevo__articulo, container, false);

        this.escaner = (Button) vista.findViewById(R.id.BEscanear);
        this.guardar = (Button) vista.findViewById(R.id.BAgregarArticulo);
        this.cancelar = (Button) vista.findViewById(R.id.BCancelarArticulo);

        this.txtNombre = (TextView) vista.findViewById(R.id.txtNombreArticulo);
        this.txtDescripcion = (TextView) vista.findViewById(R.id.txtDescripcionArticulo);
        this.txtPrecio = (TextView) vista.findViewById(R.id.txtPrecioArticulo);

        this.numeros = (Spinner) vista.findViewById(R.id.numero_articulos);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this.getContext(), R.array.numeros , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.numeros.setAdapter(spinner_adapter);


        BD = new BaseDatosSQLite(this.getActivity(), null);

        llenarEspaciosDatos();

        this.numeros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id) {
                Cursor dato = (Cursor) BD.getData();
                dato.moveToFirst();
                String cantidadEscogida = ((Spinner) vista.findViewById(R.id.numero_articulos)).getSelectedItem().toString();
                for (int i = 0; i < dato.getCount(); i++) {
                    if (((TextView) vista.findViewById(R.id.txtNombreArticulo)).getText().toString().equals(dato.getString(1).toString())) {
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

        this.escaner.setOnClickListener(this);
        this.guardar.setOnClickListener(this);
        this.cancelar.setOnClickListener(this);

        return vista;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.BEscanear){
            nuevoFragmento(new Lector_QR());
        }

        if(v.getId() == R.id.BCancelarArticulo){
            if(BD.deleteArticleState("0") == 1)
                getActivity().onBackPressed();
        }

        if(v.getId() == R.id.BAgregarArticulo){
            String nombre = txtNombre.getText().toString();
            String[] txtPrecioValor = txtPrecio.getText().toString().split(":");
            Float precio = Float.parseFloat(txtPrecioValor[1]);
            String cantidad = numeros.getSelectedItem().toString();
            ArticuloCompra articulo = new ArticuloCompra(nombre,precio,Integer.parseInt(cantidad));
            BD.updateArticle(articulo,txtDescripcion.getText().toString(),this.sucursal,"1",this.idArticulo);
            getActivity().onBackPressed();
        }
    }

     public void llenarEspaciosDatos(){
        Cursor dato = (Cursor) BD.getLastInsertData();
        dato.moveToFirst();
        if(dato != null && dato.getCount() > 0) {
            this.txtNombre.setText(dato.getString(1));
            this.txtDescripcion.setText(dato.getString(2));
            this.numeros.setSelection(Integer.parseInt(dato.getString(3))-1);
            this.txtPrecio.setText("Precio del articulo: " + String.valueOf(Float.parseFloat(dato.getString(4)) * Integer.parseInt(dato.getString(3))));
            this.sucursal = dato.getString(5);
            this.idArticulo = dato.getString(7);
        }else
            nuevoFragmento(new Lector_QR());
    }

}

