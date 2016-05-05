package main.smart.llj.vcode.domain;

import main.smart.llj.base.domain.BaseModel;

/**
 * Created by dell on 2016/5/3.
 */
public class VCode extends BaseModel{
    private String idnum;
    private String vcode;
    private String mvcode;

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getMvcode() {
        return mvcode;
    }

    public void setMvcode(String mvcode) {
        this.mvcode = mvcode;
    }
}
