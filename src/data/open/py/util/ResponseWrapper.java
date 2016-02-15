package data.open.py.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.open.py.entidad.Multa;
import data.open.py.service.MultasService;

public class ResponseWrapper {

	Integer canReg;
	Integer canPag;
	Integer pag;
	Multa[] multas;

	public Integer getCanReg() {
		return canReg;
	}

	public void setCanReg(Integer canReg) {
		this.canReg = canReg;
	}

	public Integer getCanPag() {
		return canPag;
	}

	public void setCanPag(Integer canPag) {
		this.canPag = canPag;
	}

	public Integer getPag() {
		return pag;
	}

	public void setPag(Integer pag) {
		this.pag = pag;
	}

	public Multa[] getMultas() {
		return multas;
	}

	public void setMultas(Multa[] multas) {
		this.multas = multas;
	}

}
