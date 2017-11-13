package consultorio.swing.componentes;

import javax.swing.JPanel; 

import consultorio.interfece.AcionesBasicas;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SpringLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GrupoBotonCitas extends JPanel implements ActionListener , KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AcionesBasicas accionesBasicas;
	private JButton btnGuardar;
	private JPanel panel;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnModificar;

	private ContenedorCitas contenedorCitas_;
	public GrupoBotonCitas() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBounds(283, 34, 282, 454);
		
		btnNuevo = new JButton("Nuevo");
		springLayout.putConstraint(SpringLayout.NORTH, btnNuevo, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnNuevo, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNuevo, 45, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnNuevo, 114, SpringLayout.WEST, this);
		btnNuevo.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 15));
		add(btnNuevo);
		
		panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 403, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, this);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(10, 11, 75, 23);
		panel.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(177, 11, 75, 23);
		panel.add(btnCancelar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.setBounds(94, 11, 75, 23);
		panel.add(btnModificar);
		
		contenedorCitas_ = new ContenedorCitas();
		contenedorCitas_.getLbEmail().setText("");
		contenedorCitas_.getLbTelefono().setText("");
		springLayout.putConstraint(SpringLayout.NORTH, contenedorCitas_, 6, SpringLayout.SOUTH, btnNuevo);
		springLayout.putConstraint(SpringLayout.WEST, contenedorCitas_, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, contenedorCitas_, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, contenedorCitas_, 272, SpringLayout.WEST, this);
		add(contenedorCitas_);
		
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
	
	public ContenedorCitas getContenedorCitas_() {
		return contenedorCitas_;
	}


	//////// Unsused ////////
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
