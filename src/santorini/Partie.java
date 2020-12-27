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
    
    Joueur prochainJoueur(){// permet de passer au joueur 
        if (joueurCourant == listeJoueurs[0]){
            return listeJoueurs[1];
        }return listeJoueurs[0];
    }
    
    public Ouvrier deplacerOuvrier(){
    /*  on vérifie si le déplacement est possible
        on renvoie l'ouvrier courant si le déplacement s'est fait et null sinon*/
    
    
        int xd, yd, xa, ya;// intitialisation des variables de position de depart et d'arrivee

        
        
        System.out.println("Vous devez déplacer un de vos ouviers");

        do{//On vérifie que chaque valeur saisie est comprise entre 0 et 4 (la cellule existe)
            System.out.print("Choississez une case de départ \nx : ");// on récupère la position de départ 
            Scanner sc = new Scanner(System.in);
            xd = sc.nextInt();
        }while(xd<5 && xd>-1);

        do{
            System.out.print("\ny : ");
            Scanner sc = new Scanner(System.in);
            yd = sc.nextInt();
        }while(yd<5 && yd>-1);

        do{
            System.out.print("Choisissez une case d'arrivé\nx : ");// et celle d'arrivée 
            Scanner sc = new Scanner(System.in);
            xa = sc.nextInt();
        }while(xa<5 && xa>-1);

        do{
            System.out.print("\ny : ");
            Scanner sc = new Scanner(System.in);
            ya = sc.nextInt();
        }while(ya<5 && ya>-1);

        if(grilleJeu.deplacementsPossibles(xd, yd)[xa][ya]==true && grilleJeu.presenceOuvrierJoueur(xd,yd,joueurCourant) == true){// le déplacement est possible et il y a bien un ouvrier du joueur sur la case
            grilleJeu.cellule[xa][ya].affecterOuvrier(grilleJeu.cellule[xd][yd].recupererOuvrier());// on déplace l'ouvrier de la cellule de base vers la cellule d'arrivée
            return grilleJeu.cellule[xa][ya].ouvrierCourant;
        }
         return null;
    }
    
    public boolean construireOuvrier(Ouvrier unOuvrier){
       /*prends en entrée un ouvrier (celui qui vient de se déplacer) courant et les coordonées de construciton
        renvoie true si la construciton s'est faite et false sinon*/
       if(unOuvrier == null){
       return false;
       
       }
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
                    System.out.println(leJoueur.nom+" choisissez une couleur : 1.rouge 2.bleu");
                    
                    Scanner sc = new Scanner (System.in);
                    choixCouleur = sc.nextInt();
                } while (choixCouleur <3 && choixCouleur >0); // on vérifie que la couleur est comprise entre 1 et 4


                switch (choixCouleur){
                    case 1 :
                        leJoueur.affecterCouleur("rouge");
                        break;
                    case 2 : 
                        leJoueur.affecterCouleur("bleu");
                        break;
                }


                listeJoueurs[i] = leJoueur ;//remplit le tableau de joueurs
            }while (i==1 && listeJoueurs[0].couleur == listeJoueurs[1].couleur);// si les deux couleurs sont les mêmes on refait 
            
            // le choix de la couleur créée les ouvriers du joueur avec leur couleur etleur joueur
        }
        
    }
     
    public Joueur tourDeJeu(){
        // correspond à un tour de jeu, fait deplacer un ouvrier et construire à partir de celuici
        
        System.out.print("C'est à "+joueurCourant.nom+" de jouer ("+joueurCourant.couleur+")\n");
        
        /* tant que le tour ne s'est pas bien effectué 
        ( que le joueur ne s'est pas déplacé correctement et qu'il n'a pas construit)
        on le refait, 
        si un joueur est perdant ou gagnant, le tour s'arrête et on renvoie le vainqueur,
        si le tour s'est bien effectué et qu'il n'y a pas de vainqueur on renvoie null
        */
        do{
            if(grilleJeu.etreGagnant(joueurCourant)==true){
               return joueurCourant;
           }if(grilleJeu.etrePerdant(joueurCourant) == true){
               prochainJoueur();// on change de joueur courant pour envoyer le gagnant
               return joueurCourant;
           }
        }while(construireOuvrier(deplacerOuvrier()) != false);
        return null;
    }
     
    public void debuterPartie(){
        //préparation de la partie  
        Joueur vainqueur;
        
        initialiserPartie();
        
        grilleJeu.placerOuvrier(listeJoueurs[0]);
        grilleJeu.placerOuvrier(listeJoueurs[1]);
        joueurCourant = listeJoueurs[1];
        
        do{
            prochainJoueur();//on change de joueurCourant
            vainqueur = tourDeJeu();
        }while(vainqueur == null);
        System.out.print("Fin : victoire de "+ joueurCourant.nom);
    }
    
}
