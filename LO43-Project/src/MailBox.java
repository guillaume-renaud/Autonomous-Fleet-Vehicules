import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class MailBox {

	ArrayList<Car> fleet;
	ArrayList<Passenger> passengers;

	ArrayList<MailBoxListener> listeners;
	
	
	public MailBox() {
		fleet = new ArrayList<Car>();
		passengers = new ArrayList<Passenger>();
		listeners = new ArrayList<MailBoxListener>();
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

}
