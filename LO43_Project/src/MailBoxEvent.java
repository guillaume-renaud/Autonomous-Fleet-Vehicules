
public class MailBoxEvent {

	String classNameOfUpdater;
	int indexUpdaterInMailBoxList;
	String updateAction;
	int indexReceiverInMailBoxList;
	
	public MailBoxEvent (String className, int indexList, String action) {
		this.classNameOfUpdater = className;
		this.indexUpdaterInMailBoxList = indexList;
		this.updateAction = action;
		this.indexReceiverInMailBoxList = -1;
	}
	
	public MailBoxEvent (String className, int indexList, String action, int indexList2) {
		this(className, indexList, action);
		this.indexReceiverInMailBoxList = indexList2;
		
	}
}
