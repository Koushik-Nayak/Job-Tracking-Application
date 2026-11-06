package com.jsp.job.tracking.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsp.job.tracking.entity.ApplicationStatus;
import com.jsp.job.tracking.entity.JobApplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class JobApplicationDao {

	private final EntityManager entityManager;

	public JobApplication save(JobApplication application) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			if (application.getId() == null) {
				entityManager.persist(application);
			} else {
				application = entityManager.merge(application);
			}

			transaction.commit();
			return application;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public JobApplication findById(Integer id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			JobApplication jobApplication = entityManager.find(JobApplication.class, id);
			transaction.commit();
			return jobApplication;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public JobApplication findByUserAndJob(Integer userId, Integer jobId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			JobApplication application;
			try {
				Query query = entityManager
						.createQuery("select j from JobApplication j where j.user.id = :userId and j.job.id = :jobId");
				query.setParameter("userId", userId);
				query.setParameter("jobId", jobId);
				application = (JobApplication) query.getSingleResult();
			} catch (Exception e) {
				application = null;
			}

			transaction.commit();
			return application;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public List<JobApplication> findByUser(Integer userId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			Query query = entityManager.createQuery("select j from JobApplication j where j.user.id = : id");
			query.setParameter("id", userId);
			List<JobApplication> list = query.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public List<JobApplication> findByUserAndStatus(Integer userId, ApplicationStatus status) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Query query = entityManager
					.createQuery("select j from JobApplication j where j.user.id = :id and j.status = : status");
			query.setParameter("id", userId);
			query.setParameter("status", status);
			List<JobApplication> list = query.getResultList();

			transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public List<JobApplication> findByJob(Integer jobId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Query query = entityManager.createQuery("select j from JobApplication j where j.job.id = : id");
			query.setParameter("id", jobId);
			List<JobApplication> list = query.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public Long countByUser(Integer userId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			Query query = entityManager.createQuery("select count(j) from JobApplication j where j.user.id = :id");
			query.setParameter("id", userId);
			Long count = (Long) query.getSingleResult();

			transaction.commit();
			return count;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public Long countByUserAndStatus(Integer userId, ApplicationStatus status) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			Query query = entityManager.createQuery("select count(j) from JobApplication j where j.user.id = :id and j.status=:status");
			query.setParameter("id", userId);
			query.setParameter("status", status);
			Long count = (Long) query.getSingleResult();
			transaction.commit();
			return count;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}
}
