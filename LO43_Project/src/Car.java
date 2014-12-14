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
	private String parking;
	private int coordCarX, coordCarY;
	 
	MailBox mainBox;
	
	
	/* Cheikh35 : We have to put attributes to private and to add methods getter and setters to modify theses attributes.
	 * Because this permit us to know when the attributes of car are modified, and we can put a method SendEvent, or
	 * Fire, to create and Event when we modify the car, so the MailBox, and it'll permit the Controller to know 
	 * updates*/
	
	public Car(int i, String p, int coorX, int coorY,MailBox m){
		super();
		setCarName("car"+i);
		order = new Order("WAIT");
		lastPosition = null;
		position = null;
		occuped = false;
		parking = p;
		coordCarX = coorX; // In the function main, when we will instantiate all the car, it's better to put directly in the constructor the right coordinates. 
		coordCarY = coorY;
		mainBox=m;
	}
	
	public boolean checkRoad(){
		boolean ready = true;
		for(int i=0;i<19;i++)
			if(order.mission.requestMap[i])
				for(Place p : mainBox.reservations)
					if (order.mission.requestMapPlaceName[i].equals(p))
						if(!p.getPlaceIsFree())
							ready = false;
		return ready;
	}
	
	public void move() {
		
		position.setPlaceIsFree(true);
		for(Place p : position.getAdjacencyPlaceList())
			for(int i=0;i<19;i++)
			{
				if(order.mission.requestMap[i])
				{
					if(order.mission.requestMapPlaceName[i].equals(p))
					{
						this.lastPosition = this.position;
						position = p;
						MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", 0); //I put 0 for the index of controller because it's not in a list.
						mainBox.fireMailBoxUpdated(event); 
					}	
				}	
			}	
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


	@Override
	public void onMailReceivedByCar(MailBoxEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMailReceivedByMan(MailBoxEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMailReceivedByController(MailBoxEvent e) {
		// TODO Auto-generated method stub
		if (this.order.equals("ENROLL") && e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this))
		{
			this.lastPosition = this.position;
			this.position = this.order.enrollPlace;
			this.parking = "NONE";
			this.occuped = true;
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", 0);
			mainBox.fireMailBoxUpdated(event); 
		}
		else if(this.order.equals("MISSION") && e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this)){
			boolean ready = this.checkRoad();
			while (!ready)
			{
				ready = this.checkRoad();
			}
			this.move();
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", 0);
			mainBox.fireMailBoxUpdated(event);
			}
			else if(this.order.equals("RELEASE")&& e.indexReceiverInMailBoxList == mainBox.fleet.indexOf(this))
			{
				MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "RELEASED", 0);
				mainBox.fireMailBoxUpdated(event);
			}
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

}
