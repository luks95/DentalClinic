package consultorio.swing.componentes;

import javax.swing.JTextField;

import consultorio.util.TfUpper;

import java.awt.Font;

public class TfGeneric extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TfGeneric() {
		setDocument(new TfUpper());
		setFont(new Font("Century Gothic", Font.PLAIN, 11));
		setEnabled(false);
		
	}
		
}
 