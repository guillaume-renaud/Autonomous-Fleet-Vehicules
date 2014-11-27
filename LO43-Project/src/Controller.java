
public class Controller implements MailBoxListener {

	MailBox mainBox;
	
	String requestInTraitment;
	
	
	// ---> solution : use the pattern Observable , and the class controller must implement interface that listen to every update
	
	
	public Controller (MailBox m) {
		mainBox = m;
	}
	
	//This method will be called when we want to enroll a car
	public void enrollCar(Car c) {
		Order o = new Order("ENROLL");
		c.setOrder(o);
	}
	
	//This method will be called when we want to give a mission with a destination to a car
	public void giveMissionCar() {
	}

	//This method will be called when we want to release a car
	public void releaseCar() {
	}
	
	//This method will be called when we want to park a car in a specific parking
	public void parkCar() {
	}
		
		
	//This method will be called when we want a free car for a client
	public Car findFreeCar () {
		
		for (Car c : mainBox.vehicules)
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
		Car car = mainBox.vehicules.get(e.indexInMailBoxList);
		
		//if (car.getPosition().isAParking())
		//{
			
		//}
	}

	@Override
	public void onMailReceivedByMan(MailBoxEvent e) {
		
	}

	@Override
	public void onMailReceivedByController(MailBoxEvent e) {
		
		
	}
	
	
}
