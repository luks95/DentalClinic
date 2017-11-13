package consultorio.swing.componentes;

import javax.swing.JPanel;

import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import consultorio.interfece.AcionesBasicas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class HerramientasGenericas extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JTextField tfBuscar;
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnModificar;
	private AcionesBasicas accionesBasicas;
	
	
	public HerramientasGenericas() {
		setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Herramientas", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		setBounds(10, 0, 906, 61);
		setLayout(null);
		
		
		tfBuscar = new JTextField();
		tfBuscar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ComBasicos.PlaceHolderlDefaul(tfBuscar, "Buscar");
		tfBuscar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (tfBuscar.getText().equals("Buscar")) {
					tfBuscar.setText("");
					
					tfBuscar.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfBuscar.getText().isEmpty()) {
					ComBasicos.PlaceHolderlDefaul(tfBuscar, "Buscar");
				}
			}
		});
		tfBuscar.setBounds(10, 24, 237, 26);
		add(tfBuscar);
		tfBuscar.setColumns(10);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(269, 26, 89, 23);
		add(btnNuevo);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(471, 26, 89, 23);
		add(btnEliminar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(370, 26, 89, 23);
		add(btnModificar);
		
		setupEventManager();
	}
	
	public void setAccionesBsicas(AcionesBasicas accionesBasicas) {
		this.accionesBasicas = accionesBasicas;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		
		case "Eliminar":
			accionesBasicas.eliminar();
			break;	
		default:
			break;
		}
	}

	private void setupEventManager() {
		btnNuevo.addActionListener(this);
		btnModificar.addActionListener(this);
		btnEliminar.addActionListener(this);
	}
	
	public JTextField getTfBuscar() {
		return tfBuscar;
	}


	public void setTfBuscar(JTextField tfBuscar) {
		this.tfBuscar = tfBuscar;
	}


	public JButton getBtnNuevo() {
		return btnNuevo;
	}


	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}


	public JButton getBtnEliminar() {
		return btnEliminar;
	}


	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}


	public JButton getBtnModificar() {
		return btnModificar;
	}


	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	
}
