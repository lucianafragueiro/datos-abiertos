package data.open.py.util;

import java.util.List;
import java.util.Set;

import data.open.py.entidad.Distrito;
import data.open.py.entidad.Multa;
/**
 * 
 * @author mbenitez
 * Clase utilizada para envolver los datos enviados al cliente <i>Response</i>
 */
public class ResponseWrapper {

	Integer recordsTotal;
	Integer recordsFiltered;
	Integer draw;
	Multa[] data;
	Set<String> categorias;
	List<Distrito> series;
	

	public Set<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<String> categorias) {
		this.categorias = categorias;
	}

	public List<Distrito> getSeries() {
		return series;
	}

	public void setSeries(List<Distrito> series) {
		this.series = series;
	}

	public Multa[] getData() {
		return data;
	}

	public void setData(Multa[] data) {
		this.data = data;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}


	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	

}
