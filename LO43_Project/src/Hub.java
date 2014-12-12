import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hub extends JPanel
		{

			private static final long serialVersionUID = 1L;
			
			MailBox mainBox;
			
			JButton newRequest;
			JLabel nbCar;
			JLabel nbPark;
			
			public Hub (MailBox MB) {
				super();
				
				mainBox = MB;
				
				RequestFrame rf = new RequestFrame();
				NewRequest newRequest = new NewRequest(rf);
				
				nbCar = new JLabel ("Number of cars reserved : "+this.nbCarOccuped());
				nbPark = new JLabel ("Number of cars parked : "+this.nbCarParked());
				
				this.add(nbCar);
				this.add(nbPark);
				
				this.add(newRequest);
				
				this.setBackground(Color.getHSBColor(10, 0, 50));
				this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				this.setSize(800, 100);
				this.setVisible(true);
			}
			
			public int nbCarOccuped () {
				int nb=0;
				for (Car c : mainBox.fleet)
				{
					if (c.isOccuped())
						nb++;
				}
				return nb;
			}
			
			public int nbCarParked () {
				int nb=0;
				for (Car c : mainBox.fleet)
				{
					if (!c.isOccuped())
						nb++;
				}
				return nb;
			}
		}