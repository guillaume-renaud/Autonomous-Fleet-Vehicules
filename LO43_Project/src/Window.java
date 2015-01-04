import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

	public class Window extends JFrame implements  Runnable {

		private static final long serialVersionUID = 1L;
		
		MailBox mainBox; 
		LinkedBlockingDeque<MailBoxEvent> tasks;
		int nbFreeThread=2;
		
		Thread tempo;
		Thread calcul;
		
		MovingManager internalThread;
		MovingManager internalThread2;
		
		boolean eventGiven;
		boolean noEventRemain;
		
		MailBoxEvent eventToDisplay;
		Hub displayer;
		EventLog log;
		Background bg;
		
		public Window(MailBox MB)
		{
			
			mainBox = MB;	
			tasks = new LinkedBlockingDeque<MailBoxEvent>();
			internalThread = new MovingManager("internalThread", this);
			internalThread.start();
			internalThread2 = new MovingManager("internalThread2", this);
			internalThread2.start();
			
			// Creation of the JPanel and his JLayeredPane
			JLayeredPane jlp = new JLayeredPane();
			jlp.setOpaque(false);
			
			// Create the HUB
			displayer = new Hub(mainBox);
			
			// Create the event log
			log = new EventLog(mainBox);
			//this.getContentPane().add(log.scrollPane, BorderLayout.CENTER);
			//log.scrollPane.setViewportView(log);
			log.updateLog("La police lance une requette et attend que les voleurs de voiture soient RELEASE : les poulets arriveront toujors en retard ");
			
			
			// Create background image
			bg = new Background();
			bg.setSize(new Dimension(800,600));
			
			// Create car image
			for (Car car : mainBox.fleet)
			{
				car.setBounds(car.getCoordCarX(), car.getCoordCarY(), 32, 37);
				//car.setVisible(true);
			}
			
			// Add the images to the JLayeredPane with a different deep level
			jlp.add(bg, new Integer(1));
			
			for (Car car : mainBox.fleet)
			{
				jlp.add(car, new Integer(2));
			}
						
			
			// Configure the frame
			this.setTitle("Autonomous Fleet Vehicules");
			this.setSize(new Dimension(bg.getWidth()+displayer.getWidth(),bg.getHeight()+log.getHeight()));
			this.setResizable(false);
			this.setLocationRelativeTo(null); // JFrame in the center of the window
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
			// Add the JLayeredPane to the frame
			this.add(jlp, BorderLayout.CENTER);
			this.add(displayer, BorderLayout.EAST);
			this.add(log, BorderLayout.SOUTH);
			this.setVisible(true);
		}
		
		public void setThreadCalcul (Thread calc)
		{
			this.calcul = calc;
		}

		public void moveToStartingPoint(Place start, Car c){
			c.setBounds(start.getCoordX(),start.getCoordY(), 32,37);
			c.setCoordCarX(start.getCoordX());
			c.setCoordCarY(start.getCoordY());
		}
		
		public void moveToParking(Car c) {
			c.setBounds(1000,1000, 34,37);
			c.setCoordCarX(1000);
			c.setCoordCarY(1000);
		}
		
		@SuppressWarnings("static-access")
		public void moveCarView(Place start,Place end,Car c){
			if(start.getPlaceName().contains("I"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (start.getPlaceName())
				{
				case "I1" :
					for(int i=0;i<8;i++)
					{
						c.setBounds((int) c.getX()-i, (int) ((int) c.getY()+(2.4*i)), 32, 37);
						try {

							tempo.sleep(75);
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
					for(int i=0;i<10;i++)
					{
						c.setBounds((int) c.getX()+i, (int) ((int) c.getY()+(1.09*i)), 32, 37);
						try {

							tempo.sleep(75);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 34,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "I3" : 
					for(int i = 0;i<24;i++)
					{
						c.setBounds((int) (c.getX()+i/3.), (int) (c.getY()), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(40);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "I4" : 
					for(int i = 0;i<45;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+i), (int) ((int) c.getCoordCarY()-(2.04*i)), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "I5" : 
					for(int i = 0;i<60;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-i), (int) ((int) c.getCoordCarY()-(1.13*i)), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "I6" : 
					for(int i = 0;i<98;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-i),( c.getCoordCarY()), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(8);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());

				}
			}
			else if(start.getPlaceName().contains("R1"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "O1" :
					for(int i = 0;i<47;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+2*i), (c.getCoordCarY()), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R2" :
					for(int i = 0;i<91;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-2.06*i), (int) (c.getCoordCarY()-i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "C" :
					for(int i = 0;i<108;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-1.73*i), (c.getCoordCarY()+i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R6" :
					for(int i = 0;i<105;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()+2*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				}
			}
			else if(start.getPlaceName().contains("R2"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "O2" :
					for(int i = 0;i<61;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+i), (int) (c.getCoordCarY()-1.22*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R1" :
					for(int i = 0;i<91;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+2.06*i), (int) (c.getCoordCarY()+i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R3" :
					for(int i = 0;i<80;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-2.3*i), (c.getCoordCarY()+i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "C" :
					for(int i = 0;i<100;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()+2*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				}

			}
			else if(start.getPlaceName().contains("R3"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "O3" :
					for(int i = 0;i<31;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-i), (int) (c.getCoordCarY()-2.6*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R2" :
					for(int i = 0;i<91;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+2.06*i), (int) (c.getCoordCarY()-i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "C" :
					for(int i = 0;i<111;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+1.73*i), (c.getCoordCarY()+i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R4" :
					for(int i = 0;i<43;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+i/3), (int) (c.getCoordCarY()+5.24*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				}
			}
			else if(start.getPlaceName().contains("R4"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "O4" :
					for(int i = 0;i<50;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-2*i), (c.getCoordCarY()), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R5" :
					for(int i = 0;i<94;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+1.85*i), (int) (c.getCoordCarY()+i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "C" :
					for(int i = 0;i<100;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+1.73*i), (c.getCoordCarY()-i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R3" :
					for(int i = 0;i<43;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-i/3), (int) (c.getCoordCarY()-5.24*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				}
			}
			else if(start.getPlaceName().contains("R5"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "O5" :
					for(int i = 0;i<64;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-i), (int) (c.getCoordCarY()+1.05*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R6" :
					for(int i = 0;i<91;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+2.04*i), (int) (c.getCoordCarY()-i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R4" :
					for(int i = 0;i<94;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-1.85*i), (int) (c.getCoordCarY()-i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "C" :
					for(int i = 0;i<98;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()-2*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				}

			}
			else if(start.getPlaceName().contains("R6"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "O6" :
					for(int i = 0;i<35;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+i), (int) (c.getCoordCarY()+2.6*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R5" :
					for(int i = 0;i<91;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-2.06*i), (int) (c.getCoordCarY()+i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "C" :
					for(int i = 0;i<108;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-1.73*i), (c.getCoordCarY()-i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R1" :
					for(int i = 0;i<105;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()-2*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				}
			}
			else if(start.getPlaceName().contains("C"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "R6" :
					for(int i = 0;i<108;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+1.73*i), (c.getCoordCarY()+i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R3" :
					for(int i = 0;i<111;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-1.73*i), (c.getCoordCarY()-i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R5" :
					for(int i = 0;i<98;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()+2*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R2" :
					for(int i = 0;i<98;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()-2*i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R1" :
					for(int i = 0;i<108;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+1.73*i), (c.getCoordCarY()-i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;
				case "R4" :
					for(int i = 0;i<100;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-1.73*i), (c.getCoordCarY()+i), 32, 37);
						try {
							//Thread.sleep(100);
							tempo.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					c.setBounds(end.getCoordX(),end.getCoordY(), 32,37);
					c.setCoordCarX(end.getCoordX());
					c.setCoordCarY(end.getCoordY());
					break;

				}
			}
			//calcul.start(); // /!\ to be verified !

		}		
		
		
		
		
		//@Override
		/*public void onMailReceivedByCar(MailBoxEvent e) {
			Car car = mainBox.fleet.get(e.indexUpdaterInMailBoxList);
			
			if (!(car.getLastPosition()==null))
			System.out.println(car.getLastPosition().getPlaceName() + " " + car.getPosition().getPlaceName());
			String action = e.updateAction;
				
			if (action.equals("POSITION_CHANGED"))
			{
				System.out.println("Window a d�tect� un changement de position pour "+car.getCarName());
				if (car.getLastPosition()==null)
				{
					moveToStartingPoint(mainBox.findSpecificPlace(car.getPosition().getPlaceName()),car);
				}
				else
				{
					this.moveCarView(mainBox.findSpecificPlace(car.getLastPosition().getPlaceName()), mainBox.findSpecificPlace(car.getPosition().getPlaceName()),car);
				}
				
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
			
			if (e.updateAction.equals("Start"))
			{
				System.out.println("Window � bien re�u Start");
			}
		
		}
		*/
		
		@Override
		public void run() {
			int passage = 0;			
			
			this.sleep(999999);
			
			//Boucle infine du thread affichage
			while(true)
			{
				displayer.updateLabels();
				//System.out.println(passage+"-i�me passage dans la boucle infinie de run() de Window ! ");
				
				eventGiven = false;
				noEventRemain = false;
				//On v�rifie si la liste des t�ches n'est pas vide et �galement s'il y a au moins un thread libre
				if(!this.tasks.isEmpty() && nbFreeThread!=0)
				{
					Iterator<MailBoxEvent> iterator = tasks.iterator();
					eventToDisplay = iterator.next();
					//Cas o� le thread1 est libre et le thread2 est libre
					if((!internalThread.isRunning()) && (!internalThread2.isRunning()))
					{
						while(!eventGiven && !noEventRemain)
						{
							//Si le thread1 est partiellement libre, cad n'a pas encore fini avec sa voiture
							if(internalThread.actualManagedCar!=null)
							{

								//Si la voiture de l'�vent a d�j� �t� trait�e par le thread1 on la lui attribue
								if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread.actualManagedCar))
								{
									tasks.remove(eventToDisplay);
									if (tasks.isEmpty())
										noEventRemain = true;
									
									internalThread.setManageredObjects(eventToDisplay, this, "internalThread");
									
										internalThread.interrupt();
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									eventGiven = true;
								}
								//Si la voiture n'a pas �t� trait�e par thread1 d�j�
								else
								{
									//Si le thread2 est partiellement libre, cad n'a pas encore fini avec sa voiture
									if(internalThread2.actualManagedCar!=null)
									{
										//Si la voiture de l'�vent a d�j� �t� trait�e par le thread2 on la lui attribue
										if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread2.actualManagedCar))
										{
											tasks.remove(eventToDisplay);
											if (tasks.isEmpty())
												noEventRemain = true;
											internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
											
											internalThread2.interrupt();
											try {
												Thread.sleep(30);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											eventGiven = true;
										}
										//Si les 2 thread sont partiellement libres avec une autre voiture, on prend l'event suivant. 
										else
										{
											//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
											if (!iterator.hasNext())
											{
												noEventRemain = true;
											}
											else
											{
												eventToDisplay = iterator.next();
											}
										}
									}
									//Si le thread2 est totalement libre on lui attribue l'�vent
									else
									{
										tasks.remove(eventToDisplay);
										
										if (tasks.isEmpty())
											noEventRemain = true;
										
										internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
										
										internalThread2.interrupt();
										try {
											Thread.sleep(30);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										eventGiven = true;
									}
										
								}
							}
							//Si le thread1 est totalement libre
							else
							{

								
								//Si le thread2 est partiellement libre, cad n'a pas encore fini avec sa voiture
								if(internalThread2.actualManagedCar!=null)
								{
									//Si la voiture de l'�vent a d�j� �t� trait�e par le thread2 on la lui attribue
									if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread2.actualManagedCar))
									{
										tasks.remove(eventToDisplay);
										if (tasks.isEmpty())
											noEventRemain = true;
										internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
										internalThread2.interrupt();
										try {
											Thread.sleep(30);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										eventGiven = true;
									}else
									{
										if(internalThread.actualManagedCar!=null)
										System.out.println(internalThread.actualManagedCar.getCarName());
										tasks.remove(eventToDisplay);
										if (tasks.isEmpty())
											noEventRemain = true;
										
										internalThread.setManageredObjects(eventToDisplay, this, "internalThread");
										
										internalThread.interrupt();
										try {
											Thread.sleep(30);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										eventGiven = true;
									}
								}
								//Sinon on l'attribue au thread1 totalement libre
								else
								{
									if(internalThread.actualManagedCar!=null)
									System.out.println(internalThread.actualManagedCar.getCarName());
									tasks.remove(eventToDisplay);
									if (tasks.isEmpty())
										noEventRemain = true;
									
									internalThread.setManageredObjects(eventToDisplay, this, "internalThread");
									
									internalThread.interrupt();
									try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									eventGiven = true;
								}
							}
							if (tasks.isEmpty())
								noEventRemain = true;
						}
					}
					//Cas o� le thread1 est libre et le thread2 est occup�
					else if((!internalThread.isRunning()) && (internalThread2.isRunning()))
					{
						while(!eventGiven && !noEventRemain)
						{
							//Si la voiture de l'�vent est d�j� trait�e par le thread2 occup� on passe � un autre event
							if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread2.actualManagedCar))
							{
								//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
								if (!iterator.hasNext())
								{
									noEventRemain = true;
								}
								else
								{
									eventToDisplay = iterator.next();
								}
							}
							//Si la voiture n'est pas trait�e par le thread2 occup� 
							else
							{
								//Si le thread1 libre n'a pas fini de g�rer la voiture en cours
								if (internalThread.actualManagedCar!=null)
								{
									//Si la voiture de l'�vent est justement la voiture qu'il avait commenc� � g�rer et on le lui attribue
									if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread.actualManagedCar))
									{
										tasks.remove(eventToDisplay);
										
										if (tasks.isEmpty())
											noEventRemain = true;
										
										internalThread.setManageredObjects(eventToDisplay, this, "internalThread");
										
										internalThread.interrupt();
										try {
											Thread.sleep(30);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										eventGiven = true;
									}
									//Sinon on passe � un autre event
									else
									{
										//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
										if (!iterator.hasNext())
										{
											noEventRemain = true;
										}
										else
										{
											eventToDisplay = iterator.next();
										}
									}
								}
								//Si le thread1 libre est totalement libre (ayant fini de transf�rer sa derni�re voiture au parking) on lui attribue l'�vent
								else
								{
									tasks.remove(eventToDisplay);
									
									if (tasks.isEmpty())
										noEventRemain = true;
									
									internalThread.setManageredObjects(eventToDisplay, this, "internalThread");
									
										internalThread.interrupt();
									try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									eventGiven = true;
								}
							}
						}
					}
					//Cas o� le thread1 est occup� et le thread2 est libre
					else if((internalThread.isRunning()) && (!internalThread2.isRunning()))
					{
						while(!eventGiven && !noEventRemain)
						{
							//Si la voiture de l'�vent est d�j� trait�e par le thread1 occup� on passe � un autre event
							if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread.actualManagedCar))
							{
								
								//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
								if (!iterator.hasNext())
								{
									noEventRemain = true;
								}
								else
								{
									eventToDisplay = iterator.next();
								}
							}
							//Si la voiture n'est pas trait�e par le thread1 occup� 
							else
							{
								//Si le thread2 libre n'a pas fini de g�rer la voiture en cours
								if (internalThread2.actualManagedCar!=null)
								{
									//Si la voiture de l'�vent est justement la voiture qu'il avait commenc� � g�rer et on le lui attribue
									if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread2.actualManagedCar))
									{
										tasks.remove(eventToDisplay);
										
										if (tasks.isEmpty())
											noEventRemain = true;
										
										internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
										
										internalThread2.interrupt();
										try {
											Thread.sleep(30);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										eventGiven = true;
									}
									//Sinon on passe � un autre event
									else
									{
										//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
										if (!iterator.hasNext())
										{
											noEventRemain = true;
										}
										else
										{
											eventToDisplay = iterator.next();
										}
									}
								}
								//Si le thread2 libre est totalement libre (ayant fini de transf�rer sa derni�re voiture au parking) on lui attribue l'�vent
								else
								{
									tasks.remove(eventToDisplay);
									
									if (tasks.isEmpty())
										noEventRemain = true;
									
									
									internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
									internalThread2.interrupt();
									try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									eventGiven = true;
								}
							}
						}
					}
					
				}
				
				if(tasks.isEmpty() && (!internalThread.isRunning()) && (!internalThread2.isRunning()))
				{
					System.out.println("-->> On lance calcul");
					calcul.interrupt();
					this.sleep(999999);
				}
				
				
				//passage++;
			}
		}
		
		private void sleep(int second)
		{
			try {
				Thread.sleep(second*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
		
}
