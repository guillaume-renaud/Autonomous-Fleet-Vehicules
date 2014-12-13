import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

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
				
				nbCar.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				nbPark.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				
				this.add(nbCar);
				this.add(nbPark);
				this.add(newRequest);
				
				this.setLayout(new GridLayout(3,1));
				this.setBackground(Color.LIGHT_GRAY);
				this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				this.setSize(new Dimension(100,600));
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