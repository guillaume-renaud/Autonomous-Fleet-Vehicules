public class MovingManager extends Thread {
			
			Car actualManagedCar;
			MailBoxEvent actualManagedEvent;
			Window window;
			String name;
			
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
			
			/*public boolean isAlive()
			{
				if (name.equals("internalThread"))
				{
					return window.isRunningThread1;
				}
				else
				{
					return window.isRunningThread2;
				}
				
					
			}*/
			
			public void run() {
				
				while(true)
				{
					if (actualManagedEvent!=null)
					{
						if (name.equals("internalThread"))
						{
							window.isRunningThread1 = true;
						}
						else if (name.equals("internalThread2"))
						{
							window.isRunningThread2 = true;
						}

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

						if (name.equals("internalThread"))
						{
							window.isRunningThread1 = false;
						}
						else if (name.equals("internalThread2"))
						{
							window.isRunningThread2 = false;
						}

					}
					this.suspend();

				}


			}
			
		}
