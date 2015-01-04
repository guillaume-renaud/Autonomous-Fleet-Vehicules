
//This interface will be implemented by every class which is listening to updates in the mailbox

public interface MailBoxListener {

	
	public void onMailReceivedByCar (MailBoxEvent e);
	
	public void onMailReceivedByMan (MailBoxEvent e);
	
	public void onMailReceivedByController (MailBoxEvent e);
	
	
}
