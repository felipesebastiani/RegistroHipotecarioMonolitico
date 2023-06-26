package frss.sql.config;

public class Config_Sybase {
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
	
	public Config_Sybase() {
		this.RDMS = "Sybase ASE";
		this.cadena_driver = "com.sybase.jdbc3.jdbc.SybDriver";
		this.bd_predeterminada_sistema = "sybsystemprocs";
		this.puerto_acceso = "5000";
		this.prefijo_conexion = "jdbc:sybase:Tds:";
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
		this.cadena_conexion = this.prefijo_conexion + this.bd_servidor + ":" + this.puerto_acceso + "/" + this.bd_nombre + "?useUnicode=true&characterEncoding=UTF-8";
		
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
		this.consulta_listar_bases_datos = "SELECT name FROM master.dbo.sysdatabases";
    	return this.consulta_listar_bases_datos;
    }
	
	public void setConsulta_listar_bases_datos(String consultaListarBasesDatos) {
    	this.consulta_listar_bases_datos = consultaListarBasesDatos;
    }
	
	public String getConsulta_listar_tablas() {
		this.consulta_listar_tablas = "SELECT * FROM sysobjects WHERE TYPE LIKE 'U' OR TYPE LIKE 'u'";
    	return this.consulta_listar_tablas;
    }
	
	public void setConsulta_listar_tablas(String consultaListarTablas) {
    	this.consulta_listar_tablas = consultaListarTablas;
    }
	
	public String getConsulta_describe_tabla(String nombre_tabla, String nombre_usuario_propietario) {
		this.consulta_describe_tabla = "SELECT ";
		this.consulta_describe_tabla += "     DISTINCT cols.colid AS 'ID', cols.NAME AS 'Field', tipos.NAME  AS 'Type', cols.LENGTH AS 'Lentgh', cols.prec AS 'Precision', cols.SCALE AS 'Scale', ";
		this.consulta_describe_tabla += "    ";
		
		//Consulta para saber si el campo es nulo
		this.consulta_describe_tabla += "    (SELECT CASE WHEN  ";
		this.consulta_describe_tabla += "        (SELECT CASE WHEN (SELECT i.NAME FROM sysindexes i, sysobjects o WHERE i.id = o.id AND o.TYPE = 'U' AND i.indid = 1 AND o.NAME = '" + nombre_tabla  + "' AND i.NAME = cols.NAME) IS NULL THEN '' ELSE 'PRI' END) = 'PRI'  OR ";
		this.consulta_describe_tabla += "        cols.NAME IN (SELECT DISTINCT i.NAME FROM sysobjects o, sysindexes i,  syscolumns cols WHERE o.TYPE = 'U' AND i.indid = cols.id AND o.NAME = '" + nombre_tabla  + "') AND cols.NAME NOT IN (SELECT i.NAME FROM sysindexes i, sysobjects o WHERE i.id = o.id AND o.TYPE = 'U' AND i.indid = 1 AND o.NAME = '" + nombre_tabla  + "' AND i.NAME = cols.NAME) ";
		this.consulta_describe_tabla += "    THEN  ";
		this.consulta_describe_tabla += "        'NO' ";
		this.consulta_describe_tabla += "    ELSE  ";
		this.consulta_describe_tabla += "        (SELECT CASE WHEN tipos.allownulls = 1 AND cols.id = object_id('" + nombre_tabla  + "') THEN 'YES' ELSE 'NO' END) ";
		this.consulta_describe_tabla += "    END) AS 'Null', ";

		//Consulta para extraer la llave, si es primaria PRI, si es foranea FK
		this.consulta_describe_tabla += "    (SELECT CASE WHEN  ";
		this.consulta_describe_tabla += "        (SELECT CASE WHEN (SELECT i.NAME FROM sysindexes i, sysobjects o WHERE i.id = o.id AND o.TYPE = 'U' AND i.indid = 1 AND o.NAME = '" + nombre_tabla  + "' AND i.NAME = cols.NAME) IS NULL THEN '' ELSE 'PRI' END) = 'PRI' OR ";
		this.consulta_describe_tabla += "        cols.NAME IN (SELECT DISTINCT i.NAME FROM sysobjects o, sysindexes i,  syscolumns cols WHERE o.TYPE = 'U' AND i.indid = cols.id AND o.NAME = '" + nombre_tabla  + "') AND cols.NAME NOT IN (SELECT i.NAME FROM sysindexes i, sysobjects o WHERE i.id = o.id AND o.TYPE = 'U' AND i.indid = 1 AND o.NAME = '" + nombre_tabla  + "' AND i.NAME = cols.NAME) ";
		this.consulta_describe_tabla += "    THEN  ";
		this.consulta_describe_tabla += "        (SELECT CASE WHEN  ";
		this.consulta_describe_tabla += "            cols.NAME IN (SELECT  cols.NAME FROM sysconstraints c, dbo.sysobjects o1, dbo.sysreferences r, dbo.sysobjects o2, syscolumns cols WHERE c.tableid  =  object_id('" + nombre_tabla  + "') AND ";
		this.consulta_describe_tabla += "                            c.status   =  64 AND c.constrid =  o1.id AND o1.TYPE = 'RI' AND c.constrid =  r.constrid AND r.reftabid =  o2.id AND cols.id = o2.id) ";
		this.consulta_describe_tabla += "         THEN  ";
		this.consulta_describe_tabla += "            'FK' ";
		this.consulta_describe_tabla += "         ELSE ";
		this.consulta_describe_tabla += "            'PRI' ";
		this.consulta_describe_tabla += "         END) ";
		this.consulta_describe_tabla += "    ELSE  ";
		this.consulta_describe_tabla += "        '' ";
		this.consulta_describe_tabla += "    END) AS 'Key', ";


		//Consulta para extraer informacion si es auto_increment/identity
		this.consulta_describe_tabla += "(SELECT CASE WHEN ";
		this.consulta_describe_tabla += "        CONVERT(BIT, (cols.status & 0x80)) = 1 ";
		this.consulta_describe_tabla += "    THEN ";
		this.consulta_describe_tabla += "        'indentity/auto_increment' ";
		this.consulta_describe_tabla += "    ELSE         ";
		this.consulta_describe_tabla += "        '' ";
		this.consulta_describe_tabla += "    END) AS 'Extra', ";

		//Consulta para extraer el valor por defecto de una columna
		this.consulta_describe_tabla += " cols.cdefault AS 'Default' ";

		//Consulta para extraer el valor real por defecto de una columna (se comenta porque Sybase 12.5 no permitia mas que 16 subconsultas, se deja para versiones posteriores)
		/*
		this.consulta_describe_tabla += "(SELECT DISTINCT cols.NAME, STR_REPLACE(STR_REPLACE(text,'DEFAULT  ',''),'''','') FROM syscomments sycom, syscolumns cols, systypes tipos " + "\n";
		this.consulta_describe_tabla += "WHERE sycom.id = cols.cdefault AND cols.id = object_id('" + nombre_tabla  + "') AND tipos.TYPE = cols.TYPE) AS 'DEFAULT'  " + "\n";
		*/
		
		this.consulta_describe_tabla += "FROM  ";
		this.consulta_describe_tabla += "    syscolumns cols,systypes tipos, syscomments sycom ";
		this.consulta_describe_tabla += "WHERE  ";
		this.consulta_describe_tabla += "    tipos.TYPE = cols.TYPE AND tipos.NAME NOT IN ('timestamp', 'sysname', 'nchar', 'nvarchar') ";
		this.consulta_describe_tabla += "    AND cols.id = (SELECT so.id FROM sysobjects so WHERE so.NAME = '" + nombre_tabla  + "') ";
		this.consulta_describe_tabla += "ORDER BY cols.colid   ";
    	return this.consulta_describe_tabla;
    }
	
	public void setConsulta_describe_tabla(String consultaDescribeTabla) {
    	this.consulta_describe_tabla = consultaDescribeTabla;
    }

}