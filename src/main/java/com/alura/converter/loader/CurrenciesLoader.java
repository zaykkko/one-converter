package com.alura.converter.loader;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alura.converter.model.CurrencyModel;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CurrenciesLoader {
	static final String CSV_LINE_DELIMITER = ",";

	private String fileName;

	public CurrenciesLoader(String fileName) {
		this.fileName = fileName;
	}

	public List<CurrencyModel> loadAndParse() {
		List<CurrencyModel> currencies = new ArrayList<>();

		File csvFile = new File(fileName);
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.builder().addColumn("currencyCode").addColumn("conversionRate").build();
		csvMapper.enable(CsvParser.Feature.ALLOW_COMMENTS);

		try {
			MappingIterator<Map<String, String>> it = csvMapper.readerFor(Map.class).with(schema)
					.readValues(csvFile);

			while (it.hasNext()) {
				Map<String, String> rowMap = it.next();

				try {
					CurrencyModel currencyModel = new CurrencyModel(new BigDecimal(rowMap.get("conversionRate")),
							rowMap.get("currencyCode"));

					currencies.add(currencyModel);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return currencies;
	}
}
