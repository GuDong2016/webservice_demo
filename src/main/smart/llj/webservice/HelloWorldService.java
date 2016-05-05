/**
 * Created by Grant on 15/12/22.
 */
package main.smart.llj.webservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试服务类<br>
 * Service注解为在使用Spring的packeage-scan功能进行自动装配<br>
 * WebService注解中可以不传递参数<br>
 * SOAPBinding中也可不传递参数，或者按照自己的需求进行更改
 */
//@Service("smrzService")
@WebService(targetNamespace = "main.smart.llj.webservice")
@SOAPBinding(style = Style.RPC)
public class HelloWorldService {

    /* 使用Spring来注入dao或service吧
    @Autowired
    private XXDao xxDao;*/

    /**
     * 接口方法必须加上WebMethod注解
     */
    @WebMethod
    public String sayHello() {
        System.out.println("Hello World!");
        return "Hello World!";
    }

    @WebMethod
    public String sayHelloToSomeone(String name) {
        return "hello " + name;
    }


}

