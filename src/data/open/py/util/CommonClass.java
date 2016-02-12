package data.open.py.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import data.open.py.connection.Conexion;

public class CommonClass {

	public static String getFilterFromParams(ParamWrapper params) {
		
		String toRet = "";
		StringBuffer buffer = new StringBuffer();
		String pack = params.getClass().getName();
		Class objetoInstance;
		
		try {
			objetoInstance = Class.forName(pack);
			Field[] properties = objetoInstance.getDeclaredFields();
			for (Field field : properties) {
				String nombreCampo = field.getName();
				String tipoCampo = field.getType().getName();
				field.setAccessible(true);
				if(field.get(params) != null && !nombreCampo.equals("pagina") 
						&& !nombreCampo.equals("limite")
						&& !nombreCampo.equals("cantida_reg")
						&& !nombreCampo.equals("cantidad_pag")
						){
					switch (tipoCampo) {
					case "java.lang.String":
						buffer.append(nombreCampo + " ilike "+ "'%"+field.get(params)+"%' and ");
						break;

					case "java.util.Date":
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String date = sdf.format(field.get(params));
						buffer.append(nombreCampo + " to_date("+ "'"+date+"','YYYY-MM-DD') and ");
						break;
					default :
						buffer.append(nombreCampo + " = "+ "'"+field.get(params)+"' and ");
						break;

					}
				}
			}
			int index = buffer.lastIndexOf("and");
			if(index != -1){
				toRet = buffer.substring(0,index);
			}
						
						
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toRet;
	}

	public static Map<String, Integer> pagination(String query, Integer limite) {
		Map<String, Integer> paginacion = new HashMap<>();
		ResultSet result = Conexion.getResultByQuery(query);
		Integer cantidadReg = 0;
		Integer cantidadPag = 0;
		try {
			if (result.next())
				cantidadReg = result.getInt(1);
			cantidadPag = (cantidadReg % limite) == 0 ? (cantidadReg / limite)
					: (cantidadReg / limite) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		paginacion.put("cant_reg", cantidadReg);
		paginacion.put("cant_pag", cantidadPag);

		return paginacion;

	}

}
