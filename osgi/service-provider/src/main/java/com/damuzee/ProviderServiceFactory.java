package com.damuzee;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import java.util.logging.Logger;

/**
 * Created by karkaw on 2016/8/10.
 */
public class ProviderServiceFactory implements ServiceFactory {

    Logger logger = Logger.getLogger(ProviderServiceFactory.class.getName());

    private int usageCounter = 0;

    public Object getService(Bundle bundle, ServiceRegistration serviceRegistration) {
        //logger.info("Create objectof HelloService for " + bundle.getSymbolicName());
        usageCounter++;
        logger.info("Number ofbundles using service " + usageCounter);
       // IProviderService providerService = new DefaultProviderService(bundle.getBundleContext());
        return null;
    }

    public void ungetService(Bundle bundle, ServiceRegistration serviceRegistration, Object o) {
       // logger.info("Release objectof HelloService for " + bundle.getSymbolicName());
        usageCounter--;
        logger.info("Number ofbundles using service " + usageCounter);
    }
}
