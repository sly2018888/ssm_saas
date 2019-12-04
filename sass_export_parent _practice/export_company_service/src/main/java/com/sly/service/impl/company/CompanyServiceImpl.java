package com.sly.service.impl.company;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.dao.company.CompanyDao;
import com.sly.domain.company.Company;
import com.sly.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public PageInfo findByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Company> list = companyDao.findAll();
//        List<Company> list = companyDao.findByPage((page-1)*pageSize, pageSize);
//        Long count = companyDao.findCount();
//        PageBean pb = new PageBean(page,pageSize,count,list);
//        pb.setTotal(count);
        return new PageInfo(list);
    }


    @Override
    public void addCompany(Company company) {
        companyDao.addCompany(company);
    }

    @Override
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    @Override
    public void delete(String id) {
        companyDao.delete(id);
    }

    @Override
    public Company findById(String id) {
        return companyDao.findById(id);
    }




}
