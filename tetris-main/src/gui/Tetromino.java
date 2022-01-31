package gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Tetromino extends ObjetGraphique
{
	
	protected int[][][] grillesPositions = new int[4][4][4];
	protected int position;
	protected int dimensionCase;
	protected int type;
	
	
	public Tetromino(int x, int y, int dimensions) 
	{
		super(x,y,dimensions,dimensions);
		
		this.position = 0;
		this.dimensionCase = dimensions/4;
		this.type = 0;
		
		for (int p = 0; p < 4; p++)
		{
			for(int i = 0; i<4 ; i++)
			{
				for(int j = 0; j<4;j++)
				{
					grillesPositions[p][i][j] = -10;
				}
			}
		}
	}
	
	public void rotationDroite()
	{
		this.position--;
		if(this.position < 0)
		{
			this.position = 3;
		}
	}
	
	public void rotationGauche()
	{
		this.position++;
		if(this.position > 3)
		{
			this.position = 0;
		}
	}
	
	public void deplacementGauche()
	{
		deplacer(-dimensionCase, 0);
	}
	
	public void deplacementDroite()
	{
		deplacer(dimensionCase, 0);
	}
	
	public void deplacementBas()
	{
		deplacer(0, dimensionCase);
	}
	
	public int[][] getGrillePosition()
	{
		int[][] gp = new int[4][4];
		
		for (int i = 0;i<4;i++)
		{
			for(int j = 0;j<4;j++)
			{
				gp[i][j] = this.grillesPositions[this.position][i][j];
			}
		}
		
		return gp;
		
	}
	
	
	// Permet de dessiner les tetrominos
	@Override
	void dessiner(Graphics g) 
	{
		Color couleur;
		
		for (int i=0; i<4; i++)
		{
	        for (int j=0; j<4; j++)
	        {
	            if (grillesPositions[position][i][j] > -10)
	            {
	                switch(type)
	                {
	                    case 0 : couleur = Color.GREEN;
	                             break;
	                    case 1 : couleur = Color.CYAN;
	                             break;
	                    case 2 : couleur = Color.RED;
	                             break;
	                    case 3 : couleur = Color.BLUE;
	                             break;
	                    case 4 : couleur = new Color(204, 0, 0);
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
	                //System.out.printf("%d %d %d %d ", x+j*dimensionCase, y+i*dimensionCase, dimensionCase, dimensionCase);
	            
	            }
	        }
		
		}
	}


	abstract ObjetGraphique multiclonage();
	
}
