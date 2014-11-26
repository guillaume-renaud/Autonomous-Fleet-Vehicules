
public interface MailBoxListener {

	
	
	public void onMailBoxUpdated (MailBoxEvent e);
	
	public void onMailReceivedByCar (MailBoxEvent e);
	
	public void onMailReceivedByMan (MailBoxEvent e);
	
	public void onMailReceivedByController (MailBoxEvent e);
	
	
}
