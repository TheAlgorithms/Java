package exploration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 * Classe d'amorce qui créne une instance de jeu. À compléter avec d'autres objets/outils
 * @author jo
 */
public class Exploration
{

    /**
     * Création des différentes catégories d'objets et des proportions de leurs instances par rapport aux instances d'autres catégories dans le plateau
     * Par exemple, ici, dans le plateau il y aura deux fois plus de réserves d'énergie que de caisses de grenades
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Categorie
                grenades = new Categorie(4, CaisseGrenades.class),
                mines = new Categorie(3, Mine.class),
                energie = new Categorie(8, ReserveEnergie.class),
                scannerDirectionnel = new Categorie(10, ScannerUnidirectionnel.class),
                detecteurMine = new Categorie(2, DetecteurMines.class);
        Jeu p = new Jeu(5, 11, 50, grenades,mines,energie,scannerDirectionnel,detecteurMine);
    }
}
