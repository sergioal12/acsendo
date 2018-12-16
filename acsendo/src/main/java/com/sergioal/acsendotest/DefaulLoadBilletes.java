package com.sergioal.acsendotest;

import org.springframework.beans.factory.annotation.Autowired;

import com.sergioal.acsendotest.jpa.BilleteRepository;
import com.sergioal.acsendotest.model.Billete;
import com.sergioal.acsendotest.service.BilleteService;


public class DefaulLoadBilletes {
	@Autowired
	private BilleteService billeteService;
	public void loadBilletes() {
		Billete billete1 = new Billete();
		billete1.setDenominacion(10000);
		billete1.setCantidad(10);
		//billeteService.create(billete1);
		Billete billete2 = new Billete();
		billete2.setDenominacion(20000);
		billete2.setCantidad(10);
		//billeteService.create(billete2);
		Billete billete3 = new Billete();
		billete3.setDenominacion(20000);
		billete3.setCantidad(10);
		//billeteService.create(billete3);
		
		
	}

}
