
package pe.com.ibm.csm.app.beans.types.base;

public class AuditRequest {

    protected String idTransaccion;
    protected String ipAplicacion;
    protected String nombreAplicacion;
    protected String usuarioAplicacion;
    protected String idEmpresa;

    /**
     * Obtiene el valor de la propiedad idTransaccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * Define el valor de la propiedad idTransaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTransaccion(String value) {
        this.idTransaccion = value;
    }

    /**
     * Obtiene el valor de la propiedad ipAplicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpAplicacion() {
        return ipAplicacion;
    }

    /**
     * Define el valor de la propiedad ipAplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpAplicacion(String value) {
        this.ipAplicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreAplicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    /**
     * Define el valor de la propiedad nombreAplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreAplicacion(String value) {
        this.nombreAplicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioAplicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioAplicacion() {
        return usuarioAplicacion;
    }

    /**
     * Define el valor de la propiedad usuarioAplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioAplicacion(String value) {
        this.usuarioAplicacion = value;
    }

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
}
