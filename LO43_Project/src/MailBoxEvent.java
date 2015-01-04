
//This class is a personalized event while using the observer pattern

public class MailBoxEvent {

	String classNameOfUpdater;
	int indexUpdaterInMailBoxList;
	String updateAction;
	int indexReceiverInMailBoxList;
	Place lastPlace;
	Place newPlace;

	//First constructor of MailBoxEvent using only the name of the creator, its index in the mailbox, and the action performed
	public MailBoxEvent (String className, int indexList, String action) {
		this.classNameOfUpdater = className;
		this.indexUpdaterInMailBoxList = indexList;
		this.updateAction = action;
		this.indexReceiverInMailBoxList = -1;
		lastPlace = null;
		newPlace = null;
	}

	//Second constructor of MailBoxEvent using the first in addition with the 2 places of the car's move in this case
	public MailBoxEvent (String className, int indexList, String action, Place begin, Place end) {
		this.classNameOfUpdater = className;
		this.indexUpdaterInMailBoxList = indexList;
		this.updateAction = action;
		this.indexReceiverInMailBoxList = -1;
		lastPlace = begin;
		newPlace = end;
	}

	//Third constructor of MailBovEvent using the first in addition with the index of the class concerned by this event
	public MailBoxEvent (String className, int indexList, String action, int indexList2) {
		this(className, indexList, action);
		this.indexReceiverInMailBoxList = indexList2;

	}
}
