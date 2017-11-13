package consultorio.swing.movimiento;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.JLabel;
import consultorio.swing.componentes.TfGeneric;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import consultorio.controlador.ControlCita;
import consultorio.interfece.AcionesBasicas;
import consultorio.modelo.Cita;
import consultorio.swing.componentes.GrupoBotonCitas;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class FormularioCitas extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private TfGeneric tfPaciente;
	private JDateChooser dateChooser;
	@SuppressWarnings("rawtypes")
	private JComboBox combEstado;
	private TfGeneric tfProfesional;
	private JTextPane tfObs;
	private GrupoBotonCitas grupoBotonCitas;
	private JSpinner spinner;
	private JLabel lbNroCita;
	private JButton buscarPro;
	private JButton buscarPaci;
	@SuppressWarnings("unused")
	private ControlCita controller;
	private AcionesBasicas accionesBasicas;
	private int vRe;
	private Cita citaRecibido;
	public static Calendar calendar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioCitas dialog = new FormularioCitas(1 , null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FormularioCitas(int v , Cita c) {
		this.vRe = v;
		this.citaRecibido = c;
		
		setBounds(100, 100, 561, 522);
		getContentPane().setLayout(null);
		getContentPane().setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		setTitle("Formulario Citas");
		contentPanel.setBounds(0, 0, 0, 0);
		getContentPane().setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("C\u00F3digo");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label.setBounds(10, 11, 42, 24);
		contentPanel.add(label);
		
		JLabel lblCodigo = new JLabel("");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodigo.setBounds(69, 11, 92, 24);
		contentPanel.add(lblCodigo);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblPaciente.setBounds(10, 46, 49, 24);
		contentPanel.add(lblPaciente);
		
		tfPaciente = new TfGeneric();
		tfPaciente.setEditable(false);
		tfPaciente.setBounds(71, 80, 143, 21);
		getContentPane().add(tfPaciente);
		
		JLabel lblPa = new JLabel("Paciente");
		lblPa.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblPa.setBounds(10, 78, 49, 24);
		getContentPane().add(lblPa);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblFecha.setBounds(10, 138, 49, 24);
		getContentPane().add(lblFecha);
		
		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.setBounds(71, 138, 106, 20);
		getContentPane().add(dateChooser);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblHora.setBounds(10, 198, 49, 24);
		getContentPane().add(lblHora);
		
		JLabel lblProfesional = new JLabel("Profesional");
		lblProfesional.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblProfesional.setBounds(10, 258, 58, 24);
		getContentPane().add(lblProfesional);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblEstado.setBounds(10, 318, 49, 24);
		getContentPane().add(lblEstado);
		
		combEstado = new JComboBox();
		combEstado.setModel(new DefaultComboBoxModel(new String[] {"Pendiente\t", "Cerrado", "Cancelado"}));
		combEstado.setBounds(71, 321, 113, 21);
		getContentPane().add(combEstado);
		
		tfProfesional = new TfGeneric();
		tfProfesional.setEditable(false);
		tfProfesional.setBounds(71, 260, 143, 21);
		getContentPane().add(tfProfesional);
		
		JLabel lblObs = new JLabel("Obs.");
		lblObs.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblObs.setBounds(10, 378, 49, 24);
		getContentPane().add(lblObs);
		
		tfObs = new JTextPane();
		tfObs.setEnabled(false);
		tfObs.setBounds(71, 382, 178, 88);
		getContentPane().add(tfObs);
		
		grupoBotonCitas = new GrupoBotonCitas();
		grupoBotonCitas.setBounds(259, 11, 286, 460);
		getContentPane().add(grupoBotonCitas);
		
		///Contruccion de e implementacion del contenedor de Hora
		 	calendar = Calendar.getInstance();
	        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);

	        SpinnerDateModel model = new SpinnerDateModel();
	        model.setValue(calendar.getTime());

	        spinner = new JSpinner(model);
	        spinner.setEnabled(false);

	        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
	        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
	        formatter.setAllowsInvalid(false); // this makes what you want
	        formatter.setOverwriteMode(true);
	        spinner.setBounds(71, 198, 69, 24);
	        spinner.setEditor(editor);
	        
	        getContentPane().add(spinner);
	      
	    ///////////////////////////////////////////////////////////////////////////  
	        
        JLabel lblNroCita = new JLabel("Nro. Cita");
        lblNroCita.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        lblNroCita.setBounds(10, 11, 49, 24);
        getContentPane().add(lblNroCita);
	        
	    lbNroCita = new JLabel("");
	    lbNroCita.setFont(new Font("Century Gothic", Font.BOLD, 16));
	    lbNroCita.setBounds(71, 11, 49, 33);
	    getContentPane().add(lbNroCita);
	        
	    buscarPro = new JButton("..");
	    buscarPro.setToolTipText("Buscar Doctor");
	    buscarPro.setEnabled(false);
	    buscarPro.setBounds(224, 260, 31, 21);
	    getContentPane().add(buscarPro);
	        
        buscarPaci = new JButton("...");
        buscarPaci.setToolTipText("Buscar Paciente");
        buscarPaci.setEnabled(false);
        buscarPaci.setBounds(224, 80, 31, 21);
        getContentPane().add(buscarPaci);
		
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setupEventManager();
		
		execute();
	}
	
	public void setAccionesBsicas(AcionesBasicas accionesBasicas) {
		this.accionesBasicas = accionesBasicas;
	}
	
	public void setupController(){
		this.controller = new ControlCita(this, null);
	}

	public TfGeneric getTfPaciente() {
		return tfPaciente;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCombEstado() {
		return combEstado;
	}

	public TfGeneric getTfProfesional() {
		return tfProfesional;
	}

	public JTextPane getTfObs() {
		return tfObs;
	}

	public GrupoBotonCitas getGrupoBotonCitas() {
		return grupoBotonCitas;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public JLabel getLbNroCita() {
		return lbNroCita;
	}

	public JButton getBuscarPro() {
		return buscarPro;
	}

	public JButton getBuscarPaci() {
		return buscarPaci;
	}
	
	private void execute(){
		try {
			setupController();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public int getvRe() {
		return vRe;
	}

	public Cita getCitaRecibido() {
		return citaRecibido;
	}
	
	private void setupEventManager() {
		buscarPaci.addActionListener(this);
		buscarPro.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "...":
			accionesBasicas.buscarPa();
			break;
		case "..":
			accionesBasicas.buscarDo();
			break;
		default:
			break;
		}
		
	}

}
