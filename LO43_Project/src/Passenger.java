import java.util.Scanner;
import java.util.regex.Pattern;


public class Passenger implements MailBoxListener {

	int passengerNumber;
	Request request;
	boolean occuped;
	MailBox mainBox;

	/* initialize Passenger by reading the input line from the file request.txt
	 * and creating corresponding request.*/
	public Passenger(String s,MailBox m){
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

	/*on update received from Car Passenger react as follow : */
	@Override
	public void onMailReceivedByCar(MailBoxEvent e) {
		// TODO Auto-generated method stub
		String action = e.updateAction;
		// if action is PARKED then Passenger can send a new request if it's the next one in the list.
		if (action.equals("PARKED") && this.passengerNumber == mainBox.commandControl.treatedRequest+1 )
		{
			System.out.println("");
			mainBox.window.log.updateLog("");
			System.out.println(this.passengerNumber);
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.passengers.indexOf(this), "NEW_REQUEST");
			System.out.println("Request sended");
			mainBox.window.log.updateLog("Request sended");
			mainBox.fireMailBoxUpdated(event);
		}
	}
	@Override
	public void onMailReceivedByMan(MailBoxEvent e) {
		// TODO Auto-generated method stub

	}
	//on update coming from Controller Passenger answer as follow : "
	@Override
	public void onMailReceivedByController(MailBoxEvent e) {
		// TODO Auto-generated method stub
		String action = e.updateAction;
		//if action is Start then the first passenger send his request.
		if (action.equals("Start") && this.passengerNumber == 1 )
		{
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.passengers.indexOf(this), "NEW_REQUEST");
			System.out.println("Request sended");
			mainBox.window.log.updateLog("Request sended");
			mainBox.fireMailBoxUpdated(event);
			/*as we want to deal with two request at same time, the second Passenger in the list
			 * also send his request.*/
		}else if(action.equals("Start") && this.passengerNumber == 2){
			MailBoxEvent event = new MailBoxEvent (this.getClass().getName(), mainBox.passengers.indexOf(this), "NEW_REQUEST");
			System.out.println("Request sended");
			mainBox.window.log.updateLog("Request sended");
			mainBox.fireMailBoxUpdated(event);
		}
	}
}
