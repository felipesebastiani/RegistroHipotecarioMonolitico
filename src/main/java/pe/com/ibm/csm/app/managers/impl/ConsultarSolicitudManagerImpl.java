package pe.com.ibm.csm.app.managers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frss.util.FR_Cadenas;
import pe.com.ibm.csm.app.beans.Auditoria;
import pe.com.ibm.csm.app.beans.Cliente;
import pe.com.ibm.csm.app.beans.CreditoHipotecario;
import pe.com.ibm.csm.app.beans.ResultadoSolicitud;
import pe.com.ibm.csm.app.beans.consultarsolicitud.ConsultarSolicitudRequest;
import pe.com.ibm.csm.app.beans.consultarsolicitud.ConsultarSolicitudResponse;
import pe.com.ibm.csm.app.db.beans.Bean_Cliente;
import pe.com.ibm.csm.app.db.beans.Bean_Creditohipotecario;
import pe.com.ibm.csm.app.db.beans.Bean_Solicitud;
import pe.com.ibm.csm.app.db.dao.mysql.MySQL_Cliente_DAO;
import pe.com.ibm.csm.app.db.dao.mysql.MySQL_Creditohipotecario_DAO;
import pe.com.ibm.csm.app.db.dao.mysql.MySQL_Solicitud_DAO;
import pe.com.ibm.csm.app.managers.ConsultarSolicitudManager;
import pe.com.ibm.csm.app.util.Util;
import pe.com.ibm.csm.app.util.spring.SpringFactory;

@Service
public class ConsultarSolicitudManagerImpl  extends BaseManager implements ConsultarSolicitudManager {
	private final static Logger				LOGGER	= LogManager.getLogger();

	@Autowired
	private MySQL_Cliente_DAO				mySQL_Cliente_DAO;

	@Autowired
	private MySQL_Creditohipotecario_DAO	mySQL_CreditoHipotecario_DAO;

	@Autowired
	private MySQL_Solicitud_DAO				mySQL_Solicitud_DAO;
	
    @Override
	public ConsultarSolicitudResponse consultarSolicitud(ConsultarSolicitudRequest request) {
    	ConsultarSolicitudResponse consultarSolicitudResponse = new ConsultarSolicitudResponse();
    	Auditoria auditoria = new Auditoria();
    	consultarSolicitudResponse.setAuditoria(auditoria);
    	ResultadoSolicitud	resultadoSolicitud = new ResultadoSolicitud();
    	
		try {
			this.mySQL_Cliente_DAO.setDataSource(SpringFactory.getDatasource());
			this.mySQL_CreditoHipotecario_DAO.setDataSource(SpringFactory.getDatasource());
			this.mySQL_Solicitud_DAO.setDataSource(SpringFactory.getDatasource());
			
			boolean existe_solicitud = this.mySQL_Solicitud_DAO.existeBean_Solicitud(request.getIdSol());
			if(existe_solicitud) {
				Bean_Solicitud bean_solicitud = this.mySQL_Solicitud_DAO.getBean_Solicitud(request.getIdSol());
				Bean_Creditohipotecario bean_Creditohipotecario = this.mySQL_CreditoHipotecario_DAO.getBean_Creditohipotecario(bean_solicitud.getIdCre());
				Bean_Cliente bean_cliente = this.mySQL_Cliente_DAO.getBean_Cliente(bean_Creditohipotecario.getIdCli());
				
				CreditoHipotecario creditoHipotecario = new CreditoHipotecario();
				creditoHipotecario.setIdCre(bean_Creditohipotecario.getIdCre());
				creditoHipotecario.setIdCli(bean_Creditohipotecario.getIdCli());
				creditoHipotecario.setTipoCre(bean_Creditohipotecario.getTipoCre());
				creditoHipotecario.setMontoInicialCre(bean_Creditohipotecario.getMontoInicial() + FR_Cadenas.CADENA_VACIA);
				creditoHipotecario.setMontoFinanciarCre(bean_Creditohipotecario.getMontoFinanciar() + FR_Cadenas.CADENA_VACIA);
				creditoHipotecario.setPlazoMesesCre(bean_Creditohipotecario.getPlazoMeses() + FR_Cadenas.CADENA_VACIA);
				creditoHipotecario.setSueldoBrutoCre(bean_Creditohipotecario.getSueldoBruto() + FR_Cadenas.CADENA_VACIA);
				creditoHipotecario.setCostoInmuebleCre(bean_Creditohipotecario.getCostoInmueble() + FR_Cadenas.CADENA_VACIA);
				creditoHipotecario.setTasaCre(bean_Creditohipotecario.getTasa() + FR_Cadenas.CADENA_VACIA);
				
				Cliente cliente = new Cliente();
				cliente.setIdCli(bean_cliente.getIdCli());
				cliente.setNombresCli(bean_cliente.getNombres());
				cliente.setApellidoPatCli(bean_cliente.getApellidoPat());
				cliente.setApellidoMatCli(bean_cliente.getApellidoMat());
				cliente.setTipoCli(bean_cliente.getTipo());
				cliente.setCorreoCli(bean_cliente.getEmail());
				cliente.setTelefonoCli(bean_cliente.getTelefono());
				cliente.setGeneroCli(bean_cliente.getGenero());
				
				resultadoSolicitud.setIdSol(bean_solicitud.getIdSol());
				resultadoSolicitud.setCliente(cliente);
				resultadoSolicitud.setCreditoHipotecario(creditoHipotecario);
				resultadoSolicitud.setDetalleEvaluacion(getDetalleEvaluacionSimulado(bean_solicitud, bean_Creditohipotecario, bean_cliente));
				
				consultarSolicitudResponse.setAuditoria(auditoria);
				consultarSolicitudResponse.setResultadoSolicitud(resultadoSolicitud);
				
				auditoria.setCodigo("0");
				auditoria.setDetalle("Solicitud encontrada");
			}
			else {
				auditoria.setCodigo("-1");
				auditoria.setDetalle("La solicitud no existe en nuestra base de datos");
			}
		}
		catch (Exception e) {
			auditoria.setCodigo("-2");
			auditoria.setDetalle("No se pudo buscar la solicitud");
			
			final String texto_error = "Detalle del error:" + Util.SALTO_LINEA + Util.getStackTraceFromException(e);
			LOGGER.error(texto_error);
		}

        return consultarSolicitudResponse;
    }

	private String getDetalleEvaluacionSimulado(Bean_Solicitud bean_solicitud, Bean_Creditohipotecario bean_Creditohipotecario, Bean_Cliente bean_cliente) {
		String resultado = null;
		try {
			resultado = "El Credito solicitado con codigo [" + bean_solicitud.getIdSol() + "] ha sido aprobado";
		}
		catch (Exception e) {
			final String texto_error = "Detalle del error:" + Util.SALTO_LINEA + Util.getStackTraceFromException(e);
			LOGGER.error(texto_error);
		}
		return resultado;
	}
   
}
