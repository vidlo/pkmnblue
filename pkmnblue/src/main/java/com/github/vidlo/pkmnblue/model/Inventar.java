package com.github.vidlo.pkmnblue.model;
import java.util.*;
/**
 * Trieda Inventar implementuje inventar ako ulozny pristor na veci
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */
public class Inventar
{
    private Map<String, Vec> zoznam;
    private static final int kapacita = 10;
    
    /**
     * Konstruktor triedy Inventar
     */
    public Inventar()
    {
        zoznam = new HashMap<>();
    }

    /**
     * Metóda pridá vec do inventára ak je voľné miesto
     *
     * @param vec, ktorá sa má pridať do inventára
     * @return true ak sa podarí vec pridať
     */
    public boolean vlozVec(Vec vec){
        if (vojdeSa()){
            if(vec.getPrenosnost()) {            
                zoznam.put(vec.getNazov(),vec);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Metoda odstraní vec z inventaru ak tam je
     *
     * @param nieco - vec ktoru chceme odstanit
     * @return true ak se odstraní
     */
    public boolean odhodVec(String vec) {
        boolean vyhodena = false;
        if (zoznam.containsKey(vec)) {
            vyhodena = true;
            zoznam.remove(vec);
        }
        return vyhodena;
    }

    /**
     * Metoda zistí či je vec v inventary alebo není
     *
     * @param nazov - nazov hledané věci
     * @return true ak je v inventary
     */
    public boolean obsahujeVec(String nazov) {
        return (zoznam.containsKey(nazov));
    }

    /**
     * Metoda najde vec, ktoru chceme získat
     * @return vec
     */
    public Vec getVec(String nazov) {
        return zoznam.get(nazov);
    }

    /**
     * Metoda vojdeSa zišťuje kolko vecí je v inventary
     *
     * @return true ak sa môže pridat eště dalšia vec
     */
    public boolean vojdeSa() {
        return (zoznam.size() < kapacita);
    }

    /**
     * Metoda pre zištenie obsahu invenrata
     *
     * @return zoznam vecí v inventary
     */
    public String getVeci() {
        String text = "";
        for (String nazov : zoznam.keySet()) {
            text +=  nazov + " ";
        }
        return text;
    }
}
