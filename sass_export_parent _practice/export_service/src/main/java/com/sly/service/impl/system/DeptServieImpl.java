package com.sly.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.dao.system.DeptDao;
import com.sly.domain.system.Dept;
import com.sly.service.system.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServieImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;


    @Override
    public PageInfo<Dept> findAll(Integer page,Integer pageSize,String companyId) {
        PageHelper.startPage(page,pageSize);
        List<Dept> all = deptDao.findAll(companyId);
        return new PageInfo<Dept>(all,10);
    }

    @Override
    public List<Dept> findByDeptId(String companyId) {
        return deptDao.findByDeptId(companyId);
    }

    @Override
    public void addDept(Dept dept) {
        deptDao.addDept(dept);
    }

    @Override
    public void updateDept(Dept dept) {
        deptDao.updateDept(dept);
    }

    @Override
    public Dept findById(String id) {
        return  deptDao.findById(id);
    }

    @Override
    public List<Dept> findAllDept(String companyId) {
        return deptDao.findAll(companyId);
    }

    @Override
    public void delete(String deptId) {
        deptDao.delete(deptId);
    }
}
