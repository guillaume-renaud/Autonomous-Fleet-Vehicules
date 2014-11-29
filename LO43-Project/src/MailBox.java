import java.util.ArrayList;


public class MailBox {

	ArrayList<Car> fleet;
	
	ArrayList<Passenger> passengers;

	ArrayList<MailBoxListener> listeners;
	
	ArrayList<Place> reservations;
	
	public MailBox() {
		fleet = new ArrayList<Car>();
		passengers = new ArrayList<Passenger>();
		listeners = new ArrayList<MailBoxListener>();
		reservations = new ArrayList<Place>();
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
		switch (e.classNameOfUpdater) 
		{
			case ("Car") : {
				for (MailBoxListener l : listeners)
				{
					l.onMailReceivedByCar(e);
				}
			} break;
			
			case ("Controller") : {
				for (MailBoxListener l : listeners)
				{
					l.onMailReceivedByController(e);
				}
			} break;
			
			case ("Passenger") : {
				for (MailBoxListener l : listeners)
				{
					l.onMailReceivedByMan(e);
				}
			} break;
		}
	}

}
