/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exploration;

/**
 * À compléter
 * @author jo
 */
public class Joueur
{    
    private String nom;
    private LesOutils outils;
    private boolean perdant;
    private Position position;
    /**
     * Position du joueur
     * @return une référence à une position
     */
    
    
    
    public String getNom(){
        return this.nom;
    }
    public LesOutils getOutils(){
        return this.outils;
    }
    public boolean isPerdant(){
        return this.perdant;
    }
     public Position getPosition(){
         return this.position;
     }    
    /**
     * Salle où se situe le joueur
     * @return une référence à une salle
     */
     public Salle getSalle(){
        return this.position.getPlateau().getSalle(this.position);
    }
     
     
     
    private void setNom(String nom){
        this.nom = nom;
    }
    private void setNom(){
        setNom(Lire.S("Quel est votre nom"));
    }
    private void setOutils(LesOutils outils){
        this.outils = outils;
    }
    /**
     * Récupération d'un outil trouvé dans une salle
     * @param perdant
     */
    public void setPerdant(boolean perdant){
        this.perdant = perdant;
    }
    private void setPosition(Position position)
    {
        // affectation de la position en déclenchant la méthode d'entrée de la salle
    }  
    /**
     * Avance dans une direction donnée à condition que le mur soit ouvert dans cette direction
     * @param nom
     * @param position
     */
    
    
    public Joueur(String nom,Position position){
        //code
    }
    public Joueur(Position position){
        //code
    }
    
    
    
    public void avance(Direction d)
    {
        /*
            code : il faut gérer la sortie du plateau, le fait qu'un mur soit ou non ouvert et remettre à jour la position du joueur
        */
    }

    public void recupere(Outil o)
    {
        this.outils.ajoute(o);
        //Cette méthode est déclenchée par l'interaction avec l'outil
    }    
    

    public void lanceGrenade(Direction d)
    {
        /*
            La grenade est perdue si un mur est déjà ouvert dans la direction spécifiée
            Sinon,un accès est ajouté à la salle courante vers la salle contiguë dans la direction d (et réciproquement)
            et le joueur est « aspiré » dans la salle nouvellement ouverte. La réserve de grenades du joueur est décrémentée
        */
    }
 
    
    
}
