package com.alura.converter.model;

import java.io.Serializable;

public class ConversionOption implements Serializable {
	private static final long serialVersionUID = -6110905583812200336L;

	private int id = 0;

	private String label = "This is a localized textfield";

	public ConversionOption(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}
}
