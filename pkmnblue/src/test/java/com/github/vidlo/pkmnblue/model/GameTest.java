package com.github.vidlo.pkmnblue.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author     Jarmila Pavlíčková, Jan Říha
 * @version    LS 2016/2017
 */
public class GameTest {
    private Game hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Game();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
        
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Testuje sa jeden z možných koncov.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("pokeDoupe", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("chod kuchyna");
        assertEquals(false, hra1.isGameOver());
        assertEquals("kuchyna", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("chod palletTown");
        assertEquals(false, hra1.isGameOver());
        assertEquals("palletTown", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("chod laboratorium");
        assertEquals(false, hra1.isGameOver());
        assertEquals("laboratorium", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("hovor profesor");
        assertEquals(false, hra1.isGameOver());
        assertEquals("laboratorium", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("chod palletTown");
        assertEquals(false, hra1.isGameOver());
        assertEquals("palletTown", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("chod kuchyna");
        assertEquals(false, hra1.isGameOver());
        assertEquals("kuchyna", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("chod pivnica");
        assertEquals(false, hra1.isGameOver());
        assertEquals("pivnica", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("vezmi rybarsky_prut");
        assertEquals(false, hra1.isGameOver());
        assertEquals("pivnica", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("chod kuchyna");
        assertEquals(false, hra1.isGameOver());
        assertEquals("kuchyna", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("chod palletTown");
        assertEquals(false, hra1.isGameOver());
        assertEquals("palletTown", hra1.getGamePlan().getCurrentLocation().getName());
        hra1.processCommand("pouzi rybarsky_prut jazierko");
        assertEquals(true, hra1.isGameOver());
        hra1.processCommand("koniec");
        assertEquals(true, hra1.isGameOver());
    }
}
