/**
 * @value: Descripcion de este archivo
 * @Type: Java Class File
 * @class: ConexionEstandar_FRSS.java
 * @package frss.swingcomponentes;
 * @descripcion: Clase que permite la conexion a una Base de Datos que contiene metodos de uso estandar. Requiere clase DatosConexion_FRSS.java
 * @author: Felipe Roberto Sebastiani Sobenes (FRSS)
 * @author_web: felipe@frss-soft.com
 * @author_email: felipesebastiani@yahoo.com
 * @author_company: FRSS Soft inc.
 * @fecha_de_creacion: 13-01-2006
 * @fecha_de_ultima_actualizacion: 20-08-2011
 */

package frss.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import frss.util.FR_Util;

public class ConexionEstandar_FRSS {
	private static final String	ubicacionError				= "ConexionEstandar_FRSS";
	public static boolean		activar_mensajes_en_consola	= true;
	public static boolean		cargaInfoCopyRights			= true;
	protected Connection		connection					= null;
	protected Statement			statement					= null;
	private DataSource			datasource					= null;
	private ResultSet			resultset					= null;

	public ConexionEstandar_FRSS() {

	}

	static {
		if (cargaInfoCopyRights == true) {
			String info = new String();
			info += "\n";
			info += "La libreria frss_soft.jar" + "\n";
			info += "Clase: ConexionEstandar_FRSS" + "\n";
			info += "Creador: Felipe R. Sebastiani Sobenes" + "\n";
			info += "Email01: felipesebastiani@gmail.com" + "\n";
			info += "Email02: felipe@frss-soft.com" + "\n";
			info += "Ultima actualizacion: " + "26/04/2014 10:20 pm (-5 GMT)" + "\n";
			FR_Util.trace(info);
			cargaInfoCopyRights = false;
		}
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	protected String getCadenaTiempoTranscurrido(String nombreMetodo,long elapsedTime) {
		//return String.format("Proceso ejecutado en %d mins, %d sec", TimeUnit.MILLISECONDS.toSeconds(elapsedTime), TimeUnit.MILLISECONDS.toSeconds(elapsedTime) - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(elapsedTime)));
		return nombreMetodo + "[Proceso ejecutado en " + elapsedTime + " milisegundo(s)]";
	}

	public static boolean isCargaInfoCopyRights() {
		return cargaInfoCopyRights;
	}

	public static void setCargaInfoCopyRights(boolean cargaInfoCopyRights) {
		ConexionEstandar_FRSS.cargaInfoCopyRights = cargaInfoCopyRights;
	}

	public boolean isActivar_mensajes_en_consola() {
		return activar_mensajes_en_consola;
	}

	public void setActivar_mensajes_en_consola(boolean activar_mensajes_en_consola) {
		ConexionEstandar_FRSS.activar_mensajes_en_consola = activar_mensajes_en_consola;
	}

	/**
	 * Definicion: Este metodo permite obtener una conexion al servidor de base de datos con parametros personalizados. Si no logra conectarse devulve null.
	 */
	public Connection getConnection(String cadena_driver, String cadena_conexion, String BD_usuario, String BD_password) {
		// FR_Util.trace("BD_usuario: " + BD_usuario);
		// FR_Util.trace("BD_password: " + BD_password);

		try {
			Driver driver = (Driver) Class.forName(cadena_driver).newInstance();
			DriverManager.registerDriver(driver);
			this.connection = DriverManager.getConnection(cadena_conexion, BD_usuario, BD_password);
		}
		catch (NullPointerException e) {
			// mensaje_error = "Conexion fallida. Motivo: " + e.getMessage();
			// FR_Util.traceTipoDebug(mensaje_error, ubicacionError, e);
			FR_Util.traceTipoDebug("Se ha producido un error [NullPointerException] definido como: ", "ConexionEstandar_FRSS.getConnection(...)", e);
		}
		catch (SQLException e) {
			// mensaje_error = "La instruccion SQL no es valida.\n" +
			FR_Util.traceTipoDebug("Se ha producido un error [SQLException] definido como: ", "ConexionEstandar_FRSS.getConnection(...)", e);
		}
		catch (InstantiationException e) {
			FR_Util.traceTipoDebug("Se ha producido un error [InstantiationException] definido como: ", "ConexionEstandar_FRSS.getConnection(...)", e);
		}
		catch (IllegalAccessException e) {
			FR_Util.traceTipoDebug("Se ha producido un error [IllegalAccessException] definido como: ", "ConexionEstandar_FRSS.getConnection(...)", e);
		}
		catch (ClassNotFoundException e) {
			FR_Util.traceTipoDebug("Se ha producido un error [ClassNotFoundException] definido como: ", "ConexionEstandar_FRSS.getConnection(...)", e);
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error [Exception] definido como: ", "ConexionEstandar_FRSS.getConnection(...)", e);
		}

		return this.connection;
	}

	/**
	 * Definicion: Este metodo permite obtener una conexion al servidor de base de datos con parametros personalizados. Si no logra conectarse devulve null.
	 */
	public Connection getConnection() {
		try {
			if (this.connection == null) {
				this.datasource.setLoginTimeout(5);
				this.connection = datasource.getConnection();
			}
		}
		catch (NullPointerException e) {
			// mensaje_error = "Conexion fallida. Motivo: " + e.getMessage();
			// FR_Util.traceTipoDebug(mensaje_error, ubicacionError, e);
			FR_Util.traceTipoDebug("Se ha producido un error [NullPointerException] definido como: ", "ConexionEstandar_FRSS.getConnection(...)", e);
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error [Exception] definido como: ", "ConexionEstandar_FRSS.getConnection(...)", e);
		}

		return this.connection;
	}

	/**
	 * Definicion: Este metodo cierra la conexion antes establecida al servidor de base de datos.
	 */
	public void close_conection() {
		try {
			String idConexion = this.connection + "";
			if (this.connection != null) {
				//String cadena_desconexion = "Intentando cerrar conexion con base de datos: " + idConexion + ", this.connection.isClosed()?: " + this.connection.isClosed();
				
				if(this.statement != null) {
					if (!this.statement.isClosed()) {
						//this.statement.closeOnCompletion();
						//cadena_desconexion += ", statement.isClosed()?: " + statement.isClosed();
						this.statement = null;
					}
				}
				
				//FR_Util.trace(cadena_desconexion);
			}
			
			DataSourceUtils.releaseConnection(this.connection, this.datasource);
			
			if (this.connection != null && this.connection.isClosed()) {
				//FR_Util.trace("Se ha cerrado la conexion con base de datos: " + idConexion);
				this.connection = null;
			}
			
			if (this.connection != null && this.connection.isClosed()==false) {
				FR_Util.trace("No se ha logrado cerrar la conexion con base de datos: " + idConexion);
			}
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error [Exception] definido como: ", "close_conection()", e);
		}
	}
	
	/**
	 * Definicion: Este metodo cierra la conexion antes establecida al servidor de base de datos.
	 */
	public void close_conection(ResultSet resultset) {
		try {
			String idConexion = this.connection + "";
			if (this.connection != null) {
				if(resultset != null) {
					resultset.close();
				}
				
				//String cadena_desconexion = "Intentando cerrar conexion con base de datos: " + idConexion + ", this.connection.isClosed()?: " + this.connection.isClosed();
				
				if(this.statement != null) {
					if (!this.statement.isClosed()) {
						//this.statement.closeOnCompletion();
						//cadena_desconexion += ", statement.isClosed()?: " + statement.isClosed();
						this.statement = null;
					}
				}
				
				//FR_Util.trace(cadena_desconexion);
			}
			
			DataSourceUtils.releaseConnection(this.connection, this.datasource);
			
			if (this.connection != null && this.connection.isClosed()) {
				//FR_Util.trace("Se ha cerrado la conexion con base de datos: " + idConexion);
				this.connection = null;
			}
			
			if (this.connection != null && this.connection.isClosed()==false) {
				FR_Util.trace("No se ha logrado cerrar la conexion con base de datos: " + idConexion);
			}
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error [Exception] definido como: ", "close_conection()", e);
		}
	}

	/**
	 * Definicion: Este metodo devuelve true si se trata de una operacion tipo transaccion (DELETE, DROP, INSERT, UPDATE, etc.) y logra ejecutar la consulta SQL que recibe. Si no se trata de una transaccion o no logra ejecutar la consulta SQL devuelve false.
	 * 
	 * @param query
	 * @return
	 */
	public boolean ejecutaTransaccion_SQL(String query) {
		boolean exito = false;
		try {
			long startTime = System.currentTimeMillis();

			if (isActivar_mensajes_en_consola() == true) {
				FR_Util.trace("En ejecutaTransaccion_SQL(String query): Consulta enviada:\n[" + query + "]");
			}

			this.connection = getConnection();

			int num_filas_afectadas = 0;
			if (this.connection != null) {
				statement = this.connection.createStatement();
				//statement.closeOnCompletion();
				num_filas_afectadas = statement.executeUpdate(query);
			}

			if (num_filas_afectadas > 0) {
				exito = true;
			}

			long elapsedTime = System.currentTimeMillis() - startTime;

			if (isActivar_mensajes_en_consola() == true) {
				FR_Util.trace("Registros afectados: " + num_filas_afectadas);
				FR_Util.trace(getCadenaTiempoTranscurrido("ejecutaTransaccion_SQL[Query]", elapsedTime));
			}
		}
		catch (SQLException e) {
			String mensaje_error = "La instruccion SQL no es valida.\n";
			FR_Util.traceTipoDebug(mensaje_error + "Se ha producido un error [SQLException] definido como: ", "ConexionEstandar_FRSS.ejecutaTransaccion_SQL", e);
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error [Exception]", "ConexionEstandar_FRSS", e);
		}
		finally {
			close_conection();
		}
		return exito;
	}

	/**
	 * Definicion: Este metodo devuelve true si se trata de una operacion tipo transaccion (DELETE, DROP, INSERT, UPDATE, etc.) y logra ejecutar el PreparedStatement que recibe. Si no se trata de una transaccion o no logra ejecutar el PreparedStatement devuelve false.
	 * 
	 * @param pstm
	 * @return
	 */
	public boolean ejecutaTransaccion_SQL(PreparedStatement pstm) {
		boolean exito = false;

		try {
			long startTime = System.currentTimeMillis();
			this.connection = getConnection();
			int num_filas_afectadas = 0;
			if (this.connection != null) {
				num_filas_afectadas = pstm.executeUpdate();
				close_conection();
			}

			if (num_filas_afectadas > 0) {
				exito = true;
			}

			long elapsedTime = System.currentTimeMillis() - startTime;

			if (isActivar_mensajes_en_consola() == true) {
				FR_Util.trace("Registros afectados: " + num_filas_afectadas);
				FR_Util.trace(getCadenaTiempoTranscurrido("ejecutaTransaccion_SQL[PreparedStatement]",elapsedTime));
			}
		}
		catch (SQLException e) {
			FR_Util.traceTipoDebug("Se ha producido un error [SQLException]", "ConexionEstandar_FRSS", e);
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error [Exception]", "ConexionEstandar_FRSS", e);
		}

		return exito;
	}

	/**
	 * 
	 * Este metodo ejecuta un lote de consultas SQL con una sola conexion con la Base de datos. Devuelve true si las consultas fueron ejecutada correctamente (no incluye verificacion de sintaxis) Devuelve false si el metodo no pudo ejecutarse (problemas de conexion, etc.).
	 * 
	 * @param queries
	 * @return boolean
	 */

	public boolean ejecutaArrayListTransacciones_SQL(ArrayList<String> queries) {
		boolean exito = false;
		int num_sentencias_ejecutadas = 0;
		int num_filas_afectadas = 0;
		if (activar_mensajes_en_consola == true) {
			FR_Util.trace("--------En Metodo: ejecutaArrayListTransacciones_SQL(ArrayList<String> queries) --------");
			FR_Util.trace("Bloque de consultas de tamanho: " + queries.size());
		}

		long startTime = System.currentTimeMillis();
		this.connection = getConnection();

		if (this.connection != null) {
			String query_a_ejecutar = "";
			try {
				statement = this.connection.createStatement();
				//statement.closeOnCompletion();
			}
			catch (SQLException e) {
				FR_Util.traceTipoDebug("Se ha producido un error [Exception]", "ConexionEstandar_FRSS", e);
			}

			for (int i = 0; i < queries.size(); i++) {
				query_a_ejecutar = queries.get(i);
				try {
					num_filas_afectadas += statement.executeUpdate(query_a_ejecutar);
					num_sentencias_ejecutadas++;
				}
				catch (SQLException e) {
					FR_Util.trace("Error durante la ejecucion de:\n" + query_a_ejecutar);
					FR_Util.traceTipoDebug("Se ha producido un error [Exception]", "ConexionEstandar_FRSS", e);
				}
			}

			if (num_filas_afectadas >= 0) {
				exito = true;
			}

			close_conection();
		}

		long elapsedTime = System.currentTimeMillis() - startTime;

		if (activar_mensajes_en_consola == true) {
			FR_Util.trace("Bloque terminado.\nRegistros afectados: " + num_filas_afectadas + " en " + num_sentencias_ejecutadas + " sentencias ejecutadas de " + queries.size() + " totales.");
			FR_Util.trace("------------------------------------------------------------");
			FR_Util.trace(getCadenaTiempoTranscurrido("ejecutaArrayListTransacciones_SQL([ArrayList<String> queries", elapsedTime));
		}

		return exito;
	}

	/**
	 * Definicion: Este metodo devuelve un conjunto de datos ResultSet si se trata de una operacion tipo seleccion (SELECT) y logra ejecutar la sentencia SQL que recibe. De lo contrario devuelve null (no hay datos).
	 */
	public ResultSet getResultSet(String query) {
		if (isActivar_mensajes_en_consola() == true) {
			FR_Util.trace("En getResultSet(query). Consulta enviada:\n[" + query + "]\n");
		}

		//query = CharcodeTableConversionANSI.reemplazarCaracterPorANSIMySQL(query);
		
		long startTime = System.currentTimeMillis();
		try {
			this.connection = getConnection();
			// FR_Util.trace("conexion: " + this.connection );
			statement = this.connection.createStatement();
			//statement.closeOnCompletion();
			/*
			 * FR_Util.trace("st: " + st);
			 * 
			 * El Statement st tiene un metodo execute() que devuelve un boolean: - Si es true entonces se trata de una operacion que muestra datos - Si es false se trata de una operacion es de actualizacion
			 */

			// Caso especial con Sybase ASE no admite el metodo st.execute
			/*
			 * if (datos_conexion.getRDMS().equals("Sybase ASE")) { rs = st.executeQuery(query); } else
			 */
			/*
			 * if (st.execute(query) == true) { rs = st.executeQuery(query); } else { FR_Util.trace("La instruccion SQL no es una seleccion."); }
			 */
			if (statement != null) {
				this.resultset = statement.executeQuery(query);
				System.out.println("Num Resultados: " + this.resultset.getFetchSize());
			}
			else {
				throw new NullPointerException();
			}
		}
		catch (NullPointerException e) {
			FR_Util.traceTipoDebug("Se ha producido un error [NullPointerException] definido como: ", "ConexionEstandar_FRSS.getResultSet(...)", e);
		}
		catch (SQLException e) {
			String mensaje_error = "La instruccion SQL no es valida.\n";
			mensaje_error += "[" + query + "]";
			FR_Util.traceTipoDebug(mensaje_error + "Se ha producido un error [SQLException] definido como: ", "ConexionEstandar_FRSS.getResultSet(...)", e);
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error [Exception] definido como: ", "ConexionEstandar_FRSS.getResultSet(...)", e);
		}
		finally {
			long elapsedTime = System.currentTimeMillis() - startTime;

			if (ConexionEstandar_FRSS.activar_mensajes_en_consola == true) {
				FR_Util.trace(getCadenaTiempoTranscurrido("getResultSet[Query]", elapsedTime));
			}
		}
		return this.resultset;
	}

	/**
	 * Definicion: Este metodo devuelve un valor especifico de una consulta en formato String
	 */

	public String getValor(String query) {
		String valor = "";
		if (activar_mensajes_en_consola == true) {
			FR_Util.trace("\nConsulta enviada:\n" + query + "\n");
		}

		try {
			this.resultset = getResultSet(query);

			if (this.resultset.next()) {
				if (this.resultset.getString(1) != null) {
					valor = this.resultset.getString(1);
				}
				else {
					valor = "";
				}
				// mensaje_operacion = "comandos completados con exito";
				// datos_conexion.setMensaje(mensaje_operacion);

				if (activar_mensajes_en_consola == true) {
					// FR_Util.trace(mensaje_operacion);
				}
			}
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error. getValor", ubicacionError, e);
			valor = "";
		}
		finally {
			close_conection();
		}
		return valor;
	}

	/**
	 * Definicion: Este metodo devuelve un HasMap de Strings que contiene los valores devueltos por una consulta en una columna especifica para el id y una columna especifica para el valor Nota: Tener en cuenta que los indices a buscar no podran ser nulos
	 */
	public HashMap<String, String> getHashMap_Valores_Columnas_id_valor(String query, int indice_ids, int indice_valores) {
		HashMap<String, String> hasmap_valores = new HashMap<String, String>();

		try {
			this.resultset = getResultSet(query);

			while (this.resultset.next()) {
				if (this.resultset.getString(indice_ids) != null && this.resultset.getString(indice_valores) != null) {
					hasmap_valores.put(this.resultset.getString(indice_ids), this.resultset.getString(indice_valores));
					FR_Util.trace(this.resultset.getString(indice_ids) + "-" + this.resultset.getString(indice_valores));

				}
			}
		}
		catch (SQLException e) {
			FR_Util.traceTipoDebug("Se ha producido un error [SQLException]", "ConexionEstandar_FRSS", e);
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error [Exception]", "ConexionEstandar_FRSS", e);
		}
		finally {
			close_conection();
		}
		return hasmap_valores;
	}

	/**
	 * Definicion: Este metodo imprime en consola el resultado de una operacion tipo seleccion.
	 */
	public void muestraConsulta(ResultSet rs) {
		ArrayList<String> array_cabeceras = new ArrayList<String>();
		ArrayList<String[]> array_filas = new ArrayList<String[]>();
		int colCount = 0;
		// String salida = "";
		int ancho_mayor_cabeceras = 0;
		// int[] ancho_mayor_columnas = null;
		try {
			ResultSetMetaData meta = rs.getMetaData();
			colCount = meta.getColumnCount();

			// Poniendo nombres de columnas
			for (int h = 1; h <= colCount; h++) {
				array_cabeceras.add(meta.getColumnName(h));
				if (meta.getColumnName(h).length() > ancho_mayor_cabeceras) {
					ancho_mayor_cabeceras += meta.getColumnName(h).length();
				}
			}
			// Llena las filas
			while (rs.next()) {
				String[] fila = new String[colCount];
				// ancho_mayor_columnas = new int[colCount];

				for (int i = 0; i < colCount; i++) {
					fila[i] = rs.getString(i + 1);
				}
				array_filas.add(fila);
			}

			// Imprime los resultados

			// salida += "+";
			String cabeceras = "";
			for (int i = 0; i < array_cabeceras.size(); i++) {
				if (array_cabeceras.get(i).length() > 0) {
					cabeceras += array_cabeceras.get(i) + "\t";
				}
			}

			if (activar_mensajes_en_consola == true) {
				FR_Util.trace(cabeceras);
			}

			for (int i = 0; i < array_filas.size(); i++) {
				String fila = "";
				String[] fila_actual = array_filas.get(i);
				for (int j = 0; j < fila_actual.length; j++) {
					fila += fila_actual[j] + "\t";
				}

				if (activar_mensajes_en_consola == true) {
					FR_Util.trace(fila);
				}
			}
		}
		catch (SQLException e) {
			FR_Util.traceTipoDebug("Se ha producido un error: " + e, ubicacionError, e);
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error: " + e, ubicacionError, e);
		}
		finally {
			close_conection();
		}
	}

	/**
	 * Definicion: Este metodo devuelve un ArrayList de Strings que contiene los valores devueltos por una consulta en una columna especifica Nota: Tener en cuenta que el indice a buscar debe ser mayor o igual que 1 y menor o igual que el numero de columnas arrojadas por la consulta;
	 */

	public ArrayList<String> getArrayList_Valores_Columna(String query, int indice_columna) {
		ArrayList<String> arraylist = new ArrayList<String>();

		int num_columnas = 0;
		try {
			this.resultset = getResultSet(query);

			if (this.resultset != null) {
				ResultSetMetaData meta = this.resultset.getMetaData();
				num_columnas = meta.getColumnCount();

				while (this.resultset.next()) {
					if (this.resultset.getString(indice_columna) == null) {
						arraylist.add("");
					}
					else {
						arraylist.add(this.resultset.getString(indice_columna));
					}

					// FR_Util.trace(rs.getString(indice_columna));
				}
			}
		}
		catch (SQLException e) {
			FR_Util.traceTipoDebug("Se ha producido un error [SQLException]", "ConexionEstandar_FRSS", e);
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error [Exception]", "ConexionEstandar_FRSS", e);
		}
		finally {
			FR_Util.trace("num_columnas: " + num_columnas);
			close_conection();
		}

		return arraylist;
	}

	public ArrayList<String[]> getArrayList(String query) {
		// FR_Util.trace(query);

		ArrayList<String[]> arraylist = new ArrayList<String[]>();
		this.resultset = getResultSet(query);

		try {
			ResultSetMetaData meta = this.resultset.getMetaData();
			int colCount = meta.getColumnCount();

			// Llena las filas
			while (this.resultset.next()) {
				String[] fila = new String[colCount];

				for (int i = 0; i < colCount; i++) {
					try {
						fila[i] = this.resultset.getString(i + 1);
					}
					catch (Exception e) {
						FR_Util.traceTipoDebug("Se ha producido un error [Exception]", "ConexionEstandar_Fthis.resultsetS", e);
						fila[i] = "";
					}
				}
				arraylist.add(fila);
			}
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error definido como: ", "ConexionEstandar_FRSS.getArrayList(String query)", new Exception());
		}
		finally {
			close_conection();
		}

		return arraylist;
	}

	public ArrayList<String> getArrayList_Nombres_Cabeceras(String query) {
		ArrayList<String> arraylist = new ArrayList<String>();
		this.resultset = getResultSet(query);
		try {
			ResultSetMetaData meta = this.resultset.getMetaData();
			int colCount = meta.getColumnCount();

			for (int h = 1; h <= colCount; h++) {
				arraylist.add(meta.getColumnLabel(h));
			}
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Se ha producido un error definido como: ", "ConexionEstandar_FRSS.getArrayList_Nombres_Cabeceras(String query)", new Exception());
		}
		finally {
			close_conection();
		}

		return arraylist;
	}

	// Para Flex
	public ArrayList<Object[]> getArrayList_DataGrid_Flex(String query) {
		ArrayList<Object[]> arraylist_final = new ArrayList<Object[]>();
		// FR_Util.trace(">>>>>>>>>query: " + query);
		String[] cabeceras = null;

		this.resultset = getResultSet(query);
		try {
			ResultSetMetaData meta = this.resultset.getMetaData();
			int colCount = meta.getColumnCount();

			cabeceras = new String[colCount];
			for (int h = 1; h <= colCount; h++) {
				cabeceras[h - 1] = meta.getColumnLabel(h);
				// FR_Util.trace(meta.getColumnLabel(h));
			}

			// Agregamos primero las cabeceras
			if (cabeceras != null) {
				arraylist_final.add(cabeceras);
			}

			/*
			 * FR_Util.trace("java.sql.Types.BIT: " + java.sql.Types.BIT); FR_Util.trace("java.sql.Types.TINYINT: " + java.sql.Types.TINYINT); FR_Util.trace("java.sql.Types.SMALLINT: " + java.sql.Types.SMALLINT); FR_Util.trace("java.sql.Types.INTEGER: " + java.sql.Types.INTEGER);
			 * FR_Util.trace("java.sql.Types.BIGINT: " + java.sql.Types.BIGINT); FR_Util.trace("java.sql.Types.FLOAT: " + java.sql.Types.FLOAT); FR_Util.trace("java.sql.Types.REAL: " + java.sql.Types.REAL); FR_Util.trace("java.sql.Types.DOUBLE: " + java.sql.Types.DOUBLE);
			 * FR_Util.trace("java.sql.Types.NUMERIC: " + java.sql.Types.NUMERIC); FR_Util.trace("java.sql.Types.DECIMAL: " + java.sql.Types.DECIMAL); FR_Util.trace("java.sql.Types.CHAR: " + java.sql.Types.CHAR); FR_Util.trace("java.sql.Types.VARCHAR: " + java.sql.Types.VARCHAR);
			 * FR_Util.trace("java.sql.Types.LONGVARCHAR: " + java.sql.Types.LONGVARCHAR); FR_Util.trace("java.sql.Types.DATE: " + java.sql.Types.DATE); FR_Util.trace("java.sql.Types.TIME: " + java.sql.Types.TIME); FR_Util.trace("java.sql.Types.TIMESTAMP: " + java.sql.Types.TIMESTAMP);
			 * FR_Util.trace("java.sql.Types.BINARY: " + java.sql.Types.BINARY); FR_Util.trace("java.sql.Types.VARBINARY: " + java.sql.Types.VARBINARY); FR_Util.trace("java.sql.Types.LONGVARBINARY: " + java.sql.Types.LONGVARBINARY); FR_Util.trace("java.sql.Types.NULL: " + java.sql.Types.NULL);
			 * FR_Util.trace("java.sql.Types.OTHER: " + java.sql.Types.OTHER); FR_Util.trace("java.sql.Types.JAVA_OBJECT: " + java.sql.Types.JAVA_OBJECT); FR_Util.trace("java.sql.Types.ARRAY: " + java.sql.Types.ARRAY); FR_Util.trace("java.sql.Types.BLOB: " + java.sql.Types.BLOB);
			 * FR_Util.trace("java.sql.Types.BOOLEAN: " + java.sql.Types.BOOLEAN); FR_Util.trace("java.sql.Types.NVARCHAR: " + java.sql.Types.NVARCHAR); FR_Util.trace("java.sql.Types.LONGNVARCHAR: " + java.sql.Types.LONGNVARCHAR); FR_Util.trace("java.sql.Types.SQLXML: " + java.sql.Types.SQLXML);
			 */

			// Llena las filas
			while (this.resultset.next()) {
				Object[] record = new Object[colCount];

				for (int i = 0; i < colCount; i++) {
					try {
						if (meta.getColumnType(i + 1) == java.sql.Types.BIT) {
							try {
								record[i] = this.resultset.getBoolean(i + 1);
							}
							catch (Exception e) {
								record[i] = false;
								FR_Util.traceTipoDebug("Tipo BIT. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.TINYINT) {
							try {
								record[i] = this.resultset.getInt(i + 1);
							}
							catch (Exception e) {
								record[i] = 0;
								FR_Util.traceTipoDebug("Tipo TINYINT. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.SMALLINT) {
							try {
								record[i] = this.resultset.getInt(i + 1);
							}
							catch (Exception e) {
								record[i] = 0;
								FR_Util.traceTipoDebug("Tipo SMALLINT. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.INTEGER) {
							try {
								record[i] = this.resultset.getInt(i + 1);
							}
							catch (Exception e) {
								record[i] = 0;
								FR_Util.traceTipoDebug("Tipo INTEGER. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.BIGINT) {
							try {
								record[i] = this.resultset.getInt(i + 1);
							}
							catch (Exception e) {
								record[i] = 0;
								FR_Util.traceTipoDebug("Tipo BIGINT. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.FLOAT) {
							try {
								record[i] = this.resultset.getFloat(i + 1);
							}
							catch (Exception e) {
								record[i] = 0;
								FR_Util.traceTipoDebug("Tipo FLOAT. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.REAL) {
							try {
								record[i] = this.resultset.getDouble(i + 1);
							}
							catch (Exception e) {
								record[i] = 0.0;
								FR_Util.traceTipoDebug("Tipo REAL. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.DOUBLE) {
							try {
								record[i] = this.resultset.getDouble(i + 1);
							}
							catch (Exception e) {
								record[i] = 0.0;
								FR_Util.traceTipoDebug("Tipo DOUBLE. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.NUMERIC) {
							try {
								record[i] = this.resultset.getDouble(i + 1);
							}
							catch (Exception e) {
								record[i] = 0.0;
								FR_Util.traceTipoDebug("Tipo NUMERIC. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.DECIMAL) {
							try {
								record[i] = this.resultset.getDouble(i + 1);
							}
							catch (Exception e) {
								record[i] = 0.0;
								FR_Util.traceTipoDebug("Tipo DECIMAL. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.CHAR) {
							try {
								record[i] = this.resultset.getString(i + 1);
							}
							catch (Exception e) {
								record[i] = "";
								FR_Util.traceTipoDebug("Tipo CHAR. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.VARCHAR) {
							try {
								record[i] = this.resultset.getString(i + 1);
							}
							catch (Exception e) {
								record[i] = "";
								FR_Util.traceTipoDebug("Tipo VARCHAR. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.LONGVARCHAR) {
							try {
								record[i] = this.resultset.getString(i + 1);
							}
							catch (Exception e) {
								record[i] = "";
								FR_Util.traceTipoDebug("Tipo LONGVARCHAR. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.DATE) {
							try {
								// FR_Util.trace("Tipo: " +
								// meta.getColumnType(i+1));
								Date fecha = this.resultset.getDate(i + 1);
								GregorianCalendar calendar = new GregorianCalendar();
								calendar.setTime(fecha);
								calendar.setTimeZone(TimeZone.getTimeZone("GMT-5"));
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								record[i] = sdf.format(calendar.getTime());
							}
							catch (Exception e) {
								record[i] = null;
								// FR_Util.traceTipoDebug("Tipo DATE. Error: " +
								// e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.TIME) {
							try {
								record[i] = this.resultset.getTime(i + 1);
							}
							catch (Exception e) {
								record[i] = null;
								FR_Util.traceTipoDebug("Tipo TIME. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.TIMESTAMP) {
							try {

								// FR_Util.trace("Tipo: " +
								// meta.getColumnType(i+1));
								Timestamp timestamp = this.resultset.getTimestamp(i + 1);

								GregorianCalendar calendar = new GregorianCalendar();
								calendar.setTimeInMillis(timestamp.getTime());
								calendar.setTimeZone(TimeZone.getTimeZone("GMT-5"));
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
								sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
								record[i] = sdf.format(calendar.getTime());

								// record[i] = this.resultset.getTimestamp(i + 1);
							}
							catch (SQLException e) {
								record[i] = null;
								// FR_Util.traceTipoDebug("Tipo TIMESTAMP. Error: "
								// + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
							catch (Exception e) {
								record[i] = null;
								// FR_Util.traceTipoDebug("Tipo TIMESTAMP. Error: "
								// + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.BINARY) {
							try {
								record[i] = this.resultset.getBytes(i + 1);
							}
							catch (Exception e) {
								record[i] = null;
								FR_Util.traceTipoDebug("Tipo BINARY. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.VARBINARY) {
							try {
								record[i] = this.resultset.getBytes(i + 1);
							}
							catch (Exception e) {
								record[i] = null;
								FR_Util.traceTipoDebug("Tipo VARBINARY. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.LONGVARBINARY) {
							try {
								record[i] = this.resultset.getBytes(i + 1);
							}
							catch (Exception e) {
								record[i] = null;
								FR_Util.traceTipoDebug("Tipo LONGVARBINARY. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.NULL) {
							record[i] = null;
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.OTHER) {
							record[i] = this.resultset.getObject(i + 1);
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.JAVA_OBJECT) {
							record[i] = this.resultset.getObject(i + 1);
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.ARRAY) {
							try {
								record[i] = this.resultset.getArray(i + 1);
							}
							catch (Exception e) {
								record[i] = null;
								FR_Util.traceTipoDebug("Tipo ARRAY. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.BLOB) {
							try {
								record[i] = this.resultset.getBytes(i + 1);
							}
							catch (Exception e) {
								record[i] = null;
								FR_Util.traceTipoDebug("Tipo BLOB. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.BOOLEAN) {
							try {
								record[i] = this.resultset.getBoolean(i + 1);
							}
							catch (Exception e) {
								record[i] = false;
								FR_Util.traceTipoDebug("Tipo BOOLEAN. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.NVARCHAR) {
							try {
								record[i] = this.resultset.getString(i + 1);
							}
							catch (Exception e) {
								record[i] = "";
								FR_Util.traceTipoDebug("Tipo NVARCHAR. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.LONGNVARCHAR) {
							try {
								record[i] = this.resultset.getString(i + 1);
							}
							catch (Exception e) {
								record[i] = "";
								FR_Util.traceTipoDebug("Tipo LONGNVARCHAR. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else if (meta.getColumnType(i + 1) == java.sql.Types.SQLXML) {
							try {
								record[i] = this.resultset.getSQLXML(i + 1);
							}
							catch (Exception e) {
								record[i] = null;
								FR_Util.traceTipoDebug("Tipo SQLXML. Error: " + e, "ConexionEstandar_Fthis.resultsetS", e);
							}
						}
						else {
							FR_Util.trace("No se ha podido definir el tipo de columna SQL ->->: " + meta.getColumnTypeName(i + 1));
							record[i] = this.resultset.getObject(i + 1);
						}
					}
					catch (Exception e) {
						record[i] = "+";
					}
				}
				arraylist_final.add(record);
			}
		}
		catch (NullPointerException e) {
			FR_Util.traceTipoDebug("Se ha producido un error [NullPointerException]", "ConexionEstandar_FRSS", e);
			String cadena_error = ": " + e + ".";
			cabeceras = new String[1];
			cabeceras[0] = "La consulta enviada ha generado un error";
			arraylist_final.add(cabeceras);

			Object[] record = new Object[1];
			record[0] = cadena_error;
			arraylist_final.add(record);
		}
		catch (SQLException e) {
			FR_Util.traceTipoDebug("Se ha producido un error [SQLException]", "ConexionEstandar_FRSS", e);
		}
		finally {
			close_conection();
		}

		return arraylist_final;
	}

	public ArrayList<Object[]> getArrayList_DataGrid_Flex(ArrayList<LinkedHashMap<Object, Object>> arraylist_linkedhashmap) {
		ArrayList<Object[]> arraylist_final = new ArrayList<Object[]>();

		ArrayList<String> cabeceras = new ArrayList<String>();

		for (int i = 0; i < arraylist_linkedhashmap.size(); i++) {
			LinkedHashMap<Object, Object> fila_linkedhashmap = arraylist_linkedhashmap.get(i);
			ArrayList<Object> contenido_fila = new ArrayList<Object>();

			Iterator<Object> iterator = fila_linkedhashmap.keySet().iterator();
			while (iterator.hasNext()) {
				String key_nombre_columna = iterator.next().toString();
				// Para la primera fila agregamos los nombres de las cabeceras
				// FR_Util.trace(fila_linkedhashmap.get(key_nombre_columna));
				if (i == 0) {
					cabeceras.add(key_nombre_columna);
				}

				Object item = fila_linkedhashmap.get(key_nombre_columna);

				if (item instanceof java.sql.Date) {
					try {
						// FR_Util.trace("Tipo: " + meta.getColumnType(i+1));
						Date fecha = (java.sql.Date) item;
						GregorianCalendar calendar = new GregorianCalendar();
						calendar.setTime(fecha);
						calendar.setTimeZone(TimeZone.getTimeZone("GMT-5"));
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						contenido_fila.add(sdf.format(calendar.getTime()));
					}
					catch (Exception e) {
						contenido_fila.add(null);
						// FR_Util.traceTipoDebug("Tipo DATE. Error: " + e,
						// "ConexionEstandar_FRSS", e);
					}
				}
				else if (item instanceof java.sql.Time) {
					try {
						java.sql.Time item_time = (java.sql.Time) item;
						contenido_fila.add(item_time);
					}
					catch (Exception e) {
						contenido_fila.add(null);
						FR_Util.traceTipoDebug("Tipo TIME. Error: " + e, "ConexionEstandar_FRSS", e);
					}
				}
				else if (item instanceof java.sql.Timestamp) {
					try {
						// FR_Util.trace("Tipo: " + meta.getColumnType(i+1));
						java.sql.Timestamp timestamp = (java.sql.Timestamp) item;

						GregorianCalendar calendar = new GregorianCalendar();
						calendar.setTimeInMillis(timestamp.getTime());
						calendar.setTimeZone(TimeZone.getTimeZone("GMT-5"));
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
						sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
						contenido_fila.add(sdf.format(calendar.getTime()));

						// record[i] = rs.getTimestamp(i + 1);
					}
					catch (Exception e) {
						contenido_fila.add(null);
						// FR_Util.traceTipoDebug("Tipo TIMESTAMP. Error: " + e, // "ConexionEstandar_FRSS", e);
					}
				}
				else {
					contenido_fila.add(item);
				}
			}

			// Agregamos primero las cabeceras
			if (i == 0 && cabeceras != null) {
				// String[] nombres_columnas = (String[]);
				arraylist_final.add(cabeceras.toArray());
			}

			arraylist_final.add(contenido_fila.toArray());

			close_conection();
		}

		return arraylist_final;
	}

	public String getCadenaPreparedStatementInsert(Object obj, String nombre_tabla) {
		String cadena_final = "";
		try {
			String cadena_insert_prepare_statement00 = "INSERT INTO " + nombre_tabla + " (";
			String cadena_insert_prepare_statement01 = "";
			String cadena_insert_prepare_statement02 = ") VALUES (";
			String cadena_insert_prepare_statement03 = ")";

			ArrayList<String[]> arraylist_campos = FR_Util.getCamposBean(obj);
			if (arraylist_campos != null) {
				for (int i = 0; i < arraylist_campos.size(); i++) {
					String[] campo = arraylist_campos.get(i);
					// FR_Util.trace(campo[0] + " - " + campo[1]);
					cadena_insert_prepare_statement01 += campo[0];
					cadena_insert_prepare_statement02 += "?";
					if (i != arraylist_campos.size() - 1) {
						cadena_insert_prepare_statement01 += ", ";
						cadena_insert_prepare_statement02 += ", ";
					}
				}
			}

			cadena_final += cadena_insert_prepare_statement00;
			cadena_final += cadena_insert_prepare_statement01;
			cadena_final += cadena_insert_prepare_statement02;
			cadena_final += cadena_insert_prepare_statement03;
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return cadena_final;
	}

	public String getCadenaPreparedStatementUpdate(Object obj, String nombre_tabla) {
		String cadena_final = "";
		try {
			String cadena_insert_prepare_statement00 = "UPDATE " + nombre_tabla + " SET ";
			String cadena_insert_prepare_statement01 = "";

			ArrayList<String[]> arraylist_campos = FR_Util.getCamposBean(obj);
			if (arraylist_campos != null) {
				for (int i = 0; i < arraylist_campos.size(); i++) {
					String[] campo = arraylist_campos.get(i);
					// FR_Util.trace(campo[0] + " - " + campo[1]);
					cadena_insert_prepare_statement01 += campo[0] + " = ?";
					if (i != arraylist_campos.size() - 1) {
						cadena_insert_prepare_statement01 += ", ";
					}
				}
			}

			cadena_final += cadena_insert_prepare_statement00;
			cadena_final += cadena_insert_prepare_statement01;
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return cadena_final;
	}
}