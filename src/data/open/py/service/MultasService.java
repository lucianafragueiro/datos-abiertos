package data.open.py.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import data.open.py.connection.Conexion;
import data.open.py.entidad.Multa;
import data.open.py.util.CommonClass;
import data.open.py.util.ParamWrapper;

public class MultasService {
	private StringBuffer buffer;
	private ArrayList<Multa> multas;
	
	public MultasService(){
		buffer = new StringBuffer();
		multas = new ArrayList<>();
		
		buffer.append("SELECT "
							+ " id_multa, "
							+ " codigo_sancion, "
							+ " descripcion, "
							+ " tipo_vehiculo, "
							+ " fecha_sancion, "
							+ " hora_sancion, "
							+ " monto, "
							+ " estado_multa, "
							+ " fecha_cobro, "
							+ " documento_identidad, "
							+ " conductor, "
							+ " nro_chapa, "
							+ " nro_registro, "
							+ " ciudad_registro_conducir,"
							+ " departamento_registro_conducir, "
							+ " destacamento "
						+ " FROM multas_mopc ");
	}
	
	public ArrayList<Multa> getListMultas(ParamWrapper params){
		Statement statm;
		ResultSet result;
		String where = CommonClass.getFilterFromParams(params);
		ArrayList<Multa> multas = new ArrayList<>();
		
		if(!where.equals("")){
			buffer.append(" where ");
			buffer.append(where);
		}
		
		buffer.append(" order by id_multa desc");
		buffer.append(" limit "+params.getLimite());
		buffer.append(" offset "+params.getPagina());
		
		try{
			
			statm = Conexion.getConnection().createStatement();
			result = statm.executeQuery(buffer.toString());
			buffer.append("SELECT "
					+ " id_multa, "
					+ " codigo_sancion, "
					+ " descripcion, "
					+ " tipo_vehiculo, "
					+ " fecha_sancion, "
					+ " hora_sancion, "
					+ " monto, "
					+ " estado_multa, "
					+ " fecha_cobro, "
					+ " documento_identidad, "
					+ " conductor, "
					+ " nro_chapa, "
					+ " nro_registro, "
					+ " ciudad_registro_conducir,"
					+ " departamento_registro_conducir, "
					+ " destacamento "
				+ " FROM multas_mopc ");
			
			while(result.next()){
				Multa multa = new Multa();
				multa.setIdMulta(result.getString(1));
				multa.setCodigoSancion(result.getString(2));
				multa.setDescripcion(result.getString(3));
				multa.setTipoVehiculo(result.getString(4));
				multa.setFechaSancion(result.getDate(5));
				multa.setHoraSancion(result.getString(6));
				multa.setMonto(result.getInt(7));
				multa.setEstadoMulta(result.getString(8));
				multa.setFechaCobro(result.getDate(9));
				multa.setDocumentoIdentidad(result.getString(10));
				multa.setConductor(result.getString(11));
				multa.setNroChapa(result.getString(12));
				multa.setNroRegistro(result.getString(13));
				multa.setCiudadRegistroConducir(result.getString(14));
				multa.setDepartamentoRegistroConducir(result.getString(15));
				multa.setDestacamento(result.getString(16));
				
				multas.add(multa);
				
			}
			
		}catch(Exception e){
			
		}
		
		return multas;
	}

}
