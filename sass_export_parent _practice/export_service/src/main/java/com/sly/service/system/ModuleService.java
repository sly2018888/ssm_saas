package com.sly.service.system;

import com.github.pagehelper.PageInfo;
import com.sly.domain.system.Module;
import com.sly.domain.system.User;

import java.util.List;

public interface ModuleService {
    PageInfo<Module> findByPage(Integer page, Integer pageSize);

    Module findById(String moduleId);

    void addModule(Module module);

    void updateModule(Module module);

    void deleteById(String moduleId);

    List<Module> findAll();

    List<Module> findMOduleListByUser(User user);
}
