package com.freeasinguac.paymentvalidator;

import org.apache.commons.validator.routines.CreditCardValidator;

public class Application {

	public static void main(String[] args) {
		
		// Run the JUnit Tests
//		JUnitCore junit = new JUnitCore();
//		junit.addListener(new TextListener(System.out));
//		junit.run(PaymentValidatorSuite.class);
//		CreditCardValidator ccv = new CreditCardValidator();
//		ccv.AMEX
//		System.out.println(Boolean.valueOf(props.getProperty("cc.isVisaAccepted")));
		
		// Using Apache validator to only accept AMEX and Visa
		CreditCardValidator cvv = new CreditCardValidator(CreditCardValidator.AMEX + CreditCardValidator.VISA);
		
		// Using Custom Validator to only accept AMEX and Visa
//		AcceptedCreditCardTypes acceptedCards = new AcceptedCreditCardTypes.Builder().
//				amexIsAccepted().visaIsAccepted().build();
		Payment payment = new Payment(Payment.Type.CREDIT_CARD,"378282246310005");
		PaymentValidator ccPayment = PaymentFactory.getPayment(payment);		
		System.out.println("Is the ccnumber valid? -> " + 
				ccPayment.isValidPayment(payment));
//		
//		System.out.println("The issuer from BIN is: " + 
//				ccPayment.getPaymentSource());
		
		// Need to override toString to output list of all properties and values...
		//System.out.println("The accepted forms of payment are: " + acceptedCards.toString());
		
//		boolean apacheValid = cvv.isValid(valMeConstructor.getCreditCardNumber());
//		System.out.println(String.format("According to apache, is the card valid? -> %b", apacheValid));
		
//		System.out.println("The card entered is accepted: " + 
//				valMeConstructor.isAcceptedCreditCardIssuer(acceptedCards));

	}
}
