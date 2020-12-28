/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santorini;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author tarri
 */
public class CelluleGraphique extends JButton {

    Cellule celluleAssociee;

    // Toutes les images que peuvent prendre la cellule au cours du jeu
    ImageIcon img_0vide = new javax.swing.ImageIcon(getClass().getResource("/images/cellule0vide.jpg"));
    ImageIcon img_0rouge = new javax.swing.ImageIcon(getClass().getResource("/images/cellule0Rouge.jpg"));
    ImageIcon img_0bleu = new javax.swing.ImageIcon(getClass().getResource("/images/cellule0Bleu.jpg"));
    ImageIcon img_0jaune = new javax.swing.ImageIcon(getClass().getResource("/images/cellule0Jaune.jpg"));
    ImageIcon img_0rose = new javax.swing.ImageIcon(getClass().getResource("/images/cellule0Rose.jpg"));
    ImageIcon img_0turquoise = new javax.swing.ImageIcon(getClass().getResource("/images/cellule0Turquoise.jpg"));
    ImageIcon img_1vide = new javax.swing.ImageIcon(getClass().getResource("/images/cellule1vide.jpg"));
    ImageIcon img_1rouge = new javax.swing.ImageIcon(getClass().getResource("/images/cellule1Rouge.jpg"));
    ImageIcon img_1bleu = new javax.swing.ImageIcon(getClass().getResource("/images/cellule1Bleu.jpg"));
    ImageIcon img_1jaune = new javax.swing.ImageIcon(getClass().getResource("/images/cellule1Jaune.jpg"));
    ImageIcon img_1rose = new javax.swing.ImageIcon(getClass().getResource("/images/cellule1Rose.jpg"));
    ImageIcon img_1turquoise = new javax.swing.ImageIcon(getClass().getResource("/images/cellule1Turquoise.jpg"));
    ImageIcon img_2vide = new javax.swing.ImageIcon(getClass().getResource("/images/cellule2vide.jpg"));
    ImageIcon img_2rouge = new javax.swing.ImageIcon(getClass().getResource("/images/cellule2Rouge.jpg"));
    ImageIcon img_2bleu = new javax.swing.ImageIcon(getClass().getResource("/images/cellule2Bleu.jpg"));
    ImageIcon img_2jaune = new javax.swing.ImageIcon(getClass().getResource("/images/cellule2Jaune.jpg"));
    ImageIcon img_2rose = new javax.swing.ImageIcon(getClass().getResource("/images/cellule2Rose.jpg"));
    ImageIcon img_2turquoise = new javax.swing.ImageIcon(getClass().getResource("/images/cellule2Turquoise.jpg"));
    ImageIcon img_3vide = new javax.swing.ImageIcon(getClass().getResource("/images/cellule3vide.jpg"));
    ImageIcon img_3rouge = new javax.swing.ImageIcon(getClass().getResource("/images/cellule3Rouge.jpg"));
    ImageIcon img_3bleu = new javax.swing.ImageIcon(getClass().getResource("/images/cellule3Bleu.jpg"));
    ImageIcon img_3jaune = new javax.swing.ImageIcon(getClass().getResource("/images/cellule3Jaune.jpg"));
    ImageIcon img_3rose = new javax.swing.ImageIcon(getClass().getResource("/images/cellule3Rose.jpg"));
    ImageIcon img_3turquoise = new javax.swing.ImageIcon(getClass().getResource("/images/cellule3Turquoise.jpg"));
    ImageIcon img_4 = new javax.swing.ImageIcon(getClass().getResource("/images/cellule4.jpg"));

    public CelluleGraphique(Cellule uneCellule) {
        celluleAssociee = uneCellule;
    }

    @Override // cellule de base au début de la partie 
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        if (celluleAssociee.lireCouleurOuvrier() == "vide") {
            if (celluleAssociee.construction == 0) {
                setIcon(img_0vide);
            } else if (celluleAssociee.construction == 1) {
                setIcon(img_1vide);
            } else if (celluleAssociee.construction == 2) {
                setIcon(img_2vide);
            } else if (celluleAssociee.construction == 3) {
                setIcon(img_3vide);
            } else if (celluleAssociee.construction == 4) {
                setIcon(img_4);
            }
        } else if (celluleAssociee.lireCouleurOuvrier() == "rouge") {
            if (celluleAssociee.construction == 0) {
                setIcon(img_0rouge);
            } else if (celluleAssociee.construction == 1) {
                setIcon(img_1rouge);
            } else if (celluleAssociee.construction == 2) {
                setIcon(img_2rouge);
            } else if (celluleAssociee.construction == 3) {
                setIcon(img_3rouge);
            }
        } else if (celluleAssociee.lireCouleurOuvrier() == "bleu") {
            if (celluleAssociee.construction == 0) {
                setIcon(img_0bleu);
            } else if (celluleAssociee.construction == 1) {
                setIcon(img_1bleu);
            } else if (celluleAssociee.construction == 2) {
                setIcon(img_2bleu);
            } else if (celluleAssociee.construction == 3) {
                setIcon(img_3bleu);
            }
        } else if (celluleAssociee.lireCouleurOuvrier() == "jaune") {
            if (celluleAssociee.construction == 0) {
                setIcon(img_0jaune);
            } else if (celluleAssociee.construction == 1) {
                setIcon(img_1jaune);
            } else if (celluleAssociee.construction == 2) {
                setIcon(img_2jaune);
            } else if (celluleAssociee.construction == 3) {
                setIcon(img_3jaune);
            }
        } else if (celluleAssociee.lireCouleurOuvrier() == "rose") {
            if (celluleAssociee.construction == 0) {
                setIcon(img_0rose);
            } else if (celluleAssociee.construction == 1) {
                setIcon(img_1rose);
            } else if (celluleAssociee.construction == 2) {
                setIcon(img_2rose);
            } else if (celluleAssociee.construction == 3) {
                setIcon(img_3rose);
            }
        } else if (celluleAssociee.lireCouleurOuvrier() == "turquoise") {
            if (celluleAssociee.construction == 0) {
                setIcon(img_0turquoise);
            } else if (celluleAssociee.construction == 1) {
                setIcon(img_1turquoise);
            } else if (celluleAssociee.construction == 2) {
                setIcon(img_2turquoise);
            } else if (celluleAssociee.construction == 3) {
                setIcon(img_3turquoise);
            }
        }// attribution de l'image en fonction de la cellule
    }
}
