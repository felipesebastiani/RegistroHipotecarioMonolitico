package pe.com.ibm.csm.app.managers;

import pe.com.ibm.csm.app.beans.registrarsolicitud.RegistrarSolicitudRequest;
import pe.com.ibm.csm.app.beans.registrarsolicitud.RegistrarSolicitudResponse;

public interface RegistrarSolicitudManager {
	public RegistrarSolicitudResponse registrarSolicitud(RegistrarSolicitudRequest request) throws Exception;

	
}
