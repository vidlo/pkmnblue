/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

package com.github.vidlo.pkmnblue.main;

import com.github.vidlo.pkmnblue.model.Game;
import com.github.vidlo.pkmnblue.model.IGame;
import com.github.vidlo.pkmnblue.textui.TextUI;

/**
 * Třída  Start je hlavní třídou projektu, který představuje jednoduchou
 * textovou adventuru určenou k dalším úpravám a rozšiřování.
 *
 * @author     Jarmila Pavlíčková, Jan Říha
 * @version    ZS 2017/2018
 */
public class Start
{
    /**
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        IGame game = new Game();
        TextUI ui = new TextUI(game);
        ui.play();
    }

    private Start() {}
}
