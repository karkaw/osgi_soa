package com.damuzee.service.provider.impl;

import com.damuzee.service.provider.IProviderService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karkaw on 2016/8/9.
 */
public class DefaultProviderService implements IProviderService {

    Logger logger = Logger.getLogger(DefaultProviderService.class.getName());

    private  BundleContext context ;
    public  DefaultProviderService(BundleContext context){
        this.context = context ;
    }

    //private Dictionary<?, ?> dict = null;
    private ComponentContext componentContext;

    @Override
    public Object invoke(String serverName) throws RemoteException {
        logger.info("getAppName(方法被调用。。。。。。)");

        if (context == null){
              context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
        }
        ServiceReference reference  = context.getServiceReference(serverName);
        if(reference == null){
            logger.log(Level.FINER,"找不到该服务");
            return null;
        }
        Object service = context.getService(reference);

        return service;
    }

    protected void activate(ComponentContext componentContext) {
        logger.info("DefaultProviderService commpent activated .............");
        this.componentContext = componentContext;
        //dict = componentContext.getProperties();
    }

    protected void deactivate(ComponentContext cContext) {
        logger.info("DefaultProviderService commpent deactivate .............");
    }

    protected void bind() {
        logger.info("bind()...");
    }

    protected void unbind() {
        logger.info("unbind()...");
    }

}
