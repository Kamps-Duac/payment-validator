package com.assurant.ccd.ccvalidator.tests;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.freeasinguac.paymentvalidator.Payment;
import com.freeasinguac.paymentvalidator.PaymentFactory;
import com.freeasinguac.paymentvalidator.PaymentValidator;

@RunWith(Parameterized.class)
public class CreditCardNumberValidatorTests {


	@Parameters
	public static List<String> cardNumberTypedata() {
		return TestConstants.validCreditCardNumbers;
	}
	
	@Parameter
	public String number;

	@Test
	public void CheckIsValidPaymentForCreditCards() {
		Payment payment = new Payment(Payment.Type.CREDIT_CARD, number);
		PaymentValidator paymentValidator = PaymentFactory.getPayment(payment);
		assertNotNull(paymentValidator.isValidPayment(payment));
	}
}
