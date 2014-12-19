import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;


public class EventLog extends JPanel {
	
	MailBox mainBox;
	JScrollPane scrollPane;
	JLabel jl1 ;
	JLabel jl2 ;
	JLabel jl3;
	JLabel jl4;
	
	
	public EventLog (MailBox MB) {
		super();
		
		mainBox = MB;
		
		jl1 = new JLabel("La voiture 1 est pas mal");
		jl2 = new JLabel("Le controlleur se tape une barre avec la mailbox");
		jl3 = new JLabel("La mailbox remballe le controlleur avec une balgue pourrie");
		jl4 = new JLabel("Le passager s'enfuis en courant, après une tentative de car-jacking");
		
		jl4.setForeground(Color.RED);
		
		
		scrollPane = new JScrollPane(this);
		//scrollPane.setViewportView(this);
		scrollPane.setLayout(new ScrollPaneLayout());
		scrollPane.getViewport().add(jl1);
		scrollPane.getViewport().add(jl2);
		scrollPane.getViewport().add(jl3);
		scrollPane.getViewport().add(jl4);
		
		scrollPane.setSize(new Dimension(800,95));
		scrollPane.setVisible(true);
		
		//this.setBackground(Color.getHSBColor(60, 50, 50));
		//this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		this.add(scrollPane, BorderLayout.CENTER);
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
