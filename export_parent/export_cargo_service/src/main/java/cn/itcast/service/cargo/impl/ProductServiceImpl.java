package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.ContractProductDao;
import cn.itcast.dao.cargo.FactoryDao;
import cn.itcast.dao.cargo.ProductDao;
import cn.itcast.domain.cargo.*;
import cn.itcast.service.cargo.ProductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private FactoryDao factoryDao;

    @Autowired
    private ContractProductDao contractProductDao;
    @Override
    public void save(Product product) {
        //先根据货物的工厂名称查询工厂id
        String factoryName = product.getFactoryName();
        FactoryExample factoryExample = new FactoryExample();
        factoryExample.createCriteria().andFactoryNameEqualTo(factoryName);
        List<Factory> factoryList = factoryDao.selectByExample(factoryExample);
        ContractProduct contractProduct = new ContractProduct();
        for (Factory factory : factoryList) {
            //封装工厂id
            product.setFactoryId(factory.getId());
            contractProduct.setFactoryId(factory.getId());
        }

        //把创建的货物的货号添加到合同下货物的下拉列表中
        contractProduct.setFactoryName(factoryName);
        contractProduct.setProductNo(product.getProductNo());
        contractProduct.setId(UUID.randomUUID().toString());
        contractProductDao.insertSelective(contractProduct);

        //保存产品数据
        product.setId(UUID.randomUUID().toString());
        productDao.insertSelective(product);
    }

    @Override
    public void update(Product product) {
        productDao.updateByPrimaryKeySelective(product);
    }

    @Override
    public void delete(String id) {
        //先删除合同下货物列表的数据再删除产品数据
        Product product = productDao.selectByPrimaryKey(id);
        String factoryName = product.getFactoryName();
        String productNo = product.getProductNo();
        ContractProductExample contractProductExample = new ContractProductExample();
        contractProductExample.createCriteria().andFactoryNameEqualTo(factoryName).andProductNoEqualTo(productNo);
        List<ContractProduct> contractProductList = contractProductDao.selectByExample(contractProductExample);
        for (ContractProduct contractProduct : contractProductList) {
            String id1 = contractProduct.getId();
            contractProductDao.deleteByPrimaryKey(id1);
        }

        //删除产品数据
        productDao.deleteByPrimaryKey(id);
    }

    @Override
    public Product findById(String id) {
        return productDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> findAll(ProductExample example) {
        return null;
    }

    @Override
    public PageInfo findAllAndPage(ProductExample example, int pageNum, int size) {
        PageHelper.startPage(pageNum,size);
        List<Product> list = productDao.selectByExample(example);
        return new PageInfo(list);
    }
}
