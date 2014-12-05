import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame
{
	Graphics graph;
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
		public void paintComponent(Graphics g){
			try {
				Image img = ImageIO.read(new File("image/background.jpg"));
				//Pour une image de fond sinon g.drawImage(img, 0, 0, this);
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e) {
				e.printStackTrace();
			}                
		}
	}
}