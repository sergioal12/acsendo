package com.sergioal.acsendotest.jpa;

import java.util.List;

import com.sergioal.acsendotest.model.Billete;

public interface BilleteRepository {
	public Billete create(Billete billete);
	public void delete(Billete billete);
	public List<Billete> findAll();

}
