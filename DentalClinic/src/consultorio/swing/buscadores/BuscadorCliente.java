package consultorio.swing.buscadores;


import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import consultorio.daos.PacienteDao;
import consultorio.interfece.InterfacePaciente;
import consultorio.modelo.Paciente;
import consultorio.swing.abm.FormularioPacientes;
import consultorio.swing.componentes.ModeloTalblaPacientes;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscadorCliente extends JDialog {
	////////////////Atributos
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldBuscarCli;
	private JTable table;
	private ModeloTalblaPacientes mPacientes;
	private InterfacePaciente interfacePaciente;
	private List<Paciente> lista;
	
	///////////////Metodo Constructor/////////////////////////////
	public BuscadorCliente() {
		getContentPane().setBackground(new Color(220, 220, 220));
		setTitle("Buscar Paciente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		setModal(true);
		setLocationRelativeTo(null);
		
		textFieldBuscarCli = new JTextField();
		textFieldBuscarCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				recuperarPaciente();
			}
		});
		textFieldBuscarCli.setBounds(87, 11, 207, 26);
		getContentPane().add(textFieldBuscarCli);
		textFieldBuscarCli.setColumns(10);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBuscar.setBounds(10, 14, 67, 14);
		getContentPane().add(lblBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 414, 199);
		getContentPane().add(scrollPane);
		
		mPacientes=new ModeloTalblaPacientes();
		table = new JTable(mPacientes);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					seleccionarPacientes();
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Nuevo");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormularioPacientes form = new FormularioPacientes(1 ,null);
				form.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setToolTipText("Nuevo");
		btnNewButton.setBounds(304, 11, 100, 26);
		getContentPane().add(btnNewButton);

		
		recuperarPaciente();
	}
	public void recuperarPaciente(){
		PacienteDao dao = new PacienteDao();
		lista = dao.recuperarPorFiltro(textFieldBuscarCli.getText());
		mPacientes.setLista(lista);
		mPacientes.fireTableDataChanged();
	}
	private void seleccionarPacientes() {
		Paciente a = lista.get(table.getSelectedRow());
		interfacePaciente.setPacientes(a);
		dispose();
	}
	public void setInterfacePacientes(InterfacePaciente interfacePaciente) {
		this.interfacePaciente = interfacePaciente;
	}
}
