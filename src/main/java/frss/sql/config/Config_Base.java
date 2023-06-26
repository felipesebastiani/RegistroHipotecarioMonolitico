package frss.sql.config;

public abstract class Config_Base {
	
	
	public abstract String getConsulta_tipo_describe(String nombre_tabla, String nombre_usuario_propietario);
	public abstract String getBd_sistema();
    public abstract void setBd_sistema(String bd_sistema);
	public abstract	String getRDMS();
    public abstract void setRDMS(String rDMS);
    public abstract String getCadena_driver();
    public abstract void setCadena_driver(String cadenaDriver);
    public abstract String getBd_predeterminada_sistema();
	public abstract void setBd_predeterminada_sistema(String bdPredeterminadaSistema);
	public abstract String getBd_nombre();
	public abstract void setBd_nombre(String bdNombre);
	public abstract String getBd_servidor();
	public abstract void setBd_servidor(String bdServidor);
	public abstract String getPuerto_acceso();
    public abstract void setPuerto_acceso(String puertoAcceso);
	public abstract String getPrefijo_conexion();
	public abstract void setPrefijo_conexion(String prefijoConexion);
	public abstract String getCadena_conexion();
	public abstract void setCadena_conexion(String cadenaConexion);
	public abstract String getBd_usuario();
	public abstract void setBd_usuario(String bdUsuario);
	public abstract String getBd_password();
	public abstract void setBd_password(String bdPassword);
	public abstract String getConsulta_listar_bases_datos();
    public abstract void setConsulta_listar_bases_datos(String consultaListarBasesDatos);
    public abstract String getConsulta_listar_tablas();
	public abstract void setConsulta_listar_tablas(String consultaListarTablas);
	public abstract String getConsulta_describe_tabla();
	public abstract void setConsulta_describe_tabla(String consultaDescribeTabla);	
}