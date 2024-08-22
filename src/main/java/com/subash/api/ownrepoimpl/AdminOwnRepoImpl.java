package com.subash.api.ownrepoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.subash.api.model.Appointment;
import com.subash.api.model.User;
import com.subash.api.ownrepo.AdminOwnRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AdminOwnRepoImpl implements AdminOwnRepo {

	EntityManager entityManager;

	public AdminOwnRepoImpl(EntityManager entityManager) {
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
	public List<Appointment> findAllAppointment() {
		return entityManager.createQuery("from Appointment", Appointment.class).getResultList();
	}

	@Override
	public void saveAppointment(Appointment appointment) {
		
		entityManager.merge(appointment);
	}

	@Override
	public List<User> findAllPatients() {
	    return entityManager.createQuery(
	        "SELECT u FROM User u WHERE u.role = :role", User.class)
	        .setParameter("role", "patient")
	        .getResultList();
	}

	@Override
	public String getPatientEmail(int id) {
        String hql = "SELECT u.email FROM User u JOIN u.appointment a WHERE a.patientInfo.patientinfoId = :patientinfoId";

        TypedQuery<String> query = entityManager.createQuery(hql, String.class);
        query.setParameter("patientinfoId", id);

       
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
	


}
