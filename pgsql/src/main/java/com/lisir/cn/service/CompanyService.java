package com.lisir.cn.service;

import com.lisir.cn.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> list();

    Company findOne(Integer companyId);

    Company create(Company company);

    Company update(Company company);

    void delete(Integer companyId);

}
