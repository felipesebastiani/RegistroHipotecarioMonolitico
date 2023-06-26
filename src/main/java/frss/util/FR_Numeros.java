package frss.util;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FR_Numeros {
	
	//Genera un entero aleatorio entre [a,b]
	public static int aleatorio (int a, int b){
		if(b<a)
			return (int) ((b - a + 1) * Math.random() + a);
		else
			return (int) ((a - b + 1) * Math.random() + b);
	}
	
	public static long aleatorio (long a, long b){
		if(b<a)
			return (long) ((b - a + 1) * Math.random() + a);
		else
			return (long) ((a - b + 1) * Math.random() + b);
	}
	
	public static double aleatorio(double a, double b) {
		if(a>=b)
			return ((a - b + 1)* Math.random()+ b);
		else //a<b
			return ((b - a + 1)* Math.random()+ a);
	}
	
	/**
	* FORMATEO DE NUMEROS DECIMALES 
	*
	* Con esta cadena, "#,#00.0#;(-#,#00.0#)" 
	* definimos el formato para los numero positivos y negativos
	* Los # indican valores no obligatorios
	* Los 0 indican que si no hay valor se pondr� un cero 
	*/
	public static String getDecimalFormateado(double decimal){
		String valor = "";
		DecimalFormat df = new DecimalFormat("#0.0;(-#0.0)");
		valor = df.format(decimal);
		return valor;
	}
	
	public static String formatea_con_separador_miles(long entero_largo){
		String valor = "", temporal="";
		DecimalFormat df = new DecimalFormat("#,###,###");
		temporal = df.format(entero_largo);
		
		for (int i = 0; i < temporal.length(); i++) {
			if(temporal.charAt(i)!='.')
				valor += temporal.charAt(i);
			else
				valor += ',';
		}
		return valor;
	}
	
	public static String getDecimalFormateado(String cadena_decimal, int numero_decimales){
		String valor = "";
		
		//Establece la configuraci�n del formateador de numeros decimales.
		DecimalFormatSymbols dformater_rules = new DecimalFormatSymbols ();
		dformater_rules.setDecimalSeparator ('.');
		DecimalFormat df = new DecimalFormat ("0.00", dformater_rules);
		df.setMaximumFractionDigits(numero_decimales);
		df.setMinimumFractionDigits(numero_decimales);
		valor = df.format(cadena_decimal);
		return valor;
	}
	
	public static String getDecimalFormateado(float decimal, int numero_decimales){
		String valor = "";
		
		//Establece la configuraci�n del formateador de numeros decimales.
		DecimalFormatSymbols dformater_rules = new DecimalFormatSymbols ();
		dformater_rules.setDecimalSeparator ('.');
		dformater_rules.setGroupingSeparator(' ');
		DecimalFormat df = new DecimalFormat ("###,###,##0.00", dformater_rules);
		df.setMaximumFractionDigits(numero_decimales);
		df.setMinimumFractionDigits(numero_decimales);
		valor = df.format(decimal);
		return valor;
	}
	
	public static String getDecimalFormateado(double decimal, int numero_decimales){
		String valor = "";
		
		//Establece la configuraci�n del formateador de numeros decimales.
		DecimalFormatSymbols dformater_rules = new DecimalFormatSymbols ();
		dformater_rules.setDecimalSeparator ('.');
		dformater_rules.setGroupingSeparator(' ');
		DecimalFormat df = new DecimalFormat ("###,###,##0.00",dformater_rules);
		
		df.setMaximumFractionDigits(numero_decimales);
		df.setMinimumFractionDigits(numero_decimales);
		valor = df.format(decimal);
		return valor;
	}
	
	public static String entero_a_Hexadecimal(int valor){
		String	valor_hexadecimal="";
		
		valor_hexadecimal = Integer.toHexString(valor & 0Xffffff).toUpperCase();
		
		int num_valores = valor_hexadecimal.length();
		
		if(num_valores<6){
			for(int i=0;i<6 - num_valores;i++){
				valor_hexadecimal = "0" +valor_hexadecimal;
			}
		}
		return valor_hexadecimal;
	}

	public static String color_a_Hexadecimal(Color color){
		String	valor_hexadecimal="";
		
		valor_hexadecimal = "#" +entero_a_Hexadecimal(color.getRGB());
		
		return valor_hexadecimal;
	}
	
	public static double redondeaDouble(double valor, int num_decimales) {
		double rpta =0 ;
		double power_of_ten = 1;
		
		while (num_decimales-- > 0){
			power_of_ten *= 10.0;
		}
		
		rpta = Math.round(valor * power_of_ten)/power_of_ten;
		return rpta;
	}
	
	public static double redondeaDouble(String valor_texto, int num_decimales) {
		double valor = leeDouble(valor_texto);
		double rpta =0 ;
		double power_of_ten = 1;
		
		while (num_decimales-- > 0){
			power_of_ten *= 10.0;
		}
		
		rpta = Math.round(valor * power_of_ten)/power_of_ten;
		return rpta;
	}
	
	public static float redondeaFloat(float valor, int num_decimales) {
		float rpta =0 ;
		float power_of_ten = 1;
		
		while (num_decimales-- > 0){
			power_of_ten *= 10.0;
		}
		
		rpta = Math.round(valor * power_of_ten)/power_of_ten;
		return rpta;
	}
	
	public static int leeInteger(String cadena){
		int numero = 0;
			try {
				numero = Integer.parseInt(cadena);
			}
			catch (NumberFormatException e) {
				numero = 0;
			}
		return numero;
	}
	
	public static int leeInteger(Object objeto){
		int numero = 0;
		String cadena = "";
			try {
				cadena = objeto + "";
				numero = Integer.parseInt(cadena);
			}
			catch (NumberFormatException e) {
				numero = 0;
			}
		return numero;
	}
	
	public static long leeLong(String cadena){
		long numero = 0;
			try {
				numero = Long.parseLong(cadena);
			}
			catch (NumberFormatException e) {
				numero = 0;
			}
		return numero;
	}
	
	public static long leeLong(Object objeto){
		long numero = 0;
		String cadena = "";
		
			try {
				cadena = objeto + ""; 
				numero = Long.parseLong(cadena);
			}
			catch (NumberFormatException e) {
				numero = 0;
			}
		return numero;
	}
	
	public static double leeDouble(String cadena){
		double numero = -1;
			try {
				numero = Double.parseDouble(cadena);
			}
			catch (NumberFormatException e) {
				numero = 0;
			}
		return numero;
	}
	
	public static double leeDouble(Object objeto){
		double numero = -1;
		String cadena = "";
			try {
				cadena = objeto + "";
				numero = Double.parseDouble(cadena);
			}
			catch (NumberFormatException e) {
				numero = 0;
			}
		return numero;
	}
	
	public static float leeFloat(String cadena){
		float numero = -1;
			try {
				numero = Float.parseFloat(cadena);
			}
			catch (NumberFormatException e) {
				numero = 0;
			}
		return numero;
	}
	
	public static float leeFloat(Object objeto){
		float numero = -1;
		String cadena = "";
			try {
				cadena = objeto + "";
				numero = Float.parseFloat(cadena);
			}
			catch (NumberFormatException e) {
				numero = 0;
			}
		return numero;
	}
		
	/**
	* TRANSFORMACION DE VALOR NUMERICO A EXPRESION LITERAL
	*
	* Esta secci�n permite transformar un valor num�rico en la
	* correspondiente expresi�n literal que represente a dicho valor.
	*/
	
	  static String  getCadenaLetras (int numero, long num_original) {
		  String   cadena = "", cc = "", dd = "", uu = "";
		  int      centena = 0, decena = 0, unidad = 0;
		  centena = numero/100;
		  numero %= 100;
		  decena = numero/10;
		  unidad = numero%10;
		  
		  switch (centena) {
			  case 1: cc = "cien"; 
			  		if (decena != 0  ||  unidad != 0){ 
			  			cc += "to";
			  		}
		            break; 
			  case 2: cc = "doscientos"; break; 
			  case 3: cc = "trescientos"; break; 
			  case 4: cc = "cuatrocientos"; break; 
			  case 5: cc = "quinientos"; break; 
			  case 6: cc = "seiscientos"; break; 
			  case 7: cc = "setecientos"; break; 
			  case 8: cc = "ochocientos"; break; 
			  case 9: cc = "novecientos"; break;        
		}

		switch (decena) {
			case 1: switch (unidad) {
	   			case 0: dd = "diez"; break;
	   			case 1: dd = "once"; break;
	   			case 2: dd = "doce"; break;
	  			case 3: dd = "trece"; break;
	   			case 4: dd = "catorce"; break;		  
	   			case 5: dd = "quince"; break;
	   			default: dd = "dieci"; 
			} 	 
      		break;
			case 2: dd = "veint";
		        	if (unidad == 0)
		          		dd += "e";
		        	else dd += "i";  
		        	break; 
			case 3: dd = "treint"; break; 		        
			case 4: dd = "cuarent"; break; 		        
			case 5: dd = "cincuent"; break; 		        
			case 6: dd = "sesent"; break; 		        
			case 7: dd = "setent"; break;
			case 8: dd = "ochent"; break; 		        
			case 9: dd = "novent"; break;
		}
		
		if (decena > 2) {
			if (unidad == 0){
		  		dd += "a";
			}
			else{
				dd += "i";
			}
		}	

		if (decena != 1  ||  unidad > 5){
			switch (unidad) {
		 		case 1: if (numero <10 && num_original > 10){
		 					uu = "uno";
		 				}
		    	 		else{
		    	 			uu = "un";
		    	 		}
		 				break;
		 		case 2: uu = "dos"; break;
				case 3: uu = "tres"; break;
				case 4: uu = "cuatro"; break;
				case 5: uu = "cinco"; break;
				case 6: uu = "seis"; break;
				case 7: uu = "siete"; break;
				case 8: uu = "ocho"; break;
				case 9: uu = "nueve"; break;		        
	  		}
		}
		if (decena == 0  &&  unidad == 0){
	  		cadena = cc;
		}
		else{
			if(cc.equals("")==false){
				cadena = cc + " " + dd + uu;	
			}
			else{
				cadena = cc + "" + dd + uu;
			}
		}
		
		return  cadena;
	}
		
	public static String  getCadenaNumericaEnPalabras (long numero) {
	 	String  cadena = "", c;
	 	int     x = 0, n;
	 	while (numero > 0) {
	 		x++;
	 		n = (int) (numero % 1000);
	 		c = "";
	 		switch (x) {
	   	  		case  2:
	   	  		case  4:
	   	  		case  6:
	   	  		case  8: if (n > 0)
	   	             		c = " mil ";
	   	           		 break;
	   	  		case  3: if (numero == 1)
	   	             		c = " mill�n "; 
	   	           		 else
	   	           			 c = " millones "; 
	   	           	     break;
	   	  		case  5: if (numero == 1)
	   	             		c = " bill�n "; 
	   	           		 else
	   	           			 c = " billones "; 
	  	           		 break;
	   	  		case  7: if (numero == 1)
	   	             	  	c = " trill�n "; 
	   	           		 else
	   	           			 c = " trillones "; 
	   	           		 break;	  	           
	    	}		 	
	    	numero /= 1000;	 
	    	cadena = getCadenaLetras(n, numero) + c + cadena;
	 	}
	 	return  cadena; 
	}
	
	public static String getCadenaNumericaEnPalabrasMoneda(double n, String moneda){
		FR_Util.trace("Aca >>>>> " + n);
		String  cadena = "";
		long parte_numerica_entera = (long)n;
		String cadena_parte_entera = getCadenaNumericaEnPalabras(parte_numerica_entera);
		String cadena_parte_decimal = "";
		double valor_decimales = n - parte_numerica_entera;
		
		if(FR_Numeros.redondeaDouble(valor_decimales,2)==1){
			FR_Util.trace("El redondeo de decimales equivale a 1.0, se considerara como 1 unidad mas: " + FR_Numeros.redondeaDouble(valor_decimales,0));
			parte_numerica_entera +=1;
			cadena_parte_entera = getCadenaNumericaEnPalabras(parte_numerica_entera);
			
			valor_decimales = 0;
		}
		
		FR_Util.trace("parte_numerica_entera: " + parte_numerica_entera);
		FR_Util.trace("valor_decimales: " + valor_decimales);
		FR_Util.trace("parte_numerica_entera - n: " + (parte_numerica_entera - n));
		
		
		/*
		
		valor_decimal = FR_Numeros.redondeaDouble(valor_decimal,2);
		parte_decimal = valor_decimal + "";
		FR_Util.trace("parte_decimal00: " + parte_decimal);
		
		parte_decimal = parte_decimal.substring(parte_decimal.indexOf('.')+1, parte_decimal.length());
		
		
		if(FR_Numeros.leeInteger(parte_decimal) < 10){
			
			parte_decimal =  "0" + FR_Numeros.leeInteger(parte_decimal);
		}
		
		FR_Util.trace("parte_decimal: " + parte_decimal);
		FR_Util.trace("parte_entera: " + parte_entera);
		*/
		
		cadena_parte_decimal = ((int)FR_Numeros.redondeaDouble((valor_decimales * 100),0))+"";
		
		if(cadena_parte_entera.length()>1){
			cadena_parte_entera = (cadena_parte_entera.charAt(0)+"").toUpperCase() + cadena_parte_entera.substring(1,cadena_parte_entera.length()); 
		}
		else{
			cadena_parte_entera = "Cero";
		}
		
		if(cadena_parte_decimal.equals("0")) {
			cadena_parte_decimal = "00";
		}
		
		//FR_Util.trace("U: " + cadena_parte_entera.charAt(cadena_parte_entera.length()-2));
		//FR_Util.trace("N: " + cadena_parte_entera.charAt(cadena_parte_entera.length()-1));
		
		if(cadena_parte_entera.length()>2 && cadena_parte_entera.toUpperCase().charAt(cadena_parte_entera.length()-2)=='U' && cadena_parte_entera.toUpperCase().charAt(cadena_parte_entera.length()-1)=='N') {
			cadena_parte_entera = cadena_parte_entera + "O";// Para q al final ponga UNO en vez de UN
		}
		
		cadena = cadena_parte_entera + " y " + cadena_parte_decimal + "/100 " + moneda;
		 
		return cadena;
	}
	
	public static boolean isDoubleAndInteger(double n) {
		boolean b = false;
		int temp = (int)n;
		if(n-temp == 0) {//Example: 11.00 - 11 = 0.00
			b = true;
		}
		return b;
	}
	
	/**
	 * 
	 * Obtiene la distancia entre 2 puntos A(a,b) y B(c,d)
	*/
	public static double getDistanciaEntreDosPuntos(double a, double b, double c, double d) {
		double distancia = 0;
		
		distancia = Math.sqrt(Math.pow(a-c, 2) + Math.pow(b-d, 2)); 
		
		return distancia;
	}

	/**
	 * 
	 * Obtiene la distancia entre 2 puntos A(a,b) y B(c,d)
	*/
	public static int getDistanciaEntreDosPuntos(int a, int b, int c, int d) {
		int distancia = 0;
		
		distancia = (int)Math.sqrt(Math.pow(a-c, 2) + Math.pow(b-d, 2)); 
		
		return distancia;
	}

}