package com.damuzee.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/8.
 */
public class RegisterActivator implements BundleActivator {

    Logger logger = Logger.getLogger(RegisterActivator.class.toString());

    public static BundleContext bundle_context;

    @Override
    public void start(BundleContext context) {
        System.out.print("start ProviderActivator .....................");
        bundle_context = context;
    }

    @Override
    public void stop(BundleContext context) {
       logger.info("stop ProviderActivator .....................");
    }
}
