package com.sly.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.dao.system.ModuleDao;
import com.sly.domain.system.Module;
import com.sly.domain.system.User;
import com.sly.service.system.ModuleService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService{

    @Autowired
    private ModuleDao moduleDao;

    @Override
    public PageInfo<Module> findByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Module> moduleList = moduleDao.findByPage();
        return new PageInfo<>(moduleList);
    }

    @Override
    public Module findById(String moduleId) {
        return moduleDao.findById(moduleId);
    }

    @Override
    public void addModule(Module module) {
        moduleDao.addModule(module);
    }

    @Override
    public void updateModule(Module module) {
        moduleDao.updateModule(module);
    }

    @Override
    public void deleteById(String moduleId) {
        moduleDao.deleteById(moduleId);
    }

    @Override
    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    @Override
    public List<Module> findMOduleListByUser(User user) {
        if(user.getDegree() == 0){
            return  moduleDao.findByBelong(0);
        }else if(user.getDegree() == 1){
            return  moduleDao.findByBelong(1);
        }else {
            return  moduleDao.findByUserId(user.getId());
        }
    }
}
