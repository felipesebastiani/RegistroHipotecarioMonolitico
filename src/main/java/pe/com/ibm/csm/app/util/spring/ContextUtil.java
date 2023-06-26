package pe.com.ibm.csm.app.util.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class ContextUtil {
	@Autowired
    private ApplicationContext applicationContext;

    public String getInstancedBeans() {
    	String rpta = "\nListando Spring instanced Beans";
        String[] beanNames = applicationContext.getBeanDefinitionNames();

        for (String beanName : beanNames) {

        	rpta += beanName + " : " + applicationContext.getBean(beanName).getClass().toString() + "\n";
        }

        return rpta;
    }
}
