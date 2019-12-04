package com.sly.service.cargo;

import com.github.pagehelper.PageInfo;
import com.sly.domain.cargo.ExtEproduct;
import com.sly.domain.cargo.ExtEproductExample;

public interface ExtEproductService {

    ExtEproduct findById(String id);

    void save(ExtEproduct extEproduct);

    void update(ExtEproduct extEproduct);

    void delete(String id);

    PageInfo findAll(ExtEproductExample extEproductExample, int page, int size);
}
