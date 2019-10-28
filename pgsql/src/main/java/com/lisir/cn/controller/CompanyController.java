package com.lisir.cn.controller;

import com.lisir.cn.entity.Company;
import com.lisir.cn.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping
    public List<Company> list() {
        return companyService.list();
    }

    @GetMapping("{companyId}")
    public Company findOne(@PathVariable("companyId") Integer companyId) {
        return companyService.findOne(companyId);
    }

    /**
     * 新增
     *
     * @param company
     * @return
     */
    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyService.create(company);
    }

    /**
     * 更新
     *
     * @param company
     * @return
     */
    @PutMapping
    public Company updateCompany(@RequestBody Company company) {
        return companyService.update(company);
    }

    /**
     * 删除
     *
     * @param companyId
     */
    @DeleteMapping("{companyId}")
    public void deleteCompany(@PathVariable("companyId") Integer companyId) {
        companyService.delete(companyId);
    }
}