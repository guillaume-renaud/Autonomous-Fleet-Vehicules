
public class Main {

	public static void main(String[] args) {
		MailBox mainBox = new MailBox();
		mainBox.FileReader();
		
		mainBox.fleet.add(new Car(0, "P0", 557,100));
		
		
		Window window = new Window(mainBox);
		
		//for(int i=0; i<18;i++)
		///{
		//	int indice = (i%6)+1;
		//	String p = "P"+indice;
		//	mainBox.fleet.add(new Car(i,p,0,0));
		//}
		window.run();
	}
}
