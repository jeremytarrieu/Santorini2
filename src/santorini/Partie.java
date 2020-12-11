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
        on renvoie la position finale si le déplacement s'est fait et null sinon*/
    
    if(grilleJeu.deplacementsPossibles(x1, y1)[x2][y2]==true){// le déplacement est possible
        grilleJeu.cellule[x2][y2].affecterOuvrier(grilleJeu.cellule[x1][y1].recupererOuvrier());// on déplace l'ouvrier de la cellule de base vers la cellule d'arrivée
        return grilleJeu.cellule[x2][y2].ouvrierCourant;
    }
     return null;
    }
    
    /*public boolean construireOuvrier(Ouvrier unOuvrier, int x, int y){
        for(int i =0;i<5;i++){
            for(j=0;j<5;j++){
                
            }
        }
    } */
    
    public void initialiserPartie(){
        grilleJeu.viderGrille();

    }
    
}
