package consultorio.swing.abm;

import java.util.List;   
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import consultorio.controlador.ControlProducto;
import consultorio.daos.ProductoDao;
import consultorio.modelo.Producto;
import consultorio.swing.componentes.HerramientasGenericas;
import consultorio.swing.componentes.ModeloTalblaProductos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaProductos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	String tb = "Buscar";
	private static List<Producto> lista;
	private static ProductoDao dao;
	private static HerramientasGenericas herramientasGenericas;
	private static ModeloTalblaProductos mProductos;
	private Producto producto;
	@SuppressWarnings("unused")
	private ControlProducto controller;
	private FormularioProducto formPro;
	
	/**
	 * Create the dialog.
	 */
	public ListaProductos() {
		setTitle("Lista de servicios");
		setBounds(100, 100, 932, 621);
		
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 906, 514);
		getContentPane().add(scrollPane);
		
		mProductos = new ModeloTalblaProductos();
		table = new JTable(mProductos);
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
		this.controller = new ControlProducto(null, this);
	}

	public HerramientasGenericas getHerramientasGenericas() {
		return herramientasGenericas;
	}
	
	public static void  recuperarTodo(){
		dao = new ProductoDao();
		lista = dao.recuperarTodo();
		mProductos.setLista(lista);
		mProductos.fireTableDataChanged();
	}
	
	private static void filtro(){
		dao = new ProductoDao();	
		lista = dao.recuperarPorFiltro(
				herramientasGenericas.getTfBuscar().getText());
		mProductos.setLista(lista);
		mProductos.fireTableDataChanged();
	}
	
	private void nuevo() {
		formPro = new FormularioProducto(1, null);
		formPro.setupController();
		formPro.setVisible(true);			
	}
	
	private void modificar() {
		 producto= new Producto();
		producto = lista.get(table.getSelectedRow());
		formPro = new FormularioProducto(2, producto);
		formPro.setVisible(true);
		formPro.setupController();
	}
	
	private void visualizar() {
		producto= new Producto();
		producto = lista.get(table.getSelectedRow());
		formPro = new FormularioProducto(3, producto);
		formPro.setVisible(true);
		formPro.setupController();
	}
	
	public JTable getTable() {
		return table;
	}

	public List<Producto> getLista() {
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
