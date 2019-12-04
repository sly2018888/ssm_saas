package com.sly.dao.cargo;

import com.sly.domain.cargo.ContractProduct;
import com.sly.domain.cargo.ContractProductExample;
import java.util.Map;
import java.util.List;

public interface ContractProductDao {

	//删除
    int deleteByPrimaryKey(String id);

	//保存
    int insertSelective(ContractProduct record);

	//条件查询
    List<ContractProduct> selectByExample(ContractProductExample example);

	//id查询
    ContractProduct selectByPrimaryKey(String id);

	//更新
    int updateByPrimaryKeySelective(ContractProduct record);

    void deleteByContractId(String id);

    List<Map> sellCharts(String companyId);

    List<Map> factoryCharts(String companyId);

    List<Map> onlineCharts(String companyId);
}