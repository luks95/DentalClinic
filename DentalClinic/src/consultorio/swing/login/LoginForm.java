package consultorio.swing.login;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;

import consultorio.app.Menu;
import consultorio.daos.FuncionalidadDao;
import consultorio.daos.LoginDao;
import consultorio.daos.MonedaDao;
import consultorio.daos.PerfilDao;
import consultorio.daos.UsuarioDao;
import consultorio.internos.GenerarFuncionalidades;
import consultorio.internos.GenerarPerfil;
import consultorio.internos.GenerarUserAdmin;
import consultorio.modelo.Funcionalidad;
import consultorio.modelo.Login;
import consultorio.modelo.Moneda;
import consultorio.modelo.Perfil;
import consultorio.modelo.Usuario;
import consultorio.internos.GenerarMoneda;
import consultorio.util.Factory;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JPasswordField tfPass;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private Usuario usuario;
	private List<Usuario> listaUsuario;
	private Login login;
	@SuppressWarnings("unused")
	private List<Moneda> listaMoneda;
	private List<Perfil> listaPerfil;
	private JButton btnEntrar;
	private int maxCodigo;
	private JLabel lblerrorDeClave;
	private List<Funcionalidad> listaFunciones = new ArrayList<>();

	private LoginDao dao;

	/**
	 * ) Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
		}

		try {
			Factory.setUp();
			LoginForm dialog = new LoginForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public LoginForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/img/logoLeaf.jpg")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		setTitle("ConsuLeaf- Log-in");

		setBounds(100, 100, 450, 217);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		setResizable(false);
		setLocationRelativeTo(null);

		JLabel lblPassworld = new JLabel("Contrase\u00F1a");
		lblPassworld.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblPassworld.setBounds(10, 89, 88, 14);
		contentPanel.add(lblPassworld);

		comboBox = new JComboBox();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPass.requestFocus();
				}
			}
		});
		comboBox.setMaximumRowCount(20);
		comboBox.setBounds(108, 30, 161, 28);
		contentPanel.add(comboBox);

		tfPass = new JPasswordField();
		tfPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnEntrar.requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				lblerrorDeClave.setVisible(false);
			}
		});
		tfPass.setBounds(108, 88, 161, 20);
		contentPanel.add(tfPass);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblUsuario.setBounds(10, 31, 73, 14);
		contentPanel.add(lblUsuario);

		btnEntrar = new JButton("Entrar");

		btnEntrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					accion();
				}
			}
		});
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accion();
			}
		});
		btnEntrar.setBounds(10, 144, 89, 23);
		contentPanel.add(btnEntrar);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.exit(0);
				}
			}
		});
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCerrar.setBounds(129, 144, 89, 23);
		contentPanel.add(btnCerrar);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginForm.class.getResource("/img/logoLeaf.jpg")));
		label.setBounds(288, 11, 146, 153);
		contentPanel.add(label);

		lblerrorDeClave = new JLabel("*Error de clave");
		lblerrorDeClave.setVisible(false);
		lblerrorDeClave.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblerrorDeClave.setForeground(Color.RED);
		lblerrorDeClave.setBounds(108, 108, 88, 14);
		contentPanel.add(lblerrorDeClave);

		traerLista();
	}

	private void traerLista() {
		UsuarioDao usuDao = new UsuarioDao();
		generarDatosPorDefecto();

		listaUsuario = new ArrayList<Usuario>();
		listaUsuario = usuDao.recuperarTodo();
		// System.out.println(listaUsuario.toString());
		cargarComboBox();

		comboBox.setSelectedIndex(listaUsuario.size() - 1);
	}

	@SuppressWarnings("deprecation")
	private boolean verificarPass() {
		boolean b;
		int c = comboBox.getSelectedIndex(), v = listaUsuario.size();
		System.out.println("c " + c + " v " + v);

		usuario = listaUsuario.get(comboBox.getSelectedIndex());
		if ((tfPass.getText().equals(usuario.getPassworld()))) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}

	private void accion() {
		if (verificarPass() == false) {
			tfPass.setText("");
			tfPass.requestFocus();
			System.out.println("No ");
			lblerrorDeClave.setVisible(true);
		} else {
			System.out.println("Si");
			guardarRegistroLogin();
			accederAMenu();
		}
	}

	private void genearDefaultUser() {
		UsuarioDao usuDao = new UsuarioDao();
		listaUsuario = usuDao.recuperarTodo();
		if (listaUsuario.isEmpty()) {
			System.out.println("Generando Usuario por defecto");
			GenerarUserAdmin.generar();
		}
	}

	private void generaPefiles() {
		PerfilDao perfDao = new PerfilDao();
		listaPerfil = perfDao.recuperarTodo();
		if (listaPerfil.isEmpty()) {
			System.out.println("Generando perfil por defecto");
			GenerarPerfil.generar();
		}
	}

	private void generarFunciones() {
		FuncionalidadDao funDao = new FuncionalidadDao();
		listaFunciones = funDao.recuperarTodo();
		if (listaFunciones.isEmpty()) {
			System.out.println("Generando Funciones");
			GenerarFuncionalidades.generalFuncionalidades();
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarComboBox() {
		for (int i = 0; i < listaUsuario.size(); i++) {
			comboBox.addItem(listaUsuario.get(i).getNombre());
		}
	}

	private void generarMoneda() {
		MonedaDao daoMon = new MonedaDao();
		if ((listaMoneda = daoMon.recuperarTodo()).isEmpty()) {
			GenerarMoneda.generarMonedas();
			System.out.println("Generando Moneda por defecto");
		}
	}

	private void guardarRegistroLogin() {
		dao = new LoginDao();
		maxCodigo = dao.maxCodigo() + 1;
		cargarDatoLogin();
		try {
			dao.insertarOModificar(login);
			dao.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void accederAMenu() {
		Menu.main(enviarLogin());
		dispose();
	}

	private void cargarDatoLogin() {
		login = new Login();
		Date date = new Date();
		login.setLogin_codigo(maxCodigo);
		login.setLog_fecha(date);
		login.setUsuario(usuario);
	}

	private void generarDatosPorDefecto() {
		generarFunciones();
		generaPefiles();
		genearDefaultUser();
		generarMoneda();
	}

	public Login enviarLogin() {
		cargarDatoLogin();
		return login;
	}

}
