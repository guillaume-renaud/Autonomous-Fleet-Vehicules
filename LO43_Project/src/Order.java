// test test test
public class Order 
{
	String typeOrder;
	
	Place enrollPlace;
	Place beginingMission;
	Place endingMission;
	Request mission;
	
	
	public Order(String s) {
		
		this.typeOrder = s;
		this.enrollPlace=null;
		this.beginingMission =null;
		this.endingMission=null;
		this.mission = null;
	}
	
	public Order (String s, Place enroll) {
		
		this(s);
		this.enrollPlace = enroll;
	}
	
	public Order (String s, Place begin, Place end, Request request) {
		
		this(s);
		this.beginingMission = begin;
		this.endingMission = end;
		this.mission = request;
		
	}

}
