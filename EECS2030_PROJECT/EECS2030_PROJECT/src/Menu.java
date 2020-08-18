import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;


public class Menu extends JComponent
		{
			private SnakeButton start = new SnakeButton("New Game");
			private SnakeButton scoreboard = new SnakeButton("Scoreboard");
			private SnakeButton instruction = new SnakeButton("Instructions");
			
			public Menu()
			{
				start.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Snake.changePanel("gamelevel1", Menu.this);					
					}
				});	
				
				scoreboard.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Snake.changePanel("scoreboard", Menu.this);					
					}
				});
				
               instruction.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Snake.changePanel("instruction", Menu.this);					
					}
				});
				
			}
			

			@Override
			protected void paintComponent(Graphics g) 
			{
				start.setBounds(300, 240, 200, 40);
				scoreboard.setBounds(300, 300, 200, 40);
				instruction.setBounds(300, 360, 200, 40);
				add(start);
				add(scoreboard);
				add(instruction);
			}
			
		}