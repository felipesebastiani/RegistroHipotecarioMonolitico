package pe.com.ibm.csm.app.util;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Node;

/**
 * Utilitario creado para parsear objetos a XML y viceversa utilizando la API y los marshallers de JAXB.
 * 
 */
public class JAXBUtilitarios {

	private final static Logger			LOGGER				= LogManager.getLogger();
	@SuppressWarnings("rawtypes")
	private static HashMap<Class, JAXBContext>	mapContexts	= new HashMap<Class, JAXBContext>();

	@SuppressWarnings("rawtypes")
	private static JAXBContext obtainJaxBContextFromClass(Class clas) {
		JAXBContext context;
		context = mapContexts.get(clas);
		if (context == null) {
			try {
				LOGGER.info("Inicializando jaxcontext... para la clase " + clas.getName());
				context = JAXBContext.newInstance(clas);
				mapContexts.put(clas, context);
			}
			catch (Exception e) {
				LOGGER.error("Error creando JAXBContext: " + Util.getStackTraceFromException(e));
			}
		}
		return context;
	}

	public String getXmlTextFromJaxB(Object objJaxB) {
		String commandoRequestEnXml = null;
		JAXBContext context;
		try {
			//context = obtainJaxBContextFromClass(objJaxB.getClass());
			context = obtainJaxBContextFromClass(com.sun.xml.bind.v2.runtime.JAXBContextImpl.class);
			Marshaller marshaller = context.createMarshaller();
			StringWriter xmlWriter = new StringWriter();
			marshaller.marshal(objJaxB, xmlWriter);
			XmlObject xmlObj = XmlObject.Factory.parse(xmlWriter.toString());
			commandoRequestEnXml = xmlObj.toString();
		}
		catch (Exception e) {
			LOGGER.error("Error parseando object to xml:" + Util.getStackTraceFromException(e));
		}
		return commandoRequestEnXml;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static String anyObjectToXmlText_ANTES(Object objJaxB) {
		Field[] campos = objJaxB.getClass().getDeclaredFields();
		ArrayList<String> nombres_campos_no_imprimir = new ArrayList<String>();
		for (int i = 0; i < campos.length; i++) {
			try {
				if (campos[i] != null && campos[i].toGenericString().indexOf("byte[]") != -1) {
					nombres_campos_no_imprimir.add(campos[i].getName());
					LOGGER.info("El campo [" + campos[i].getName() + "] contiene informacion binaria que se mostrara como referencia");
					campos[i] = null;
				}
			}
			catch (Exception e) {
				LOGGER.error("Error parseando campos:" + Util.getStackTraceFromException(e));
			}
		}

		String commandoRequestEnXml = null;
		if (objJaxB != null) {
			JAXBContext context;
			try {
				context = obtainJaxBContextFromClass(objJaxB.getClass());

				Marshaller marshaller = context.createMarshaller();
				StringWriter xmlWriter = new StringWriter();

				JAXBElement jaxb_element = new JAXBElement(new QName("", objJaxB.getClass().getName()), objJaxB.getClass(), objJaxB);
				marshaller.marshal(jaxb_element, xmlWriter);

				final String str_xml_parser = xmlWriter.toString();
				XmlObject xmlObj = XmlObject.Factory.parse(str_xml_parser);

				commandoRequestEnXml = xmlObj.toString();

			}
			catch (Exception e) {
				LOGGER.error("Error parseando object to xml:" + Util.getStackTraceFromException(e));
			}
		}
		else {
			commandoRequestEnXml = "El objeto es nulo";
		}
		return commandoRequestEnXml;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String anyObjectToXmlText(Object objJaxB) {

		String commandoAMostrarse = null;
		if (objJaxB != null) {
			try {
				Field[] campos = objJaxB.getClass().getDeclaredFields();
				ArrayList<String> nombres_campos_no_imprimir = new ArrayList<String>();
				for (int i = 0; i < campos.length; i++) {
					try {
						if (campos[i] != null && campos[i].toGenericString().indexOf("byte[]") != -1) {
							nombres_campos_no_imprimir.add(campos[i].getName());
							LOGGER.info("El campo [" + campos[i].getName() + "] contiene informacion binaria que se mostrara como referencia");
							campos[i] = null;
						}
					}
					catch (Exception e) {
						LOGGER.error("Error parseando campos:" + Util.getStackTraceFromException(e));
					}
				}
				JAXBContext context = obtainJaxBContextFromClass(objJaxB.getClass());

				Marshaller marshaller = context.createMarshaller();
				StringWriter xmlWriter = new StringWriter();

				JAXBElement jaxb_element = new JAXBElement(new QName("", objJaxB.getClass().getName()), objJaxB.getClass(), objJaxB);
				marshaller.marshal(jaxb_element, xmlWriter);

				final String str_xml_parser = xmlWriter.toString();
				XmlObject xmlObj = XmlObject.Factory.parse(str_xml_parser);

				if (xmlObj != null) {
					Node raiz = xmlObj.getDomNode();
					if (raiz.getChildNodes() != null) {
						for (int i = 0; i < raiz.getChildNodes().getLength(); i++) {
							Node raiz00 = raiz.getChildNodes().item(i);
							modificarParaImpresionNodosByte(raiz00, nombres_campos_no_imprimir);
						}
					}
				}

				commandoAMostrarse = xmlObj.toString();

			}
			catch (Exception e) {
				LOGGER.error("Error parseando object to xml:" + Util.getStackTraceFromException(e));
			}
		}
		else {
			commandoAMostrarse = "El objeto es nulo";
		}
		return commandoAMostrarse;
	}
	
	private static void modificarParaImpresionNodosByte(Node nodo, ArrayList<String> nombres_campos_no_imprimir) {
		try {
			if (nodo.getChildNodes() != null) {
				for (int m = 0; m < nodo.getChildNodes().getLength(); m++) {
					Node subnodo = nodo.getChildNodes().item(m);

					if (subnodo.getChildNodes() != null && subnodo.getChildNodes().getLength() > 0) {
						modificarParaImpresionNodosByte(subnodo, nombres_campos_no_imprimir);
					}
					else {
						for (int k = 0; k < nombres_campos_no_imprimir.size(); k++) {
							if (nodo.getLocalName().equalsIgnoreCase(nombres_campos_no_imprimir.get(k))) {

								/*
								 * Se remueve el contenido del nodo porque contiene informacion tipo bytes e imprimirla es demasiado costoso para el proceso
								 */
								if (subnodo.getNodeType() == Node.TEXT_NODE) {
									subnodo.setNodeValue("===BYTES ARRAY===");
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e) {
		}
	}
}
