package com.sly.service.impl.cargo;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.dao.cargo.ContractDao;
import com.sly.dao.cargo.ContractProductDao;
import com.sly.dao.cargo.ExtCproductDao;
import com.sly.domain.cargo.Contract;
import com.sly.domain.cargo.ContractExample;
import com.sly.domain.vo.ContractProductVo;
import com.sly.service.cargo.ContractService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ExtCproductDao  extCproductDao;
    @Autowired
    private ContractProductDao contractProductDao;


    @Override
    public Contract findById(String id) {
        return contractDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Contract contract) {
        contractDao.insertSelective(contract);
    }

    @Override
    public void update(Contract contract) {
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        extCproductDao.deleteByContractId(id);
        contractProductDao.deleteByContractId(id);
        contractDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ContractExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Contract> list = contractDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public List<ContractProductVo> findContractProductVoByShipTime(String inputDate,String companyId) {
        return contractDao.findContractProductVoByShipTime(inputDate,companyId);
    }
}
