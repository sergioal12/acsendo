package com.sergioal.acsendotest.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
	private String message = " ";
	

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
		List<Billete> billetes1 = null;
		 List<int[]>valores;
		try {
			billetes1 = this.billeteService.findAll();
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "no hay billetes disponibles"));
	    }
			//calCambio.calcularCambio(this.monto, billetes1);
		
		valores = calCambio.calcularCambio(this.monto, billetes1);
		int[] valValores = new int[3];
		valValores = valores.get(0);
		if(valValores[0] == -2) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "el valor ingresado es menor al billete mas pequeño."));
		}else if(valValores[0] == -1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "no se puede entregar la cantidad solicitada"));
		}else if(valValores[0]>0) {
			int sumatoria =0;
			String mensaje = " ";
			for(int[] elem: valores) {
				System.out.println("esto es elem en front: "+elem[0]+" esto es elem en 2: "+elem[1]+" esto es elem en 3: "+elem[2]+" esto es elem en 4: "+elem[3]);
				sumatoria = sumatoria + elem[4];
				mensaje = mensaje + " denominacion: "+elem[1] + " cantidad: "+ elem[0] + " ";
			}
			int valFinal = this.monto - sumatoria;
			
			
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "se entrega"+mensaje+" la cantidad: "+valFinal +" no pudo ser entregada"));
		}
		
		
		
	}
	
	
	
	
	
}
