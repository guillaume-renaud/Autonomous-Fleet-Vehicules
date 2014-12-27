import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Request {
	String start;
	String destination;
	boolean[] requestMap;
	String[] requestMapPlaceName;
	
public Request(String begin, String end){
	int index = 0;
	requestMapPlaceName = new String[] {"I1","I2","I3","I4","I5","R1","R2","R3","R4","R5","R6","O1","O2","O3","O4","O5","O6","C"};
	String filePath = "requestMap.txt";
	Scanner sc;
	try {
	sc = new Scanner(new File(filePath));
	start = begin;
	destination = end;
	switch (start){
	case "I1" : 
		switch (destination){
		case "O2" : index = 0;
			break;
		case "O3" : index = 1;
			break;
		case "O4" : index = 2;
			break;
		case "O5" : index = 3;
			break;
		case "O6" : index = 4;
			break;
		default : System.out.println("error!"); 
			break;
		}
		break;
	case "I2" : 
		switch (destination){
		case "O1" : index = 5;
			break;
		case "O3" : index = 6;
			break;
		case "O4" : index = 7;
			break;
		case "O5" : index = 8;
			break;
		case "O6" : index = 9;
			break;
		default : System.out.println("error!"); 
			break;
		}
		break;
	case "I3" : 
		switch (destination){
		case "O1" : index = 10;
			break;
		case "O2" : index = 11;
			break;
		case "O4" : index = 12;
			break;
		case "O5" : index = 13;
			break;
		case "O6" : index = 14;
			break;
		default : System.out.println("error!");
			break;
		}
		break;
	case "I4" : 
		switch (destination){
		case "O1" : index = 15;
			break;
		case "O2" : index = 16;
			break;
		case "O3" : index = 17;
			break;
		case "O5" : index = 18;
			break;
		case "O6" : index = 19;
			break;
		default : System.out.println("error!");
			break;
		}
		break;
	case "I5" : 
		switch (destination){
		case "O1" : index = 20;
			break;
		case "O2" : index = 21;
			break;
		case "O3" : index = 22;
			break;
		case "O4" : index = 23;
			break;
		case "O6" : index = 24;
			break;
		default : System.out.println("error!"); 
			break;
		}
		break;
	case "I6" : 
		switch (destination){
		case "O1" : index = 25;
			break;
		case "O2" : index = 26;
			break;
		case "O3" : index = 27;
			break;
		case "O4" : index = 28;
			break;
		case "O5" : index = 29;
			break;
		default : System.out.println("error!"); 
			break;
		}
		break;
	default : System.out.println("error!"); 
		break;
	}
	for(int i=0;i<=index;i++)
	{
		sc.nextLine();
	}
	sc.useDelimiter(Pattern.compile("[\n \\s ]"));
	requestMap = new boolean[19];
	Arrays.fill(requestMap, false);
	sc.next();
	sc.next();
	for (int i=0; i<19;i++){
		if (sc.next().equals("F"))
			requestMap[i]=false;
		else
			requestMap[i]=true;
	}
	sc.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("error");
		e.printStackTrace();
	}
}
}

