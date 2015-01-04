import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*This is also a leading class in our program. It represent in the same time the entity of car graphically and as an actor if event communication
 * The car receive, reserve and check all resources related to a specific trip, a implement by itself the associated displacement.*/

public class Car extends JPanel implements MailBoxListener
{	
	private static final long serialVersionUID = 1L;

	private String carName;
	private Order order;
	private Place lastPosition; // We put the anterior position. It permit us to move the car in the view, knowing from where to where the car moved
	private Place position;
	private boolean occuped;
	private boolean displayed;
	private String parking;
	private int coordCarX, coordCarY;
	private int numCar;

	MailBox mainBox;

	/* We have put attributes to private and added methods getter and setters to modify theses attributes.
	 * This permit us to know when the attributes of car are modified, and when we can put a method SendEvent, or
	 * Fire, to create and Event when we modify the car, so when we modify the MailBox, and it'll permit the Controller
	 *  and other Listeners to know updates from car */

	public Car(int i, String p, int coorX, int coorY, MailBox m)
	{
		super();
		numCar = i;
		setCarName("car"+i);
		order = new Order("WAIT");
		lastPosition = null;
		position = null;
		occuped = false;
		parking = p;
		coordCarX = coorX; // In the function main, when we will instantiate all the car, it's better to put directly in the constructor the right coordinates. 
		coordCarY = coorY;
		mainBox = m;
	}

	public Car (Car copie)
	{
		this.carName = copie.carName;
		this.order = copie.order;
		this.lastPosition = copie.lastPosition;
		this.position = copie.position;
		this.occuped = copie.occuped;
		this.parking = copie.parking;
		this.coordCarX = copie.coordCarX;
		this.coordCarY = copie.coordCarY;
		this.mainBox = copie.mainBox;
		this.numCar = copie.numCar;
	}


	public void reserveRoad()
	{
		for(int i=0;i<19;i++)
			if(order.mission.requestMap[i])
				for(Place p : mainBox.reservations)
					if (order.mission.requestMapPlaceName[i].equals(p.getPlaceName()))
						p.setPlaceIsFree(false);
	}


	public boolean checkRoad()
	{
		boolean ready = true;
		for(int i=0;i<19;i++)
			if(order.mission.requestMap[i])
				for(Place p : mainBox.reservations)
					if (order.mission.requestMapPlaceName[i].equals(p.getPlaceName()))
						if(!p.getPlaceIsFree())
							ready = false;
		return ready;
	}


	public void move()
	{
		for(Place p : position.getAdjacencyPlaceList())
			for(int i=0;i<19;i++)
				if(order.typeOrder.equals("MISSION"))
					if(order.mission.requestMap[i])
						if(order.mission.requestMapPlaceName[i].equals(p.getPlaceName()))
						{
							this.lastPosition = this.position;

							if(lastPosition!=null)				
								this.lastPosition.setPlaceIsFree(true);

							position = p;
							order.mission.requestMap[i]=false;
							MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", lastPosition, position);
							System.out.println("The "+this.getCarName()+" has moved from "+this.getLastPosition().getPlaceName()+" to "+this.getPosition().getPlaceName());
							mainBox.window.log.updateLog("The "+this.getCarName()+" has moved from "+this.getLastPosition().getPlaceName()+" to "+this.getPosition().getPlaceName());
							mainBox.fireMailBoxUpdated(event);
						}	
	}	


	public void paintComponent(Graphics g)
	{
		try
		{
			int nb = ((numCar-1)%6)+1;

			Image img = ImageIO.read(new File("image/car"+nb+".png"));
			g.drawImage(img, 0, 0, this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onMailReceivedByCar(MailBoxEvent e)
	{
		// TODO Auto-generated method stub
		if(e.updateAction.equals("POSITION_CHANGED") && e.indexUpdaterInMailBoxList == mainBox.fleet.indexOf(this) && this.order.typeOrder.equals("MISSION") && e.lastPlace!=null)
			this.move();
	}

	@Override
	public void onMailReceivedByMan(MailBoxEvent e)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onMailReceivedByController(MailBoxEvent e)
	{
		// TODO Auto-generated method stub
		if (this.order.typeOrder.equals("ENROLL") && e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this) )
		{
			System.out.println("The "+this.getCarName()+" has received ENROLL");
			mainBox.window.log.updateLog("The "+this.getCarName()+" has received ENROLL");
			this.lastPosition = this.position;
			this.position = this.order.enrollPlace;
			this.parking = "NONE";
			this.occuped = true;

			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", lastPosition, position);
			System.out.println("The "+this.getCarName()+" is properly ENROLLED !");
			mainBox.window.log.updateLog("The "+this.getCarName()+" is properly ENROLLED !");
			mainBox.fireMailBoxUpdated(event); 
		}
		else if(this.order.typeOrder.equals("MISSION") && e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this))
		{
			System.out.println("The "+this.getCarName()+" has received its MISSION");
			mainBox.window.log.updateLog("The "+this.getCarName()+" has received its MISSION");
			boolean ready = this.checkRoad();

			if(!ready)
			{
				MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "WAIT");
				mainBox.fireMailBoxUpdated(event);
			}
			else
			{
				this.reserveRoad();
				this.move();
			}

		}
		else if(this.order.typeOrder.equals("PARK")&& e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this))
		{

			System.out.println("The "+this.getCarName()+" has received PARK");
			mainBox.window.log.updateLog("The "+this.getCarName()+" has received PARK");
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "PARKED");

			Order o = new Order("WAIT");
			this.setOrder(o);
			this.setOccuped(false);
			System.out.println("The "+this.getCarName()+" is properly PARKED");
			mainBox.window.log.updateLog("The "+this.getCarName()+" is properly PARKED");
			mainBox.fireMailBoxUpdated(event);
		}
	}

	protected Order getOrder()
	{
		return this.order;
	}

	protected Place getLastPosition()
	{
		return this.lastPosition;
	}

	protected Place getPosition()
	{
		return this.position;
	}

	protected boolean isOccuped()
	{
		return this.occuped;
	}


	protected void setOrder(Order o)
	{ 
		this.order = o;
	}

	protected void setLastPosition(Place p)
	{
		this.lastPosition = p;
	}

	protected void setPosition(Place p)
	{
		this.position = p;
	}

	protected void setOccuped(boolean b)
	{
		this.occuped = b;
	}

	protected String getParking()
	{
		return parking;
	}

	protected void setParking(String p)
	{
		parking = p;
	}

	protected String getCarName()
	{
		return carName;
	}

	protected void setCarName(String carName)
	{
		this.carName = carName;
	}

	protected int getCoordCarX()
	{
		return coordCarX;
	}

	protected void setCoordCarX(int coordCarX)
	{
		this.coordCarX = coordCarX;
	}

	protected int getCoordCarY()
	{
		return coordCarY;
	}

	protected void setCoordCarY(int coordCarY)
	{
		this.coordCarY = coordCarY;
	}

	protected boolean isDisplayed()
	{
		return displayed;
	}

	protected void setDisplayed(boolean displayed)
	{
		this.displayed = displayed;
	}

}
