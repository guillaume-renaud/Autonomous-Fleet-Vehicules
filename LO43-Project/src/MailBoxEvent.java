
public class MailBoxEvent {

	String classNameOfUpdater;
	int indexInMailBoxList;
	
	public MailBoxEvent (String className, int indexList) {
		this.classNameOfUpdater = className;
		this.indexInMailBoxList = indexList;
		
	}
	
}
