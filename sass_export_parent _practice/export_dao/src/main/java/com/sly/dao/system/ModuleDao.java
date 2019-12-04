package com.sly.dao.system;

import com.sly.domain.system.Module;

import java.util.List;

public interface ModuleDao {
    List<Module> findByPage();

    Module findById(String moduleId);

    void addModule(Module module);

    void updateModule(Module module);

    void deleteById(String moduleId);

    List<Module> findAll();

    List<Module> findByBelong(Integer i);

    List<Module> findByUserId(String id);
}
