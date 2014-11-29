// test test test
public class Order {

	String typeOrder;
	
	Place enrollPlace;
	Place beginingMission;
	Place endingMission;
	Place parkPlace;
	
	
	public Order(String s) {
		
		this.typeOrder = s;
		this.enrollPlace=null;
		this.beginingMission =null;
		this.endingMission=null;
		this.parkPlace=null;
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
