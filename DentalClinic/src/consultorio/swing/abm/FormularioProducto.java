package consultorio.swing.abm;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;

import consultorio.controlador.ControlProducto;
import consultorio.modelo.Producto;
import consultorio.swing.componentes.TfGeneric;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import consultorio.swing.componentes.GrupoBotonProducto;

public class FormularioProducto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblCantidad;

	@SuppressWarnings("unused")
	private ControlProducto controller;
	private int vPr;
	private Producto produRecibido;
	private TfGeneric tfCaracteris;
	private TfGeneric tfNombre;
	private TfGeneric tfCosto;
	private TfGeneric tfPorcentaje;
	private JLabel lblCodigo;
	private GrupoBotonProducto grupoBotonProducto;
	
	
	/**
	 * Create the dialog.
	 *
	 */
	public FormularioProducto(int v, Producto p) {
		setTitle("Formulario Producto");
		this.vPr = v;
		this.produRecibido = p;
		
		
		setBounds(100, 100, 348, 409);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		
		grupoBotonProducto = new GrupoBotonProducto();
		grupoBotonProducto.getBtnCancelar().setEnabled(false);
		grupoBotonProducto.getBtnModificar().setEnabled(false);
		grupoBotonProducto.getBtnGuardar().setEnabled(false);
		grupoBotonProducto.setBounds(10, 11, 322, 358);
		contentPanel.add(grupoBotonProducto);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 244, 302, 76);
		grupoBotonProducto.add(panel);
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Info del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		
		JLabel lblCant = new JLabel(" Cantidad");
		lblCant.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		lblCant.setBounds(10, 29, 100, 20);
		panel.add(lblCant);
		
		lblCantidad = new JLabel("");
		lblCantidad.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblCantidad.setBounds(120, 35, 85, 14);
		panel.add(lblCantidad);
		
		JLabel label = new JLabel("C\u00F3digo");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label.setBounds(10, 11, 68, 24);
		grupoBotonProducto.add(label);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_1.setBounds(10, 60, 68, 24);
		grupoBotonProducto.add(label_1);
		
		JLabel label_2 = new JLabel("Caracteristicas");
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_2.setBounds(10, 109, 80, 24);
		grupoBotonProducto.add(label_2);
		
		JLabel label_3 = new JLabel("Costo Unitario");
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_3.setBounds(10, 158, 85, 24);
		grupoBotonProducto.add(label_3);
		
		tfCaracteris = new TfGeneric();
		tfCaracteris.setBounds(99, 111, 205, 22);
		grupoBotonProducto.add(tfCaracteris);
		
		tfNombre = new TfGeneric();
		tfNombre.setBounds(99, 62, 113, 22);
		grupoBotonProducto.add(tfNombre);
		
		tfCosto = new TfGeneric();
		tfCosto.setBounds(99, 160, 113, 22);
		grupoBotonProducto.add(tfCosto);
		
		JLabel label_4 = new JLabel("Porcentaje");
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_4.setBounds(10, 207, 85, 24);
		grupoBotonProducto.add(label_4);
		
		tfPorcentaje = new TfGeneric();
		tfPorcentaje.setBounds(99, 209, 68, 22);
		grupoBotonProducto.add(tfPorcentaje);
		
		lblCodigo = new JLabel("");
		lblCodigo.setBounds(99, 11, 68, 24);
		grupoBotonProducto.add(lblCodigo);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		execute();
	}

	public void setupController(){
		this.controller = new ControlProducto(this, null);
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public TfGeneric getTfProcentaje() {
		return tfPorcentaje;
	}

	public TfGeneric getTfCosto() {
		return tfCosto;
	}

	public TfGeneric getTfCaracteristicas() {
		return tfCaracteris;
	}

	public TfGeneric getTfNombre() {
		return tfNombre;
	}

	public int getvPr() {
		return vPr;
	}

	public Producto getProduRecibido() {
		return produRecibido;
	}
	
	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public GrupoBotonProducto getGrupoBotonProducto() {
		return grupoBotonProducto;
	}

	private void execute(){
		try {
			setupController();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
