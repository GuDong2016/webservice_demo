package main.smart.llj.webservice;

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
 * <p>
 * Created by Grant on 15/12/22.
 * <p>
 * Created by Grant on 15/12/22.
 * <p>
 * Created by Grant on 15/12/22.
 * <p>
 * Created by Grant on 15/12/22.
 * <p>
 * Created by Grant on 15/12/22.
 * <p>
 * Created by Grant on 15/12/22.
 * <p>
 * Created by Grant on 15/12/22.
 */
/**
 * Created by Grant on 15/12/22.
 */

import main.smart.llj.loginfo.domain.LogInfo;
import main.smart.llj.loginfo.service.impl.LogInfoServiceImpl;
import main.smart.llj.userinfo.service.impl.UserInfoServiceImpl;
import main.smart.llj.webservice.domain.LogInfoRequestBean;
import main.smart.llj.webservice.domain.LoginRequestBean;
import org.apache.commons.lang.StringUtils;
import main.smart.llj.core.util.DateUtilZh;
import main.smart.llj.core.util.IdServer;
import main.smart.llj.userinfo.domain.UserInfo;
import main.smart.llj.webservice.domain.RegisterRequestBean;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;


import javax.jws.WebMethod;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试服务类<br>
 * Service注解为在使用Spring的packeage-scan功能进行自动装配<br>
 * WebService注解中可以不传递参数<br>
 * SOAPBinding中也可不传递参数，或者按照自己的需求进行更改
 */
@Service("smrzService")
@WebService(targetNamespace = "main.smart.llj.webservice")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SmrzService {
    @Autowired
    UserInfoServiceImpl userinfoService;
    @Autowired
    LogInfoServiceImpl logInfoService;

    @WebMethod
    public String userLogin(LoginRequestBean loginRequestBean) {
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

            userInfoList = this.getUserinfoService().queryForUserByPhone(userInfo.getPhone());
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
            userInfoList = this.getUserinfoService().queryForUserByIdnum(userInfo.getIdnum());
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
            userInfoList = this.getUserinfoService().queryForUserByMail(userInfo.getMail());
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

    @WebMethod
    public String userRegister(RegisterRequestBean registerRequestBean) {
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
            if ((this.getUserinfoService().queryForUserByIdnum(registerRequestBean.getIdnum()).size()) > 0) {
                return "9001";
                // "该身份证号已经被注册";
            } else {
                userInfo.setIdnum(registerRequestBean.getIdnum());
            }
        }
        if (StringUtils.isNotBlank(registerRequestBean.getMail())) {
            if ((this.getUserinfoService().queryForUserByMail(registerRequestBean.getMail()).size()) > 0) {
                return "9002";
                //"该邮箱已经被注册";
            } else {
                userInfo.setMail(registerRequestBean.getMail());
            }
        }
        if (StringUtils.isNotBlank(registerRequestBean.getPhone())) {
            if ((this.getUserinfoService().queryForUserByPhone(registerRequestBean.getPhone()).size()) > 0) {
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

        int flag = this.getUserinfoService().saveUser(userInfo);
        if (flag == 1) {
            return "0000";
        } else {
            return "9999";
        }
    }

    @WebMethod
    public String saveLoginfo(LogInfoRequestBean logInfoRequestBean) {
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
    }


    public UserInfoServiceImpl getUserinfoService() {
        return userinfoService;
    }

    public void setUserinfoService(UserInfoServiceImpl userinfoService) {
        this.userinfoService = userinfoService;
    }


}
