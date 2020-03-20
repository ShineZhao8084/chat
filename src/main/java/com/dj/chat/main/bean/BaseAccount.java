package com.dj.chat.main.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * base_account
 * @author 
 */
public class BaseAccount implements Serializable {
    private Long accountId;

    private String userName;

    private String loginName;

    /**
     * 生日
     */
    private Date birthday;

    private String loginPwd;

    private String phone;

    private String email;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 上次密码修改时间
     */
    private Date lastPwdModifyTime;

    /**
     * 密码重试次数
     */
    private Byte passwordRetry;

    /**
     * -1：已删除1：正常2：账号未启用3：账号已被锁定
     */
    private Byte state;

    private byte[] profilePhoto;

    private static final long serialVersionUID = 1L;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastPwdModifyTime() {
        return lastPwdModifyTime;
    }

    public void setLastPwdModifyTime(Date lastPwdModifyTime) {
        this.lastPwdModifyTime = lastPwdModifyTime;
    }

    public Byte getPasswordRetry() {
        return passwordRetry;
    }

    public void setPasswordRetry(Byte passwordRetry) {
        this.passwordRetry = passwordRetry;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BaseAccount other = (BaseAccount) that;
        return (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getLoginPwd() == null ? other.getLoginPwd() == null : this.getLoginPwd().equals(other.getLoginPwd()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getRegisterTime() == null ? other.getRegisterTime() == null : this.getRegisterTime().equals(other.getRegisterTime()))
            && (this.getLastLoginTime() == null ? other.getLastLoginTime() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))
            && (this.getLastPwdModifyTime() == null ? other.getLastPwdModifyTime() == null : this.getLastPwdModifyTime().equals(other.getLastPwdModifyTime()))
            && (this.getPasswordRetry() == null ? other.getPasswordRetry() == null : this.getPasswordRetry().equals(other.getPasswordRetry()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (Arrays.equals(this.getProfilePhoto(), other.getProfilePhoto()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getLoginPwd() == null) ? 0 : getLoginPwd().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getRegisterTime() == null) ? 0 : getRegisterTime().hashCode());
        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());
        result = prime * result + ((getLastPwdModifyTime() == null) ? 0 : getLastPwdModifyTime().hashCode());
        result = prime * result + ((getPasswordRetry() == null) ? 0 : getPasswordRetry().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + (Arrays.hashCode(getProfilePhoto()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accountId=").append(accountId);
        sb.append(", userName=").append(userName);
        sb.append(", loginName=").append(loginName);
        sb.append(", birthday=").append(birthday);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", lastPwdModifyTime=").append(lastPwdModifyTime);
        sb.append(", passwordRetry=").append(passwordRetry);
        sb.append(", state=").append(state);
        sb.append(", profilePhoto=").append(profilePhoto);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}