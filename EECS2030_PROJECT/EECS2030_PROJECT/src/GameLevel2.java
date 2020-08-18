import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameLevel2 extends JComponent implements ActionListener

{
	private static final int BALL_SIZE = 10;
	private static final int GAME_WIDTH  = 600;
	private static final int GAME_HEIGHT =600;
    private final static int TOTALPIXELS = (GAME_WIDTH * GAME_HEIGHT) / (BALL_SIZE * BALL_SIZE);
    
	private static final int TOPLEFTBAR_X1  = 50;
	private static final int TOPLEFTBAR_Y1 =50;
	private static final int TOPLEFTBAR_X2  = 50;
	private static final int TOPLEFTBAR_Y2 =50;
	private static final int TOPRIGHTBAR_X1  = 530;
	private static final int TOPRIGHTBAR_Y1 =50;
	private static final int TOPRIGHTBAR_X2  = 440;
	private static final int TOPRIGHTBAR_Y2 =50;
	private static final int BOTTOMLEFTBAR_X1  = 50;
	private static final int BOTTOMLEFTBAR_Y1 =450;
	private static final int BOTTOMLEFTBAR_X2  = 50;
	private static final int BOTTOMLEFTBAR_Y2 =550;
	private static final int BOTTOMRIGHTBAR_X1  = 440;
	private static final int BOTTOMRIGHTBAR_Y1 =550;
	private static final int BOTTOMRIGHTBAR_X2  = 530;
	private static final int BOTTOMRIGHTBAR_Y2 =450;
	
	private static final Random RANDOMS = new Random();
	private final int RAND_POS = 29;
	private final static int[] SNAKE_X = new int[TOTALPIXELS];
    private final static int[] SNAKE_Y = new int[TOTALPIXELS];
	private int snake_Body;
	private int apple_x;
	private int apple_y;  
	private int bonus_x;
	private int bonus_y; 
	private int poison_x;
	private int poison_y;
	private int poison_x1;
	private int poison_y1;
	private Image apple;
	private Image bonus;
	private Image thumbsup;
	private Image win;
	
	private final static int HIGHSCORE = 50;
    private static int score = 0;
    private int speed = 70;
	private Timer timer = new Timer(speed, this);
	
    
	private boolean  gameIn = true;
	private boolean gameWin = false;
    private boolean gameover = false;
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;

    private SnakeButton exit = new SnakeButton("Exit");
    private SnakeButton speedup = new SnakeButton("SPEED +");
    private SnakeButton speeddown = new SnakeButton("SPEED -");
    private SnakeButton pause = new SnakeButton("Pause");
    private SnakeButton play = new SnakeButton("Play");
    
	private KeyActors key = new KeyActors();

	public GameLevel2()
	{  
		setFocusable(true);
		Snake.Game_Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addKeyListener(key);
		setActors();
		timer.addActionListener(new ActionListener() 
		{ 
		
			public void actionPerformed(ActionEvent event)
		{
		if (gameIn) 
			 {
		            snakeMovement();
		            snakeEncounter();
			 }

		repaint();       
		
	}
});
		speedup.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {timer.setDelay( speed-=10 );}});
		speeddown.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {timer.setDelay( speed+=10 );}});
		pause.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {timer.stop();}});
		play.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {timer.start();}});
		exit.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 gameIn = false;
		        if (! gameIn) {
					checkgameover();  
				}
		        
		        Snake.changePanel("menu", GameLevel2.this); 
		      }
		});
	}

	
	public void paintComponent(Graphics g)
	{
		  g.drawRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		  exit.setBounds(650, 550, 100, 40);
		  add(exit);
		  speedup.setBounds(650, 370, 100, 40);
		  add(speedup);
		  speeddown.setBounds(650, 450, 100, 40);
		  add(speeddown);
		  pause.setBounds(650, 200, 100, 40);
		  add(pause);
		  play.setBounds(650, 280, 100, 40);
		  add(play);
		  
		
		  g.setColor(Color.black);
		  g.fillRect(TOPLEFTBAR_X1,TOPLEFTBAR_Y2,10,100);
		  g.fillRect(TOPLEFTBAR_X2,TOPLEFTBAR_Y2,100,10);
		  
		  g.fillRect(TOPRIGHTBAR_X1,TOPRIGHTBAR_Y1,10,100);
		  g.fillRect(TOPRIGHTBAR_X2,TOPRIGHTBAR_Y2,100,10);
		  
		  g.fillRect(BOTTOMLEFTBAR_X1,BOTTOMLEFTBAR_Y1,10,100);
		  g.fillRect(BOTTOMLEFTBAR_X2,BOTTOMLEFTBAR_Y2,100,10);
		  
		  g.fillRect(BOTTOMRIGHTBAR_X1,BOTTOMRIGHTBAR_Y1,100,10);
		  g.fillRect(BOTTOMRIGHTBAR_X2,BOTTOMRIGHTBAR_Y2,10,100);
		  
		  g.setFont(new Font("TimesNewRoman 93", 1, 50));
		  g.drawString("Snake", 625, 50);
		  g.setFont(new Font("TimesNewRoman 93", 1, 50));
		  g.drawString("Game", 630, 100);
		  g.setFont(new Font("Arial", 1, 20));
		  g.drawString("Score: "+score,650, 150);
	    
		  if (gameIn)
		  {       
	       g.drawImage(apple,apple_x, apple_y,this);
		   g.setColor(Color.BLUE);
		   g.fillRect(poison_x, poison_y, BALL_SIZE, BALL_SIZE);
		   
		   g.setColor(Color.BLUE);
		   g.fillRect(poison_x1, poison_y1, BALL_SIZE, BALL_SIZE);// food
		   
	

	      for (int i = 0; i < snake_Body; i++) 
	      {
	            if (i == 0)
	            {
	                g.setColor(Color.RED);
	            	g.fillRect(SNAKE_X[i], SNAKE_Y[i],BALL_SIZE,BALL_SIZE);
	            } else {
	            	g.setColor(Color.YELLOW);
	            	g.fillRect(SNAKE_X[i], SNAKE_Y[i],BALL_SIZE,BALL_SIZE);
	            }
	            Toolkit.getDefaultToolkit().sync();
	          }
	      if(((score%7) ==0) && (score != 0) && (score != HIGHSCORE))
		   {
			   g.drawImage(bonus,bonus_x, bonus_y,this);  
		   }
			}
		  if(gameover)
		  {
			  remove(play);
		      remove(pause);
		      remove(speedup);
		      remove(speeddown);
		      ImageIcon thumbsupid = new ImageIcon("thumbsup.png");
		      thumbsup = thumbsupid.getImage();
		      g.setFont(new Font("TimesNewRoman 93", 1, 60));
		      g.drawString("Game Over", 150, 300);
		      g.setFont(new Font("TimesNewRoman 93", 1, 20));
		      g.drawString("Better Luck Next Time", 180,350);
		      g.drawImage(thumbsup,420, 312,this);   
		  }
		  
		  if(gameWin)
		  {
			  remove(play);
			  remove(pause);
			  remove(speedup);
			  remove(speeddown);
			  score = HIGHSCORE;
			  ImageIcon winid = new ImageIcon("win.png");
		      win = winid.getImage();
		      g.drawImage(win,150, 170,this);
		      g.setColor(Color.BLACK);
		      g.setFont(new Font("TimesNewRoman 93", 1, 30));
			  g.drawString("Level 3 in progress ....", 170, 400);
			 
		  }
		  
	      }
	

  public void setActors()		
   {
	  ImageIcon appleid = new ImageIcon("apple.png");
	  apple = appleid.getImage();
      ImageIcon bonusid = new ImageIcon("grapes.png");
	  bonus = bonusid.getImage();
	  
	  snake_Body = 5;
	  int r =300;

      for (int z = 0; z < snake_Body; z++) 
      {
        SNAKE_X[z] =r - z * 10;
        SNAKE_Y[z] = r;
        foodLocator();
       }
    
    }
	
	 private void snakeMovement() 
	 {

	        for (int i = snake_Body; i > 0; i--) 
	        {
	        	SNAKE_X[i] = SNAKE_X[(i - 1)];
	        	SNAKE_Y[i] = SNAKE_Y[(i - 1)];
	        }
	        for (int i = 0; i <= snake_Body; i++) 
	        {
       	       if (SNAKE_X[i] >= GAME_WIDTH)
	        {
	        	SNAKE_X[i] = i;
	        }
       	    if (SNAKE_Y[i] >= GAME_HEIGHT)
	        {
	        	SNAKE_Y[i] = i;
	        }
       	 
       	    if (SNAKE_Y[i] < 0) {
    	    	SNAKE_Y[i] = GAME_HEIGHT-i;
	        }
       	    
       	    if (SNAKE_X[i] < 0) 
       	    {
       	    	SNAKE_X[i] = GAME_WIDTH-i;
	        }
	        }
	       
	        if (leftDirection) {
	        	SNAKE_X[0] -= BALL_SIZE;
	        }

	        if (rightDirection) {
	        	SNAKE_X[0] += BALL_SIZE;
	        }

	        if (upDirection) {
	        	SNAKE_Y[0] -= BALL_SIZE;
	        }

	        if (downDirection) {
	        	SNAKE_Y[0] += BALL_SIZE;
	        }
	        requestFocusInWindow();
   }
	
	
	 private void snakeEncounter() {

	        for (int z = snake_Body; z > 0; z--)
	        {

	        if ((z > 3) && (SNAKE_X[0] == SNAKE_X[z]) && (SNAKE_Y[0] == SNAKE_Y[z])) 
	        {
	            gameIn = false;
	            gameover = true;
	        }
	        }

	       for (int z = 0; z <= 90; z+=10)
	        {

	        if ((SNAKE_X[0] == TOPLEFTBAR_X1 )&& (SNAKE_Y[0] == TOPLEFTBAR_Y1+z)) 
	        {
	            gameIn = false;
	            gameover = true;
	        }
	        if ((SNAKE_X[0] == TOPLEFTBAR_X2+z )&& (SNAKE_Y[0] == TOPLEFTBAR_Y2)) 
	        {
	            gameIn = false;
	            gameover = true;
	        }
	        if ((SNAKE_X[0] == TOPRIGHTBAR_X1 )&& (SNAKE_Y[0] == TOPRIGHTBAR_Y1+z)) 
	        {
	            gameIn = false;
	            gameover = true;
	        }
	        if ((SNAKE_X[0] == TOPRIGHTBAR_X2+z )&& (SNAKE_Y[0] == TOPRIGHTBAR_Y2)) 
	        {
	            gameIn = false;
	            gameover = true;
	        }
	        if ((SNAKE_X[0] == BOTTOMLEFTBAR_X1 )&& (SNAKE_Y[0] == BOTTOMLEFTBAR_Y1+z)) 
	        {
	            gameIn = false;
	            gameover = true;
	        }
	        if ((SNAKE_X[0] == BOTTOMLEFTBAR_X2+z )&& (SNAKE_Y[0] == BOTTOMLEFTBAR_Y2)) 
	        {
	            gameIn = false;
	            gameover = true;
	        }
	        if ((SNAKE_X[0] == BOTTOMRIGHTBAR_X1+z )&& (SNAKE_Y[0] == BOTTOMRIGHTBAR_Y1)) 
	        {
	            gameIn = false;
	            gameover = true;
	        }
	        if ((SNAKE_X[0] == BOTTOMRIGHTBAR_X2 )&& (SNAKE_Y[0] == BOTTOMRIGHTBAR_Y2+z)) 
	        {
	            gameIn = false;
	            gameover = true;
	        }
	        }
	        
	        if ((SNAKE_X[0] == poison_x) && (SNAKE_Y[0] == poison_y) ||(SNAKE_X[0] == poison_x1) && (SNAKE_Y[0] == poison_y1)) 
	        {

	            gameIn = false;
	            gameover = true;
	        }
	        
	        if ((SNAKE_X[0] == apple_x) && (SNAKE_Y[0] == apple_y)) 
	        {

	            snake_Body++;
	            score++;
	           foodLocator();
	        }
	        if(((score%7) ==0) && (score != 0) && (score != HIGHSCORE)&&(gameIn))
			{
	        if ((SNAKE_X[0] == bonus_x) && (SNAKE_Y[0] == bonus_y)) 
	        {
	            snake_Body+=3;
	            score+=3;
	            foodLocator();
	        }
			}
	        
	        if(score >= HIGHSCORE)
	        {
	        	gameIn = false;
	        	gameWin  = true;
	        }
	        
	       
	    }
	
	private void foodLocator() 
	{

        int apple_random = (int) (Math.random() * RAND_POS);
        apple_x = ((apple_random *BALL_SIZE));

        apple_random = (int) (Math.random() * RAND_POS);
        apple_y = ((apple_random * BALL_SIZE));

        int poison_random = (int) (Math.random() * RAND_POS+10);
        poison_x = ((poison_random *BALL_SIZE));

        poison_random = (int) (Math.random() * RAND_POS+10);
        poison_y = ((poison_random * BALL_SIZE));
        
        int poison_random2 = (int) (Math.random() * RAND_POS+10);
        poison_x1 = ((poison_random2 *BALL_SIZE));

        poison_random2 = (int) (Math.random() * RAND_POS+10);
        poison_y1 = ((poison_random2 * BALL_SIZE));
        
        for (int z = 0; z <= 90; z+=10)
        {

        if (( apple_x == TOPLEFTBAR_X1 )&& (apple_y == TOPLEFTBAR_Y1+z) || ( poison_x == TOPLEFTBAR_X1 )&& ( poison_y  == TOPLEFTBAR_Y1+z) || ( poison_x1 == TOPLEFTBAR_X1 )&& ( poison_y1  == TOPLEFTBAR_Y1+z) ) 
        {
            foodLocator();
        }
        if ((apple_x == TOPLEFTBAR_X2+z )&& (apple_y == TOPLEFTBAR_Y2) || (poison_x== TOPLEFTBAR_X2+z )&& (poison_y == TOPLEFTBAR_Y2)|| (poison_x== TOPLEFTBAR_X2+z )&& (poison_y == TOPLEFTBAR_Y2)) 
        {
           foodLocator();
        }
        if (( apple_x == TOPRIGHTBAR_X1 )&& (apple_y == TOPRIGHTBAR_Y1+z) || ( poison_x == TOPRIGHTBAR_X1 )&& ( poison_y  == TOPRIGHTBAR_Y1+z) || ( poison_x1 == TOPRIGHTBAR_X1 )&& ( poison_y1  == TOPRIGHTBAR_Y1+z) )  
        {
        	 foodLocator();
        }
        if ((apple_x == TOPRIGHTBAR_X2+z )&& (apple_y == TOPRIGHTBAR_Y2) || (poison_x== TOPRIGHTBAR_X2+z )&& (poison_y == TOPRIGHTBAR_Y2)|| (poison_x== TOPRIGHTBAR_X2+z )&& (poison_y == TOPRIGHTBAR_Y2)) 
        {
        	 foodLocator();
        }
        if (( apple_x == BOTTOMLEFTBAR_X2 )&& (apple_y == BOTTOMLEFTBAR_Y2+z) || ( poison_x == BOTTOMLEFTBAR_X2 )&& ( poison_y  == BOTTOMLEFTBAR_Y2+z) || ( poison_x1 == BOTTOMLEFTBAR_X2 )&& ( poison_y1  == BOTTOMLEFTBAR_Y2+z) )  
        {
        	foodLocator();
        }
        if (( apple_x == BOTTOMLEFTBAR_X1+z )&& (apple_y == BOTTOMLEFTBAR_Y1) || ( poison_x == BOTTOMLEFTBAR_X1+z )&& ( poison_y  == BOTTOMLEFTBAR_Y1) || ( poison_x1 == BOTTOMLEFTBAR_X1+z )&& ( poison_y1  == BOTTOMLEFTBAR_Y1) )  
        {
        	foodLocator();
        }
        }
        if(((score%7) ==0) && (score != 0) && (score != HIGHSCORE)&&(gameIn))
		{
        int bonus_random = (int) (Math.random() * RAND_POS);
        bonus_x = ((bonus_random *BALL_SIZE));

        bonus_random = (int) (Math.random() * RAND_POS);
        bonus_y = ((bonus_random * BALL_SIZE));

        for (int z = 1; z <= snake_Body; z++)
        {

        if(((apple_x == SNAKE_X[z]) && (apple_y  == SNAKE_Y[z]))||((bonus_x == SNAKE_X[z]) && (bonus_y  == SNAKE_Y[z]))||((poison_x == SNAKE_X[z]) && (poison_y  == SNAKE_Y[z]))||((poison_x1 == SNAKE_X[z]) && (poison_y1  == SNAKE_Y[z]))) 
        {
            foodLocator();
        }
        }

		}

    }
	
	 private void checkgameover()
		{
			 if(!gameIn)
			{ 
				String input =JOptionPane.showInputDialog("Enter your name:");
				if(input == null || input == "")
				{
					input = "Unkown Name";
				}
				ScoreBoard<String,Integer> gb1 = new ScoreBoard<String,Integer>();
				gb1.scoreGovernor(input,score+50);
				score = 0;
				Snake.Game_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			}
			
			
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//=================================================================================================================
	 
	 
    public class KeyActors implements KeyListener
         {

   	   @Override
   		public void keyPressed(KeyEvent e) 
   		{
   			
   			int key = e.getKeyCode();

   	        if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) 
   	        {
   	            leftDirection = true;
   	            upDirection = false;
   	            downDirection = false;
   	        }

   	        if ((key == KeyEvent.VK_RIGHT) && (!leftDirection))
   	        {
   	            rightDirection = true;
   	            upDirection = false;
   	            downDirection = false;
   	        }

   	        if ((key == KeyEvent.VK_UP) && (!downDirection)) 
   	        {
   	            upDirection = true;
   	            rightDirection = false;
   	            leftDirection = false;
   	        }

   	        if ((key == KeyEvent.VK_DOWN) && (!upDirection)) 
   	        {
   	            downDirection = true;
   	            rightDirection = false;
   	            leftDirection = false;
   	        }
   			
   		}
   		

	           @Override
	           public void keyReleased(KeyEvent e)
	           {
		
	           }

	          @Override
	          public void keyTyped(KeyEvent e) 
	          {

           	}
	
          }
		
   }
 

	




