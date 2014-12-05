
public class Car implements MailBoxListener {
	
	String carName;
	private Order order;
	private Place position;
	private boolean occuped;
	private String parking;
	 
	MailBox mainBox = new MailBox();
	
	
	/* Cheikh35 : We have to put attributes to private and to add methods getter and setters to modify theses attributes.
	 * Because this permit us to know when the attributes of car are modified, and we can put a method SendEvent, or
	 * Fire, to create and Event when we modify the car, so the MailBox, and it'll permit the Controller to know 
	 * updates*/
	
	public Car(int i,String p){
		carName	= "car"+i;
		order = new Order("wait");
		position = null;
		occuped = false;
		parking = p;
	}
	
	public boolean checkRoad(){
		boolean ready = true;
		for(int i=0;i<19;i++)
			if(order.mission.requestMap[i])
				for(Place p : mainBox.reservations)
					if (order.mission.requestMapPlaceName[i].equals(p))
						if(!p.placeIsFree)
							ready = false;
		return ready;
	}
	
	public void move() {
		
		position.placeIsFree=true;
		
		for(Place p : position.adjacencyPlaceList)
		{
			for(int i=0;i<19;i++)
			{
				if(order.mission.requestMap[i])
				{
					if(order.mission.requestMapPlaceName[i].equals(p))
					{
						position = p;
						
						MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.fleet.indexOf(this), "POSITION_CHANGED", 0); //I put 0 for the index of controller because it's not in a list.
						mainBox.fireMailBoxUpdated(event);
					}	
				}	
			}	
		}
			
		
	}
	public Order getOrder() {
		return this.order;
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
	
	public void setPosition(Place p) {
		this.position = p;
	}
	
	public void setOccuped(boolean b) {
		this.occuped = b;
	}

	
	@Override
	public void onMailBoxUpdated(MailBoxEvent e) {
		// TODO Auto-generated method stub
		
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
		
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String p) {
		parking = p;
	}

}
