
public class Controller implements MailBoxListener {

	MailBox mainBox;
	
	//String requestInTraitment;
	
	
	// ---> solution : use the pattern Observable , and the class controller must implement interface that listen to every update
	
	
	public Controller (MailBox m) {
		mainBox = m;
	}
	
	//This method will be called when we want to enroll a car
	public void enrollCar(Car c, Place start) {
		
		Order o = new Order("ENROLL", start);
		c.setOrder(o);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "ENROLL");
		mainBox.fireMailBoxUpdated(event);
	}
	
	//This method will be called when we want to give a mission with a destination to a car
	public void giveMissionCar(Car c, Place start, Place end) {
		
		Order o = new Order("MISSION", start, end);
		c.setOrder(o);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "MISSION");
		mainBox.fireMailBoxUpdated(event);
	}

	//This method will be called when we want to release a car
	public void releaseCar(Car c) {
		
		Order o = new Order("RELEASE");
		c.setOrder(o);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "RELEASE");
		mainBox.fireMailBoxUpdated(event);
	}
	
	//This method will be called when we want to park a car in a specific parking
	/* We have to rewrite this function when a Parking will exactly be defined
	 * 
	 *ERROR
	 */
	public void parkCar(Car c, Place whereParking) {
		
		Order o = new Order("PARK", whereParking);
		c.setOrder(o);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "ENROLL");
		mainBox.fireMailBoxUpdated(event);
	}
		
		
	//This method will be called when we want a free car for a client
	public Car findFreeCar () {
		
		for (Car c : mainBox.fleet)
		{
			if (c.isOccuped()==false)
			{
				return c;
			}
		}
		
		return null;
		
	}

	@Override
	public void onMailBoxUpdated(MailBoxEvent e) {
		
	}

	@Override
	public void onMailReceivedByCar(MailBoxEvent e) {
		Car car = mainBox.fleet.get(e.indexInMailBoxList);
		String action = e.updateAction;
		
		if (action.equals("POSITION_CHANGED"))
		{
			
		}
		else if (action.equals(""))
		{
			
		}
	}

	@Override
	public void onMailReceivedByMan(MailBoxEvent e) {
		
	}

	@Override
	public void onMailReceivedByController(MailBoxEvent e) {
		
		
	}
	
	
}
