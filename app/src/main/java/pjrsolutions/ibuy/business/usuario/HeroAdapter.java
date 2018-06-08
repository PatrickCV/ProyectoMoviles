package pjrsolutions.ibuy.business.usuario;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pjrsolutions.ibuy.R;
import pjrsolutions.ibuy.business.base.ActividadAbstracta;
import pjrsolutions.ibuy.domain.Tarjeta;

public class HeroAdapter extends ArrayAdapter<Tarjeta> {

    //our hero list
    List<Tarjeta> heroList;
    Lista_Tarjeta lista_tarjeta;


    //constructor to get the list
    public HeroAdapter(List<Tarjeta> heroList,Lista_Tarjeta lista_tarjeta) {
        super(lista_tarjeta.getActivity(), R.layout.layout_lista_tarjetas, heroList);
        this.heroList = heroList;
        this.lista_tarjeta=lista_tarjeta;
    }


    //method returning list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = lista_tarjeta.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_lista_tarjetas, null, true);

        //getting the textview for displaying name
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);

        //the update and delete textview
        TextView textViewUpdate = listViewItem.findViewById(R.id.textViewUpdate);
        TextView textViewDelete = listViewItem.findViewById(R.id.textViewDelete);

        final Tarjeta tarjeta = this.heroList.get(position);

        textViewName.setText(tarjeta.getNumeroTarjeta());

        //attaching click listener to update
        textViewUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //so when it is updating we will
                //make the isUpdating as true
                //isUpdating = true;
                Bundle args = new Bundle();
                args.putString("id",String.valueOf(tarjeta.getId()));
                args.putString("numero",String.valueOf(tarjeta.getNumeroTarjeta()));
                args.putString("nombre",String.valueOf(tarjeta.getNombreTitular()));
                args.putString("fecha",String.valueOf(tarjeta.getFechaVencimiento()));
                args.putString("direccion",String.valueOf(tarjeta.getDireccion()));
                args.putString("codPostal",String.valueOf(tarjeta.getCodigoPostal()));
                Modificar_Tarjeta frgament= new Modificar_Tarjeta();
                frgament.setArguments(args);
                lista_tarjeta.nuevoFragmento(frgament);
                //we will set the selected hero to the UI elements
                /*editTextHeroId.setText();
                editTextName.setText(hero.getName());
                editTextRealname.setText(hero.getRealname());
                ratingBar.setRating(hero.getRating());
                spinnerTeam.setSelection(((ArrayAdapter<String>) spinnerTeam.getAdapter()).getPosition(hero.getTeamaffiliation()));

                //we will also make the button text to Update
                buttonAddUpdate.setText("Update");*/
            }
        });

        //when the user selected delete
        textViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // we will display a confirmation dialog before deleting
                AlertDialog.Builder builder = new AlertDialog.Builder(lista_tarjeta.getActivity());

                builder.setTitle("Delete " + tarjeta.getNumeroTarjeta())
                        .setMessage("Desea borrar tarjtea ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                lista_tarjeta.deleteHero(tarjeta.getId());
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        return listViewItem;
    }
}