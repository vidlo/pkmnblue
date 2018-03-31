package com.github.vidlo.pkmnblue.model;
import java.util.*;
/**
 * Trieda Vec implementuje vec s názvom, prenosnosťou a vecami spojenými s príkazom použi.
 *
 * @author   Ondrej Vitko
 * @version  ZS 2017/2018
 */
public class Vec
{
   private String nazov;
   private boolean prenosnost;
   private boolean chceMenit;
   private boolean prebehlaVymena;
   private Vec chce;
   private Vec vlastni;
   
   /**
    * Konstruktor triedy Vec - vytvorí danú vec
    *
    * @param nazov              Identifikátor veci
    * @param prenositelnost     Určuje, či je možné vec zobrať a následne použiť
    */
   public Vec(String nazov, boolean prenosnost, Vec chce, Vec vlastni)
   {
       this.nazov = nazov;
       this.prenosnost = prenosnost;
       this.chceMenit = true;
       this.prebehlaVymena = false;
       this.chce = chce;
       this.vlastni = vlastni;
   }
   
   /**
    * Konstruktor triedy Vec - vytvorí danú vec
    *
    * @param nazov              Identifikátor veci
    * @param prenositelnost     Určuje, či je možné vec zobrať a následne použiť
    */
   public Vec(String nazov, boolean prenosnost)
   {
       this.nazov = nazov;
       this.prenosnost = prenosnost;
   }
    
   /**
    * Metóda getNazov vracia názov veci
    * 
    * @return názov veci
    */
   public String getNazov() {
       return nazov;
   }
    
   /**
     * Metoda getPrenosnost vracia True/False podľa prenosnosti
     * 
     * @return prenosnosť
     */
   public boolean getPrenosnost() {
       return prenosnost;
   }
   
   @Override
   public String toString() {
   // TODO Auto-generated method stub
   return getNazov();
   }
   
   /**
     * Metoda vracia instanci třiedy Vec, ktorú postava chce
     */
    public Vec getChce() {
        return chce;
    }
    
   /**
    * Metoda vracia instanci třiedy Vec, ktorú postava vlastní
    */    
   public Vec getVlastni() {
       return vlastni;
   }
   
   /**
     * Metoda nastavuje true ak vymena (použitie) prebehlo
     */
      public void setVymenaPrebehla(boolean prebehla) {
        this.prebehlaVymena = prebehla;
    }
   
   /**
    * Metoda equals
    * 
    * Metoda pro porovnání dvou prostorů. 
    * 
    * @param       o - Objekt (věc), který se má porovnávat s aktuální
    * @return      Pokud má zadaná věc stejný název s porovnávanou true, jinak false
    */  
   @Override
   public boolean equals(Object obj) {
       if (obj instanceof Vec) {
           Vec druha = (Vec)obj;
           return nazov.equals(druha.nazov);
       }
       else {
           return false;
       }
   }
   
   /**
    * Metoda hashCode
    * 
    * Vrací číselný identifikátor veci
    * 
    * @return  celočíselná hodnota charakteristická po danou instanci
    */
   @Override
   public int hashCode() {
       return nazov.hashCode();
   }
}
