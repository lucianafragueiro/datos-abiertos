package data.open.py.entidad;

import java.util.Date;

public class Multa {
	
	 private  String idMulta;
	 private String codigoSancion;
	 private String descripcion;
	 private String tipoVehiculo;
	 private Date fechaSancion;
	 private String horaSancion;
	 private Integer monto;
	 private String estadoMulta;
	 private Date fechaCobro ;
	 private String documentoIdentidad ;
	 private String conductor ;
	 private String nroChapa ;
	 private String nroRegistro ;
	 private String ciudadRegistroConducir ;
	 private String departamentoRegistroConducir ;
	 
	 private String destacamento;
	public String getIdMulta() {
		return idMulta;
	}
	public void setIdMulta(String idMulta) {
		this.idMulta = idMulta;
	}
	public String getCodigoSancion() {
		return codigoSancion;
	}
	public void setCodigoSancion(String codigoSancion) {
		this.codigoSancion = codigoSancion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public Date getFechaSancion() {
		return fechaSancion;
	}
	public void setFechaSancion(Date fechaSancion) {
		this.fechaSancion = fechaSancion;
	}
	public String getHoraSancion() {
		return horaSancion;
	}
	public void setHoraSancion(String horaSancion) {
		this.horaSancion = horaSancion;
	}
	public Integer getMonto() {
		return monto;
	}
	public void setMonto(Integer monto) {
		this.monto = monto;
	}
	public String getEstadoMulta() {
		return estadoMulta;
	}
	public void setEstadoMulta(String estadoMulta) {
		this.estadoMulta = estadoMulta;
	}
	public Date getFechaCobro() {
		return fechaCobro;
	}
	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	public String getConductor() {
		return conductor;
	}
	public void setConductor(String conductor) {
		this.conductor = conductor;
	}
	public String getNroChapa() {
		return nroChapa;
	}
	public void setNroChapa(String nroChapa) {
		this.nroChapa = nroChapa;
	}
	public String getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	public String getCiudadRegistroConducir() {
		return ciudadRegistroConducir;
	}
	public void setCiudadRegistroConducir(String ciudadRegistroConducir) {
		this.ciudadRegistroConducir = ciudadRegistroConducir;
	}
	public String getDepartamentoRegistroConducir() {
		return departamentoRegistroConducir;
	}
	public void setDepartamentoRegistroConducir(String departamentoRegistroConducir) {
		this.departamentoRegistroConducir = departamentoRegistroConducir;
	}
	public String getDestacamento() {
		return destacamento;
	}
	public void setDestacamento(String destacamento) {
		this.destacamento = destacamento;
	}
	 
	 
}
