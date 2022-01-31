package gui;

public class Tetrominos extends Tetromino
{

	public Tetrominos(int x, int y, int dimensions) 
	{
		super(x, y, dimensions);
	    type = 0;

	    /*
	     * -*--
	     * -**-
	     * --*-
	     * ----
	     */
	    grillesPositions[0][0][0] = -10;
	    grillesPositions[0][0][1] = 0;
	    grillesPositions[0][0][2] = -10;
	    grillesPositions[0][0][3] = -10;

	    grillesPositions[0][1][0] = -10;
	    grillesPositions[0][1][1] = 0;
	    grillesPositions[0][1][2] = 0;
	    grillesPositions[0][1][3] = -10;

	    grillesPositions[0][2][0] = -10;
	    grillesPositions[0][2][1] = -10;
	    grillesPositions[0][2][2] = 0;
	    grillesPositions[0][2][3] = -10;

	    grillesPositions[0][3][0] = -10;
	    grillesPositions[0][3][1] = -10;
	    grillesPositions[0][3][2] = -10;
	    grillesPositions[0][3][3] = -10;

	    /*
	     * ----
	     * --**
	     * -**-
	     * ----
	     */
	    grillesPositions[1][0][0] = -10;
	    grillesPositions[1][0][1] = -10;
	    grillesPositions[1][0][2] = -10;
	    grillesPositions[1][0][3] = -10;

	    grillesPositions[1][1][0] = -10;
	    grillesPositions[1][1][1] = -10;
	    grillesPositions[1][1][2] = 0;
	    grillesPositions[1][1][3] = 0;

	    grillesPositions[1][2][0] = -10;
	    grillesPositions[1][2][1] = 0;
	    grillesPositions[1][2][2] = 0;
	    grillesPositions[1][2][3] = -10;

	    grillesPositions[1][3][0] = -10;
	    grillesPositions[1][3][1] = -10;
	    grillesPositions[1][3][2] = -10;
	    grillesPositions[1][3][3] = -10;

	    /*
	     * -*--
	     * -**-
	     * --*-
	     * ----
	     */
	    grillesPositions[2][0][0] = -10;
	    grillesPositions[2][0][1] = 0;
	    grillesPositions[2][0][2] = -10;
	    grillesPositions[2][0][3] = -10;

	    grillesPositions[2][1][0] = -10;
	    grillesPositions[2][1][1] = 0;
	    grillesPositions[2][1][2] = 0;
	    grillesPositions[2][1][3] = -10;

	    grillesPositions[2][2][0] = -10;
	    grillesPositions[2][2][1] = -10;
	    grillesPositions[2][2][2] = 0;
	    grillesPositions[2][2][3] = -10;

	    grillesPositions[2][3][0] = -10;
	    grillesPositions[2][3][1] = -10;
	    grillesPositions[2][3][2] = -10;
	    grillesPositions[2][3][3] = -10;

	    /*
	     * ----
	     * --**
	     * -**-
	     * ----
	     */
	    grillesPositions[3][0][0] = -10;
	    grillesPositions[3][0][1] = -10;
	    grillesPositions[3][0][2] = -10;
	    grillesPositions[3][0][3] = -10;

	    grillesPositions[3][1][0] = -10;
	    grillesPositions[3][1][1] = -10;
	    grillesPositions[3][1][2] = 0;
	    grillesPositions[3][1][3] = 0;

	    grillesPositions[3][2][0] = -10;
	    grillesPositions[3][2][1] = 0;
	    grillesPositions[3][2][2] = 0;
	    grillesPositions[3][2][3] = -10;

	    grillesPositions[3][3][0] = -10;
	    grillesPositions[3][3][1] = -10;
	    grillesPositions[3][3][2] = -10;
	    grillesPositions[3][3][3] = -10;	
	    
	}
	
	@Override
	public ObjetGraphique multiclonage()
	{
		Tetrominos nouv = new Tetrominos(this.x,this.y,this.largeur);
		nouv.position = this.position;
		return nouv;
	}
	
}
