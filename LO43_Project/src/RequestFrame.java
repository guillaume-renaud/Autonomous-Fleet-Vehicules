import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class RequestFrame extends JFrame {

			private static final long serialVersionUID = 1L;
			
			String[] input = {"I1","I2","I3","I4","I5","I6"};
			String[] output = {"O1","O2","O3","O4","O5","O6"};
			
			JPanel customChoice = new JPanel();
			
			JComboBox<String> start ;
			JComboBox<String> end ;
			
			JLabel intro;
			JLabel starting;
			JLabel ending;
			
			JButton ok;
			JButton cancel;
			
			
			
			public RequestFrame () {
				
				super();
				
				customChoice = new JPanel();
				
				start = new JComboBox<String>(input);
				end = new JComboBox<String>(output);
				
				ok = new JButton("Ok");
				cancel = new JButton("Cancel");
				
				
				intro = new JLabel("Please choose the starting and ending point of your wanted trip");
				starting = new JLabel("Starting point");
				ending = new JLabel("Ending point");
				
				customChoice.add(intro);
			    customChoice.add(starting);
			    customChoice.add(start);
			    customChoice.add(ending);
			    customChoice.add(end);
			    customChoice.add(ok);
			    customChoice.add(cancel);
				customChoice.setBackground(Color.LIGHT_GRAY);
				customChoice.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				
				this.getContentPane().add(customChoice);
				
				this.setTitle("New Request");
				this.setSize(400, 100);
				this.setResizable(false);
				this.setAlwaysOnTop(true);
				this.setLocationRelativeTo(null); // JFrame in the center of the window
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
		}
