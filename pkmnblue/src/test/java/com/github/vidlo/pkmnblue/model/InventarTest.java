package com.github.vidlo.pkmnblue.model;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class InventarTest.
 *
 * @author  Ondrej Vitko
 * @version ZS 2017/2018
 */
public class InventarTest
{
    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Testuje se vložení věci 
     */
    @Test
    public void testVlozVec()
    {
        Inventar inventar = new Inventar();
        Vec vec1 = new Vec("baterka", true);
        assertEquals(true, inventar.vlozVec(vec1));
    }

    /**
     * Testuje se kapacita inventara, která je max. 10 věcí, tj. jedenásta se již nevloží 
     */
    @Test
    public void testKapacitaBatohu()
    {
        Inventar inventar = new Inventar();
        Vec vec1 = new Vec("vec1", true);
        Vec vec2 = new Vec("vec2", true);
        Vec vec3 = new Vec("vec3", true);
        Vec vec4 = new Vec("vec4", true);
        Vec vec5 = new Vec("vec5", true);
        Vec vec6 = new Vec("vec6", true);
        Vec vec7 = new Vec("vec7", true);
        Vec vec8 = new Vec("vec8", true);
        Vec vec9 = new Vec("vec9", true);
        Vec vec10 = new Vec("vec10", true);
        Vec vec11 = new Vec("vec11", true);
        assertEquals(true, inventar.vlozVec(vec1));
        assertEquals(true, inventar.vlozVec(vec2));
        assertEquals(true, inventar.vlozVec(vec3));
        assertEquals(true, inventar.vlozVec(vec4));
        assertEquals(true, inventar.vlozVec(vec5));
        assertEquals(true, inventar.vlozVec(vec6));
        assertEquals(true, inventar.vlozVec(vec7));
        assertEquals(true, inventar.vlozVec(vec8));
        assertEquals(true, inventar.vlozVec(vec9));
        assertEquals(true, inventar.vlozVec(vec10));
        assertEquals(false, inventar.vlozVec(vec11));
    }

    /**
     * Test nepřenositelných věcí
     */
    @Test
    public void testVlozeniNeprenositelneVeci()
    {
        Inventar inventar = new Inventar();
        Vec vec1 = new Vec("motocykl", false);
        assertEquals(false, inventar.vlozVec(vec1));
    }
}
