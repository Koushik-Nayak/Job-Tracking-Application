package com.jsp.job.tracking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.job.tracking.dao.JobApplicationDao;
import com.jsp.job.tracking.entity.ApplicationStatus;
import com.jsp.job.tracking.entity.Job;
import com.jsp.job.tracking.entity.JobApplication;
import com.jsp.job.tracking.entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobApplicationService {

    private final JobApplicationDao applicationDao;
    private final JobService jobService;
    private final UserService userService;

    public JobApplication apply(Integer userId, Integer jobId) {

        JobApplication existing =
                applicationDao.findByUserAndJob(userId, jobId);

        if (existing != null) {
            return null;
        }

        User user = userService.findById(userId);
        Job job = jobService.findById(jobId);

        if (user == null || job == null || !job.isActive()) {
            return null;
        }

        JobApplication application = new JobApplication();
        application.setUser(user);
        application.setJob(job);
        application.setAppliedDate(LocalDate.now());
        application.setStatus(ApplicationStatus.APPLIED);
        application.setLastUpdated(LocalDateTime.now());

        return applicationDao.save(application);
    }

    public JobApplication findById(Integer id) {
        return applicationDao.findById(id);
    }

    public List<JobApplication> findByUser(Integer userId) {
        return applicationDao.findByUser(userId);
    }

    public List<JobApplication> findByUserAndStatus(
            Integer userId, ApplicationStatus status) {
        return applicationDao.findByUserAndStatus(userId, status);
    }

    public List<JobApplication> findByJob(Integer jobId) {
        return applicationDao.findByJob(jobId);
    }

    public boolean updateStatus(Integer applicationId,
                                ApplicationStatus status,
                                LocalDate interviewDate,
                                String interviewTime,
                                String notes) {

        JobApplication application = applicationDao.findById(applicationId);

        if (application == null) {
            return false;
        }

        application.setStatus(status);
        application.setInterviewDate(interviewDate);
        application.setInterviewTime(interviewTime);
        application.setNotes(notes);
        application.setLastUpdated(LocalDateTime.now());

        applicationDao.save(application);
        return true;
    }

    public Long countByUser(Integer userId) {
        return applicationDao.countByUser(userId);
    }

    public Long countByUserAndStatus(Integer userId, ApplicationStatus status) {
        return applicationDao.countByUserAndStatus(userId, status);
    }
}
