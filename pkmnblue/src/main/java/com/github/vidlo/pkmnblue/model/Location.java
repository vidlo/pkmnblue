/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vidlo.pkmnblue.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
/**
 * Trida Lokace - popisuje jednotlivé lokace (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Lokace" reprezentuje jedno místo (místnost, prostor, ...) ve scénáři hry.
 * Lokace může mít sousední lokace připojené přes východy. Pro každý východ
 * si lokace ukládá odkaz na sousedící lokace.
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */
public class Location {
    private String name;
    private String description;
    private Set<Location> exits;   // obsahuje susedné lokacie
    private Map<String, Vec> zoznamVeci;
    private Map<String, Postava> zoznamPostav;
    
    /**
     * Vytvoření lokace se zadaným popisem, 
     *
     * @param    name nazov lokace, jednoznačný identifikátor, jedno slovo nebo víceslovný název bez mezer
     * @param    description Popis lokace
     */
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashSet<Location>();
        zoznamVeci = new HashMap<String, Vec>();
        zoznamPostav = new HashMap<String, Postava>();
    }

    /**
     * Definuje východ z lokace (sousední/vedlejsi lokace). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední lokace uvedena
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední lokace).
     * Druhé zadání stejné lokace tiše přepíše předchozí zadání (neobjeví
     * se žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param    location lokace, která sousedi s aktualní lokací.
     *
     */
    public void addExit(Location location) {
        exits.add(location);
    }

    /**
     * Metoda equals pro porovnání dvou lokací. Překrývá se metoda equals ze
     * třídy Object. Dvě lokace jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param     o object, který se má porovnávat s aktuálním
     * @return    hodnotu true, pokud má zadaná lokace stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Location)) {
            return false;    // pokud parametr není typu Lokace, vrátíme false
        }
        // přetypujeme parametr na typ Lokace 
        Location druha = (Location) o;
        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.
        return (java.util.Objects.equals(this.name, druha.name));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object.
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.name);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací názov lokace (byl zadán při vytváření lokace jako parametr
     * konstruktoru)
     *
     * @return    názov lokace
     */
    public String getName() {
        return name;       
    }

    /**
     * Vrací "dlouhý" popis lokace
     *
     * @return    dlouhý popis lokace
     */
    public String getFullDescription() {
        return "\nSi v miestnosti/lokacií s názvom " + name + " [" + description + "]" +".\n"
                + getExitNames() + "\n"
                + vypisPostav() + "\n"
                + vypisVeci() + "\n";
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return    popis východů - názvů sousedních lokací
     */
    private String getExitNames() {
        String vracenyText = "Východy z lokácie:";
        for (Location sousedni : exits) {
            vracenyText += " " + sousedni.getName() + ";";
        }
        return vracenyText;
    }

    /**
     * Metoda vypisVeci vrací testový řetězec, který vypíše všechny věci v místnosti, pokud jsou viditelné
     *
     * @return      Seznam věcí
     */
    private String vypisVeci() {
        String vypis = "Veci:";
        for (String nazov : zoznamVeci.keySet()) {
             vypis += " " + nazov + ";";
        }
        return vypis;
    }

    /**
     * Metoda vypisPostav vrací textový řetězec postav, které se nachází v místnosti
     *
     * @return      Zoznam postav
     */
    private String vypisPostav() {
        String vypis = "\nPostavy:";
        for (String meno : zoznamPostav.keySet()) {
            vypis += " " + meno + ";";
        }
        return vypis;
    }
    
    /**
     * Vrací lokaci, která sousedí s aktuální lokací a jejíž název je zadán
     * jako parametr. Pokud lokace s udaným jménem nesousedí s aktuální
     * lokací, vrací se hodnota null.
     *
     * @param     locationName Jméno sousední lokace (východu)
     * @return    lokace, která se nachází za příslušným východem, nebo hodnota null, pokud lokace zadaného jména není sousedem.
     */
    public Location getExitLocation(final String locationName) {
        List<Object>hledaneLokace = 
            exits.stream()
                   .filter(sousedni -> sousedni.getName().equals(locationName))
                   .collect(Collectors.toList());
        if(hledaneLokace.isEmpty()){
            return null;
        }
        else {
            return (Location) hledaneLokace.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující lokace, se kterými ta lokace sousedí.
     * Takto získaný seznam sousedních lokací nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Lokace.
     *
     * @return    nemodifikovatelná kolekce lokací (východů), se kterými tato lokace sousedí.
     */
    public Collection<Location> getExitLocations() {
        return Collections.unmodifiableCollection(exits);
    }
    
    /**
     * Metoda vlozVec vloží vec do prostoru
     *
     * @param nazev         Názov vkládané věci
     * @return              True, pokud se ji podaří vložit
     */
    public boolean vlozVec(Vec nazov){
        zoznamVeci.put(nazov.getNazov(), nazov);
        return true;
    }

    /**
     * Metoda vyberVec vybere věc z místnosti
     *
     * @return              Vrátí vybranou věc
     */
    public Vec vyberVec(String meno) {
        Vec vyberana = null;
        for (String nazov : zoznamVeci.keySet()) {
            if (nazov.equals(meno)){
                vyberana = zoznamVeci.get(nazov);
                zoznamVeci.remove(nazov);
                break;
            }  
        }
        return vyberana;
    }
    
    public Collection<Vec> getVeci() {
    	return Collections.unmodifiableCollection(zoznamVeci.values());
    	}
   
    	@Override
    	public String toString() {
    	// TODO Auto-generated method stub
    	return getName();
    	}

    /**
     * Metoda obsahujeVec vrací zda se věc nachází v prostoru
     *
     * @return          True, pokud se prostoru nachází
     */
    public boolean obsahujeVec(String meno) {         
        for (String nazov : zoznamVeci.keySet()) {
            if (nazov.equals(meno)) {
                return true;
            }
        }     
        return false;  
    }

    /**
     * Metoda vlozPostavu vloží postavu do prostoru
     *
     * @param postava       Meno postavy, kterou chceme do prostoru vložit
     * @return              True, pokud se podaří postavu vložit
     */
    public boolean vlozPostavu(Postava postava) {
        zoznamPostav.put(postava.getMeno(), postava);
        return true;
    }

    /**
     * Meotda existujePostava vrací jméno postavy pokud se nachází v prostoru
     *
     * @return          Instance nalezené postavy podla mena
     */
    public Postava existujePostava(String meno) {
        return zoznamPostav.get(meno);
    }
    
    /**
     * Meotda existujePostava vrací meno postavy pokud se nachází v prostoru
     *
     * @return          Instance nalezené postavy podla mena
     */
    public Vec existujeVec(String nazov) {
        return zoznamVeci.get(nazov);
    }

    /**
     * Metoda vyberPostavu
     * 
     * Metoda vybere postavu ze seznamu 
     * 
     * @param meno
     * @return meno vybrané postavy
     */
    public Postava vyberPostavu(String meno) {
        Postava postava;
        if (zoznamPostav.containsKey(meno)){
            postava = zoznamPostav.get(meno);
            return postava;
        }
        return null;
    }
    
    /**
     * Metoda odstranPostavu odebere postavu z místnosti
     *
     * @param jmeno         Název odstaňované postavy
     * @return              True, pokud se podaří odstranit
     */
    public boolean odstranPostavu(String meno) {
        zoznamPostav.remove(meno);
        return true;
    }
}
