package consultorio.swing.componentes;

import javax.swing.JPanel;
import java.awt.Font;

import javax.swing.JLabel;

public class ContenedorCitas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContenedorImagen contenedorImagen;
	private JLabel lbEdad;
	private JLabel lbTelefono;
	private JLabel lbDireccion;
	private JLabel lbEmail;

	public ContenedorCitas() {
		setLayout(null);
		
		contenedorImagen = new ContenedorImagen();
		contenedorImagen.getEliminar().setBounds(10, 25, 208, 195);
		contenedorImagen.setBounds(9, 11, 244, 231);
		add(contenedorImagen);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(9, 253, 46, 14);
		lblEdad.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		add(lblEdad);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblTelfono.setBounds(9, 278, 56, 14);
		add(lblTelfono);
		
		JLabel lblDirecin = new JLabel("Direci\u00F3n:");
		lblDirecin.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblDirecin.setBounds(9, 303, 46, 14);
		add(lblDirecin);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblEmail.setBounds(9, 328, 46, 14);
		add(lblEmail);
		
		lbEdad = new JLabel("0");
		lbEdad.setBounds(65, 251, 46, 20);
		add(lbEdad);
		
		lbTelefono = new JLabel("");
		lbTelefono.setBounds(65, 276, 148, 20);
		add(lbTelefono);
		
		lbDireccion = new JLabel("");
		lbDireccion.setBounds(65, 301, 148, 20);
		add(lbDireccion);
		
		lbEmail = new JLabel("");
		lbEmail.setBounds(65, 326, 148, 20);
		add(lbEmail);
	}

	public ContenedorImagen getContenedorImagen() {
		return contenedorImagen;
	}
	
	public JLabel getLbEdad() {
		return lbEdad;
	}

	public JLabel getLbTelefono() {
		return lbTelefono;
	}

	public JLabel getLbDireccion() {
		return lbDireccion;
	}

	public JLabel getLbEmail() {
		return lbEmail;
	}
	
}
