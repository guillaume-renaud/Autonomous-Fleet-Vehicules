import javax.swing.JButton;

//Component, button, used to add new and random requests

public class NewRequest extends JButton {

	private static final long serialVersionUID = 1L;

	RequestFrame rf;

	public NewRequest (RequestFrame rf, String text) {
		super(text);
		this.rf = rf;

		this.addActionListener(new ButtonRequestController());
	}

}
