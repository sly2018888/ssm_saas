package cn.itcast.service.cargo;

import cn.itcast.domain.cargo.PackingListC;
import cn.itcast.domain.cargo.PackingListCExample;
import com.github.pagehelper.PageInfo;

public interface PackingService {

    void save(PackingListC packingListC);

    void update(PackingListC packingListC);

    void delete(String id);

    PackingListC findById(String id);

    PageInfo findAll(PackingListCExample example, Integer page, Integer pageSize);
}
