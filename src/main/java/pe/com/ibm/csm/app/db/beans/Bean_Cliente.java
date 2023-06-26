package pe.com.ibm.csm.app.db.beans;
import java.io.Serializable;

public class Bean_Cliente implements Serializable {
	private	static	final	long	serialVersionUID = 1L;
	private	String	idCli				= null;
	private	String	nombres		= null;
	private	String	apellidoPat		= null;
	private	String	apellidoMat		= null;
	private	String	tipo					= null;
	private	String	email				= null;
	private	String	telefono				= null;
	private	String	genero				= null;

	public String getIdCli() {
		return this.idCli;
	}

	public void setIdCli(String idCli) {
		this.idCli = idCli;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPat() {
		return this.apellidoPat;
	}

	public void setApellidoPat(String apellidoPat) {
		this.apellidoPat = apellidoPat;
	}

	public String getApellidoMat() {
		return this.apellidoMat;
	}

	public void setApellidoMat(String apellidoMat) {
		this.apellidoMat = apellidoMat;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}