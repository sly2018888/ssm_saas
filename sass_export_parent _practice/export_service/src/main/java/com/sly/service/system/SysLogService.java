package com.sly.service.system;

import com.github.pagehelper.PageInfo;
import com.sly.domain.system.SysLog;

public interface SysLogService {
    PageInfo findAll(Integer page, Integer pageSize);

    void save(SysLog sysLog);
}
