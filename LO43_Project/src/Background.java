import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g)
	{
		try
		{
			Image img = ImageIO.read(new File("image/background.png"));
			// For a background image otherwise g.drawImage(img, 0, 0, this);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}