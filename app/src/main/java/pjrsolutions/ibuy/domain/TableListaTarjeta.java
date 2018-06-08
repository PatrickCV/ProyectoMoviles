package pjrsolutions.ibuy.domain;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableListaTarjeta {
    private TableLayout tableLayout;
    private Context context;
    private String[]header;
    private ArrayList<String[]> data;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC;
    private int indexR;
    private boolean multiColor=false;
    private int firstColor, secondColor;

    public TableListaTarjeta(TableLayout tableLayout, Context context){
        this.tableLayout = tableLayout;
        this.context=context;
    }
    public void addHeader(String[] header){
        this.header=header;
        createHeader();
    }
    public void addData(ArrayList<String[]>data){
        this.data=data;
        createDataTable();
    }
    public void newRow(){
        tableRow= new TableRow(context);
    }
    public void newCell(){
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(25);
    }
    private void createHeader(){
        indexC=0;//Columna
        newRow();
        while (indexC<header.length){
            newCell();
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }
    private void  createDataTable(){
        String info;
        for(indexR=1;indexR<=header.length;indexR++){
            newRow();
            for(indexC=0;indexC<header.length;indexC++){
                newCell();
                String[] colums= data.get(indexR-1);
                info=(indexC<colums.length)?colums[indexC]:"";
                txtCell.setText(info);
                tableRow.addView(txtCell,newTableRowParams());

            }
            tableLayout.addView(tableRow);
        }
    }
    //añadir items ala tabla
    public void addItems(String[]item){
        String info;
        data.add(item);
        indexC=0;
        newRow();
        while (indexC<header.length){
            newCell();
            info=(indexC<item.length)?item[indexC]:"";
            txtCell.setText(info);
            tableRow.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(tableRow,data.size());
        reColoring();
    }
    public void backgroundHeader(int color){
        indexC=0;//Columna
        while (indexC<header.length){
            txtCell=getCell(0,indexC++);
            txtCell.setTextColor(Color.BLACK);
            txtCell.setBackgroundColor(color);
        }
    }
    public void backgroundData(int firstColor,int secondColor){
        for(indexR=1;indexR<=header.length;indexR++){
            multiColor=!multiColor;
            for(indexC=0;indexC<header.length;indexC++){
                txtCell=getCell(indexR,indexC);
                txtCell.setTextColor(Color.BLACK);
                txtCell.setBackgroundColor((multiColor)?firstColor:secondColor);

            }
            this.firstColor=firstColor;
            this.secondColor=secondColor;
        }
    }
    public void reColoring(){
        indexC=0;//Columna
        multiColor=!multiColor;
        while (indexC<header.length){
            txtCell=getCell(data.size()-1,indexC++);
            txtCell.setBackgroundColor((multiColor)?firstColor:secondColor);
        }
    }
    private TableRow getRow(int index){
        return (TableRow)tableLayout.getChildAt(index);
    }
    private TextView getCell(int rowIndex,int colIndex){
        tableRow=getRow(rowIndex);
        return (TextView) tableRow.getChildAt(colIndex);
    }
    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params= new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return params;
    }
}

