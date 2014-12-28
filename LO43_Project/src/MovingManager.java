public class MovingManager implements Runnable {
			
			Car actualManagedCar;
			MailBoxEvent actualManagedEvent;
			Window window;
			boolean isRunning = false;
			
			
			public void setManageredObjects (MailBoxEvent e, Window w)
			{
				actualManagedEvent = e;
				window = w;
				actualManagedCar = window.mainBox.fleet.get(e.indexUpdaterInMailBoxList);
				isRunning = false;
			}
			
			public boolean isAlive()
			{
				return isRunning;
			}
			
			public void run() {
				
				isRunning = true;
				
				window.nbFreeThread--;
				
				if (actualManagedEvent.updateAction.equals("POSITION_CHANGED"))
				{
					if (actualManagedEvent.lastPlace==null)
					{
						window.moveToStartingPoint(window.mainBox.findSpecificPlace(actualManagedEvent.newPlace.getPlaceName()),actualManagedCar);
					}
					else
					{
						
						window.moveCarView(window.mainBox.findSpecificPlace(actualManagedEvent.lastPlace.getPlaceName()), window.mainBox.findSpecificPlace(actualManagedEvent.newPlace.getPlaceName()),actualManagedCar);
					}
					
				}
				else if (actualManagedEvent.updateAction.equals("PARKED"))
				{
					window.moveToParking(actualManagedCar);
					actualManagedCar = null;
					
				}
				window.nbFreeThread++;
				isRunning = false;
			}
			
		}
