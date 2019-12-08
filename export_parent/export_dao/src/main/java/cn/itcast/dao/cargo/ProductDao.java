package cn.itcast.dao.cargo;

import cn.itcast.domain.cargo.Product;
import cn.itcast.domain.cargo.ProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    int deleteByPrimaryKey(String id);


    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

}