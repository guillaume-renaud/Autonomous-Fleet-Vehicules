import javax.swing.JFrame;


public class RequestFrame extends JFrame {

			private static final long serialVersionUID = 1L;
			
			
			
			public RequestFrame () {
				super();
				
				this.setTitle("New Request");
				this.setSize(350, 350);
				this.setResizable(false);
				this.setAlwaysOnTop(true);
				this.setLocationRelativeTo(null); // JFrame in the center of the window
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			
		}
