/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vidlo.pkmnblue.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class SeznamPrikazu - eviduje seznam přípustných příkazů adventury.
 * Používá se pro rozpoznávání příkazů
 * a vrácení odkazu na třídu implementující konkrétní příkaz.
 * Každý nový příkaz (instance implementující rozhraní Prikaz) se
 * musí do seznamu přidat metodou vlozPrikaz.
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    LS 2016/2017
 */
public class ListOfCommands {
    // mapa pro uložení přípustných příkazů
    private  Map<String, ICommand> commandsMap;

    /**
     * Konstruktor
     */
    public ListOfCommands() {
        commandsMap = new HashMap<>();
    }
    
    /**
     * Vkládá nový příkaz.
     *
     * @param    prikaz instance třídy implementující rozhraní IPrikaz
     */
    public void insertCommand(ICommand command) {
        commandsMap.put(command.getName(), command);
    }

    /**
     * Vrací odkaz na instanci třídy implementující rozhraní IPrikaz,
     * která provádí příkaz uvedený jako parametr.
     *
     * @param     retezec klíčové slovo příkazu, který chce hráč zavolat
     * @return    instance třídy, která provede požadovaný příkaz
     */
    public ICommand getCommand(String retezec) {
        if (commandsMap.containsKey(retezec)) {
            return commandsMap.get(retezec);
        }
        else {
            return null;
        }
    }

    /**
     * Kontroluje, zda zadaný řetězec je přípustný příkaz.
     *
     * @param     commandName řetězec, který se má otestovat, zda je přípustný příkaz
     * @return    vrací hodnotu true, pokud je zadaný řetězec přípustný příkaz
     */
    public boolean checkCommand(String commandName) {
        return commandsMap.containsKey(commandName);
    }

    /**
     * Vrací seznam přípustných příkazů, jednotlivé příkazy jsou odděleny mezerou.
     *
     * @return    řetězec, který obsahuje seznam přípustných příkazů
     */
    public String getCommandNames() {
        String seznam = "";
        for (String slovoPrikazu : commandsMap.keySet()){
            seznam += " " + slovoPrikazu + ";";
        }
        return seznam;
    }
    
}
