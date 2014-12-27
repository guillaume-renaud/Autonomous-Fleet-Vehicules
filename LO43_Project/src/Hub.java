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
	JPanel container;
	
	public Hub (MailBox MB) {
		super();
		
		mainBox = MB;
		
		RequestFrame rf = new RequestFrame();
		NewRequest newRequest = new NewRequest(rf);
		
		nbCar = new JLabel ("Number of cars reserved : "+this.nbCarOccuped());
		nbPark = new JLabel ("Number of cars parked : "+this.nbCarParked());
		container = new JPanel();
		
		nbCar.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		nbPark.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		container.setBackground(Color.LIGHT_GRAY);
		container.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		container.setLayout(new FlowLayout());
		
		this.add(nbCar);
		this.add(nbPark);
		container.add(newRequest);
		this.add(container);
		
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