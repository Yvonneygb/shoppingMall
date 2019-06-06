package com.wyu.infolib.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private Integer id;

    private String nickname;

    private String avatarUrl;

    private String userAccount;

    private String userPassword;

    private String userName;

    private Integer sex;

    private String userCollege;

    private String userIdCard;

    private String telephone;

    private Integer userType;

    private String remarks;

    private Integer authentication;

    private Date loginTime;

    private Date regTime;

    private String openid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserCollege() {
        return userCollege;
    }

    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege == null ? null : userCollege.trim();
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard == null ? null : userIdCard.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Integer authentication) {
        this.authentication = authentication;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", userCollege='" + userCollege + '\'' +
                ", userIdCard='" + userIdCard + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userType=" + userType +
                ", remarks='" + remarks + '\'' +
                ", authentication=" + authentication +
                ", loginTime=" + loginTime +
                ", regTime=" + regTime +
                ", openid='" + openid + '\'' +
                '}';
    }
}