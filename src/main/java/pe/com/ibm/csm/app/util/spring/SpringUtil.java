package pe.com.ibm.csm.app.util.spring;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;

import frss.util.FR_Cadenas;

public class SpringUtil {
	
	public static Object[] getArregloObjetosSegunHashMap(LinkedHashMap<String, SqlParameter> hashmap) {
		SqlParameter[] arreglo_respuesta = null;
		if (hashmap != null) {
			arreglo_respuesta = new SqlParameter[hashmap.size()];
			List<String> keys = new ArrayList<String>(hashmap.keySet());
			for (int i = 0; i < keys.size(); i++) {
				String key = keys.get(i) + FR_Cadenas.CADENA_VACIA;
				arreglo_respuesta[i] = hashmap.get(key);
			}
		}
		else {
			arreglo_respuesta = new SqlParameter[0];
		}
		
		return arreglo_respuesta;
	}
}
