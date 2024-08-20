package com.subash.api.ownrepoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.subash.api.model.Appointment;
import com.subash.api.model.CarePlan;
import com.subash.api.model.EHR;
import com.subash.api.model.PsychiatristLogin;
import com.subash.api.ownrepo.PsychiatristOwnRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PsychiatristOwnRepoImpl implements PsychiatristOwnRepo {

	EntityManager entityManager;

	public PsychiatristOwnRepoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void save(PsychiatristLogin psychiatristLogin) {
		entityManager.merge(psychiatristLogin);
	}

	@Override
	public PsychiatristLogin findById(int id) {
		return entityManager.find(PsychiatristLogin.class, id);
	}

	@Override
	public List<PsychiatristLogin> findAll() {
		return entityManager.createQuery("from PsychiatristLogin", PsychiatristLogin.class).getResultList();
	}

	@Override
	public void update(PsychiatristLogin psychiatristLogin) {
		entityManager.merge(psychiatristLogin);
	}

	@Override
	public void deleteById(int id) {
		PsychiatristLogin psychiatristLogin = entityManager.find(PsychiatristLogin.class, id);
		if (psychiatristLogin != null) {
			entityManager.remove(psychiatristLogin);
		}
	}

	@Override
	public List<Appointment> findAllAppointment(int id) {
	    // Create a JPQL query to find all appointments for a given psychiatristId with status 'scheduled'
	    return entityManager.createQuery(
	        "SELECT a FROM Appointment a WHERE a.psychiatristId = :psychiatristId AND a.status = 'scheduled'", Appointment.class)
	        .setParameter("psychiatristId", id)
	        .getResultList();
	}

	@Override
	public void save(CarePlan carePlan) {
		entityManager.merge(carePlan);
		
	}

	@Override
	public EHR saveEhr(EHR ehr) {
		// TODO Auto-generated method stub
		return entityManager.merge(ehr);
	}



}
