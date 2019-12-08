package cn.itcast.service.cargo;

import cn.itcast.domain.cargo.Product;
import cn.itcast.domain.cargo.ProductExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {
    /**
     * 保存
     */
    void save(Product product);

    /**
     * 更新
     */
    void update(Product product);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 根据id查询
     */
    Product findById(String id);

    //查询所有
    public List<Product> findAll(ProductExample example);


    PageInfo findAllAndPage(ProductExample example, int pageNum, int size);
}
