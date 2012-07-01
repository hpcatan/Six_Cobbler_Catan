package com.games.catan.util;

public class UtilException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6731156380680613605L;
	public UtilException(){}
	public UtilException(String message){
		super(message);
	}
	public UtilException(String message,Throwable throwable){
		super(message,throwable);
	}
}
