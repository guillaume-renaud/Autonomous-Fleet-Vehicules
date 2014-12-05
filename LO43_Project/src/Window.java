import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame
{
	public Window()
	{
		this.setTitle("Autonomous Fleet Vehicules");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	// JFrame in the center of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    JPanel panel = new JPanel();
	    panel.
	    //panel.setBackground(Color.ORANGE);        
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    this.setContentPane(panel);
		
		this.setVisible(true);
	}
}
