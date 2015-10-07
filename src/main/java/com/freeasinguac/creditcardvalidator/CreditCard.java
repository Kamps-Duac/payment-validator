package com.freeasinguac.creditcardvalidator;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CreditCard{
	
	// Get from Properties
	private static Properties props;
	static {
		props = new Properties();
		InputStream in = CreditCard.class.getResourceAsStream("/config.properties");
		try {
			props.load(in);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Credit Card Bank Names	
	public enum Type {
		VISA
		,MASTERCARD
		,AMEX
		,DINERS
		,DISCOVER
		;}	

	//Credit Card Bank Regular Expressions
	public static final String VISA_REGEX = props.getProperty("ccRegex.visa");
	public static final String MASTER_REGEX = props.getProperty("ccRegex.master");
	public static final String AMEX_REGEX = props.getProperty("ccRegex.amex");
	public static final String DINERSCLUB_REGEX = props.getProperty("ccRegex.diners");
	public static final String DISCOVER_REGEX = props.getProperty("ccRegex.discover");
	
	// Create map of Regex to Credit Card
	HashMap<String, Type> createRegextoCCTypeMap() {
		HashMap<String, Type> ccRegexMap = 
				new HashMap<String, Type>();
		ccRegexMap.put(VISA_REGEX, Type.VISA);
		ccRegexMap.put(MASTER_REGEX, Type.MASTERCARD);
		ccRegexMap.put(AMEX_REGEX, Type.AMEX);
		ccRegexMap.put(DINERSCLUB_REGEX, Type.DINERS);
		ccRegexMap.put(DISCOVER_REGEX, Type.DISCOVER);
		return ccRegexMap;
	}
}