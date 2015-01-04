import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//This class is a JPanel in the south of the frame where we write all the actions communicated between the different actors

public class EventLog extends JPanel {


	private static final long serialVersionUID = 1L;


	MailBox mainBox;
	JScrollPane scrollPane;
	JTextArea text;
	boolean vide = true;

	//Constructor of the EventLog, where we instantiate every component 
	public EventLog (MailBox MB) {
		super();

		mainBox = MB;

		text = new JTextArea();
		text.setEditable(false);

		scrollPane = new JScrollPane(text);
		scrollPane.setPreferredSize(new Dimension(800,85));
		this.add(scrollPane);


		this.setLayout(new GridLayout(1,1));
		this.setBackground(Color.getHSBColor(60, 50, 50));
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		this.setSize(new Dimension(800,115));
		this.setVisible(true);

	}

	//This method permit to update the informations displayed in the log panel
	public void updateLog (String lastLog) {
		if(vide)
		{
			text.append(lastLog);
			vide=false;
		}
		else
		{
			text.append("\n"+lastLog);
		}

		scrollPane.setAutoscrolls(true);
		text.setCaretPosition(text.getDocument().getLength()); 

	}
}
