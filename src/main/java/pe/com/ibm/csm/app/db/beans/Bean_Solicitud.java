package pe.com.ibm.csm.app.db.beans;
import java.io.Serializable;

public class Bean_Solicitud implements Serializable {
	private	static	final	long	serialVersionUID = 1L;
	private	String	idSol				= null;
	private	String	idCre				= null;
	private	String	resultado		= null;

	public String getIdSol() {
		return this.idSol;
	}

	public void setIdSol(String idSol) {
		this.idSol = idSol;
	}

	public String getIdCre() {
		return this.idCre;
	}

	public void setIdCre(String idCre) {
		this.idCre = idCre;
	}

	public String getResultado() {
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}