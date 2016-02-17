package data.open.py.util;

import java.util.Date;

public class ParamWrapper {
	
	 private  String id_multa;
	 private String codigo_sancion;
	 private String descripcion;
	 private String tipo_vehiculo;
	 private Date fecha_sancion;
	 private Date hora_sancion;
	 private Integer monto;
	 private String estado_multa;
	 private Date fecha_cobro ;
	 private String ciudad_registro_conducir ;
	 private String departamento_registro_conducir ;
	 private Integer pagina = 0;
	 private Integer limite = 10;
	 private Integer cantida_reg;
	 private Integer cantidad_pag;
	 private Integer cantidad_reg_filter;
	 private Integer draw;
	 
	 
	 
	public String getId_Multa() {
		return id_multa;
	}
	public void setId_Multa(String id_Multa) {
		this.id_multa = id_Multa;
	}
	public String getCodigo_sancion() {
		return codigo_sancion;
	}
	public void setCodigo_sancion(String codigo_sancion) {
		this.codigo_sancion = codigo_sancion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo_vehiculo() {
		return tipo_vehiculo;
	}
	public void setTipo_vehiculo(String tipo_vehiculo) {
		this.tipo_vehiculo = tipo_vehiculo;
	}
	public Date getFecha_sancion() {
		return fecha_sancion;
	}
	public void setFecha_sancion(Date fecha_sancion) {
		this.fecha_sancion = fecha_sancion;
	}
	public Date getHora_sancion() {
		return hora_sancion;
	}
	public void setHora_sancion(Date hora_sancion) {
		this.hora_sancion = hora_sancion;
	}
	public Integer getMonto() {
		return monto;
	}
	public void setMonto(Integer monto) {
		this.monto = monto;
	}
	public String getEstado_multa() {
		return estado_multa;
	}
	public void setEstado_multa(String estado_multa) {
		this.estado_multa = estado_multa;
	}
	public Date getFecha_cobro() {
		return fecha_cobro;
	}
	public void setFecha_cobro(Date fecha_cobro) {
		this.fecha_cobro = fecha_cobro;
	}
	public String getCiudad_registro_conducir() {
		return ciudad_registro_conducir;
	}
	public void setCiudad_registro_conducir(String ciudad_registro_conducir) {
		this.ciudad_registro_conducir = ciudad_registro_conducir;
	}
	public String getDepartamento_registro_conducir() {
		return departamento_registro_conducir;
	}
	public void setDepartamento_registro_conducir(
			String departamento_registro_conducir) {
		this.departamento_registro_conducir = departamento_registro_conducir;
	}
	public Integer getPagina() {
		return pagina;
	}
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
	public Integer getLimite() {
		return limite;
	}
	public void setLimite(Integer limite) {
		this.limite = limite;
	}
	public Integer getCantida_reg() {
		return cantida_reg;
	}
	public void setCantida_reg(Integer cantida_reg) {
		this.cantida_reg = cantida_reg;
	}
	public Integer getCantidad_pag() {
		return cantidad_pag;
	}
	public void setCantidad_pag(Integer cantidad_pag) {
		this.cantidad_pag = cantidad_pag;
	}
	public Integer getCantidad_reg_filter() {
		return cantidad_reg_filter;
	}
	public void setCantidad_reg_filter(Integer cantidad_reg_filter) {
		this.cantidad_reg_filter = cantidad_reg_filter;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	
	
	 
	 
}
