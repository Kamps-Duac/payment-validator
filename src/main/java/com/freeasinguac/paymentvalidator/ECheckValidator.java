package com.freeasinguac.paymentvalidator;


public class ECheckValidator implements PaymentValidator {
	
	public Boolean isValidPayment(Payment payment) {
		return !payment.getAccount().isEmpty();
	}



}
