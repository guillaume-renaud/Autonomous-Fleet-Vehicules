public class MovingManager extends Thread {
			
			Car actualManagedCar;
			MailBoxEvent actualManagedEvent;
			Window window;
			String name;
			boolean isRunning = false;
			
			// to initialize the internalThread
			public MovingManager(String name, Window w)
			{
				this.name = name;
				this.window = w;
			}
			//to set an event to the Thread
			public void setManageredObjects (MailBoxEvent e, Window w, String name)
			{
				actualManagedEvent = e;
				window = w;
				actualManagedCar = window.mainBox.fleet.get(e.indexUpdaterInMailBoxList);
				this.name = name;
			}
			// this is a flag to help window knowing when to stop execution and start Calcul() again.
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
						//First, we check if the action is POSITION_CHANGED or PARKED
						if (actualManagedEvent.updateAction.equals("POSITION_CHANGED"))
						{
							// case of enroll of a car
							if (actualManagedEvent.lastPlace==null)
							{
								actualManagedCar.setDisplayed(true);
								window.moveToStartingPoint(window.mainBox.findSpecificPlace(actualManagedEvent.newPlace.getPlaceName()),actualManagedCar);
							}
							//simple move from a place to another
							else
							{
								window.moveCarView(window.mainBox.findSpecificPlace(actualManagedEvent.lastPlace.getPlaceName()), window.mainBox.findSpecificPlace(actualManagedEvent.newPlace.getPlaceName()),actualManagedCar);
							}

						}
						/*if it's PARKED event then we move the car to the parking and make 
						 * it disappear from the screen.*/
						else if (actualManagedEvent.updateAction.equals("PARKED"))
						{
							window.moveToParking(actualManagedCar);
							actualManagedCar.setDisplayed(false);
							actualManagedCar = null;

						}
						//we change the flag value for next call
						window.nbFreeThread++;
						isRunning = false;
						
							this.sleep(999999);

					}	
					
			}
			// we used sleep() and interrupt() to synchronize the tasks.
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
