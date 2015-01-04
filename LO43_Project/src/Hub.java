import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
			JLabel nbRequest;
			JPanel container;
			JPanel container2;
			
			public Hub (MailBox MB) {
				super();
				
				mainBox = MB;
				
				RequestFrame rf = new RequestFrame(mainBox);
				NewRequest newRequest = new NewRequest(rf, "New Request");
				NewRequest randomRequest = new NewRequest(rf, "Random Request");
				
				nbCar = new JLabel ("Number of cars reserved : 0");
				nbPark = new JLabel ("Number of cars parked : "+mainBox.fleet.size());
				nbRequest = new JLabel("Number of request on standby : "+mainBox.passengers.size());
				container = new JPanel();
				container2 = new JPanel();
				
				nbCar.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				nbPark.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				nbRequest.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				container.setBackground(Color.LIGHT_GRAY);
				container.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				container.setLayout(new FlowLayout());
				container2.setBackground(Color.LIGHT_GRAY);
				container2.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				container2.setLayout(new FlowLayout());
				
				this.add(nbCar);
				this.add(nbPark);
				this.add(nbRequest);
				container.add(newRequest);
				container2.add(randomRequest);
				this.add(container);
				this.add(container2);
				
				this.setLayout(new GridLayout(6,1));
				this.setBackground(Color.LIGHT_GRAY);
				this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				this.setSize(new Dimension(150,600));
				this.setVisible(true);
			}
			
			public int nbCarOccuped () {
				int nb=0;
				for (Car c : mainBox.fleet)
				{
					if (c.isDisplayed())
						nb++;
				}
				return nb;
			}
			
			public int nbCarParked () {
				int nb=0;
				for (Car c : mainBox.fleet)
				{
					if (!c.isDisplayed())
						nb++;
				}
				return nb;
			}
			public int nbRequestLeft (){
				int nb = 0;
				nb = mainBox.passengers.size()-mainBox.commandControl.treatedRequest;
				return nb;
			}
			
			public void updateLabels() {
				nbCar.setText("Number of cars reserved : "+this.nbCarOccuped());
				nbPark.setText("Number of cars parked : "+this.nbCarParked());
				nbRequest.setText("Number of request on standby : " + this.nbRequestLeft());
			}
		}