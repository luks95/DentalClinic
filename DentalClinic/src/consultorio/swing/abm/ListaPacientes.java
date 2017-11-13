package consultorio.swing.abm;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import consultorio.daos.PacienteDao;
import consultorio.modelo.Paciente;
import consultorio.swing.componentes.ComBasicos;
import consultorio.swing.componentes.ModeloTalblaPacientes;

import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListaPacientes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField tfBuscar;
	private JTable table;
	private static ModeloTalblaPacientes mPacientes;
	private static List<Paciente> lista;
	private static PacienteDao dao;
	private Paciente paciente;
	String tb = "Buscar";

	/**
	 * Create the dialog.
	 */
	public ListaPacientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaPacientes.class.getResource("/img/logoLeaf.jpg")));

		setTitle("Pacientes");
		setBounds(100, 100, 932, 621);

		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Herramientas", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 0, 906, 61);
		getContentPane().add(panel);
		panel.setLayout(null);

		tfBuscar = new JTextField();
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				filtro();
			}
		});
		ComBasicos.PlaceHolderlDefaul(tfBuscar, tb);
		tfBuscar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (tfBuscar.getText().equals(tb)) {
					tfBuscar.setText("");
					tfBuscar.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfBuscar.getText().isEmpty()) {
					ComBasicos.PlaceHolderlDefaul(tfBuscar, tb);
				}
			}
		});
		tfBuscar.setBounds(10, 24, 237, 26);

		panel.add(tfBuscar);
		tfBuscar.setColumns(10);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		btnNuevo.setBounds(257, 26, 89, 23);
		panel.add(btnNuevo);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(356, 26, 89, 23);
		panel.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		btnEliminar.setBounds(455, 26, 89, 23);
		panel.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 906, 514);
		getContentPane().add(scrollPane);

		mPacientes = new ModeloTalblaPacientes();
		table = new JTable(mPacientes);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
					visualizar();
				
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(5);
		scrollPane.setViewportView(table);
		recuperarTodo();
	}

	private void nuevo() {
		FormularioPacientes forPaci = new FormularioPacientes(1, null);
		forPaci.setVisible(true);
		
	}

	public static void recuperarTodo() {
		dao = new PacienteDao();
		lista = dao.recuperarTodo();
		mPacientes.setLista(lista);
		mPacientes.fireTableDataChanged();
	}

	public static void filtro() {
		dao = new PacienteDao();
		lista = dao.recuperarPorFiltro(tfBuscar.getText());
		mPacientes.setLista(lista);
		mPacientes.fireTableDataChanged();

	}

	private void modificar() {
		paciente = new Paciente();
		paciente = lista.get(table.getSelectedRow());
		FormularioPacientes forPaci = new FormularioPacientes(2, paciente);
		forPaci.setVisible(true);
	}

	private void eliminar() {
		paciente = new Paciente();
		paciente = lista.get(table.getSelectedRow());
		dao = new PacienteDao();
		int res = 0;
		res = JOptionPane.showConfirmDialog(null,
				"Desea Eliminar " + paciente.getApellido() + ", " + paciente.getNombre() + "?", "Confirmación", 2, 3);

		if (res == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(paciente);
				dao.ejecutar();
				recuperarTodo();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se puede eliminar " + paciente.getNombre()
						+ " porque ya está siendo utilizádo en una estadía.");
			}
		}
	}

	private void visualizar() {
		paciente = new Paciente();
		paciente = lista.get(table.getSelectedRow());
		FormularioPacientes forPaci = new FormularioPacientes(3, paciente);
		forPaci.setVisible(true);
	}
}
