import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Instructions extends JComponent
{
	    
		private JLabel header = new JLabel("Instructions");	
		private JButton backButton = new SnakeButton("Back");
		private JTextArea content = new JTextArea("1. Click the Play button to start the game.\n\n" + "2. Use the arrow keys to control the snake.\n\n" +
		"3.Eat as many apples as you can to come to a winning point.\n\n" + "4.Avoid hitting the wall.\n\n" +
				"5. Avoid eating poison it causes sudden death. \n\n" + "6. Press the Speed+ button to increase the speed of the snake.\n\n" +
		 "7. Press the Speed- button to reduce the speed of the snake.\n\n" + "8. Press the pause button to pause the game.\n\n"
				+ "9. Please enter your name when the game ends to keep track of your high score.\n\n" + "10.Click the score board button in the menu to access the top 10 scores.");	
		
		
		public Instructions()
		{
			backButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Snake.changePanel("menu", Instructions.this); 
				}
			});
		}
		
		protected void paintComponent(Graphics g){
			
			header.setFont(new Font("Times new Roman", Font.PLAIN, 40));
			header.setBounds(300,05,300,100);
			
			backButton.setFont(new Font("Times new Roman", Font.BOLD, 20));
			backButton.setBounds(300, 555, 200, 40);
			
			
			content.setFont(new Font("Times new Roman", Font.PLAIN, 18));
			content.setBounds(170,110,650,450);
		    content.setBackground(new Color(255, 178, 178));
			content.setEditable(false);

			add(header);
			add(backButton);
			add(content, BorderLayout.CENTER);
  }
}
