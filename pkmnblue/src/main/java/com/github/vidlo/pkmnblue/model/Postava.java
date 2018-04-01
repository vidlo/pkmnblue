package com.github.vidlo.pkmnblue.model;
/**
 * Trieda Postava implementuje postavu s menom a monológom.
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */
public class Postava
{
    private String meno;
    private String monolog1;
    private String monolog2;
    private boolean chceHovorit;
    private boolean chceMenit;
    private boolean prebeholRozhovor;
    private boolean prebehlaVymena;
    private Vec chce;
    private Vec vlastni;
    private GamePlan plan;

    /**
     * Konstruktor triedy Postava, ktorá má veci na vymenu
     */
    public Postava(String meno, String monolog1, String monolog2, Vec chce, Vec vlastni)
    {
        this.meno = meno;
        this.monolog1 = monolog1;    
        this.monolog2 = monolog2;  
        this.chceHovorit = true;
        this.chceMenit = true;
        this.prebeholRozhovor = false;
        this.prebehlaVymena = false;
        this.chce = chce;
        this.vlastni = vlastni;
    }
    
    /**
     * Konstruktor triedy Postava, ktorá nemá veci na vymenu
     */
    public Postava(String meno, String monolog1, String monolog2)
    {
        this.meno = meno;
        this.monolog1 = monolog1;    
        this.monolog2 = monolog2;  
        this.chceHovorit = true;
        this.chceMenit = false;
        this.prebeholRozhovor = false;
    }        
    
    /**
     * Metoda vracia meno postavy
     *
     * @return meno postavy
     */
    public String getMeno() {
        return meno;
    }

    /**
     * Metoda vracia true ak si s postavou eště nehovoril
     */
    public boolean getPrebeholRozhovor() {
        return prebeholRozhovor;
    }

    /**
     * Metoda vracia instanciu triedy Vec, ktorú postava chce
     */
    public Vec getChce() {
        return chce;
    }
    
    /**
     * Metoda vracia instanciu triedy Vec, ktorú postava vlastní
     */    
    public Vec getVlastni() {
        return vlastni;
    }
    
    @Override
    public String toString() {
    // TODO Auto-generated method stub
    return getMeno();
    }
    
    /**
     * Metoda implementuje rozhovor
     * Ak rozhovor prebehol, vrátí se druhý monológ od postavy. 
     */
    public String hovor() {
        if(chceHovorit) {
            if(!prebeholRozhovor) {
                setRozhovorPrebehol(true);
                return monolog1;
            }
            else {
                return monolog2;
            }
        }
        else {
            return "\nTáto osoba s tebou hovoriť nebude.\n";
        }
    }
    
    
    /**
     * Metoda vracia monolog1 postavy
     *
     * @return monolog1
     */
    public String getMonolog1() {
        return monolog1;
    }
      
    /**
     * Metoda vrací monolog2 postavy
     *
     * @return monolog2
     */
    public String getMonolog2() {
        return monolog2;
    }
    
    /**
     * Metoda nastavuje true ak vymena (obdarovanie) prebehla
     */
      public void setVymenaPrebehla(boolean prebehla) {
        this.prebehlaVymena = prebehla;
    }
    
    /**
     * Metoda nastavuje true ak sa hráč s postavou hovoril
     */
    public void setRozhovorPrebehol(boolean prebehol) {
        this.prebeholRozhovor = prebehol;
    }
}
