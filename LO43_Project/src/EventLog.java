import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class EventLog extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	
	
	MailBox mainBox;
	JScrollPane scrollPane;
	JLabel jl1 ;
	JLabel jl2 ;
	JLabel jl3;
	JLabel jl4;
	
	
	public EventLog (MailBox MB) {
		super();
		
		mainBox = MB;
		
		jl1 = new JLabel("Requette envoyée par un passager");
		jl2 = new JLabel("Requette bien reçue par le controller. La voiture car1 a bien reçu ENROLL");
		jl3 = new JLabel("Window a détecté un changement de position pour car1");
		jl4 = new JLabel("La voiture car1 c'est bien ENROLL comme il faut !");
		
		jl4.setForeground(Color.RED);
		
		
		scrollPane = new JScrollPane(this);
		//scrollPane.setViewportView(this);
		//scrollPane.setLayout(new ScrollPaneLayout());
		//scrollPane.getViewport().add(jl1);
		//scrollPane.getViewport().add(jl2);
		//scrollPane.getViewport().add(jl3);
		//scrollPane.getViewport().add(jl4);
		this.add(jl1);
		this.add(jl2);
		this.add(jl3);
		this.add(jl4);
		
		//scrollPane.setSize(new Dimension(800,95));
		//scrollPane.setVisible(true);
		this.setLayout(new GridLayout(4,1));
		this.setBackground(Color.getHSBColor(60, 50, 50));
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		//this.add(scrollPane, BorderLayout.CENTER);
		this.setSize(new Dimension(800,95));
		this.setVisible(true);
		
	}

	public void updateLog (String lastLog) {
<<<<<<< HEAD
		text.append("\n"+lastLog);
=======
		jl1.setText(jl2.getText());
		jl2.setText(jl3.getText());
		jl3.setText(jl4.getText());
		jl4.setText(lastLog+"\n");
>>>>>>> branch 'master' of https://github.com/guillaume-renaud/Autonomous-Fleet-Vehicules.git
		
		
	}
}
