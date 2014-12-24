import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		
		int i, j;
		
		MailBox mainBox = new MailBox();
		Window window = new Window(mainBox);
		mainBox.setWindow(window);
		
		mainBox.FileReader();
		
		//Places declaration and add them into mainBox.reservations
		mainBox.reservations.add(new Place("C", true, new ArrayList<Place>(),378,287));
		mainBox.reservations.add(new Place("I1", true, new ArrayList<Place>(),600,90));
		mainBox.reservations.add(new Place("I2", true, new ArrayList<Place>(),310,17));
		mainBox.reservations.add(new Place("I3", true, new ArrayList<Place>(),103,170));
		mainBox.reservations.add(new Place("I4", true, new ArrayList<Place>(),162,480));
		mainBox.reservations.add(new Place("I5", true, new ArrayList<Place>(),437,550));
		mainBox.reservations.add(new Place("I6", true, new ArrayList<Place>(),660,390));
		mainBox.reservations.add(new Place("O1", true, new ArrayList<Place>(),660,177));
		mainBox.reservations.add(new Place("O2", true, new ArrayList<Place>(),437,17));
		mainBox.reservations.add(new Place("O3", true, new ArrayList<Place>(),162,90));
		mainBox.reservations.add(new Place("O4", true, new ArrayList<Place>(),103,390));
		mainBox.reservations.add(new Place("O5", true, new ArrayList<Place>(),310,550));
		mainBox.reservations.add(new Place("O6", true, new ArrayList<Place>(),600,480));
		mainBox.reservations.add(new Place("R1", true, new ArrayList<Place>(),563,180));
		mainBox.reservations.add(new Place("R2", true, new ArrayList<Place>(),377,90));
		mainBox.reservations.add(new Place("R3", true, new ArrayList<Place>(),192,170));
		mainBox.reservations.add(new Place("R4", true, new ArrayList<Place>(),206,390));
		mainBox.reservations.add(new Place("R5", true, new ArrayList<Place>(),378,483));
		mainBox.reservations.add(new Place("R6", true, new ArrayList<Place>(),563,390));
		
		//Fill in the adjacencyListPlace for each place
		for(i=1; i<7; i++)
		{
			mainBox.findSpecificPlace("C").getAdjacencyPlaceList().add(mainBox.findSpecificPlace("R"+i));
			mainBox.findSpecificPlace("R"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("C"));
		}
		for(i=1; i<7; i++)
		{
			mainBox.findSpecificPlace("I"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("R"+i));
			mainBox.findSpecificPlace("O"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("R"+i));
		}		
		for(i=1; i<7; i++)
		{
			mainBox.findSpecificPlace("R"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("O"+i));
			if(i==1)
			{
				mainBox.findSpecificPlace("R"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("R6"));
				mainBox.findSpecificPlace("R"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("R2"));
			}
			else if(i==6)
			{
				mainBox.findSpecificPlace("R"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("R5"));
				mainBox.findSpecificPlace("R"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("R1"));
			}
			else
			{
				j = i+1;
				mainBox.findSpecificPlace("R"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("R"+j));
				j = i-1;
				mainBox.findSpecificPlace("R"+i).getAdjacencyPlaceList().add(mainBox.findSpecificPlace("R"+j));
			}
		}
		
		//Car declarations and add them to mainBox.fleet
		for (int k=1; k<13; k++)
		{
			mainBox.fleet.add(new Car( k, "P"+(((k-1)%6)+1), 100, 50,mainBox));
		}
		for (Car c : mainBox.fleet)
		{
			mainBox.addMailBoxListener(c);
		}
		
		
		for (Passenger p : mainBox.passengers)
		{
			mainBox.addMailBoxListener(p);
		}
		
		
		Thread affichage = new Thread(window);
		
		
		affichage.start();
		
		
		//mainBox.commandControl.start();
		
		System.out.println("nombre d'events dans tasks de Window : "+window.tasks.size());
		System.out.println();
		for (Car c : mainBox.fleet)
		System.out.println("Les coordonées de "+c.getCarName()+" sont : ("+c.getCoordCarX()+";"+c.getCoordCarY()+") et il est visible ? : "+c.isVisible());
		
		
		//mainBox.commandControl.test();
	}
}
