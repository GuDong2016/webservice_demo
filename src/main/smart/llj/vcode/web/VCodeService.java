package main.smart.llj.vcode.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.smart.llj.core.util.DateUtilZh;
import main.smart.llj.core.util.IdServer;
import main.smart.llj.core.util.MD5;
import main.smart.llj.userinfo.domain.UserInfo;
import main.smart.llj.userinfo.service.impl.UserInfoServiceImpl;
import main.smart.llj.vcode.domain.VCode;
import main.smart.llj.vcode.service.impl.VCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created by dell on 2016/5/3.
 */
@Controller
@Path("vcode")
public class VCodeService {
    @Autowired
    UserInfoServiceImpl userinfoService;
    @Autowired
    VCodeServiceImpl vCodeService;

    @GET
    @Path("/vcode/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getVCode(@PathParam("id") String id){
        String ret = null;
        VCode vCode = new VCode();

        Map<String, String> map = new HashMap<String, String>();

        List<UserInfo> uList = new ArrayList<UserInfo>();
        uList = this.userinfoService.queryForUserByIdnum(id);
        if (uList.size() < 1){
            map.put("msg", "用户未注册");
            map.put("vcode", null);
        }
        else {
            map.put("msg", "success");
            String rdnum = this.getSJS();

            vCode.setId(IdServer.getIdForUUID());
            vCode.setIdnum(id);
            vCode.setVcode(rdnum);
            vCode.setCreatetime(DateUtilZh.getFormattedDate(new Date()));
            vCode.setIfuse("y");
            try {
                vCode.setMvcode(MD5.crypt(id+rdnum));
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("vcode", vCode.getMvcode());
            int flag = this.vCodeService.saveVCode(vCode);
            if (flag != 1){
                map.put("msg", "获取验证码失败");
                map.put("vcode", null);
            }
        }


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public String getSJS(){
        String ret = "";

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            ret += random.nextInt(10);
        }

        return ret;
    }
}
