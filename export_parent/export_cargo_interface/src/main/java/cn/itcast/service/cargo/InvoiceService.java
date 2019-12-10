package cn.itcast.service.cargo;

import cn.itcast.domain.cargo.Invoice;
import cn.itcast.domain.cargo.InvoiceExample;
import com.github.pagehelper.PageInfo;

public interface InvoiceService {

    void save(Invoice invoice);

    void update(Invoice invoice);

    void delete(String id);

    Invoice findById(String id);

    PageInfo findAll(InvoiceExample example, Integer page, Integer pageSize);

}
