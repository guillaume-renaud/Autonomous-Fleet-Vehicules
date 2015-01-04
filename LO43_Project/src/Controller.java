/*this is one of the most important class of our program. As his name let think it's the manager of
 * the requests of all the passengers. it gives tempo of the execution process.*/
public class Controller implements MailBoxListener {

	MailBox mainBox;
	int nbCarInMission = 0;
	int treatedRequest = 0;
	int debug=0;
	// this method is called at the beginning of the program and starts the request treatment.
	public void start ()
	{
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "Start");
		System.out.println("We start !");
		mainBox.window.log.updateLog("We start !");
		mainBox.fireMailBoxUpdated(event);
	}
	
	public Controller (MailBox m) {
		mainBox = m;
	}
	
	//This method will be called when we want to enroll a car
	public void enrollCar(Car c, Place start, Request request) {
		Order o = new Order("ENROLL", start);
		c.setOrder(o);
		c.getOrder().mission = request;
		c.setCoordCarX(start.getCoordX());
		c.setCoordCarY(start.getCoordY());
		this.nbCarInMission++;
		this.treatedRequest++;
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "ENROLL", mainBox.fleet.indexOf(c));
		mainBox.fireMailBoxUpdated(event);
	}
	
	//This method will be called when we want to give a mission with a destination to a car
	public void giveMissionCar(Car c, Place start, Place end, Request request) {

		Order o = new Order("MISSION", start, end, request);
		c.setOrder(o);
		MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), 0, "MISSION", mainBox.fleet.indexOf(c));
		System.out.println("The controller has in fact given a mission to the "+c.getCarName()+" : it must move from "+start.getPlaceName()+" to "+end.getPlaceName());
		mainBox.window.log.updateLog("The controller has in fact given a mission to the "+c.getCarName()+" : it must move from "+start.getPlaceName()+" to "+end.getPlaceName());
		mainBox.fireMailBoxUpdated(event);
	}


	
	//This method will be called when we want to park a car in a specific parking
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
	//This method will be called when we want a free car for a client
	public Car findFreeCar (String p) {
		
		Car c = mainBox.findFreeCar(p); 
		
		return c;
	}
	

	/*for messages from Car we look the type of update and answer the corresponding
	 * instruction in each cases. */
	@Override
	public void onMailReceivedByCar(MailBoxEvent e) {
		Car car = mainBox.fleet.get(e.indexUpdaterInMailBoxList);
		String action = e.updateAction;
		//if this car changed its position
		if (action.equals("POSITION_CHANGED"))
		{
				
			//Case when the car arrived to the starting point of the mission
			if (car.getPosition()==car.getOrder().enrollPlace)
			{
				this.giveMissionCar(car, mainBox.findSpecificPlace(car.getOrder().mission.start), mainBox.findSpecificPlace(car.getOrder().mission.destination), car.getOrder().mission);
			}
			// Case when the car as finished its mission (arrived)
			else if (car.getPosition()==car.getOrder().endingMission)
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
		//if we received WAIT it means the path is occuped so controller gives the mission again
		}else if (action.equals("WAIT")){
			this.giveMissionCar(car, mainBox.findSpecificPlace(car.getOrder().mission.start), mainBox.findSpecificPlace(car.getOrder().mission.destination), car.getOrder().mission);
		}
	}
	/*in case of update from Passenger Controller answer as follow*/
	@Override
	public void onMailReceivedByMan(MailBoxEvent e) {
		
		String action = e.updateAction;
		//if passenger sent a new request
		if (action.equals("NEW_REQUEST"))
		{
			Passenger passenger = mainBox.passengers.get(e.indexUpdaterInMailBoxList);
			Car car = null;
			System.out.println("Request properly received by the controller");
			mainBox.window.log.updateLog("Request properly received by the controller");
			//We search an available car at the starting point
			String beginning = mainBox.passengers.get(e.indexUpdaterInMailBoxList).request.start;
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
			this.enrollCar(car, mainBox.findSpecificPlace(passenger.request.start), passenger.request);
		}
	}

	@Override
	public void onMailReceivedByController(MailBoxEvent e) {
		
		
	}
	
	
}
