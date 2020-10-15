/**
 * S3B
 * Gross Léo
 * Meyer Antoine
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Grille {
    /**
     * Constante qui définit le caractère correspondant à une case bloqué (qui ne peut pas être vide ou acceuillir une bille)
     */
    public static final char bloque = ' ';
    /**
     * Constante qui définit le caractère correspondant à une case vide
     */
    public static final char vide = '.';
    /**
     * Constante qui définit le caractère correspondant à une case avec une bille
     */
    public static final char bille = 'o';

    /**
     * Tableau à deux dimensions de caractère qui représente le tablier de jeu
     */
    private char[][] tablier;

    /**
     * Entier correspondant a la hauteur du tablier
     */
    private int hauteurTablier;

    /**
     * Entier correspondant a la largeur du tablier
     */
    private int largeurTablier;

    /**
     * Entier correspondant aux nombre de billes présente sur le tablier
     */
    private int billes ;

    /**
     * Constructeur vide d'une grille , initialise un tablier par défaut de 7*7 et associe sa hauteur, sa largeur et son nombre de billes a leurs attributs respectifs
     */
    public Grille(String filename){
        this.tablier= chargerTablier(filename);
        this.hauteurTablier = tablier[0].length;
        this.largeurTablier = tablier.length;
        this.billes=this.calculerNbBilles();
    }

    public Grille(){
        this.tablier= new char[][]{
                {' ', ' ', 'o', 'o', 'o', ' ', ' '},
                {' ', ' ', 'o', 'o', 'o', ' ', ' '},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', '.', 'o', 'o', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {' ', ' ', 'o', 'o', 'o', ' ', ' '},
                {' ', ' ', 'o', 'o', 'o', ' ', ' '}
        };
        this.hauteurTablier = tablier[0].length;
        this.largeurTablier = tablier.length;
        this.billes=this.calculerNbBilles();
    }

    /**
     * Cette méthode permet de dessiner dans la console le tablier.
     */
    public void dessinerTablier() {
        for(int a=0; a<hauteurTablier; a++){
            for(int b=0; b<largeurTablier; b++){
                System.out.print(tablier[b][a]+ " ");
            }
            System.out.println();
        }
    }

    /**
     * Cette méthode permet de récupérer la hauteur du tablier
     * @return int hauteur du tablier
     */
    public int getHauteurTablier() {
        return this.hauteurTablier;
    }

    /**
     * Cette méthode permet de récupérer la Largeur du tablier
     * @return int largeur du tablier
     */
    public int getLargeurTablier() {
        return this.largeurTablier;
    }

    /**
     * Cette méthode permet de récuperer le nombre de billes présentes dans le tablier
     */
    public int getBilles() {
        return this.billes;
    }

    /**
     * Cette méthode permet de faire un mouvement de jeu, c'est a dire déplacer une bille au dessus d'une autre et enlever la bille qui a été sautée.
     * @param m Tableau d'entier, permet de connaitre la position dans le tablier de la case de départ de la bille (sur la ligne 0), la case de la bille sautée (sur la ligne 1)et la case de destination de la bille (sur la ligne 2)
     */
    public void deplacerBilles(int[][] m) {
        tablier[m[0][0]][m[1][0]] = vide;
        tablier[m[0][1]][m[1][1]] = vide;
        tablier[m[0][2]][m[1][2]] = bille;
        billes = billes - 1;
    }

    /**
     * Cette méthode permet d'annuler un mouvement, elle remet les cases qui sont désignées dans le tableau passé en paramètre) dans l'état précédant l'appel de la méthode @see Grille.deplacerBilles(int[][]m)
     * @param m Tableau d'entier, permet de connaitre la position dans le tablier de la case de départ de la bille (sur la ligne 0), la case de la bille sautée (sur la ligne 1)et la case de destination de la bille (sur la ligne 2)
     */
    public void effacerBilles(int[][] m) {
        tablier[m[0][0]][m[1][0]] = bille;
        tablier[m[0][1]][m[1][1]] = bille;
        tablier[m[0][2]][m[1][2]] = vide;
        billes = billes + 1;
    }

    /**
     * Cette méthode permet d'initialiser un tableau de mouvement , en fonction de la position de la case de base (i,j) et d'une direction passée en paramètre
     * Valeurs possibles de d :
     *  1 - HAUT
     *  2 - BAS
     *  3 - GAUCHE
     *  4 - DRoITE
     * @param d direction
     * @param i colonne de la case de base
     * @param j ligne de la case de base
     */
    public int[][] choixMouvement(int i, int j, int d) {
        int[][] mouvement = new int[2][3];
        mouvement[0][0] = i;
        mouvement[1][0] = j;

        if(d==1){

            mouvement[0][1] = i;
            mouvement[1][1] = j-1;
            mouvement[0][2] = i;
            mouvement[1][2] = j-2;
        }
        if(d==2){
            mouvement[0][1] = i;
            mouvement[1][1] = j+1;
            mouvement[0][2] = i;
            mouvement[1][2] = j+2;
        }
        if(d==3){
            mouvement[0][1] = i-1;
            mouvement[1][1] = j;
            mouvement[0][2] = i-2;
            mouvement[1][2] = j;
        }
        if(d==4){
            mouvement[0][1] = i+1;
            mouvement[1][1] = j;
            mouvement[0][2] = i+2;
            mouvement[1][2] = j;
        }

        return mouvement;
    }

    /**
     * Cette méthode permet de connaitre le statut d'une case en fonction de la position passée en paramètre
     * @param i Colonne de la bille dans le tablier
     * @param j Ligne de la bille dans le tablier
     * @return Char Statut de la case {' ';'.';'o'}
     */
    public char getBille(int i , int j){
        return this.tablier[i][j];
    }

    /**
     *Cette méthode recalcule le nombre de billes présentes dans le tablier
     * @return nombre de billes dans le tablier
     */
    public int calculerNbBilles(){
        int ans = 0;
        for(int a=0; a<hauteurTablier; a++){
            for(int b=0; b<largeurTablier; b++){
                if(this.tablier[b][a] == bille ) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public char[][] getTablier() {
        return tablier;
    }

    public char[][] chargerTablier(String nom){
        char[][] nTab=null;
        try{
            File f = new File(nom);
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line = "";
            int nbLigne =  0,nbColonne = line.length();
            List<char[]> l = new ArrayList<char[]>();

            while((line= br.readLine())!=null){
                l.add(line.toCharArray());
            }
            nTab= new char[l.size()][l.get(0).length];
            for(int i=0;i<l.size();i++){
                for(int j=0;j<l.get(i).length;j++){
                    nTab[j][i]=l.get(i)[j];
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return nTab;
    }
}