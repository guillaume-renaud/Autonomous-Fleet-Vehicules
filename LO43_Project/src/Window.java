import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Window extends JFrame 
{
	private static final long serialVersionUID = 1L;

	public Window()
	{
		this.setTitle("Autonomous Fleet Vehicules");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	// JFrame in the center of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    this.setContentPane(new Panneau());
		
		this.setVisible(true);
	}
	
	public class Panneau extends JPanel {
		
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			try {
				Image img = ImageIO.read(new File("image/fenetre.png"));
				//Pour une image de fond sinon g.drawImage(img, 0, 0, this);
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				g.fillOval(325, 35, 35, 35);
	    	} catch (IOException e) {
	    		e.printStackTrace();
		    }                
		}               
	}
}
