/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vidlo.pkmnblue.model;
import java.util.Collection;
import java.util.Collections;
import java.util.Observable;
/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny lokace, propojuje je vzájemně pomocí východů 
 * a pamatuje si aktuální lokaci, ve které se hráč právě nachází.
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */
public class GamePlan extends Observable {
    private Location currentLocation;
    private Inventar inventar;
    
    /**
     * Konstruktor který vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    public GamePlan() {
        inventar = new Inventar();
        prepareWorldMap();
    }

    /**
     * Vytváří jednotlivé lokace a propojuje je pomocí východů.
     * Jako výchozí aktuální lokaci nastaví domeček.
     */
    private void prepareWorldMap() {
        //vytvorenie jednotliych lokacii
        Location pokeDoupe = new Location("pokeDoupe","Hráčova detská izba v hráčovom dome", 1205.0, 305.0);
        Location kuchyna = new Location("kuchyna", "Kuchyňa v hráčovom dome", 1205.0, 345.0);
        Location pivnica = new Location("pivnica","Plesnivá pivnica v hráčovom dome", 1205.0, 387.0);
        Location palletTown = new Location("palletTown","Hráčovo rodné mesto", 913.0, 285.0);
        Location sklad = new Location("sklad","Sklad náradia", 866.0, 375.0);
        Location laboratorium = new Location("laboratorium","Laboratórium profesora Oaka", 1133.0, 475.0);
        Location routeJedna = new Location("routeJedna","Divočina za mestom zvaná Route 1", 997.0, 89.0);

        //priechody medzi lokaciami
        pokeDoupe.addExit(kuchyna);
        kuchyna.addExit(pokeDoupe);
        kuchyna.addExit(pivnica);
        kuchyna.addExit(palletTown);
        pivnica.addExit(kuchyna);
        palletTown.addExit(kuchyna);
        palletTown.addExit(sklad);
        palletTown.addExit(laboratorium);
        palletTown.addExit(routeJedna);
        sklad.addExit(palletTown);
        laboratorium.addExit(palletTown);
        routeJedna.addExit(palletTown);
        
        currentLocation = pokeDoupe;  // hra začíná v pokeDoupe
        
        //vytvorenie predmetov
        Vec magicarp = new Vec("magicarp", true);
        Vec rattata = new Vec("rattata", true);
        Vec pidgey = new Vec("pidgey", true);
        
        Vec prut = new Vec("rybarsky_prut", true);
        Vec syr = new Vec("syr", true);
        Vec lopata = new Vec("lopata", true);
        Vec zizaly = new Vec("zizaly", true);
        Vec zem = new Vec("zem", false, lopata, zizaly);
        Vec jazierko = new Vec("jazierko", false, prut, magicarp);
        Vec kamen = new Vec("kamen", false, syr, rattata);
        Vec strom = new Vec("strom", false, zizaly, pidgey);
        
        
        //vlozenie veci do lokacii
        kuchyna.vlozVec(syr);
        pivnica.vlozVec(prut);
        palletTown.vlozVec(zem);
        palletTown.vlozVec(jazierko);
        sklad.vlozVec(lopata);
        routeJedna.vlozVec(zem);
        routeJedna.vlozVec(kamen);
        routeJedna.vlozVec(strom);
        
        //vytvorenie postav
        Postava profesor = new Postava("profesor", "\nBohužial, ideš neskoro a všetkých Pokémonov som už rozdal. \nDám ti ale Pokéball aby si si mohol chytiť svojho vlastného.\n", "\nNa čo čakáš, vymysli spôsob ako chytiť Pokémona.\n");
        Postava mama = new Postava("mama", "\nDobré ráno miláčik. Mal by si sa poponáhľať za profesorom, už je veľa hodín.\n", "\nUž si navštívil profesora?\n");
        
        //vlozenie postav do lokacii
        laboratorium.vlozPostavu(profesor);
        kuchyna.vlozPostavu(mama);
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return    aktuální lokace
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    location nová aktuální lokace
     */
    public void setCurrentLocation(Location location) {
       currentLocation = location;
       setChanged();
       notifyObservers();
    }

    /**
     * Metoda getBatoh vrací odkaz na aktuální batoh
     *
     * @return batoh
     */
    public Inventar getInventar() {
        return inventar;
    }
    
    
    public boolean vyhra() {
        if(getInventar().getVeci().contains("magicarp") || getInventar().getVeci().contains("pidgey")|| getInventar().getVeci().contains("rattata")) {
            return true;
        }
	return false;
    }
}
