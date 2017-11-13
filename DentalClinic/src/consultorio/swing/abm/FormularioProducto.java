package consultorio.swing.abm;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;

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
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(99, 11, 92, 24);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("Nro. Servicio");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_1.setBounds(10, 11, 68, 24);
		contentPanel.add(label_1);
	}
}
