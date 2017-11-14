package com.tasfe.framework.support.exception;

/**
 * @author  zhujunjie
 * @date 2017年11月8日
 * @version 1.0
 */
public class DaoException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3658216696054712345L;

	 /**
     * Error message.
     */
    public String message;
    
    public DaoException(){}

    public DaoException(String message){
    	super(message);
    }
    
    public DaoException(String message, Throwable e) {
    	super("tasfe dao  Exception(DAO层获取异常) : "+message,e);
    }
    
    public DaoException(Throwable e) {
    	super(e);
    }
}
