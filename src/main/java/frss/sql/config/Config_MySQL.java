package frss.sql.config;

public class Config_MySQL {
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
	
	public Config_MySQL() {
		this.RDMS = "MySQL Server";
		this.cadena_driver = "com.mysql.jdbc.Driver";
		this.bd_predeterminada_sistema = "mysql";
		this.puerto_acceso = "3306";
		this.prefijo_conexion = "jdbc:mysql://";
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
		this.cadena_conexion = this.prefijo_conexion + this.bd_servidor + ":" + this.puerto_acceso + "/" + this.bd_nombre + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
		
    	return this.cadena_conexion;
    }
	
	public void setCadena_conexion(String cadenaConexion) {
    	this.cadena_conexion = cadenaConexion;
    }
	
	public String getBd_usuario() {
    	return this.bd_usuario;
    }
	
	public void setBd_usuario(String bdUsuario) {
    	this.bd_usuario = bdUsuario;
    }
	
	public String getBd_password() {
    	return this.bd_password;
    }
	
	public void setBd_password(String bdPassword) {
    	this.bd_password = bdPassword;
    }
	
	public String getConsulta_listar_bases_datos() {
		this.consulta_listar_bases_datos = "SHOW DATABASES";
    	return this.consulta_listar_bases_datos;
    }
	
	public void setConsulta_listar_bases_datos(String consultaListarBasesDatos) {
    	this.consulta_listar_bases_datos = consultaListarBasesDatos;
    }
	
	public String getConsulta_listar_tablas() {
		this.consulta_listar_tablas = "SHOW TABLES";
    	return this.consulta_listar_tablas;
    }
	
	public void setConsulta_listar_tablas(String consultaListarTablas) {
    	this.consulta_listar_tablas = consultaListarTablas;
    }
	
	public String getConsulta_describe_tabla(String nombre_tabla, String nombre_usuario_propietario) {
		this.consulta_describe_tabla = "DESCRIBE " + nombre_tabla;
    	return this.consulta_describe_tabla;
    }
	
	public void setConsulta_describe_tabla(String consultaDescribeTabla) {
    	this.consulta_describe_tabla = consultaDescribeTabla;
    }
}