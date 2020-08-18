import java.awt.Color;
import javax.swing.JButton;

public class SnakeButton  extends JButton
		{

			public SnakeButton(String s)
				{
			     super(s); 
			     setFocusPainted(false);
			     setBorderPainted(false);
			     setBackground(new Color(89, 65, 65));
			     setForeground(Color.white);    
				}
		 }