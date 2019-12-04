package com.sly.service.company;

import com.github.pagehelper.PageInfo;
import com.sly.domain.company.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    void addCompany(Company company);

    void updateCompany(Company company);

    void delete(String id);

    Company findById(String id);

    PageInfo findByPage(Integer page, Integer pageSize);
}
