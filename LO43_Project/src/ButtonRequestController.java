import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//This class permit the control of button presents in the hub, to add a new or a random requests

public class ButtonRequestController implements ActionListener 
{
	NewRequest button;
			
	@Override
	//This method is called after every action. If we click on New Request, we open a frame for choosing expected places, otherwise we create automatically a request.
	public void actionPerformed(ActionEvent arg0) 
	{
		Object o = arg0.getSource();
		button = (NewRequest) o;
		
		if (button.getText().equals("New Request"))
		{
			if(button.rf.isVisible())
				this.button.rf.setVisible(false);
			else
				this.button.rf.setVisible(true);
		}
		else if (button.getText().equals("Random Request"))
		{
			int depart;
			Random rand = new Random();
			int arrivee =0;
			 
			depart = rand.nextInt(6 - 1 + 1) + 1;
			
			while(arrivee==0 || arrivee==depart)
				arrivee= rand.nextInt(6 - 1 + 1) + 1;
			
			Passenger p = new Passenger((button.rf.mainBox.passengers.size()+1)+" start:I"+depart+" destination:O"+arrivee, button.rf.mainBox);
			button.rf.mainBox.passengers.addLast(p);
			button.rf.mainBox.addMailBoxListener(p);
			
			if( button.rf.mainBox.passengers.indexOf(p) == button.rf.mainBox.commandControl.treatedRequest && ((button.rf.mainBox.commandControl.nbCarInMission == 0) || (button.rf.mainBox.commandControl.nbCarInMission == 1)))
			{
				MailBoxEvent event = new MailBoxEvent (p.getClass().getName(), button.rf.mainBox.passengers.indexOf(p), "NEW_REQUEST");
				
				System.out.println("Requ�te envoy�e");
				
				button.rf.mainBox.fireMailBoxUpdated(event);
			}
		}
	}
}