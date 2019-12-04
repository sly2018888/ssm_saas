package com.sly.service.impl.cargo;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.dao.cargo.ContractDao;
import com.sly.dao.cargo.ContractProductDao;
import com.sly.dao.cargo.ExtCproductDao;
import com.sly.dao.cargo.FactoryDao;
import com.sly.domain.cargo.*;
import com.sly.service.cargo.ContractProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.List;

@Service
@Transactional
public class ContractProductServiceImpl implements ContractProductService {

    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private FactoryDao factoryDao;

    @Override
    public void save(ContractProduct contractProduct) {
        Double price = contractProduct.getPrice();
        Integer cnumber = contractProduct.getCnumber();
        Double amount = price*cnumber;
        contractProduct.setAmount(amount);

//        修改合同数据 货物种类  总金额
        String contractId = contractProduct.getContractId();
        Contract contract = contractDao.selectByPrimaryKey(contractId);

        contract.setProNum(contract.getProNum()+1);
        contract.setTotalAmount(contract.getTotalAmount() + amount);
        contractDao.updateByPrimaryKeySelective(contract);

        contractProductDao.insertSelective(contractProduct);
    }

    @Override
    public void update(ContractProduct contractProduct) {
//      数据库中本来的数据  小计金额
        String productId = contractProduct.getId();
        ContractProduct contractProduct1 = contractProductDao.selectByPrimaryKey(productId);
        Double amount_old = contractProduct1.getAmount();
//        要修改的小计金额
        Double price = contractProduct.getPrice();
        Integer cnumber = contractProduct.getCnumber();
        Double amount_new = price*cnumber;
        contractProduct.setAmount(amount_new);

        String contractId = contractProduct.getContractId();
        Contract contract = contractDao.selectByPrimaryKey(contractId);
        contract.setTotalAmount(contract.getTotalAmount()- amount_old +amount_new);

        contractProductDao.updateByPrimaryKeySelective(contractProduct);
    }

    @Override
    public void delete(String id) {
        //查询货物
        ContractProduct contractProduct = contractProductDao.selectByPrimaryKey(id);
        //查询货物下的所有附件
        ExtCproductExample extCproductExample = new ExtCproductExample();
        extCproductExample.createCriteria().andContractProductIdEqualTo(id);
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);

        //查询购销合同
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        //计算总金额
        double money = contractProduct.getAmount();
        for (ExtCproduct extCproduct : extCproducts) {
            money +=extCproduct.getAmount();//查询附件的小计金额
            extCproductDao.deleteByPrimaryKey(extCproduct.getId());//删除附件
        }
        //删除货物
        contractProductDao.deleteByPrimaryKey(id);
        //设置合同的金额
        contract.setTotalAmount(contract.getTotalAmount()-money);//合同金额-附件金额
        contract.setProNum(contract.getProNum()-1);//s货物数量-1
        contract.setExtNum(contract.getExtNum()-extCproducts.size());//合同附件数量-附件的总数量
        contractDao.updateByPrimaryKeySelective(contract);

    }

    @Override
    public ContractProduct findById(String id) {
        return contractProductDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ContractProductExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<ContractProduct> contractProducts = contractProductDao.selectByExample(example);
        return new PageInfo(contractProducts);
    }

    @Override
    public void saveList(List<ContractProduct> productList) {
        for (ContractProduct contractProduct : productList) {
            //查询厂家的对象
            FactoryExample example = new FactoryExample();
            example.createCriteria().andFactoryNameEqualTo(contractProduct.getFactoryName()).andCtypeEqualTo("货物");
            List<Factory> factories = factoryDao.selectByExample(example);
            if(factories!=null && factories.size()>0){
                contractProduct.setFactoryId(factories.get(0).getId());
            }
            this.save(contractProduct);
        }
    }

    @Override
    public List<Map> sellCharts(String companyId) {
        return  contractProductDao.sellCharts(companyId);
    }

    @Override
    public List<Map> factoryCharts(String companyId) {
        return  contractProductDao.factoryCharts(companyId);
    }

    @Override
    public List<Map> onlineCharts(String companyId) {
        return  contractProductDao.onlineCharts(companyId);
    }

}
