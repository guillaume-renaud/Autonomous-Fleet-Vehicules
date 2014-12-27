 import java.util.ArrayList;


public class Place 
{
	private String placeName;
	private boolean placeIsFree;
	private ArrayList<Place> adjacencyPlaceList;
	private int coordX, coordY;
	
	public Place (String name, boolean free, ArrayList<Place> listPlaces, int x, int y) 
	{ 
		setPlaceName(name);
		setPlaceIsFree(true);
		setAdjacencyPlaceList(listPlaces);
		setCoordX(x);
		setCoordY(y);
	}

	public String getPlaceName() 
	{
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public boolean getPlaceIsFree() {
		return placeIsFree;
	}

	void setPlaceIsFree(boolean placeIsFree) {
		this.placeIsFree = placeIsFree;
	}

	public ArrayList<Place> getAdjacencyPlaceList() {
		return adjacencyPlaceList;
	}

	public void setAdjacencyPlaceList(ArrayList<Place> adjacencyPlaceList) {
		this.adjacencyPlaceList = adjacencyPlaceList;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}	

	
	
}

