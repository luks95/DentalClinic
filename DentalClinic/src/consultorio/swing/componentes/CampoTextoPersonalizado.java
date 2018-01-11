package consultorio.swing.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import consultorio.util.NumberUtil;
import consultorio.util.UpperCaseFilter;

public class CampoTextoPersonalizado extends JTextField{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CampoTextoPersonalizado() {
		setFont(new Font("Tahoma", Font.BOLD, 11));
		setDisabledTextColor(Color.GRAY);
		((AbstractDocument) getDocument()).setDocumentFilter(new UpperCaseFilter());
		setEnabled(false);
	}
	public void recibeEnteros(){
		addKeyListener(new KeyAdapter() {
			
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
				
			}
		});
	}
	public void recibeDecimales(){
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(getText().isEmpty()){
					return;
				}
				if(getText().substring(getText().length()-1).equals(",")){
					return;
				}
				if(getText().substring(getText().length()-1).equals("0")&&getText().contains(",")){
					return;
				}
					String t = getText();
					t = t.replace(".", "");
					t = t.replace(",", ".");
					Double d = Double.parseDouble(t);
					setText(NumberUtil.getNumeroFormateado(d));
				
			}
						
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)&&c!=',') {
					e.consume();
				}
			}
		});
	}
}
