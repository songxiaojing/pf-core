package com.topsec.bdc.platform.core.services;

import java.util.HashMap;
import java.util.Hashtable;

import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.topsec.bdc.platform.core.Activator;
import com.topsec.bdc.platform.log.PlatformLogger;


/**
 * @author baiyanwei
 * 
 *         Dec 27, 2013
 * 
 *         The service helper is very important part of platform ,all services will be started in this helper , helper can loading property for every service,and register and lookup a type of service from it. using helper to register a service into OSGI.
 */
public class ServiceHelper<T extends IService> {

    private static final PlatformLogger _logger = PlatformLogger.getLogger(ServiceHelper.class);
    //
    @SuppressWarnings("rawtypes")
    private static HashMap<IService, ServiceRegistration> _registerServiceMap = new HashMap<IService, ServiceRegistration>();

    /**
     * register a service into OSGI frame.
     * 
     * @param <T>
     * @param service
     * @return
     */
    public static <T extends IService> T registerService(T service) {

        return registerService(service, true, true);
    }

    /**
     * register one service into OSGI frame
     * 
     * @param service
     * @param isStartup
     * @param isPropertyes
     * @return
     */
    public static <T extends IService> T registerService(T service, boolean isStartup, boolean isPropertyes) {

        try {
            if (isPropertyes) {
                PropertyLoaderService propertyLoaderService = findService(PropertyLoaderService.class);
                // if propertyLoaderService is null, we will do nothing. Because
                // PropertyLoaderService is the first service that to be
                // registered
                if (propertyLoaderService != null) {
                    // inject the variables from configure file
                    propertyLoaderService.injectServiceProperties(service);
                }
            }
            if (isStartup == true) {
                service.start();
            }
            Hashtable<String, Object> properties = new Hashtable<String, Object>();
            ServiceInfo serviceAnnotation = service.getClass().getAnnotation(ServiceInfo.class);
            if (serviceAnnotation != null) {
                properties.put("description", serviceAnnotation.description());
            }
            @SuppressWarnings("rawtypes")
            ServiceRegistration registration = Activator.getContext().registerService(service.getClass().getName(), service, properties);

            _registerServiceMap.put(service, registration);
        } catch (Exception e) {
            _logger.exception(e);
        }
        return service;
    }

    /**
     * find one service from the OSGI frame.
     * 
     * @param clazz
     * @return
     */
    public static <T extends IService> T findService(Class<?> clazz) {

        return findService(clazz.getName());
    }

    /**
     * find one service from the OSGI frame.
     * 
     * @param clazz
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T extends IService> T findService(String clazz) {

        ServiceReference serviceReference = Activator.getContext().getServiceReference(clazz);
        if (serviceReference == null) {
            return null;
        }
        return (T) Activator.getContext().getService(serviceReference);
    }

    /**
     * unregister one service.
     * 
     * @param service
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static <T extends IService> void unregisterService(T service) throws Exception {

        if (service != null) {
            service.stop();
            ServiceRegistration unregisterService = null;
            synchronized (_registerServiceMap) {
                unregisterService = _registerServiceMap.remove(service);
            }
            if (unregisterService != null) {
                unregisterService.unregister();
            }
        }
    }
}
