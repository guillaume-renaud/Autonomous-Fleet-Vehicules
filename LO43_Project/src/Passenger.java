import java.util.Scanner;
import java.util.regex.Pattern;


public class Passenger implements MailBoxListener {
	
	int passengerNumber;
	Request request;
	boolean occuped;
	MailBox mainBox;
	
	
	public Passenger(String s,MailBox m){
		System.out.println(s);
		mainBox = m;
		Scanner scanner;
		scanner = new Scanner(s);
		scanner.useDelimiter(Pattern.compile("[ : \n]")); // define the delimiter for parsing the line
		passengerNumber = scanner.nextInt();
		scanner.next();
		String begin = scanner.next();
		scanner.next();
		String end = scanner.next();
		request = new Request(begin, end);
		scanner.close();
	}
	
	
	@Override
	public void onMailReceivedByCar(MailBoxEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onMailReceivedByMan(MailBoxEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMailReceivedByController(MailBoxEvent e) {
		// TODO Auto-generated method stub
		String action = e.updateAction;
		if (action.equals("Start") && this.passengerNumber == 1 )
		{
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.passengers.indexOf(this), "NEW_REQUEST");
			System.out.println("Requette envoyée");
			mainBox.fireMailBoxUpdated(event);
		}else if(action.equals("MISSION") && this.passengerNumber == mainBox.commandControl.actualClient.passengerNumber+1){
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.passengers.indexOf(this), "NEW_REQUEST");
			System.out.println("Requette envoyée");
			mainBox.fireMailBoxUpdated(event);
		}else if(action.equals("WAIT") && this.passengerNumber == mainBox.passengers.get(e.indexReceiverInMailBoxList).passengerNumber)
		{
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.passengers.indexOf(this), "NEW_REQUEST");
			System.out.println("Requette envoyée");
			mainBox.fireMailBoxUpdated(event);
			System.out.println("On doit attendre là!");
		}
		
	}
}
