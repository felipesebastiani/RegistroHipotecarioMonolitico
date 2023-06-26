package pe.com.ibm.csm.app.impl.rest;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import frss.util.FR_Cadenas;
import pe.com.ibm.csm.app.beans.consultarsolicitud.ConsultarSolicitudRequest;
import pe.com.ibm.csm.app.beans.consultarsolicitud.ConsultarSolicitudResponse;
import pe.com.ibm.csm.app.beans.registrarsolicitud.RegistrarSolicitudRequest;
import pe.com.ibm.csm.app.beans.registrarsolicitud.RegistrarSolicitudResponse;
import pe.com.ibm.csm.app.managers.ConsultarSolicitudManager;
import pe.com.ibm.csm.app.managers.RegistrarSolicitudManager;
import pe.com.ibm.csm.app.util.Util;

@RestController
//@RequestMapping("/rest")
public class RestService {
	private final static Logger			LOGGER						= LogManager.getLogger();
	private final String				REST_VERSION				= "REST";

	@Autowired
	private RegistrarSolicitudManager	registrarSolicitudManager	= null;

	@Autowired
	private ConsultarSolicitudManager	consultarSolicitudManager	= null;

	public RestService() {
		try {
			File parent_log_dir = new File(".").getParentFile();
			File log_dir = null;

			if (parent_log_dir != null) {
				log_dir = new File(parent_log_dir.getAbsolutePath() + File.separator + "logs");
			}
			else {
				log_dir = new File("logs");
			}

			System.out.println("log_dir [" + log_dir.getAbsolutePath() + "]");
			//Archivo.crearArbolDirectorios(log_dir.getAbsolutePath());
		}
		catch (Exception e) {
		}

	}

	@CrossOrigin
	@RequestMapping(value = "/registrarSolicitud", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody RegistrarSolicitudResponse registrarSolicitud(@RequestBody RegistrarSolicitudRequest request) {
		RegistrarSolicitudResponse response = null;
		final String NOMBRE_METODO = "[" + REST_VERSION + "][RestService.registrarSolicitud]";
		String mensaje = "";
		try {
			LOGGER.info("Request: " + NOMBRE_METODO + FR_Cadenas.SALTO_LINEA + Util.toJSon(request));

			response = registrarSolicitudManager.registrarSolicitud(request);

		}
		catch (Exception e) {
			final String texto_error = mensaje + " Error de componente. Detalle del error:" + Util.SALTO_LINEA + Util.getStackTraceFromException(e);
			LOGGER.error(texto_error);
		}
		finally {
			if (response == null) {
				response = new RegistrarSolicitudResponse();
				response.getAuditoria().setCodigo("-3");
				response.getAuditoria().setDetalle("Error de inicializacion de componente");
			}

			LOGGER.info("Response: " + FR_Cadenas.SALTO_LINEA + Util.toJSon(response));
		}
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = "/consultarSolicitud", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ConsultarSolicitudResponse consultarSolicitud(@RequestBody ConsultarSolicitudRequest request) {
		ConsultarSolicitudResponse response = null;
		final String NOMBRE_METODO = "[" + REST_VERSION + "][RestService.consultarSolicitud]";
		String mensaje = "";
		try {
			LOGGER.info("Request: " + NOMBRE_METODO + FR_Cadenas.SALTO_LINEA + Util.toJSon(request));

			response = consultarSolicitudManager.consultarSolicitud(request);

		}
		catch (Exception e) {
			final String texto_error = mensaje + " Error de componente. Detalle del error:" + Util.SALTO_LINEA + Util.getStackTraceFromException(e);
			LOGGER.error(texto_error);
		}
		finally {
			if (response == null) {
				response = new ConsultarSolicitudResponse();
				response.getAuditoria().setCodigo("-3");
				response.getAuditoria().setDetalle("Error de inicializacion de componente");
			}

			LOGGER.info("Response: " + FR_Cadenas.SALTO_LINEA + Util.toJSon(response));
		}
		return response;
	}
}
