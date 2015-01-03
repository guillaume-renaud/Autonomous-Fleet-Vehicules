
public class MailBoxEvent {

	String classNameOfUpdater;
	int indexUpdaterInMailBoxList;
	String updateAction;
	int indexReceiverInMailBoxList;
	Place lastPlace;
	Place newPlace;
	Request mission;
	
	
	public MailBoxEvent (String className, int indexList, String action) {
		this.classNameOfUpdater = className;
		this.indexUpdaterInMailBoxList = indexList;
		this.updateAction = action;
		this.indexReceiverInMailBoxList = -1;
		lastPlace = null;
		newPlace = null;
	}
	
	public MailBoxEvent (String className, int indexList, String action, Place begin, Place end,Request r) {
		this.classNameOfUpdater = className;
		this.mission=r;
		this.indexUpdaterInMailBoxList = indexList;
		this.updateAction = action;
		this.indexReceiverInMailBoxList = -1;
		lastPlace = begin;
		newPlace = end;
	}
	
	public MailBoxEvent (String className, int indexList, String action, int indexList2) {
		this(className, indexList, action);
		this.indexReceiverInMailBoxList = indexList2;
		
	}
}
