<%@page import="data.open.py.util.CharWrapper"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="data.open.py.util.ResponseWrapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.open.py.util.ParamWrapper"%>
<%@page import="data.open.py.util.CommonClass"%>
<%@page import="data.open.py.entidad.Multa"%>
<%@page import="data.open.py.entidad.*"%>

<%@page import="java.util.*"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="commonClass" class="data.open.py.util.CommonClass"></jsp:useBean>
<jsp:useBean id="multaService"
	class="data.open.py.service.MultasService"></jsp:useBean>

<%
	String draw = request.getParameter("draw") != null ? request.getParameter("draw") : "0";
	String start = request.getParameter("start") != null ? request.getParameter("start") : "0";
	String length = request.getParameter("length") != null ? request.getParameter("length") : "10";
	String search = request.getParameter("search[value]") != null ? request.getParameter("search[value]") : "";
	String orderColum = request.getParameter("order[0][column]") != null ? request.getParameter("order[0][column]") : null;
	String orderDir = request.getParameter("order[0][dir]") != null ? request.getParameter("order[0][dir]") : null;
	String dpto = request.getParameter("dpto") != null ? request.getParameter("dpto") : "";

	ParamWrapper params = new ParamWrapper();

	if (search.equals(""))
		search = null;
	params.setId_Multa(search);
	params.setEstado_multa(search);
	params.setTipo_vehiculo(search);
	params.setDescripcion(search);
	params.setDepartamento_registro_conducir(search);
	params.setCiudad_registro_conducir(search);
	params.setCodigo_sancion(search);
	params.setDraw(Integer.parseInt(draw));
	params.setLimite(Integer.parseInt(length));
	params.setPagina(Integer.parseInt(start));

	StringBuffer query = multaService.getPaginationQuery(params);
	Map<String, Integer> map = CommonClass.pagination(query.toString(),
			params.getLimite());
	params.setCantidad_reg_filter(map.get("cant_reg"));
	params.setCantidad_pag(map.get("cant_pag"));

	String consulta = "SELECT COUNT(*) FROM multas_mopc ";
	map = CommonClass.pagination(consulta, params.getLimite());
	params.setCantida_reg(map.get("cant_reg"));
%>
<%
	if (dpto.equals("")) {
		ArrayList<Multa> list = multaService.getListMultas(params,orderColum,orderDir);
		ResponseWrapper respon = new ResponseWrapper();
		respon.setRecordsTotal(params.getCantida_reg());
		respon.setRecordsFiltered(params.getCantidad_reg_filter());
		respon.setDraw(params.getDraw());
		Multa[] multas = list.toArray(new Multa[list.size()]);
		respon.setData(multas);
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		out.write(mapper.writeValueAsString(respon));
	} else {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		out.write(mapper.writeValueAsString(multaService.getCharData(dpto)));
	}
%>