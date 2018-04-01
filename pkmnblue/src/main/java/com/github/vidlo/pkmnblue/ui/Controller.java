package com.github.vidlo.pkmnblue.ui;

import java.util.Observable;
import java.util.Observer;

import com.github.vidlo.pkmnblue.model.IGame;
import com.github.vidlo.pkmnblue.model.Vec;
import com.github.vidlo.pkmnblue.model.Location;
import com.github.vidlo.pkmnblue.model.Postava;
import com.github.vidlo.pkmnblue.model.Game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 *
 */
public class Controller extends VBox implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Vec> seznamVeciMistnost;
	@FXML private ListView<Location> seznamVychodu;
	@FXML private ListView<Postava> seznamPostav;
	@FXML private ListView<Vec> inventar;
	@FXML private ImageView uzivatel;
	@FXML private MenuItem novaHra;
	@FXML private MenuItem koniec;
	@FXML private MenuItem prirucka;
	
	@FXML private ImageView pokeball;
	@FXML private ImageView prut;
	@FXML private ImageView lopata;
	@FXML private ImageView syr;
	@FXML private ImageView zizaly;
	@FXML private ImageView magicarp;
	@FXML private ImageView pidgey;
	@FXML private ImageView rattata;
	
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
	
	@FXML public void setNovaHra() {
		vstupniText.setDisable(false);
		this.inicializuj(new Game());
	}
	
	@FXML public void setKoniec() {
		hra.setGameOver(true);
		if(hra.isGameOver()) {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);}
		}
	
	@FXML public void displayHelp() {
		Stage stage = new Stage();
        stage.setTitle("Príručka");
        WebView webview = new WebView();
        webview.getEngine().load(Controller.class.getResource("prirucka.htm").toExternalForm());
        stage.setScene(new Scene(webview, 500, 500));
        stage.show();
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	public void inicializuj(IGame hra) {
		seznamVeciMistnost.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamPostav.getItems().clear();
		vystup.setText(hra.getProlog());
		vystup.setEditable(false);
		this.hra = hra;
		seznamVeciMistnost.getItems().addAll(hra.getGamePlan().getCurrentLocation().getVeci());
		seznamVychodu.getItems().addAll(hra.getGamePlan().getCurrentLocation().getExitLocations());
		seznamPostav.getItems().addAll(hra.getGamePlan().getCurrentLocation().getPostavy());
		inventar.getItems().addAll(hra.getGamePlan().getInventar().getVeci2());
		uzivatel.setX(hra.getGamePlan().getCurrentLocation().getX());
		uzivatel.setY(hra.getGamePlan().getCurrentLocation().getY());
		hra.getGamePlan().addObserver(this);
		
		pokeball.setVisible(false);
		prut.setVisible(false);
		lopata.setVisible(false);
		zizaly.setVisible(false);
		syr.setVisible(false);
		magicarp.setVisible(false);
		pidgey.setVisible(false);
		rattata.setVisible(false);
	}
	
	

	@Override
	public void update(Observable arg0, Object arg1) {		
		seznamVeciMistnost.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamPostav.getItems().clear();
		inventar.getItems().clear();
		seznamVeciMistnost.getItems().addAll(hra.getGamePlan().getCurrentLocation().getVeci());
		seznamVychodu.getItems().addAll(hra.getGamePlan().getCurrentLocation().getExitLocations());
		seznamPostav.getItems().addAll(hra.getGamePlan().getCurrentLocation().getPostavy());
		inventar.getItems().addAll(hra.getGamePlan().getInventar().getVeci2());
		uzivatel.setX(hra.getGamePlan().getCurrentLocation().getX());
		uzivatel.setY(hra.getGamePlan().getCurrentLocation().getY());
		if(hra.getGamePlan().getInventar().obsahujeVec("pokeball")) {pokeball.setVisible(true);} else {pokeball.setVisible(false);}
		if(hra.getGamePlan().getInventar().obsahujeVec("rybarsky_prut")) {prut.setVisible(true);} else {prut.setVisible(false);}
		if(hra.getGamePlan().getInventar().obsahujeVec("lopata")) {lopata.setVisible(true);} else {lopata.setVisible(false);}
		if(hra.getGamePlan().getInventar().obsahujeVec("zizaly")) {zizaly.setVisible(true);} else {zizaly.setVisible(false);}
		if(hra.getGamePlan().getInventar().obsahujeVec("syr")) {syr.setVisible(true);} else {syr.setVisible(false);}
		if(hra.getGamePlan().getInventar().obsahujeVec("magicarp")) {magicarp.setVisible(true);} else {magicarp.setVisible(false);}
		if(hra.getGamePlan().getInventar().obsahujeVec("rattata")) {syr.setVisible(true);} else {rattata.setVisible(false);}
		if(hra.getGamePlan().getInventar().obsahujeVec("pidgey")) {pidgey.setVisible(true);} else {pidgey.setVisible(false);}
		
	}

}