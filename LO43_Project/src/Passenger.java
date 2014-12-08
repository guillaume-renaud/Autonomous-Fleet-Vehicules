import java.util.Scanner;
import java.util.regex.Pattern;


public class Passenger implements MailBoxListener {
	
	int passengerNumber;
	Request request;
	boolean occuped;
	
	
	
	public Passenger(String s){
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
		
	}
}
