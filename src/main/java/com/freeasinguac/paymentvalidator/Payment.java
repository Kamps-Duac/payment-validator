package com.freeasinguac.paymentvalidator;

public class Payment {
	//Payment Types
	public enum Type{
		CREDIT_CARD
		,ELECTRONIC_CHECK
		;}
	
	Type type;
	public Type getType() {
		return type;
	}

	public String getAccount() {
		return account;
	}

	private String account;
	
	public Payment(Type type, String account) {
		this.type = type;
		this.account = account;
	}
	
	
}
