package main.smart.llj.userinfo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.smart.llj.userinfo.domain.UserInfo;
import main.smart.llj.userinfo.service.impl.UserInfoServiceImpl;
import main.smart.llj.webservice.SmrzService;
import main.smart.llj.webservice.domain.LogInfoRequestBean;
import main.smart.llj.webservice.domain.LoginRequestBean;
import main.smart.llj.webservice.domain.RegisterRequestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/5/3.
 */
@Controller
@Path("userinfo")
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userinfoService;

    @GET
    @Path("/name/{i}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String testRest(@PathParam("i") String i) {
        String  str = "hello" + i;

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(str);
    }

    @GET
    @Path("/register/{info}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String doRegister(@PathParam("info") String info){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        RegisterRequestBean registerRequestBean = gson.fromJson(info,RegisterRequestBean.class);
        String ret = this.userinfoService.doRegister(registerRequestBean);
        return gson.toJson(ret);
    }

    @GET
    @Path("/login/{info}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String doLogin(@PathParam("info")String info){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        LoginRequestBean loginRequestBean = gson.fromJson(info, LoginRequestBean.class);
        String ret = this.userinfoService.doLogin(loginRequestBean);
        return gson.toJson(ret);
    }

    @GET
    @Path("/loginfo/{info}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String saveLogInfo(@PathParam("info")String info){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        LogInfoRequestBean loginfoRequestBean = gson.fromJson(info, LogInfoRequestBean.class);
        SmrzService smrzService = new SmrzService();
        String ret = smrzService.saveLoginfo(loginfoRequestBean);
        return gson.toJson(ret);
    }


    //获取用户
    @GET
    @Path("/userinfo/{info}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getUserInfo(@PathParam("info") String info){
        Gson gson = new Gson();
        Map<String, String> paraMap = gson.fromJson(info,new TypeToken<Map<String, String>>(){}.getType());
        String codetype = paraMap.get("type");
        String code = paraMap.get("code");
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        if ("phone".equals(codetype)){
            userInfoList =  this.userinfoService.queryForUserByPhone(code);
        }else
        if ("idnum".equals(codetype)){
            userInfoList = this.userinfoService.queryForUserByIdnum(code);
        }else
        if ("mail".equals(codetype)){
            userInfoList = this.userinfoService.queryForUserByMail(code);
        }
        else {
            return null;
        }

        return gson.toJson(userInfoList.get(0));
    }

}
