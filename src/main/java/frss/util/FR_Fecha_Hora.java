package frss.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FR_Fecha_Hora {

	public static boolean validaFecha_formato_DATE(String cadena) {
		boolean rpta = false;
		int dia = 0, mes = 0, anho = 0;

		try {
			dia = FR_Numeros.leeInteger(cadena.charAt(0) + "" + cadena.charAt(1) + "");
			mes = FR_Numeros.leeInteger(cadena.charAt(3) + "" + cadena.charAt(4) + "");
			anho = FR_Numeros.leeInteger(cadena.charAt(6) + "" + cadena.charAt(7) + "" + cadena.charAt(8) + "" + cadena.charAt(9) + "");

			if (cadena.charAt(2) != '-' || cadena.charAt(5) != '-') {
				rpta = false;
			}
			else if (dia < 1 || dia > 31) {
				rpta = false;
			}
			else if (mes < 1 || mes > 12) {
				rpta = false;
			}
			else if (anho < 1900) {
				rpta = false;
			}
			else {
				rpta = true;
			}
		}
		catch (Exception e) {
			rpta = false;
		}
		return rpta;
	}

	public static String getFecha_formato_DATE_con_eleccion_de_separador(Date fecha, char divisor) {
		String cadena = "";
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		calendario_gregoriano.setTime(fecha);

		int dia_num = calendario_gregoriano.get(Calendar.DATE), mes_num = calendario_gregoriano.get(Calendar.MONTH) + 1, anho_num = calendario_gregoriano.get(Calendar.YEAR);

		String dia = "", mes = "", anho = "";
		try {
			if (dia_num < 10)
				dia = "0" + dia_num;
			else
				dia = dia_num + "";

			if (mes_num < 10)
				mes = "0" + mes_num;
			else
				mes = mes_num + "";

			anho = anho_num + "";

			cadena = anho + divisor + mes + divisor + dia;
		}
		catch (Exception e) {
			FR_Util.traceTipoDebug("Error del tipo:" + e.getMessage(), "FR_Fecha_Hora", e);
			cadena = "00" + divisor + "00" + divisor + "0000";
		}
		return cadena;
	}

	public static String getFecha_formato_DATE(Date fecha) {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		calendario_gregoriano.setTime(fecha);
		int dia_num = calendario_gregoriano.get(Calendar.DATE), mes_num = calendario_gregoriano.get(Calendar.MONTH) + 1, anho_num = calendario_gregoriano.get(Calendar.YEAR);
		String cadena = "";
		String dia = "", mes = "", anho = "";
		try {
			if (dia_num < 10)
				dia = "0" + dia_num;
			else
				dia = dia_num + "";

			if (mes_num < 10)
				mes = "0" + mes_num;
			else
				mes = mes_num + "";

			anho = anho_num + "";

			cadena = anho + "-" + mes + "-" + dia;
		}
		catch (Exception e) {
			FR_Util.trace("Error del tipo:" + e.getMessage());
			cadena = "1900-01-01";
		}
		FR_Util.trace("dia: " + dia);
		FR_Util.trace("mes: " + mes);
		FR_Util.trace("año: " + anho);

		return cadena;
	}

	public static String getFecha_actual_formato_dia_guion_mes_guion_anho() {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		int num_dia_mes = calendario_gregoriano.get(Calendar.DAY_OF_MONTH), num_mes = calendario_gregoriano.get(Calendar.MONTH) + 1, anho = calendario_gregoriano.get(Calendar.YEAR);
		String texto_fecha;

		if (num_dia_mes < 10) {
			if (num_mes < 10) {
				texto_fecha = "0" + num_dia_mes + "-0" + num_mes + "-" + anho;
			}
			else {
				texto_fecha = "0" + num_dia_mes + "-" + num_mes + "-" + anho;
			}
		}
		else {
			if (num_mes < 10) {
				texto_fecha = num_dia_mes + "-0" + num_mes + "-" + anho;
			}
			else {
				texto_fecha = num_dia_mes + "-" + num_mes + "-" + anho;
			}
		}

		return texto_fecha;
	}

	public static String getFecha_actual_formato_dia_de_mes_del_anho() {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		int num_dia_semana = calendario_gregoriano.get(Calendar.DAY_OF_WEEK), num_dia_mes = calendario_gregoriano.get(Calendar.DAY_OF_MONTH), num_mes = calendario_gregoriano.get(Calendar.MONTH), anho = calendario_gregoriano.get(Calendar.YEAR);
		String nombre_dia = "", nombre_mes = "", texto_fecha;

		nombre_dia = getNombreDia(num_dia_semana);
		nombre_mes = getNombreMes(num_mes);

		if (num_dia_mes < 10) {
			texto_fecha = nombre_dia + ", 0" + num_dia_mes + " de " + nombre_mes + " del " + anho;
		}
		else {
			texto_fecha = nombre_dia + ", " + num_dia_mes + " de " + nombre_mes + " del " + anho;
		}

		return texto_fecha;
	}

	public static String getFecha_formato_DATETIME(Date la_fecha) {
		String nueva_fecha = "";
		try {
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(la_fecha);

			String anho = calendario.get(Calendar.YEAR) + "";
			String mes = "";
			String dia = "";
			String horas = "";
			String minutos = "";
			String segundos = "";

			if ((calendario.get(Calendar.MONTH) + 1) < 10)
				mes = "0" + (calendario.get(Calendar.MONTH) + 1);
			else
				mes = (calendario.get(Calendar.MONTH) + 1) + "";

			if (calendario.get(Calendar.DATE) < 10)
				dia = "0" + calendario.get(Calendar.DATE);
			else
				dia = "" + calendario.get(Calendar.DATE);

			if (calendario.get(Calendar.HOUR_OF_DAY) < 10)
				horas = "0" + calendario.get(Calendar.HOUR_OF_DAY);
			else
				horas = "" + calendario.get(Calendar.HOUR_OF_DAY);

			if (calendario.get(Calendar.MINUTE) < 10)
				minutos = "0" + calendario.get(Calendar.MINUTE);
			else
				minutos = "" + calendario.get(Calendar.MINUTE);

			if (calendario.get(Calendar.SECOND) < 10)
				segundos = "0" + calendario.get(Calendar.SECOND);
			else
				segundos = "" + calendario.get(Calendar.SECOND);

			nueva_fecha = anho + "-" + mes + "-" + dia + " " + horas + ":" + minutos + ":" + segundos;
		}
		catch (Exception e) {
			nueva_fecha = null;
		}
		return nueva_fecha;
	}

	public static String getFechaFormateada_desde_DATETIME_para_JTable(Date la_fecha) {
		String nueva_fecha = "";
		try {
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(la_fecha);

			String anho = calendario.get(Calendar.YEAR) + "";
			String mes = "";
			String dia = "";
			String horas = "";
			String minutos = "";
			String segundos = "";
			String parte_meridiano = "";

			if (calendario.get(Calendar.DATE) < 10) {
				dia = "0" + calendario.get(Calendar.DATE);
			}
			else {
				dia = "" + calendario.get(Calendar.DATE);
			}

			if ((calendario.get(Calendar.MONTH) + 1) < 10) {
				mes = "0" + (calendario.get(Calendar.MONTH) + 1);
			}
			else {
				mes = (calendario.get(Calendar.MONTH) + 1) + "";
			}

			if (calendario.get(Calendar.HOUR) < 10) {
				horas = "0" + calendario.get(Calendar.HOUR);
			}
			else {
				horas = "" + calendario.get(Calendar.HOUR);
			}

			if (calendario.get(Calendar.MINUTE) < 10) {
				minutos = "0" + calendario.get(Calendar.MINUTE);
			}
			else {
				minutos = "" + calendario.get(Calendar.MINUTE);
			}

			if (calendario.get(Calendar.SECOND) < 10) {
				segundos = "0" + calendario.get(Calendar.SECOND);
			}
			else {
				segundos = "" + calendario.get(Calendar.SECOND);
			}

			if (calendario.get(Calendar.HOUR_OF_DAY) < 12) {
				parte_meridiano = "a.m.";
			}
			else {
				parte_meridiano = "p.m.";
			}

			nueva_fecha = dia + "/" + mes + "/" + anho + " - " + horas + ":" + minutos + ":" + segundos + " " + parte_meridiano;
		}
		catch (Exception e) {
			nueva_fecha = null;
		}
		return nueva_fecha;
	}

	public static String getFecha_formato_dia_guion_mes_guion_anho_con_eleccion_de_separador(Date fecha, char divisor) {
		String cadena = "";

		SimpleDateFormat formatter = new SimpleDateFormat("dd" + divisor + "MM" + divisor + "yyyy");
		cadena = formatter.format(fecha);
		return cadena;
	}

	public static String getFecha_formato_dia_guion_mes_guion_anho(Date la_fecha) {
		String nueva_fecha = "";
		String strDia, strMes = "";
		int dia, mes, anho;

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(la_fecha);
		dia = calendario.get(Calendar.DATE);
		mes = calendario.get(Calendar.MONTH) + 1;
		anho = calendario.get(Calendar.YEAR);

		// Dia
		if (dia < 10)
			strDia = "0" + dia;
		else
			strDia = "" + dia;

		// Mes
		if (mes < 10)
			strMes = "0" + mes;
		else
			strMes = "" + mes;

		nueva_fecha = strDia + "-" + strMes + "-" + anho;

		return nueva_fecha;
	}

	public static String getFecha_formato_nombre_dia_num_dia_de_mes_del_anho(Date fecha) {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		calendario_gregoriano.setTime(fecha);
		int num_dia_semana = calendario_gregoriano.get(Calendar.DAY_OF_WEEK), num_dia_mes = calendario_gregoriano.get(Calendar.DAY_OF_MONTH), num_mes = calendario_gregoriano.get(Calendar.MONTH), anho = calendario_gregoriano.get(Calendar.YEAR);
		String nombre_dia = "", nombre_mes = "", texto_fecha;

		nombre_dia = getNombreDia(num_dia_semana);
		nombre_mes = getNombreMes(num_mes);

		if (num_dia_mes < 10) {
			texto_fecha = nombre_dia + ", 0" + num_dia_mes + " de " + nombre_mes + " del " + anho;
		}
		else {
			texto_fecha = nombre_dia + ", " + num_dia_mes + " de " + nombre_mes + " del " + anho;
		}

		return texto_fecha;
	}

	public static String getFecha_formato_num_dia_de_mes_del_anho(Date fecha) {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		calendario_gregoriano.setTime(fecha);
		int num_dia_mes = calendario_gregoriano.get(Calendar.DAY_OF_MONTH), num_mes = calendario_gregoriano.get(Calendar.MONTH), anho = calendario_gregoriano.get(Calendar.YEAR);
		String nombre_mes = "", texto_fecha;

		nombre_mes = getNombreMes(num_mes);

		if (num_dia_mes < 10) {
			texto_fecha = "0" + num_dia_mes + " de " + nombre_mes + " del " + anho;
		}
		else {
			texto_fecha = num_dia_mes + " de " + nombre_mes + " del " + anho;
		}

		return texto_fecha;
	}

	public static int getDia(Date fecha) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		int dia = calendario.get(Calendar.DATE);

		return dia;
	}

	public static int getMes(Date fecha) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		int mes = calendario.get(Calendar.MONTH) + 1;

		return mes;
	}

	public static int getAnho(Date fecha) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		int anho = calendario.get(Calendar.YEAR);

		return anho;
	}

	public static int getDiaActual() {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		calendario_gregoriano.setTime(new Date());
		int num_dia_mes = calendario_gregoriano.get(Calendar.DAY_OF_MONTH);

		return num_dia_mes;
	}

	public static int getMes_actual() {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		calendario_gregoriano.setTime(new Date());
		int num_mes = calendario_gregoriano.get(Calendar.MONTH);

		return num_mes;
	}

	public static int getAnho_actual() {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		calendario_gregoriano.setTime(new Date());
		int num_anho = calendario_gregoriano.get(Calendar.YEAR);

		return num_anho;
	}

	public static Date getFecha(int anho, int mes, int dia) {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar(anho, mes - 1, dia);
		return calendario_gregoriano.getTime();
	}

	public static Date getFecha_actual() {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		return calendario_gregoriano.getTime();
	}

	public static String getHora(Date fecha) {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		calendario_gregoriano.setTime(fecha);

		int horas = calendario_gregoriano.get(Calendar.HOUR_OF_DAY), minutos = calendario_gregoriano.get(Calendar.MINUTE), segundos = calendario_gregoriano.get(Calendar.SECOND);
		String parte_del_dia = "", texto_hora = "";
		if (horas == 0)
			texto_hora = "12";
		else if (horas < 10) {
			texto_hora = "0" + horas;
		}
		else if (horas < 13) {
			texto_hora = "" + horas;
		}
		else {
			if (horas - 12 < 10) {
				texto_hora = "0" + (int) (horas - 12);
			}
			else {
				texto_hora = "" + (int) (horas - 12);
			}
		}
		// Establece la parte del día.
		if (horas < 12) {
			parte_del_dia = " a.m.";
		}
		else {
			parte_del_dia = " p.m.";
		}

		texto_hora += ":";

		if (minutos < 10)
			texto_hora = texto_hora + "0" + minutos;
		else
			texto_hora = texto_hora + minutos;

		texto_hora += ":";

		if (segundos < 10)
			texto_hora = texto_hora + "0" + segundos;
		else
			texto_hora = texto_hora + "" + segundos;

		texto_hora += parte_del_dia;

		return texto_hora;
	}

	public static String getHora_actual() {
		GregorianCalendar calendario_gregoriano = new GregorianCalendar();
		int horas = calendario_gregoriano.get(Calendar.HOUR_OF_DAY), minutos = calendario_gregoriano.get(Calendar.MINUTE), segundos = calendario_gregoriano.get(Calendar.SECOND);
		String parte_del_dia = "", texto_hora = "";
		if (horas == 0)
			texto_hora = "12";
		else if (horas < 10) {
			texto_hora = "0" + horas;
		}
		else if (horas < 13) {
			texto_hora = "" + horas;
		}
		else {
			if (horas - 12 < 10) {
				texto_hora = "0" + (int) (horas - 12);
			}
			else {
				texto_hora = "" + (int) (horas - 12);
			}
		}
		// Establece la parte del día.
		if (horas < 12) {
			parte_del_dia = " a.m.";
		}
		else {
			parte_del_dia = " p.m.";
		}

		texto_hora += ":";

		if (minutos < 10)
			texto_hora = texto_hora + "0" + minutos;
		else
			texto_hora = texto_hora + minutos;

		texto_hora += ":";

		if (segundos < 10)
			texto_hora = texto_hora + "0" + segundos;
		else
			texto_hora = texto_hora + "" + segundos;

		texto_hora += parte_del_dia;

		return texto_hora;
	}

	public static String getNumHoras() {
		String num_horas = "12";

		Calendar calendario = Calendar.getInstance();

		if (calendario.get(Calendar.HOUR_OF_DAY) < 10) {
			num_horas = "0" + calendario.get(Calendar.HOUR_OF_DAY);
		}
		else {
			num_horas = "" + calendario.get(Calendar.HOUR_OF_DAY);
		}

		return num_horas;
	}

	public static int getNumeroMes(String nombre_mes) {
		String[] meses = new String[12];
		int num_mes = -1;
		meses[0] = "ENERO";
		meses[1] = "FEBRERO";
		meses[2] = "MARZO";
		meses[3] = "ABRIL";
		meses[4] = "MAYO";
		meses[5] = "JUNIO";
		meses[6] = "JULIO";
		meses[7] = "AGOSTO";
		meses[8] = "SETIEMBRE";
		meses[9] = "OCTUBRE";
		meses[10] = "NOVIEMBRE";
		meses[11] = "DICIEMBRE";

		for (short i = 0; i < meses.length; i++) {
			if (meses[i].equals(nombre_mes.toUpperCase())) {
				num_mes = i;
				break;
			}
		}
		return num_mes;
	}

	public static String getNombreMes(int num_mes) {
		String nombre_mes = "";
		switch (num_mes) {
			case 0:
				nombre_mes = "Enero";
				break;
			case 1:
				nombre_mes = "Febrero";
				break;
			case 2:
				nombre_mes = "Marzo";
				break;
			case 3:
				nombre_mes = "Abril";
				break;
			case 4:
				nombre_mes = "Mayo";
				break;
			case 5:
				nombre_mes = "Junio";
				break;
			case 6:
				nombre_mes = "Julio";
				break;
			case 7:
				nombre_mes = "Agosto";
				break;
			case 8:
				nombre_mes = "Setiembre";
				break;
			case 9:
				nombre_mes = "Octubre";
				break;
			case 10:
				nombre_mes = "Noviembre";
				break;
			case 11:
				nombre_mes = "Diciembre";
				break;
			default:
				nombre_mes = "null";
		}
		return nombre_mes;
	}

	public static String getNombreDia(int num_dia_semana) {
		String nombre_dia = "";
		if (num_dia_semana == Calendar.MONDAY)
			nombre_dia = "Lunes";
		else if (num_dia_semana == Calendar.TUESDAY)
			nombre_dia = "Martes";
		else if (num_dia_semana == Calendar.WEDNESDAY)
			nombre_dia = "Miércoles";
		else if (num_dia_semana == Calendar.THURSDAY)
			nombre_dia = "Jueves";
		else if (num_dia_semana == Calendar.FRIDAY)
			nombre_dia = "Viernes";
		else if (num_dia_semana == Calendar.SATURDAY)
			nombre_dia = "Sábado";
		else if (num_dia_semana == Calendar.SUNDAY)
			nombre_dia = "Domingo";
		else
			nombre_dia = "null";

		return nombre_dia;
	}

	public static String getCadenaFecha_lastModified(long lastModified) {
		String cadenaFecha_lastModified = null;
		java.text.DateFormat formatter = new java.text.SimpleDateFormat("EEEE MMMM d, yyyy ");
		java.util.Date fecha_lastModified = new java.util.Date(lastModified);
		cadenaFecha_lastModified = formatter.format(fecha_lastModified);

		return cadenaFecha_lastModified;
	}

	/**
	 * Obtiene el número de días entre 2 fechas. El valor 0 es retornado si las fechas son las mismas. El orden de las fechas no importa, el resultado es un valor absoluto, es decir, el número de días >=0.
	 */
	public static long getNumeroDiasEntreDosFechas(Date fecha_inicio, Date fecha_fin) {
		long num_dias = 0;
		Calendar fecha_inicial = Calendar.getInstance();
		fecha_inicial.setTime(fecha_inicio);

		Calendar fecha_final = Calendar.getInstance();
		fecha_final.setTime(fecha_fin);

		if (fecha_inicial.after(fecha_final)) { // swap dates so that fecha_inicial is start and fecha_final is end
			Calendar swap = fecha_inicial;
			fecha_inicial = fecha_final;
			fecha_final = swap;
		}

		num_dias = fecha_final.get(Calendar.DAY_OF_YEAR) - fecha_inicial.get(Calendar.DAY_OF_YEAR);
		int y2 = fecha_final.get(Calendar.YEAR);
		if (fecha_inicial.get(Calendar.YEAR) != y2) {
			fecha_inicial = (Calendar) fecha_inicial.clone();
			do {
				num_dias += fecha_inicial.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				fecha_inicial.add(java.util.Calendar.YEAR, 1);
			}
			while (fecha_inicial.get(java.util.Calendar.YEAR) != y2);
		}

		return num_dias;
	}

	/**
	 * Obtiene el número de meses entre 2 fechas. El valor 0 es retornado si las fechas son las mismas. El orden de las fechas no importa, el resultado es un valor absoluto, es decir, el número de meses >=0.
	 * 
	 * Se han considerado meses de 30 días.
	 */
	public static long getNumeroMesesEntreDosFechas(Date fecha_inicio, Date fecha_fin) {
		return (getNumeroDiasEntreDosFechas(fecha_inicio, fecha_fin) / 30);
	}

	/**
	 * Obtiene el número de años entre 2 fechas. El valor 0 es retornado si las fechas son las mismas. El orden de las fechas no importa, el resultado es un valor absoluto, es decir, el número de años >=0.
	 */
	public static long getNumeroAnhosEntreDosFechas(Date fecha_inicio, Date fecha_fin) {
		return (getNumeroDiasEntreDosFechas(fecha_inicio, fecha_fin) / 365);
	}

	/**
	 * Obtiene la fecha aumentada o disminuida en n días según la fecha parámetro. Acepta n días negativos
	 **/

	public static Date getFechaEnElTiempo(Date fecha_actual, int n, String unidad_tiempo) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha_actual);
		if (unidad_tiempo.equals("DIAS") == true) {
			calendar.add(Calendar.DAY_OF_YEAR, n);
		}
		else if (unidad_tiempo.equals("MESES") == true) {
			calendar.add(Calendar.MONTH, n);
		}
		else if (unidad_tiempo.equals("AÑOS") == true) {
			calendar.add(Calendar.YEAR, n);
		}

		/*
		 * GregorianCalendar gregorian_calendar = new GregorianCalendar();//GregorianCalendar.getInstance(); gregorian_calendar.setTime(fecha_actual); gregorian_calendar.add(Calendar.DAY_OF_MONTH,n);
		 */

		return calendar.getTime();
	}

	/**
	 * Conversiones entre java.sql.Date, java.sql.Time y java.util.date
	 * 
	 */
	private static final DateFormat	utilDateFormatter	= new SimpleDateFormat("dd-MM-yyyy");
	private static final DateFormat	sqlDateFormatter	= new SimpleDateFormat("yyyy-MM-dd");

	public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
		java.sql.Date fecha_sql = null;
		try {
			fecha_sql = java.sql.Date.valueOf(sqlDateFormatter.format(utilDate));
		}
		catch (Exception e) {
			fecha_sql = null;
			e.printStackTrace();
		}

		return fecha_sql;
	}

	public static java.util.Date sqlDateToutilDate(java.sql.Date sqlDate) {
		java.util.Date fecha_util = null;

		try {
			fecha_util = utilDateFormatter.parse(utilDateFormatter.format(sqlDate));
		}
		catch (ParseException e) {
			fecha_util = null;
			e.printStackTrace();
		}
		return fecha_util;
	}

	public static String getCadenaDateTimeSQL_Oracle(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		String anho = calendar.get(Calendar.YEAR) + "";
		String mes = (calendar.get(Calendar.MONTH) + 1) + "";
		String dia = (calendar.get(Calendar.DATE)) + "";
		String horas_full = (calendar.get(Calendar.HOUR_OF_DAY)) + "";
		String minutos = (calendar.get(Calendar.MINUTE)) + "";
		String segundos = (calendar.get(Calendar.SECOND)) + "";
		String indicador_tiempo = "";

		if (calendar.get(Calendar.DATE) < 10) {
			dia = "0" + dia;
		}

		if (calendar.get(Calendar.MONTH) < 10) {
			mes = "0" + mes;
		}

		if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
			horas_full = "0" + horas_full;
		}

		if (calendar.get(Calendar.MINUTE) < 10) {
			minutos = "0" + minutos;
		}

		if (calendar.get(Calendar.SECOND) < 10) {
			segundos = "0" + segundos;
		}

		if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
			indicador_tiempo = "AM";
		}
		else {
			indicador_tiempo = "PM";
		}

		if (calendar.get(Calendar.HOUR_OF_DAY) > 12) {
			int valor_hora = (calendar.get(Calendar.HOUR_OF_DAY) - 12);
			horas_full = valor_hora + "";
			if (valor_hora < 10) {
				horas_full = "0" + valor_hora;
			}
		}

		if (horas_full.equals("00")) {
			horas_full = "12";
		}

		// 'MM/DD/YYYY HH:MI:SS PM

		String rpta = dia + "/" + mes + "/" + anho + " " + horas_full + ":" + minutos + ":" + segundos + " " + indicador_tiempo;
		FR_Util.trace("oracle_sql_date_format: " + rpta);

		return rpta;
	}
	
	public static String getCadenaDateSQL(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		String anho = calendar.get(Calendar.YEAR) + "";
		String mes = (calendar.get(Calendar.MONTH) + 1) + "";
		String dia = (calendar.get(Calendar.DATE)) + "";

		if (calendar.get(Calendar.DATE) < 10) {
			dia = "0" + dia;
		}

		if (calendar.get(Calendar.MONTH) < 10) {
			mes = "0" + mes;
		}

		String rpta =  anho + "-" + mes + "-" + dia;
		FR_Util.trace("getCadenaDateSQL: " + rpta);

		return rpta;
	}

	public static int calcularEdadAnhos(LocalDate birthDate) {
		LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());

		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		}
		else {
			return 0;
		}
	}

	public static int[] calcularEdadAnhosYMeses(LocalDate birthDate) {
		int[] valores = new int[2];

		int allYears = 0;
		int partialMonths = 0;

		try {
			LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());

			Period period = Period.between(birthDate, currentDate);

			allYears = period.getYears();
			partialMonths = period.getMonths();

			/*
			// Si hay dias de diferencia considerar que transcurrido 1 mes más
			if (period.getDays() > 0) {
				partialMonths += 1;
			}

			System.out.println("anhos: " + allYears);
			System.out.println("meses: " + partialMonths);
			*/

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		valores[0] = allYears;
		valores[1] = partialMonths;

		return valores;
	}
	
	public static int[] calcularEdadAnhosYMeses(Date birthDate) {
		int[] valores = new int[2];

		int allYears = 0;
		int partialMonths = 0;

		try {
			LocalDate localDatebirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			
			LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());

			Period period = Period.between(localDatebirthDate, currentDate);

			allYears = period.getYears();
			partialMonths = period.getMonths();

			/*
			// Si hay dias de diferencia considerar que transcurrido 1 mes más
			if (period.getDays() > 0) {
				partialMonths += 1;
			}
			

			System.out.println("anhos: " + allYears);
			System.out.println("meses: " + partialMonths);
			*/

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		valores[0] = allYears;
		valores[1] = partialMonths;

		return valores;
	}
	
	public static Date StringToDate(String str_date, String date_format) {
		Date fecha_rpta = null;
		try {
			fecha_rpta=new SimpleDateFormat(date_format).parse(str_date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return fecha_rpta;
	}

	public static void main(String[] args) throws ParseException {
		// LocalDate birthDate = LocalDate.of(1981, Month.MARCH, 16);
		LocalDate birthDate = LocalDate.of(2004, Month.NOVEMBER, 9);
		FR_Fecha_Hora.calcularEdadAnhosYMeses(birthDate);
		
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("09/11/2004");
		System.out.println("sDate1" + "\t"+ date1);
		int[] datos = FR_Fecha_Hora.calcularEdadAnhosYMeses(date1);
		System.out.println("anhos: " + datos[0]);
		System.out.println("meses: "  + datos[1]);
		
	}
}