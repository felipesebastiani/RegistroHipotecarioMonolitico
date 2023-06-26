package pe.com.ibm.csm.app.beans;

public class ResultadoSolicitud {
	private String				idSol				= null;
	private String				detalleEvaluacion	= null;
	private Cliente				cliente				= null;

	private CreditoHipotecario	creditoHipotecario	= null;

	public String getIdSol() {
		return idSol;
	}

	public void setIdSol(String idSol) {
		this.idSol = idSol;
	}

	public String getDetalleEvaluacion() {
		return detalleEvaluacion;
	}

	public void setDetalleEvaluacion(String detalleEvaluacion) {
		this.detalleEvaluacion = detalleEvaluacion;
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
