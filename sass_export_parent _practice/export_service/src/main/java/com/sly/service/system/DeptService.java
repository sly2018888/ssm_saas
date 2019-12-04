package com.sly.service.system;

import com.github.pagehelper.PageInfo;
import com.sly.domain.system.Dept;

import java.util.List;

public interface DeptService {
    PageInfo findAll(Integer page, Integer pageSize, String companyId);

    List<Dept> findByDeptId(String companyId);

    void addDept(Dept dept);

    void updateDept(Dept dept);

    Dept findById(String id);

    List<Dept> findAllDept(String companyId);

    void delete(String deptId);
}
