package data.open.py.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import data.open.py.connection.Conexion;
import data.open.py.entidad.Distrito;
import data.open.py.entidad.Estado;
import data.open.py.entidad.Multa;
import data.open.py.util.CharWrapper;
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
			System.out.println("sql: "+buffer.toString());
			result = statm.executeQuery(buffer.toString());
			
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
				multa.setCiudadRegistroConducir(result.getString(10));
				multa.setDepartamentoRegistroConducir(result.getString(11));
				multa.setDestacamento(result.getString(12));
				
				multas.add(multa);
				
			}
			
		}catch(Exception e){
			
		}
		
		return multas;
	}
	
	public StringBuffer getPaginationQuery(ParamWrapper params){
		StringBuffer query = new StringBuffer();
		query.append("SELECT COUNT(*) FROM multas_mopc ");
		String where = CommonClass.getFilterFromParams(params);
		ArrayList<Multa> multas = new ArrayList<>();
		
		if(!where.equals("")){
			query.append(" where ");
			query.append(where);
		}
		System.out.println("sql: "+query.toString());
		return query;
	}
	
	public CharWrapper getCharData(String dpto){
		System.out.println("getCharData");
		String query = ""
				+ " SELECT (SELECT coalesce (COUNT(estado_multa),0) as cantidad "
				+ " FROM multas_mopc "
				+ " WHERE unaccent(ciudad_registro_conducir) = upper(unaccent(?)) "
				+ " and estado_multa = 'Pendiente' "
				+ " group by estado_multa "
				+ " order by cantidad desc) as pendiente, "
				+ " (SELECT  coalesce(COUNT(estado_multa),0) as cantidad "
				+ " FROM multas_mopc   "
				+ " WHERE unaccent(ciudad_registro_conducir) = upper(unaccent(?)) "
				+ " and estado_multa = 'Pagado' "
				+ " group by estado_multa "
				+ " order by cantidad desc) as pagado, "
				+ " (SELECT  coalesce (COUNT(estado_multa),0) as cantidad "
				+ " FROM multas_mopc "
				+ " WHERE unaccent(ciudad_registro_conducir) = upper(unaccent(?)) "
				+ " and estado_multa = 'Amonestado' "
				+ " group by estado_multa "
				+ " order by cantidad desc) as amonestado";
		String query1 = ""
						+ " SELECT  departamento_registro_conducir, "
						+ " ciudad_registro_conducir,"
						+ " COUNT(ciudad_registro_conducir) as cantidad "
						+ " FROM multas_mopc "
						+ " WHERE departamento_registro_conducir not ilike '%DEL SUR%' "
						+ " and unaccent(departamento_registro_conducir) = upper(unaccent(?))  "
						+ " GROUP BY departamento_registro_conducir,ciudad_registro_conducir  "
						+ " order by 1,2 desc";
		PreparedStatement preSta;
		PreparedStatement preSta1;
		CharWrapper toRet = new CharWrapper();
		ArrayList<String> categorias =  new ArrayList<String>();
		try {
			
			preSta = Conexion.getConnection().prepareStatement(query1);
			preSta1 = Conexion.getConnection().prepareStatement(query);
			preSta.setString(1,dpto);
			ResultSet rs = preSta.executeQuery();
			ResultSet rs1;
						
			while(rs.next()){
				
				Distrito dist = new Distrito();
				dist.setNombre(rs.getString(2));
				categorias.add(rs.getString(2));
				
				preSta1.setString(1, rs.getString(2));
				preSta1.setString(2, rs.getString(2));
				preSta1.setString(3, rs.getString(2));
				rs1 = preSta1.executeQuery();
				rs1.next();
				
				Estado pagado = new Estado();
				pagado.setName("Pagado");
				pagado.setData(rs1.getInt(1));
				dist.getEstado().add(pagado);
				
				Estado pendiente = new Estado();
				pendiente.setName("Pendiente");
				pendiente.setData(rs1.getInt(2));
				dist.getEstado().add(pendiente);
				
				Estado amonestado = new Estado();
				amonestado.setName("Amonestado");
				amonestado.setData(rs1.getInt(3));
				dist.getEstado().add(amonestado);
				toRet.getSeries().add(dist);
				preSta1.clearParameters();
			}
			
			toRet.setCategorias(categorias.toArray(new String[categorias.size()]));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return toRet;
	}
	
	public static void main(String agr[]){
		
		MultasService m = new MultasService();
		CharWrapper chars = m.getCharData("Cen");
		for(String cate : chars.getCategorias()){
			System.out.println(cate);
		}
		
		
	}

}
