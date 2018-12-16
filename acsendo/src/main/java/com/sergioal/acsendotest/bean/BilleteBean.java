package com.sergioal.acsendotest.bean;

import java.util.List;

import javax.faces.view.ViewScoped;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sergioal.acsendotest.model.Billete;
import com.sergioal.acsendotest.service.BilleteService;

@Component
@ViewScoped
public class BilleteBean   {
	
	@Autowired
	private BilleteService billeteService;
	@Autowired
	private CalcularCambio calCambio;
	
	private List<Billete> billetes;
	private Billete billete = new Billete();
	private static long id = -1;
	
	private int monto;
	private int denominacion;
	private int cantidad;
	
	

	
	

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

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public void create() {
		this.billeteService.create(this.cantidad, this.denominacion);
	}
	
	public void delete(Billete billete) {
		try {
			this.billeteService.delete(billete);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Billete> getBilletes(){
		return this.billeteService.findAll();
	}
	
	public void test() {
		List<Billete> billetes1 = this.billeteService.findAll();
		System.out.println("probando sin funciona pasar el dato asi: "+billetes1.get(0).getCantidad());
		System.out.println("probando sin funciona pasar el dato asi: "+billetes1.get(1).getCantidad());
		calCambio.calcularCambio(this.monto, billetes1);
		
		//System.out.println("esto es billete: "+this.billete.getCantidad() +"esto es monto: "+this.billete.getDenominacion());
	}
	
	
	
}
