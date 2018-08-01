package com.styzf.core.common.exception;

public class StyzfNoRecordException extends StyzfException {
    private static final long serialVersionUID = 1L;

    public StyzfNoRecordException(Exception e) {
        super(e);
    }
  
    public StyzfNoRecordException() {
        super("no-record", "没有找到对应的记录");
    }
}
