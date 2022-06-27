package com.alura.converter.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.alura.converter.constants.Localization;
import com.alura.converter.model.ConversionOption;

public class ConverterPicker extends JFrame {
	private static final long serialVersionUID = 6355266473904190838L;

	static final int FRAME_WIDTH = 400, FRAME_HEIGHT = 150;

	private Properties config;

	private GroupLayout groupLayout;
	private JLabel labelConverterCombo;
	private JComboBox<ConversionOption> comboConverter;
	private JButton startProgramBtn;

	public ConverterPicker(Properties config) {
		super(Localization.MAIN_MENU_WINDOW_TITLE);

		this.config = config;

		/*
		 * File iconFile = new File("static/icons/favicon.png"); //Meh
		 * 
		 * try { BufferedImage icon = ImageIO.read(iconFile);
		 * 
		 * setIconImage(icon); } catch (IOException e) { setIconImage(null); }
		 */

		Container container = getContentPane();
		groupLayout = new GroupLayout(container);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		setLayout(groupLayout);

		createComponents();
		createGroups();
		attachListeners();

		// pack();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void createComponents() {
		labelConverterCombo = new JLabel(Localization.MAIN_MENU_CONVERTER_PICKER);
		labelConverterCombo.setForeground(Color.BLACK);

		comboConverter = new JComboBox<ConversionOption>();
		comboConverter.addItem(new ConversionOption(1, Localization.CURRENCY_CONVERTER));
		comboConverter.addItem(new ConversionOption(2, Localization.TEMPERATURE_CONVERTER));

		startProgramBtn = new JButton(Localization.MAIN_MENU_OPEN_CONVERTER_BTN);
	}

	private void attachListeners() {
		startProgramBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openSpecificConverter();
			}
		});
	}

	private void createGroups() {
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(labelConverterCombo).addComponent(comboConverter).addComponent(startProgramBtn));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelConverterCombo))
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(comboConverter))
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(startProgramBtn)));
	}

	private WindowAdapter closeEventHandler() {
		return new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				startProgramBtn.setEnabled(true);
			}
		};
	}

	private void openSpecificConverter() {
		ConversionOption targetConversor = (ConversionOption) comboConverter.getSelectedItem();

		startProgramBtn.setEnabled(false);

		switch (targetConversor.getId()) {
		case 1:
			new CurrencyConverter(closeEventHandler(), this.config.getProperty("defaultCurrency"),
					this.config.getProperty("csvFileName"));
			break;

		case 2:
			new TemperatureConverter(closeEventHandler());
			break;

		default:
			throw new RuntimeException("Wrong ConversionOption::getId for openSpecificConverter()");
		}
	}
}
