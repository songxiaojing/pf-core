package com.topsec.bdc.platform.core.activator;

import java.util.ArrayList;
import java.util.List;

import com.topsec.bdc.platform.core.services.IService;
import com.topsec.bdc.platform.core.services.ServiceHelper;
import com.topsec.bdc.platform.log.PlatformLogger;


/**
 * 
 * Platform Activator.
 * 
 * Extends PlatformActivator .
 * 
 * @title PlatformActivator
 * @package com.topsec.bdc.platform.core.activator
 * @author baiyanwei
 * @version
 * @date Jul 13, 2015
 * 
 */
public abstract class PlatformActivator implements IPlatformActivator {

    /**
     * thelogger.
     */
    final private static PlatformLogger _logger = PlatformLogger.getLogger(PlatformActivator.class);
    /**
     * Service collection.
     */
    protected List<IService> _serviceList = new ArrayList<IService>();

    @Override
    public <T extends IService> T registerService(T service, boolean isStartup, boolean isPropertyes) throws Exception {

        if (service == null) {
            return null;
        }
        //register service into OSGI framework.
        ServiceHelper.registerService(service, isStartup, isPropertyes);
        //
        synchronized (_serviceList) {
            this._serviceList.add(service);
        }
        return service;
    }

    @Override
    public <T extends IService> T unregisterService(T service) throws Exception {

        if (service == null) {
            return null;
        }
        //register service into OSGI framework.
        ServiceHelper.unregisterService(service);
        //
        synchronized (_serviceList) {
            this._serviceList.remove(service);
        }
        return service;
    }

    @Override
    public void unregisterAllService() throws Exception {

        // unregister all services.
        for (int i = 0; i < this._serviceList.size(); i++) {
            try {
                ServiceHelper.unregisterService(_serviceList.get(i));
            } catch (Exception e) {
                _logger.exception(e);
            }
        }
        //
        synchronized (_serviceList) {
            _serviceList.clear();
        }

    }

    @Override
    public <T extends IService> T registerService(T service) throws Exception {

        if (service == null) {
            return null;
        }
        //register service into OSGI framework.
        ServiceHelper.registerService(service);
        //
        synchronized (_serviceList) {
            this._serviceList.add(service);
        }
        return service;
    }

}
