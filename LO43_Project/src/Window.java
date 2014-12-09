import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

	public class Window extends JFrame implements MailBoxListener, Runnable{

		MailBox mainBox; 
		
		Car car;
		public Window()
		{
			// Configure the frame
			this.setTitle("Autonomous Fleet Vehicules");
			this.setSize(800, 600);
			this.setResizable(false);
			this.setLocationRelativeTo(null); // JFrame in the center of the window
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// Creation of the JLayeredPane
			JLayeredPane jlpTest = new JLayeredPane();
			jlpTest.setOpaque(false);
			
			// Create background image
			Background bg = new Background();
			bg.setSize(this.getWidth(),this.getHeight());
			
			// Create car image
			car = new Car(0, "P0");
			car.setBounds(557, 100, 32, 37);
			
			// Add the two images to the JLayeredPane with a different deep level
			jlpTest.add(bg, new Integer(1));
			jlpTest.add(car, new Integer(2));
			
			// Add the JLayeredPane to the frame
			this.add(jlpTest, BorderLayout.CENTER);
			this.setVisible(true);
		}

		
		
		@Override
		public void onMailReceivedByCar(MailBoxEvent e) {
			this.car = mainBox.fleet.get(e.indexUpdaterInMailBoxList);
			String action = e.updateAction;
				
			if (action.equals("POSITION_CHANGED"))
			{
				
			}
			else if (action.equals("RELEASED"))
			{
				
			}
			
		}
		@Override
		public void onMailReceivedByMan(MailBoxEvent e) {
			

			
		}
		@Override
		public void onMailReceivedByController(MailBoxEvent e) {
			
			
		
		}
		
		public class Background extends JPanel
		{
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

		@Override
		public void run() {
			
			car.setBounds(557, 100, 32, 37);
			try {
				for (int i=0; i<150; i++)
				{
					car.setBounds(557-i, 100+i, 32, 37);
				Thread.sleep(10);
				}
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
//**********************************************************************************************************************
//JPanel jpBack, jpCars;
//JLayeredPane jlpGame;
//jpBack = new JPanel();
//jpBack.setBounds(0, 0, 800, 400);
//jpCars = new JPanel();
//jpCars.setBounds(0, 0, 800, 400);
//jlpGame = new JLayeredPane();
//jlpGame.setPreferredSize(new Dimension(800, 400));
//
//JLabel toto = new JLabel();
//toto.setBackground(Color.green);
//jpBack.add(toto);
//
//jlpGame.add(jpCars, new Integer(1));
//jlpGame.add(jpBack, new Integer(2));
//
//
//
//this.getContentPane().add(jlpGame);
//pack();
// **********************************************************************************************************************
//try
//{
// imgBackground = ImageIO.read(new File("image/fenetre.png"));
// imgCar = ImageIO.read(new File("image/car.png"));
//
// Dimension dim = jpBack.getSize();
// imgBackground = jpBack.createImage(dim.width, dim.height);
// g = imgBackground.getGraphics();
// g.fillRect(0, 0, dim.width, dim.height); // Dessin du background
// //Dimension dim2 = jpCars.getSize();
// //imgCar = jpCars.createImage(dim2.width, dim2.height); // Image qui servira � dessiner les voitures par la suite
//}
//catch (IOException e1)
//{
// e1.printStackTrace();
//}
//try {
// Image img = ImageIO.read(new File("image/fenetre.png"));
// //Pour une image de fond sinon g.drawImage(img, 0, 0, this);
// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
// //g.fillOval(325, 35, 35, 35);
//} catch (IOException e) {
// e.printStackTrace();
//} 