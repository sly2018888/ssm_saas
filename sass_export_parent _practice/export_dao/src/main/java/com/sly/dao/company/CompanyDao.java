package com.sly.dao.company;

import com.sly.domain.company.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyDao {

    List<Company> findAll();

    void addCompany(Company company);

    void updateCompany(Company company);

    void delete(String id);

    Company findById(String id);

    List<Company> findByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    Long findCount();
}
