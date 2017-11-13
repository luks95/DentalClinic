package consultorio.swing.abm;

import java.util.List;  
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import consultorio.controlador.ControlServicio;
import consultorio.daos.ServicioDao;
import consultorio.modelo.Servicio;
import consultorio.swing.componentes.HerramientasGenericas;
import consultorio.swing.componentes.ModeloTalblaServicios;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaServicios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	String tb = "Buscar";
	private static List<Servicio> lista;
	private static ServicioDao dao;
	private static HerramientasGenericas herramientasGenericas;
	private static ModeloTalblaServicios mServicios;
	private Servicio servicio;
	@SuppressWarnings("unused")
	private ControlServicio controller;
	private FormularioServicio formPro;
	
	/**
	 * Create the dialog.
	 */
	public ListaServicios() {
		setTitle("Lista de servicios");
		setBounds(100, 100, 932, 621);
		
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 906, 514);
		getContentPane().add(scrollPane);
		
		mServicios = new ModeloTalblaServicios();
		table = new JTable(mServicios);
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
		this.controller = new ControlServicio(null, this);
	}

	public HerramientasGenericas getHerramientasGenericas() {
		return herramientasGenericas;
	}
	
	public static void  recuperarTodo(){
		dao = new ServicioDao();
		lista = dao.recuperarTodo();
		mServicios.setLista(lista);
		mServicios.fireTableDataChanged();
	}
	
	private static void filtro(){
		dao = new ServicioDao();	
		lista = dao.recuperarPorFiltro(
				herramientasGenericas.getTfBuscar().getText());
		mServicios.setLista(lista);
		mServicios.fireTableDataChanged();
	}
	
	private void nuevo() {
		formPro = new FormularioServicio(1, null);
		formPro.setupController();
		formPro.setVisible(true);			
	}
	
	private void modificar() {
		 servicio= new Servicio();
		servicio = lista.get(table.getSelectedRow());
		formPro = new FormularioServicio(2, servicio);
		formPro.setVisible(true);
		formPro.setupController();
	}
	
	private void visualizar() {
		servicio= new Servicio();
		servicio = lista.get(table.getSelectedRow());
		formPro = new FormularioServicio(3, servicio);
		formPro.setVisible(true);
		formPro.setupController();
	}
	
	public JTable getTable() {
		return table;
	}

	public List<Servicio> getLista() {
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
