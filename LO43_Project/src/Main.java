import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		MailBox mainBox = new MailBox();
		mainBox.FileReader();
		Controller controller = new Controller(mainBox);
		for(int i=0; i<18;i++)
		{
			int indice = (i%6)+1;
			String p = "P"+indice;
			controller.mainBox.fleet.add(new Car(i,p));
		}
		
		JFrame window = new JFrame();
		window.setVisible(true);
		
		// GraphicInterface g = new GraphicInterface();
	}
}
