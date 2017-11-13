package consultorio.swing.componentes;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ComBasicos<T> {
	public static void limpiarTf(List<JTextField> tf) {
		for (int i = 0; i < tf.size(); i++) {
			tf.get(i).setText("");
		}
	}

	public static void lblLimpiar(List<JLabel> lbl) {
		for (int i = 0; i < lbl.size(); i++) {
			lbl.get(i).setText("");
		}
	}

	public static void PlaceHolderlDefaul(JTextField ph, String t) {
		ph.setText(t);
		ph.setForeground(Color.GRAY);
		ph.setFont(new Font("Century Gothic", Font.PLAIN, 11));
	}
	
	public static void activarTF(List<JTextField> tf, boolean b){
		for (int i = 0; i < tf.size(); i++) {
			tf.get(i).setEnabled(b);
		}
	}
	
}
