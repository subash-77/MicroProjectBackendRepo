package com.subash.api.ownrepoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;


import com.subash.api.model.CarePlan;
import com.subash.api.model.EHR;
import com.subash.api.model.User;
import com.subash.api.ownrepo.PatientOwnRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PatientOwnRepoImpl implements PatientOwnRepo {

	EntityManager entityManager;

	public PatientOwnRepoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void save(User user) {
		entityManager.merge(user);
	}

	@Override
	public User findById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public void deleteById(int id) {
		User user = entityManager.find(User.class, id);
		if (user != null) {
			entityManager.remove(user);
		}
	}

	@Override
    public List<User> findAllAppointment(int userId) {
        String jpql = "SELECT u FROM User u JOIN u.appointment a WHERE u.userId = :userId";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

	@Override
	public List<CarePlan> findAllCarePlan(int patientId) {
	    String jpql = "SELECT c FROM CarePlan c WHERE c.patientId = :patientId";
	    TypedQuery<CarePlan> query = entityManager.createQuery(jpql, CarePlan.class);
	    query.setParameter("patientId", patientId);
	    return query.getResultList();
	}

	@Override
	public void updateCarePlan(CarePlan careplan) {
		entityManager.merge(careplan);
	}
	
	public List<EHR> getEHRsByPatientId(String patientId) {
        TypedQuery<EHR> query = entityManager.createQuery(
            "SELECT e FROM EHR e WHERE e.patientId = :patientId", EHR.class);
        query.setParameter("patientId", patientId);
        return query.getResultList();
    }

}
