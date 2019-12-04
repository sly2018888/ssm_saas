package com.sly.service.impl.cargo;

import com.sly.dao.cargo.ExtEproductDao;
import com.sly.domain.cargo.ExtEproduct;
import com.sly.domain.cargo.ExtEproductExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.service.cargo.ExtEproductService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

@Service
public class ExtEproductServiceImpl implements ExtEproductService {

    @Autowired
    private ExtEproductDao extEproductDao;

    @Override
    public ExtEproduct findById(String id) {
        return extEproductDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(ExtEproduct extEproduct) {
        extEproductDao.insertSelective(extEproduct);
    }

    @Override
    public void update(ExtEproduct extEproduct) {
        extEproductDao.updateByPrimaryKeySelective(extEproduct);
    }

    @Override
    public void delete(String id) {
        extEproductDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExtEproductExample extEproductExample, int page, int size) {
        PageHelper.startPage(page,size);
        List<ExtEproduct> list = extEproductDao.selectByExample(extEproductExample);
        return new PageInfo(list);
    }
}
