import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Window.NewRequest;


public class ButtonRequestController implements ActionListener {

			NewRequest button;
			
			public ButtonRequestController (NewRequest nw) {
				super();
				button = nw;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(button.rf.isVisible())
					this.button.rf.setVisible(false);
				else
					this.button.rf.setVisible(true);
				
			}
			
			
		}