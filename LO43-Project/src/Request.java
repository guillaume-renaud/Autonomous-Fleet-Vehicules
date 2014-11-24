import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Request {
	String start;
	String destination;
	boolean[] requestMap;
	String[] requestMapPlaceName;
	
	public Request(String begin, String end, String map){
		Scanner sc = new Scanner (map);
		sc.useDelimiter(Pattern.compile("[\n]"));
		start = begin;
		destination = end;
		requestMapPlaceName = new String[] {"I1","I2","I3","I4","I5","O1","O2","O3","O4","O5","O6","R1","R2","R3","R4","R5","R6","C"};
		requestMap = new boolean[19];
		Arrays.fill(requestMap, false);
		for (int i=0; i<19;i++){
			if (sc.next() == "F")
				requestMap[i]=false;
			else
				requestMap[i]=true;
		}
		sc.close();
	}
}

