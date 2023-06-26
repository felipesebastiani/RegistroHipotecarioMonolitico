package pe.com.ibm.csm.app.beans.consultarsolicitud;

import java.io.Serializable;

import pe.com.ibm.csm.app.beans.Auditoria;
import pe.com.ibm.csm.app.beans.ResultadoSolicitud;

public class ConsultarSolicitudResponse implements Serializable {
	private static final long	serialVersionUID	= 1L;

	private Auditoria			auditoria			= null;
	private ResultadoSolicitud	resultadoSolicitud	= null;

	public Auditoria getAuditoria() {
		if (auditoria == null) {
			auditoria = new Auditoria();
		}
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	public ResultadoSolicitud getResultadoSolicitud() {
		return resultadoSolicitud;
	}

	public void setResultadoSolicitud(ResultadoSolicitud resultadoSolicitud) {
		this.resultadoSolicitud = resultadoSolicitud;
	}

}