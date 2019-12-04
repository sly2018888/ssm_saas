package com.sly.dao.system;

import com.sly.domain.system.SysLog;

import java.util.List;

public interface SysLogDao {

    List<SysLog> findAll();

    void save(SysLog sysLog);
}
