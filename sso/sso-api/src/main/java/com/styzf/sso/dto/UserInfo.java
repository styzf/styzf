package com.styzf.sso.dto;

import com.styzf.core.common.base.BaseDTO;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author styzf
 * @since 2018-08-14
 */
public class UserInfo extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String stUid;
    /**
     * 用户名
     */
    private String stName;
    /**
     * 用户电话
     */
    private String stPhone;
    /**
     * 用户邮件
     */
    private String stEmail;
    /**
     * 用户qq
     */
    private String stQq;
    /**
     * 用户微信
     */
    private String stWeChat;
    /**
     * 用户角色
     */
    private String stRole;
    /**
     * 用户vip
     */
    private Integer stVip;
    /**
     * 用户积分
     */
    private Integer stScore;
    /**
     * 备用字段
     */
    private String attribute1;
    /**
     * 备用字段
     */
    private String attribute2;
    /**
     * 备用字段
     */
    private String attribute3;
    /**
     * 备用字段
     */
    private String attribute4;
    /**
     * 备用字段
     */
    private String attribute5;
    
    public String getStUid() {
        return stUid;
    }

    public void setStUid(String stUid) {
        this.stUid = stUid;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStPhone() {
        return stPhone;
    }

    public void setStPhone(String stPhone) {
        this.stPhone = stPhone;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public String getStQq() {
        return stQq;
    }

    public void setStQq(String stQq) {
        this.stQq = stQq;
    }

    public String getStWeChat() {
        return stWeChat;
    }

    public void setStWeChat(String stWeChat) {
        this.stWeChat = stWeChat;
    }

    public String getStRole() {
        return stRole;
    }

    public void setStRole(String stRole) {
        this.stRole = stRole;
    }

    public Integer getStVip() {
        return stVip;
    }

    public void setStVip(Integer stVip) {
        this.stVip = stVip;
    }

    public Integer getStScore() {
        return stScore;
    }

    public void setStScore(Integer stScore) {
        this.stScore = stScore;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

}
