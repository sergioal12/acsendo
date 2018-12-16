package com.sergioal.acsendotest.service;

import java.util.List;

import com.sergioal.acsendotest.model.Billete;

public interface BilleteService {
	public Billete create(int cantidad, int denominacion);
	public void delete(Billete billete) throws Exception;
	public List<Billete> findAll();
}
