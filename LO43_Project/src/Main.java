
public class Main {

	public static void main(String[] args) {
		MailBox mainBox = new MailBox();
		mainBox.FileReader();
		
		//test instantiation multiple cars
		mainBox.fleet.add(new Car(1, "P1", 557 ,100));
		//mainBox.fleet.add(new Car(2, "P2", 384 ,100));
		//mainBox.fleet.add(new Car(3, "P3", 234 ,175));
		//mainBox.fleet.add(new Car(4, "P4", 246 ,375));
		//mainBox.fleet.add(new Car(5, "P5", 384 ,471));
		//mainBox.fleet.add(new Car(6, "P6", 534, 385));
		
		
		
		Window window = new Window(mainBox);
		mainBox.addMailBoxListener(window);
		
		
		window.run();
		
		
		window.run();
		
		//mainBox.commandControl.test();
	}
}
