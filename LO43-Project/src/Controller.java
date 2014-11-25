
public class Controller implements PassagerListener, CarListener{

	MailBox mainBox;
	
	String requestInTraitment;
	
	// Il faut d�s maintenant chercher comment r�cup�rer un changement dans un des attributs de BAL. (listener, ...)
	
	// ---> solution : utiliser le pattern Observable , et la classe controller doit impl�menter les interfaces qui �coutent chaque changement
	
	
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
