import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class MailBox {

	LinkedList<Car> fleet; // I have changed it to LinkedList which is a specific queue.
	
	LinkedList<Passenger> passengers;

	ArrayList<MailBoxListener> listeners;
	
	ArrayList<Place> reservations;
	
	Controller commandControl;
	
	public MailBox() {
		fleet = new LinkedList<Car>();
		passengers = new LinkedList<Passenger>();
		listeners = new ArrayList<MailBoxListener>();
		reservations = new ArrayList<Place>();
		commandControl = new Controller(this);
	}
	
	public void FileReader() {
		String filePath = "request.txt";
		Scanner scanner;
		try {
			scanner = new Scanner(new File(filePath));
			while (scanner.hasNextLine()) {
			    this.passengers.add(new Passenger(scanner.nextLine()));
			 }
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}

		
	
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

	public Car findFreeCar() {
		
		for (Car c : this.fleet)
		{
			if (!c.getParking().equals("NONE") && c.getPosition()==null && !c.isOccuped())
			{
				return c;
			}
		}
		return null;
	}
	
	public Place findSpecificPlace (String name)
	{
		for (Place p : this.reservations)
		{
			if (p.placeName.equals(name))
			{
				return p;
			}
		}
		return null;
	}

}
