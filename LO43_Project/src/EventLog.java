import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class EventLog extends JPanel {
	
	MailBox mainBox;
	
	public EventLog (MailBox MB) {
		super();
		
		mainBox = MB;
		
		JTextArea jl = new JTextArea();
		jl.setText("En fait l� je suis en train d'essayer de bien r�organiser toutes les JPanel, que ce soit le background, le hub ou bien le JPanel des logs dans lequel j'�cris actuellement qui servira s�rement � mettre tous les derniers �vents d�tect�s broooo ! ");
		
		this.add(jl);
		
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.setSize(new Dimension(800,100));
		this.setVisible(true);
	}

}
