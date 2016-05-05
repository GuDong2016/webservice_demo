package main.smart.llj.loginfo.domain;

import main.smart.llj.base.domain.BaseModel;

/**
 * Created by Grant on 16/4/25.
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
public class LogInfo extends BaseModel {
    private String logintype;
    private String phone;
    private String mail;
    private String idnum;
    private String ip;
    private String osversion;
    private String opertime;
    private String phonemodel;
    private String locationx;
    private String locationy;
    private String opertype;
    private String appversion;


    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime;
    }

    public String getPhonemodel() {
        return phonemodel;
    }

    public void setPhonemodel(String phonemodel) {
        this.phonemodel = phonemodel;
    }

    public String getLocationx() {
        return locationx;
    }

    public void setLocationx(String locationx) {
        this.locationx = locationx;
    }

    public String getLocationy() {
        return locationy;
    }

    public void setLocationy(String locationy) {
        this.locationy = locationy;
    }

    public String getOpertype() {
        return opertype;
    }

    public void setOpertype(String opertype) {
        this.opertype = opertype;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }
}
