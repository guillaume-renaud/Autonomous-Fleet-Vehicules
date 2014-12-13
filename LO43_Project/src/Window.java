import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

	public class Window extends JFrame implements MailBoxListener, Runnable{

		private static final long serialVersionUID = 1L;
		MailBox mainBox; 
		
		Thread thread;
		
		public Window(MailBox MB)
		{
			mainBox = MB;
			
				
			// Creation of the JLayeredPane
			JLayeredPane jlpTest = new JLayeredPane();
			jlpTest.setOpaque(false);
						
			// Create the HUB
			Hub displayer = new Hub(mainBox);
			
			// Create the event log
			EventLog log = new EventLog(mainBox);
			
			
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
					for(int i=0;i<68;i++)
					{
						c.setBounds(c.getCoordCarX()+i, (int) ((int) c.getCoordCarY()+(1.09*i)), 32, 37);
						try {
							
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
							thread.sleep(25);
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
				this.moveCarView(mainBox.findSpecificPlace("I1"), mainBox.findSpecificPlace("R1"),car);
				this.moveCarView(mainBox.findSpecificPlace("R1"), mainBox.findSpecificPlace("C"),car);
				this.moveCarView(mainBox.findSpecificPlace("C"), mainBox.findSpecificPlace("R4"),car);
				this.moveCarView(mainBox.findSpecificPlace("R4"), mainBox.findSpecificPlace("O4"),car);
				
			}
		}
		
		
}