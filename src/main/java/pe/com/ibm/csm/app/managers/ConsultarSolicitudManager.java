package pe.com.ibm.csm.app.managers;

import pe.com.ibm.csm.app.beans.consultarsolicitud.ConsultarSolicitudRequest;
import pe.com.ibm.csm.app.beans.consultarsolicitud.ConsultarSolicitudResponse;

public interface ConsultarSolicitudManager {
	public ConsultarSolicitudResponse consultarSolicitud(ConsultarSolicitudRequest request) throws Exception;

	
}
