package com.styzf.core.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author styzf
 * @date 2018年7月23日 
 * 
 * @param <T>
 */
public class PageDTO<T extends BaseDTO> implements Serializable {
    /**
     * @author styzf
     * @date 2018年7月23日 
     * 
     */
    private static final long serialVersionUID = 1311785060751428298L;

    private int page = 1;
    
    private int pageSize = 10;
    
    private long rowCount;
    
    private List<T> list;
    
    private String orderBy;

    private boolean ascSort = true;
    
    private T dto;
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public long getRowCount() {
        return rowCount;
    }
    
    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }
    
    public List<T> getList() {
        return list;
    }
    
    public void setList(List<T> list) {
        this.list = list;
    }
    
    public String getOrderBy() {
        return orderBy;
    }
    
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
    public boolean isAscSort() {
        return ascSort;
    }
    
    public void setAscSort(boolean isAscSort) {
        this.ascSort = isAscSort;
    }
    
    public T getDto() {
        return dto;
    }
    
    public void setDto(T dto) {
        this.dto = dto;
    }
    
    public long getTotalPage() {
        long totalPage = rowCount / pageSize;
        if ((totalPage == 0L) || (rowCount % pageSize != 0L)) {
          totalPage += 1L;
        }
        return totalPage;
    }
    
    public boolean isLastPage() {
        return page >= getTotalPage();
    }

    public boolean isFirstPage() {
        return page <= 1;
    }
    
    public int getNextPage() {
        if (isLastPage()) {
          return page;
        }
        return page + 1;
    }
    
    public int getPrePage() {
        if (isFirstPage()) {
          return page;
        }
        return page - 1;
    }
}
