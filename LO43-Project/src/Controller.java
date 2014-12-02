
public class Controller implements MailBoxListener {

	MailBox mainBox;
	
	//String requestInTraitment;
	
	int nbCarInMission = 0;
	
	// ---> solution : use the pattern Observable , and the class controller must implement interface that listen to every update
	
	
	public Controller (MailBox m) {
		mainBox = m;
	}
	
	//This method will be called when we want to enroll a car
	public void enrollCar(Car c, Place start) {
		
		Order o = new Order("ENROLL", start);
		c.setOrder(o);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "ENROLL", mainBox.fleet.indexOf(c));
		mainBox.fireMailBoxUpdated(event);
	}
	
	//This method will be called when we want to give a mission with a destination to a car
	public void giveMissionCar(Car c, Place start, Place end, Request request) {
		
		Order o = new Order("MISSION", start, end, request);
		c.setOrder(o);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "MISSION", mainBox.fleet.indexOf(c));
		mainBox.fireMailBoxUpdated(event);
	}

	//This method will be called when we want to release a car
	public void releaseCar(Car c) {
		
		Order o = new Order("RELEASE");
		c.setOrder(o);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "RELEASE", mainBox.fleet.indexOf(c));
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
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "PARK", mainBox.fleet.indexOf(c));
		mainBox.fireMailBoxUpdated(event);
	}
		
		
	//This method will be called when we want a free car for a client
	public Car findFreeCar () {
		
		Car c = mainBox.findFreeCar();
		
		return c;
	}

	@Override
	public void onMailBoxUpdated(MailBoxEvent e) {
		
	}

	@Override
	public void onMailReceivedByCar(MailBoxEvent e) {
		Car car = mainBox.fleet.get(e.indexUpdaterInMailBoxList);
		String action = e.updateAction;
		
		if (action.equals("POSITION_CHANGED"))
		{
			//Case when the car arrived to the starting point of the mission
			if (car.getPosition()==car.getOrder().enrollPlace)
			{
				
			}
			// Case when the car as finished its mission (arrived)
			else if (car.getPosition()==car.getOrder().endingMission)
			{
				
			}
			
		}
		else if (action.equals(""))
		{
			
		}
	}

	@Override
	public void onMailReceivedByMan(MailBoxEvent e) {
		String action = e.updateAction;
		Passenger passenger = mainBox.passengers.get(e.indexUpdaterInMailBoxList);
		
		
		if (action.equals("NEW_REQUEST"))
		{
			Car car = this.findFreeCar();
			this.enrollCar(car, mainBox.findSpecificPlace(passenger.request.start));
		}
	}

	@Override
	public void onMailReceivedByController(MailBoxEvent e) {
		
		
	}
	
	
}
