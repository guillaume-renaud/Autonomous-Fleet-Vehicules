import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

//This class permit the control of the 2 buttons OK and Cancel of the frame displayed for entering new requests. 

public class ButtonController implements ActionListener
{
	JButton button;
	RequestFrame jf;
	MailBox mainBox;
	
	//Constructor of the ButtonController
	public ButtonController (RequestFrame jff, MailBox MB)
	{
		jf = jff;
		mainBox = MB;
	}
	
	@Override
	//This method is called after every action. If we click in OK, we send a new request, otherwise we dispose the frame
	public void actionPerformed(ActionEvent arg0) 
	{
		Object o = arg0.getSource();
		button = (JButton) o;
		
		if(button.getText().equals("Ok"))
		{
			String depart = (String)jf.start.getSelectedItem();
			String arrivee = (String)jf.end.getSelectedItem();
			
			if(depart.getBytes()[1]!=arrivee.getBytes()[1])
			{
				Passenger p = new Passenger((mainBox.passengers.size()+1)+" start:"+depart+" destination:"+arrivee, mainBox);
				mainBox.passengers.addLast(p);
				mainBox.addMailBoxListener(p);
		
				if( mainBox.passengers.indexOf(p) == mainBox.commandControl.treatedRequest && (mainBox.commandControl.nbCarInMission == 1 || mainBox.commandControl.nbCarInMission == 0))
				{
					MailBoxEvent event = new MailBoxEvent (p.getClass().getName(), mainBox.passengers.indexOf(p), "NEW_REQUEST");
					
					System.out.println("Requête envoyée");
					
					mainBox.fireMailBoxUpdated(event);
				}
				jf.dispose();
			}
		}
		else if(button.getText().equals("Cancel"))
		{
			jf.dispose();
		}
		
		
	}
}
