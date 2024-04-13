/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exploration;


/**
 *
 * @author jo
 */
public class ScannerUnidirectionnel extends Outil
{
    private static final int MARGE_ERREUR = 20;
    private int getMarge(){return ScannerUnidirectionnel.MARGE_ERREUR;}
    
    private Direction directionCourante;
    public Direction getDirectionCourante()
    {
        return this.directionCourante;
    }
    protected void setDirectionCourante()
    {
        do
        {
            this.directionCourante = new Direction(Lire.S("Entrez une direction en combinant 'h','b','g','d' ou 'haut','bas','gauche','droite'"));
        }while(!directionCourante.isValide());
    }
    public ScannerUnidirectionnel()
    {
        super
        (
                "<symbole à afficher dans le plateau>",
                "<Nom de l'outil pour les affichages>",
                "<Descriptif de la fonctionnalité de l'outil>",
                2//coût énergétique de l'utilisation du scanner
        );
    }    

    @Override
    public void activation(Joueur j)
    {
        /* recherche dans une direction demandée au joueur jusqu'à tomber sur une position null (en dehors de l'enceinte)
            ou une salle contenant un objet. Le nombre de salles parcourues est affiché à 20% près
        */
    }
}
