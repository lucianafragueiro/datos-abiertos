package data.open.py.util;

import java.util.ArrayList;

import data.open.py.entidad.Distrito;

public class CharWrapper {
	
	private String categorias[];
	private ArrayList<Distrito> series = new ArrayList<>();
	
	public String[] getCategorias() {
		return categorias;
	}
	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}
	public ArrayList<Distrito> getSeries() {
		return series;
	}
	public void setSeries(ArrayList<Distrito> series) {
		this.series = series;
	}
	
	

}
