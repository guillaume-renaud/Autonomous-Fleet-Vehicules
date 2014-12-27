import javax.swing.JButton;


public class NewRequest extends JButton {

			private static final long serialVersionUID = 1L;
			
			RequestFrame rf;
			
			public NewRequest (RequestFrame rf) {
				super("New Request");
				this.rf = rf;
				
				this.addActionListener(new ButtonRequestController());
			}
			
		}
