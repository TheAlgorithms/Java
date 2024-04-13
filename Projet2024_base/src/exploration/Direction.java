/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exploration;

/**
 * Classe complète… à laquelle il est néanmoins possible d'ajouter des fonctionnalités
 * @author jo
 */
public class Direction
{
    // Directions prédéfinies
    public static final Direction DROITE = new Direction("droite");
    public static final Direction GAUCHE = new Direction("gauche");    
    public static final Direction HAUT = new Direction("haut");
    public static final Direction BAS = new Direction("bas");
    public static final Direction HAUT_GAUCHE = new Direction("haut gauche");
    public static final Direction HAUT_DROITE = new Direction("haut droite");
    public static final Direction BAS_GAUCHE = new Direction("bas gauche");
    public static final Direction BAS_DROITE = new Direction("bas droite");
    private static final Direction[] DIRECTIONS = {DROITE,HAUT_DROITE,HAUT,HAUT_GAUCHE,GAUCHE,BAS_GAUCHE,BAS,BAS_DROITE};

    /**
     * Restitue la direction prédéfinie correspondant au rang spécifié
     * @param rang Un entier quelconque (Toutes les valeurs sont légales)
     * @return Une des positions prédédinies
     */
    public static Direction getDirection(int rang){return Direction.DIRECTIONS[new Direction(rang).getRang()];}

    /**
     * Restitue la direction correspondant aux décalages de ligne et de colonne spécifiés
     * @param dLig décalage de ligne (En principe dans l'intervalle [-1,1] mais peut être erroné)
     * @param dCol décalage de colonne (En principe dans l'intervalle [-1,1] mais peut être erroné)
     * @return Une direction qui est une des prédéfinies si elle est valide ou une nouvelle direction dans le cas contraire
     */
    public static Direction getDirection(int dLig,int dCol)
    {   
        Direction nouvelle = new Direction(dLig,dCol);
        if(nouvelle.isValide()) return Direction.DIRECTIONS[nouvelle.getRang()];
        else return nouvelle;
    }

    /**
     * Restitue une direction à partir d'un texte chaîne qui peut combiner
     * l'indication d'une direction verticale (haut ou h, bas ou b) et
     * horizontale (droite ou d, gauche ou g).
     * La casse n'importe pas et les caractères "-"," " et "_" sont ignorés.
     * La direction obtenue est invalide (0,0) si le texte spécifié est incorrect
     * @param dirTexte
     * @return
     */
    public static Direction getDirection(String dirTexte)
    {
        Direction nouvelle = new Direction(dirTexte);
        return getDirection(nouvelle.getdLig(),nouvelle.getdCol());
    }

    /**
     *
     * @return
     */
    public boolean isValide()
    {
        boolean res = !(getdCol()==0 && getdLig()==0);
        if(res)res = getdCol()>=-1 && getdCol()<=1;
        if(res)res = getdLig()>=-1 && getdLig()<=1;
        return res;
    }

    private int dLig;// direction sur l'axe vertical
    /**
     * restitue le décalage en ligne (vertical) entre -1 (bas) et 1 (haut) s'il n'est pas erroné
     * @return un entier entre -1 et 1 — sauf si le décalage est invalide (Dans ce cas, les valeurs peuvent être différentes)
     */
    public int getdLig(){return this.dLig;}
    private void setdLig(int dLig){this.dLig = dLig;}

    private int dCol;// direction sur l'axe horizontal
    /**
     * restitue le décalage en colonne (horizontal) entre -1 (bas) et 1 (haut) s'il n'est pas erroné
     * @return
     */
    public int getdCol(){return this.dCol;}
    private void setdCol(int dCol){this.dCol = dCol;}


    /**
     * Restitue le rang du décalage dans le sens trigonométrique (droite -> 0, haut droite -> 1, haut -> 2…)
     * @return un entier entre 0 et 7
     */
    public int getRang()
    {
        if(!isValide())throw new RuntimeException("Direction invalide");
        double dCol = this.dCol,dLig= this.dLig;
        if(Math.abs(dCol*dLig)==1) dCol = dCol*Math.cos(Math.PI/4);
        // dans les angles, on se ramène à un multiple de PI/4
        int rang = (int)Math.round(Math.acos(dCol)/Math.PI*4);
        /*
         * On obtient un nombre entre 0 et 4. Il n'y a pas de distinction entre les directions suivant leur décalage vertical (négatif ou positif)
         */
        if(dLig<0) rang = 7-rang+1; // pour générer les pas correspondant à un décalage vertical négatif
        return rang;
    }
    
    /**
     * Restitue la direction suivante dans le sens trigonométrique
     * @return une direction
     */
    public Direction getSucc()
    {
        if(isValide())return Direction.getDirection(this.getRang()+1);
        return null;
    }

    /**
     * Restitue la direction précédente en (sens trigonométrique inverse)
     * @return une direction
     */
    public Direction getPred()
    {
        if(isValide())return Direction.getDirection(this.getRang()-1);
        return null;
    }
    
    private static String getNormalisation(String dirTexte)
    {
        String simplification = dirTexte.toLowerCase();
        simplification = simplification.replace("haut", "h");
        simplification = simplification.replace("bas", "b");
        simplification = simplification.replace("gauche", "g");
        simplification = simplification.replace("droite", "d");
        simplification = simplification.replaceAll("-", "");
        simplification = simplification.replaceAll(" ", "");
        simplification = simplification.replaceAll("_", "");
        return simplification;
    }
    /**
     * Restitue l'adresse d'une nouvelle direction ayant dLig et dCol comme décalages respectifs en ligne et en colonne
     * @param dCol entier en principe dans l'intervalle [-1,1] qui représente le décalage en ligne
     * @param dLig entier en principe dans l'intervalle [-1,1] qui représente le décalage en colonne
     */
    public Direction(int dLig, int dCol)
    {
        this.setdLig(dLig);
        this.setdCol(dCol);
    }
   /**
     * Direction par défaut à droite
     */
    public Direction()
    {
        this(DROITE.getdLig(),DROITE.getdCol());
    }

    /**
     * Construit la direction correspondant à la chaine en paramètre. Restitue la direction 0,0 (invalide) si le texte ne correspond pas à une direction
     * @param dirTexte chaîne qui peut combiner l'indication d'une direction verticale (haut ou h, bas ou b) et horizontale (droite ou d, gauche ou g). La casse n'importe pas.
     */
    public Direction(String dirTexte)
    {
        dirTexte = getNormalisation(dirTexte);
        if (dirTexte != null)
        {
            int taille = dirTexte.length();
            if (taille > 2){setdCol(0);setdLig(0);}
            else if (taille > 0)
            {
                setDxDy(dirTexte.charAt(0));
                if (taille > 1) setDxDy(dirTexte.charAt(1));
            }
        }
    }

    /**
     * Restitue une direction correspondant à un angle repéré par rang. angle = rangxPI/4 : 0,±8,±16,…->droite, 1,9,17,… -7,-15,…->haut droite…, 2,10,18,… -> haut…
     * @param rang entier positif ou négatif
     */
    public Direction(int rang)
    {
        this((int)Math.round(Math.sin(rang*Math.PI/4)),(int)Math.round(Math.cos(rang*Math.PI/4)));
    }

    private void setDxDy(char dir)
    {
        switch (dir)
        {
            case 'h' : this.dLig += 1;break;
            case 'b' : this.dLig -= 1;break;
            case 'd' : this.dCol += 1;break;
            case 'g' : this.dCol -= 1;
        }
    }

    /**
     * Restitue la combinaison de deux directions… qui peut être invalide
     * @param autre
     * @return une nouvelle direction
     */
    public Direction getCombinaison(Direction autre)
    {
        if(autre == null || !this.isValide() || !autre.isValide()) return null;
        return Direction.getDirection(this.getdLig()+autre.getdLig(),this.getdCol()+autre.getdCol());
    }
    
    /**
     * Restitue la direction inverse de la direction courante (son rang est augmenté/diminué de 4)
     * @return Une nouvelle direction inverse de la première
     */
    public Direction getInverse()
    {
        return Direction.getDirection(this.getRang()+4);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        if (isValide())
        {
            String[] tous = {"droite","haut droite","haut","haut gauche","gauche","bas gauche","bas","bas droite"};
            return tous[getRang()]+":("+this.getdLig()+","+this.getdCol()+")";
        }
        return "invalide";
    }

    @Override
    public boolean equals(Object autre)
    {
        if (this == autre) return true;
        if (autre == null)return false;
        if (getClass() != autre.getClass()) return false;
        Direction o = (Direction) autre;
        if(!this.isValide()||!o.isValide()) return false;
        return this.getRang()==o.getRang();
    }    
}