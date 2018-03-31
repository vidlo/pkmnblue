package com.github.vidlo.pkmnblue.model;
/**
 * Trieda PrikazUse implementuje pro hru příkaz pouzi.
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */
public class CommandUse implements ICommand
{
    private static final String NAME = "pouzi";
    private GamePlan plan;

    /**
    * Konstruktor triedy pouzi
    *
    * @param    plan herní plán, ve kterém se bude ve hře "pouzivat predmety" 
    */    
    public CommandUse(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "pouzi". Skusa pouzit predmet. Ak hrac vlastni predmet a predmet na 
     * ktory sa predmet pouziva je v miestnosti tak hrac dostane predmet kroty predmet vlastni
     * a pouzivany predmet ostane v inventary.
     *
     * @param     parametry jako parametr obsahuje nazov veci ktoru chcem pouzit a nazov veci 
     * na ktoru to chcem pouzit 
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    public String process(String... parametry) {
        if (parametry.length != 2) {
            return "\nNevím, co mám dát. Musíš uvést název věci!\n";  
        }
        
        String objekt = parametry[1];
        String vec = parametry[0];
        
        Vec aka = plan.getCurrentLocation().existujeVec(objekt);
        Vec davana = plan.getInventar().getVec(vec);
        
        if (vec == null) {
            return "\nTuto věc nemáš v batohu\n";
        }
        
        if (aka == null) {
            return "\nTento predmet tu nieje.\n";
        }
        
        if(aka.getChce().equals(davana)) {            
            plan.getInventar().vlozVec(aka.getVlastni());
            aka.setVymenaPrebehla(true);            
            return "\nPoužil si predmet " + davana.getNazov() + " na " + aka.getNazov() + 
                   ". \n" + "\nDostal si " + aka.getVlastni().getNazov() + ".\n";     
        }
        else {
            return "\nToto nejde použiť.\n";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return    nazov prikazu
     */
    public String getName() {
        return NAME;
    }
}
