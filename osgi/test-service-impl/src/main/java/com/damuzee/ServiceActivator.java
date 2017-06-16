package com.damuzee;

import com.damuzee.service.IBaseService;
import com.damuzee.service.impl.BaseServiceImpl;
import org.osgi.framework.*;

import java.util.logging.Logger;

/**
 * Created by karkaw on 2016/8/11.
 */
public class ServiceActivator  implements BundleActivator ,ServiceListener,BundleListener {

    Logger logger = Logger.getLogger(ServiceActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        //Object baseService = context.getServiceReference(IBaseService.class.getName());
        IBaseService baseService = new BaseServiceImpl();
        context.registerService(IBaseService.class.getName(),baseService,null);
        logger.info("start baseService ...... " + baseService);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("stop ServiceActivator .........");
    }

    @Override
    public void bundleChanged(BundleEvent bundleEvent) {
        logger.info("bundleChanged ServiceActivator .........");
    }

    @Override
    public void serviceChanged(ServiceEvent serviceEvent) {
        logger.info("serviceChanged ServiceActivator .........");
    }
}
