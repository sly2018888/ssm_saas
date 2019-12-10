package cn.itcast.service.cargo;


import cn.itcast.domain.cargo.Shipping;
import cn.itcast.domain.cargo.ShippingExample;
import com.github.pagehelper.PageInfo;

public interface ShippingService {

    PageInfo<Shipping> findPage(ShippingExample shippingExample, Integer pageNum, Integer pageSize);

    void delete(String id);

    Shipping findById(String id);

    void update(Shipping shipping);

    void save(Shipping shipping);

    // PackingListC findPackingListByShippingId(String id);
}
