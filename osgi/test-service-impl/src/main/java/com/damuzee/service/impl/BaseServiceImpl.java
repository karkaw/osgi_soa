package com.damuzee.service.impl;

import com.damuzee.service.IBaseService;
import org.osgi.service.component.ComponentContext;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karka.w on 2016/8/7.
 */
public class  BaseServiceImpl implements IBaseService {

    private static final Logger LOGGER = Logger.getLogger(BaseServiceImpl.class.getName());

    public void bind(ComponentContext content) {
        LOGGER.log(Level.INFO, "BaseServiceImpl activated.");
      //  content.getBundleContext().registerService(String.valueOf(this.getClass()),this,null);
    }

    public void unbind(ComponentContext content) {
        LOGGER.log(Level.INFO, "BaseServiceImpl deactivated.");
    }

    public String getHelloWord(String name) {
        return "欢迎"+name+"的到来!!!";
    }
    public String getUser(String name,int age) {

        return "名字:"+name +"--->"+"年龄:"+age;
    }
}
