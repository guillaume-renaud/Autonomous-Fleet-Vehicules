import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Car extends JPanel implements MailBoxListener {
	
	private static final long serialVersionUID = 1L;
	
	private String carName;
	private Order order;
	private Place lastPosition; // We put the anterior position. It permit us to move the car in the view, knowing from where to where the car moved
	private Place position;
	private boolean occuped;
	private boolean displayed;
	private String parking;
	private int coordCarX, coordCarY;
	 
	MailBox mainBox;
	
	
	/* Cheikh35 : We have to put attributes to private and to add methods getter and setters to modify theses attributes.
	 * Because this permit us to know when the attributes of car are modified, and we can put a method SendEvent, or
	 * Fire, to create and Event when we modify the car, so the MailBox, and it'll permit the Controller to know 
	 * updates*/
	
	public Car(int i, String p, int coorX, int coorY, MailBox m){
		super();
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
	
	public Car (Car copie) {
		this.carName = copie.carName;
		this.order = copie.order;
		this.lastPosition = copie.lastPosition;
		this.position = copie.position;
		this.occuped = copie.occuped;
		this.parking = copie.parking;
		this.coordCarX = copie.coordCarX;
		this.coordCarY = copie.coordCarY;
		this.mainBox = copie.mainBox;
		
	}
	
	public void reserveRoad(){
		for(int i=0;i<19;i++)
			if(order.mission.requestMap[i])
				for(Place p : mainBox.reservations)
				{
					if (order.mission.requestMapPlaceName[i].equals(p.getPlaceName()))
					{
						p.setPlaceIsFree(false);
					}
				}
	}
	public boolean checkRoad(){
		boolean ready = true;
		for(int i=0;i<19;i++)
		{
			if(order.mission.requestMap[i])
			{
				for(Place p : mainBox.reservations)
				{
					if (order.mission.requestMapPlaceName[i].equals(p.getPlaceName()))
					{
						if(!p.getPlaceIsFree())
							ready = false;
					}
				}
			}
		}
		return ready;
	}
	
	public void move() {
		
		//position.setPlaceIsFree(true);
		for(Place p : position.getAdjacencyPlaceList())
			for(int i=0;i<19;i++)
			{
				if(order.typeOrder.equals("MISSION"))
				{
					if(order.mission.requestMap[i])
					{
						if(order.mission.requestMapPlaceName[i].equals(p.getPlaceName()))
						{
							this.lastPosition = this.position;
							if(lastPosition!=null)				
							{

								this.lastPosition.setPlaceIsFree(true);
							}
							position = p;
							order.mission.requestMap[i]=false;
							MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", lastPosition, position);
							//mainBox.window.tasks.addLast(event);
							System.out.println("La voiture "+this.getCarName()+" a bougé de "+this.getLastPosition().getPlaceName()+" à "+this.getPosition().getPlaceName());
							mainBox.fireMailBoxUpdated(event);
						}	

						
					}	
				}
				
			}
		/*for(Place p : position.getAdjacencyPlaceList())
		{
			for(int i=0;i<19;i++)
			{
				if(order.typeOrder.equals("MISSION"))
				if(order.mission.requestMap[i])
				{
					if(order.mission.requestMapPlaceName[i].equals(p.getPlaceName()))
					{
						this.lastPosition = this.position;
						position = p;
						order.mission.requestMap[i]=false;
						MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", lastPosition, position);
						mainBox.window.tasks.addLast(event);
						System.out.println("La voiture "+this.getCarName()+" a bougé de "+this.getLastPosition().getPlaceName()+" à "+this.getPosition().getPlaceName());
						mainBox.fireMailBoxUpdated(event); 
					}	
				}	
			}
		}
		for(Place p : position.getAdjacencyPlaceList())
		{
			for(int i=0;i<19;i++)
			{
				if(order.typeOrder.equals("MISSION"))
				if(order.mission.requestMap[i])
				{
					if(order.mission.requestMapPlaceName[i].equals(p.getPlaceName()))
					{
						this.lastPosition = this.position;
						position = p;
						order.mission.requestMap[i]=false;
						MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", lastPosition, position);
						mainBox.window.tasks.addLast(event);
						System.out.println("La voiture "+this.getCarName()+" a bougé de "+this.getLastPosition().getPlaceName()+" à "+this.getPosition().getPlaceName());
						mainBox.fireMailBoxUpdated(event);
						
					}	
				}	
			}
		}
		if(order.typeOrder.equals("MISSION"))
		for(Place p : position.getAdjacencyPlaceList())
		{
			for(int i=0;i<19;i++)
			{
				if(order.typeOrder.equals("MISSION"))
				if(order.mission.requestMap[i])
				{
					if(order.mission.requestMapPlaceName[i].equals(p.getPlaceName()))
					{
						this.lastPosition = this.position;
						position = p;
						order.mission.requestMap[i]=false;
						MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", lastPosition, position);
						mainBox.window.tasks.addLast(event);
						System.out.println("La voiture "+this.getCarName()+" a bougé de "+this.getLastPosition().getPlaceName()+" à "+this.getPosition().getPlaceName());
						mainBox.fireMailBoxUpdated(event); 
						
					}	
				}	
			}

		}*/
	}	
	
	public void paintComponent(Graphics g)
	{
		try
		{
			Image img = ImageIO.read(new File("image/car.png"));
			g.drawImage(img, 0, 0, this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onMailReceivedByCar(MailBoxEvent e) {
		// TODO Auto-generated method stub
		if (e.updateAction.equals("WAIT") && this.order.typeOrder.equals("ENROLL") && e.indexUpdaterInMailBoxList == mainBox.fleet.indexOf(this) )
		{
			boolean ready = this.checkRoad();
			System.out.println(ready);
			if(!ready)
			{
				MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "WAIT");
				mainBox.fireMailBoxUpdated(event);
			}else
			{
				MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "READY");
				mainBox.fireMailBoxUpdated(event);
			}
		}
		if(e.updateAction.equals("POSITION_CHANGED") && e.indexUpdaterInMailBoxList == mainBox.fleet.indexOf(this) && this.order.typeOrder.equals("MISSION") && e.lastPlace!=null)
		{
			this.move();
		}
	}

	@Override
	public void onMailReceivedByMan(MailBoxEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMailReceivedByController(MailBoxEvent e) {
		// TODO Auto-generated method stub
		
		if (this.order.typeOrder.equals("ENROLL") && e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this) )
		{
			System.out.println("La voiture "+this.getCarName()+" a bien reçu ENROLL");
			this.lastPosition = this.position;
			this.position = this.order.enrollPlace;
			this.parking = "NONE";
			this.occuped = true;
			
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", lastPosition, position);
			//mainBox.window.tasks.addLast(event);
			System.out.println("La voiture "+this.getCarName()+" c'est bien ENROLL comme il faut !");
			mainBox.fireMailBoxUpdated(event);
		}
		else if(this.order.typeOrder.equals("MISSION") && e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this))
		{
			System.out.println("La voiture "+this.getCarName()+" a bien reçu sa MISSION");
			
			boolean ready = this.checkRoad();
			if(!ready)
			{
				MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "WAIT");
				mainBox.fireMailBoxUpdated(event);
			}else
			{
				this.reserveRoad();
				this.move();
			}
			
		}
		else if(this.order.typeOrder.equals("RELEASE")&& e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this))
		{
			System.out.println("La voiture "+this.getCarName()+" a bien fini sa MISSION");
			System.out.println("La voiture "+this.getCarName()+" a bien reçu RELEASE");
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "RELEASED");
			System.out.println("La voiture "+this.getCarName()+" c'est bien RELEASED");
			mainBox.fireMailBoxUpdated(event);
		}
		else if(this.order.typeOrder.equals("PARK")&& e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this))
		{
			
			System.out.println("La voiture "+this.getCarName()+" a bien reçu PARK");
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "PARKED");
			//mainBox.window.tasks.addLast(event);
			System.out.println("La voiture "+this.getCarName()+" s'est bien PARKED");
			mainBox.fireMailBoxUpdated(event);
		}
	}

	public Order getOrder() {
		return this.order;
	}
	
	public Place getLastPosition() {
		return this.lastPosition;
	}
	
	public Place getPosition() {
		return this.position;
	}
	
	public boolean isOccuped() {
		return this.occuped;
	}
	
	
	public void setOrder(Order o) { //It's just an example, it's not finished.
		this.order = o;
	}
	
	public void setLastPosition(Place p) {
		this.lastPosition = p;
	}
	
	public void setPosition(Place p) {
		this.position = p;
	}
	
	public void setOccuped(boolean b) {
		this.occuped = b;
	}
	
	public String getParking() {
		return parking;
	}

	public void setParking(String p) {
		parking = p;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getCoordCarX() {
		return coordCarX;
	}

	public void setCoordCarX(int coordCarX) {
		this.coordCarX = coordCarX;
	}

	public int getCoordCarY() {
		return coordCarY;
	}

	public void setCoordCarY(int coordCarY) {
		this.coordCarY = coordCarY;
	}

	public boolean isDisplayed() {
		return displayed;
	}

	public void setDisplayed(boolean displayed) {
		this.displayed = displayed;
	}

}
