package pjrsolutions.ibuy.webServices;
import java.util.HashMap;
import java.util.Map;

import pjrsolutions.ibuy.business.Compra.NuevaCompra;
import pjrsolutions.ibuy.webServices.base.WebServiceCliente;

public class WebServiceRealizarCompra extends WebServiceCliente {

    private static final String URL_GET_PAGINA = "http://patrickconejo14.000webhostapp.com/webservices/webServiceRealizarCompra.php?codigo=realizarCompra&usuario=%s&articulos=%s&tarjeta=%s&total=%s&sucursal=%s";

    private static Map<String, String> URLS = (new HashMap<String, String>());

    public WebServiceRealizarCompra (NuevaCompra nuevaCompra) {

        super();

        this.putUrl("realizarCompra", WebServiceRealizarCompra.URL_GET_PAGINA);

    }

    @Override
    protected String doInBackground(String... params) {
        if (params.length == 0) {

            return null;

        } else {

            String[] valores = new String[params.length - 1];

            for (int x = 1; x < params.length; x++) {

                valores[x - 1] = params[x];

                System.out.println(valores[x - 1]);

            }

            this.setTipo(params[0]);

            if (params[0].equals("realizarCompra")) {
                this.setUrl(String.format(this.getUrls().get("realizarCompra"), valores));
                this.trabajo();
            }
        }
        return null;
    }


    @Override
    protected void onProgressUpdate(String[] values) {
        System.out.println("Exito: " + this.getRespuesta());
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String valor) {

    }
}
