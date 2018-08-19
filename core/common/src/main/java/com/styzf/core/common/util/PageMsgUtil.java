package com.styzf.core.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 *  分页工具类
 * </p>
 * 
 * @author styzf
 * @date 2018年8月18日 
 * @since 1.0.0
 */
public class PageMsgUtil implements Serializable {

    private static final long serialVersionUID = -5877139871142583405L;
    private int num;
    private int size;
    private long rowCount;
    private List<?> list;
    private int pageCount;
    private int startRow;
    private int first = 1;
    private int last;
    private int next;
    private int prev;
    private int start;
    private int end;
    private int numCount = 10;
    public List<Integer> showPages = new ArrayList<>();
    private Object queryClass;
    private String queryUrl;
    
    public PageMsgUtil(int size, String str_num, long rowCount) {
        int num = 1;
        if (str_num != null) {
            num = Integer.parseInt(str_num);
        }
        this.num = num;
        this.size = size;
        this.rowCount = rowCount;
        
        pageCount = ((int)Math.ceil(rowCount / size));
        this.num = Math.min(this.num, pageCount);
        this.num = Math.max(1, this.num);
        
        startRow = ((this.num - 1) * size);
        last = pageCount;
        next = Math.min(pageCount, this.num + 1);
        prev = Math.max(1, this.num - 1);
        
        
        start = Math.max(this.num - numCount / 2, first);
        end = Math.min(start + numCount, last);
        if (end - start < numCount) {
            start = Math.max(end - numCount, 1);
        }
        for (int i = start; i <= end; i++) {
            showPages.add(Integer.valueOf(i));
        }
    }
    
    
    public PageMsgUtil(int size, int num, long rowCount) {
        if (num <= 0) {
            num = 1;
        }
        this.num = num;
        this.size = size;
        this.rowCount = rowCount;
        
        pageCount = ((int)Math.ceil(rowCount / size));
        this.num = Math.min(this.num, pageCount);
        this.num = Math.max(1, this.num);
        
        startRow = ((this.num - 1) * size);
        last = pageCount;
        next = Math.min(pageCount, this.num + 1);
        prev = Math.max(1, this.num - 1);
        
        
        start = Math.max(this.num - numCount / 2, first);
        end = Math.min(start + numCount, last);
        if (end - start < numCount) {
            start = Math.max(end - numCount, 1);
        }
        for (int i = start; i <= end; i++) {
            showPages.add(Integer.valueOf(i));
        }
    }
    
    public Object getQueryClass() {
        return queryClass;
    }
    
    public void setQueryClass(Object queryClass) {
        this.queryClass = queryClass;
    }
    
    public int getNum() {
        return num;
    }
    
    public void setNum(int num) {
        if (num <= 0) {
            this.num = 1;
        } else {
            this.num = num;
        }
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public long getRowCount() {
        return rowCount;
    }
    
    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }
    
    public int getPageCount() {
        return pageCount;
    }
    
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    public int getStartRow() {
        return startRow;
    }
    
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    
    public int getFirst() {
        return first;
    }
    
    public void setFirst(int first) {
        this.first = first;
    }
    
    public int getLast() {
        return last;
    }
    
    public void setLast(int last) {
        this.last = last;
    }
    
    public int getNext() {
        return next;
    }
    
    public void setNext(int next) {
        this.next = next;
    }
    
    public int getPrev() {
        return prev;
    }
    
    public void setPrev(int prev) {
        this.prev = prev;
    }
    
    public int getStart() {
        return start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
    public int getEnd() {
        return end;
    }
    
    public void setEnd(int end) {
        this.end = end;
    }
    
    public int getNumCount() {
        return numCount;
    }
    
    public void setNumCount(int numCount) {
        this.numCount = numCount;
    }
    
    public List<Integer> getShowPages() {
        return showPages;
    }
    
    public void setShowPages(List<Integer> showPages) {
        this.showPages = showPages;
    }
    
    public String getQueryUrl() {
        return queryUrl;
    }
    
    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }
    
    public List<?> getList() {
        return list;
    }
    
    public void setList(List<?> list) {
        this.list = list;
    }
    
}
