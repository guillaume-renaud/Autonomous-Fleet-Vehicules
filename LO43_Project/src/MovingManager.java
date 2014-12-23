public class MovingManager extends Thread {
			
			Car actualManagedCar;
			MailBoxEvent actualManagedEvent;
			Window window;
			
			
			public void setManageredObjects (MailBoxEvent e, Window w)
			{
				actualManagedEvent = e;
				actualManagedCar = window.mainBox.fleet.get(e.indexUpdaterInMailBoxList);
				window = w;
			}
			
			public void run() {
				
				window.nbFreeThread--;
				if (actualManagedEvent.lastPlace==null)
				{
					window.moveToStartingPoint(window.mainBox.findSpecificPlace(actualManagedCar.getPosition().getPlaceName()),actualManagedCar);
				}
				else
				{
					window.moveCarView(window.mainBox.findSpecificPlace(actualManagedCar.getLastPosition().getPlaceName()), window.mainBox.findSpecificPlace(actualManagedCar.getPosition().getPlaceName()),actualManagedCar);
				}
				window.nbFreeThread++;
			}
			
		}