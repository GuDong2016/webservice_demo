package main.smart.llj.webservice.domain;

/**
 * Created by dell on 2016/4/27.
 */
public class LoginRequestBean {
    private String phone;
    private String mail;
    private String idnum;
    private String logintype;
    private String password;

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

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
