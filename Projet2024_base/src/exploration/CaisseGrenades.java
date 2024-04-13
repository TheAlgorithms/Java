/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exploration;

/**
 * À compléter
 * @author jo
 */
public class CaisseGrenades extends Objet
{
    private final static int MAX = 9;// nombre maximal dans les caisses de grenades trouvées dans les salles

    @Override
    public void interaction(Joueur j)
    {
        /* S'il reste des grenades dans cette réserve,
           propose au joueur d'en prendre un certain nombre,
           ce qui augmente les grenades du joueur et diminue d'autant cette réserve
        */
    }

    public CaisseGrenades(int nbGrenades)
    {
        super("<symbole>","nom");
        //suite code
    }
    
    public CaisseGrenades()
    {
        this(0/*nb de grenades tirées au hasard entre 1 et CaisseGrenades.MAX pour remplacer 0*/);
    }
}
