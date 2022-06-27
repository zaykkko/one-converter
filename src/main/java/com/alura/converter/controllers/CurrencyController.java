package com.alura.converter.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Currency;

import com.alura.converter.model.CurrencyModel;

public class CurrencyController {
	private Currency defaultLocale;

	public CurrencyController(Currency localeCurrency) {
		this.defaultLocale = localeCurrency;
	}

	public BigDecimal convert(BigDecimal inputAmount, CurrencyModel pickedCurrency, Boolean isToLocale) {
		BigDecimal result;

		if (isToLocale) {
			result = inputAmount.multiply(pickedCurrency.getConversionRate());
		} else {
			result = inputAmount.divide(pickedCurrency.getConversionRate(), 4, RoundingMode.HALF_UP);
		}

		return result;
	}

	public String format(BigDecimal convertedValue, CurrencyModel pickedCurrency, Boolean isToLocale) {
		Currency activeLocale = isToLocale ? defaultLocale : pickedCurrency.getLocaleCurrency();

		DecimalFormat decimalFormatter = new DecimalFormat(
				String.format("%s #,##0.####", activeLocale.getCurrencyCode()));

		return decimalFormatter.format(convertedValue);
	}
}
