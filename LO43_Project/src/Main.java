
public class Main {

	public static void main(String[] args) {
		Window window = new Window();
		window.run();
		MailBox mainBox = new MailBox();
		mainBox.FileReader();
		
		
		for(int i=0; i<18;i++)
		{
			int indice = (i%6)+1;
			String p = "P"+indice;
			mainBox.fleet.add(new Car(i,p,0,0));
		}
	}
}
