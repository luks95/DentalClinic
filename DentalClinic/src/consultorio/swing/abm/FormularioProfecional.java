package consultorio.swing.abm;

import java.awt.Color;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import consultorio.controlador.ControlProfecional;
import consultorio.modelo.Doctor;
import consultorio.swing.componentes.ComBasicos;
import consultorio.swing.componentes.ContenedorImagen;
import consultorio.swing.componentes.TfGeneric;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import algoritmo.CalculoDigitoVerificador;
import consultorio.swing.componentes.GrupoBoton;
import javax.swing.SpringLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioProfecional extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfDoc;
	private JTextField tfapellido;
	private JTextField tfNombre;
	private JTextField tfTelefono;
	private JTextField tfdireccion;
	private JTextField tfemail;
	private JTextArea tfobs;
	private JLabel lblCodigo;
	private JLabel lblEdad;
	private JRadioButton rdbRuc;
	private JRadioButton rdbDoc;
	private TfGeneric tfEspe;
	private TfGeneric tfRec;
	
	private int vRe;
	private Doctor profRecibido;
	
	@SuppressWarnings("unused")
	private ControlProfecional controller;
	private ContenedorImagen contenedorImagen;
	private GrupoBoton grupoBoton;
	private JDateChooser dateChooser;
	private ButtonGroup btnGroup;
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public FormularioProfecional(int v, Doctor p) {
		this.vRe = v;
		this.profRecibido = p;
		
		setBounds(100, 100, 561, 522);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		getContentPane().setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		setTitle("Formulario Profesionales");
		getContentPane().setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel label = new JLabel("E-mail");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label.setBounds(23, 255, 58, 24);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Obs.");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_1.setBounds(23, 365, 42, 24);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Direcci\u00F3n");
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_2.setBounds(23, 221, 58, 24);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Tel\u00E9fono");
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_3.setBounds(23, 186, 64, 24);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Fecha Nacimiento");
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_4.setBounds(23, 151, 111, 24);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Edad");
		label_5.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_5.setBounds(238, 173, 35, 20);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Apellido");
		label_6.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_6.setBounds(23, 81, 64, 24);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("Nombre");
		label_7.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_7.setBounds(23, 46, 64, 24);
		getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("C\u00F3digo");
		label_8.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_8.setBounds(23, 11, 42, 24);
		getContentPane().add(label_8);
		
		lblCodigo = new JLabel("");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodigo.setBounds(82, 11, 92, 24);
		getContentPane().add(lblCodigo);
		
		lblEdad = new JLabel("0");
		lblEdad.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblEdad.setBounds(247, 155, 21, 20);
		getContentPane().add(lblEdad);
		
		btnGroup = new ButtonGroup();
		
		rdbDoc = new JRadioButton("Doc.");
		rdbDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbDoc.isSelected()) {
					textField.setVisible(false);
					textField.setText("");
				}
			}
		});
		rdbDoc.setSelected(true);
		rdbDoc.setOpaque(false);
		rdbDoc.setHorizontalTextPosition(SwingConstants.LEFT);
		rdbDoc.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		rdbDoc.setBounds(6, 121, 60, 23);
		btnGroup.add(rdbDoc);
		getContentPane().add(rdbDoc);
		
		rdbRuc = new JRadioButton("R.U.C.");
		rdbRuc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				darDigito();
			}
		});
		rdbRuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbRuc.isSelected()) {
					textField.setVisible(true);
					darDigito();
				}
			}
		});
		rdbRuc.setHorizontalTextPosition(SwingConstants.LEFT);
		rdbRuc.setHorizontalAlignment(SwingConstants.CENTER);
		rdbRuc.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		rdbRuc.setContentAreaFilled(false);
		rdbRuc.setBounds(56, 120, 64, 23);
		btnGroup.add(rdbRuc);
		getContentPane().add(rdbRuc);
		
		tfDoc = new TfGeneric();
		tfDoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (tfDoc.getText().isEmpty()) {
						tfDoc.requestFocus();
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "El campo DOCUMENTO es obligatorio");
					} else {
						darDigito();
						dateChooser.requestFocus();
					}

				}
			}
		});
		tfDoc.setColumns(10);
		tfDoc.setBounds(121, 121, 116, 24);
		getContentPane().add(tfDoc);
		
		tfapellido = new TfGeneric();
		tfapellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (tfapellido.getText().isEmpty()) {
						tfapellido.requestFocus();
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "El campo APELLIDO es obligatorio");
					} else {
						tfDoc.requestFocus();
					}

				}
			}
		});
		tfapellido.setColumns(10);
		tfapellido.setBounds(75, 81, 197, 24);
		getContentPane().add(tfapellido);
		
		tfNombre = new TfGeneric();
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (tfNombre.getText().isEmpty()) {
						tfNombre.requestFocus();
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "El campo NOMBRE es obligatorio");
					} else {
						tfapellido.requestFocus();
					}

				}
			}
		});
		tfNombre.setColumns(10);
		tfNombre.setBounds(75, 46, 197, 24);
		getContentPane().add(tfNombre);
		
		tfTelefono = new TfGeneric();
		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {	
						tfdireccion.requestFocus();
				}	
			}
		});
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(75, 186, 104, 24);
		getContentPane().add(tfTelefono);
		
		tfdireccion = new TfGeneric();
		tfdireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						tfemail.requestFocus();
				}
			}
		});
		tfdireccion.setColumns(10);
		tfdireccion.setBounds(75, 222, 197, 24);
		getContentPane().add(tfdireccion);
		
		tfemail = new JTextField();
		tfemail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfemail.getText().equals( "ejemplo99@email.com")) {
					tfemail.setText("");
					tfemail.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfemail.getText().isEmpty()) {
					ComBasicos.PlaceHolderlDefaul(tfemail, "ejemplo99@email.com");
				}
			}
		});
		tfemail.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		tfemail.setEnabled(false);
		tfemail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						tfEspe.requestFocus();
				}
			}
		});
		tfemail.setColumns(10);
		tfemail.setBounds(75, 256, 197, 24);
		ComBasicos.PlaceHolderlDefaul(tfemail, "ejemplo99@email.com");
		getContentPane().add(tfemail);
		
		tfobs = new JTextArea();
		tfobs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					grupoBoton.getBtnGuardar().requestFocus();
				}
			}
		});
		tfobs.setEnabled(false);
		tfobs.setLineWrap(true);
		tfobs.setBounds(75, 366, 197, 106);
		getContentPane().add(tfobs);
		
		dateChooser = new JDateChooser();
		dateChooser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (dateChooser.getDate() == null) {
						dateChooser.requestFocus();
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "El campo FECHA es obligatorio");
					} else {
						tfapellido.requestFocus();
					}

				}
			}
		});
		dateChooser.getCalendarButton().setEnabled(false);
		dateChooser.setBounds(131, 155, 95, 20);
		getContentPane().add(dateChooser);
		
		JLabel lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblEspecialidad.setBounds(23, 290, 76, 24);
		getContentPane().add(lblEspecialidad);
		
		tfEspe = new TfGeneric();
		tfEspe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						tfRec.requestFocus();
				}
			}
		});
		tfEspe.setColumns(10);
		tfEspe.setBounds(96, 291, 176, 24);
		getContentPane().add(tfEspe);
		
		JLabel lblReg = new JLabel("R.E.G. ");
		lblReg.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblReg.setBounds(23, 325, 76, 24);
		getContentPane().add(lblReg);
		
		tfRec = new TfGeneric();
		tfRec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfobs.requestFocus();
				}
			}
		});
		tfRec.setColumns(10);
		tfRec.setBounds(75, 326, 104, 24);
		getContentPane().add(tfRec);
		
		grupoBoton = new GrupoBoton();
		SpringLayout springLayout = (SpringLayout) grupoBoton.getLayout();
		grupoBoton.setBounds(283, 34, 252, 438);
		getContentPane().add(grupoBoton);
		
		contenedorImagen = new ContenedorImagen();
		springLayout.putConstraint(SpringLayout.NORTH, contenedorImagen, 60, SpringLayout.NORTH, grupoBoton);
		springLayout.putConstraint(SpringLayout.WEST, contenedorImagen, 0, SpringLayout.WEST, grupoBoton);
		springLayout.putConstraint(SpringLayout.SOUTH, contenedorImagen, 329, SpringLayout.NORTH, grupoBoton);
		springLayout.putConstraint(SpringLayout.EAST, contenedorImagen, 0, SpringLayout.EAST, grupoBoton);
		grupoBoton.add(contenedorImagen);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (rdbRuc.isSelected()) {
					if (Character.isLetter(c)) {
						getToolkit().beep();
						e.consume();
						JOptionPane.showMessageDialog(null, "Porfavor, ingrese solo Números");
						tfDoc.setText("");
					}
				}
			}
		});
		textField.setVisible(false);
		textField.setEditable(false);
		textField.setBounds(245, 121, 27, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		execute();
		
	}
	
	public void setupController(){
		this.controller = new ControlProfecional(this, null);
	}

	public JTextField getTfDoc() {
		return tfDoc;
	}


	public JTextField getTfapellido() {
		return tfapellido;
	}


	public JTextField getTfNombre() {
		return tfNombre;
	}


	public JTextField getTfTelefono() {
		return tfTelefono;
	}

	public JTextField getTfdireccion() {
		return tfdireccion;
	}


	public JTextField getTfemail() {
		return tfemail;
	}


	public JTextArea getTfobs() {
		return tfobs;
	}


	public JLabel getLblCodigo() {
		return lblCodigo;
	}


	public JLabel getLblEdad() {
		return lblEdad;
	}

	public ContenedorImagen getContenedorImagen() {
		return contenedorImagen;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public TfGeneric getTfEspe() {
		return tfEspe;
	}

	public TfGeneric getTfRec() {
		return tfRec;
	}
	
	public GrupoBoton getGurpoBoton() {
		return grupoBoton;
	}
	
	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public int getvRe() {
		return vRe;
	}

	public Doctor getProfRecibido() {
		return profRecibido;
	}
	
	public ButtonGroup getBtnGroup() {
		return btnGroup;
	}

	private void darDigito() {
		if ((rdbRuc.isSelected()) && !(tfDoc.getText().isEmpty())) {
			int v = Integer.parseInt(tfDoc.getText());
			textField.setText(CalculoDigitoVerificador.calculandoDigito(tfDoc.getText(), v) + "");
		}
	}
	
	private void execute(){
		try {
			setupController();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
