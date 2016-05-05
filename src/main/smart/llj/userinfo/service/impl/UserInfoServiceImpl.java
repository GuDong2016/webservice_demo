package main.smart.llj.userinfo.service.impl;

import main.smart.llj.core.util.DateUtilZh;
import main.smart.llj.core.util.IdServer;
import main.smart.llj.loginfo.domain.LogInfo;
import main.smart.llj.userinfo.dao.IUserInfoDao;
import main.smart.llj.userinfo.domain.UserInfo;
import main.smart.llj.userinfo.service.IUserInfoService;
import main.smart.llj.webservice.domain.LogInfoRequestBean;
import main.smart.llj.webservice.domain.LoginRequestBean;
import main.smart.llj.webservice.domain.RegisterRequestBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2016/4/27.
 */
@Service("userinfoService")
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public List<UserInfo> findall() {
        return this.userInfoDao.findall();
    }

    @Override
    public int saveUser(UserInfo userInfo) {
        Integer ret = this.userInfoDao.saveUser(userInfo);
        if (ret == null){
            return 0;
        }
        else {
            return ret.intValue();
        }
    }

    @Override
    public List<UserInfo> queryForUserByMail(String str) {
        return this.userInfoDao.queryForUserByMail(str);
    }

    @Override
    public List<UserInfo> queryForUserByIdnum(String str) {
        return this.userInfoDao.queryForUserByIdnum(str);
    }

    @Override
    public List<UserInfo> queryForUserByPhone(String str) {
        return this.userInfoDao.queryForUserByPhone(str);
    }

    @Override
    public String doLogin(LoginRequestBean loginRequestBean) {
        UserInfo userInfo = new UserInfo();
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        if ("phone".equals(loginRequestBean.getLogintype())) {
            //UserInfo userInfo = new UserInfo();
            if (StringUtils.isNotBlank(loginRequestBean.getPassword()) && StringUtils.isNotBlank(loginRequestBean.getPhone())) {
                userInfo.setPassword(loginRequestBean.getPassword());
                userInfo.setPhone(loginRequestBean.getPhone());
            } else {
                return "8001";
                //用户名或密码为空;
            }

            userInfoList = this.userInfoDao.queryForUserByPhone(userInfo.getPhone());
//            if (userInfoList.size() < 1) {
//                //用户名未注册或未绑定;
//                return "8002";
//            } else {
//                String dbPassword = userInfoList.get(0).getPassword();
//                if ((userInfo.getPassword().trim()).equals(dbPassword.trim())) {
//                    //登录成功
//                    return "8000";
//                } else {
//                    //密码不正确
//                    return "8003";
//                }
//            }
        } else if ("idnum".equals(loginRequestBean.getLogintype())) {
            //UserInfo userInfo = new UserInfo();
            if (StringUtils.isNotBlank(loginRequestBean.getPassword()) && StringUtils.isNotBlank(loginRequestBean.getIdnum())) {
                userInfo.setPassword(loginRequestBean.getPassword());
                userInfo.setIdnum(loginRequestBean.getIdnum());
            } else {
                return "8001";
                //用户名或密码为空;
            }
            //List<UserInfo> userInfoList = new ArrayList<UserInfo>();
            userInfoList = this.userInfoDao.queryForUserByIdnum(userInfo.getIdnum());
//            if (userInfoList.size() < 1) {
//                //用户名未注册或未绑定;
//                return "8002";
//            } else {
//                String dbPassword = userInfoList.get(0).getPassword();
//                if ((userInfo.getPassword().trim()).equals((dbPassword.trim()))) {
//                    //登陆成功;
//                    return "8000";
//                } else {
//                    //密码不正确;
//                    return "8003";
//                }
//            }


        } else if ("mail".equals(loginRequestBean.getLogintype())) {
            //UserInfo userInfo = new UserInfo();
            if (StringUtils.isNotBlank(loginRequestBean.getPassword()) && StringUtils.isNotBlank(loginRequestBean.getMail())) {
                userInfo.setPassword(loginRequestBean.getPassword());
                userInfo.setMail(loginRequestBean.getMail());
            } else {
                return "8001";
                //用户名或密码为空;
            }

            //List<UserInfo> userInfoList = new ArrayList<UserInfo>();
            userInfoList = this.userInfoDao.queryForUserByMail(userInfo.getMail());
//            if (userInfoList.size() < 1) {
//                //用户名未注册或未绑定;
//                return "8002";
//            } else {
//                String dbPassword = userInfoList.get(0).getPassword();
//                if ((userInfo.getPassword().trim()).equals((dbPassword.trim()))) {
//                    //登陆成功;
//                    return "8000";
//                } else {
//                    //密码不正确;
//                    return "8003";
//                }
//            }

        } else {
            //未知登录方式
            return "8999";
        }

        if (userInfoList.size() < 1) {
            //用户名未注册或未绑定;
            return "8002";
        } else {
            String dbPassword = userInfoList.get(0).getPassword();
            if ((userInfo.getPassword().trim()).equals(dbPassword.trim())) {
                //登录成功
                return "8000";
            } else {
                //密码不正确
                return "8003";
            }
        }
    }

    @Override
    public String doRegister(RegisterRequestBean registerRequestBean) {
        String id = IdServer.getIdForUUID();
        String createtime = DateUtilZh.getFormattedDate(new Date());
        String updatetime = DateUtilZh.getFormattedDate(new Date());
        String ifuse = "y";

        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setCreatetime(createtime);
        userInfo.setUpdatetime(updatetime);
        userInfo.setIfuse(ifuse);

        if (StringUtils.isNotBlank(registerRequestBean.getIdnum())) {
            if ((this.userInfoDao.queryForUserByIdnum(registerRequestBean.getIdnum()).size()) > 0) {
                return "9001";
                // "该身份证号已经被注册";
            } else {
                userInfo.setIdnum(registerRequestBean.getIdnum());
            }
        }
        if (StringUtils.isNotBlank(registerRequestBean.getMail())) {
            if ((this.userInfoDao.queryForUserByMail(registerRequestBean.getMail()).size()) > 0) {
                return "9002";
                //"该邮箱已经被注册";
            } else {
                userInfo.setMail(registerRequestBean.getMail());
            }
        }
        if (StringUtils.isNotBlank(registerRequestBean.getPhone())) {
            if ((this.userInfoDao.queryForUserByPhone(registerRequestBean.getPhone()).size()) > 0) {
                return "9003";
                //"该手机号已经被注册";
            } else {
                userInfo.setPhone(registerRequestBean.getPhone());
            }
        }
        if (StringUtils.isNotBlank(registerRequestBean.getPassword())) {
            userInfo.setPassword(registerRequestBean.getPassword());
        }
        if (StringUtils.isNotBlank(registerRequestBean.getPhonetype())) {
            userInfo.setPhonetype(registerRequestBean.getPhonetype());
        }
        if (StringUtils.isNotBlank(registerRequestBean.getRealname())) {
            userInfo.setRealname(registerRequestBean.getRealname());
        }

        int flag = this.userInfoDao.saveUser(userInfo);
        if (flag == 1) {
            return "0000";
        } else {
            return "9999";
        }
    }

    /*@Override
    public String saveLog(LogInfoRequestBean logInfoRequestBean) {
        LogInfo logInfo = new LogInfo();
        logInfo.setId(IdServer.getIdForUUID());
        logInfo.setCreatetime(DateUtilZh.getFormattedDate(new Date()));
        logInfo.setUpdatetime(DateUtilZh.getFormattedDate(new Date()));
        logInfo.setIfuse("y");

        logInfo.setPhone(logInfoRequestBean.getPhone());
        logInfo.setMail(logInfoRequestBean.getMail());
        logInfo.setIdnum(logInfoRequestBean.getIdnum());
        logInfo.setLogintype(logInfoRequestBean.getLogintype());
        logInfo.setIp(logInfoRequestBean.getIp());
        logInfo.setOsversion(logInfoRequestBean.getOsversion());
        logInfo.setOpertime(logInfoRequestBean.getOpertime());
        logInfo.setPhonemodel(logInfoRequestBean.getPhonemodel());
        logInfo.setLocationx(logInfoRequestBean.getLocationx());
        logInfo.setLocationy(logInfoRequestBean.getLocationy());
        logInfo.setOpertype(logInfoRequestBean.getOpertype());
        logInfo.setAppversion(logInfoRequestBean.getAppversion());

        int flag = this.logInfoService.saveLogInfo(logInfo);
        if(flag > 0){
            //存储日志成功
            return "0000";
        } else {
            //存储日志不成功
            return "0001";
        }
    }*/

    @Override
    public int updateOpenid(UserInfo userInfo) {
        return this.userInfoDao.updateOpenid(userInfo);
    }
}
