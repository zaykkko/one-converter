package com.alura.converter.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import com.alura.converter.constants.Localization;
import com.alura.converter.controllers.CurrencyController;
import com.alura.converter.loader.CurrenciesLoader;
import com.alura.converter.model.CurrencyModel;

public class CurrencyConverter extends JFrame {
	private static final long serialVersionUID = -4551150801689964186L;

	static final int FRAME_WIDTH = 400, FRAME_HEIGHT = 220;

	private List<CurrencyModel> currencies;
	private CurrencyController currencyController;
	private Currency defaultLocale;
	private String csvFileName;

	private GroupLayout groupLayout;
	private JLabel inputLabel, comboBoxLabel, errorLabel;
	private JTextField amountInput;
	private JComboBox<CurrencyModel> comboBoxCurrencyPicker;
	private JCheckBox toLocalCheckbox;
	private JButton runConversion;

	public CurrencyConverter(WindowAdapter adapter, String localeCode, String csvFileName) {
		// super("Peronios a moneda extranjera");
		super(Localization.CURRENCY_CONVERTER);

		this.defaultLocale = Currency.getInstance(localeCode);
		this.csvFileName = csvFileName; 

		this.currencyController = new CurrencyController(this.defaultLocale);
		this.currencies = new CurrenciesLoader(csvFileName).loadAndParse();

		addWindowListener(adapter);

		Container container = getContentPane();
		groupLayout = new GroupLayout(container);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		setLayout(groupLayout);

		if (this.currencies.size() > 0) {
			buildWindow();
		} else {
			buildErrorWindow();
		}

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void buildWindow() {
		createComponents(false);
		createGroups(false);
		attachListeners();
	}

	private void buildErrorWindow() {
		createComponents(true);
		createGroups(true);
	}

	private void createComponents(Boolean isErrorWindow) {
		if (!isErrorWindow) {
			inputLabel = new JLabel(Localization.CURRENCY_INPUT_LABEL);
			inputLabel.setForeground(Color.BLACK);

			amountInput = new JTextField();

			comboBoxLabel = new JLabel(Localization.CURRENCY_COMBO_LABEL);

			comboBoxCurrencyPicker = new JComboBox<CurrencyModel>();
			this.currencies.forEach(currency -> comboBoxCurrencyPicker.addItem(currency));

			String checkBoxLabel = String.format(Localization.CURRENCY_CHECK_LABEL,
					this.defaultLocale.getDisplayName());
			toLocalCheckbox = new JCheckBox(checkBoxLabel, false);

			runConversion = new JButton(Localization.CURRENCY_RUN_BTN);
		} else {
			errorLabel = new JLabel(String.format(Localization.CURRENCY_ERROR_LABEL, csvFileName));
		}
	}

	private void attachListeners() {
		runConversion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startConversion();
			}
		});
	}

	private void createGroups(Boolean isErrorWindow) {
		if (!isErrorWindow) {
			groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(inputLabel)
					.addComponent(amountInput).addComponent(comboBoxLabel).addComponent(comboBoxCurrencyPicker)
					.addComponent(toLocalCheckbox).addComponent(runConversion));
			groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(inputLabel))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(amountInput))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(comboBoxLabel))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(comboBoxCurrencyPicker))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(toLocalCheckbox))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(runConversion)));
		} else {
			groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(errorLabel));
			groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(errorLabel));
		}
	}

	private void startConversion() {
		BigDecimal inputAmount;

		try {
			inputAmount = new BigDecimal(amountInput.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, Localization.NOT_NUMERIC_VALUE_ERROR, Localization.ERROR_MESSAGE_TITLE,
					JOptionPane.ERROR_MESSAGE);

			return;
		}

		CurrencyModel pickedCurrency = (CurrencyModel) comboBoxCurrencyPicker.getSelectedItem();

		Boolean isToLocal = toLocalCheckbox.isSelected();

		BigDecimal conversionResult = this.currencyController.convert(inputAmount, pickedCurrency, isToLocal);

		JOptionPane.showMessageDialog(this,
				String.format(Localization.CONVERSION_RESULT_MESSAGE,
						this.currencyController.format(conversionResult, pickedCurrency, isToLocal)),
				Localization.CONVERSION_RESULT_TITLE, JOptionPane.INFORMATION_MESSAGE);
	}
}
