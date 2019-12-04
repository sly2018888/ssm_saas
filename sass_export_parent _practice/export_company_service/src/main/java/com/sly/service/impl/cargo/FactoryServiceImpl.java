package com.sly.service.impl.cargo;

import com.alibaba.dubbo.config.annotation.Service;
import com.sly.dao.cargo.FactoryDao;
import com.sly.domain.cargo.Factory;
import com.sly.domain.cargo.FactoryExample;
import com.sly.service.cargo.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryDao factoryDao;


    @Override
    public void save(Factory factory) {
        factoryDao.insertSelective(factory);
    }

    @Override
    public void update(Factory factory) {
        factoryDao.updateByPrimaryKeySelective(factory);
    }

    @Override
    public void delete(String id) {
        factoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public Factory findById(String id) {
        return factoryDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Factory> findAll(FactoryExample example) {
        return  factoryDao.selectByExample(example);
    }
}
