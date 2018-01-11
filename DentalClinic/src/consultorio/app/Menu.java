package consultorio.app;

import java.awt.EventQueue;


import javax.swing.JFrame;
import consultorio.daos.LoginDao;
import consultorio.modelo.Login;
import consultorio.swing.abm.ListaPacientes;
import consultorio.swing.abm.ListaProductos;
import consultorio.swing.abm.ListaProfecionales;
import consultorio.swing.abm.ListaServicios;
import consultorio.swing.config.GestionUsuarios;
import consultorio.swing.movimiento.ListaCitas;
import consultorio.util.Factory;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.util.Date;

public class Menu {

	private JFrame frame;
	private Login log;
	private static Login loginSesion;

	/**
	 * Launch the application.
	 */
	public static void main(final Login l) {

		loginSesion = l;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Factory.setUp();
					Menu window = new Menu();
					window.frame.setVisible(true);
					window.frame.setTitle("ConsuLeaf- " + l.getUsuario().getNombre());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/img/logoLeaf.jpg")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cerrar();
			}
		});
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setBounds(100, 100, 660, 608);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("Consultas");
		btnNewButton.setBorder(UIManager.getBorder("Button.border"));
		toolBar.add(btnNewButton);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnAr = new JMenu("Sistema");
		menuBar.add(mnAr);

		JMenuItem mntmGestiosDeUsuarios = new JMenuItem("Gestion de Usuarios");
		mntmGestiosDeUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		mntmGestiosDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirGestioUsuario();
			}
		});
		mnAr.add(mntmGestiosDeUsuarios);

		JMenuItem mntmReportes = new JMenuItem("Configuracion");
		mnAr.add(mntmReportes);

		JMenu mnRegistros = new JMenu("Registros");
		menuBar.add(mnRegistros);

		JMenuItem mntmPaciente = new JMenuItem("Paciente");
		mntmPaciente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mntmPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirPaciente();
			}
		});
		mnRegistros.add(mntmPaciente);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Profecional");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaProfecionales lProf = new ListaProfecionales();
				lProf.setVisible(true);
				lProf.setupController();
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mnRegistros.add(mntmNewMenuItem);
		
		JMenuItem mntmProfecional = new JMenuItem("Servicio");
		mntmProfecional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaServicios lCit = new ListaServicios();
				lCit.setVisible(true);
				lCit.setupController();
			}
		});
		mntmProfecional.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnRegistros.add(mntmProfecional);
		
		JMenuItem mntmProducto = new JMenuItem("Producto");
		mntmProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaProductos lProdu = new ListaProductos();
				lProdu.setVisible(true);
				lProdu.setupController();
			}
		});
		mnRegistros.add(mntmProducto);
		
		JMenu mnMovimientos = new JMenu("Movimientos");
		menuBar.add(mnMovimientos);
		
		JMenuItem mntmCitas = new JMenuItem("Citas");
		mntmCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaCitas list = new ListaCitas();
				list.setVisible(true);
				list.setupController();
			}
		});
		mnMovimientos.add(mntmCitas);
	}

	public void cerrar() {
		Object[] opciones = { "Aceptar", "Cancelar" };
		int eleccion = JOptionPane.showOptionDialog(frame, "¿En realidad desea cerrar la aplicacion?",
				"Mensaje de Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
				"Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			sesionCloseUpdate();
			System.exit(0);
		} else {
		}
	}

	private void updateLogin() {
		log = new Login();
		log.setLogin_codigo(loginSesion.getLogin_codigo());
		log.setLog_fecha(loginSesion.getLog_fecha());

		Date date = new Date();
		log.setOut_fecha(date);
		log.setUsuario(loginSesion.getUsuario());
	}

	private void sesionCloseUpdate() {
		LoginDao daoLog = new LoginDao();
		updateLogin();
		try {
			daoLog.insertarOModificar(log);
			daoLog.ejecutar();
			System.out.println("APAGADO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void abrirGestioUsuario() {
		if (loginSesion.getUsuario().getPerfil().getId() == 1) {
			GestionUsuarios gestUsu = new GestionUsuarios();
			gestUsu.setVisible(true);
			System.out.println("Abra Gestion de usuarios");
		} else {
			JOptionPane.showMessageDialog(null, "Usuario no autorizado");
		}
	}

	private void abrirPaciente() {
		ListaPacientes paciLis = new ListaPacientes();
		paciLis.setVisible(true);

	}
}
