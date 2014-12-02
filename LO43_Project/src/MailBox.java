import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class MailBox {

	ArrayList<Car> fleet;// change to queue
	
	ArrayList<Passenger> passengers;

	ArrayList<MailBoxListener> listeners;
	
	ArrayList<Place> reservations;
	
	Place[] requestMap;
	
	public MailBox() {
		fleet = new ArrayList<Car>();
		passengers = new ArrayList<Passenger>();
		listeners = new ArrayList<MailBoxListener>();
		reservations = new ArrayList<Place>();
		requestMap = new Place[] {I1,I2,I3,I4,I5,I6,R1,R2,R3,R4,R5,R6,O1,O2,O3,O4,O5,O6,C};
		Arrays.fill(requestMap, false);
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
			if (c.isInParking() && !c.isOccuped())
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
