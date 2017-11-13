package consultorio.swing.movimiento;

import java.util.List;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import consultorio.controlador.ControlCita;
import consultorio.daos.CitaDao;
import consultorio.modelo.Cita;
import consultorio.swing.componentes.HerramientasGenericas;
import consultorio.swing.componentes.ModeloTalblaCitas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaCitas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	String tb = "Buscar";
	private static List<Cita> lista;
	private static CitaDao dao;
	private static HerramientasGenericas herramientasGenericas;
	private static ModeloTalblaCitas mCitas;
	private Cita cita;
	@SuppressWarnings("unused")
	private ControlCita controller;
	
	/**
	 * Create the dialog.
	 */
	public ListaCitas() {
		setTitle("Lista de Citas");
		setBounds(100, 100, 932, 621);
		
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 906, 514);
		getContentPane().add(scrollPane);
		
		mCitas = new ModeloTalblaCitas();
		table = new JTable(mCitas);
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
		this.controller = new ControlCita(null, this);
	}

	public HerramientasGenericas getHerramientasGenericas() {
		return herramientasGenericas;
	}
	
	public static void  recuperarTodo(){
		dao = new CitaDao();
		lista = dao.recuperarTodo();
		mCitas.setLista(lista);
		mCitas.fireTableDataChanged();
	}
	
	private static void filtro(){
		dao = new CitaDao();	
		lista = dao.recuperarPorFiltro(
				herramientasGenericas.getTfBuscar().getText());
		mCitas.setLista(lista);
		mCitas.fireTableDataChanged();
	}
	
	private void nuevo() {
		FormularioCitas formCitas = new FormularioCitas(1, null);
		formCitas.setupController();
		formCitas.setVisible(true);			
	}
	
	private void modificar() {
		 cita= new Cita();
		cita = lista.get(table.getSelectedRow());
		FormularioCitas formCitas = new FormularioCitas(2, cita);
		formCitas.setVisible(true);
		formCitas.setupController();
	}
	
	private void visualizar() {
		cita= new Cita();
		cita = lista.get(table.getSelectedRow());
		FormularioCitas formCitas = new FormularioCitas(3, cita);
		formCitas.setVisible(true);
		formCitas.setupController();
	}
	
	public JTable getTable() {
		return table;
	}

	public List<Cita> getLista() {
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
