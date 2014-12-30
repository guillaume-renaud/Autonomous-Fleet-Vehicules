import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ButtonController implements ActionListener{

	
	JButton button;
	RequestFrame jf;
	MailBox mainBox;
	
	public ButtonController (RequestFrame jff, MailBox MB) {
		jf = jff;
		mainBox = MB;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();

		button = (JButton) o;
		
		if(button.getText().equals("Ok"))
		{
			String depart = (String)jf.start.getSelectedItem();
			String arrivee = (String)jf.end.getSelectedItem();
			
			Passenger p = new Passenger((mainBox.passengers.size()+1)+" start:"+depart+" destination:"+arrivee, mainBox);
			
			mainBox.passengers.addLast(p);
			mainBox.addMailBoxListener(p);
		
			MailBoxEvent event = new MailBoxEvent (p.getClass().getName(), mainBox.passengers.indexOf(p), "NEW_REQUEST");
			
			System.out.println("Requette envoyée");
			
			mainBox.fireMailBoxUpdated(event);
			
		}
		else if(button.getText().equals("Cancel"))
		{
			jf.dispose();
		}
		
		
	}
}
