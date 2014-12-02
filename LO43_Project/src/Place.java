import java.util.ArrayList;


public class Place 
{
	String placeName;
	boolean placeIsFree;
	ArrayList<Place> adjacencyPlaceList;
	
	public Place (String name, boolean free, ArrayList<Place> listPlaces) 
	{
		placeName = name;
		placeIsFree = true;
		adjacencyPlaceList = listPlaces;
	}	
}
