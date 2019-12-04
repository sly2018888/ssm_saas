package com.sly.dao.system;

import com.sly.domain.system.Dept;

import java.util.List;

public interface DeptDao {

    List<Dept> findAll(String companyId);

    List<Dept> findByDeptId(String companyId);

    void addDept(Dept dept);

    void updateDept(Dept dept);

    Dept findById(String id);

    void delete(String deptId);
}
