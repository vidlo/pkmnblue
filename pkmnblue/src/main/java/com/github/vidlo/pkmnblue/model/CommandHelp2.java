/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vidlo.pkmnblue.model;

/**
 * Třída PrikazHelp implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Říha
 * @version    LS 2016/2017
 */
public class CommandHelp2 implements ICommand {
   private static final String NAME = "pomoc";

   /**
    * Konstruktor třídy pomoc
    *
    */    
    public CommandHelp2() {
        
    }

   /**
     * Vrací základní nápovědu po zadání příkazu "pomoec". Nyní se vypisuje
     * menšia nápoveda k hre.
     *
     * @return    napoveda ke hre
     */
    public String process(String... parametry) {
        return "\nBohužial si zaspal a nedostal si svojho prvého Pokémona."
        + "\nTvojou úlohou je teda vymyslieť ako Pokémona chytiť.\n"; 
    }

   /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *  
     * @return    nazev prikazu
     */
    public String getName() {
        return NAME;
     }

}
