package pe.com.ibm.csm.app.managers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frss.util.FR_Numeros;
import frss.util.FR_Util;
import pe.com.ibm.csm.app.beans.Auditoria;
import pe.com.ibm.csm.app.beans.Cliente;
import pe.com.ibm.csm.app.beans.CreditoHipotecario;
import pe.com.ibm.csm.app.beans.registrarsolicitud.RegistrarSolicitudRequest;
import pe.com.ibm.csm.app.beans.registrarsolicitud.RegistrarSolicitudResponse;
import pe.com.ibm.csm.app.db.beans.Bean_Cliente;
import pe.com.ibm.csm.app.db.beans.Bean_Creditohipotecario;
import pe.com.ibm.csm.app.db.beans.Bean_Solicitud;
import pe.com.ibm.csm.app.db.dao.mysql.MySQL_Cliente_DAO;
import pe.com.ibm.csm.app.db.dao.mysql.MySQL_Creditohipotecario_DAO;
import pe.com.ibm.csm.app.db.dao.mysql.MySQL_Solicitud_DAO;
import pe.com.ibm.csm.app.managers.RegistrarSolicitudManager;
import pe.com.ibm.csm.app.util.Util;
import pe.com.ibm.csm.app.util.spring.SpringFactory;

@Service
public class RegistrarSolicitudManagerImpl extends BaseManager implements RegistrarSolicitudManager {
	private final static Logger				LOGGER	= LogManager.getLogger();

	@Autowired
	private MySQL_Cliente_DAO				mySQL_Cliente_DAO;

	@Autowired
	private MySQL_Creditohipotecario_DAO	mySQL_CreditoHipotecario_DAO;

	@Autowired
	private MySQL_Solicitud_DAO				mySQL_Solicitud_DAO;

	@Override
	public RegistrarSolicitudResponse registrarSolicitud(RegistrarSolicitudRequest request) throws Exception {
		RegistrarSolicitudResponse registrarSolicitudResponse = new RegistrarSolicitudResponse();
		Auditoria auditoria = new Auditoria();
		registrarSolicitudResponse.setAuditoria(auditoria);
		registrarSolicitudResponse.setIdSol(request.getIdSol());

		try {
			this.mySQL_Cliente_DAO.setDataSource(SpringFactory.getDatasource());
			this.mySQL_CreditoHipotecario_DAO.setDataSource(SpringFactory.getDatasource());
			this.mySQL_Solicitud_DAO.setDataSource(SpringFactory.getDatasource());
			
			Cliente cliente = request.getCliente();
			CreditoHipotecario creditoHipotecario = request.getCreditoHipotecario();
			
			FR_Util.trace("cliente: " + cliente);
			FR_Util.trace("creditoHipotecario: " + creditoHipotecario);
			
			Bean_Cliente bean_cliente = new Bean_Cliente();
			bean_cliente.setIdCli(cliente.getIdCli());
			bean_cliente.setNombres(cliente.getNombresCli());
			bean_cliente.setApellidoPat(cliente.getApellidoPatCli());
			bean_cliente.setApellidoMat(cliente.getApellidoMatCli());
			bean_cliente.setTipo(cliente.getTipoCli());
			bean_cliente.setEmail(cliente.getCorreoCli());
			bean_cliente.setTelefono(cliente.getTelefonoCli());
			bean_cliente.setGenero(cliente.getGeneroCli());
			
			FR_Util.trace("this.mySQL_Cliente_DAO: " + this.mySQL_Cliente_DAO);
			Bean_Cliente bean_cliente_busqueda = this.mySQL_Cliente_DAO.getBean_Cliente(request.getCliente().getIdCli());
			boolean exito_operacion_cliente = false;
			
			if (bean_cliente_busqueda == null) {
				exito_operacion_cliente = this.mySQL_Cliente_DAO.insertarBean_Cliente(bean_cliente);
				LOGGER.info("El cliente no existe se registro");
			}
			else {
				exito_operacion_cliente = this.mySQL_Cliente_DAO.actualizarBean_Cliente(bean_cliente);
				LOGGER.info("El cliente existe se actualizo");
			}
			
			if (exito_operacion_cliente) {
				
				Bean_Creditohipotecario bean_creditohipotecario = new Bean_Creditohipotecario();
				bean_creditohipotecario.setIdCre(request.getIdSol());
				bean_creditohipotecario.setIdCli(cliente.getIdCli());
				bean_creditohipotecario.setTipoCre(creditoHipotecario.getTipoCre());
				bean_creditohipotecario.setMontoInicial(FR_Numeros.leeDouble(creditoHipotecario.getMontoInicialCre()));
				bean_creditohipotecario.setMontoFinanciar(FR_Numeros.leeDouble(creditoHipotecario.getMontoFinanciarCre()));
				bean_creditohipotecario.setPlazoMeses(FR_Numeros.leeInteger(creditoHipotecario.getPlazoMesesCre()));
				bean_creditohipotecario.setSueldoBruto(FR_Numeros.leeDouble(creditoHipotecario.getSueldoBrutoCre()));
				bean_creditohipotecario.setCostoInmueble(FR_Numeros.leeDouble(creditoHipotecario.getCostoInmuebleCre()));
				bean_creditohipotecario.setTasa(FR_Numeros.leeDouble(creditoHipotecario.getTasaCre()));
				
				boolean exito_insert_credito_hipotecario = this.mySQL_CreditoHipotecario_DAO.insertarBean_Creditohipotecario(bean_creditohipotecario);

				if (exito_insert_credito_hipotecario) {
					Bean_Solicitud bean_solicitud = new Bean_Solicitud();
					bean_solicitud.setIdSol(request.getIdSol());
					bean_solicitud.setIdCre(creditoHipotecario.getIdCre());
					bean_solicitud.setResultado(null);
					
					boolean existe_solicitud = this.mySQL_Solicitud_DAO.existeBean_Solicitud(bean_solicitud.getIdSol());
					boolean exito_operacion_solicitud;
					if (existe_solicitud) {
						exito_operacion_solicitud = this.mySQL_Solicitud_DAO.actualizarBean_Solicitud(bean_solicitud);
					}
					else {
						exito_operacion_solicitud = this.mySQL_Solicitud_DAO.insertarBean_Solicitud(bean_solicitud);
					}
					
					if(exito_operacion_solicitud) {
						auditoria.setCodigo("0");
						auditoria.setDetalle("Solicitud registrada correctamente: " + bean_solicitud.getIdSol());
					}
					else {
						auditoria.setCodigo("-1");
						auditoria.setDetalle("No se pudo registrar los datos de la solicitud");
					}
				}
				else {
					auditoria.setCodigo("-2");
					auditoria.setDetalle("No se pudo registrar los datos del credito hipotecario solicitado");
					
				}
			}
			else {
				auditoria.setCodigo("-3");
				auditoria.setDetalle("No se pudo registrar los datos del cliente");
				LOGGER.error(registrarSolicitudResponse.getAuditoria().getDetalle());
			}
		}
		catch (Exception e) {
			auditoria.setCodigo("-4");
			auditoria.setDetalle("No se pudo registrar la solicitud");
			
			final String texto_error = "Detalle del error:" + Util.SALTO_LINEA + Util.getStackTraceFromException(e);
			LOGGER.error(texto_error);
		}

		return registrarSolicitudResponse;
	}
}
