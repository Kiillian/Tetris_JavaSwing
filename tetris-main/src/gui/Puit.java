package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Puit extends ObjetGraphique{

	
	//Variables priv�es !
	private Tetromino tetrominoCourant;
	private Tetromino tetrominoSuivant;
	private int grille[][] = new int[25][14]; // ?????
	private int dimensionCase;
	
	//Classe puit
	//G�re le puit ou tombe les tetrominos
	public Puit(int x, int y, int largeur, int hauteur) 
	{
		super(x, y, largeur, hauteur);
		// intialize random java
		dimensionCase=largeur/14;
		// initialisation de la grille
	    for (int i=0; i<25; i++)
	        for (int j=0; j<14; j++)
	            if (i>21)
	                grille[i][j]=0; // bas grille cases pleines
	            else
	                if ((j<2)||(j>11))
	                    grille[i][j]=0; // bords gauche et droit cases pleines
	                else
	                    grille[i][j]=-10; // centre grille cases vide

	    // initialisation tetros suivant et courant
	    nouveauTetrominoSuivant();
	    changerTetrominoCourant();
	    nouveauTetrominoSuivant();
		
	}
	
	public void changerTetrominoCourant() 
	{
		tetrominoCourant = tetrominoSuivant;
	}

	public Tetromino getTetrominoCourant(){
		return tetrominoCourant;
	}

	public void setTetrominoCourant(Tetromino tetrominoCourant){
		this.tetrominoCourant = tetrominoCourant;
	}

	/* 0 : S vert
	 * 1 : Z cyan
	 * 2 : I rouge
	 * 3 : O bleu
	 * 4 : T marron
	 * 5 : L magenta
	 * 6 : J gris
	 */
	
	//On prend un tetromino au hasard pour d�finir le prochain
	public void nouveauTetrominoSuivant() 
	{
		int type = 0 + (int)(Math.random() * ((6 - 0) + 1));
	    switch(type)
	    {
	        case 0 : tetrominoSuivant = new Tetrominos(x+5*dimensionCase,y,dimensionCase*4);
	                 break;
	        case 1 : tetrominoSuivant = new Tetrominoz(x+5*dimensionCase,y,dimensionCase*4);
	                 break;
	        case 2 : tetrominoSuivant = new Tetrominoi(x+5*dimensionCase,y,dimensionCase*4);
	                 break;
	        case 3 : tetrominoSuivant = new Tetrominoo(x+5*dimensionCase,y,dimensionCase*4);
	                 break;
	        case 4 : tetrominoSuivant = new Tetrominot(x+5*dimensionCase,y,dimensionCase*4);
	                 break;
	        case 5 : tetrominoSuivant = new Tetrominol(x+5*dimensionCase,y,dimensionCase*4);
	                 break;
	        case 6 : tetrominoSuivant = new Tetrominoj(x+5*dimensionCase,y,dimensionCase*4);
	                 break;
	    }
	}

	// Affichage du puits et des tetrominos sur la jFrame
	@Override
	void dessiner(Graphics g) {
		Color couleur;

	    // On dessine la grille
	    g.setColor(Color.BLACK);
	    g.drawRect(x+2*dimensionCase,y,10*dimensionCase,22*dimensionCase);
	    g.fillRect( x+2*dimensionCase,y,10*dimensionCase,22*dimensionCase);
	    for (int i=0; i<22; i++)
	        for (int j=2; j<12; j++)
	            if (grille[i][j] > -1)
	            {
	                switch(grille[i][j])
	                {
	                    case 0 : couleur = Color.GREEN;
	                             break;
	                    case 1 : couleur = Color.CYAN;
	                             break;
	                    case 2 : couleur = Color.RED;
	                             break;
	                    case 3 : couleur = Color.BLUE;
	                             break;
	                    case 4 : couleur = new Color(204, 0, 0);;
	                             break;
	                    case 5 : couleur = Color.MAGENTA;
	                             break;
	                    case 6 : couleur = Color.GRAY;
	                             break;
	                             
	                    default :
	                    	couleur = Color.YELLOW;
	                    	break;
	                }
	                g.setColor(couleur);
	                g.fillRect( x+j*dimensionCase, y+i*dimensionCase, dimensionCase, dimensionCase);
	            }

	    // On dessine le tetro courant
	    if (tetrominoCourant != null)
	    {
	        tetrominoCourant.dessiner(g);
	    }
		
	}
	
	
	@Override
	ObjetGraphique multiclonage() {
		return null;
	}
	
	/*
	 * 0 : pas de déplacement, ni de rotation
	 * 1 : dep gauche
	 * 2 : dep droite
	 * 3 : dep bas
	 * 4 : rot gauche
	 * 5 : rot droite
	 */
	
	
	
	//Methode qui test les collisions tetromino - tetrominos mais aussi "mur" - tetromino
	public boolean testCollision(int direction)
	{
	    int gp[][];
	    int i,j,ligne,colonne;
	    boolean resultat = false;
	    Tetromino clone;

	    // On clone le tetromino courant
	    clone = (Tetromino) tetrominoCourant.multiclonage();
	    
	    // On déplace ou on fait tourner le clone
	    switch(direction)
	    {
	        case 1 : clone.deplacementGauche();
	                 break;
	        case 2 : clone.deplacementDroite();
	                 break;
	        case 3 : clone.deplacementBas();
	                 break;
	        case 4 : clone.rotationGauche();
	                 break;
	        case 5 : clone.rotationDroite();
	                 break;
	    }

	    // On recupere sa grille de positions
	    gp=clone.getGrillePosition();

	    // On somme les cases de la grille de pos du clone et du puit en dessous
	    // si pour une case cette somme est >= 0 il y a collision
	    colonne = (clone.getX() - x) / dimensionCase;
	    ligne = (clone.getY() - y) / dimensionCase;
	    for (i=ligne;i<ligne+4;i++)
	        for (j=colonne; j<colonne+4;j++)
	            if (gp[i-ligne][j-colonne]+grille[i][j]>=0)
	            {
	            	System.out.printf("Ligne : %d | Colonne : %d",i,j);
	                resultat = true;
	            }
	            	

	    return resultat;
	}
	
	//Permet de fixer un tetromino dans le puit
	public void fusionTetroCourantPuit()
	{
	    int i,j,ligne,colonne;

	    // On recopie les cases pleines de la grille de position du tetro courant dans la grille du puit
	    colonne = (tetrominoCourant.getX() - x) / dimensionCase;
	    ligne = (tetrominoCourant.getY() - y) / dimensionCase;
	    
	    
	    for (i=ligne;i<ligne+4;i++)
	        for (j=colonne; j<colonne+4;j++)
	            if (tetrominoCourant.getGrillePosition()[i-ligne][j-colonne]>=0)
	                grille[i][j]=tetrominoCourant.getGrillePosition()[i-ligne][j-colonne];
	}
	
	public ObjetGraphique getCloneTetrominoSuivant()
	{
		return tetrominoSuivant.multiclonage();
	}
	
	//Permet de supprimer une ligne quand elle est compl�te
	public int traiterLignesPleines(int niveau, int nblignes)
	{

	    int i,j,k;
	    boolean lignePleine;
	    int nbLignesPleines = 0;
	    int score = 0;

	    i=21;
	    while (i>0)
	    {
	        //On parcours les lignes
	        lignePleine = true;
	        j=2;
	        while(lignePleine && j<12)
	        {
	            // Si une des cases de la ligne courante est vide -> ligne pas pleine
	            if (grille[i][j] == -10)
	                lignePleine = false;
	            else
	                j++;
	        }
	        if (lignePleine)
	        {
	            // Si la ligne courante est pleine on décale toutes les lignes au dessus de 1 vers le bas
	            for (k=i;k>0;k--)
	                for (j=2;j<12;j++)
	                    grille[k][j]=grille[k-1][j];
	            // On genere une premiere ligne vide
	            for (j=2;j<12;j++)
	                grille[0][j]=-10;

	            // On augmente le nb de lignes pleines générées par le tetro courant
	            nbLignesPleines++;
	        }
	        else
	            i--;
	    }

	    // En fonction du nombre de lignes pleines générées par le tetro courant et le niveau on calcul le score
	    switch(nbLignesPleines++)
	    {
	        case 1 : score = 40*(niveau+1);
	                 break;
	        case 2 : score = 100*(niveau+1);
	                 break;
	        case 3 : score = 300*(niveau+1);
	                 break;
	        case 4 : score = 1200*(niveau+1);
	                 break;
	    }

	    // On augmente le nombre de lignes pleines de la partie
	    nblignes += nbLignesPleines++;

	    // On met a jour le niveau
	    niveau = nblignes / 10;

	    return score;	
	}
}
