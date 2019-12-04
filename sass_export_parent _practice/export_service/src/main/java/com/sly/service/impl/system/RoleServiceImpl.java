package com.sly.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.dao.system.RoleDao;
import com.sly.domain.system.Module;
import com.sly.domain.system.Role;
import com.sly.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo<Role> findAll(String companyId,Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Role> roleList = roleDao.findAll(companyId);
        return  new PageInfo<>(roleList);
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void deleteById(String id) {
        roleDao.deleteById(id);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void findModuleById(String roleid) {
        roleDao.findModuleById(roleid);
    }

    @Override
    public List<Role> findAllRole(String companyId) {
        return roleDao.findAll(companyId);
    }

    @Override
    public List<String> findRolesById(String userId) {
        return roleDao.findRolesById(userId);
    }

    @Override
    public List<Module> getZtreeNodes(String roleid) {
        return roleDao.getZtreeNodes(roleid);
    }

    @Override
    public List<String> findModulesByRoleId(String roleid) {
        return roleDao.findModulesByRoleId(roleid);
    }

    @Override
    public void addRoleModule(String roleid, String moduleIds) {
        roleDao.deleteRoleModule(roleid);
        String[] moduleId = moduleIds.split(",");
        for (String s : moduleId) {
            roleDao.addRoleModule(roleid,s);
        }
    }
}
