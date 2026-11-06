package com.jsp.job.tracking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.job.tracking.dao.JobDao;
import com.jsp.job.tracking.entity.Job;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobService {

    private final JobDao jobDao;

    public Job save(Job job) {
        if (job.getPostedDate() == null) {
            job.setPostedDate(LocalDate.now());
        }
        return jobDao.save(job);
    }

    public Job findById(Integer id) {
        return jobDao.findById(id);
    }

    public List<Job> findAllActive() {
        return jobDao.findAllActive();
    }

    public List<Job> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAllActive();
        }
        return jobDao.search(keyword.trim());
    }

    public List<Job> findByCompany(Integer companyId) {
        return jobDao.findByCompany(companyId);
    }

    public void delete(Integer id) {
        jobDao.delete(id);
    }
}
