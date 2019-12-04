package com.sly.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.dao.system.SysLogDao;
import com.sly.domain.system.SysLog;
import com.sly.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService{

    @Autowired
    private SysLogDao sysLogDao;


    @Override
    public PageInfo findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<SysLog> list =  sysLogDao.findAll();
        return new PageInfo(list);
    }

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }
}
