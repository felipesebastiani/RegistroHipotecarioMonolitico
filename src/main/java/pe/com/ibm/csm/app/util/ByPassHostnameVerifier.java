package pe.com.ibm.csm.app.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class ByPassHostnameVerifier implements HostnameVerifier {

	@Override
	public boolean verify(String hostname, SSLSession session) {
		return true;
	}

}
