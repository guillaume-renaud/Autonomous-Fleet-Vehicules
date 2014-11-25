
public class Controller implements PassagerListener, CarListener{

	MailBox mainBox;
	
	String requestInTraitment;
	
	// Il faut dès maintenant chercher comment récupérer un changement dans un des attributs de BAL. (listener, ...)
	
	// ---> solution : utiliser le pattern Observable , et la classe controller doit implémenter les interfaces qui écoutent chaque changement
	
	
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
	
	
}
