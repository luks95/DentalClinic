package consultorio.swing.componentes;

import javax.swing.JPanel; 

import consultorio.interfece.AcionesBasicas;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GrupoBotonServicio extends JPanel implements ActionListener , KeyListener {
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
	private JScrollPane scrollPane;
	private JTable table;
	private ModeloTalblaSERProdu mServProdu;
	public GrupoBotonServicio() {
		setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(10, 11, 104, 48);
		btnNuevo.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 15));
		add(btnNuevo);
		
		panel = new JPanel();
		panel.setBounds(10, 340, 355, 72);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(30, 11, 89, 23);
		panel.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(230, 11, 89, 23);
		panel.add(btnCancelar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.setBounds(130, 38, 89, 23);
		panel.add(btnModificar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 355, 261);
		add(scrollPane);
		
		mServProdu = new ModeloTalblaSERProdu();
		table = new JTable(mServProdu);
		scrollPane.setViewportView(table);
		
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

	public JTable getTable() {
		return table;
	}
	
	public ModeloTalblaSERProdu getmServProdu() {
		return mServProdu;
	}

	//////// Unsused ////////
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	
}
