/**
 * S3B
 * Gross Léo
 * Meyer Antoine
 */
import java.util.ArrayList;

public class SoloNoble {
    /**
     * Grille représentant le tablier de jeu d'un SoloNoble
     */
    private Grille g;

    /**
     * liste de toutes les cases qui change (1 coup de jeu valant 3 valeurs puisqu'on influt sur 3 billes à chaque coup)
     */
    private ArrayList<int[]> etape = new ArrayList<int[]>();

    /**
     * Constructeur vide qui initialise la grille du jeu et qui dessine le tablier dans la console pour montrer l'état initial;
     */
    public SoloNoble(String fn){
        this.g=new Grille(fn);
        g.dessinerTablier();
    }

    public SoloNoble(){
        this.g=new Grille();
        g.dessinerTablier();
    }

    /**
     * getter pour récupérer la grille à résoudre
     * @return
     */
    public Grille getGrille()
    {
        return this.g;
    }

    /**
     * getter pour récupérer chaque étape de la résolution
     * @return
     */
    public ArrayList<int[]> getEtape(){
        return this.etape;
    }

    /**
     * permet de dessiner les différentes étapes de la résolution
     */
    public void dessinerTableauEtape(char[][] w){
        for(int a=0; a<w.length; a++){
            for(int b=0; b<w[0].length; b++){
                System.out.print(w[b][a]+ " ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    /**
     * Cette méthode résoud la grille en donnant les instruction qu'il a fallut faire pour y parvenir
     * @param billes nombre de brille présentes dans la grille
     * @return boolean true si le nombre de billes est de 1 (et donc que la partie est gagnée ) sinon vaut false
     */
    public boolean resoudreSoloNoble(int billes){
        boolean fructeux = false;
        int i=0,j=0,k;
        if(billes==1){
            fructeux=true;
        }else{
            int largeur = g.getLargeurTablier();
            int hauteur = g.getHauteurTablier();
            while(!fructeux && i<largeur){
                while (!fructeux && j <hauteur){
                    char b = g.getBille(i,j);
                    if(b==Grille.bille){
                        int mvt[][];
                        k=1;
                        while(k<5 && !fructeux){
                            // Choix du mouvement
                            mvt=g.choixMouvement(i,j,k);
                            // On vérifie si on ne sort pas de la grille
                            if(mvt[0][2]>=0 && mvt[0][2]<g.getLargeurTablier()&& mvt[1][2]>=0 && mvt[1][2]<g.getHauteurTablier()){
                                // On vérifie que le saut soit possible
                                char saute = g.getBille(mvt[0][1],mvt[1][1]),dest= g.getBille(mvt[0][2],mvt[1][2]);
                                if(g.getBille(mvt[0][1],mvt[1][1])==Grille.bille&&g.getBille(mvt[0][2],mvt[1][2])==Grille.vide){
                                    // on fait le mouvement
                                    g.deplacerBilles(mvt);
                                    int nb = g.calculerNbBilles();
                                    fructeux=resoudreSoloNoble(nb);
                                    //g.dessinerTablier();
                                    if(!fructeux){
                                        g.effacerBilles(mvt);
                                    }else{
                                        //ajoute dans le tableau des etapes
                                        int[] aa = {mvt[0][0],mvt[1][0]};
                                        int[] bb = {mvt[0][2],mvt[1][2]};
                                        int[] cc = {mvt[0][1],mvt[1][1]};
                                        etape.add(aa);
                                        etape.add(bb);
                                        etape.add(cc);
                                        //écrire
                                        //System.out.println("Prendre la bille en ("+mvt[0][0]+","+mvt[1][0]+") et la mettre en ("+mvt[0][2]+","+mvt[1][2]+") afin d'enlever la bille en ("+mvt[0][1]+","+mvt[1][1]+")");
                                    }
                                }
                            }
                            k++;
                        }
                    }
                    j++;
                }
                i++;
                j=0;
            }
        }
        return fructeux;
    }

    /**
     * Méthode principale qui lance le jeu et le résoud
     * @param args
     */
    public static void main(String[] args) {
        SoloNoble sn;
        System.out.println("Grille de départ");
        if(args.length==1){
            sn = new SoloNoble(args[0]);

        }else{
            sn =new SoloNoble();
        }

        //tableau evolutif pour afficher les reponses
        char[][] etapes = new char[sn.g.getHauteurTablier()][sn.g.getLargeurTablier()];
        for(int i=0; i<sn.g.getHauteurTablier(); i++){
            etapes[i] = sn.g.getTablier()[i].clone();
        }

        //affichage comme dans le sujet
        System.out.println("Grille ("+sn.getGrille().getHauteurTablier()+"x"+sn.getGrille().getLargeurTablier()+"), Nombre de billes : "+sn.getGrille().getBilles());
        long time =System.currentTimeMillis();
        
        if(sn.resoudreSoloNoble(sn.g.getBilles())){
            //on indique qu'on a une reponse
            System.out.println("Solution trouvée !");
            System.out.println("----------------------");
            //on dessine le tableau d'etape
            sn.dessinerTableauEtape(etapes);
            //on affiche les solutions sous forme de tableaux a suivre pour arriver a la solution
            int i = sn.getEtape().size()-1;
            while(i>=0){
                //faire enlever puis mettre puis enlever
                etapes[sn.getEtape().get(i)[0]][sn.getEtape().get(i)[1]] = '.';
                i=i-1;
                etapes[sn.getEtape().get(i)[0]][sn.getEtape().get(i)[1]] = 'o';
                i=i-1;
                etapes[sn.getEtape().get(i)[0]][sn.getEtape().get(i)[1]] = '.';
                i = i - 1;
                //on dessine le tableau d'etape
                sn.dessinerTableauEtape(etapes);
            }


        }else{
            System.out.println("Il n'y a pas de solution possible !");
        }
        //sn.g.dessinerTablier();
        //System.out.println(System.currentTimeMillis()-time);

    }
}
