package com.cd.book.util;

import java.text.DecimalFormat;

public class NumberFormatter {

	public static double formatDouble(double value){
	DecimalFormat decimalFormat = new DecimalFormat("#.#");
	decimalFormat.format(value);
	Double doubleValue = Double.valueOf(decimalFormat.format(value));
	return doubleValue;
	
	}
}
