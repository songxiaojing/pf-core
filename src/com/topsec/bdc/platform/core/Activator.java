package com.topsec.bdc.platform.core;

import org.osgi.framework.BundleContext;

import com.topsec.bdc.platform.core.activator.PlatformActivator;
import com.topsec.bdc.platform.core.message.MessagePreparerService;
import com.topsec.bdc.platform.core.node.NodeService;
import com.topsec.bdc.platform.core.services.PropertyLoaderService;
import com.topsec.bdc.platform.log.PlatformLogger;


/**
 * 
 * Activator of Platform core bundle.
 * 
 * Start activator.
 * 
 * @title Activator
 * @package com.byw.dalek.platform.core
 * @author baiyanwei
 * @version
 * @date May 23, 2015
 * 
 */
public class Activator extends PlatformActivator {

    /**
     * logger.
     */
    final private static PlatformLogger _logger = PlatformLogger.getLogger(Activator.class);
    /**
     * OSGI bundle.
     */
    private static BundleContext CONTEXT = null;

    /**
     * Get Activator.
     * 
     * @return
     */
    public static BundleContext getContext() {

        return CONTEXT;
    }

    @Override
    public void start(BundleContext context) throws Exception {

        Activator.CONTEXT = context;
        // register property loading service.
        this.registerService(new PropertyLoaderService(), true, false);

        // register message formatter service
        this.registerService(new MessagePreparerService());

        // register message formatter service
        this.registerService(new NodeService());
        //
        _logger.info("Platform core is started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {

        this.unregisterAllService();
        //
        _logger.info("Platform-core is stopped.");
    }
}
