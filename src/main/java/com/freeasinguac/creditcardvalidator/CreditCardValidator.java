package com.freeasinguac.creditcardvalidator;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.freeasinguac.paymentvalidator.Payment;
import com.freeasinguac.paymentvalidator.PaymentValidator;

public class CreditCardValidator implements PaymentValidator{
	
	// Load configuration properties
	private static Properties props;
		static {
			props = new Properties();
			InputStream in = CreditCardValidator.class.getResourceAsStream("/config.properties");
			try {
				props.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	public Boolean isValidPayment(Payment creditCard) {
		return luhnCheckSum(creditCard.getAccount()) && isAcceptedCard(creditCard.getAccount());
	}
	
	 /**
	 * Uses the Luhn algorithm to detect accidental input errors and poorly
	 * fabricated credit card numbers. 
	 * <p>
	 * The Luhn algorithm doubles doubles every second account number, starting from the right.
	 * If the product of this doubling is greater than 9, then the sum of the two digits produced is used.
	 * The sum of all digits should be divisible by zero.
	 */
	// Private Methods
	private boolean luhnCheckSum(String creditCardNumber) {
		try {
			creditCardNumber = creditCardNumber.replaceAll("\\D", "");
			char[] creditCardNumberArry = creditCardNumber.toCharArray();

			int checkSum = 0;
			
			// starting from the right most digit (it is possible that not all cards are 16 digits)
			for (int i = creditCardNumberArry.length - 1; i >= 0; i--) {
 
				char ccDigit = creditCardNumberArry[i];
				
				// Every 2nd digit get's doubled
				if ((creditCardNumberArry.length - i) % 2 == 0) {
					int doubleddDigit = Character.getNumericValue(ccDigit) * 2;
					checkSum += (doubleddDigit % 9 == 0 && doubleddDigit != 0) ? 9
							: doubleddDigit % 9;

				} else {
					checkSum += Character.getNumericValue(ccDigit);
				}

			}

			return (checkSum != 0 && checkSum % 10 == 0);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}
	
	public CreditCard.Type getPaymentSource(String creditCardAccountNumber) {
		CreditCard cc = new CreditCard();
		HashMap<String, CreditCard.Type> ccMap = cc.createRegextoCCTypeMap();
		for (Map.Entry<String, CreditCard.Type> entry : ccMap.entrySet()){
			Pattern p = Pattern.compile(entry.getKey());
			Matcher m = p.matcher(creditCardAccountNumber);
			if (m.matches())
				return entry.getValue();
		}
		return null;
	}
	
	// Create a map to read what cards are accepted from config file
	private Boolean isAcceptedCard(String creditCardAccountNumber) {
		CreditCard.Type ccType = getPaymentSource(creditCardAccountNumber);
		Map<CreditCard.Type, Boolean> acceptedCardMap = 
				new EnumMap<CreditCard.Type, Boolean>(CreditCard.Type.class);
		acceptedCardMap.put(CreditCard.Type.VISA, 
				Boolean.valueOf(props.getProperty("cc.isVisaAccepted")));
		acceptedCardMap.put(CreditCard.Type.MASTERCARD, 
				Boolean.valueOf(props.getProperty("cc.isMasterAccepted")));
		acceptedCardMap.put(CreditCard.Type.AMEX, 
				Boolean.valueOf(props.getProperty("cc.isAmexAccepted")));
		acceptedCardMap.put(CreditCard.Type.DINERS, 
				Boolean.valueOf(props.getProperty("cc.isDinersAccepted")));
		acceptedCardMap.put(CreditCard.Type.DISCOVER, 
				Boolean.valueOf(props.getProperty("cc.isDiscoverAccepted")));
		
		return acceptedCardMap.get(ccType);
	}

}
