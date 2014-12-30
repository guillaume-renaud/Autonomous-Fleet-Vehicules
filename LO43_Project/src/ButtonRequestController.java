import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ButtonRequestController implements ActionListener {

			NewRequest button;
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object o = arg0.getSource();

				button = (NewRequest) o;
				
				if (button.getText().equals("New Request"))
				{
					if(button.rf.isVisible())
						this.button.rf.setVisible(false);
					else
						this.button.rf.setVisible(true);
				}
				else if (button.getText().equals("Random Request"))
				{
					
				}
				
				
				
				
				
			}
			
			
		}