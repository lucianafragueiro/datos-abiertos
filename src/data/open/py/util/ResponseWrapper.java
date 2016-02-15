package data.open.py.util;

import data.open.py.entidad.Multa;

public class ResponseWrapper {

	Integer recordsTotal;
	Integer recordsFiltered;
	Integer draw;
	Multa[] data;
	

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
