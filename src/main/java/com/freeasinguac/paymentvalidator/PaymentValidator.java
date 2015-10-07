package com.freeasinguac.paymentvalidator;
/**
 * An interface to validate Payment Type.
 * 
 *  As of version 1.0, the only implementation is for Credit Cards.
 */

public interface PaymentValidator {
		
	/**
	 * Checks if the provided Payment Account is Valid.
	 * @return Boolean True or False
	 */
	public Boolean isValidPayment(Payment payment);
}