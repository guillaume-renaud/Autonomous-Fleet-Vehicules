
public class Controller implements MailBoxListener {

	MailBox mainBox;
	
	String requestInTraitment;
	
	
	// ---> solution : use the pattern Observable , and the class controller must implement interface that listen to every update
	
	
	public Controller () {
		mainBox = new MailBox();
	}
	
	public void updateListened() {}
	
	public int enrollCar() {
		return 0;}
	
	public int giveMissionCar() {
		return 0;}
	
	public int releaseCar() {
		return 0;}

	@Override
	public void onMailBoxUpdated(MailBoxEnvent e) {
		
	}

	@Override
	public void onMailReceivedByCar(MailBoxEnvent e) {
		
	}

	@Override
	public void onMailReceivedByMan(MailBoxEnvent e) {
		
	}

	@Override
	public void onMailReceivedByController(MailBoxEnvent e) {
		
		
	}
	
	
}
