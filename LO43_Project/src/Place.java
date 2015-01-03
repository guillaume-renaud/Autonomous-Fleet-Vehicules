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

	protected String getPlaceName() {
		return placeName;
	}

	protected void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	protected boolean getPlaceIsFree() {
		return placeIsFree;
	}

	protected void setPlaceIsFree(boolean placeIsFree) {
		this.placeIsFree = placeIsFree;
	}

	protected ArrayList<Place> getAdjacencyPlaceList() {
		return adjacencyPlaceList;
	}

	protected void setAdjacencyPlaceList(ArrayList<Place> adjacencyPlaceList) {
		this.adjacencyPlaceList = adjacencyPlaceList;
	}

	protected int getCoordX() {
		return coordX;
	}

	protected void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	protected int getCoordY() {
		return coordY;
	}

	protected void setCoordY(int coordY) {
		this.coordY = coordY;
	}	

	
	
}

