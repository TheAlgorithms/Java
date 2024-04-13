/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exploration;


/**
 * À compléter
 * @author jo
 */
public class DetecteurMines extends Outil
{
    @Override
    public void interaction(Joueur j){
     j.recupere(this);
    }
    public DetecteurMines()
    {
        super
        (
                ">*",
                "Detecteur de mines",
                "un détecteur de mines, qui permet de connaître le nombre total de mines qui se situent dans les salles contiguës. Chaque utilisation de ce détecteur consomme 3 unités d'énergie.",
                3//coût énergétique de la détection des mines
        );
    }    

    @Override
    public void activation(Joueur j)
    {
        int nb =0;
        
        j.getPosition().getSuivante();
        for (int i=0;i<8;i++){
            if((j.getSalle().getVoisine(Direction(i)).getContenu()).equals(Mine));
                nb=nb+1;
        }
        System.out.println(nb);
        
        
        
        /* explore les salles autour de la salle du joueur
        (direction à droite par rapport à la position de la salle du joueur,
        puis positions successives qui sont fondées sur les directions successives dans le sens trigonométrique : voir direction et position),
        compte les mines et affiche le résultat
        */
        
    }
}
