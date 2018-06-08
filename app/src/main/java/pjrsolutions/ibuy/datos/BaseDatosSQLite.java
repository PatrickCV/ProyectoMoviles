package pjrsolutions.ibuy.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pjrsolutions.ibuy.domain.ArticuloCompra;

public class BaseDatosSQLite extends SQLiteOpenHelper {
    public Context context;
    public SQLiteDatabase.CursorFactory factory;


    public BaseDatosSQLite(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, Constantes.BD_NOMBRE, factory, Constantes.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constantes.CONSULTA_CREA_TABLA_CARRITO_ACTUAL);
    }

    @Override
    /**
     * pERMITE ACTUALIZAR UNA TABLA EXISTENTE
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constantes.CONSULTA_CREA_TABLA_CARRITO_ACTUAL);
        onCreate(db);
    }

    /**
     * @param articulo
     * @return iNSERTA UN USUARIO NUEVO QUE YA FUE CARGADO EN EL REGISTRO
     */
    public boolean insertArticle(ArticuloCompra articulo, String descripcion, String sucursal, String estado, String idArticulo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.CARRO_ARTICULO_NOMBRE, articulo.getNombre());
        contentValues.put(Constantes.CARRO_ARTICULO_DESCRIPCION, descripcion);
        contentValues.put(Constantes.CARRO_ARTICULO_CANTIDAD, articulo.getCantidad());
        contentValues.put(Constantes.CARRO_ARTICULO_PRECIO_UNIDAD, articulo.getPrecio());
        contentValues.put(Constantes.CARRO_ARTICULO_SUCURSAL, sucursal);
        contentValues.put(Constantes.CARRO_ARTICULO_ESTADO, estado);
        contentValues.put(Constantes.CARRO_ARTICULO_IDARTICULO, idArticulo);
        long result = db.insert(Constantes.TABLA_CARRO, null, contentValues);
        //long result = 1;
        return (result != -1) ? true : false;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from tb_carrito_actual where Estado = '1'", null);
        return res;
    }

    public Cursor getLastInsertData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from tb_carrito_actual where Estado = '0'  order by Id desc limit 1", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Constantes.TABLA_CARRO);
        return numRows;
    }

    public boolean updateArticle(ArticuloCompra articulo, String descripcion, String sucursal, String estado, String idArticulo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.CARRO_ARTICULO_NOMBRE, articulo.getNombre());
        contentValues.put(Constantes.CARRO_ARTICULO_DESCRIPCION, descripcion);
        contentValues.put(Constantes.CARRO_ARTICULO_CANTIDAD, articulo.getCantidad());
        contentValues.put(Constantes.CARRO_ARTICULO_PRECIO_UNIDAD, articulo.getPrecio());
        contentValues.put(Constantes.CARRO_ARTICULO_SUCURSAL, sucursal);
        contentValues.put(Constantes.CARRO_ARTICULO_ESTADO, estado);
        contentValues.put(Constantes.CARRO_ARTICULO_IDARTICULO, idArticulo);
        db.update(
                Constantes.TABLA_CARRO, contentValues, Constantes.CARRO_ARTICULO_NOMBRE + " = ? ", new String[]{
                        articulo.getNombre()
                });
        return true;
    }

    public Integer deleteArticle(String articulo) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(
                Constantes.TABLA_CARRO, Constantes.CARRO_ARTICULO_NOMBRE + " = ? ", new String[]{
                        articulo
                });
    }

    public Integer deleteArticleState(String estado) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(
                Constantes.TABLA_CARRO, Constantes.CARRO_ARTICULO_ESTADO + " = ?", new String[]{
                        estado
                });
    }

}
