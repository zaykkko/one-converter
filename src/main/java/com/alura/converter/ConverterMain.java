package com.alura.converter;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import com.alura.converter.view.ConverterPicker;

public class ConverterMain {
	public final static String CONFIG_FILE = "config.properties";
	
	public static void main(String[] args) {
		Properties config = new Properties();
		//Fallback values if config.properties doesn't exists
		config.setProperty("csvFileName", "monedas.csv");
		config.setProperty("defaultCurrency", "ARS");
		
        try {
        	config.load(new FileReader(CONFIG_FILE));
        	
        	System.out.println("File found and load.");
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
		ConverterPicker pickerFrame = new ConverterPicker(config);
		pickerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// pickerFrame.setPreferredSize(new Dimension(450, 280));
	}
}
