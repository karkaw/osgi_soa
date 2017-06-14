package com.damuzee;

import com.damuzee.service.IBaseService;
import com.damuzee.service.impl.BaseServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.logging.Logger;

/**
 * Created by karkaw on 2016/8/11.
 */
public class ServiceActivator  implements BundleActivator{

    Logger logger = Logger.getLogger(ServiceActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        Object baseService = context.getServiceReference(IBaseService.class.getName());
        logger.info("start baseService ...... " + baseService);

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("stop ServiceActivator .........");
    }
}
