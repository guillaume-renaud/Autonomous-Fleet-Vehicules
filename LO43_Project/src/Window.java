import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

	public class Window extends JFrame implements  Runnable{

		private static final long serialVersionUID = 1L;
		
		MailBox mainBox; 
		
		LinkedList<MailBoxEvent> tasks;

		int nbFreeThread=2;
		
		Thread thread;
		
		public Window(MailBox MB)
		{
			
			mainBox = MB;	
			tasks = new LinkedList<MailBoxEvent>();
			
			
			// Creation of the JLayeredPane
			JLayeredPane jlpTest = new JLayeredPane();
			jlpTest.setOpaque(false);
						
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
			}
			
			// Add the images to the JLayeredPane with a different deep level
			jlpTest.add(bg, new Integer(1));
			
			for (Car car : mainBox.fleet)
			{
				jlpTest.add(car, new Integer(2));
			}
			
			// Configure the frame
			this.setTitle("Autonomous Fleet Vehicules");
			this.setSize(bg.getWidth()+displayer.getWidth(),bg.getHeight()+log.getHeight());
			this.setResizable(false);
			this.setLocationRelativeTo(null); // JFrame in the center of the window
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// Add the JLayeredPane to the frame
			this.add(jlpTest, BorderLayout.CENTER);
			this.add(displayer, BorderLayout.EAST);
			this.add(log, BorderLayout.SOUTH);
			this.setVisible(true);
		}

		public void moveToStartingPoint(Place start, Car c){
			c.setBounds(start.getCoordX(),start.getCoordY(), 34,37);
			c.setCoordCarX(start.getCoordX());
			c.setCoordCarY(start.getCoordY());
		}
		
		public void moveCarView(Place start,Place end,Car c){
		if(start.getPlaceName().contains("I"))
		{
			//calcul.interrupt(); // /!\ to be verified !
			switch (start.getPlaceName())
			{
				case "I1" :
					for(int i=0;i<38;i++)
					{
						c.setBounds(c.getCoordCarX()-i, (int) ((int) c.getCoordCarY()+(2.4*i)), 32, 37);
						try {
							
							Thread.sleep(50);
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
					for(int i=0;i<68;i++)
					{
						c.setBounds(c.getCoordCarX()+i, (int) ((int) c.getCoordCarY()+(1.09*i)), 32, 37);
						try {
							
							Thread.sleep(25);
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
					for(int i = 0;i<90;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+i), (c.getCoordCarY()), 32, 37);
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
				case "I6" : 
					for(int i = 0;i<98;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-i),( c.getCoordCarY()), 32, 37);
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
					
			}
		}
		else if(start.getPlaceName().contains("R1"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "O1" :
					for(int i = 0;i<90;i++)
					{
						c.setBounds((int) (c.getCoordCarX()+i), (c.getCoordCarY()), 32, 37);
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
						c.setBounds((int) (c.getCoordCarX()-i), (int) (c.getCoordCarY()-2.06*i), 32, 37);
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
					for(int i = 0;i<211;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()+i), 32, 37);
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
						c.setBounds((int) (c.getCoordCarX()+i), (int) (c.getCoordCarY()+2.06*i), 32, 37);
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
					for(int i = 0;i<108;i++)
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
				case "C" :
					for(int i = 0;i<211;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()+i), 32, 37);
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
						c.setBounds((int) (c.getCoordCarX()+i), (int) (c.getCoordCarY()+2.06*i), 32, 37);
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
				case "R4" :
					for(int i = 0;i<211;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()+i), 32, 37);
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
			else if(start.getPlaceName().contains("R4"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "O4" :
					for(int i = 0;i<90;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-i), (c.getCoordCarY()), 32, 37);
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
						c.setBounds((int) (c.getCoordCarX()+i), (int) (c.getCoordCarY()+2.06*i), 32, 37);
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
					for(int i = 0;i<211;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()-i), 32, 37);
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
			else if(start.getPlaceName().contains("R5"))
			{
				//calcul.interrupt(); // /!\ to be verified !
				switch (end.getPlaceName())
				{
				case "O5" :
					for(int i = 0;i<61;i++)
					{
						c.setBounds((int) (c.getCoordCarX()-i), (int) (c.getCoordCarY()+1.22*i), 32, 37);
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
						c.setBounds((int) (c.getCoordCarX()+i), (int) (c.getCoordCarY()-2.06*i), 32, 37);
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
				case "C" :
					for(int i = 0;i<211;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()-i), 32, 37);
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
					for(int i = 0;i<31;i++)
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
						c.setBounds((int) (c.getCoordCarX()-i), (int) (c.getCoordCarY()-2.06*i), 32, 37);
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
					for(int i = 0;i<211;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()-i), 32, 37);
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
				case "R5" :
					for(int i = 0;i<211;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()+i), 32, 37);
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
					for(int i = 0;i<211;i++)
					{
						c.setBounds((int) (c.getCoordCarX()), (c.getCoordCarY()-i), 32, 37);
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
					for(int i = 0;i<105;i++)
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
			
			while(true)
			{
				System.out.println("HAMZA BOxx1");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!this.tasks.isEmpty() && nbFreeThread!=0)
				{
					
					
				}
			}
		}
		
		public class EventManager extends Thread {
			
			Car actualManagedCar;
			MailBoxEvent actualManagedEvent;
			
			
			public void setManageredObjects (MailBoxEvent e)
			{
				actualManagedEvent = e;
				actualManagedCar = mainBox.fleet.get(e.indexUpdaterInMailBoxList);
			}
			
			public void run() {
				while(true)
				{
					System.out.println("HAMZA BOXX2");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
			
		}
		
}