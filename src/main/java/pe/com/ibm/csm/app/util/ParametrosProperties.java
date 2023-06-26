package pe.com.ibm.csm.app.util;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ParametrosProperties implements Serializable {
	private static final long	serialVersionUID					= 1L;
	private final static Logger			LOGGER				= LogManager.getLogger();

	public final String			IDT3Codigo							= "-3";
	public final String			IDT3Mensaje							= "Error de Componente";

	//@Value("${ruta_almacenamiento_local_imagenes}")
	//public String				ruta_almacenamiento_local_imagenes	= null;

	

	public ParametrosProperties() {
		LOGGER.info("Inicializando lectura de variables desde Properties");
	}

}
