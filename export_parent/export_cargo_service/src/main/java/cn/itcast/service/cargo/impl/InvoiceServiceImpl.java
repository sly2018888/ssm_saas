package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.ContractDao;
import cn.itcast.dao.cargo.ExportDao;
import cn.itcast.dao.cargo.InvoiceDao;
import cn.itcast.dao.cargo.PackingListCDao;
import cn.itcast.domain.cargo.*;
import cn.itcast.service.cargo.InvoiceService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private PackingListCDao packingListCDao;

    @Autowired
    private ExportDao exportDao;

    @Override
    public void save(Invoice invoice) {
        String scNo = invoice.getScNo();
        String[] contractIds = scNo.split(",");
        double money = 0.0;
        for (String contractId : contractIds) {
            Contract contract = contractDao.selectByPrimaryKey(contractId);
            money+=contract.getTotalAmount();
        }
        invoice.setAllCount(money);
        invoiceDao.insertSelective(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        if(invoice.getState() == null){
            invoiceDao.updateByPrimaryKeySelective(invoice);
        }else {
            String invoiceId = invoice.getInvoiceId();

            PackingListC packingListC = packingListCDao.selectByPrimaryKey(invoiceId);
            String[] exportIds = packingListC.getExportIds().split(",");
            Export export = new Export();
            if (invoice.getState() == 1) {//草稿  提交发票
                for (String exportId : exportIds) {
                    export.setId(exportId);
                    export.setState(4);
                    exportDao.updateByPrimaryKeySelective(export);
                }
            } else if (invoice.getState() == 0) {//取消提交发票
                for (String exportId : exportIds) {
                    export.setId(exportId);
                    export.setState(3);
                    exportDao.updateByPrimaryKeySelective(export);
                }
            }
            invoiceDao.updateByPrimaryKeySelective(invoice);
        }
    }

    @Override
    public void delete(String id) {
        invoiceDao.deleteByPrimaryKey(id);
    }

    @Override
    public Invoice findById(String id) {
        return invoiceDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(InvoiceExample example, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Invoice> list = invoiceDao.selectByExample(example);
        return new PageInfo(list);
    }
}
