package com.games.catan.exception;

public class CatanLogicException extends Exception {
	private static final long serialVersionUID = 5956367903241544334L;
	public CatanLogicException(String message){
		super(message);
	}
	public CatanLogicException(){
		super();
	}
	public CatanLogicException(String message,Throwable throwable){
		super(message,throwable);
	}
}
