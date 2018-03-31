package com.github.vidlo.pkmnblue.model;
/**
 * Trieda PrikazPrintInventory implementuje pro hru příkaz inventar.
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */
public class CommandPrintInventory implements ICommand
{
   private static final String NAME = "inventar";
   private GamePlan plan;

   /**
    * Konstruktor triedy inventar
    *
    * @param    plan herní plán, ve kterém se bude ve hře "vypisovat inventar" 
    */    
    public CommandPrintInventory(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "inventar". Prikaz vypise zoznam veci, ktore hrac vlastni.
     *
     * @param     
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    public String process(String... parametry) {
        if (plan.getInventar().getVeci().equals("")) {
            return "\nInventár je prázdny.\n";
        }
        else {
            return "\nInventár obsahuje: " + plan.getInventar().getVeci() + "\n"; 
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
