/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vidlo.pkmnblue.model;

/**
 * Třída PrikazTerminate implementuje pro hru příkaz konec.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Jan Riha
 * @version    LS 2016/2017
 *
 */
public class CommandTerminate implements ICommand {
    private static final String NAME = "koniec";

    private Game game;

    /**
     * Konstruktor třídy
     *
     * @param    hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public CommandTerminate(Game hra) {
        this.game = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKoniecHry(true))
     * jinak pokračuje např. při zadání "koniec a".
     *
     * @return zpráva, kterou vypíše hra hráči
     */

    public String process(String... parametry) {
        if (parametry.length > 0) {
            return "\nČo mám ukončiť? Nechápem, prečo si zadal druhé slovo.\n";
        }
        else {
            game.setGameOver(true);
            return "\nHra bola ukončena. Vráť sa skoro.\n";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     * @return    nazev prikazu
     */
    public String getName() {
        return NAME;
    }
}
