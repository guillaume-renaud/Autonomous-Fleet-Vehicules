
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
	public void move(){
		int i=0;
		while(mainBox.requestMap[i] == position)
			i++;
		if (mainBox.requestMap[i].placeIsFree == false)
			System.out.println("error in requestMap!");
		mainBox.requestMap[i].placeIsFree=false;
		order.mission.requestMap[i]=false;
		for (int j=0;j<19;j++)
		{
			if(order.mission.requestMap[j] == true)
				if (position.adjacencyPlaceList.contains(mainBox.requestMap[j]))
				{
					position = mainBox.requestMap[j];// add fire "new free place"
					j = 19;
				}
		}
		if (position == order.endingMission)
		{
			// fire end mission to listeners
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
