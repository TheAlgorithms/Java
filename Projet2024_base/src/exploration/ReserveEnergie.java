/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exploration;

/**
 *
 * @author jo
 */
public class ReserveEnergie extends Objet
{
    private final static int MAX = 9;

    
    @Override
    public void interaction(Joueur j)
    {
        /* S'il reste des unités d'énergie dans cette réserve,
           propose au joueur d'en prendre un certain nombre,
           ce qui augmente l'énergie du joueur et diminue d'autant cette réserve
        */
    }

    public ReserveEnergie(int disponible)
    {
        super("<symbole>","nom");
        //suite code
    }
    
    public ReserveEnergie()
    {
        this(0/*nb d'unités d'énergie tirées au hasard entre 1 et ReserveEnergie.MAX pour remplacer 0*/);
    }    
}
