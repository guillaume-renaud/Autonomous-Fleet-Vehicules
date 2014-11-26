
public class Car {
	
	String name;
	private Order order;
	private Place position;
	private boolean occuped;
	 
	MailBox mainBox = new MailBox();
	
	
	/* Hamza : We have to put attributes to private and to add methods getter and setters to modify theses attributes.
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
		
		MailBoxEvent e = new MailBoxEvent(this.getClass().getName(), mainBox.vehicules.indexOf(this));
		this.mainBox.fireMailBoxUpdated(e);
	}
	
	public void setPosition(Place p) {
		this.position = p;
	}
	
	public void setOccuped(boolean b) {
		this.occuped = b;
	}
}
