package pe.com.ibm.csm.app.db.dao;

import pe.com.ibm.csm.app.db.beans.Bean_Solicitud;

public interface Solicitud_DAO {

	public abstract Bean_Solicitud getBean_Solicitud(String idSol);

	public abstract Bean_Solicitud getBean_Solicitud_segun_Query(String query);

	public abstract boolean existeBean_Solicitud(String idSol);

	public abstract boolean insertarBean_Solicitud(Bean_Solicitud bean_solicitud);

	public abstract boolean actualizarBean_Solicitud(Bean_Solicitud bean_solicitud);

	public abstract boolean eliminarBean_Solicitud(Bean_Solicitud bean_solicitud);

	public abstract boolean eliminarBean_SolicitudSegunID(String idSol);

	public abstract String getSQLQuery_insertarBean_Solicitud(Bean_Solicitud bean_solicitud);

	public abstract String getSQLQuery_actualizarBean_Solicitud(Bean_Solicitud bean_solicitud);

	public abstract String getSQLQuery_eliminarBean_Solicitud(Bean_Solicitud bean_solicitud);

	public abstract String getIdSegunDatos_Bean_Solicitud(Bean_Solicitud bean_solicitud);
}