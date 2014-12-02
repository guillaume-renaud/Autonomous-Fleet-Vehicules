
public class Car implements MailBoxListener {
	
	String name;
	private Order order;
	private Place position;
	private boolean occuped;
	private String parking;
	 
	MailBox mainBox = new MailBox();
	
	
	/* Cheikh35 : We have to put attributes to private and to add methods getter and setters to modify theses attributes.
	 * Because this permit us to know when the attributes of car are modified, and we can put a method SendEvent, or
	 * Fire, to create and Event when we modify the car, so the MailBox, and it'll permit the Controller to know 
	 * updates*/
	

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
