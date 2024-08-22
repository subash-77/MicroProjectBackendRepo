package com.subash.api.ownrepoimpl;

import org.springframework.stereotype.Repository;

import com.subash.api.model.Token;
import com.subash.api.model.User;
import com.subash.api.ownrepo.RegisterOwnRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class RegisterOwnRepoImpl implements RegisterOwnRepo {

	public RegisterOwnRepoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	EntityManager entityManager;

	@Override
	public void save(User user) {
		entityManager.persist(user);
	}

	@Override
	public User findByEmail(String email) {
		String hql = "FROM User WHERE email = :email";
		TypedQuery<User> query = entityManager.createQuery(hql, User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	@Override
	public void saveToken(Token token) {
		entityManager.persist(token);

	}

	@Override
	public String findByRandomValue(String randomValue) {
		String hql = "SELECT t.token FROM Token t WHERE t.randomValue = :randomValue";
		TypedQuery<String> query = entityManager.createQuery(hql, String.class);
		query.setParameter("randomValue", randomValue);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteTokenIfExpiried(String randomValue) {
		
		String hql = "DELETE FROM Token t WHERE t.randomValue = :randomValue";
	    Query query = entityManager.createQuery(hql);
	    query.setParameter("randomValue", randomValue);
	    query.executeUpdate();
	    
	}

	@Override
	public User findRole(String email) {
		String hql = "FROM User WHERE email = :email";
		TypedQuery<User> query = entityManager.createQuery(hql, User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	@Override
	public User findPsyId(String email) {
		String hql = "FROM User WHERE email = :email";
		TypedQuery<User> query = entityManager.createQuery(hql, User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	@Override
	public User findPatientId(String email) {
		String hql = "FROM User WHERE email = :email";
		TypedQuery<User> query = entityManager.createQuery(hql, User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

}
