package com.freeasinguac.paymentvalidator;

import com.freeasinguac.creditcardvalidator.CreditCardValidator;
import com.freeasinguac.paymentvalidator.Payment.Type;

public class PaymentFactory {

	public static PaymentValidator getPayment(Payment payment){
		switch(payment.type){
		case CREDIT_CARD: return new CreditCardValidator();
		case ELECTRONIC_CHECK: return new ECheckValidator();
		default: throw new AssertionError("Unknown type: " + payment.type);
		}
	}	
}
