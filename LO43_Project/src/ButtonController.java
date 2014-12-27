import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ButtonController implements ActionListener{

	
	JButton button;
	JFrame jf;
	
	public ButtonController (JFrame jff) {
		jf = jff;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();

		button = (JButton) o;
		
		if(button.getText().equals("Ok"))
		{
			
		}
		else if(button.getText().equals("Cancel"))
		{
			jf.dispose();
		}
		
		
	}
}
