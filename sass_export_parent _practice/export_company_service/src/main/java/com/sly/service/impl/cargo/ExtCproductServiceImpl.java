package com.sly.service.impl.cargo;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.dao.cargo.ContractDao;
import com.sly.dao.cargo.ExtCproductDao;
import com.sly.domain.cargo.Contract;
import com.sly.domain.cargo.ExtCproduct;
import com.sly.domain.cargo.ExtCproductExample;
import com.sly.service.cargo.ExtCproductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ExtCproductServiceImpl implements ExtCproductService{

    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ContractDao contractDao;
    @Override
    public void save(ExtCproduct extCproduct) {
        //计算小计金额
        Double price = extCproduct.getPrice();
        Integer cnumber = extCproduct.getCnumber();
        double amount = price*cnumber;
        extCproduct.setAmount(amount);
        //修改合同附件数量
        String contractId = extCproduct.getContractId();
        Contract contract = contractDao.selectByPrimaryKey(contractId);//根据id获取合同
        contract.setExtNum(contract.getExtNum()+1);
        contract.setTotalAmount(contract.getTotalAmount()+amount);
        contractDao.updateByPrimaryKeySelective(contract);
        extCproductDao.insertSelective(extCproduct);
    }

    @Override
    public void update(ExtCproduct extCproduct) {
        ExtCproduct extCproduct_old = extCproductDao.selectByPrimaryKey(extCproduct.getId());
        Double amount_old = extCproduct_old.getAmount();
        //计算小计金额
        Double price = extCproduct.getPrice();
        Integer cnumber = extCproduct.getCnumber();
        double amount_new = price*cnumber;
        extCproduct.setAmount(amount_new);

        //修改合同总金额
        String contractId = extCproduct.getContractId();
        Contract contract = contractDao.selectByPrimaryKey(contractId);//根据id获取合同
        contract.setTotalAmount(contract.getTotalAmount()-amount_old+amount_new);
        contractDao.updateByPrimaryKeySelective(contract);
        extCproductDao.updateByPrimaryKeySelective(extCproduct);
    }

    @Override
    public void delete(String id) {
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        String contractId = extCproduct.getContractId();
        Contract contract = contractDao.selectByPrimaryKey(contractId);
//        合同附件数-1
        contract.setExtNum(contract.getExtNum()-1);
//        合同总金额-附件小计
        contract.setTotalAmount(contract.getTotalAmount()-extCproduct.getAmount());
        contractDao.updateByPrimaryKeySelective(contract);
        extCproductDao.deleteByPrimaryKey(id);
    }

    @Override
    public ExtCproduct findById(String id) {
        return  extCproductDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExtCproductExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(example);
        return new PageInfo(extCproducts);
    }
}
