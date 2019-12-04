package com.sly.service.cargo;


import com.sly.domain.cargo.ExportProduct;
import com.github.pagehelper.PageInfo;
import com.sly.domain.cargo.ExportProductExample;

import java.util.List;


public interface ExportProductService {

	ExportProduct findById(String id);

	void save(ExportProduct exportProduct);

	void update(ExportProduct exportProduct);

	void delete(String id);

	PageInfo findAll(ExportProductExample exportProductExample, int page, int size);

    List<ExportProduct> findByExportId(String id);
}
