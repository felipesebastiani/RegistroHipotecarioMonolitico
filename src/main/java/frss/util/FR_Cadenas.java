package frss.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;


public class FR_Cadenas {
	public static final String	SALTO_LINEA		= "\n";
	public static final String	TAB				= "\t";
	public static final String	CADENA_VACIA	= "";

	public static String reemplazaCadena(String cadena, String cadena_buscada, String cadena_a_sustuir) {
		int posicion_del_ultimo_patron = 0, posicion_de_un_nuevo_patron = 0;
		// Almaceno la longitud del patñn, asñ evitarñ invocar el mñtodo
		// length() en un
		// bucle, hecho que podrña producir un innecesario nñmero de llamdas a
		// ese mñtodo.
		int longitud_del_patron = cadena_buscada.length();
		StringBuffer result = new StringBuffer();
		// Si cadena_buscada, cadena o cadena_a_sustuir son null, o cadena o
		// cadena_buscada son una cadena de
		// caracteres vacña no podremos relizar esta operaciñn, por lo que
		// devolveremos null
		if (cadena == null || cadena.equals("") || cadena_buscada.equals("") || cadena_a_sustuir == null) {
			return null;
		}
		// El metodo indexOf nos devuelve la posicion de la primera ocurrencia
		// del patrnn
		// que se pasa de argumento, despues del caracter
		// posicion_del_ultimo_patron
		while ((posicion_de_un_nuevo_patron = cadena.indexOf(cadena_buscada, posicion_del_ultimo_patron)) >= 0) {
			// Añado al StringBuffer el fragmento de la cadena de caracteres
			// entre la ultima ocurrencia
			// del cadena_buscada y la nueva
			result.append(cadena.substring(posicion_del_ultimo_patron, posicion_de_un_nuevo_patron));
			// Añado al StringBufer el nuevo cadena_buscada en la posicion en la
			// que estab el viejo en la cadena cadena
			result.append(cadena_a_sustuir);
			// Actualiza la posicion del ultimo cadena_buscada
			posicion_del_ultimo_patron = posicion_de_un_nuevo_patron + longitud_del_patron;
		}
		// añadimos al buffer el resto de la cadena cadena
		result.append(cadena.substring(posicion_del_ultimo_patron));
		return result.toString();

	}

	public static String reemplazaCaracterSaltoLineaParaCadenaHTML(String cadena_origen) {
		String rpta = null;
		String caracter_buscado = "\n";
		String cadena_reemplazo = "<p></p>";

		for (int i = 0; i < cadena_origen.length(); i++) {
			char caracter = cadena_origen.charAt(i);
			FR_Util.trace("[i: " + i + "][char: " + caracter + "][entero: " + (int) caracter + "]");

			if (caracter == 13) {// Salto de linea
				// if(cadena_reemplazo.equals("<br/>")) {
				rpta += cadena_reemplazo;
				// }
			}

			if ((caracter + "").equals(caracter_buscado) == true) {
				rpta += cadena_reemplazo;
			}
			else {
				rpta += caracter;
			}
		}

		return rpta;
	}

	public static Vector<String> getVector_palabras(String frase) {
		Vector<String> vector_palabras = new Vector<String>();
		String palabra = "";

		for (int i = 0; i < frase.length(); i++) {
			if (frase.charAt(i) != ' ') {
				palabra += frase.charAt(i);
			}
			else {
				vector_palabras.addElement(palabra);
				palabra = "";
			}

			if (i == frase.length() - 1 && palabra.equals("") == false && palabra.equals(" ") == false) {
				vector_palabras.addElement(palabra);
			}
		}

		// Removemos alguna palabra en blanco
		vector_palabras.removeElement("");

		return vector_palabras;
	}

	public static Vector<String> getVector_lineas_segun_separador(Vector<String> vector_palabras, int maximo_caracteres_x_linea, String cadena_separacion) {
		Vector<String> vector_lineas = new Vector<String>();
		String linea = "";

		for (int i = 0; i < vector_palabras.size(); i++) {
			linea = linea + " " + vector_palabras.elementAt(i);

			if (linea.length() > maximo_caracteres_x_linea) {
				if (i != vector_palabras.size() - 1) {
					linea += cadena_separacion;
				}
				vector_lineas.addElement(linea);
				linea = "";
			}

			// Caso de la ultima iteraciñn
			if (i == vector_palabras.size() - 1 && linea.equals("") == false) {
				vector_lineas.addElement(linea);
				linea = "";
			}
		}

		return vector_lineas;
	}

	public static Vector<String> getVector_palabras_ordenadas_alfabeticament_ascendentemente(Vector<String> vector_palabras) {
		Vector<String> vector_palabras_ordenadas = new Vector<String>();

		Object array[] = vector_palabras.toArray();
		Arrays.sort(array);

		for (int j = 0; j < array.length; j++) {
			vector_palabras_ordenadas.addElement(array[j] + "");
		}

		return vector_palabras_ordenadas;
	}

	public static Vector<String> antes_getVector_lineas_ancho_maximo(String frase, int tamanho) {
		Vector<String> vector = new Vector<String>();
		while (frase.length() > 0) {
			if (frase.length() > tamanho) {
				vector.addElement(frase.substring(0, tamanho));
				frase = frase.substring(tamanho, frase.length());
			}
			else {
				vector.addElement(frase.substring(0, frase.length()));
				frase = "";
			}
		}

		return vector;
	}

	public static Vector<String> getVector_lineas_ancho_maximo(String frase, int tamanho) {
		Vector<String> vector_lineas = new Vector<String>();

		if (frase.length() <= tamanho) {
			vector_lineas.addElement(frase);
			return vector_lineas;
		}
		else {
			Vector<String> vector_palabras = getVector_palabras(frase);
			String linea = "";

			while (vector_palabras.size() > 0) {
				String palabra = vector_palabras.elementAt(0);

				if (linea.length() + palabra.length() <= tamanho) {
					linea += palabra + " ";

					vector_palabras.removeElementAt(0);

				}
				else {
					// linea = "";
					vector_lineas.addElement(linea);
					linea = "";
				}

				if (vector_palabras.size() == 0) {
					vector_lineas.addElement(linea);
				}
				FR_Util.trace(linea);
			}
		}

		return vector_lineas;
	}

	/**
	 * Este metodo divide una frase en lineas de manera arbitraria segñn el caracter enviado como indicador de una nueva linea
	 * 
	 * Se considera el separador_linea oficial como \n
	 */
	public static Vector<String> getVector_lineas_segun_separador_linea(String frase) {
		Vector<String> vector_lineas = new Vector<String>();

		// Cargamos las notas linea por linea
		// Leemos linea por linea
		StringReader sr = new StringReader(frase);
		BufferedReader br = new BufferedReader(sr);
		String nextLine = "";
		String linea = "";

		try {
			while ((nextLine = br.readLine()) != null) {
				linea = nextLine;

				if (linea.length() != 0) {
					vector_lineas.addElement(linea);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * frase = frase.replaceAll(divisor+"", "ñ");
		 * 
		 * String linea = ""; for (int i = 0; i < frase.length(); i++) { if(frase.charAt(i)!= 'ñ') { linea += frase.charAt(i); } else { vector_lineas.addElement(linea); linea = ""; } }
		 * 
		 * for (int i = 0; i < vector_lineas.size(); i++) { FR_Util.trace("+++" + vector_lineas.elementAt(i) + "+++"); }
		 */
		return vector_lineas;
	}

	public static ArrayList<String> stringTokenizer_array(String cadena, char separador) {
		ArrayList<String> arreglo_palabras = new ArrayList<String>();
		StringTokenizer tempStringTokenizer = new StringTokenizer(cadena, separador + "");
		FR_Util.trace("cadena a dividir: " + cadena);
		// push all the words to the stack one by one
		while (tempStringTokenizer.hasMoreTokens()) {
			arreglo_palabras.add(tempStringTokenizer.nextElement() + "");
		}

		return arreglo_palabras;
	}

	public static boolean leerBooleanEnCadena(String cadena) {
		boolean valor = false;

		if (cadena != null && cadena.equalsIgnoreCase("true")) {
			valor = true;
		}

		return valor;
	}
}