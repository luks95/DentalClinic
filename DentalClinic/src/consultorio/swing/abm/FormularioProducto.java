package consultorio.swing.abm;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import consultorio.swing.componentes.TfGeneric;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

public class FormularioProducto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JLabel lblCantidad;
	private JButton btnGuardar;
	private JButton btnModificar;
	private TfGeneric tfProcentaje;
	private TfGeneric tfCosto;
	private TfGeneric tfCaracteristicas;
	private Component tfNombre;
	private JComponent button;

	
	/**
	 * Create the dialog.
	 *
	 */
	public FormularioProducto() {
		
		setBounds(100, 100, 330, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		
		button = new JButton("Nuevo");
		button.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 15));
		button.setBounds(177, 11, 127, 39);
		contentPanel.add(button);
		
		JLabel lblCodigo = new JLabel("");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodigo.setBounds(99, 11, 68, 24);
		contentPanel.add(lblCodigo);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblCdigo.setBounds(10, 11, 68, 24);
		contentPanel.add(lblCdigo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNombre.setBounds(10, 60, 68, 24);
		contentPanel.add(lblNombre);
		
		JLabel lblCaracteristicas = new JLabel("Caracteristicas");
		lblCaracteristicas.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblCaracteristicas.setBounds(10, 109, 80, 24);
		contentPanel.add(lblCaracteristicas);
		
		JLabel lblPrecioDeCosto = new JLabel("Costo Unitario");
		lblPrecioDeCosto.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblPrecioDeCosto.setBounds(10, 158, 85, 24);
		contentPanel.add(lblPrecioDeCosto);
		
		tfNombre = new TfGeneric();
		tfNombre.setBounds(99, 62, 113, 22);
		contentPanel.add(tfNombre);
		
		tfCaracteristicas = new TfGeneric();
		tfCaracteristicas.setBounds(99, 111, 205, 22);
		contentPanel.add(tfCaracteristicas);
		
		tfCosto = new TfGeneric();
		tfCosto.setBounds(99, 160, 113, 22);
		contentPanel.add(tfCosto);
		
		JLabel lblPorcentaje = new JLabel("Porcentaje");
		lblPorcentaje.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblPorcentaje.setBounds(10, 207, 85, 24);
		contentPanel.add(lblPorcentaje);
		
		tfProcentaje = new TfGeneric();
		tfProcentaje.setBounds(99, 209, 68, 22);
		contentPanel.add(tfProcentaje);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Info del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 244, 304, 76);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCant = new JLabel(" Cantidad");
		lblCant.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		lblCant.setBounds(10, 30, 100, 20);
		panel.add(lblCant);
		
		lblCantidad = new JLabel("");
		lblCantidad.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblCantidad.setBounds(120, 35, 85, 14);
		panel.add(lblCantidad);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 331, 89, 23);
		contentPanel.add(btnGuardar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(118, 331, 89, 23);
		contentPanel.add(btnModificar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(225, 331, 89, 23);
		contentPanel.add(btnCancelar);
	}


	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public TfGeneric getTfProcentaje() {
		return tfProcentaje;
	}

	public TfGeneric getTfCosto() {
		return tfCosto;
	}

	public TfGeneric getTfCaracteristicas() {
		return tfCaracteristicas;
	}

	public Component getTfNombre() {
		return tfNombre;
	}

	public JComponent getButton() {
		return button;
	}
	
	
}
