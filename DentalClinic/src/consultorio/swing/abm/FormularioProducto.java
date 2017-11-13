package consultorio.swing.abm;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import consultorio.swing.componentes.TfGeneric;

public class FormularioProducto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioProducto dialog = new FormularioProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 *
	 */
	public FormularioProducto() {
		
		setBounds(100, 100, 330, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton button = new JButton("Nuevo");
		button.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 15));
		button.setBounds(200, 11, 104, 48);
		contentPanel.add(button);
		
		JLabel lblCodigo = new JLabel("");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodigo.setBounds(99, 11, 92, 24);
		contentPanel.add(lblCodigo);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblCdigo.setBounds(10, 11, 68, 24);
		contentPanel.add(lblCdigo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNombre.setBounds(10, 67, 68, 24);
		contentPanel.add(lblNombre);
		
		JLabel lblCaracteristicas = new JLabel("Caracteristicas");
		lblCaracteristicas.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblCaracteristicas.setBounds(10, 135, 80, 24);
		contentPanel.add(lblCaracteristicas);
		
		JLabel lblPrecioDeCosto = new JLabel("Precio de Costo");
		lblPrecioDeCosto.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblPrecioDeCosto.setBounds(10, 203, 85, 24);
		contentPanel.add(lblPrecioDeCosto);
		
		TfGeneric tfNombre = new TfGeneric();
		tfNombre.setBounds(99, 70, 113, 22);
		contentPanel.add(tfNombre);
		
		TfGeneric tfCaracteristicas = new TfGeneric();
		tfCaracteristicas.setBounds(99, 137, 205, 22);
		contentPanel.add(tfCaracteristicas);
		
		TfGeneric tfCosto = new TfGeneric();
		tfCosto.setBounds(99, 205, 113, 22);
		contentPanel.add(tfCosto);
	}
}
