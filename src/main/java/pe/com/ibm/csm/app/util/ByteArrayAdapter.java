package pe.com.ibm.csm.app.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ByteArrayAdapter extends XmlAdapter<byte[], byte[]> {
	private final static Logger			LOGGER				= LogManager.getLogger();
	
	private boolean	audit;

	public ByteArrayAdapter(boolean audit) {
		this.audit = audit;
	}

	@Override
	public byte[] marshal(byte[] bytes) throws Exception {
		byte[] retorno = null;
		LOGGER.info("marshalling: identificando tipo de contenido bytes");
		LOGGER.info("this.audit: " + this.audit + " - bytes: " + bytes);
		
		if (bytes != null) {
			if (this.audit) {
				retorno = new String("Data Binaria").getBytes();
			}
			else {
				retorno = bytes;
			}
		}
		else {
			retorno = bytes;
		}
		return retorno;
	}

	@Override
	public byte[] unmarshal(byte[] bytes) throws Exception {
		return bytes;
	}
}
