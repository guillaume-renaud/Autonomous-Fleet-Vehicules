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
			if(!eventFire.isEmpty())
			{
				
				e = eventFire.getFirst();
				eventFire.removeFirstOccurrence(e);
				switch (e.classNameOfUpdater) 
				{
					case ("Car") : {
						System.out.println("YOLO1");
						//Si c'est un event relatif à un changement de position
						if(e.updateAction.equals("POSITION_CHANGED") || e.updateAction.equals("PARKED"))
						{
							
							//Cas où les deux threads sont totalement libres
							if (window.internalThread.actualManagedEvent==null && window.internalThread2.actualManagedEvent==null)
							{
								System.out.println("YOLO2");
								window.tasks.addLast(e);
								if (window.tasks.size()==2)
								{
									System.out.println("yolo reciproque");
									affichage.interrupt();
									this.sleep(999999);
								}
								else
								{
									//On cherche l'event changement de position suivant dans liste 
									Iterator<MailBoxEvent> iterator = eventFire.iterator();
									if(iterator.hasNext())
									{
										e = iterator.next();
										while (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED") && iterator.hasNext())
										{
											e = iterator.next();
										}
										if (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED"))
										{
											System.out.println("yolo réciproque");
											affichage.interrupt();
											this.sleep(999999);
										}
										
									}
									else
									{
										System.out.println("yolo réciproque");
										affichage.interrupt();
										this.sleep(999999);
									}
								
								}
							}
							//Cas où le thread2 est totalement libre et le thread1 partiellement libre
							else if (window.internalThread.actualManagedEvent!=null && window.internalThread2.actualManagedEvent==null)
							{
								System.out.println("YOLO21");
								//Si la voiture n'est pas déjà déplacée par le thread1
								if(e.indexUpdaterInMailBoxList!=window.internalThread.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									//On ajoute cet event à la liste des tasks
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("yolo réciproque");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										//On cherche l'event changement de position suivant dans liste 
										Iterator<MailBoxEvent> iterator = eventFire.iterator();
										if(iterator.hasNext())
										{
											e = iterator.next();
											while (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED") && iterator.hasNext())
											{
												e = iterator.next();
											}
											if (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED"))
											{
												System.out.println("yolo réciproque");
												affichage.interrupt();
												this.sleep(999999);
											}

											
										}
										else
										{
											System.out.println("yolo réciproque");
											affichage.interrupt();
											this.sleep(999999);
										}
									}
								}
							}
							//Cas où le thread1 est totalement libre et le thread2 partiellement libre
							else if (window.internalThread.actualManagedEvent==null && window.internalThread2.actualManagedEvent!=null)
							{
								System.out.println("YOLO2");
								//Si la voiture n'est pas déjà déplacée par le thread2
								if(e.indexUpdaterInMailBoxList!=window.internalThread2.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									//On ajoute cet event à la liste des tasks
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("yolo réciproque");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										//On cherche l'event changement de position suivant dans liste 
										Iterator<MailBoxEvent> iterator = eventFire.iterator();
										if(iterator.hasNext())
										{
											e = iterator.next();
											while (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED") && iterator.hasNext())
											{
												e = iterator.next();
											}
											if (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED"))
											{
												System.out.println("yolo réciproque");
												affichage.interrupt();
												this.sleep(999999);
											}
										}
										else
										{
											System.out.println("yolo réciproque");
											affichage.interrupt();
											this.sleep(999999);
										}
									}
								}else
								{
									System.out.println("yolo réciproque");
									affichage.interrupt();
									this.sleep(999999);
								}
							}
							//Cas où le thread1 est partiellement libre et le thread2 partiellement libre
							else if (window.internalThread.actualManagedEvent!=null && window.internalThread2.actualManagedEvent!=null)
							{
								System.out.println("YOLO2");
								//Si la voiture n'est pas déjà déplacée ni par le thread1 ni le thread2
								if(e.indexUpdaterInMailBoxList!=window.internalThread.actualManagedEvent.indexUpdaterInMailBoxList && e.indexUpdaterInMailBoxList!=window.internalThread2.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									//On ajoute cet event à la liste des tasks
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("yolo réciproque");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										//On cherche l'event changement de position suivant dans liste 
										Iterator<MailBoxEvent> iterator = eventFire.iterator();
										if(iterator.hasNext())
										{
											e = iterator.next();
											while (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED") && iterator.hasNext())
											{
												e = iterator.next();
											}
											if (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED"))
											{
												System.out.println("yolo réciproque");
												affichage.interrupt();
												this.sleep(999999);
											}
										}
										else
										{
											System.out.println("yolo réciproque");
											affichage.interrupt();
											this.sleep(999999);
										}
										
									}
								}
								if(e.indexUpdaterInMailBoxList==window.internalThread.actualManagedEvent.indexUpdaterInMailBoxList || e.indexUpdaterInMailBoxList==window.internalThread2.actualManagedEvent.indexUpdaterInMailBoxList)
								{
									window.tasks.addLast(e);
									if (window.tasks.size()==2)
									{
										System.out.println("yolo réciproque");
										affichage.interrupt();
										this.sleep(999999);
									}
									else
									{
										//On cherche l'event changement de position suivant dans liste 
										Iterator<MailBoxEvent> iterator = eventFire.iterator();
										if(iterator.hasNext())
										{
											e = iterator.next();
											while (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED") && iterator.hasNext())
											{
												e = iterator.next();
											}
											if (!e.updateAction.equals("POSITION_CHANGED") && !e.updateAction.equals("PARKED"))
											{
												System.out.println("yolo réciproque");
												affichage.interrupt();
												this.sleep(999999);
											}
										}
										else
										{
											System.out.println("yolo réciproque");
											affichage.interrupt();
											this.sleep(999999);
										}
									}
								}
							}
						}
						System.out.println("YOLO3");
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
