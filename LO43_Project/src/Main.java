
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world");
		MailBox mainBox = new MailBox();
		mainBox.FileReader();
		Controller controller = new Controller(mainBox);
		
		
		GraphicInterface g = new GraphicInterface();
	}
}
