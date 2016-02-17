package data.open.py.entidad;

import java.util.ArrayList;

public class Distrito {
	private String nombre;
	private ArrayList<Estado> estado = new ArrayList<>();
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Estado> getEstado() {
		return estado;
	}
	public void setEstado(ArrayList<Estado> estado) {
		this.estado = estado;
	}
	
	
}
