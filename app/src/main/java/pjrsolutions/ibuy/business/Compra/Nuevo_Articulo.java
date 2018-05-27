package pjrsolutions.ibuy.business.Compra;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;


/**
 * A simple {@link Fragment} subclass.
 */
public class Nuevo_Articulo extends FragmentoAbstracto implements View.OnClickListener {

    public Button escaner;
    public Button guardar;
    public Button cancelar;
    public Spinner numeros;

    View vista;

    public Nuevo_Articulo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = (View) inflater.inflate(R.layout.fragment_nuevo__articulo, container, false);

        escaner = (Button) vista.findViewById(R.id.BEscanear);
        guardar = (Button) vista.findViewById(R.id.BAgregarArticulo);
        cancelar = (Button) vista.findViewById(R.id.BCancelarArticulo);

        numeros = (Spinner) vista.findViewById(R.id.numero_articulos);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this.getContext(), R.array.numeros , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numeros.setAdapter(spinner_adapter);

        escaner.setOnClickListener(this);
        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        return vista;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.BEscanear){
            nuevoFragmento(new Lector_QR());
        }

    }
}

