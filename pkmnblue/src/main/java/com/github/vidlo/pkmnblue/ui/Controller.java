package com.github.vidlo.pkmnblue.ui;

import com.github.vidlo.pkmnblue.model.IGame;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky
 *
 */
public class Controller extends GridPane {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	private IGame hra;
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() {
		String vystupPrikazu = hra.processCommand(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		
		if(hra.isGameOver()) {
			vstupniText.setDisable(true);}
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	public void inicializuj(IGame hra) {
		vystup.setText(hra.getProlog());
		vystup.setEditable(false);
		this.hra = hra;
	}

}