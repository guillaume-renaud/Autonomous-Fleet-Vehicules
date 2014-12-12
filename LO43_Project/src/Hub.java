import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hub extends JPanel
		{

			private static final long serialVersionUID = 1L;
			
			MailBox mainBox;
			
			JButton newRequest;
			JLabel nbCar;
			
			public Hub (MailBox MB) {
				super();
				
				mainBox = MB;
				
				RequestFrame rf = new RequestFrame();
				NewRequest newRequest = new NewRequest(rf);
				
				nbCar = new JLabel ("Number of cars in the screen : "+mainBox.fleet.size());
				
				this.add(nbCar);
				
				this.add(newRequest);
				
				
				this.setSize(800, 100);
				this.setVisible(true);
			}
		}