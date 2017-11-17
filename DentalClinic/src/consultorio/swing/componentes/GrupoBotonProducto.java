package consultorio.swing.componentes;

import javax.swing.JPanel;  

import consultorio.interfece.AcionesBasicas;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GrupoBotonProducto extends JPanel implements ActionListener , KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AcionesBasicas accionesBasicas;

	private JButton btnNuevo;

	private JButton btnGuardar;

	private JButton btnModificar;

	private JButton btnCancelar;
	public GrupoBotonProducto() {
		setLayout(null);
		
		 btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 15));
		btnNuevo.setBounds(177, 11, 127, 39);
		add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 331, 89, 23);
		add(btnGuardar);
		
		 btnModificar = new JButton("Modificar");
		btnModificar.setBounds(118, 331, 89, 23);
		add(btnModificar);
		
		 btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(225, 331, 89, 23);
		add(btnCancelar);
		
		setupEventManager();
		
	}
	
	public void setAccionesBsicas(AcionesBasicas accionesBasicas) {
		this.accionesBasicas = accionesBasicas;
	}
	
	private void setupEventManager() {
		btnNuevo.addActionListener(this);
		btnGuardar.addActionListener(this);
		btnModificar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnCancelar.addKeyListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Nuevo":
			accionesBasicas.nuevo();
			break;
		case "Modificar":
			accionesBasicas.modificar();
			break;
		case "Eliminar":
			accionesBasicas.eliminar();
			break;
		case "Guardar":
			accionesBasicas.guardar();
			break;
		case "Cancelar":
			accionesBasicas.cancelar();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		accionesBasicas.guardar();
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	

	//////// Unsused ////////
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
