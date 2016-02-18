package data.open.py.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.open.py.connection.Conexion;
import data.open.py.entidad.Distrito;
import data.open.py.entidad.Estado;
import data.open.py.entidad.Multa;
import data.open.py.util.CharWrapper;
import data.open.py.util.CommonClass;
import data.open.py.util.ParamWrapper;
import data.open.py.util.ResponseWrapper;

public class MultasService {
	private StringBuffer buffer;
	private ArrayList<Multa> multas;

	public MultasService() {
		buffer = new StringBuffer();
		multas = new ArrayList<>();

		buffer.append("SELECT " + " id_multa, " + " codigo_sancion, "
				+ " descripcion, " + " tipo_vehiculo, " + " fecha_sancion, "
				+ " hora_sancion, " + " monto, " + " estado_multa, "
				+ " fecha_cobro, " + " ciudad_registro_conducir,"
				+ " departamento_registro_conducir, " + " destacamento "
				+ " FROM multas_mopc ");
	}

	public ArrayList<Multa> getListMultas(ParamWrapper params) {
		Statement statm;
		ResultSet result;
		String where = CommonClass.getFilterFromParams(params);
		ArrayList<Multa> multas = new ArrayList<>();

		if (!where.equals("")) {
			buffer.append(" where ");
			buffer.append(where);
		}

		buffer.append(" order by id_multa desc");
		buffer.append(" limit " + params.getLimite());
		buffer.append(" offset " + params.getPagina());

		try {

			statm = Conexion.getConnection().createStatement();
			System.out.println("sql: " + buffer.toString());
			result = statm.executeQuery(buffer.toString());

			while (result.next()) {
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

		} catch (Exception e) {

		}

		return multas;
	}

	public StringBuffer getPaginationQuery(ParamWrapper params) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT COUNT(*) FROM multas_mopc ");
		String where = CommonClass.getFilterFromParams(params);
		ArrayList<Multa> multas = new ArrayList<>();

		if (!where.equals("")) {
			query.append(" where ");
			query.append(where);
		}
		System.out.println("sql: " + query.toString());
		return query;
	}

	public ResponseWrapper getCharData(String dpto) {

		String query1 = ""
				+ " SELECT  "
				+ "			departamento_registro_conducir,"
				+ " 		ciudad_registro_conducir,"
				+ "			estado_multa, "
				+ " 		COUNT(ciudad_registro_conducir) as cantidad "
				+ " FROM multas_mopc "
				+ " WHERE departamento_registro_conducir not ilike '%DEL SUR%' "
				+ " and unaccent(departamento_registro_conducir) = upper(unaccent(?)) "
				+ " GROUP BY departamento_registro_conducir,ciudad_registro_conducir ,estado_multa "
				+ " order by 1,2 desc";
		PreparedStatement preSta;
		// PreparedStatement preSta1;
		List<Registro> registros = new ArrayList<>();
		try {

			preSta = Conexion.getConnection().prepareStatement(query1);
			// preSta1 = Conexion.getConnection().prepareStatement(query);
			preSta.setString(1, dpto);
			ResultSet rs = preSta.executeQuery();
			while (rs.next()) {
				Registro t = new Registro();
				t.departamento = rs.getString("departamento_registro_conducir");
				t.ciudad = rs.getString("ciudad_registro_conducir");
				t.estado = rs.getString("estado_multa");
				t.cantidad = rs.getInt("cantidad");
				registros.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseWrapper rw = new ResponseWrapper();
		Set<String> categorias = new HashSet<String>();
		for (Registro r : registros) {
			categorias.add(r.ciudad);
		}
		rw.setCategorias(categorias);
		Map<String, Distrito> distritos = new HashMap<String, Distrito>();

		for (Registro r : registros) {
			Distrito actual;
			if (distritos.containsKey(r.ciudad)) {
				actual = distritos.get(r.ciudad);
			} else {
				actual = new Distrito(r.ciudad);
				distritos.put(r.ciudad, actual);
			}
			Estado e = new Estado();
			e.setName(r.estado);
			e.setData(r.cantidad);
			actual.getEstado().add(e);
		}
		
		rw.setSeries(new ArrayList<Distrito>(distritos.values()));

		return rw;
	}

	public static void main(String agr[]) {

		MultasService m = new MultasService();
		// CharWrapper chars = m.getCharData("Cen");
		// for (String cate : chars.getCategorias()) {
		// System.out.println(cate);
		// }

	}

	public static class Registro {
		String departamento;
		String ciudad;
		String estado;
		Integer cantidad;
	}
}
