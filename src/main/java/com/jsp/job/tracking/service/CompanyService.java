package com.jsp.job.tracking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.job.tracking.dao.CompanyDao;
import com.jsp.job.tracking.entity.Company;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService {


    private final CompanyDao companyDao;

    public Company save(Company company) {
        return companyDao.save(company);
    }

    public Company findById(Integer id) {
        return companyDao.findById(id);
    }

    public List<Company> findAll() {
        return companyDao.findAll();
    }

    public void delete(Integer id) {
        companyDao.delete(id);
    }
    
    public boolean hasJobs(Integer companyId) {
        return companyDao.hasJobs(companyId);
    }
}
