package com.topsec.bdc.platform.core.services;

/**
 * @author baiyanwei
 * 
 *         Mar 26, 2014
 * 
 */
public interface ILife {

    /**
     * start service
     * 
     * @throws Exception
     */
    public void start() throws Exception;

    /**
     * stop service
     * 
     * @throws Exception
     */
    public void stop() throws Exception;
}
