package com.assurant.ccd.ccvalidator.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.freeasinguac.creditcardvalidator.CreditCard;
import com.freeasinguac.creditcardvalidator.CreditCardValidator;

@RunWith(Parameterized.class)
public class CreditCardIdentifierTests {

	@Parameters
	public static List<Object[]> ccTypeList() {
		return TestConstants.ccNumAndCardType;
	}
	
	private String cNumber;
	private CreditCard.Type cType;
	
	public CreditCardIdentifierTests(String number, CreditCard.Type type) {
		cNumber = number;
		cType = type;
	}
	
	static CreditCardValidator ccv;
	
	@BeforeClass
	public static void beforeClass() {
		ccv = new CreditCardValidator();
	}
	
	@Test
	public void IdentifiesCardTypeCorrectly() {
		CreditCard.Type expectedType = ccv.getPaymentSource(cNumber);
		assertEquals(expectedType, cType);
	}

}
