package com.alura.converter.constants;

//Totalmente innecesario pero quería usar constantes xd
public final class Localization {
	public final static String CURRENCY_CONVERTER = "Conversor de moneda";
	public final static String TEMPERATURE_CONVERTER = "Conversor de fahrenheit a celsius";
	public final static String NOT_NUMERIC_VALUE_ERROR = "Por favor introduce un valor numérico.";
	public final static String ERROR_MESSAGE_TITLE = "¡Ocurrió un error!";
	public final static String CONVERSION_RESULT_MESSAGE = "Resultado de la conversión: %s.";
	public final static String CONVERSION_RESULT_TITLE = "Resultado de conversión";

	public final static String MAIN_MENU_WINDOW_TITLE = "Selección de tipo de conversor";
	public final static String MAIN_MENU_CONVERTER_PICKER = "Seleccione una opción de conversión";
	public final static String MAIN_MENU_OPEN_CONVERTER_BTN = "Abrir conversor";

	public final static String INVALID_DEFAULT_LOCALE_MESSAGE = "El código de país que haz ingresado es incorrecto: %s.\n\nSe utilizará la moneda por defecto: %s.";
	public final static String CURRENCY_INPUT_LABEL = "Ingrese la cantidad a convertir:";
	public final static String CURRENCY_COMBO_LABEL = "Elige una moneda:";
	public final static String CURRENCY_CHECK_LABEL = "Convertir esa moneda a %s";
	public final static String CURRENCY_RUN_BTN = "Convertir";
	public final static String CURRENCY_ERROR_LABEL = "<html>Ocurrió un error mientras leíamos el archivo que contiene información sobre los intercambios de moneda (%s).<br><br>Por favor, verificálo y vuelve a intentarlo.</html>";

	public final static String TEMP_F = "Fahrenheit";
	public final static String TEMP_C = "Celsius";
	public final static String TEMP_INPUT_LABEL = "Ingrese la temperatura en grados %s:";
	public final static String TEMP_CHECKBOX_LABEL = "Rotar conversiones";
	public final static String TEMP_RUN_LABEL = "Convertir a %s";
}
