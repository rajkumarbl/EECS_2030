import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snake 
{
  private static final int APP_WIDTH = 800;
  private static final int APP_HEIGHT = 600;
  static JFrame Game_Frame = new JFrame();
  private static JPanel Game_Panel = new JPanel();



 
	public Snake(){		
		Game_Panel.setPreferredSize(new Dimension(APP_WIDTH,APP_HEIGHT));  
		Game_Panel.setBackground(new Color(255, 178, 178)); 
		Game_Panel.setLayout(new BorderLayout()); 
		changePanel("menu"); 
		Game_Frame.add(Game_Panel);
		Game_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game_Frame.setTitle("Snake Game - Menu");
		Game_Frame.setResizable(false);
		Game_Frame.pack();
		Game_Frame.setVisible(true);	
		
	}
	
	public static void changePanel(String menu_option)
	{
			if(menu_option == "menu" )
			{
				Game_Frame.setTitle("Snake Game - Menu");
				Game_Panel.add(new Menu(), BorderLayout.CENTER);
			}
			if(menu_option == "gamelevel1" )
			{
				Game_Frame.setTitle("Snake Game - Level 1");
				Game_Panel.add(new GameLevel1(), BorderLayout.CENTER); 
			}
			if(menu_option == "scoreboard")
			{
				Game_Frame.setTitle("Snake Game - Scoreboard");
				Game_Panel.add(new ScoreBoard(), BorderLayout.CENTER); 
			}
			if(menu_option == "instruction")
			{
				Game_Frame.setTitle("Snake Game - Instructions");
				Game_Panel.add(new Instructions(), BorderLayout.CENTER); 
			}
			
			if(menu_option == "gamelevel2")
			{
				
				Game_Frame.setTitle("Snake Game - Level 2");
				Game_Panel.add(new GameLevel2(), BorderLayout.CENTER); 
			}
			
			
	}
	
	
	public static void changePanel(String menu_option, JComponent component){
		Game_Panel.remove(component); 
		Game_Panel.revalidate(); 
		changePanel(menu_option); 
		Game_Panel.repaint(); 
	}
 
	public static void main(String[] args) 
	{
		
				new Snake(); 
			
	}
	
}