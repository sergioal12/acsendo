package com.sergioal.acsendotest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Billete {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private int denominacion;
	
	@Column(nullable=false)
	private int cantidad;
	
	public Billete() {
		super();
		this.id = null;
		this.denominacion = 0;
		this.cantidad = 0;
	}
	
	public Billete(int cantidad, int denominacion) {
		this.id = null;
		this.denominacion = denominacion;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(int denominacion) {
		this.denominacion = denominacion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
