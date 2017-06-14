package com.damuzee.service;

import com.damuzee.service.provider.IProviderService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by karkaw on 2016/8/7.
 */
public class GateWay {


    public Object invoke(String serviceName){

        //TODO 从Zookeeper查询服务的存根服务器

        //TODO 通过RMI实例化对象
        String host = "127.0.0.1";
        int port = 1099;
        try {
            Registry registry = LocateRegistry.getRegistry(host,port);
            IProviderService providerService = (IProviderService) registry.lookup("appService");
            Object result = providerService.invoke(serviceName);
            return result ;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object invoke(String serviceName,Object params){

        return  null ;
    }

}
