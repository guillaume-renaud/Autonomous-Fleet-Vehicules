import java.util.ArrayList;


public class Place 
{
	String placeName;
	boolean placeIsFree;
	ArrayList<Place> adjacencyPlaceList;
	int coordX, coordY;
	
	public Place (String name, boolean free, ArrayList<Place> listPlaces, int x, int y) 
	{
		placeName = name;
		placeIsFree = true;
		adjacencyPlaceList = listPlaces;
		coordX =x;
		coordY = y;
	}	
}
