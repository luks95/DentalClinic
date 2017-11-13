package consultorio.swing.config;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import consultorio.daos.PerfilDao;
import consultorio.daos.UsuarioDao;
import consultorio.modelo.Perfil;
import consultorio.modelo.Usuario;
import consultorio.swing.componentes.ModeloTalblaUsuario;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionUsuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JPasswordField tfClave;
	private JPasswordField tfClaveComfirm;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxPerfiles;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnNuevo;
	private Usuario usuario;
	private JLabel lblLasClavesNo;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JTable table;
	private List<Usuario> lista;
	private ModeloTalblaUsuario mUsuario;
	private List<Perfil> listaPerfil;
	private Perfil perfil;
	private JButton btnModificar;
	private int v;
	private boolean bandera;
	private boolean banderaMouse;

	@SuppressWarnings("rawtypes")
	public GestionUsuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionUsuarios.class.getResource("/img/logoLeaf.jpg")));
		setTitle("ConsuLeaf - Gestion de Usuario");
		setBounds(100, 100, 589, 394);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Carga de Datos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 28, 328, 256);
		contentPanel.add(panel);
		panel.setLayout(null);

		tfNombre = new JTextField();
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfApellido.requestFocus();
				}
			}

		});
		tfNombre.setEnabled(false);
		tfNombre.setBounds(82, 23, 159, 20);
		panel.add(tfNombre);
		tfNombre.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 26, 46, 14);
		panel.add(lblNombre);
		lblNombre.setFont(new Font("Century Gothic", Font.PLAIN, 11));

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 56, 46, 14);
		panel.add(lblApellido);
		lblApellido.setFont(new Font("Century Gothic", Font.PLAIN, 11));

		tfApellido = new JTextField();
		tfApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfClave.requestFocus();
				}
			}
		});
		tfApellido.setEnabled(false);
		tfApellido.setBounds(82, 54, 159, 20);
		panel.add(tfApellido);
		tfApellido.setColumns(10);

		JLabel lblPass = new JLabel("Clave");
		lblPass.setBounds(10, 87, 64, 14);
		panel.add(lblPass);
		lblPass.setFont(new Font("Century Gothic", Font.PLAIN, 11));

		tfClave = new JPasswordField();
		tfClave.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfClaveComfirm.requestFocus();
				}
			}
		});
		tfClave.setEnabled(false);
		tfClave.setBounds(82, 85, 160, 20);
		panel.add(tfClave);

		JLabel lblConfirmarClave = new JLabel("Confirmar clave");
		lblConfirmarClave.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblConfirmarClave.setBounds(10, 116, 99, 14);
		panel.add(lblConfirmarClave);

		tfClaveComfirm = new JPasswordField();
		tfClaveComfirm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (verificarIgualdad() != true) {
					accionClaveDesigual();
				}
			}
		});
		tfClaveComfirm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				lblLasClavesNo.setVisible(false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificarIgualdad() == true) {
						comboBoxPerfiles.requestFocus();
					} else {
						accionClaveDesigual();
					}
				}
			}
		});
		tfClaveComfirm.setEnabled(false);
		tfClaveComfirm.setBounds(82, 151, 160, 20);
		panel.add(tfClaveComfirm);

		comboBoxPerfiles = new JComboBox();
		comboBoxPerfiles.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnGuardar.requestFocus();
				}
			}
		});
		comboBoxPerfiles.setEnabled(false);
		comboBoxPerfiles.setBounds(82, 196, 159, 20);
		panel.add(comboBoxPerfiles);

		JLabel lblSelecionarPerfil = new JLabel("Selecionar Perfil");
		lblSelecionarPerfil.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblSelecionarPerfil.setBounds(10, 172, 99, 14);
		panel.add(lblSelecionarPerfil);

		lblLasClavesNo = new JLabel("*Las claves no coinciden");
		lblLasClavesNo.setVisible(false);
		lblLasClavesNo.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblLasClavesNo.setForeground(Color.RED);
		lblLasClavesNo.setBounds(82, 130, 147, 14);
		panel.add(lblLasClavesNo);

		label = new JLabel("*");
		label.setBounds(66, 26, 14, 14);
		panel.add(label);

		label_1 = new JLabel("*");
		label_1.setBounds(66, 88, 14, 14);
		panel.add(label_1);

		label_2 = new JLabel("*");
		label_2.setBounds(66, 154, 14, 14);
		panel.add(label_2);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(251, 22, 67, 23);
		panel.add(btnNuevo);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});

		btnGuardar = new JButton("Guardar");
		btnGuardar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					verificarGuardar();
				}
			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verificarGuardar();
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(9, 295, 89, 23);
		contentPanel.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(108, 295, 89, 23);
		contentPanel.add(btnCancelar);

		banderaMouse = true;

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(348, 28, 225, 256);
		contentPanel.add(scrollPane);
		mUsuario = new ModeloTalblaUsuario();
		table = new JTable(mUsuario);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (banderaMouse == true) {
					cargarATabla();
				}
			}
		});
		scrollPane.setViewportView(table);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(348, 295, 89, 23);
		contentPanel.add(btnModificar);

		recuperarTodo();
		traerPerfiles();
	}

	private void recuperarTodo() {
		UsuarioDao dao = new UsuarioDao();
		lista = dao.recuperarTodo();
		mUsuario.setLista(lista);
		mUsuario.fireTableDataChanged();
	}

	private void traerPerfiles() {
		PerfilDao daoPerf = new PerfilDao();
		listaPerfil = new ArrayList<Perfil>();
		listaPerfil = daoPerf.recuperarTodo();
		System.out.println(listaPerfil.toString());
		cargarComboBox();
		comboBoxPerfiles.setSelectedIndex(listaPerfil.size() - 1);
	}

	@SuppressWarnings("unchecked")
	private void cargarComboBox() {
		for (int i = 0; i < listaPerfil.size(); i++) {
			comboBoxPerfiles.addItem(listaPerfil.get(i).getPerfilNombre());
		}
	}

	private void limpiar() {
		v = 0;
		tfNombre.setText("");
		tfApellido.setText("");
		tfClave.setText("");
		tfClaveComfirm.setText("");
		comboBoxPerfiles.setSelectedIndex(3);
	}

	private void nuevo() {
		tfNombre.requestFocus();
		btnNuevo.setEnabled(false);
		btnModificar.setEnabled(false);
		limpiar();
		activarTodo(true);
		bandera = true;
		banderaMouse = false;
		table.setEnabled(false);
		table.setRowSelectionAllowed(banderaMouse);
	}

	private void activarTodo(boolean b) {
		tfNombre.setEnabled(b);
		tfApellido.setEnabled(b);
		tfClave.setEnabled(b);
		tfClaveComfirm.setEnabled(b);
		comboBoxPerfiles.setEnabled(b);
		btnGuardar.setEnabled(b);
		btnCancelar.setEnabled(b);

	}

	private void cancelar() {
		btnNuevo.setEnabled(true);
		activarTodo(false);
		bandera = true;
		btnModificar.setEnabled(false);
		limpiar();
		table.setEnabled(true);
		banderaMouse = true;
		table.setRowSelectionAllowed(banderaMouse);
	}

	private void guardar() {
		UsuarioDao usuDao = new UsuarioDao();
		cargaDatos();

		try {
			usuDao.insertarOModificar(usuario);
			usuDao.ejecutar();
			cancelar();
			recuperarTodo();
		} catch (Exception e) {
			usuDao.rollback();
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	private boolean verificarIgualdad() {
		String vpass1 = tfClave.getText(), vpass2 = tfClaveComfirm.getText();
		boolean b;
		if (!vpass1.equals(vpass2)) {
			b = false;
		} else {
			b = true;
		}
		return b;
	}

	@SuppressWarnings("deprecation")
	////// Umi validacion hapoarehegua///////
	private void verificarGuardar() {
		String nombre = tfNombre.getText(), clave = tfClave.getText(), clave2 = tfClaveComfirm.getText();

		if (nombre.isEmpty() || clave.isEmpty() || clave2.isEmpty()) {

			if (nombre.isEmpty() && clave.isEmpty() && clave2.isEmpty()) {

				JOptionPane.showMessageDialog(null, "Llenar todos los campos");
				tfNombre.requestFocus();

			} else if (nombre.isEmpty()) {

				getToolkit().beep();
				tfNombre.requestFocus();

			}
		} else if (verificarIgualdad() == true) {
			guardar();
		} else {
			accionClaveDesigual();
		}
	}

	private void accionClaveDesigual() {
		lblLasClavesNo.setVisible(true);
		tfClave.setText("");
		tfClaveComfirm.setText("");
		tfClave.requestFocus();
		getToolkit().beep();
	}

	@SuppressWarnings("deprecation")
	private void cargaDatos() {
		usuario = new Usuario();
		perfil = listaPerfil.get(comboBoxPerfiles.getSelectedIndex());
		UsuarioDao usuDao = new UsuarioDao();
		Date date = new Date();
		if (bandera == true) {
			v = usuDao.maxCodigo() + 1;
			System.out.println("Codigo del momento: " + v);
		}

		usuario.setCodigoUsuario(v);
		usuario.setNombre(tfNombre.getText());
		usuario.setApellido(tfApellido.getText());
		usuario.setPassworld(tfClaveComfirm.getText());
		usuario.setFechaRegistro(date);
		usuario.setPerfil(perfil);
	}

	private void modificar() {
		activarTodo(true);
		btnModificar.setEnabled(false);
		btnNuevo.setEnabled(false);
		bandera = false;
		banderaMouse = false;
		table.setRowSelectionAllowed(banderaMouse);
	}

	private void cargarATabla() {
		usuario = lista.get(table.getSelectedRow());

		v = usuario.getCodigoUsuario();
		tfNombre.setText(usuario.getNombre());
		tfApellido.setText(usuario.getApellido());
		tfClave.setText(usuario.getPassworld());
		tfClaveComfirm.setText(usuario.getPassworld());
		int pId = usuario.getPerfil().getId();
		System.out.println(pId);
		comboBoxPerfiles.setSelectedIndex(listaPerfil.size() - pId);
		btnModificar.setEnabled(true);
	}
}
