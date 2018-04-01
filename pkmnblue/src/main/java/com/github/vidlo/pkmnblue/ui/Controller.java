package com.github.vidlo.pkmnblue.ui;

import java.util.Observable;
import java.util.Observer;

import com.github.vidlo.pkmnblue.model.IGame;
import com.github.vidlo.pkmnblue.model.Vec;
import com.github.vidlo.pkmnblue.model.Location;
import com.github.vidlo.pkmnblue.model.Postava;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky
 *
 */
public class Controller extends VBox implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Vec> seznamVeciMistnost;
	@FXML private ListView<Location> seznamVychodu;
	@FXML private ListView<Postava> seznamPostav;
	@FXML private ImageView uzivatel;
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
		seznamVeciMistnost.getItems().addAll(hra.getGamePlan().getCurrentLocation().getVeci());
		seznamVychodu.getItems().addAll(hra.getGamePlan().getCurrentLocation().getExitLocations());
		seznamPostav.getItems().addAll(hra.getGamePlan().getCurrentLocation().getPostavy());
		uzivatel.setX(hra.getGamePlan().getCurrentLocation().getX());
		uzivatel.setY(hra.getGamePlan().getCurrentLocation().getY());
		hra.getGamePlan().addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {		
		seznamVeciMistnost.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamPostav.getItems().clear();
		seznamVeciMistnost.getItems().addAll(hra.getGamePlan().getCurrentLocation().getVeci());
		seznamVychodu.getItems().addAll(hra.getGamePlan().getCurrentLocation().getExitLocations());
		seznamPostav.getItems().addAll(hra.getGamePlan().getCurrentLocation().getPostavy());
		uzivatel.setX(hra.getGamePlan().getCurrentLocation().getX());
		uzivatel.setY(hra.getGamePlan().getCurrentLocation().getY());
	}

}