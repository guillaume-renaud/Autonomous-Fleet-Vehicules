import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


public class MailBox implements  Runnable{

	LinkedList<Car> fleet; // I have changed it to LinkedList which is a specific queue.
	
	LinkedList<Passenger> passengers;

	ArrayList<MailBoxListener> listeners;
	
	ArrayList<Place> reservations;
	
	Controller commandControl;
	
	LinkedBlockingDeque<MailBoxEvent> eventFire;
	
	Window window;
	
	Thread affichage;
	
	public MailBox() {
		fleet = new LinkedList<Car>();		
		passengers = new LinkedList<Passenger>();
		listeners = new ArrayList<MailBoxListener>();
		reservations = new ArrayList<Place>();
		eventFire = new LinkedBlockingDeque<MailBoxEvent>();
		commandControl = new Controller(this);
		this.addMailBoxListener(commandControl);
		
	}
	
	public void setWindow(Window w, Thread aff)
	{
		window = w;
		affichage = aff;
	}
	
	public void FileReader() {
		String filePath = "request.txt";
		Scanner scanner;
		try {
			scanner = new Scanner(new File(filePath));
			while (scanner.hasNextLine()) {
			    this.passengers.add(new Passenger(scanner.nextLine(),this));
			 }
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}

		
	
	}
	public void addMailBoxListener (MailBoxListener l)
	{
		if (!listeners.contains(l))
		listeners.add(l);
	}
	
	public void removeMailBoxListener (MailBoxListener l)
	{
		if (listeners.contains(l))
			listeners.remove(l);
	}
	
	public void fireMailBoxUpdated (MailBoxEvent e) {
		this.eventFire.addLast(e);
	}

	public Car findFreeCar(String p) {
		
		for (Car c : this.fleet)
		{
			if (!c.getParking().equals("NONE") && c.getPosition()==null && !c.isOccuped() && c.getParking().equals(p))
			{
				return c;
			}
		}
		for (Car c : this.fleet)
		{
			if (!c.getParking().equals("NONE") && c.getPosition()==null && !c.isOccuped())
			{
				c.setParking(p);
				return c;
			}
		}
		return null;
	}
	
	public Place findSpecificPlace (String name)
	{
		for (Place p : this.reservations)
		{
			if (p.getPlaceName().equals(name))
			{
				return p;
			}
		}
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		MailBoxEvent e;
		while(true)
		{
			window.displayer.updateLabels();
			if(!eventFire.isEmpty())
			{
				
				e = eventFire.getFirst();
				eventFire.removeFirstOccurrence(e);
				switch (e.classNameOfUpdater) 
				{
					case ("Car") : {
						System.out.println("--> On rentre dans le case");
						System.out.println("taille de eventFire() : "+eventFire.size());
						System.out.println(e.updateAction);
						//Si c'est un event relatif � un changement de position ou park
						if(e.updateAction.equals("POSITION_CHANGED") || e.updateAction.equals("PARKED"))
						{
							System.out.println("--> C'est un event position ");
							//Cas o� les deux threads sont totalement libres
							if (window.internalThread.actualManagedCar==null && window.internalThread2.actualManagedCar==null)
							{
								System.out.println("--> Les deux threads sont totalement libres : on ajoute la voiture d'index "+e.indexUpdaterInMailBoxList);
								window.tasks.addLast(e);
								if (window.tasks.size()==2)
								{
									System.out.println("--> La liste tasks est de taille maximale 2");
									System.out.println("-->> On lance affichage");
									affichage.interrupt();
									this.sleep(999999);
								}
								else
								{
									System.out.println("--> La liste tasks est de taille 1");
									//On cherche l'event changement de position suivant dans liste.
									if(!eventFire.isEmpty())
									{
										System.out.println("--> Il a au moins un autre event dans eventFire");
										MailBoxEvent event = eventFire.getFirst();
										if (!event.updateAction.equals("POSITION_CHANGED") || e.updateAction.equals("PARKED"))
										{
											System.out.println("--> Mais cet event n'est pas position ou park");
											System.out.println("-->> On lance affichage");
											affichage.interrupt();
											this.sleep(999999);
										}
										else
										{
											System.out.println("--> Et cet event est un changement de position ou park");
										}
										
									}
									else
									{
										System.out.println("--> Il n'y a pas d'autres events dans eventFire");
										System.out.println("-->> On lance affichage");
										affichage.interrupt();
										this.sleep(999999);
									}
								
								}
							}
							//Cas o� le thread2 est totalement libre et le thread1 partiellement libre
							else if (window.internalThread.actualManagedCar!=null && window.internalThread2.actualManagedCar==null)
							{
								System.out.println("--> Le thread2 est totalement libre et le thread1 partiellement libre");
								//Si la voiture n'est pas d�j� d�plac�e par le thread1
								if(e.indexUpdaterInMailBoxList!=window.internalThread.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									System.out.println("--> On n'a pas d�j� d�plac� par thread1, et on ajoute donc la voiture d'index "+e.indexUpdaterInMailBoxList);
									//On ajoute cet event � la liste des tasks
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("--> La liste tasks est de taille maximale 2");
										System.out.println("-->> On lance affichage");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										System.out.println("--> La liste tasks est de taille 1");
										//On cherche l'event changement de position suivant dans liste 
										if(!eventFire.isEmpty())
										{
											System.out.println("--> Il a au moins un autre event dans eventFire");
											MailBoxEvent event = eventFire.getFirst();
											if (!event.updateAction.equals("POSITION_CHANGED") || e.updateAction.equals("PARKED"))
											{
												System.out.println("--> Mais cet event n'est pas position ou park");
												System.out.println("-->> On lance affichage");
												affichage.interrupt();
												this.sleep(999999);
											}
											else
											{
												System.out.println("--> Et cet event est un changement de position ou park");
											}
											
										}
										else
										{
											System.out.println("--> Il n'y a pas d'autres events dans eventFire");
											System.out.println("-->> On lance affichage");
											affichage.interrupt();
											this.sleep(999999);
										}
									}
								}
								if(e.indexUpdaterInMailBoxList==window.internalThread.actualManagedEvent.indexUpdaterInMailBoxList || e.indexUpdaterInMailBoxList==window.internalThread2.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									System.out.println("--> On a d�j� d�plac� par thread1 ou thread2, et on ajoute donc la voiture d'index "+e.indexUpdaterInMailBoxList);
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("--> La liste tasks est de taille maximale 2");
										System.out.println("-->> On lance affichage");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										System.out.println("--> La liste tasks est de taille 1");
										//On cherche l'event changement de position suivant dans liste 
										if(!eventFire.isEmpty())
										{
											System.out.println("--> Il a au moins un autre event dans eventFire");
											MailBoxEvent event = eventFire.getFirst();
											if (!event.updateAction.equals("POSITION_CHANGED") || e.updateAction.equals("PARKED"))
											{
												System.out.println("--> Mais cet event n'est pas position ou park");
												System.out.println("-->> On lance affichage");
												affichage.interrupt();
												this.sleep(999999);
											}
											else
											{
												System.out.println("--> Et cet event est un changement de position ou park");
											}
											
										}
										else
										{
											System.out.println("--> Il n'y a pas d'autres events dans eventFire");
											System.out.println("-->> On lance affichage");
											affichage.interrupt();
											this.sleep(999999);
										}
									}
								}
							}
							//Cas o� le thread1 est totalement libre et le thread2 partiellement libre
							else if (window.internalThread.actualManagedCar==null && window.internalThread2.actualManagedCar!=null)
							{
								System.out.println("--> Le thread1 est totalement libre et le thread2 partiellement libre");
								//Si la voiture n'est pas d�j� d�plac�e par le thread2
								if(e.indexUpdaterInMailBoxList!=window.internalThread2.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									System.out.println("--> On n'a pas d�j� d�plac� par thread2, et on ajoute donc la voiture d'index "+e.indexUpdaterInMailBoxList);
									//On ajoute cet event � la liste des tasks
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("--> La liste tasks est de taille maximale 2");
										System.out.println("-->> On lance affichage");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										System.out.println("--> La liste tasks est de taille 1");
										//On cherche l'event changement de position suivant dans liste 
										if(!eventFire.isEmpty())
										{
											System.out.println("--> Il a au moins un autre event dans eventFire");
											MailBoxEvent event = eventFire.getFirst();
											if (!event.updateAction.equals("POSITION_CHANGED") || e.updateAction.equals("PARKED"))
											{
												System.out.println("--> Mais cet event n'est pas position  ou park");
												System.out.println("-->> On lance affichage");
												affichage.interrupt();
												this.sleep(999999);
											}
											else
											{
												System.out.println("--> Et cet event est un changement de position ou park");
											}
											
										}
										else
										{
											System.out.println("--> Il n'y a pas d'autres events dans eventFire");
											System.out.println("-->> On lance affichage");
											affichage.interrupt();
											this.sleep(999999);
										}
									}
								}
								if(e.indexUpdaterInMailBoxList==window.internalThread.actualManagedEvent.indexUpdaterInMailBoxList || e.indexUpdaterInMailBoxList==window.internalThread2.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									System.out.println("--> On a d�j� d�plac� par thread1 ou thread2, et on ajoute donc la voiture d'index "+e.indexUpdaterInMailBoxList);
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("--> La liste tasks est de taille maximale 2");
										System.out.println("-->> On lance affichage");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										//On cherche l'event changement de position suivant dans liste 
										if(!eventFire.isEmpty())
										{
											System.out.println("--> Il a au moins un autre event dans eventFire");
											MailBoxEvent event = eventFire.getFirst();
											if (!event.updateAction.equals("POSITION_CHANGED") || e.updateAction.equals("PARKED"))
											{
												System.out.println("--> Mais cet event n'est pas position ou park");
												System.out.println("-->> On lance affichage");
												affichage.interrupt();
												this.sleep(999999);
											}
											else
											{
												System.out.println("--> Et cet event est un changement de position ou park");
											}
											
										}
										else
										{
											System.out.println("--> Il n'y a pas d'autres events dans eventFire");
											System.out.println("-->> On lance affichage");
											affichage.interrupt();
											this.sleep(999999);
										}
									}
								}
							}
							//Cas o� le thread1 est partiellement libre et le thread2 partiellement libre
							else if (window.internalThread.actualManagedCar!=null && window.internalThread2.actualManagedCar!=null)
							{
								System.out.println("--> Le thread1 est partiellement libre et le thread2 partiellement libre");
								//Si la voiture n'est pas d�j� d�plac�e ni par le thread1 ni le thread2
								if(e.indexUpdaterInMailBoxList!=window.internalThread.actualManagedEvent.indexUpdaterInMailBoxList && e.indexUpdaterInMailBoxList!=window.internalThread2.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									System.out.println("--> On n'a pas d�j� d�plac� ni par thread1 ni par thread2, et on ajoute donc la voiture d'index "+e.indexUpdaterInMailBoxList);
									//On ajoute cet event � la liste des tasks
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("--> La liste tasks est de taille maximale 2");
										System.out.println("-->> On lance affichage");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										//On cherche l'event changement de position suivant dans liste 
										if(!eventFire.isEmpty())
										{
											System.out.println("--> Il a au moins un autre event dans eventFire");
											MailBoxEvent event = eventFire.getFirst();
											if (!event.updateAction.equals("POSITION_CHANGED") || e.updateAction.equals("PARKED"))
											{
												System.out.println("--> Mais cet event n'est pas position ou park");
												System.out.println("-->> On lance affichage");
												affichage.interrupt();
												this.sleep(999999);
											}
											else
											{
												System.out.println("--> Et cet event est un changement de position ou park");
											}
											
										}
										else
										{
											System.out.println("--> Il n'y a pas d'autres events dans eventFire");
											System.out.println("-->> On lance affichage");
											affichage.interrupt();
											this.sleep(999999);
										}
									}
								}
								else if(e.indexUpdaterInMailBoxList==window.internalThread.actualManagedEvent.indexUpdaterInMailBoxList || e.indexUpdaterInMailBoxList==window.internalThread2.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									System.out.println("--> On a d�j� d�plac� par thread1 ou par thread2, et on ajoute donc la voiture d'index "+e.indexUpdaterInMailBoxList);
									//On ajoute cet event � la liste des tasks
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("--> La liste tasks est de taille maximale 2");
										System.out.println("-->> On lance affichage");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										//On cherche l'event changement de position suivant dans liste 
										if(!eventFire.isEmpty())
										{
											System.out.println("--> Il a au moins un autre event dans eventFire");
											MailBoxEvent event = eventFire.getFirst();
											if (!event.updateAction.equals("POSITION_CHANGED") || e.updateAction.equals("PARKED"))
											{
												System.out.println("--> Mais cet event n'est pas position ou park");
												System.out.println("-->> On lance affichage");
												affichage.interrupt();
												this.sleep(999999);
											}
											else
											{
												System.out.println("--> Et cet event est un changement de position ou park");
											}
											
										}
										else
										{
											System.out.println("--> Il n'y a pas d'autres events dans eventFire");
											System.out.println("-->> On lance affichage");
											affichage.interrupt();
											this.sleep(999999);
										}
									}
								}
							}
						}
						System.out.println("--> On fire maintenant l'event li� a la voiture d'index "+e.indexUpdaterInMailBoxList);
						for (MailBoxListener l : listeners)
						{
							l.onMailReceivedByCar(e);
						}
					} break;
					
					case ("Controller") : {
						for (MailBoxListener l : listeners)
						{
							l.onMailReceivedByController(e);
						}
					} break;
					
					case ("Passenger") : {
						for (MailBoxListener l : listeners)
						{
							l.onMailReceivedByMan(e);
						}
					} break;
					case ("Start") : {
						for (MailBoxListener l : listeners)
						{
							l.onMailReceivedByController(e);
						}
					} break;
				}
			}
		
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
