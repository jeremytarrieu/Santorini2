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
public class Ouvrier {
    String couleur = new String(); // "rouge" ou "bleu"
    String joueur = new String();
    
    public Ouvrier(Joueur unJoueur){
        joueur = unJoueur.nom;
        couleur = unJoueur.couleur;
    }
    
    public Ouvrier(){}
    
    public String lireCouleur(){
        return couleur;
    }
}
