import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

	public class Window extends JFrame implements  Runnable {

		private static final long serialVersionUID = 1L;
		
		MailBox mainBox; 
		LinkedList<MailBoxEvent> tasks;
		int nbFreeThread=2;
		Thread thread;
		
		MovingManager internalThread;
		MovingManager internalThread2;
		
		boolean isRunningThread1 = false;
		boolean isRunningThread2 = false;
		
		boolean eventGiven;
		boolean noEventRemain;
		MailBoxEvent eventToDisplay;
		
		public Window(MailBox MB)
		{
			
			mainBox = MB;	
			tasks = new LinkedList<MailBoxEvent>();
			internalThread = new MovingManager("internalThread", this);
			internalThread.start();
			internalThread2 = new MovingManager("internalThread2", this);
			internalThread2.start();
			
			// Creation of the JPanel and his JLayeredPane
			JLayeredPane jlp = new JLayeredPane();
			jlp.setOpaque(false);
			
			// Create the HUB
			Hub displayer = new Hub(mainBox);
			
			// Create the event log
			EventLog log = new EventLog(mainBox);
			//this.getContentPane().add(log.scrollPane, BorderLayout.CENTER);
			//log.scrollPane.setViewportView(log);
			//log.updateLog("La police lance une requette et attend que les voleurs de voiture soient RELEASE : les poulets arriveront toujors en retard ");
			
			
			// Create background image
			Background bg = new Background();
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
			this.setSize(bg.getWidth()+displayer.getWidth(),bg.getHeight()+log.getHeight());
			this.setResizable(false);
			this.setLocationRelativeTo(null); // JFrame in the center of the window
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
			// Add the JLayeredPane to the frame
			this.add(jlp, BorderLayout.CENTER);
			this.add(displayer, BorderLayout.EAST);
			this.add(log, BorderLayout.SOUTH);
			this.setVisible(true);
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
							
							Thread.sleep(75);
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
							
							Thread.sleep(75);
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
							Thread.sleep(40);
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
							Thread.sleep(25);
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
							Thread.sleep(15);
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
							Thread.sleep(8);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(15);
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
							Thread.sleep(10);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(5);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(20);
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
							Thread.sleep(50);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(50);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(10);
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
							Thread.sleep(25);
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
							Thread.sleep(20);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
							Thread.sleep(25);
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
				System.out.println("Window a détecté un changement de position pour "+car.getCarName());
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
				System.out.println("Window à bien reçu Start");
			}
		
		}
		*/
		
		@Override
		public void run() {
						
			
			//Boucle infine du thread affichage
			while(true)
			{
				eventGiven = false;
				noEventRemain = false;
				//On vérifie si la liste des tâches n'est pas vide et également s'il y a au moins un thread libre
				if(!this.tasks.isEmpty() && nbFreeThread!=0)
				{
					eventToDisplay = tasks.getFirst();
					
					//Cas où le thread1 est libre et le thread2 est libre
					if((!internalThread.isAliveOther()) && (!internalThread2.isAliveOther()))
					{
						while(!eventGiven && !noEventRemain)
						{
							//Si le thread1 est partiellement libre, cad n'a pas encore fini avec sa voiture
							if(internalThread.actualManagedCar!=null)
							{
								//Si la voiture de l'évent a déjà été traitée par le thread1 on la lui attribue
								if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread.actualManagedCar))
								{
									tasks.remove(eventToDisplay);
									
									if (tasks.isEmpty())
										noEventRemain = true;
									
									internalThread.setManageredObjects(eventToDisplay, this, "internalThread");
									
									internalThread.interrupt();
									eventGiven = true;
								}
								//Si la voiture n'a pas été traitée par thread1 déjà
								else
								{
									//Si le thread2 est partiellement libre, cad n'a pas encore fini avec sa voiture
									if(internalThread2.actualManagedCar!=null)
									{
										//Si la voiture de l'évent a déjà été traitée par le thread2 on la lui attribue
										if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread2.actualManagedCar))
										{
											tasks.remove(eventToDisplay);
											
											if (tasks.isEmpty())
												noEventRemain = true;
											
											internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
											internalThread2.interrupt();
											eventGiven = true;
										}
										//Si les 2 thread sont partiellement libres avec une autre voiture, on prend l'event suivant. 
										else
										{
											//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
											if (tasks.indexOf(eventToDisplay)+1>tasks.indexOf(tasks.getLast()))
											{
												noEventRemain = true;
											}
											else
											{
												eventToDisplay = tasks.get(tasks.indexOf(eventToDisplay)+1);
											}
										}
									}
									//Si le thread2 est totalement libre on lui attribue l'évent
									else
									{
										tasks.remove(eventToDisplay);
										
										if (tasks.isEmpty())
											noEventRemain = true;
										
										internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
										internalThread2.interrupt();
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
									//Si la voiture de l'évent a déjà été traitée par le thread2 on la lui attribue
									if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread2.actualManagedCar))
									{
										tasks.remove(eventToDisplay);
										
										if (tasks.isEmpty())
											noEventRemain = true;
										
										internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
										internalThread2.interrupt();
										eventGiven = true;
									}
								}
								//Sinon on l'attribue au thread1 totalement libre
								else
								{
									tasks.remove(eventToDisplay);
									
									if (tasks.isEmpty())
										noEventRemain = true;
									
									internalThread.setManageredObjects(eventToDisplay, this, "internalThread");
									internalThread.interrupt();
									eventGiven = true;
								}
							}
							if (tasks.isEmpty())
								noEventRemain = true;
						}
					}
					//Cas où le thread1 est libre et le thread2 est occupé
					else if((!internalThread.isAliveOther()) && (internalThread2.isAliveOther()))
					{
						while(!eventGiven && !noEventRemain)
						{
							//Si la voiture de l'évent est déjà traitée par le thread2 occupé on passe à un autre event
							if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread2.actualManagedCar))
							{
								//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
								if (tasks.indexOf(eventToDisplay)+1>tasks.indexOf(tasks.getLast()))
								{
									noEventRemain = true;
								}
								else
								{
									eventToDisplay = tasks.get(tasks.indexOf(eventToDisplay)+1);
								}
							}
							//Si la voiture n'est pas traitée par le thread2 occupé 
							else
							{
								//Si le thread1 libre n'a pas fini de gérer la voiture en cours
								if (internalThread.actualManagedCar!=null)
								{
									//Si la voiture de l'évent est justement la voiture qu'il avait commencé à gérer et on le lui attribue
									if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread.actualManagedCar))
									{
										tasks.remove(eventToDisplay);
										
										if (tasks.isEmpty())
											noEventRemain = true;
										
										internalThread.setManageredObjects(eventToDisplay, this, "internalThread");
										internalThread.interrupt();
										eventGiven = true;
									}
									//Sinon on passe à un autre event
									else
									{
										//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
										if (tasks.indexOf(eventToDisplay)+1>tasks.indexOf(tasks.getLast()))
										{
											noEventRemain = true;
										}
										else
										{
											eventToDisplay = tasks.get(tasks.indexOf(eventToDisplay)+1);
										}
									}
								}
								//Si le thread1 libre est totalement libre (ayant fini de transférer sa dernière voiture au parking) on lui attribue l'évent
								else
								{
									tasks.remove(eventToDisplay);
									
									if (tasks.isEmpty())
										noEventRemain = true;
									
									internalThread.setManageredObjects(eventToDisplay, this, "internalThread");
									internalThread.interrupt();
									eventGiven = true;
								}
							}
						}
					}
					//Cas où le thread1 est occupé et le thread2 est libre
					else if((internalThread.isAliveOther()) && (!internalThread2.isAliveOther()))
					{
						while(!eventGiven && !noEventRemain)
						{
							//Si la voiture de l'évent est déjà traitée par le thread1 occupé on passe à un autre event
							if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread.actualManagedCar))
							{
								
								//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
								if (tasks.indexOf(eventToDisplay)+1>tasks.indexOf(tasks.getLast()))
								{
									noEventRemain = true;
								}
								else
								{
									eventToDisplay = tasks.get(tasks.indexOf(eventToDisplay)+1);
								}
							}
							//Si la voiture n'est pas traitée par le thread1 occupé 
							else
							{
								//Si le thread2 libre n'a pas fini de gérer la voiture en cours
								if (internalThread2.actualManagedCar!=null)
								{
									//Si la voiture de l'évent est justement la voiture qu'il avait commencé à gérer et on le lui attribue
									if (eventToDisplay.indexUpdaterInMailBoxList==mainBox.fleet.indexOf(internalThread2.actualManagedCar))
									{
										tasks.remove(eventToDisplay);
										
										if (tasks.isEmpty())
											noEventRemain = true;
										
										internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
										internalThread2.interrupt();
										eventGiven = true;
									}
									//Sinon on passe à un autre event
									else
									{
										//S'il n'y plus d'autres events on sort de la boucle sinon on prend l'event suivant
										if (tasks.indexOf(eventToDisplay)+1>tasks.indexOf(tasks.getLast()))
										{
											noEventRemain = true;
										}
										else
										{
											eventToDisplay = tasks.get(tasks.indexOf(eventToDisplay)+1);
										}
									}
								}
								//Si le thread2 libre est totalement libre (ayant fini de transférer sa dernière voiture au parking) on lui attribue l'évent
								else
								{
									tasks.remove(eventToDisplay);
									
									if (tasks.isEmpty())
										noEventRemain = true;
									
									
									internalThread2.setManageredObjects(eventToDisplay, this, "internalThread2");
									internalThread2.interrupt();
									eventGiven = true;
								}
							}
						}
					}
					
				}
			}
		}
		
		
}
