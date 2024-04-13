/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exploration;

import java.lang.reflect.InvocationTargetException;

/**
 * Classe complète
 * Les instances de Categorie créent des instance d'objets de classes variées (En parcourant un tableau de catégories, on peut créer des instances de classes différentes)
 * Cela permet de remplir le plateau avec des objets divers
 * @author jo
 */
public class Categorie
{

    private Class classe;
    public Class getClasse(){return this.classe;}
    private void setClasse(Class classe){this.classe = classe;}

    private int proportion;

    /**
     * Restitue la proportion d'objets de cette catégorie par rapport aux objets d'autres catégories
     * @return
     */
    public int getProportion(){return this.proportion;}
    private void setProportion(int proportion){this.proportion = proportion;
    }
    
    /**
     * Génère une instance d'objet de la classe donnée en utilisant le constructeur par défaut (sans paramètre)
     * @return une instance d'objet de la classe référencée dans l'attribut classe
     */
    public Objet getNouveau()
    {
        try
        {
            return (Objet)this.getClasse().getDeclaredConstructor().newInstance();
        } catch (SecurityException | IllegalArgumentException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            return null;
        } 
    }

    /**
     * Crée une instance de catégorie
     * @param proportion
     * @param classe classe de l'objet
     */
    public Categorie(int proportion, Class classe)
    {
        this.setProportion(proportion);
        this.setClasse(classe);
    }
}
