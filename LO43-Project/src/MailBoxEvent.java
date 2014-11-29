
public class MailBoxEvent {

	String classNameOfUpdater;
	int indexInMailBoxList;
	String updateAction;
	
	public MailBoxEvent (String className, int indexList, String action) {
		this.classNameOfUpdater = className;
		this.indexInMailBoxList = indexList;
		this.updateAction = action;
		
	}
	
}
