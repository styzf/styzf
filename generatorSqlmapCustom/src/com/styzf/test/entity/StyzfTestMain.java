package com.styzf.test.entity;

import java.util.Date;

public class StyzfTestMain {
    private String testId;

    private String testRelId;

    private String testName;

    private Integer testAge;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private String attribute4;

    private String attribute5;

    private String docCreatorId;

    private Date docCreateTime;

    private String docLastUpdateId;

    private Date docLastUpdateTime;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId == null ? null : testId.trim();
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

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1 == null ? null : attribute1.trim();
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2 == null ? null : attribute2.trim();
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3 == null ? null : attribute3.trim();
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4 == null ? null : attribute4.trim();
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5 == null ? null : attribute5.trim();
    }

    public String getDocCreatorId() {
        return docCreatorId;
    }

    public void setDocCreatorId(String docCreatorId) {
        this.docCreatorId = docCreatorId == null ? null : docCreatorId.trim();
    }

    public Date getDocCreateTime() {
        return docCreateTime;
    }

    public void setDocCreateTime(Date docCreateTime) {
        this.docCreateTime = docCreateTime;
    }

    public String getDocLastUpdateId() {
        return docLastUpdateId;
    }

    public void setDocLastUpdateId(String docLastUpdateId) {
        this.docLastUpdateId = docLastUpdateId == null ? null : docLastUpdateId.trim();
    }

    public Date getDocLastUpdateTime() {
        return docLastUpdateTime;
    }

    public void setDocLastUpdateTime(Date docLastUpdateTime) {
        this.docLastUpdateTime = docLastUpdateTime;
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
        sb.append(", attribute1=").append(attribute1);
        sb.append(", attribute2=").append(attribute2);
        sb.append(", attribute3=").append(attribute3);
        sb.append(", attribute4=").append(attribute4);
        sb.append(", attribute5=").append(attribute5);
        sb.append(", docCreatorId=").append(docCreatorId);
        sb.append(", docCreateTime=").append(docCreateTime);
        sb.append(", docLastUpdateId=").append(docLastUpdateId);
        sb.append(", docLastUpdateTime=").append(docLastUpdateTime);
        sb.append("]");
        return sb.toString();
    }
}