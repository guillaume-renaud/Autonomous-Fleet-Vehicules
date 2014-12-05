import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		MailBox mainBox = new MailBox();
		mainBox.FileReader();
		Controller controller = new Controller(mainBox);
		
		JFrame window = new JFrame();
		window.setVisible(true);
		
		// GraphicInterface g = new GraphicInterface();
	}
}
