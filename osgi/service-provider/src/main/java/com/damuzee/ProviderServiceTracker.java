package com.damuzee;

import com.damuzee.service.provider.IProviderService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import java.util.logging.Logger;

/**
 * Created by karkaw on 2016/8/10.
 */
public class ProviderServiceTracker extends ServiceTracker {
    Logger logger = Logger.getLogger(ProviderServiceFactory.class.getName());

    public ProviderServiceTracker(BundleContext context) {

        super(context, IProviderService.class.getName(), null);

    }

    public Object addingService(ServiceReference reference) {

        logger.info("Inside HelloServiceTracker.addingService " + reference.getBundle());

        return super.addingService(reference);

    }

    public void removedService(ServiceReference reference, Object service) {

        logger.info("Inside HelloServiceTracker.removedService " + reference.getBundle());

        super.removedService(reference, service);

    }
}
