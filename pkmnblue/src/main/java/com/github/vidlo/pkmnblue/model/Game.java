/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vidlo.pkmnblue.model;


/**
 * Toto je hlavní třída logiky aplikace. Třída vytváří instanci třídy
 * GamePlan, která inicializuje lokace hry a vytváří seznam platných
 * příkazů a instance tříd provádějící jednotlivé příkazy.
 *
 * Vypisuje uvítací a ukončovací text hry. Také vyhodnocuje jednotlivé
 * příkazy zadané uživatelem.
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */

public class Game implements IGame
{
    private ListOfCommands listOfCommands;
    private GamePlan gamePlan;
    private boolean gameOver = false;
    private Inventar inventar;
    

    /**
     * Vytváří hru a inicializuje lokace (prostřednictvím třídy GamePlan)
     * a seznam platných příkazů.
     */
    public Game()
    {
        gamePlan = new GamePlan();
        listOfCommands = new ListOfCommands();
        Inventar inventar = new Inventar();
        
        listOfCommands.insertCommand(new CommandHelp(listOfCommands));
        listOfCommands.insertCommand(new CommandHelp2());
        listOfCommands.insertCommand(new CommandMove(gamePlan));
        listOfCommands.insertCommand(new CommandTerminate(this));
        
        listOfCommands.insertCommand(new CommandTalk(gamePlan, inventar));
        listOfCommands.insertCommand(new CommandTake(gamePlan));
        listOfCommands.insertCommand(new CommandPrintInventory(gamePlan));
        listOfCommands.insertCommand(new CommandUse(gamePlan));
    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     *
     * @return úvodní zprávu pro hráče
     */
    public String getProlog()
    {
        return "\nAhoj! Veľmi ma teší.\n" +
               "Tento svet je obývaný stvoreniami zvanými aj Pokémoni.\n" +
               "Pre niektorých to sú domáci miláčikovia, iní s nimi bojujú. \n" +
               "\n" +
               "Ty ako hráč sa chceš stať trénerom Pokémonov.\n" +
               "V deň kedy si mal dostáť svojho prvého Pokémona si však zaspal a na teba už žiadny nezostal.\n" +
               "Tvojou úlohou ja za každú cenu získať svojho prvého Pokémona.\n" +
               "Budeš musieť vymyslieť spôsob ako sa k nejakému dostáť.\n" +
               "\n" +
               "Svet snov a dobrodružstiev s Pokémonmi čaká. Poď do toho!\n" +
               "\n" +
               gamePlan.getCurrentLocation().getFullDescription();
    }
    
    /**
     * Vrátí závěrečnou zprávu pro hráče.
     *
     * @return závěrečnou zprávu pro hráče
     */
    public String getEpilog()
    {
        return "\nVďaka, že ste si zahral Pokémon BlueJ version. S láskou, tvorca hry. ♥";
    }

    /**
     * Vrací true, pokud hra skončila.
     *
     * @return true, pokud hra již skončila; jinak false
     */
    public boolean isGameOver()
    {
        return gameOver;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo
     * příkazu a další parametry. Pak otestuje zda příkaz je klíčovým slovem,
     * např. jdi. Pokud ano spustí samotné provádění příkazu.
     *
     * @param line  text, který zadal uživatel jako příkaz do hry
     * @return řetězec, který se má vypsat na obrazovku
     */
    public String processCommand(String line)
    {
        String[] words = line.split("[ \t]+");
        String cmdWord = words[0];
        String[] parameters = new String[words.length - 1];

        for (int i = 0; i < parameters.length; i++) {
            parameters[i]= words[i+1];
        }

        String result = null;
        if (listOfCommands.checkCommand(cmdWord)) {
            ICommand command = listOfCommands.getCommand(cmdWord);
            result = command.process(parameters);
            if (gamePlan.vyhra() && gamePlan.getInventar().getVeci().contains("rattata")){
                gameOver = true;
                result = "\nSyr prilákal Rattatu a ty si ho chytil do Pokéballu."
                + "\nTeraz sa môžeš stať najlepším trenérom Pokémonov na svete.\n"
                + "To Be Continued..\n";
            } else {
                if (gamePlan.vyhra() && gamePlan.getInventar().getVeci().contains("magicarp")){
                    gameOver = true;
                    result = "\nPomocou rybárskeho prutu sa ti podarilo uloviť Magicarpa a chytil si ho do Pokéballu."
                    + "\nTeraz sa môžeš stať najlepším trenérom Pokémonov na svete.\n"
                    + "To Be Continued..\n";
                } else {
                    if (gamePlan.vyhra() && gamePlan.getInventar().getVeci().contains("pidgey")){
                        gameOver = true;
                        result = "\nŽížaly prilákali Pidgeyho a ty si ho chytil do Pokéballu."
                        + "\nTeraz sa môžeš stať najlepším trenérom Pokémonov na svete.\n"
                        + "To Be Continued..\n";
                    }}}
        } else {
            result = "\nNeviem, čo tým myslíš. Tento príkaz nepoznám.";
        }

        return result;
    }

    /**
     * Nastaví, že nastal konec hry, metodu využívá třída CommandTerminate,
     * mohou ji použít i další implementace rozhraní ICommand.
     *
     * @param příznak, zda hra již skončila
     */
    public void setGameOver(boolean konecHry)
    {
        this.gameOver = konecHry;
    }

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní lokace hry.
     *
     * @return herní plán
     */
    public GamePlan getGamePlan()
    {
        return gamePlan;
    }
    
    public Inventar getInventar(){
        return inventar;
     }
}
