package com.sly.service.impl.cargo;

import com.sly.dao.cargo.*;
import com.sly.domain.cargo.*;
import com.sly.domain.vo.ExportProductResult;
import com.sly.domain.vo.ExportProductVo;
import com.sly.domain.vo.ExportResult;
import com.sly.service.cargo.ExportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ExportServiceImpl implements ExportService {

    @Autowired
    private ExportDao exportDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ExportProductDao exportProductDao;
    @Autowired
    private ExtEproductDao extEproductDao;

    @Override
    public Export findById(String id) {
        return exportDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Export export) {
        //关联6张表  修改3张表
        String contractIds = export.getContractIds();
        //Arrays.asList数组转集合   in查询
        List<String> contractIdList = Arrays.asList(contractIds.split(","));
        ContractProductExample contractProductExample = new ContractProductExample();
        contractProductExample.createCriteria().andContractIdIn(contractIdList);
        List<ContractProduct> contractProductList = contractProductDao.selectByExample(contractProductExample);

        for (ContractProduct contractProduct : contractProductList) {
            ExportProduct exportProduct = new ExportProduct();
            BeanUtils.copyProperties(contractProduct,exportProduct);
            exportProduct.setExportId(export.getId());//关联报运单
            //货物下的附件
            List<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
            for (ExtCproduct extCproduct : extCproducts) {
                ExtEproduct extEproduct = new ExtEproduct();
                BeanUtils.copyProperties(extCproduct,extEproduct);
                extEproduct.setExportId(export.getId());
                extEproduct.setExportProductId(exportProduct.getId());
                extEproductDao.insertSelective(extEproduct);
                }
            exportProductDao.insertSelective(exportProduct);
        }

        ContractExample contractExample =new ContractExample();
        contractExample.createCriteria().andIdIn(contractIdList);
        List<Contract> contracts = contractDao.selectByExample(contractExample);
        Integer totalProNum = 0;
        Integer totalExtNum = 0;
        StringBuffer sb = new StringBuffer("");
        for (Contract contract : contracts) {
            totalProNum += contract.getProNum();
            totalExtNum += contract.getExtNum();
            sb.append(contract.getCustomName()).append(" ");
        }
        export.setProNum(totalProNum);
        export.setExtNum(totalExtNum);
        export.setCustomerContract(sb.toString());
        exportDao.insertSelective(export);
    }

    @Override
    public void update(Export export) {
        //报运单   报运单货物修改
        exportDao.updateByPrimaryKeySelective(export);
        List<ExportProduct> exportProducts = export.getExportProducts();
        if(exportProducts!=null && exportProducts.size()>0){
            for (ExportProduct exportProduct : exportProducts) {
                exportProductDao.updateByPrimaryKeySelective(exportProduct);
            }
        }

        exportDao.updateByPrimaryKeySelective(export);
    }

    @Override
    public void delete(String id) {
        exportDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExportExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Export> list = exportDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void updateE(ExportResult exportResult) {
        //将修改后的数据
        String exportId = exportResult.getExportId();//获取报运单号
        Export export = new Export();
        export.setId(exportId);
        export.setRemark(exportResult.getRemark());
        export.setState(exportResult.getState());
        exportDao.updateByPrimaryKeySelective(export);

        Set<ExportProductResult> products = exportResult.getProducts();//设置税
        ExportProduct exportProduct = null;
        for (ExportProductResult product : products) {
            exportProduct = new ExportProduct();
            exportProduct.setTax(product.getTax());
            exportProduct.setId(product.getExportProductId());
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }


    }
}
