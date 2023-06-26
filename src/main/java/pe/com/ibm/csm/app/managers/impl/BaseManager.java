package pe.com.ibm.csm.app.managers.impl;

import java.io.File;
import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import frss.util.SerializationUtil;
import pe.com.ibm.csm.app.util.ByPassHostnameVerifier;
import pe.com.ibm.csm.app.util.NullX509TrustManager;

public class BaseManager {
	
	public void disableCertificateValidation() {
		try {
			SSLContext sslc = SSLContext.getInstance("TLS");
			TrustManager[] trustManagerArray = { (TrustManager) new NullX509TrustManager() };
			sslc.init(null, trustManagerArray, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new ByPassHostnameVerifier());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String generaArchivoCertificado(String encodedBase64) {
		String rutaArchivoTemporal = null;
		String ruta_tmp = System.getProperty("java.io.tmpdir");
		System.out.println("ruta_tmp: " + ruta_tmp);

		try {
			String prefix = "clientTruststore";
			String suffix = ".p12";
			File directory = new File(ruta_tmp);

			File tempfile = File.createTempFile(prefix, suffix, directory);
			tempfile.deleteOnExit();

			System.out.println("Temp file : " + tempfile.getAbsolutePath());

			SerializationUtil.base64StringTofile(encodedBase64, tempfile.getAbsolutePath());

			rutaArchivoTemporal = tempfile.getAbsolutePath();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return rutaArchivoTemporal;
	}
	
	public void deleteTempJKSFile(String ruta_jks) {
		try {
			File f = new File(ruta_jks);
			f.delete();
		}
		catch (Exception e) {
		}
		
	}
	
	public void enableJKSProperties(String ruta_jks, String p12Pass) {
		System.setProperty("javax.net.ssl.keyStore", ruta_jks);
		System.setProperty("javax.net.ssl.keyStorePassword", p12Pass);
		System.setProperty("javax.net.ssl.trustStore", ruta_jks);
		System.setProperty("javax.net.ssl.trustStorePassword", p12Pass);

		System.setProperty("java.net.preferIPv4Stack", "true");

		System.setProperty("java.protocol.handler.pkgs", "javax.net.ssl");
		//Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		// System.setProperty("javax.net.debug", "ssl:verbose");
	}
}
