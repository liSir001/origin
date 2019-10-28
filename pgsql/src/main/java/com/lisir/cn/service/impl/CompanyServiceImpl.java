package com.lisir.cn.service.impl;

import com.lisir.cn.dao.CompanyDao;
import com.lisir.cn.entity.Company;
import com.lisir.cn.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> list() {
        return companyDao.findAll();
    }

    @Override
    public Company findOne(Integer companyId) {
        return companyDao.findById(companyId).get();
    }

    @Override
    public Company create(Company company) {
        return companyDao.save(company);
    }

    @Override
    public Company update(Company company) {
        return companyDao.save(company);
    }

    @Override
    public void delete(Integer companyId) {
        companyDao.deleteById(companyId);
    }
}
