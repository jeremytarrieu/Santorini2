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
public class Grille {
    
    
    Cellule cellule[][] = new Cellule[5][5];
    //plateau de jeu 

    public Grille() {//constructeur, créée les cellules de la grille
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cellule[i][j] = new Cellule();
            }
        }
    }

    public boolean presenceOuvrier(int x, int y) {
        //prends les coordonées d'une cellule
        //renvoie true s'il y a un ouvrier, false sinon
        if (cellule[x][y].ouvrierCourant == null) {
            return false;
        }
        return true;
    }

    public boolean presenceOuvrierJoueur(int x, int y, Joueur unJoueur) {
        if (cellule[x][y].ouvrierCourant == unJoueur.ouvriers[0]
                || cellule[x][y].ouvrierCourant == unJoueur.ouvriers[1]) {
            return true;
        }
        return false;
    }

    public void placerOuvrier(Joueur unJoueur) {
        // au début de partie le joueur doit placer deux ouvrier sur la grille
        int x, y;// Les coordonées de l'ouvrier
        do {// positionnement du premier ouvrier
            System.out.print(unJoueur.nom+", positionnez votre premier Ouvrier (veuillez à saisir une case libre)\nSaisissez ses coordonées\n x : ");
            do {
                Scanner sc = new Scanner(System.in);
                x = sc.nextInt();
            } while (x > 4 && x < 0);
            System.out.print("\ny : ");
            do {
                Scanner sc = new Scanner(System.in);
                y = sc.nextInt();
            } while (y > 4 && y < 0);
        } while (presenceOuvrier(x, y) == true);
        cellule[x][y].affecterOuvrier(unJoueur.ouvriers[0]);
        // tant qu'il y a un ouvrier sur la case selectionnée, la case est redemandée
        //meme démarche pour le second ouvrier
        do {
            System.out.print("Positionnez votre deuxieme Ouvrier (veuillez à saisir une case libre)\nSaisissez ses coordonées\n x : ");
            do {
                Scanner sc = new Scanner(System.in);
                x = sc.nextInt();
            } while (x > 4 && x < 0);
            System.out.print("\ny : ");
            do {
                Scanner sc = new Scanner(System.in);
                y = sc.nextInt();
            } while (y > 4 && y < 0);
        } while (presenceOuvrier(x, y) == true);
        cellule[x][y].affecterOuvrier(unJoueur.ouvriers[1]);
    }
    
    public boolean cellulesVoisines(int x1, int x2, int y1, int y2){
        if((x1-x2)<-1 || (x1-x2)>1 || (y1-y2)<-1 || (y1-y2)>1){
            return false;
        }return true;
    }

    public boolean deplacementPossible(Cellule celluleDepart, Cellule celluleArrivee) {
        /*Prends en arguments deux cellules, renvoie true si le déplacement est possible, false sinon.
        
        Pour cela on verifie si :
            (*) celluleDepart est à côté de celluleArrivee 
            (*) le batimentArrivee n'est pas plus haut de 2 cases que le batiementDepart 
            (*) le batiment n'a pas de toit (4 constructions)
            (*) il n'y a pas d'ouvrier sur la cellule  
         */
        
        //On retrouve les coordonnées x et y des deux cellules et on fait les tests
        int xd = 0;
        int xa = 0;
        int ya = 0;
        int yd = 0;
        for(int x = 0; x<5;x++){
            for(int y = 0;y<5;y++){
                if(cellule[x][y] == celluleDepart){
                    xd = x;
                    yd = y;
                }else if (cellule[x][y]== celluleArrivee){
                    xa = x;
                    ya = y;
                }
            }
        }
        
        if (cellulesVoisines(xd, xa, yd, ya)==true // les cellules sont voisines
                && celluleArrivee.constructionMax() == false // le batiemnt n'est pas fini
                && (celluleArrivee.construction - celluleDepart.construction) <2 // le batiment d'arrivé n'est plus plus haut de 2 ...
                && celluleArrivee.ouvrierCourant == null){
            return true;
        }
        return false;
    }    
        
    public boolean constructionPossible(Cellule celluleOuvrier, Cellule celluleConst) {
        /* Prends en argument deux cellules, celle de l'ouvrier et celle sur laquelle veut construire l'ouvrier.
        Renvoie true si l'ouvrier peut construire sur la cellule, false sinon.
        
        On vérifie si : (*) Il n'y a pas d'ouvrier sur la case d'arrivée
                        (*) Le batiment n'est pas achevé.
                        (*) Les cellules sont voisines*/
        
        // On recupère la position des cellules 
        int xd = 0;
        int xa = 0;
        int ya = 0;
        int yd = 0;
        for(int x = 0; x<5;x++){
            for(int y = 0;y<5;y++){
                if(cellule[x][y] == celluleOuvrier){
                    xd = x;
                    yd = y;
                }else if (cellule[x][y]== celluleConst){
                    xa = x;
                    ya = y;
                }
            }
        }
        if (cellulesVoisines(xd, xa, yd, ya)==true 
                && celluleConst.constructionMax() == false // le batiemnt n'est pas fini
                && celluleConst.ouvrierCourant == null){
            return true;
        }
        return false;
        
    }

    public boolean etreGagnant(Joueur unJoueur) {
        /* Prends en agrument un Joueur et renvoie un booleen.
        Cherche pour chaque cellule de la grille si l'ouvrier courant appartient au Joueur 
        et si le batiment est construit au troisieme etage.
        Si c'est le cas le joueur gagne et on renvoie true, sinon on renvoie false
         */
        for (int i = 0; i < 2; i++) {// i correspond au nombre d'ouvriers
            for (int x = 0; x < 5; x++) {// chaque case de la grille
                for (int y = 0; y < 5; y++) {
                    if (cellule[x][y].ouvrierCourant == unJoueur.ouvriers[i]
                            && cellule[x][y].construction == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean etreBloque(Joueur unJoueur) {
        /*Prends un joueur et vérifie si ses ouvriers peuvent se déplacer, 
        si au moins 1 peut alors le joueur n'a pas perdu et on renvoie false
         */
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cellule[i][j].ouvrierCourant == unJoueur.ouvriers[0] || cellule[i][j].ouvrierCourant == unJoueur.ouvriers[1]) {
                    // on vérifie qu'il y a un ouvrier du joueur dans la case 
                    for (int x = 0; i < 5; x++) {
                        // on parcourt toutes les cases de deplacements possibles
                        for (int y = 0; i < 5; y++) {
                            // si deplacementPossibles sont tous false pour les ouvriers du joueur alors on renvoie true pck il ne peut plus bouger 
                            if (deplacementPossible(cellule[i][j], cellule[x][y]) == true) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean nePasPouvoirConstruire(Joueur unJoueur) {
        /*Prends un joueur et vérifie si ses ouvriers peuvent se déplacer, 
        si au moins 1 peut alors le joueur n'a pas perdu et on renvoie false
         */
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cellule[i][j].ouvrierCourant == unJoueur.ouvriers[0] || cellule[i][j].ouvrierCourant == unJoueur.ouvriers[1]) {
                    // on vérifie qu'il y a un ouvrier du joueur dans la case 
                    for (int x = 0; i < 5; x++) {
                        // on parcourt toutes les cases de deplacements possibles
                        for (int y = 0; i < 5; y++) {
                            // si deplacementPossibles sont tous false pour les ouvriers du joueur alors on renvoie true pck il ne peut plus bouger 
                            System.out.println("i = "+i);
                            System.out.println("j = "+j);
                            if (constructionPossible(cellule[i][j], cellule[x][y]) == true) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean etrePerdant(Joueur unJoueur) {
        // Renvoie true si le joueur est perdant, et false sinon
        //Unjoueur est perdant s'il ne peut plus construire ou qu'il ne peut pas se déplacer
        if (etreBloque(unJoueur) == true || nePasPouvoirConstruire(unJoueur) == true) {
            return true;
        }
        return false;
    }

    public void viderGrille() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cellule[i][j].viderCellule();
            }
        }
    }

}
