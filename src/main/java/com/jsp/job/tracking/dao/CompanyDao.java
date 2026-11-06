package com.jsp.job.tracking.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsp.job.tracking.entity.Company;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CompanyDao {

	private final EntityManager entityManager;

	public Company save(Company company) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			if (company.getId() == null) {
				entityManager.persist(company);
			} else {
				company = entityManager.merge(company);
			}

			transaction.commit();
			return company;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public Company findById(Integer id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Company company = entityManager.find(Company.class, id);
			transaction.commit();
			return company;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public List<Company> findAll() {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Query query = entityManager.createQuery("SELECT c FROM Company c ORDER BY c.companyName");
			List<Company> list = query.getResultList();
            transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public void delete(Integer id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			Company company = entityManager.find(Company.class, id);
			if (company != null) {
				entityManager.remove(company);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}
	
	public boolean hasJobs(Integer companyId) {
	    EntityTransaction transaction = entityManager.getTransaction();

	    try {
	        transaction.begin();

	        Query query = entityManager.createQuery(
	            "select count(j) from Job j where j.company.id = :id"
	        );

	        query.setParameter("id", companyId);

	        Long count = (Long) query.getSingleResult();

	        transaction.commit();

	        return count > 0;

	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw e;
	    }
	}
}
