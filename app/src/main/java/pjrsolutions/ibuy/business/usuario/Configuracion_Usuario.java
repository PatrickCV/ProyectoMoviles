package pjrsolutions.ibuy.business.usuario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;

import pjrsolutions.ibuy.business.base.FragmentoAbstracto;


public class Configuracion_Usuario extends FragmentoAbstracto implements View.OnClickListener{
    private Button actualizarBtn;
    private Button metodoPagoBtn;

    public Configuracion_Usuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =(View )inflater.inflate( R.layout.fragment_configuracion__usuario, container, false);
        this.actualizarBtn =(Button) view.findViewById(R.id.actualizarBtn);
        this.metodoPagoBtn =(Button) view.findViewById(R.id.metodoPagoBtn);

        this.actualizarBtn.setOnClickListener(this);
        this.metodoPagoBtn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actualizarBtn:{
                this.nuevoFragmento(new Modificar_Usuario());
                //this.nuevoFragmento(new Registrar_Tarjeta());
                break;
            }
            case R.id.metodoPagoBtn:{
                this.nuevoFragmento(new Lista_Tarjeta());
            }


        }
    }
}
