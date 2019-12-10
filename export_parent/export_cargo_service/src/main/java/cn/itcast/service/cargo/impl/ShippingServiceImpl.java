package cn.itcast.service.cargo.impl;


import cn.itcast.dao.cargo.ShippingDao;
import cn.itcast.domain.cargo.Shipping;
import cn.itcast.domain.cargo.ShippingExample;
import cn.itcast.service.cargo.ShippingService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingDao shippingDao;

    @Override
    public PageInfo<Shipping> findPage(ShippingExample shippingExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Shipping> list = shippingDao.selectByExample(shippingExample);

        return new PageInfo(list);
    }

    @Override
    public void delete(String id) {
        shippingDao.deleteByPrimaryKey(id);
    }

    @Override
    public Shipping findById(String id) {
        return shippingDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Shipping shipping) {
        shippingDao.updateByPrimaryKeySelective(shipping);
    }

    @Override
    public void save(Shipping shipping) {
        shippingDao.insert(shipping);
    }

/*    @Override
    public PackingListC findPackingListByShippingId(String id) {
        return packingService.findById(id);
    }*/
}
