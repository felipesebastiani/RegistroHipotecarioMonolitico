package pe.com.ibm.csm.app.util.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pe.com.ibm.csm.app.util.Util;

public class ApplicationContextProvider implements ApplicationContextAware {
	private final static Logger			LOGGER				= LogManager.getLogger();
	
	private static ApplicationContext	applicationContext	= null;

	public ApplicationContextProvider() {
		super();
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextProvider.applicationContext = applicationContext;
		System.out.println("0 applicationContext: " + applicationContext);
		if(ApplicationContextProvider.applicationContext == null){
			applicationContext = new AnnotationConfigApplicationContext(ApplicationContextProvider.class);
			System.out.println("ApplicationContextProvider: " + applicationContext);
		}
		
		listarClasesEnContexto();
	}

	public static void listarClasesEnContexto() {
		LOGGER.info("listarClasesEnContexto: Iniciando");
		try {
			String[] beanNames = getApplicationContext().getBeanDefinitionNames();
			String lista_clases = "";
			for (String beanName : beanNames) {

				lista_clases += ("[" + beanName + "] : [" + getApplicationContext().getBean(beanName).getClass().toString() + "]" + Util.SALTO_LINEA);
			}
			LOGGER.info("Clases cargadas en contexto: " + lista_clases);
		}
		catch (Exception e) {
			LOGGER.error("Se ha detectado un error: " + Util.getStackTraceFromException(e));
		}
	}
}
