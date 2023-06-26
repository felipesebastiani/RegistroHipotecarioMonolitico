package frss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerializationUtil {

	/**
	 * transformaArchivoToBase64
	 * 
	 * @param filePath
	 * @return String
	 * @throws IOException
	 **/
	// Soportado desde java 1.8
	public static String fileToBase64(String filePath) throws IOException {
		String base64 = "";
		File file = new File(filePath);

		try (FileInputStream fis = new FileInputStream(file)) {
			byte fileData[] = new byte[(int) file.length()];
			fis.read(fileData);
			base64 = java.util.Base64.getEncoder().encodeToString(fileData);
		}
		catch (FileNotFoundException e) {
			throw e;
		}
		catch (IOException ioe) {
			throw ioe;
		}

		return base64;
	}

	/**
	 * transformaBase64ToArchivo
	 * 
	 * @param base64
	 * @param pathFile
	 * @throws IOException
	 **/
	// Soportado desde java 1.8
	public static void base64StringTofile(String base64, String pathFile) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(pathFile)) {
			byte[] fileData = java.util.Base64.getDecoder().decode(base64);
			fos.write(fileData);
		}
		catch (FileNotFoundException e) {
			throw e;
		}
		catch (IOException ioe) {
			throw ioe;
		}
	}
	
	public static void main(String[] args) {
		try {
			String str = fileToBase64("F:/Dropbox/02_Laboral/IBM/capacitaciones/BootCamps/SalesPlay_Integracion_Obs/RegistroHipotecario/certificado/clientTruststore.p12");
			System.out.println(str);
			
			String ruta_tmp = System.getProperty("java.io.tmpdir");
			System.out.println("ruta_tmp: " + ruta_tmp);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
