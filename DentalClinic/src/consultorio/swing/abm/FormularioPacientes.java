package consultorio.swing.abm;

import javax.swing.JDialog;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import consultorio.daos.PacienteDao;
import consultorio.modelo.Paciente;
import consultorio.swing.componentes.ComBasicos;
import consultorio.util.CalcularEdad;
import consultorio.util.Dibujo;
import consultorio.util.TfUpper;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import algoritmo.CalculoDigitoVerificador;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FormularioPacientes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDoc;
	private JTextField tfTelefono;
	private JTextField tfDireccion;
	private JTextField tfEmail;
	private JLabel lblNro;
	private JLabel lblImgIcon;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnQuitarFoto;
	private JButton btnAadirFoto;

	private Paciente paciente;
	private Paciente pacienteRecibido;
	private int vRe;
	private PacienteDao dao;
	private JDateChooser dateChooser;
	private JTextField tfDigito;
	private JRadioButton rdDoc;

	JFileChooser selecion = new JFileChooser();
	File archivo;
	private byte[] byteImg;
	Dibujo gestionA = new Dibujo();
	String ph = "ejemplo99@correo.com";
	
	private List<JTextField> tf = new ArrayList<>();
	private List<JLabel> lbl = new ArrayList<>();
	private JRadioButton rdRuc;
	private JButton btnModificar;
	private JTextArea tfObs;
	private JLabel lbEdad;
	private ButtonGroup btnGroup;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 */
	public FormularioPacientes(int v, Paciente p) {
		pacienteRecibido = p;
		vRe = v;
		getContentPane().setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));

		setTitle("Formulario Pacientes");
		setBounds(100, 100, 561, 522);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(240, 255, 240));

		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);

		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblCodigo.setBounds(24, 11, 42, 24);
		getContentPane().add(lblCodigo);

		lblNro = new JLabel("");
		lblNro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNro.setBounds(83, 11, 92, 24);
		getContentPane().add(lblNro);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNombre.setBounds(24, 46, 64, 24);
		getContentPane().add(lblNombre);

		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(24, 81, 64, 24);
		getContentPane().add(lblNewLabel_1);

		tfNombre = new JTextField();
		tfNombre.setDocument(new TfUpper());
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (tfNombre.getText().isEmpty()) {
						tfNombre.requestFocus();
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "El campo NOMBRE es obligatorio");
					} else {
						tfApellido.requestFocus();
					}

				}
			}
		});
		tfNombre.setBounds(77, 46, 197, 24);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		tf.add(tfNombre);

		tfApellido = new JTextField();
		tfApellido.setDocument(new TfUpper());
		tfApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (tfApellido.getText().isEmpty()) {
						tfApellido.requestFocus();
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "El campo APELLIDO es obligatorio");
					} else {
						tfDoc.requestFocus();
					}

				}
			}
		});
		tfApellido.setBounds(77, 81, 197, 24);
		getContentPane().add(tfApellido);
		tf.add(tfApellido);
		tfApellido.setColumns(10);

		tfDoc = new JTextField();
		tfDoc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				darDigito();
			}
		});
		tfDoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (tfDoc.getText().isEmpty()) {
						tfDoc.requestFocus();
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "El campo DOC./R.U.C es obligatorio");
					} else {
						darDigito();
						dateChooser.requestFocus();
					}

				}

			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (rdRuc.isSelected()) {
					if (Character.isLetter(c)) {
						getToolkit().beep();
						e.consume();
						JOptionPane.showMessageDialog(null, "Porfavor, ingrese solo Números");
						tfDoc.setText("");
					}
				}
			}
		});
		tfDoc.setBounds(115, 116, 102, 24);
		getContentPane().add(tfDoc);
		tf.add(tfDoc);
		tfDoc.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Fecha Nacimiento");
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(24, 151, 111, 24);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Tel\u00E9fono");
		lblNewLabel_4.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(24, 186, 64, 24);
		getContentPane().add(lblNewLabel_4);

		tfTelefono = new JTextField();
		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfDireccion.requestFocus();
				}
			}
		});
		tfTelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				darEdad();
			}
		});
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(77, 186, 104, 24);
		tf.add(tfTelefono);
		getContentPane().add(tfTelefono);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblEdad.setBounds(239, 173, 35, 20);
		getContentPane().add(lblEdad);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblDireccin.setBounds(24, 221, 58, 24);
		getContentPane().add(lblDireccin);

		tfDireccion = new JTextField();
		tfDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfEmail.requestFocus();
				}
			}
		});
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(77, 222, 197, 24);
		getContentPane().add(tfDireccion);
		tf.add(tfDireccion);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblEmail.setBounds(24, 255, 58, 24);
		getContentPane().add(lblEmail);

		tfEmail = new JTextField();
		ComBasicos.PlaceHolderlDefaul(tfEmail, ph);
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfEmail.getText().equals(ph)) {
					tfEmail.setText("");
					tfEmail.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfEmail.getText().isEmpty()) {
					ComBasicos.PlaceHolderlDefaul(tfEmail, ph);
				}
			}
		});
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfObs.requestFocus();
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(77, 256, 197, 24);
		getContentPane().add(tfEmail);
		tf.add(tfEmail);

		JLabel lblImag = new JLabel("Obs.");
		lblImag.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblImag.setBounds(24, 290, 42, 24);
		getContentPane().add(lblImag);

		tfObs = new JTextArea();
		tfObs.setLineWrap(true);
		tfObs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnGuardar.requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (tfObs.getText().length() > 100) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		tfObs.setBounds(77, 291, 197, 181);
		getContentPane().add(tfObs);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new CompoundBorder(
						new BevelBorder(BevelBorder.RAISED, new Color(240, 240, 240), new Color(255, 255, 255),
								new Color(105, 105, 105), new Color(160, 160, 160)),
						new LineBorder(new Color(180, 180, 180))),
				"Foto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(285, 105, 248, 268);
		getContentPane().add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 226, 197);
		panel.add(scrollPane);

		lblImgIcon = new JLabel("");
		scrollPane.setViewportView(lblImgIcon);

		btnAadirFoto = new JButton("A\u00F1adir Foto");
		btnAadirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anhadir();
			}
		});
		btnAadirFoto.setBounds(10, 233, 89, 23);
		panel.add(btnAadirFoto);

		btnQuitarFoto = new JButton("Quitar Foto");
		btnQuitarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblImgIcon.setIcon(null);
				byteImg = null;
			}
		});
		btnQuitarFoto.setBounds(147, 233, 89, 23);
		panel.add(btnQuitarFoto);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(284, 384, 249, 88);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnGuardar.setBounds(10, 11, 89, 23);
		panel_1.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btnCancelar.setBounds(150, 11, 89, 23);
		panel_1.add(btnCancelar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(83, 54, 89, 23);
		panel_1.add(btnModificar);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		btnNuevo.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 15));
		btnNuevo.setBounds(284, 46, 104, 48);
		getContentPane().add(btnNuevo);

		dateChooser = new JDateChooser();
		dateChooser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (dateChooser.getDate() == null) {
						dateChooser.requestFocus();
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "El campo Fecha de Nacimiento es obligatorio");
					} else {
						tfTelefono.requestFocus();
					}

				}
			}
		});
		dateChooser.setBounds(135, 155, 95, 20);
		getContentPane().add(dateChooser);

		btnGroup = new ButtonGroup();

		rdDoc = new JRadioButton("Doc.");
		rdDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdDoc.isSelected()) {
					tfDigito.setVisible(false);
					tfDigito.setText("");
				}
			}
		});
		rdDoc.setHorizontalTextPosition(SwingConstants.LEFT);
		rdDoc.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		rdDoc.setOpaque(false);
		rdDoc.setSelected(true);
		rdDoc.setBounds(6, 118, 60, 23);
		getContentPane().add(rdDoc);

		btnGroup.add(rdDoc);

		rdRuc = new JRadioButton("R.U.C.");
		rdRuc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfDigito.setVisible(true);
				darDigito();
			}
		});
		rdRuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdRuc.isSelected()) {
					tfDigito.setVisible(true);
					darDigito();
				}
			}
		});
		rdRuc.setHorizontalAlignment(SwingConstants.CENTER);
		rdRuc.setHorizontalTextPosition(SwingConstants.LEFT);
		rdRuc.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		rdRuc.setContentAreaFilled(false);
		rdRuc.setBounds(56, 117, 64, 23);
		getContentPane().add(rdRuc);

		btnGroup.add(rdRuc);

		tfDigito = new JTextField();
		tfDigito.setEditable(false);
		tfDigito.setVisible(false);
		tfDigito.setBounds(227, 116, 27, 24);
		getContentPane().add(tfDigito);
		tf.add(tfDigito);
		tfDigito.setColumns(10);

		lbEdad = new JLabel("");
		lbEdad.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lbEdad.setBounds(248, 155, 27, 20);
		getContentPane().add(lbEdad);
		lbl.add(lbEdad);
		lbl.add(lblNro);
		lbl.add(lblImgIcon);
		btnNuevo.requestFocus();
		verify();
	}

	public void nuevo() {
		limpiar();

		dao = new PacienteDao();
		lblNro.setText(dao.maxCodigo() + 1 + "");
		btnActive(true);
		btnNuevo.setEnabled(false);
		tfActive(true);
		tfNombre.requestFocus();
	}

	private void limpiar() {
		ComBasicos.limpiarTf(tf);
		ComBasicos.lblLimpiar(lbl);
		ComBasicos.PlaceHolderlDefaul(tfEmail, ph);
		tfObs.setText("");
		dateChooser.setDate(null);
		lblImgIcon.setIcon(null);
	}

	private void guardar() {
		dao = new PacienteDao();

		tomaPaciente();

		try {
			dao.insertarOModificar(paciente);
			dao.ejecutar();
			cancelar();
			ListaPacientes.recuperarTodo();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	private void tomaPaciente() {
		paciente = new Paciente();

		paciente.setCodigoCli(Integer.parseInt(lblNro.getText()));
		paciente.setNombre(tfNombre.getText());
		paciente.setApellido(tfApellido.getText());
		paciente.setDoc(tfDoc.getText());
		paciente.setFechaNac(dateChooser.getDate());
		paciente.setTelefono(tfTelefono.getText());
		paciente.setDireccion(tfDireccion.getText());
		paciente.setEmail(tfEmail.getText());
		paciente.setObs(tfObs.getText());
		paciente.setImagen(byteImg);
	}

	private void cargaForm() {
		if (pacienteRecibido != null) {
			lblNro.setText(pacienteRecibido.getCodigoCli() + "");
			tfNombre.setText(pacienteRecibido.getNombre());
			tfApellido.setText(pacienteRecibido.getApellido());
			tfDoc.setText(pacienteRecibido.getDoc());
			dateChooser.setDate(pacienteRecibido.getFechaNac());
			darEdad();
			tfTelefono.setText(pacienteRecibido.getTelefono());
			tfDireccion.setText(pacienteRecibido.getDireccion());
			tfEmail.setText(pacienteRecibido.getEmail());
			tfObs.setText(pacienteRecibido.getObs());
			byteImg = pacienteRecibido.getImagen();
			if (pacienteRecibido.getImagen() != null) {
				lblImgIcon.setIcon(new ImageIcon(byteImg));
			}
		}
	}

	private void verify() {
		/// Nuevo
		if (vRe == 1) {
			nuevo();
		}

		/// Modifica
		if (pacienteRecibido != null) {
			cargaForm();
			btnActive(true);
			tfActive(true);
			btnNuevo.setEnabled(false);
		}

		/// Visualiza
		if ((pacienteRecibido != null) && (vRe == 3)) {
			cargaForm();
			tfActive(false);
			btnActive(false);
			btnNuevo.setEnabled(true);
			btnModificar.setEnabled(true);
		}

	}

	private void cancelar() {
		btnActive(false);
		btnNuevo.setEnabled(true);
		btnModificar.setEnabled(false);
		tfActive(false);
		limpiar();
	}

	private void btnActive(Boolean b) {
		btnAadirFoto.setEnabled(b);
		btnCancelar.setEnabled(b);
		btnGuardar.setEnabled(b);
		btnQuitarFoto.setEnabled(b);
		rdDoc.setEnabled(b);
		rdRuc.setEnabled(b);

	}

	private void tfActive(boolean b) {
		tfNombre.setEnabled(b);
		tfApellido.setEnabled(b);
		tfDoc.setEnabled(b);
		dateChooser.setEnabled(b);
		;
		tfDireccion.setEnabled(b);
		tfEmail.setEnabled(b);
		tfTelefono.setEnabled(b);
		tfDigito.setEnabled(b);
		tfObs.setEnabled(b);
	}

	private void modificar() {
		btnActive(true);
		tfActive(true);
	}

	private void darEdad() {
		lbEdad.setText(CalcularEdad.calcularEdad(dateChooser.getDate()) + "");
	}

	private void darDigito() {
		if ((rdRuc.isSelected()) && !(tfDoc.getText().isEmpty())) {
			int v = Integer.parseInt(tfDoc.getText());
			tfDigito.setText(CalculoDigitoVerificador.calculandoDigito(tfDoc.getText(), v) + "");
		}
	}

	private void anhadir() {
		if (selecion.showDialog(null, "Abrir archivo") == JFileChooser.APPROVE_OPTION) {
			archivo = selecion.getSelectedFile();
			if (archivo.canRead()) {
				if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png")
						|| archivo.getName().endsWith("gif")) {
					byteImg = gestionA.abreImagen(archivo);
					lblImgIcon.setIcon(new ImageIcon(byteImg));
				} else {
					JOptionPane.showMessageDialog(null, "Formato no valido");
				}
			}
		}
	}
}