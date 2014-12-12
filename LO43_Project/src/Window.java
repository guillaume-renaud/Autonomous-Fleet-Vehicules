import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

	public class Window extends JFrame implements MailBoxListener, Runnable{

		private static final long serialVersionUID = 1L;
		MailBox mainBox; 
		
		Thread thread;
		
		public Window(MailBox MB)
		{
			mainBox = MB;
			
			
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
			
			// Create the HUB
			Hub displayer = new Hub(mainBox);
			
			// Create car image
			for (Car car : mainBox.fleet)
			{
				car.setBounds(car.getCoordCarX(), car.getCoordCarY(), 34, 37);
			}
			
			// Add the images to the JLayeredPane with a different deep level
			jlpTest.add(bg, new Integer(1));
			
			for (Car car : mainBox.fleet)
			{
				jlpTest.add(car, new Integer(2));
			}
			// Add the JLayeredPane to the frame
			this.add(jlpTest, BorderLayout.CENTER);
			this.add(displayer, BorderLayout.SOUTH);
			this.setVisible(true);
		}

		public void moveCarView(Place start,Place end,Car c){
		if(start.getPlaceName().contains("I"))
		{
			//calcul.interrupt(); // /!\ to be verified !
			switch (start.getPlaceName())
			{
				case "I1" :
					for(int i=0;i<25;i++)
					{
						c.setBounds(c.getCoordCarX()-i, (int) ((int) c.getCoordCarY()+(3.5*i)), 34, 37);
						try {
							Thread.sleep(100);
							thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 34,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
				break;
				case "I2" :
					for(int i=0;i<55;i++)
					{
						c.setBounds(c.getCoordCarX()+i, (int) ((int) c.getCoordCarY()+(1.2*i)), 34, 37);
						try {
							Thread.sleep(100);
							thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 34,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
				case "I3" : 
					for(int i = 0;i<9;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+10.5*i), (int) ((int) c.getCoordCarY()-i), 34, 37);
						try {
							Thread.sleep(100);
							thread.sleep(30);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 34,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
			}
			//calcul.start(); // /!\ to be verified !
		}
		
		}		
		
		@Override
		public void onMailReceivedByCar(MailBoxEvent e) {
			Car car = mainBox.fleet.get(e.indexUpdaterInMailBoxList);
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
			
			System.out.println("Le système dévent marche bien");
			System.out.println(""+e.classNameOfUpdater);
			System.out.println(""+e.indexReceiverInMailBoxList);
			System.out.println(""+e.indexUpdaterInMailBoxList);
			System.out.println(""+e.updateAction);
		
		}
		
		@Override
		public void run() {
			
			for (Car car : mainBox.fleet)
			{
				this.moveCarView(new Place("I3",false,null,0,0), null, car);
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
// //imgCar = jpCars.createImage(dim2.width, dim2.height); // Image qui servira ï¿½ dessiner les voitures par la suite
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
