import java.util.Scanner;

public class JeuDeLaVie {
    private static final int TAILLE = 5;
    private int[][] grille;
    
    public JeuDeLaVie() {
        grille = new int[TAILLE][TAILLE];
    }
    
  
    public void saisirGrilleInitiale() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez la grille initiale (25 valeurs : 0 pour morte, 1 pour vivante)");
        System.out.println("Entrez les valeurs ligne par ligne :");
        
        for (int i = 0; i < TAILLE; i++) {
            System.out.print("Ligne " + (i + 1) + " (5 valeurs séparées par des espaces) : ");
            for (int j = 0; j < TAILLE; j++) {
                grille[i][j] = scanner.nextInt();
            }
        }
    }
    

    public int countNeighbors(int ligne, int colonne) {
        int count = 0;
        
        // Parcourt les 8 voisines possibles
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Ignore la cellule elle-même
                if (i == 0 && j == 0) {
                    continue;
                }
                
                int nouvelleLigne = ligne + i;
                int nouvelleColonne = colonne + j;
                
                // Vérifie si la voisine est dans les limites de la grille
                if (nouvelleLigne >= 0 && nouvelleLigne < TAILLE && 
                    nouvelleColonne >= 0 && nouvelleColonne < TAILLE) {
                    count += grille[nouvelleLigne][nouvelleColonne];
                }
            }
        }
        
        return count;
    }
    
  
    public int[][] nextGeneration() {
        int[][] nouvelleGrille = new int[TAILLE][TAILLE];
        
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                int voisinesVivantes = countNeighbors(i, j);
                
              
                if (grille[i][j] == 1) {
                    // Cellule vivante
                    if (voisinesVivantes == 2 || voisinesVivantes == 3) {
                        nouvelleGrille[i][j] = 1; 
                    } else {
                        nouvelleGrille[i][j] = 0; 
                    }
                } else {
                    // Cellule morte
                    if (voisinesVivantes == 3) {
                        nouvelleGrille[i][j] = 1;
                    } else {
                        nouvelleGrille[i][j] = 0; 
                    }
                }
            }
        }
        
        return nouvelleGrille;
    }
    

    public void printGrid() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if (grille[i][j] == 1) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
  
    public void setGrille(int[][] nouvelleGrille) {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                grille[i][j] = nouvelleGrille[i][j];
            }
        }
    }
    
    public static void main(String[] args) {
        JeuDeLaVie jeu = new JeuDeLaVie();
        
        jeu.saisirGrilleInitiale();
        
        System.out.println("Grille initiale :");
        jeu.printGrid();
        
         for (int generation = 1; generation <= 5; generation++) {
            int[][] nouvelleGrille = jeu.nextGeneration();
            jeu.setGrille(nouvelleGrille);
            
            System.out.println("Génération " + generation + " :");
            jeu.printGrid();
        }
    }
}
