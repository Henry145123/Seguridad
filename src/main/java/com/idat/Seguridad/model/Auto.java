package com.idat.Seguridad.model;

import javax.persistence.*;

@Entity
@Table(name = "autos")
public class Auto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAuto;
	
	private String nombreAuto;
	
	private String modelo;

	public Integer getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(Integer idAuto) {
		this.idAuto = idAuto;
	}

	public String getNombreAuto() {
		return nombreAuto;
	}

	public void setNombreAuto(String nombreAuto) {
		this.nombreAuto = nombreAuto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	

}
