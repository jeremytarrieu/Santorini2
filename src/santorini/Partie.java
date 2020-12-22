/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santorini;

/**
 *
 * @author tarri
 */
public class Partie {
    Joueur listeJoueurs[] = new Joueur [2];
    Joueur joueurCourant;
    Grille grilleJeu = new Grille();
    
    
    public Ouvrier deplacerOuvirer(int x1, int x2, int y1, int y2){
    /* pour x1 y1 la position de départ et x2 y2 position d'arrivée 
        on vérifie si le déplacement est possible
        on renvoie l'ouvrier courant si le déplacement s'est fait et null sinon*/
    
    if(grilleJeu.deplacementsPossibles(x1, y1)[x2][y2]==true){// le déplacement est possible
        grilleJeu.cellule[x2][y2].affecterOuvrier(grilleJeu.cellule[x1][y1].recupererOuvrier());// on déplace l'ouvrier de la cellule de base vers la cellule d'arrivée
        return grilleJeu.cellule[x2][y2].ouvrierCourant;
    }
     return null;
    }
    
    public boolean construireOuvrier(Ouvrier unOuvrier, int x, int y){
       /*prends en entrée un ouvrier (celui qui vient de se déplacer) courant et les coordonées de construciton
        renvoie true si la construciton s'est faite et false sinon*/
        
        for(int i =0;i<5;i++){ // pour chasue cellule on regarde s'il y a l'ouvrier et s'il peut construire sur la case voulue
            for(int  j =0;j<5;j++){
                if(grilleJeu.cellule[i][j].ouvrierCourant == unOuvrier && grilleJeu.constructionsPossibles(i,j)[x][y] == true){
                    return grilleJeu.cellule[x][y].construire(); // renvoie true 
                    
                }
            }
        }return false;
    }
    
    public void initialiserPartie(){
        grilleJeu.viderGrille();

    }
    
}
