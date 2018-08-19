package com.styzf.springboot.mybatisPlus.util;

import java.io.Serializable;

import com.baomidou.mybatisplus.plugins.Page;
import com.styzf.core.common.base.BaseDto;
import com.styzf.core.common.base.PageDto;
import com.styzf.core.common.util.OrikaUtil;

public class PageUtil implements Serializable{

    private static final long serialVersionUID = 1L;

    public static <D extends BaseDto> PageDto<D> mapPage(Page<?> page, Class<D> clazz) {
        PageDto<D> pageDto = new PageDto<D>();
        pageDto.setPage(page.getCurrent());
        pageDto.setPageSize(page.getSize());
        pageDto.setRowCount(page.getTotal());
        pageDto.setList(OrikaUtil.mapList(page.getRecords(), clazz));
        return pageDto;
    }

}
