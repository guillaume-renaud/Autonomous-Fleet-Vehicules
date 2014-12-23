
public class Controller implements MailBoxListener {

	MailBox mainBox;
	Passenger actualClient;
	//String requestInTraitment;
	
	int nbCarInMission = 0;
	
	// ---> solution : use the pattern Observable , and the class controller must implement interface that listen to every update
	
	public void start ()
	{
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "Start");
		System.out.println("On start ! ");
		mainBox.fireMailBoxUpdated(event);
	}
	
	public Controller (MailBox m) {
		mainBox = m;
	}
	
	//This method will be called when we want to enroll a car
	public void enrollCar(Car c, Place start) {
		Order o = new Order("ENROLL", start);
		c.setOrder(o);
		c.setCoordCarX(start.getCoordX());
		c.setCoordCarY(start.getCoordY());
		this.nbCarInMission++;
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "ENROLL", mainBox.fleet.indexOf(c));
		mainBox.fireMailBoxUpdated(event);
	}
	
	//This method will be called when we want to give a mission with a destination to a car
	public void giveMissionCar(Car c, Place start, Place end, Request request) {
		
		Order o = new Order("MISSION", start, end, request);
		c.setOrder(o);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "MISSION", mainBox.fleet.indexOf(c));
		System.out.println("Le controlleur a bien donn� une mission � la voiture "+c.getCarName()+" : la voiture doit aller de "+start.getPlaceName()+" � "+end.getPlaceName());
		
		mainBox.fireMailBoxUpdated(event);
	}

	//This method will be called when we want to release a car
	public void releaseCar(Car c) {
		
		Order o = new Order("RELEASE");
		c.setOrder(o);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "RELEASE", mainBox.fleet.indexOf(c));
		System.out.println("Le controlleur a bien envoy� un RELEASE � la voiture "+c.getCarName());
		mainBox.fireMailBoxUpdated(event);
	}
	
	//This method will be called when we want to park a car in a specific parking
	/* We have to rewrite this function when a Parking will exactly be defined
	 * 
	 *ERROR
	 */
	public void parkCar(Car c, String parking) {
		
		Order o = new Order("PARK");
		c.setOrder(o);
		
		c.setParking(parking);
		c.getPosition().setPlaceIsFree(true);
		c.setPosition(null);
		
		this.nbCarInMission--;
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "PARK", mainBox.fleet.indexOf(c));
		mainBox.fireMailBoxUpdated(event);
	}
	
	public void waitCar (Car c) {
		Order o = new Order("WAIT");
		c.setOrder(o);
		c.setOccuped(false);
		
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "WAIT", mainBox.fleet.indexOf(c));
		mainBox.fireMailBoxUpdated(event);
	}
		
		
	//This method will be called when we want a free car for a client
	public Car findFreeCar (String p) {
		
		Car c = mainBox.findFreeCar(p); 
		
		return c;
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
				this.giveMissionCar(car, mainBox.findSpecificPlace(actualClient.request.start), mainBox.findSpecificPlace(actualClient.request.destination), actualClient.request);
			}
			// Case when the car as finished its mission (arrived)
			else if (car.getPosition()==car.getOrder().endingMission)
			{
				this.releaseCar(car);
			}
			
		}
		else if (action.equals("RELEASED"))
		{
			String parking = "NONE";
			switch (car.getPosition().getPlaceName()){
			case "O1" : parking="P1";
				break;
			case "O2" : parking="P2";
				break;
			case "O3" : parking="P3";
				break;
			case "O4" : parking="P4";
				break;
			case "O5" : parking="P5";
				break;
			case "O6" : parking="P6";
				break;
			}
			
			this.parkCar(car, parking);
		}
		else if (action.equals("PARKED"))
		{
			this.waitCar(car);
		}
	}

	@Override
	public void onMailReceivedByMan(MailBoxEvent e) {
		
		String action = e.updateAction;
		Passenger passenger = mainBox.passengers.get(e.indexUpdaterInMailBoxList);
		this.actualClient = passenger;
		Car car = null;
		if (action.equals("NEW_REQUEST"))
		{
			System.out.println("Requette bien re�ue par le controller");
			
			String beginning = actualClient.request.start;
			
			switch (beginning){
			case "I1" : car = this.findFreeCar("P1");
				break;
			case "I2" : car = this.findFreeCar("P2");
				break;
			case "I3" : car = this.findFreeCar("P3");
				break;
			case "I4" : car = this.findFreeCar("P4");
				break;
			case "I5" : car = this.findFreeCar("P5");
				break;
			case "I6" : car = this.findFreeCar("P6");
				break;
			}
			car.setPosition(null);
			this.enrollCar(car, mainBox.findSpecificPlace(passenger.request.start));
		}
	}

	@Override
	public void onMailReceivedByController(MailBoxEvent e) {
		
		
	}
	
	
}
