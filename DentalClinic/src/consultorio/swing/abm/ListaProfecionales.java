package consultorio.swing.abm;

import java.util.List; 
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import consultorio.controlador.ControlProfecional;
import consultorio.daos.ProfecionalDao;
import consultorio.modelo.Doctor;
import consultorio.swing.componentes.HerramientasGenericas;
import consultorio.swing.componentes.ModeloTalblaProfesionales;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaProfecionales extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	String tb = "Buscar";
	private static List<Doctor> lista;
	private static ProfecionalDao dao;
	private static HerramientasGenericas herramientasGenericas;
	private static ModeloTalblaProfesionales mDoctor;
	private Doctor doctor;
	@SuppressWarnings("unused")
	private ControlProfecional controller;
	
	/**
	 * Create the dialog.
	 */
	public ListaProfecionales() {
		setTitle("Lista de profesionales");
		setBounds(100, 100, 932, 621);
		
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 906, 514);
		getContentPane().add(scrollPane);
		
		mDoctor = new ModeloTalblaProfesionales();
		table = new JTable(mDoctor);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2)
					visualizar();
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
		
		herramientasGenericas = new HerramientasGenericas();
		herramientasGenericas.getBtnModificar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		herramientasGenericas.getBtnNuevo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		herramientasGenericas.getTfBuscar().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				filtro();
			}
		});
		herramientasGenericas.getTfBuscar().setEnabled(true);
		herramientasGenericas.setBounds(10, 6, 906, 60);
		getContentPane().add(herramientasGenericas);
		
		recuperarTodo();
		execute();

	}
	
	public void setupController(){
		this.controller = new ControlProfecional(null, this);
	}

	public HerramientasGenericas getHerramientasGenericas() {
		return herramientasGenericas;
	}
	
	public static void  recuperarTodo(){
		dao = new ProfecionalDao();
		lista = dao.recuperarTodo();
		mDoctor.setLista(lista);
		mDoctor.fireTableDataChanged();
	}
	
	private static void filtro(){
		dao = new ProfecionalDao();	
		lista = dao.recuperarPorFiltro(
				herramientasGenericas.getTfBuscar().getText());
		mDoctor.setLista(lista);
		mDoctor.fireTableDataChanged();
	}
	
	private void nuevo() {
		FormularioProfecional formPro = new FormularioProfecional(1, null);
		formPro.setupController();
		formPro.setVisible(true);			
	}
	
	private void modificar() {
		 doctor= new Doctor();
		doctor = lista.get(table.getSelectedRow());
		FormularioProfecional formPro = new FormularioProfecional(2, doctor);
		formPro.setVisible(true);
		formPro.setupController();
	}
	
	private void visualizar() {
		doctor= new Doctor();
		doctor = lista.get(table.getSelectedRow());
		FormularioProfecional formPro = new FormularioProfecional(3, doctor);
		formPro.setVisible(true);
		formPro.setupController();
	}
	
	public JTable getTable() {
		return table;
	}

	public List<Doctor> getLista() {
		return lista;
	}

	private void execute(){
		try {
			setupController();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
		
	
}
