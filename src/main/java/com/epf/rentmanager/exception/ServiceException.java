package com.epf.rentmanager.exception;

public class ServiceException extends Exception {

    public ServiceException(){
        super();
    }

    public ServiceException(String msg)
    {
        super(msg);
    }


}
