package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;

public class FenetrePrincipale extends JFrame {

	private JPanel contentPane;
	
	//Nos variables
	private Puit puit;
	boolean perdu;
	boolean start;
	long score;
	int niveau;
	int nbLignes;
	int interval;
	
	
	Timer timer;
	TimerTask task;
	
	public JTextArea score_1;
	public JTextArea diff_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale frame = new FenetrePrincipale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetrePrincipale() 
	{
		//Affichage des constantes KeyEvent qui nous intéressent
		System.out.printf("VK_K : %d, VK_m : %d, VK_O : %d, VK_L : %d, VK_SPACE : %d ",KeyEvent.VK_K, KeyEvent.VK_M, KeyEvent.VK_O, KeyEvent.VK_L, KeyEvent.VK_SPACE );
		//Gestion des touches du clavier
		getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.printf("KeyPress : %d", e.getKeyCode());
				
				if (!perdu && start)
			    {
					//System.out.println("Inside");
			        // On test si le mouvement voulu par l'utilisateur est possible
			        // si oui on déplace le tetro courant
			        switch(e.getKeyCode())
			        {
			            case KeyEvent.VK_K :     if (!puit.testCollision(1))
			                                    puit.getTetrominoCourant().deplacementGauche();
			            						//System.out.println("1");
			                                 break;
			            case KeyEvent.VK_M :     if (!puit.testCollision(2))
			                                    puit.getTetrominoCourant().deplacementDroite();
			                                 break;
			            case KeyEvent.VK_O :     if (!puit.testCollision(4))
			                                    puit.getTetrominoCourant().rotationGauche();
			                                 break;
			            case KeyEvent.VK_L :     if (!puit.testCollision(5))
			                                    puit.getTetrominoCourant().rotationDroite();
			                                 break;
			            case KeyEvent.VK_SPACE : if (!puit.testCollision(3))
			                                    puit.getTetrominoCourant().deplacementBas();
			                                 break;
			        }
			        

			        // On réaffiche la fenêtre
			        repaint();
			    }
				
			}
		});

		
		//Création de la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 100, 600, 600);
		getContentPane().setLayout(null);
		
		//On permet a la fenetre d'etre focus pour pouvoir recevoir des keyEvent
		getContentPane().setFocusable(true);
		
		//Création du boutton start
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setFocusable(false);
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			//Methode appelée quand on appuie sur le boutton start
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//Initialisation du timer
				if (!perdu && !start)
				{
			        start = true;
			        timer = new Timer();
			        task = new TimerTask()
					{
						public void run()
						{
							ticTimer();
							
						}
						
					};
			    }
				
				
				//On lance la "task" tout les intervals de temps
				timer.schedule(task, new Date(), interval);
			}
			
		});
		
		btnNewButton.setBounds(485, 527, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			//Gestion du boutton reset
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				timer.cancel();
				initGame();
				repaint();
			}
		});
		btnNewButton_1.setBounds(485, 493, 89, 23);
		getContentPane().add(btnNewButton_1);

		//Initialisation de la partie
		initGame();
	}
	
	public void initGame()
	{
		//Mise en place des variables
		puit = new Puit(10,10,280,500);
		perdu = false;
		score = 0;
		niveau = 0;
		nbLignes = 0;
		interval = 1000; // 1 secondes
		start = false;
	}
	
	public void paint(Graphics g) 
	{
		//Fonction qui permet de dessiner sur le jFrame
		super.paint(g);
		/*
		puit.getTetrominoCourant().dessiner(g);
		
		//Test
		//g.fillRect(50,50,300,100);
		puit.dessiner(g);
		
		
		System.out.println("ALALLAOALALA");
		*/
		g.translate(-25, 25);
	    // Dessin du puit
	    puit.dessiner(g);

	    // Dessin du tetromino suivant
	    ObjetGraphique t = puit.getCloneTetrominoSuivant();
	    
	    t.deplacer(200,20);
	    
	    g.setColor(Color.BLACK);
	    g.fillRect(t.getX(),t.getY()-20,t.getLargeur(),t.getHauteur()+40);
	    
	    t.dessiner(g);
	    
	    
	    //Affichage des textes
	    g.setColor(Color.BLACK);
	    Font f = new Font("Comic sans MS", Font.BOLD, 20);
	    g.setFont(f);
	    
	    //Création des strings
	    String s1 = String.format("Score : %d", score);
	    String s2 = String.format("Difficulte : %d", niveau);
	    
	    //Affichage score
	    g.drawString(s1, 450, 56);
	    
	    //Affichage niveau
	    g.drawString(s2, 450, 118);
	    
	    
	    


	}
	
	//Methode appelée toutes les secondes, pour mettre à jour le jeu
	public void update()
	{

	    if (!puit.testCollision(3))
	    {
	        // Pas de collision on déplace le tetro courant vers le base
	        puit.getTetrominoCourant().deplacementBas();
	    }
	    else
	    {
	        // Collision !!!

	        // On fusionne le tetro courant avec le puit
	        puit.fusionTetroCourantPuit();

	        // On supprime les lignes pleines et on met a jour le score le niveau et le nb de lignes completées
	        score += puit.traiterLignesPleines(niveau,nbLignes);

	        // On adapte la vitesse du timer au niveau
	        interval = 1000 - 50*niveau;
	        if (interval<100) interval = 100;

	        // Le tetro suivant devient le tetro courant
	        puit.changerTetrominoCourant();

	        // On génère un nouveau tetro suivant
	        puit.nouveauTetrominoSuivant();

	        // On regarde si le nouveau tetro courant n'ntre pas en collision avec le puit -> perdu
	        if (puit.testCollision(0))
	        {
	            // Si c'est le cas on arrête le timer, on ouvre une messagebox pour afficher le message
	            // et on indique que l'on a perdu en modifiant le booléen "perdu"
	        	
	            timer.cancel();
	            //On affiche une infobox pour informer le joueur de sa défaite
	            FenetrePrincipale.infoBox("Perdu !", "La defaite c'est vraiment pas top");

	            perdu = true;
	        }
	    }

	    // On réaffiche la fenêtre
	    this.repaint();

	}
	
	//Methode permettant de créer une infobox
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	//Fonction appelée par le taskTimer
	private void ticTimer()
	{
		//System.out.println("Timer tic !");
		update();
	}
}
