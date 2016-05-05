package main.smart.llj.userinfo.service;

import main.smart.llj.base.service.IService;
import main.smart.llj.userinfo.dao.IUserInfoDao;
import main.smart.llj.userinfo.domain.UserInfo;
import main.smart.llj.webservice.domain.LogInfoRequestBean;
import main.smart.llj.webservice.domain.LoginRequestBean;
import main.smart.llj.webservice.domain.RegisterRequestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2016/4/27.
 */

public interface IUserInfoService extends IService {

    public List<UserInfo> findall();
    public int saveUser(UserInfo userInfo);
    public List<UserInfo> queryForUserByIdnum(String str);
    public List<UserInfo> queryForUserByPhone(String str);
    public List<UserInfo> queryForUserByMail(String str);
    public String doLogin(LoginRequestBean loginRequestBean);
    public String doRegister(RegisterRequestBean registerRequestBean);
    //public String saveLog(LogInfoRequestBean logInfoRequestBean);

}
