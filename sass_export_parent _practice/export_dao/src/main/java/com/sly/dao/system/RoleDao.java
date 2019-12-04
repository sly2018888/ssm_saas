package com.sly.dao.system;

import com.github.pagehelper.PageInfo;
import com.sly.domain.system.Module;
import com.sly.domain.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    List<Role> findAll(String companyId);

    void addRole(Role role);

    void updateRole(Role role);

    void deleteById(String id);

    Role findById(String id);

    void findModuleById(String roleid);

    List<String> findRolesById(String userId);

    List<Module> getZtreeNodes(String roleid);

    List<String> findModulesByRoleId(String roleid);

    void addRoleModule(@Param("roleid") String roleid,@Param("s") String s);

    void deleteRoleModule(String roleid);
}
