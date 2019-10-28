package com.lisir.cn.dao;

import com.lisir.cn.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company, Integer> {
}
