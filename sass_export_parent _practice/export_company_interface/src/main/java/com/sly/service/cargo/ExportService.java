package com.sly.service.cargo;

import com.sly.domain.cargo.Export;
import com.sly.domain.cargo.ExportExample;
import com.github.pagehelper.PageInfo;
import com.sly.domain.vo.ExportResult;


public interface ExportService {

    Export findById(String id);

    void save(Export export);

    void update(Export export);

    void delete(String id);

	PageInfo findAll(ExportExample example, int page, int size);

    void updateE(ExportResult exportResult);
}
