package com.github.vidlo.pkmnblue.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author     Luboš Pavlíček, Jan Říha
 * @version    LS 2016/2017
 */
public class ListOfCommandsTest
{
    private Game hra;
    private CommandTerminate prKoniec;
    private CommandMove prChod;
    
    @Before
    public void setUp() {
        hra = new Game();
        prKoniec = new CommandTerminate(hra);
        prChod = new CommandMove(hra.getGamePlan());
    }

    @Test
    public void testVlozeniVybrani() {
        ListOfCommands seznPrikazu = new ListOfCommands();
        seznPrikazu.insertCommand(prKoniec);
        seznPrikazu.insertCommand(prChod);
        assertEquals(prKoniec, seznPrikazu.getCommand("koniec"));
        assertEquals(prChod, seznPrikazu.getCommand("chod"));
        assertEquals(null, seznPrikazu.getCommand("napoveda"));
    }
    @Test
    public void testJePlatnyPrikaz() {
        ListOfCommands seznPrikazu = new ListOfCommands();
        seznPrikazu.insertCommand(prKoniec);
        seznPrikazu.insertCommand(prChod);
        assertEquals(true, seznPrikazu.checkCommand("koniec"));
        assertEquals(true, seznPrikazu.checkCommand("chod"));
        assertEquals(false, seznPrikazu.checkCommand("napoveda"));
        assertEquals(false, seznPrikazu.checkCommand("Koniec"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        ListOfCommands seznPrikazu = new ListOfCommands();
        seznPrikazu.insertCommand(prKoniec);
        seznPrikazu.insertCommand(prChod);
        String nazvy = seznPrikazu.getCommandNames();
        assertEquals(true, nazvy.contains("koniec"));
        assertEquals(true, nazvy.contains("chod"));
        assertEquals(false, nazvy.contains("napoveda"));
        assertEquals(false, nazvy.contains("Koniec"));
    }
    
}
