package pjrsolutions.ibuy.webServices.base;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
	Esta clase abstracta posee utilidades para realizar consultas asincronicas.
*/
public abstract class WebServiceCliente extends AsyncTask<String, String, String> {

	private Map<String, String> urls; // Diccionario de urls.
	private String url; // Url actual.
	private String respuesta; // Respuesta del servidor.
	private String tipo; // Tipo de consulta.
	private int maxIntentos; // Maxima cantidad de intentos.

	/**
		Constructor inicializa los campos.
	*/
	public WebServiceCliente () {

		this.urls = new HashMap<String, String>();
		this.url = "";
		this.respuesta = "";
		this.tipo = "";
		this.maxIntentos = 3;

	}

	/**
	* Poseera las actividades del hilo.
	*
	* @param params: array de parametros
	* @return String
	*/
	@Override
	protected abstract String doInBackground (String... params);

	/**
	* Servira para realizar cambios a la vista cuando haya una respuesta
	* o algun resultado.
	*
	* @param params: array de parametros
	*/
	@Override
	protected abstract void onProgressUpdate (String... params);

	/**
	* Aqui se ejecutan actividades antes de iniciar el hilo.
	*/
	@Override
	protected abstract void onPreExecute ();

	/**
	* Aqui se ejecutan actividades despues de iniciar el hilo.
	*
	* @param valor: valor enviado.
	*/
	@Override
	protected abstract void onPostExecute (String valor);

	/**
	* Realiza los intentos de realizar la peticion al servidor hasta cierta
	* cantidad de intentos.
	* Si hay respuesta, enviara "exito" al onProgressUpdate, sino,
	* enviara "error".
	*/
	protected void trabajo () {

		boolean respondio = false;

		for (int x = 0; x < this.getMaxIntentos(); x ++) {

			System.out.println("=========== Intento " + (x + 1));

			if (this.hacerPeticion()) {

				respondio = true;

				break;

			}

		}

		if (respondio) {

			this.publishProgress("exito");

		} else {

			this.publishProgress("error");

		}

	}

	/**
	* Realiza la peticion al servidor y conserva el resultado en caso de
	* haber exito.
	*
	* @return boolean: respondio correcta o incorrectamente la peticion.
	*/
	private boolean hacerPeticion () {

		StringBuilder respuesta;

		try {

			URL url = new URL(this.url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			respuesta = new StringBuilder();
			String inputLine;

			while ((inputLine = input.readLine()) != null) {
				respuesta.append(inputLine);
			}

			input.close();

			this.respuesta = respuesta.toString();
			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;

		}

	}

	/**
	* Sets, Gets y Otros.
	*/

	protected Map<String, String> getUrls () {

		return this.urls;

	}

	protected void putUrl (String llave, String url) {

		this.urls.put(llave, url);

	}

	protected void setUrl (String url) {

		this.url = url;

	}

	protected String getUrl () {

		return this.url;

	}

	public void setRespuesta (String respuesta) {

		this.respuesta = respuesta;

	}

	protected String getRespuesta () {

		return this.respuesta;

	}

	protected void setTipo (String tipo) {

		this.tipo = tipo;

	}

	protected void setMaxIntentos (int maxIntentos) {

		this.maxIntentos = maxIntentos;

	}

	protected int getMaxIntentos () {

		return this.maxIntentos;

	}

}