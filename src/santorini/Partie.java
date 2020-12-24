/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santorini;

import java.util.Scanner;

/**
 *
 * @author tarri
 */
public class Partie {
    Joueur listeJoueurs[] = new Joueur [2];
    Joueur joueurCourant;
    Grille grilleJeu = new Grille();
    
    
    public Ouvrier deplacerOuvirer(){
    /*  on vérifie si le déplacement est possible
        on renvoie l'ouvrier courant si le déplacement s'est fait et null sinon*/
    int xd, yd, xa, ya;// intitialisation des variables de position de depart et d'arrivee
    System.out.println("Vous devez déplacer un de vos ouviers");
    System.out.print("Choississez une case de départ \nx : ");// on récupère la position de départ 
    Scanner sc = new Scanner(System.in);
    xd = sc.nextInt();

    System.out.print("\ny : ");
    sc = new Scanner(System.in);
    yd = sc.nextInt();

    System.out.print("Choisissez une case d'arrivé\nx : ");// et celle d'arrivée 
    sc = new Scanner(System.in);
    xa = sc.nextInt();

    System.out.print("\ny : ");
    sc = new Scanner(System.in);
    ya = sc.nextInt();
    
    if(grilleJeu.deplacementsPossibles(xd, yd)[xa][ya]==true){// le déplacement est possible
        grilleJeu.cellule[xa][ya].affecterOuvrier(grilleJeu.cellule[xd][yd].recupererOuvrier());// on déplace l'ouvrier de la cellule de base vers la cellule d'arrivée
        return grilleJeu.cellule[xa][ya].ouvrierCourant;
    }
     return null;
    }
    
    public boolean construireOuvrier(Ouvrier unOuvrier){
       /*prends en entrée un ouvrier (celui qui vient de se déplacer) courant et les coordonées de construciton
        renvoie true si la construciton s'est faite et false sinon*/
       
        int x, y;
        
        System.out.print("Choisissez maintenant le bâtimemnt à construire à partir de l'ouvrier que vous avez déplacé\nEntrez sa position x : ");
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        System.out.print("\ny : ");
        sc = new Scanner(System.in);
        y = sc.nextInt();
        for(int i =0;i<5;i++){ // pour chasue cellule on regarde s'il y a l'ouvrier et s'il peut construire sur la case voulue
            for(int  j =0;j<5;j++){
                if(grilleJeu.cellule[i][j].ouvrierCourant == unOuvrier && grilleJeu.constructionsPossibles(i,j)[x][y] == true){
                    return grilleJeu.cellule[x][y].construire(); // renvoie true 
                }
            }
        }return false;
    }
    
    public void initialiserPartie(){
        // on vide la grille
        grilleJeu.viderGrille(); 
        
        //on demande les noms des joueurs
        for(int i = 0;i<2;i++){ // on fait une boucle pour le premier et le deuxieme joueur
            Scanner sc = new Scanner(System.in);
            System.out.print("Entrez le nom du joueur "+i+" : ");
            String nomJoueur = new String();
            nomJoueur = sc.nextLine();
            
            listeJoueurs[i] = new Joueur(nomJoueur);
            
        }
        affecterCouleur(listeJoueurs[0], listeJoueurs[1]);

    }
    
    public void affecterCouleur(Joueur j1, Joueur j2){
         // il faut affecter les couleurs après avoir initialisé le nom du joueur
         Joueur leJoueur;
        for (int i =0; i<2; i++){ // on fait une boucle pour passer du premier au deuxieme joueur
            if(i == 0){
                leJoueur = j1;
            }else{leJoueur = j2;}
            
            int choixCouleur; 
            do{ // test de la couleur du joueur 2 par rapport au 1
                do{ // test que la couleur rentrée est bien entre 1 et 4
                    if(i==1){ // lorsque'on demande au deuxieme joueur on le previens de ne pas choisir la meme couleur que le premier
                        System.out.print("Choisissez une coulur différente autre que le "+listeJoueurs[0].couleur);
                    }
                    System.out.println(leJoueur.nom+" choisissez une couleur : 1.rouge 2.jaune 3.bleu 4.marron");
                    
                    Scanner sc = new Scanner (System.in);
                    choixCouleur = sc.nextInt();
                } while (choixCouleur <5 && choixCouleur >0); // on vérifie que la couleur est comprise entre 1 et 4


                switch (choixCouleur){
                    case 1 :
                        leJoueur.affecterCouleur("rouge");
                        break;
                    case 2 : 
                        leJoueur.affecterCouleur("jaune");
                        break;
                    case 3 : 
                        leJoueur.affecterCouleur("bleu");
                        break;
                    case 4 : 
                        leJoueur.affecterCouleur("marron");
                }


                listeJoueurs[i] = leJoueur ;//remplit le tableau de joueurs
            }while (i==1 && listeJoueurs[0].couleur == listeJoueurs[1].couleur);// si les deux couleurs sont les mêmes on refait 
        }
        
    }
     
    public boolean tourDeJeu(){
        
        System.out.print("C'est à "+joueurCourant.nom+" de jouer ("+joueurCourant.couleur+")\n");
        
        return true;
    }
     
    public void debuterPartie(){
        //préparation de la partie
        initialiserPartie();
        
        
        
    }

}
