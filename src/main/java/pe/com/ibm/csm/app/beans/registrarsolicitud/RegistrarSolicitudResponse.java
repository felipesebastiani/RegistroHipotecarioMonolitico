package pe.com.ibm.csm.app.beans.registrarsolicitud;

import pe.com.ibm.csm.app.beans.Auditoria;

public class RegistrarSolicitudResponse {
	private String		idSol		= null;
	private Auditoria	auditoria	= null;

	public String getIdSol() {
		return idSol;
	}

	public void setIdSol(String idSol) {
		this.idSol = idSol;
	}

	public Auditoria getAuditoria() {
		if (auditoria == null) {
			auditoria = new Auditoria();
		}
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}
}