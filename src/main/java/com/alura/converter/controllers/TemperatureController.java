package com.alura.converter.controllers;

import java.text.DecimalFormat;

public class TemperatureController {
	public Double convert(Double temperature, Boolean isFahrenheit) {
		if (isFahrenheit) {
			return this.fahrenheitToCelsius(temperature);
		}

		return this.celsiusToFarenheit(temperature);
	}

	public String format(Double temperature, Boolean isFahrenheit) {
		DecimalFormat decimalFormatter = new DecimalFormat(String.format("#,##0.####%s ", isFahrenheit ? "°F" : "°C"));

		return decimalFormatter.format(temperature);
	}

	private Double celsiusToFarenheit(Double celsius) {
		return (celsius * 1.8) + 32;
	}

	private Double fahrenheitToCelsius(Double fahrenheit) {
		return (fahrenheit - 32) * 0.5556;
	}
}
