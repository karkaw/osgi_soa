package com.damuzee.test;

import com.damuzee.provider.IProviderService;
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
        IProviderService baseService = (IProviderService) gateWay.invoke("");
        System.out.print(baseService);
    }
}
