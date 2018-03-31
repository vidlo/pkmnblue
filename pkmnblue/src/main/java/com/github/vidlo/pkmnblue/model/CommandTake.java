package com.github.vidlo.pkmnblue.model;
/**
 * Trieda PrikazTake implementuje pro hru příkaz vezmi.
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */
public class CommandTake implements ICommand
{
    private static final String NAME = "vezmi";
    private GamePlan plan;
    private Inventar inventar;

    /**
    * Konstruktor triedy vezmi
    *
    * @param    plan herní plán, ve kterém se bude ve hře "brat predmety" 
    */    
    public CommandTake(GamePlan plan) {
        this.plan = plan;
        this.inventar = plan.getInventar();
    }

    /**
     * Provádí příkaz "vezmi". Zkuša vziat predmet. 
     * Predmet nieje mozne vziat pokial hrac nema v invetary predmet pokeball.
     * Ak hrac vlastni pokeball, moze brat predmety, ktore su v mistnosti kde sa 
     * nachadza a patria do inventara.
     * 
     *
     * @param     parametry jako parametr nazov predmetu
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    public String process(String... parametry) {
        if (parametry.length == 0) {
            return "\nČo mám zobrať? Musíš zadať meno veci.\n";
        }

        String nazovVeci = parametry[0];
        Location aktualna = plan.getCurrentLocation();
        
        if (!plan.getInventar().getVeci().contains("pokeball")) {
            return "\nMal by si ísť navštíviť profesora Oaka.\n";
            }
        else {
            if (aktualna.obsahujeVec(nazovVeci)) {
                Vec vec = aktualna.vyberVec(nazovVeci); 
                if (inventar.vlozVec(vec)) {
                    return "\nVec " + nazovVeci + " bola pridaná do inventára.\n";
                    } else {
                        aktualna.vlozVec(vec);
                        return "\nVec nepatrí do batohu.\n";
                    }                
            }
            else {
                return "\nNič také tu nieje.\n";
            }}
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
