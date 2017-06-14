package com.damuzee.test;

import com.damuzee.service.GateWay;
import com.damuzee.service.IBaseService;
import org.junit.Test;

/**
 * Created by karkaw on 2016/8/7.
 */
public class GatewayTest {

    @Test
    public void testInvote(){
        GateWay gateWay = new  GateWay();
        IBaseService baseService = (IBaseService) gateWay.invoke("com.damuzee.service.IBaseService");
        String result = baseService.getHelloWord("22312");
        System.out.print(result);
    }
}
