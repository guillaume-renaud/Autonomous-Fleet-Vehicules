import java.util.ArrayList;


public class MailBox {

	ArrayList<Car> vehicules;
	ArrayList<Passenger> passengers;

	ArrayList<MailBoxListener> listeners;
	
	
	public MailBox() {
		vehicules = new ArrayList<Car>();
		passengers = new ArrayList<Passenger>();
		listeners = new ArrayList<MailBoxListener>();
	}
	
	public void addMailBoxListener (MailBoxListener l)
	{
		if (!listeners.contains(l))
		listeners.add(l);
	}
	
	public void removeMailBoxListener (MailBoxListener l)
	{
		if (listeners.contains(l))
			listeners.remove(l);
	}
	
	public void fireMailBoxUpdated (MailBoxEvent e) {
		
	}

}
