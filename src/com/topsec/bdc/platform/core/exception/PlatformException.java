package com.topsec.bdc.platform.core.exception;

import java.io.Serializable;


/**
 * 
 * The platform's exception.
 * 
 * In all platform bundles, all bundles should throw the PlatformException by itself logic .
 * 
 * @title PlatformException
 * @package com.byw.dalek.platform.core.exception
 * @author baiyanwei
 * @version
 * @date Feb 18, 2015
 * 
 */
public class PlatformException extends Exception implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3950386951928420050L;
    /**
     * The message is about the exception
     */
    private String _exceptionMassage = null;

    public PlatformException(String cause, Throwable able) {

        super(cause, able);
        this._exceptionMassage = cause;
    }

    public PlatformException(String exceptionMassage) {

        super(exceptionMassage);
        this._exceptionMassage = exceptionMassage;
    }

    /**
     * get the exception message.
     * 
     * @return
     */
    public String getExceptionMassage() {

        return _exceptionMassage;
    }

    @Override
    public String toString() {

        return super.toString();
    }

}
