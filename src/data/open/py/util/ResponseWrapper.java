package data.open.py.util;

import data.open.py.entidad.Distrito;
import data.open.py.entidad.Multa;

public class ResponseWrapper {

	Integer recordsTotal;
	Integer recordsFiltered;
	Integer draw;
	Multa[] data;
	String  categorias[];
	Distrito series [];
	

	public String[] getCategorias() {
		return categorias;
	}

	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}

	public Distrito[] getSeries() {
		return series;
	}

	public void setSeries(Distrito[] series) {
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
