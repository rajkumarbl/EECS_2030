import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;

public class ScoreBoard<T1, T2> extends JComponent 
{
	private SnakeButton backButton = new SnakeButton("Back");
    private static ArrayList<String> playerName = new ArrayList<>(); 
    private static ArrayList<Integer> playerScore = new ArrayList<>(); 
    private static int size = 0;
    private static int NO_OF_HIGHSCORE = 10;
    
    
    public ScoreBoard()
	{
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Snake.changePanel("menu",ScoreBoard.this);
			}
		});	
	}
    
    protected void paintComponent(Graphics g)
   	{ 
   		  g.setColor(Color.black);
   		  g.drawLine(200,110,630,110);
   	      g.drawLine(200,140,630,140);
   	      g.drawLine(200,110,200,140);
   	     
   	      g.drawLine(250, 110, 250, 140);
   	      g.drawLine(530, 110, 530, 140);
   	      g.drawLine(630, 110, 630, 140);  
   	      
   		  g.drawString("Rank", 210, 135);
   		  g.drawString("Name", 370, 135);
   		  g.drawString("Score", 560, 135);
   		 
   		for(int i = 1; i < size+1; i++)
  		 {
   		   g.drawLine(200, 110, 200, (i*25)+140);
   		   g.drawLine(250, 110, 250, (i*25)+140);
   		   g.drawLine(530, 110, 530, (i*25)+140);
   		   g.drawLine(630, 110, 630 ,(i*25)+140);
   		   g.drawLine(200,(i*25)+140,630,(i*25)+140);
  		 }
  		 
   		 
   		 for(int i = 0; i < size; i++)
   		 { 
   		      try{ 
   		    	  
   		    	  g.drawString(i+1 + "", 220, 160 + ((i+i) * 12)); 
   		    	  g.drawString( playerName.get(i), 260, 160 + ((i+i) * 12)); 
   		    	  g.drawString(Integer.toString(playerScore.get(i)), 540, 160 + ((i+i) * 12));  
   		    	  } 
   		          catch (IndexOutOfBoundsException e)
   		          { 
   	    		  break; 
   	    	      }
   		     
   		  }
        backButton.setBounds(320, 500, 200, 40); 
   	    add(backButton);
   	    g.setFont(new Font("TimesNewRoman 93", 1, 40));
	    g.drawString("Scoreboard", 300, 80);
   		
   	}

    public void scoreGovernor(T1 b, T2 a) 
	{playerName.add((String) b);
	playerScore.add((Integer) a);
	
	 for (int i = 0; i < playerScore.size(); i++) {

         for (int j = playerScore.size() - 1; j > i; j--) {
             if (playerScore.get(i) < playerScore.get(j)) {

                 int tmp1 = playerScore.get(i);
                 String tmp2 = playerName.get(i);
                 playerScore.set(i,playerScore.get(j));
                 playerName.set(i,playerName.get(j));
                 playerScore.set(j,tmp1);
                 playerName.set(j,tmp2);

              }

            }

	      }
		 if (size < NO_OF_HIGHSCORE)
		 {
			 size = playerName.size();
		 }
		 else
		 {
			 size = NO_OF_HIGHSCORE;
		 }
	}
		
    
}
