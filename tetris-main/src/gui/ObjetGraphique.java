package gui;

import java.awt.Graphics;

public abstract class ObjetGraphique {
	
	//Attribut protegé
	protected int x;
	protected int y;
	protected int largeur;
	protected int hauteur;
	
	//Constructeur
	public ObjetGraphique(int x, int y, int largeur, int hauteur)
	{
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}
	
	//Permet de deplacer un objet graphique
	public void deplacer(int deplacementHorizontal, int deplacementVertical)
	{
		x+= deplacementHorizontal;
		y+= deplacementVertical;
	}
	
	//Permet de centrer sur le pointeur de la souris
	public void centrerSurPointeurSouris(int x, int y)
	{
		this.x = x - largeur / 2;
		this.y = y - hauteur / 2;
	}
	
	public int getXCentre()
	{
		return x + largeur/2;
	}
	
	public int getYCentre()
	{
		return y + hauteur/2;
	}
	
	//Permet de savoir si un point est compris dans un objet graphique
	public boolean contientPoint(int x, int y)
	{
		boolean OK = true;
		if ((x < this.x) || (x > (this.x+largeur-1)))
				OK = false;
		else
		{
			if ((y < this.y) || (y > (this.y+hauteur - 1)))
				OK = false;
		}
		return OK;
	}
	
	//Permet de savoir si un rectangle est compris dans un objet graphique
	public boolean contientRectangle(int x, int y, int largeur, int hauteur)
	{
		boolean OK = true;
		OK = contientPoint(x,y) && contientPoint(x+largeur-1, y ) && contientPoint(x,y+hauteur-1) && contientPoint(x+largeur-1, y+hauteur-1);
		return OK;
	}
	
	abstract void dessiner(Graphics g);
	
	abstract ObjetGraphique multiclonage();
	
}