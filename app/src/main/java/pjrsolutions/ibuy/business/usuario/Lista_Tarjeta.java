package pjrsolutions.ibuy.business.usuario;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;

import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pjrsolutions.ibuy.domain.TableListaTarjeta;
import pjrsolutions.ibuy.domain.Tarjeta;
import pjrsolutions.ibuy.webServices.WebServiceTarjeta;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_Tarjeta extends FragmentoAbstracto implements View.OnClickListener  {
    private TableLayout tabla;
    private Button agregarTarjeta;
    private String[]header={"Número de Tarjeta","Opciones"};
    private ArrayList<String[]>rows = new ArrayList<>();
    ListView listView;
    ProgressBar progressBar;
    WebServiceTarjeta webUser ;

    List<Tarjeta>listaTarjeta;

    public Lista_Tarjeta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =(View)inflater.inflate(R.layout.fragment_lista__tarjeta, container, false);
      // tabla =(TableLayout) view.findViewById(R.id.table);
      /* TableListaTarjeta  tablaTarjeta = new TableListaTarjeta(tabla,view.getContext().getApplicationContext());
       tablaTarjeta.addHeader(header);
       tablaTarjeta.addData(getTarjetas());
       tablaTarjeta.backgroundHeader(Color.GRAY);
       tablaTarjeta.backgroundData(Color.LTGRAY,Color.LTGRAY);*/
       agregarTarjeta =(Button) view.findViewById(R.id.agregarTarjeta);
       listView = (ListView) view.findViewById(R.id.listViewHeroes);
       progressBar =(ProgressBar) view.findViewById(R.id.progressBar);

       listaTarjeta = new ArrayList<>();
        this.agregarTarjeta.setOnClickListener(this);

        listarTarjetas();
       return view;
    }

    /*private ArrayList<String[]> getTarjetas(){
        rows.add(new String[]{"*****1234","modificar-Eliminar"});
        rows.add(new String[]{"*****3453","modificar-Eliminar"});
        rows.add(new String[]{"*****6786","modificar-Eliminar"});
        return rows;
    }*/


    private void listarTarjetas() {
        webUser = new WebServiceTarjeta(this);
        webUser.execute("mostrarLista","702360852");
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.agregarTarjeta){
            nuevoFragmento(new Registrar_Tarjeta());
        }/*if(v.getId()==R.id.textViewUpdate){

        }/*if(v.getId()==R.id.eliminarTarjeta()){
            //elimnar
        }*/
    }


    public void refreshHeroList(JSONArray heroes) throws JSONException {
        listaTarjeta.clear();

        for (int i = 0; i < heroes.length(); i++) {
            JSONObject obj = heroes.getJSONObject(i);

            this.listaTarjeta.add(new Tarjeta(obj.getString("id"),
                    obj.getString("numero"),
                    obj.getString("nombre"),
                    obj.getString("fecha"),
                    null,
                    obj.getString("direccion"),
                    obj.getString("codPostal")
            ));
        }
        HeroAdapter adapter = new HeroAdapter(listaTarjeta,this);
        listView.setAdapter(adapter);
    }

    public void deleteHero(String id){
        webUser = new WebServiceTarjeta(this);
        webUser.execute("eliminar",id);
    }
}



