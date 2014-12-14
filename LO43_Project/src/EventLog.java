import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EventLog extends JPanel {
	
	MailBox mainBox;
	
	JLabel jl1 ;
	JLabel jl2 ;
	JLabel jl3;
	JLabel jl4;
	
	
	public EventLog (MailBox MB) {
		super();
		
		mainBox = MB;
		
		jl1 = new JLabel("log1\n");
		jl2 = new JLabel("log2\n");
		jl3 = new JLabel("log3\n");
		jl4 = new JLabel("log4\n");
		
		jl4.setForeground(Color.RED);
		
		this.add(jl1);
		this.add(jl2);
		this.add(jl3);
		this.add(jl4);
		
		this.setLayout(new GridLayout(4,1));
		
		
		this.setBackground(Color.getHSBColor(60, 50, 50));
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.setSize(new Dimension(800,95));
		this.setVisible(true);
		
	}

	public void updateLog (String lastLog) {
		jl1.setText(jl2.getText());
		jl2.setText(jl3.getText());
		jl3.setText(jl4.getText());
		jl4.setText(lastLog+"\n");
		
		
	}
}
