public class MovingManager extends Thread {
			
			Car actualManagedCar;
			MailBoxEvent actualManagedEvent;
			Window window;
			String name;
			boolean isRunning = false;
			
			
			public MovingManager(String name, Window w)
			{
				this.name = name;
				this.window = w;
			}
			
			public void setManageredObjects (MailBoxEvent e, Window w, String name)
			{
				actualManagedEvent = e;
				window = w;
				actualManagedCar = window.mainBox.fleet.get(e.indexUpdaterInMailBoxList);
				this.name = name;
			}
			
			public boolean isRunning()
			{
				return isRunning;
					
			}
			
			public void run() {
				
				this.sleep(999999);
				
					while(true)
					{

						isRunning = true;

						window.nbFreeThread--;

						if (actualManagedEvent.updateAction.equals("POSITION_CHANGED"))
						{
							if (actualManagedEvent.lastPlace==null)
							{
								actualManagedCar.setDisplayed(true);
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
							actualManagedCar.setDisplayed(false);
							actualManagedCar = null;

						}
						window.nbFreeThread++;

						isRunning = false;
						
							this.sleep(999999);

					}	
					
			}
			
			private void sleep(int second)
			{
			try {
			Thread.sleep(second*1000);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			}
			}
		}
