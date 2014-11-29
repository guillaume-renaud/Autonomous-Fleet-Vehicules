// test test test
public class Order {

	String typeOrder;
	
	Place enrollPlace;
	Place beginingMission;
	Place endingMission;
	
	
	public Order(String s) {
		
		this.typeOrder = s;
	}
	
	public Order (String s, Place enroll) {
		
		this(s);
		this.enrollPlace = enroll;
	}
	
	public Order (String s, Place begin, Place end) {
		
		this(s);
		this.beginingMission = begin;
		this.endingMission = end;
		
	}

}
