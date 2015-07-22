package com.topsec.bdc.platform.core.configuration;

import org.eclipse.core.runtime.IConfigurationElement;


/**
 * @author baiyanwei
 * 
 *         Mar 26, 2014
 * 
 *         The Platform Extension Handler.
 */
public interface IExtensionHandler {

    public void extend(IConfigurationElement configurationElement);

}
