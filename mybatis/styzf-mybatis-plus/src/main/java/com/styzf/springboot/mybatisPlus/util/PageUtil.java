package com.styzf.springboot.mybatisPlus.util;

import java.io.Serializable;

import com.baomidou.mybatisplus.plugins.Page;
import com.styzf.core.common.base.BaseDTO;
import com.styzf.core.common.base.PageDTO;
import com.styzf.core.common.util.OrikaUtil;

public class PageUtil implements Serializable{

    private static final long serialVersionUID = 1L;

    public static <D extends BaseDTO> PageDTO<D> mapPage(Page<?> page, Class<D> clazz) {
        PageDTO<D> pageDto = new PageDTO<D>();
        pageDto.setPage(page.getCurrent());
        pageDto.setPageSize(page.getSize());
        pageDto.setRowCount(page.getTotal());
        pageDto.setList(OrikaUtil.mapList(page.getRecords(), clazz));
        return pageDto;
    }

}
