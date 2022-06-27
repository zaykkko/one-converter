package com.alura.converter.view;

import java.awt.Color;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import com.alura.converter.constants.Localization;
import com.alura.converter.controllers.TemperatureController;

public class TemperatureConverter extends JFrame {
	private static final long serialVersionUID = 4098452243306775760L;

	static final int FRAME_WIDTH = 400, FRAME_HEIGHT = 160;

	private TemperatureController temperatureController;
	private Boolean isFahrenheit = true;

	private GroupLayout groupLayout;
	private JLabel inputLabel;
	private JTextField temperatureInput;
	private JCheckBox switchConversionCheckBox;
	private JButton runConversion;

	public TemperatureConverter(WindowAdapter adapter) {
		super(Localization.TEMPERATURE_CONVERTER);

		this.temperatureController = new TemperatureController();

		addWindowListener(adapter);

		Container container = getContentPane();
		groupLayout = new GroupLayout(container);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		setLayout(groupLayout);

		createComponents();
		createGroups();
		attachListeners();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void setLabels() {
		String tempInputLabel = String.format(Localization.TEMP_INPUT_LABEL,
				isFahrenheit ? Localization.TEMP_F : Localization.TEMP_C);
		inputLabel.setText(tempInputLabel);

		String tempButtonLabel = String.format(Localization.TEMP_RUN_LABEL,
				isFahrenheit ? Localization.TEMP_C : Localization.TEMP_F);
		runConversion.setText(tempButtonLabel);
	}

	private void createComponents() {
		inputLabel = new JLabel();
		inputLabel.setForeground(Color.BLACK);

		temperatureInput = new JTextField();

		switchConversionCheckBox = new JCheckBox(Localization.TEMP_CHECKBOX_LABEL, false);

		runConversion = new JButton();

		setLabels();
	}

	private void createGroups() {
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(inputLabel)
				.addComponent(temperatureInput).addComponent(switchConversionCheckBox).addComponent(runConversion));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(inputLabel))
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(temperatureInput))
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(switchConversionCheckBox))
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(runConversion)));
	}

	private void attachListeners() {
		switchConversionCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				isFahrenheit = e.getStateChange() == ItemEvent.DESELECTED;

				setLabels();
			}
		});

		runConversion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startConversion();
			}
		});
	}

	private void startConversion() {
		Double inputTemperature;

		try {
			inputTemperature = Double.valueOf(temperatureInput.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, Localization.NOT_NUMERIC_VALUE_ERROR, Localization.ERROR_MESSAGE_TITLE,
					JOptionPane.ERROR_MESSAGE);

			return;
		}

		Double conversionResult = this.temperatureController.convert(inputTemperature, isFahrenheit);

		JOptionPane.showMessageDialog(this,
				String.format(Localization.CONVERSION_RESULT_MESSAGE,
						this.temperatureController.format(conversionResult, isFahrenheit)),
				Localization.CONVERSION_RESULT_TITLE, JOptionPane.INFORMATION_MESSAGE);
	}
}
