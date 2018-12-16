package com.sergioal.acsendotest.service;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sergioal.acsendotest.jpa.BilleteRepository;
import com.sergioal.acsendotest.model.Billete;

@Service("billeteService")
public class BilleteServiceImpl implements BilleteService{
	@Autowired
	private BilleteRepository billeteRepository;
	
	@Override
	@Transactional
	public Billete create(int cantidad, int denominacion) {
		// TODO Auto-generated method stub
		Billete billete = new Billete();
		billete.setCantidad(cantidad);
		billete.setDenominacion(denominacion);
		return this.billeteRepository.create(billete);
	}

	@Override
	@Transactional
	public void delete(Billete billete) throws Exception{
		if(billete.getId()==null)
			throw new Exception("no existe ese billete");
		this.billeteRepository.delete(billete);
		
	}

	@Override
	@Transactional
	public List<Billete> findAll() {
		return this.billeteRepository.findAll();
	}

}
