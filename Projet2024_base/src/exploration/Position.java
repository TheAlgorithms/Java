/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exploration;

/**
 *
 * @author jo
 */
public class Position
{
    private static char getNumChar(int num){return (char)('A'+num-1);}    

    /**
     *
     * @param num
     * @param p
     * @return
     */
    public static Position get(int num, Plateau p)
    {
        return new Position(num, p);
    }
    
    private Plateau plateau;

    /**
     *
     * @return
     */
    public Plateau getPlateau()
    {
        return this.plateau;
    }

    /**
     *
     * @param plateau
     */
    public void setPlateau(Plateau plateau)
    {
        this.plateau = plateau;
    }
     
    private int rang;

    /**
     *
     * @return
     */
    public int getRang()
    {
        return this.rang;
    }

    /**
     *
     * @param rang
     */
    public void setRang(int rang)
    {
        this.rang = rang;
    }
    
    /**
     *
     * @return
     */
    public int getLig(){return this.getRang()/this.getPlateau().getNbCol()+1;}

    /**
     *
     * @return
     */
    public char getCharLig(){return this.getNumChar(this.getLig());}

    /**
     *
     * @return
     */
    public int getCol(){return this.getRang()%this.getPlateau().getNbCol()+1;}

    /**
     *
     * @return
     */
    public char getCharCol(){return this.getNumChar(this.getCol());}
    
    /**
     *
     * @return
     */
    public boolean isValide()
    {
        return this.getRang()>=0 && this.getRang()<=this.getPlateau().getNbSalles();
    }
    
    /**
     *
     * @param rang
     * @param p
     */
    public Position(int rang, Plateau p)
    {
        this.setRang(rang);
        this.setPlateau(p);
    }

    /**
     *
     * @param lig
     * @param col
     * @param p
     */
    public Position(int lig, int col, Plateau p)
    {
        this((lig-1)*p.getNbCol()+col-1,p);
    }
    
    /**
     *
     * @param p
     * @return
     */
    public Position getSuivante(Direction p)
    {
        return new Position (this.getLig()+p.getdLig(),this.getCol()+p.getdCol(),this.getPlateau());
    }

    /**
     *
     * @return
     */
    public Position getSuivante()
    {
        return new Position(this.getRang()+1,this.getPlateau());
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + this.rang;
        return hash;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        final Position autre = (Position) o;
        return this.getRang()==autre.getRang();
    }
    
    @Override
    public String toString()
    {
        return this.getRang()+":("+this.getLig()+","+this.getCol()+")";
    }
}
