package com.jsp.job.tracking.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsp.job.tracking.entity.Job;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class JobDao {

	private final EntityManager entityManager;

	public Job save(Job job) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			if (job.getId() == null) {
				entityManager.persist(job);
			} else {
				job = entityManager.merge(job);
			}

			transaction.commit();
			return job;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public Job findById(Integer id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Job job = entityManager.find(Job.class, id);
			transaction.commit();
			return job;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public List<Job> findAllActive() {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			Query query = entityManager.createQuery("select j from Job j where j.active = true order by j.postedDate desc");
			List<Job> list = query.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public List<Job> search(String keyword) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			Query query = entityManager.createQuery("select j from Job j where j.active=true and (j.location like : keyword or j.company.companyName like : keyword or j.jobTitle like : keyword)"); 
			query.setParameter("keyword", "%" + keyword + "%");
			List<Job> list = query.getResultList();
            transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public List<Job> findByCompany(Integer companyId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			Query query = entityManager.createQuery("select j from Job j where j.company.id = :id");
			query.setParameter("id", companyId);
			List<Job> list = query.getResultList();
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

			Job job = entityManager.find(Job.class, id);
			if (job != null) {
				job.setActive(false);
				entityManager.merge(job);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}
}
