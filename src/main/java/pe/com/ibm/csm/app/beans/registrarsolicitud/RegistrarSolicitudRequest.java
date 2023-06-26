package pe.com.ibm.csm.app.beans.registrarsolicitud;

import java.io.Serializable;

import pe.com.ibm.csm.app.beans.Cliente;
import pe.com.ibm.csm.app.beans.CreditoHipotecario;
import pe.com.ibm.csm.app.beans.Auditoria;

public class RegistrarSolicitudRequest implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private Auditoria			auditoria			= null;
	private String				idSol				= null;
	private Cliente				cliente				= null;
	private CreditoHipotecario	creditoHipotecario	= null;

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	public String getIdSol() {
		return idSol;
	}

	public void setIdSol(String idSol) {
		this.idSol = idSol;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CreditoHipotecario getCreditoHipotecario() {
		return creditoHipotecario;
	}

	public void setCreditoHipotecario(CreditoHipotecario creditoHipotecario) {
		this.creditoHipotecario = creditoHipotecario;
	}

}