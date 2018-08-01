package com.styzf.test.dto;

import java.io.Serializable;

public class TestMainDTO implements Serializable{
    private static final long serialVersionUID = 4994188332061056540L;

    private Integer testId;

    private String testRelId;

    private String testName;

    private Integer testAge;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestRelId() {
        return testRelId;
    }

    public void setTestRelId(String testRelId) {
        this.testRelId = testRelId == null ? null : testRelId.trim();
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }

    public Integer getTestAge() {
        return testAge;
    }

    public void setTestAge(Integer testAge) {
        this.testAge = testAge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", testId=").append(testId);
        sb.append(", testRelId=").append(testRelId);
        sb.append(", testName=").append(testName);
        sb.append(", testAge=").append(testAge);
        sb.append("]");
        return sb.toString();
    }
}