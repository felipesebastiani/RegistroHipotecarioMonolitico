package frss.sql.config;

import frss.util.FR_Util;

public class Config_Oracle {
	protected	String	RDMS						= null;
	protected	String	cadena_driver				= null;
	protected	String	bd_predeterminada_sistema	= null;
	protected	String	bd_nombre					= null;
	protected	String	bd_servidor					= null;
	protected	String	puerto_acceso				= null;
	protected	String	prefijo_conexion			= null;
	protected	String	cadena_conexion				= null;
	protected	String	bd_usuario					= null;
	protected	String	bd_password					= null;
	protected	String	consulta_listar_bases_datos	= null;
	protected 	String	consulta_listar_tablas		= null;
	protected 	String	consulta_describe_tabla		= null;
	
	public Config_Oracle() {
		this.RDMS = "Oracle";
		this.cadena_driver = "oracle.jdbc.driver.OracleDriver";
		this.bd_predeterminada_sistema = "orcl";
		this.puerto_acceso = "1521";
		this.prefijo_conexion = "jdbc:oracle:thin:@";
	}
	
	public String getRDMS() {
    	return this.RDMS;
    }
	
	public void setRDMS(String rDMS) {
    	this.RDMS = rDMS;
    }
	
	public String getCadena_driver() {
    	return this.cadena_driver;
    }
	
	public void setCadena_driver(String cadenaDriver) {
    	this.cadena_driver = cadenaDriver;
    }
	
	public String getBd_predeterminada_sistema() {
    	return this.bd_predeterminada_sistema;
    }
	
	public void setBd_predeterminada_sistema(String bdPredeterminadaSistema) {
    	this.bd_predeterminada_sistema = bdPredeterminadaSistema;
    }
	
	public String getBd_nombre() {
    	return this.bd_nombre;
    }
	
	public void setBd_nombre(String bdNombre) {
    	this.bd_nombre = bdNombre;
    }
	
	public String getBd_servidor() {
    	return this.bd_servidor;
    }
	
	public void setBd_servidor(String bdServidor) {
    	this.bd_servidor = bdServidor;
    }
	
	public String getPuerto_acceso() {
    	return this.puerto_acceso;
    }
	
	public void setPuerto_acceso(String puertoAcceso) {
    	this.puerto_acceso = puertoAcceso;
    }
	
	public String getPrefijo_conexion() {
    	return this.prefijo_conexion;
    }
	
	public void setPrefijo_conexion(String prefijoConexion) {
    	this.prefijo_conexion = prefijoConexion;
    }
	
	public String getCadena_conexion() {
		this.cadena_conexion = this.prefijo_conexion + this.bd_servidor + ":" + this.puerto_acceso + ":" + this.bd_nombre + "";
		FR_Util.trace(">> " + this.cadena_conexion);
    	return this.cadena_conexion;
    }
	
	public void setCadena_conexion(String cadenaConexion) {
    	this.cadena_conexion = cadenaConexion;
    }
	
	public String getBd_usuario() {
		FR_Util.trace(">> " + this.bd_usuario);
		return this.bd_usuario;
    }
	
	public void setBd_usuario(String bdUsuario) {
    	this.bd_usuario = bdUsuario;
    }
	
	public String getBd_password() {
		FR_Util.trace(">> " + this.bd_password);
    	return this.bd_password;
    }
	
	public void setBd_password(String bdPassword) {
    	this.bd_password = bdPassword;
    }
	
	public String getConsulta_listar_bases_datos() {
		this.consulta_listar_bases_datos = "sp_databases";
    	return this.consulta_listar_bases_datos;
    }
	
	public void setConsulta_listar_bases_datos(String consultaListarBasesDatos) {
    	this.consulta_listar_bases_datos = consultaListarBasesDatos;
    }
	
	public String getConsulta_listar_tablas() {
		this.consulta_listar_tablas = "";
    	return this.consulta_listar_tablas;
    }
	
	public void setConsulta_listar_tablas(String consultaListarTablas) {
    	this.consulta_listar_tablas = consultaListarTablas;
    }
	
	public String getConsulta_describe_tabla(String nombre_tabla, String nombre_usuario_propietario) {
		String query_subconsulta_keys = "SELECT CONSTRAINT_TYPE FROM ALL_CONS_COLUMNS A JOIN ALL_CONSTRAINTS C ON A.CONSTRAINT_NAME = C.CONSTRAINT_NAME WHERE C.TABLE_NAME = '" + nombre_tabla + "'  AND (C.CONSTRAINT_TYPE = 'P' OR C.CONSTRAINT_TYPE = 'R') AND C.INDEX_NAME IS NOT NULL AND C.OWNER = A.OWNER AND C.OWNER = '" + nombre_usuario_propietario + "' AND UTC.COLUMN_NAME = A.COLUMN_NAME";
		this.consulta_describe_tabla = "SELECT UTC.COLUMN_NAME AS Field, UTC.DATA_TYPE AS \"Type\", UTC.DATA_LENGTH AS \"Length\", UTC.NULLABLE AS \"Null\", (" + query_subconsulta_keys  +") AS \"Key\", UTC.DATA_DEFAULT AS \"Default\"  FROM USER_TAB_COLUMNS UTC WHERE TABLE_NAME = '" + nombre_tabla + "' ORDER BY UTC.COLUMN_ID";

    	return this.consulta_describe_tabla;
    }
	
	public void setConsulta_describe_tabla(String consultaDescribeTabla) {
    	this.consulta_describe_tabla = consultaDescribeTabla;
    }	
}