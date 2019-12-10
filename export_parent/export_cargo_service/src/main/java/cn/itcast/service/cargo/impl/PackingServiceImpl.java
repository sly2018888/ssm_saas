package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.ExportDao;
import cn.itcast.dao.cargo.PackingListCDao;
import cn.itcast.domain.cargo.Export;
import cn.itcast.domain.cargo.PackingListC;
import cn.itcast.domain.cargo.PackingListCExample;
import cn.itcast.service.cargo.PackingService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PackingServiceImpl implements PackingService {

    @Autowired
    private PackingListCDao packingListCDao;

    @Autowired
    private ExportDao exportDao;

    @Override
    public void save(PackingListC packingListC) {
        String[] exportIds = packingListC.getExportIds().split(",");
        StringBuilder stringBuilder= new StringBuilder();
        for (String exportId:exportIds) {
            Export export = exportDao.selectByPrimaryKey(exportId);
            String customerContract = export.getContractIds();//合同号
            stringBuilder.append(customerContract + ",");
        }
        packingListC.setExportNos(stringBuilder.toString().substring(0,stringBuilder.toString().length()-1));//设置合同号
        Export export = new Export();
        for (String exportId:exportIds) {
            export.setId(exportId);
            export.setState(6);//预装箱
            exportDao.updateByPrimaryKeySelective(export);
        }
        packingListCDao.insertSelective(packingListC);
    }

    @Override
    public void update(PackingListC packingListC) {
        PackingListC packingListC1 = packingListCDao.selectByPrimaryKey(packingListC.getPackingListId());//查询当前id的装箱单信息
        String[] exportIds = packingListC1.getExportIds().split(",");
        if(packingListC.getState()==1){//提交装箱单
            for (String exportId : exportIds) {
                Export export = new Export();
                export.setId(exportId);
                export.setState(7);//代表已装箱
                exportDao.updateByPrimaryKeySelective(export);
            }
        }else if(packingListC.getState()==0){//取消提交装箱单
            for (String exportId : exportIds) {
                Export export = new Export();
                export.setId(exportId);
                export.setState(6);//预装箱
                exportDao.updateByPrimaryKeySelective(export);
            }
        }
        packingListCDao.updateByPrimaryKeySelective(packingListC);
    }

    @Override
    public void delete(String id) {
        PackingListC packingListC = packingListCDao.selectByPrimaryKey(id);
        String[] exportIds = packingListC.getExportIds().split(",");
        Export export = new Export();
        for (String exportId:exportIds) {
            export.setId(exportId);
            export.setState(2);
            exportDao.updateByPrimaryKeySelective(export);
        }
        packingListCDao.deleteByPrimaryKey(id);
    }

    @Override
    public PackingListC findById(String id) {
        return packingListCDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(PackingListCExample example, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<PackingListC> list = packingListCDao.selectByExample(example);
        return new PageInfo(list);
    }


}
