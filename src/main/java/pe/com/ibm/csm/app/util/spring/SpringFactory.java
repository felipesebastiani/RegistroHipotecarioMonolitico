package pe.com.ibm.csm.app.util.spring;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import pe.com.ibm.csm.app.util.Util;

public class SpringFactory {
	private final static Logger			LOGGER				= LogManager.getLogger();
	protected static ApplicationContext	ctx		= null;

	@Autowired
	private ListableBeanFactory			beanFactory;
	static {
		SpringFactory.ctx = ApplicationContextProvider.getApplicationContext();
	}

	public SpringFactory() {
		super();

		for (String beanName : beanFactory.getBeanDefinitionNames()) {
			LOGGER.info(beanName);
		}
	}

	/**
	 * @author fsebastiani
	 * @return DataSource
	 */
	public static DataSource getDatasource() {
		DataSource ds = null;
		try {
			final String alias = "bd_registro_hipotecario";
			LOGGER.debug("Intentando acceder al datasource [" + alias + "]");

			if (SpringFactory.ctx.getBean(alias) != null) {
				ds = (DataSource) SpringFactory.ctx.getBean(alias);
			}

		}
		catch (Exception e) {
			LOGGER.error("[getDatasource]Se ha detectado un error: " + Util.getStackTraceFromException(e));
		}
		LOGGER.info("getDatasource() retornando: " + ds);
		return ds;
	}
}
