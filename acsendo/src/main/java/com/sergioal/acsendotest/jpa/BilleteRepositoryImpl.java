package com.sergioal.acsendotest.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sergioal.acsendotest.model.Billete;

@Repository("billeteRepository")
public class BilleteRepositoryImpl implements BilleteRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Billete create(Billete billete) {
		
		if(billete.getId() == null) {
			this.em.persist(billete);
			this.em.flush();
			this.em.refresh(billete);
		}else {
			return null;
		}
		return billete;
	}

	@Override
	@Transactional
	public void delete(Billete billete) {
		if(!this.em.contains(billete)) {
			billete = this.em.merge(billete);
		}
		this.em.remove(billete);
	}

	@Override
	@Transactional
	public List<Billete> findAll() {
		
		return this.em.createQuery("from Billete", Billete.class).getResultList();
	}

}
