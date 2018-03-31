/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vidlo.pkmnblue.model;

/**
 * Třída PrikazMove implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Říha
 * @version    LS 2016/2017
 */
public class CommandMove implements ICommand {
    private static final String NAME = "chod";
    private GamePlan plan;

    /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public CommandMove(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "chod". Zkouší se vyjít do zadané lokace. Pokud lokace
     * existuje, vstoupí se do nového lokace. Pokud zadaná sousední lokace
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param     parametry jako parametr obsahuje jméno lokace (východu), do kterého se má jít.
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    public String process(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední lokace), tak ....
            return "\nKam mám ísť? Musíš zadať názov východu.";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousední lokace
        Location sousedniLokace = plan.getCurrentLocation().getExitLocation(smer);

        if (sousedniLokace == null) {
            return "\nTadiaľ sa ísť nedá!";
        }
        else {
            plan.setCurrentLocation(sousedniLokace);
            return sousedniLokace.getFullDescription();
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
