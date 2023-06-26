package pe.com.ibm.csm.app.db.beans;
import java.io.Serializable;

public class Bean_Creditohipotecario implements Serializable {
	private	static	final	long	serialVersionUID = 1L;
	private	String	idCre					= null;
	private	String	idCli					= null;
	private	String	tipoCre					= null;
	private	double	montoInicial			= -1.0;
	private	double	montoFinanciar		= -1.0;
	private	int		plazoMeses				= -1;
	private	double	sueldoBruto				= -1.0;
	private	double	costoInmueble			= -1.0;
	private	double	tasa					= -1.0;

	public String getIdCre() {
		return this.idCre;
	}

	public void setIdCre(String idCre) {
		this.idCre = idCre;
	}

	public String getIdCli() {
		return this.idCli;
	}

	public void setIdCli(String idCli) {
		this.idCli = idCli;
	}

	public String getTipoCre() {
		return this.tipoCre;
	}

	public void setTipoCre(String tipoCre) {
		this.tipoCre = tipoCre;
	}

	public double getMontoInicial() {
		return this.montoInicial;
	}

	public void setMontoInicial(double montoInicial) {
		this.montoInicial = montoInicial;
	}

	public double getMontoFinanciar() {
		return this.montoFinanciar;
	}

	public void setMontoFinanciar(double montoFinanciar) {
		this.montoFinanciar = montoFinanciar;
	}

	public int getPlazoMeses() {
		return this.plazoMeses;
	}

	public void setPlazoMeses(int plazoMeses) {
		this.plazoMeses = plazoMeses;
	}

	public double getSueldoBruto() {
		return this.sueldoBruto;
	}

	public void setSueldoBruto(double sueldoBruto) {
		this.sueldoBruto = sueldoBruto;
	}

	public double getCostoInmueble() {
		return this.costoInmueble;
	}

	public void setCostoInmueble(double costoInmueble) {
		this.costoInmueble = costoInmueble;
	}

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}
}