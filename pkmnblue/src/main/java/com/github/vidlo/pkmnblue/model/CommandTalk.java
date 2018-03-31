package com.github.vidlo.pkmnblue.model;
/**
 * Trieda PrikazTalk implementuje pro hru příkaz hovor.
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */
public class CommandTalk implements ICommand
{
    private static final String NAME = "hovor";
    private GamePlan plan;
    private Inventar inventar;

    /**
    * Konstruktor triedy hovor
    *
    * @param    plan herní plán, ve kterém se bude ve hře "hovorit s postavami" 
    */    
    public CommandTalk(GamePlan plan, Inventar inventar) {
        this.plan = plan;
        this.inventar = inventar;
    }

    /**
     * Provádí příkaz "hovor". Skuša hovorit s postavami. 
     * Ak sa postava nachadza v miestonosti, zobrazi text postavy.
     *
     * @param     parametry jako parametr obsahuje meno postavy
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    public String process(String... parametry) {
        if (parametry.length != 1) {
            return "\nPríkaz musí mať práve jeden parameter!\n";
        } 
        
        Location aktualna = plan.getCurrentLocation();
        String osloveny = parametry[0];
        Postava postava = aktualna.vyberPostavu(osloveny);
        
        if (postava == null){
            return "\nTato osoba tu nieje.\n";
        }
        
        if (postava.getMeno().equals("profesor") && !postava.getPrebeholRozhovor()) {
            plan.getInventar().vlozVec(new Vec("pokeball", true));
            return postava.hovor() + "\nPokélopta bola pridaná do inventára.\n";
        }
        else {
            return postava.hovor();
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
