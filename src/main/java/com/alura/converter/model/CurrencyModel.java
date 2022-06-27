package com.alura.converter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

public class CurrencyModel implements Serializable {
	private static final long serialVersionUID = 5524483037556992726L;

	private BigDecimal conversionRate;

	private Currency currencyLocale;

	public CurrencyModel(BigDecimal rate, String locale) {
		this.conversionRate = rate;
		this.currencyLocale = Currency.getInstance(locale);
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	public Currency getLocaleCurrency() {
		return currencyLocale;
	}

	@Override
	public String toString() {
		String displayName = currencyLocale.getDisplayName();

		return displayName.substring(0, 1).toUpperCase() + displayName.substring(1);
	}
}
