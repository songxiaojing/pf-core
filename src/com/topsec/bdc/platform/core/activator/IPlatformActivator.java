package com.topsec.bdc.platform.core.activator;

import org.osgi.framework.BundleActivator;

import com.topsec.bdc.platform.core.exception.PlatformException;
import com.topsec.bdc.platform.core.services.IService;


/**
 * 
 * Platform Activator.
 * 
 * Define the behavior of activator in platform.
 * 
 * @title IPlatformActivator
 * @package com.byw.dalek.platform.core.activator
 * @author baiyanwei
 * @version 1.0.0
 * @date May 23, 2015
 * 
 */
public interface IPlatformActivator extends BundleActivator {

    /**
     * register the service into bundles.
     * 
     * @param service
     * @param isStartup
     * @param isPropertyes
     * @return
     * @throws PlatformException
     */
    public <T extends IService> T registerService(T service, boolean isStartup, boolean isPropertyes) throws Exception;

    /**
     * register the service into bundles.
     * 
     * @param service
     * @return
     * @throws PlatformException
     */
    public <T extends IService> T registerService(T service) throws Exception;

    /**
     * unregister the service.
     * 
     * @param service
     * @return
     * @throws PlatformException
     */
    public <T extends IService> T unregisterService(T service) throws Exception;

    /**
     * unregister all services in this bundle.
     * 
     * @throws PlatformException
     */
    public void unregisterAllService() throws Exception;
}
