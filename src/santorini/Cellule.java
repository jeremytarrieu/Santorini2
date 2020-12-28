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
public class Cellule {
    /* une cellule correspond à une colonne du plateau
    sa hauteur est determinée par la variable construction*/
    Ouvrier ouvrierCourant;
    int construction; /* variable allant de 0 à 4
                        - 0 sol
                        - 1 à 3 étages
                        - chapeau*/    
    
    public Cellule(){//constructeur
        ouvrierCourant = null;
        construction = 0;
    }
    
    public boolean celluleLibre(){
        if(ouvrierCourant == null){
            return true;
        }return false;
    }
    
    public boolean presenceJoueur(Joueur unJoueur){
        // Si le joueur possède un ouvrier sur la cellule on renvoie true
        if(ouvrierCourant == unJoueur.ouvriers[0] || ouvrierCourant == unJoueur.ouvriers[1]){
            return true;
        }return false;
    }
    
    public void affecterOuvrier(Ouvrier unOuvrier){
        // affecte à la cellule un Ouvrier 
            ouvrierCourant = unOuvrier;
    }
    
    public Ouvrier recupererOuvrier(){
        // supprime l'ouvrier de la cellule et renvoie sa reference
        //renvoie null s'il n'y a pas d'ouvrier
        Ouvrier ouvrierRetour = ouvrierCourant;
        ouvrierCourant = null;
        return ouvrierRetour;
    }
    public void construire(){
        
        construction++;
    }
    
    public boolean constructionMax(){
        //si la construction a atteint son niveau max on renvoie true
        if(construction == 4) return true;
        return false;
    }
    
    
    public String lireCouleurOuvrier(){
        if (ouvrierCourant == null){
        return "vide";}
        return ouvrierCourant.couleur;
    }
    public void viderCellule(){
        ouvrierCourant = null;
        construction = 0;
    }
}
