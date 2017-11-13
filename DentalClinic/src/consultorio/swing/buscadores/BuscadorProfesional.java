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
import consultorio.daos.ProfecionalDao;
import consultorio.interfece.InterfaceDoctor;
import consultorio.modelo.Doctor;
import consultorio.swing.abm.FormularioProfecional;
import consultorio.swing.componentes.ModeloTalblaProfesionales;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscadorProfesional extends JDialog {
	////////////////Atributos
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldBuscarDoc;
	private JTable table;
	private ModeloTalblaProfesionales mProfesional;
	private InterfaceDoctor interfaceDoctor;
	private List<Doctor> lista;
	
	///////////////Metodo Constructor/////////////////////////////
	public BuscadorProfesional() {
		getContentPane().setBackground(new Color(220, 220, 220));
		setTitle("Buscar Profesional");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		setLocationRelativeTo(null);
		
		textFieldBuscarDoc = new JTextField();
		textFieldBuscarDoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				recuperarPaciente();
			}
		});
		textFieldBuscarDoc.setBounds(87, 11, 207, 26);
		getContentPane().add(textFieldBuscarDoc);
		textFieldBuscarDoc.setColumns(10);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBuscar.setBounds(10, 14, 67, 14);
		getContentPane().add(lblBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 414, 199);
		getContentPane().add(scrollPane);
		
		mProfesional=new ModeloTalblaProfesionales();
		table = new JTable(mProfesional);
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
				FormularioProfecional form = new FormularioProfecional(1 ,null);
				form.setupController();
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
		ProfecionalDao dao = new ProfecionalDao();
		lista = dao.recuperarPorFiltro(textFieldBuscarDoc.getText());
		mProfesional.setLista(lista);
		mProfesional.fireTableDataChanged();
	}
	private void seleccionarPacientes() {
		Doctor a = lista.get(table.getSelectedRow());
		interfaceDoctor.setDoctor(a);
		dispose();
	}
	public void setInterfaceDoctor(InterfaceDoctor interfaceDoctor) {
		this.interfaceDoctor = interfaceDoctor;
	}
}
