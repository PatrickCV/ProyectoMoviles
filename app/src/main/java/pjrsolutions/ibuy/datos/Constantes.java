package pjrsolutions.ibuy.datos;

public class Constantes {
    //Tiempo de espera para que pase al login, mientras, se mostrara una barra que muestre que esta cargando algo
    public static int Tiempo_Espera = 5000;
    public static final String TAG_INFO = "INFO";

    public static final String PREFERENCIA_NOMBRE_ARCHIVO = "Mispreferencias";
    public static final String PREFERENCIA_RECORDAR_USUARIO = "remember_user";
    public static final String PREFERENCE_USERNAME = "username";
    public static final String PREFERENCE_SELECTED_RADIO_ID = "radio_id";
    public static final String PREFERENCE_PROGRESS_POSITION = "progress";

    public static final String EMPTY_STRING = "";

    public static final int DATABASE_VERSION = 1;
    public static final String BD_NOMBRE = "DBCarrito.db";
    public static final String TABLA_CARRO = "tb_carrito_actual";
    public static final String CARRO_ARTICULO_ID= "Id";
    public static final String CARRO_ARTICULO_NOMBRE = "Nombre";
    public static final String CARRO_ARTICULO_DESCRIPCION = "Descripcion";
    public static final String CARRO_ARTICULO_CANTIDAD = "Cantidad";
    public static final String CARRO_ARTICULO_PRECIO_UNIDAD = "Precio";
    public static final String CARRO_ARTICULO_SUCURSAL = "Sucursal";
    public static final String CARRO_ARTICULO_ESTADO = "Estado";

    public static final String CONSULTA_CREA_TABLA_CARRITO_ACTUAL = "CREATE TABLE " +
            Constantes.TABLA_CARRO + "(" +
            Constantes.CARRO_ARTICULO_ID  +" integer primary key autoincrement, " +
            Constantes.CARRO_ARTICULO_NOMBRE  +" text, " +
            Constantes.CARRO_ARTICULO_DESCRIPCION + " text, " +
            Constantes.CARRO_ARTICULO_CANTIDAD  + " text, " +
            Constantes.CARRO_ARTICULO_PRECIO_UNIDAD  + " text, " +
            Constantes.CARRO_ARTICULO_SUCURSAL  + " text, " +
            Constantes.CARRO_ARTICULO_ESTADO + " text)" ;

    public static final String CONSULTA_BORRA_TABLA_CARRITO = "DROP TABLE IF EXISTS " + Constantes.TABLA_CARRO;

    public static final String CONSULTA_CAPTURA_LISTA_CARRITO = "SELECT * FROM " + Constantes.TABLA_CARRO;
}
