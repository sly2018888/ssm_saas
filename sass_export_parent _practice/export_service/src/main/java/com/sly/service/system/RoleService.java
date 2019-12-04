package com.sly.service.system;

import com.github.pagehelper.PageInfo;
import com.sly.domain.system.Module;
import com.sly.domain.system.Role;

import java.util.List;

public interface RoleService {
    PageInfo<Role> findAll(String companyId,Integer page, Integer pageSize);

    void addRole(Role role);

    void updateRole(Role role);

    void deleteById(String id);

    Role findById(String id);

    void findModuleById(String roleid);

    List<Role> findAllRole(String companyId);

    List<String> findRolesById(String userId);

    List<Module> getZtreeNodes(String roleid);

    List<String> findModulesByRoleId(String roleid);

    void addRoleModule(String roleid, String moduleIds);
}
