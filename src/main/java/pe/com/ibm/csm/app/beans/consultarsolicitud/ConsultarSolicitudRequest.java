package pe.com.ibm.csm.app.beans.consultarsolicitud;

import java.io.Serializable;

public class ConsultarSolicitudRequest implements Serializable {
	private static final long	serialVersionUID	= 1L;

	private String				idSol				= null;

	public String getIdSol() {
		return idSol;
	}

	public void setIdSol(String idSol) {
		this.idSol = idSol;
	}
	
	private String             idCli               = null;
	
	public String getIdCli() {
		return idCli;
	}

	public void setIdCli(String idCli) {
		this.idCli = idCli;
	}
	
}