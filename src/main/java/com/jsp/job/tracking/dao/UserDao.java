package com.jsp.job.tracking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.job.tracking.dao.UserDao;
import com.jsp.job.tracking.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserDao {

	@Autowired
	private final EntityManager entityManager;

	public User save(User user) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			if (user.getId() == null) {
				entityManager.persist(user);
			} else {
				user = entityManager.merge(user);
			}

			transaction.commit();
			return user;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public User findByEmail(String email) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			String jpql = "SELECT u FROM User u WHERE u.email = :email";
			User user;

			try {
				Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
				query.setParameter("email", email);
				user = (User) query.getSingleResult();
			} catch (Exception e) {
				user = null;
			}

			transaction.commit();
			return user;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public User findById(Integer id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			User user = entityManager.find(User.class, id);
			transaction.commit();
			return user;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}
}
