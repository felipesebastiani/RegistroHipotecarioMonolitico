package pe.com.ibm.csm.app.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Util {
	private final static Logger			LOGGER				= LogManager.getLogger();

	public static final String	SALTO_LINEA		= "\n";
	public static final String	CADENA_VACIA	= "";
	public static final String	LETRA_S			= "S";
	public static final String	LETRA_N			= "N";
	public static final String	CADENA_TRUE		= "true";
	public static final String	CADENA_FALSE	= "false";

	public static String getStackTraceFromException(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter, true));
		return stringWriter.toString();
	}

	public static String getStackTraceFromThrowable(Throwable throwable) {
		StringWriter stringWriter = new StringWriter();
		throwable.printStackTrace(new PrintWriter(stringWriter, true));
		return stringWriter.toString();
	}

	public static int leerInteger(String cadena) {
		int numero = 0;
		try {
			numero = Integer.parseInt(cadena);
		}
		catch (NumberFormatException e) {
			numero = 0;
		}
		return numero;
	}

	public static Integer leerIntegerObject(String cadena) throws NumberFormatException {
		Integer numero = 0;
		try {
			numero = Integer.parseInt(cadena);
		}
		catch (NumberFormatException nfe) {
			LOGGER.error("[Util.leerIntegerObject]Error leyendo como Integer el valor [" + cadena + "]");
			throw nfe;
		}
		return numero;
	}

	public static Date cadenaAFecha(String fecha) {
		Locale locale = Locale.getDefault();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", locale);
		String cadenaFecha = fecha;
		Date fechaDate = null;
		try {
			fechaDate = (Date) formatter.parse(cadenaFecha);
		}
		catch (ParseException e) {
			fechaDate = null;
		}
		return fechaDate;
	}

	public static double leerDouble(String cadena) {
		double numero = 0;
		try {
			numero = Double.parseDouble(cadena);
		}
		catch (NumberFormatException e) {
			numero = 0;
		}
		return numero;
	}

	public static float leerFloat(String cadena) {
		float numero = 0;
		try {
			numero = Float.parseFloat(cadena);
		}
		catch (NumberFormatException e) {
			numero = 0;
		}
		return numero;
	}

	public static String generarIdTransaccion() {
		return new Date().getTime() + "";
	}

	public static void logInicioTransaccion(String idTransaccion, String nombre_metodo, Object request) {
		String mensajelog = "[idTx=" + idTransaccion + "][" + nombre_metodo + "]";
		LOGGER.info(Util.SALTO_LINEA + mensajelog + "[INICIO " + nombre_metodo + "]");
		LOGGER.info(mensajelog + "Datos de entrada: " + Util.SALTO_LINEA + JAXBUtilitarios.anyObjectToXmlText(request));
	}

	public static void logFinTransaccion(String idTransaccion, String nombre_metodo, Object response, long tiempoInicialTrx, long tiempoFinalTrx) {
		String mensajelog = "[idTx=" + idTransaccion + "][" + nombre_metodo + "]";
		if (response != null) {
			LOGGER.info(mensajelog + "Datos de Salida: " + Util.SALTO_LINEA + JAXBUtilitarios.anyObjectToXmlText(response));
		}
		else {
			LOGGER.info(mensajelog + "Datos de Salida: response = null");
		}

		LOGGER.info(mensajelog + "Tiempo total de proceso (ms): " + (tiempoFinalTrx - tiempoInicialTrx));
		LOGGER.info(mensajelog + Util.SALTO_LINEA + "[Fin " + nombre_metodo + "]" + Util.SALTO_LINEA + Util.SALTO_LINEA);
	}

	public static void logFinTransaccion(String mensajelog, String nombre_metodo, long tiempoInicialTrx, long tiempoFinalTrx) {
		LOGGER.info(mensajelog + "Tiempo total de proceso (ms): " + (tiempoFinalTrx - tiempoInicialTrx));
		LOGGER.info(mensajelog + Util.SALTO_LINEA + "[Fin " + nombre_metodo + "]" + Util.SALTO_LINEA + Util.SALTO_LINEA);
	}

	public static String getCurrentDateWithFormat(String formatPattern) {
		Locale locale = Locale.getDefault();
		Calendar now = new GregorianCalendar(locale);
		return getDateWithFormat(now, formatPattern);
	}

	public static String getDateWithFormat(Calendar date, String formatPattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern, Locale.getDefault());
		return dateFormat.format(date.getTime());
	}

	public static String[] getArregloPalabrasSegunSeparador(String texto, String separador) {
		String[] rpta = new String[0];
		try {
			rpta = texto.split("\\" + separador);
		}
		catch (Exception e) {
			rpta = new String[0];
		}

		return rpta;
	}

	public static long getDaysBetween(Date d1, Date d2) {
		return (long) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public static boolean isAlphanumeric(String str) {
		try {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a) {
					return false;
				}
			}
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	public static String getIdTransaccion(AuditRequest audit) {
		String idTransaccion = "";
		String idTransaccion_ref = null;
		if (audit == null) {
			idTransaccion = Util.generarIdTransaccion();
			LOGGER.info("No se encontro el objeto AuditRequest, se genero un nuevo idTransaccion: [" + idTransaccion + "]");
		}
		else {
			if (audit.getIdTransaccion() != null) {
				idTransaccion_ref = audit.getIdTransaccion();
			}

			if (idTransaccion_ref == null) {
				idTransaccion = Util.generarIdTransaccion();
				LOGGER.info("No se encontro el objeto AuditRequest.idTransaccion, se genero un nuevo idTransaccion: [" + idTransaccion_ref + "]");
			}
			else if (idTransaccion_ref.length() == 0) {
				idTransaccion = Util.generarIdTransaccion();
			}
			else {
				idTransaccion = idTransaccion_ref;
			}
		}
		return idTransaccion;
	}
	*/

	public static boolean between(Date date, Date dateStart, Date dateEnd) {
		if (date != null && dateStart != null && dateEnd != null) {
			if (date.after(dateStart) && date.before(dateEnd)) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	public static boolean esCampoVacio(String campo) {
		boolean vacio = false;

		if (campo == null) {
			vacio = true;
		}

		if (campo != null && campo.length() == 0) {
			vacio = true;
		}

		if (campo != null && campo.equalsIgnoreCase(Util.CADENA_VACIA)) {
			vacio = true;
		}

		return vacio;
	}
	
	public static String toJSon(Object object) {
		String jsonInString = null;
		try {
			if (object != null) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.enable(SerializationFeature.INDENT_OUTPUT);

				// Convert object to JSON string and pretty print
				jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			}
			else {
				jsonInString = "No se puede convertir a JSON, el objeto es nulo";
			}
		}
		catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (JsonMappingException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return jsonInString;
	}
}