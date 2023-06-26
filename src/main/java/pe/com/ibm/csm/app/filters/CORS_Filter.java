package pe.com.ibm.csm.app.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frss.util.FR_Util;

import java.io.IOException;

@WebFilter("/*")
public class CORS_Filter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		FR_Util.trace("Filtro CORS_Filter inicializado");
	}

    @Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			response.addHeader("Access-Control-Max-Age", "7200");
	        response.addHeader("Access-Control-Allow-Credentials", "true");
	        /*
		// Just ACCEPT and REPLY OK if OPTIONS
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}*/
	        
        if (request.getMethod().equals("OPTIONS")) {
        	response.setStatus(HttpServletResponse.SC_ACCEPTED);
        	FR_Util.trace("SC_ACCEPTED");
			return;
		}
        
		chain.doFilter(request, servletResponse);
	}

	@Override
	public void destroy() {
	}
}