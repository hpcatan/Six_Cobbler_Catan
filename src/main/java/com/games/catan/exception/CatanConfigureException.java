package com.games.catan.exception;

public class CatanConfigureException extends Exception{
	private static final long serialVersionUID = 3491431173335080124L;
	public CatanConfigureException(){
		super();
	}
	public CatanConfigureException(String message){
		super(message);
	}
	public CatanConfigureException(String message,Throwable throwable){
		super(message,throwable);
	}
}
