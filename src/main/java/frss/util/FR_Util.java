package frss.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FR_Util {
	final static String			nombre_clase					= "FR_Util";
	private static String		nombre_archivo_log				= "";
	final static String			extension_html					= ".html";
	public static final String	SALTOLINEA						= "\n";
	private static String		nombre_aplicacion				= "";
	private static String		ruta_reporte_sucesos			= "";
	private static String		ruta_config_xml					= "";
	private static File			directorio_config_aplicacion	= null;
	private static File			directorio_reporte_sucesos		= null;
	private static File			directorio_xml_aplicacion		= null;
	private static boolean		mostrar_mensajes_en_consola		= true;

	private static Logger		LOGGER							= LogManager.getLogger();

	// private static boolean isLoggerInicializado = false;

	/*
	protected static void inicializarLogger() {
		String ruta_log4j_xml = getRuta_WEBINF() + System.getProperty("file.separator") + "log4j.xml";
		if (!isLoggerInicializado) {
			System.out.println("Intentando acceder al recurso: " + ruta_log4j_xml + ". Existe?: " + new File(ruta_log4j_xml).exists());

			URL url = Loader.getResource(ruta_log4j_xml); // Esto carga el fichero como recurso si está en el CLASSPATH

			if (url != null) {
				PropertyConfigurator.configure(url);
			}
			isLoggerInicializado = true;
		}

		if (logger == null) {
			System.out.println("Se generará el logger según el archivo: [" + ruta_log4j_xml + "]");
			logger = Logger.getLogger(FR_Util.class);
		}
	}
	 */

	public static long genera_serialVersionUID() {
		return FR_Numeros.aleatorio(0000000000000000000L, 8888888888888888888L);
	}

	/**
	 * Permite el cambio visual entre Paneles
	 */
	public static void cambiarPanel(JPanel pnl_a_ocultar, JPanel pnl_a_mostrar) {
		pnl_a_ocultar.setVisible(false);
		pnl_a_mostrar.setVisible(true);
	}

	/**
	 * Imprime información en consola de diversas formas
	 */
	public static void trace(Object objeto) {
		// inicializarLogger();
		if (mostrar_mensajes_en_consola == true) {
			System.out.print(objeto + "\n");
		}
		LOGGER.info(objeto);
	}

	public static void traceTipoTabla(Object objeto) {
		// inicializarLogger();
		System.out.print(objeto + "\t");
		LOGGER.info(objeto + "\t");
	}

	public static void traceTipoDebug(String mensaje, Object lugar, Exception e) {
		// inicializarLogger();
		String num_linea = e.getStackTrace()[0].getLineNumber() + "";
		System.out.print("\nEn la clase: " + lugar + " (linea: " + num_linea + "):\n" + mensaje + "\n\n");
		String cadena = FR_Fecha_Hora.getFecha_actual_formato_dia_guion_mes_guion_anho() + " " + FR_Fecha_Hora.getHora_actual() + ": En --> " + lugar + " (linea : " + num_linea + "). Información: " + mensaje + ".";

		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String stacktrace = sw.toString();
		cadena += "Stack Trace Debugged:\n" + stacktrace;

		if (mostrar_mensajes_en_consola == true) {
			System.out.println(cadena);
		}
		LOGGER.info(cadena);
	}

	// Ejecuta un proceso en el Sistema Operativo:
	public static Process ejecutaProceso(String comandos) {
		Process proceso = null;
		try {
			proceso = Runtime.getRuntime().exec(comandos);
		}
		catch (IOException e) {
			FR_Util.traceTipoDebug("Fallo en la ejecución del proceso: " + proceso + "\nMotivo:" + e.getMessage(), "FR_Util", e);
		}
		return proceso;
	}

	public static void ejecutaAplicacionWindows(String comandos) {
		ejecutaProceso("cmd /c" + comandos);
	}

	public static void abrirArchivo(File archivo) {
		if (Desktop.isDesktopSupported() == true) {
			Desktop desktop = Desktop.getDesktop();
			try {
				if (archivo.exists() == true) {
					desktop.open(archivo);
				}
				else {
					JOptionPane.showMessageDialog(null, "No se encontrar el archivo: " + archivo.getAbsolutePath(), "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
			catch (IOException e) {
				FR_Util.traceTipoDebug("Se ha producido un error: " + e, FR_Util.nombre_clase, e);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No se puede ejecutar el comando de apertura en este sistema operativo", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}

	// Retira los TAGs de una cadena HTML y devuelve sólo el texto
	public static String filtraContenidoHTML(String cadenaHTML) {
		String texto_llano = "";
		boolean flag = true;
		try {
			for (int i = 0; i < cadenaHTML.length(); i++) {
				if (cadenaHTML.charAt(i) == '<')
					flag = false;
				else if (cadenaHTML.charAt(i) == '>')
					flag = true;

				if (flag == true && cadenaHTML.charAt(i) != '>')
					texto_llano += cadenaHTML.charAt(i);
			}
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error: " + e, FR_Util.nombre_clase, e);
		}
		return texto_llano;
	}

	public static void ejecuta_retraso(int num_segundos) {
		try {
			// Espera num_segundos para hacer la toma
			long time = Long.parseLong(num_segundos + "") * 1000;
			FR_Util.trace("Esperando " + (time / 1000) + " segundo(s) para hacer la captura de pantalla...");
			Thread.sleep(time);
		}
		catch (NumberFormatException nfe) {
			FR_Util.traceTipoDebug("La cantidad de tiempo no expresa un valor valido de segundos.", FR_Util.nombre_clase, nfe);
		}
		catch (InterruptedException e) {
			FR_Util.traceTipoDebug("Se ha producido un error: " + e, FR_Util.nombre_clase, e);
		}
	}

	public static String getNombre_aplicacion() {
		return nombre_aplicacion;
	}

	public static void setNombre_aplicacion(String nombre_aplicacion) {
		FR_Util.nombre_aplicacion = nombre_aplicacion;
	}

	public static File getDirectorio_config_aplicacion() {
		return directorio_config_aplicacion;
	}

	public static void setDirectorio_config_aplicacion(File directorio_config_aplicacion) {
		FR_Util.directorio_config_aplicacion = directorio_config_aplicacion;
	}

	public static File getDirectorio_reporte_sucesos() {
		return directorio_reporte_sucesos;
	}

	public static void setDirectorio_reporte_sucesos(File directorio_reporte_sucesos) {
		FR_Util.directorio_reporte_sucesos = directorio_reporte_sucesos;
	}

	public static File getDirectorio_xml_aplicacion() {
		return directorio_xml_aplicacion;
	}

	public static void setDirectorio_xml_aplicacion(File directorio_xml_aplicacion) {
		FR_Util.directorio_xml_aplicacion = directorio_xml_aplicacion;
	}

	public static String getNombre_archivo_log() {
		return nombre_archivo_log;
	}

	public static void setNombre_archivo_log(String nombre_archivo_log) {
		FR_Util.nombre_archivo_log = nombre_archivo_log;
	}

	public static String getRuta_reporte_sucesos() {
		return ruta_reporte_sucesos;
	}

	public static void setRuta_reporte_sucesos(String ruta_reporte_sucesos) {
		FR_Util.ruta_reporte_sucesos = ruta_reporte_sucesos;
	}

	public static String getRuta_config_xml() {
		ruta_config_xml = getRuta_config_xml_WEB();
		return ruta_config_xml;
	}

	public static void setRuta_config_xml(String ruta_config_xml) {
		FR_Util.ruta_config_xml = ruta_config_xml;
	}

	public static String getRuta_WEBINF() {
		FR_Util fr_util = new FR_Util();
		ruta_config_xml = new File(fr_util.getRuta_aplicacion_WEB() + System.getProperty("file.separator") + "WEB-INF").getAbsolutePath();
		return ruta_config_xml;
	}

	public static String getRuta_config_xml_WEB() {
		FR_Util fr_util = new FR_Util();
		ruta_config_xml = new File(fr_util.getRuta_aplicacion_WEB() + System.getProperty("file.separator") + "xml").getAbsolutePath();
		return ruta_config_xml;
	}

	private String getRuta_aplicacion_WEB() {
		String ruta = "";
		FR_Util fr_util = new FR_Util();

		URL url = null;
		try {
			Enumeration<URL> enumeration = fr_util.getClass().getClassLoader().getResources("/");
			while (enumeration.hasMoreElements()) {
				URL element = enumeration.nextElement();
				if (element != null) {
					url = element;
				}
			}
		}
		catch (Exception e) {
			trace("Error: " + e);
		}

		try {
			File fx = new File(url.toURI());

			// Obtenemos la carpeta classes y luego su parent que es WEB-INF
			ruta = fx.getParentFile().getParentFile().getAbsolutePath();

		}
		catch (Exception e) {
			trace("Error: " + e);
		}

		return ruta;
	}

	public static ArrayList<String[]> getCamposBean(Object obj) throws IllegalAccessException {
		ArrayList<String[]> arreglo_campos = new ArrayList<String[]>();

		if (obj != null) {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					String nombre_campo = field.getName();
					String valor_campo = field.get(obj) + "";
					String[] campos = new String[2];
					campos[0] = nombre_campo;
					campos[1] = valor_campo;

					if (nombre_campo.equalsIgnoreCase("serialVersionUID") == false) {
						arreglo_campos.add(campos);
					}
				}
				catch (IllegalAccessException e) {
					throw e;
				}
			}
		}

		return arreglo_campos;
	}

	public static String getStackTraceFromException(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter, true));
		return stringWriter.toString();
	}

	// Soportado desde java 1.8
	public static String encodeFileToBase64(String filePath) throws IOException {
		String base64 = "";
		File file = new File(filePath);
		try (FileInputStream fis = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte fileData[] = new byte[(int) file.length()];
			fis.read(fileData);
			base64 = java.util.Base64.getEncoder().encodeToString(fileData);
		}
		catch (FileNotFoundException e) {
			throw e;
		}
		catch (IOException ioe) {
			throw ioe;
		}
		return base64;
	}

	// Soportado desde java 1.8
	public static void decoder(String base64, String pathFile) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(pathFile)) {
			// Converting a Base64 String into Image byte array
			byte[] fileData = java.util.Base64.getDecoder().decode(base64);
			fos.write(fileData);
		}
		catch (FileNotFoundException e) {
			throw e;
		}
		catch (IOException ioe) {
			throw ioe;
		}
	}

	public static String toJSon(Object object) {
		String jsonInString = null;
		try {
			if (object != null) {
				ObjectMapper mapper = new ObjectMapper();

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