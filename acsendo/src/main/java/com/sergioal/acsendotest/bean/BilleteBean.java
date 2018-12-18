package com.sergioal.acsendotest.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sergioal.acsendotest.model.Billete;
import com.sergioal.acsendotest.service.BilleteService;

@Named
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
		
		
		try {
			List<Billete> billetes1 = null;
			 List<int[]>valores;
			billetes1 = this.billeteService.findAll();
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
					
					sumatoria = sumatoria + elem[1];
					mensaje = mensaje + " denominacion: "+elem[3] + " cantidad: "+ elem[0] + " ";
				}
				
				int valFinal = this.monto - sumatoria;
				
				String message1 = "se entrega"+mensaje+" la cantidad: "+valFinal +" no pudo ser entregada";
				 
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message1));
			}
		}catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "no hay billetes disponibles"));
	    }
			//calCambio.calcularCambio(this.monto, billetes1);
		
		
		
		
		
	}
	
	
	
	
	
}
