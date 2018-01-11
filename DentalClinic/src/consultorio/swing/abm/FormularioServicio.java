package consultorio.swing.abm;

import java.awt.Color; 
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import consultorio.controlador.ControlServicio;
import consultorio.modelo.Servicio;
import consultorio.swing.componentes.TfGeneric;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import consultorio.swing.componentes.CampoTextoPersonalizado;
import consultorio.swing.componentes.GrupoBotonServicio;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioServicio extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfTipoNombre;
	private JTextArea tfobs;
	private JLabel lblCodigo;
	
	private int vRe;
	private Servicio serRecibido;
	
	@SuppressWarnings("unused")
	private ControlServicio controller;
	private GrupoBotonServicio grupoBotonServicio;
	private JTextField tftotaProducto;
	private CampoTextoPersonalizado tfCostoServ;
	private CampoTextoPersonalizado tfcostoFi;

	/**
	 * Create the dialog.
	 */
	public FormularioServicio(int v, Servicio s) {
		this.vRe = v;
		this.serRecibido = s;
		
		setBounds(100, 100, 716, 478);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		getContentPane().setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		setTitle("Formulario Servicio");
		getContentPane().setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel label_1 = new JLabel("Obs.");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_1.setBounds(23, 277, 42, 24);
		getContentPane().add(label_1);
		
		JLabel lblNombreServicio = new JLabel("Nombre Servicio");
		lblNombreServicio.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNombreServicio.setBounds(23, 58, 90, 24);
		getContentPane().add(lblNombreServicio);
		
		JLabel lblNroServicio = new JLabel("Nro. Servicio");
		lblNroServicio.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNroServicio.setBounds(23, 11, 68, 24);
		getContentPane().add(lblNroServicio);
		
		lblCodigo = new JLabel("");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodigo.setBounds(123, 11, 92, 24);
		getContentPane().add(lblCodigo);
		
		tfTipoNombre = new TfGeneric();
		tfTipoNombre.setColumns(10);
		tfTipoNombre.setBounds(133, 59, 180, 24);
		getContentPane().add(tfTipoNombre);
		
		tfobs = new JTextArea();	
		tfobs.setEnabled(false);
		tfobs.setLineWrap(true);
		tfobs.setBounds(75, 278, 238, 116);
		getContentPane().add(tfobs);
		
		grupoBotonServicio = new GrupoBotonServicio();
		grupoBotonServicio.setBounds(323, 11, 377, 426);
		getContentPane().add(grupoBotonServicio);
		
		JLabel lblPrecioCosto = new JLabel("Costo Servicio");
		lblPrecioCosto.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblPrecioCosto.setBounds(23, 165, 90, 24);
		getContentPane().add(lblPrecioCosto);
		
		JLabel lblPrecioFinal = new JLabel("Total");
		lblPrecioFinal.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblPrecioFinal.setBounds(23, 223, 90, 24);
		getContentPane().add(lblPrecioFinal);
		
		JLabel lblPrecioCosto_1 = new JLabel("Total en productos");
		lblPrecioCosto_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblPrecioCosto_1.setBounds(23, 110, 102, 24);
		getContentPane().add(lblPrecioCosto_1);
		
		tftotaProducto = new TfGeneric();
		tftotaProducto.setColumns(10);
		tftotaProducto.setBounds(133, 112, 180, 24);
		getContentPane().add(tftotaProducto);
		
		tfCostoServ = new CampoTextoPersonalizado();
		tfCostoServ.recibeDecimales();
		tfCostoServ.setColumns(10);
		tfCostoServ.setBounds(133, 167, 92, 24);
		getContentPane().add(tfCostoServ);
		
		tfcostoFi = new CampoTextoPersonalizado();
		tfcostoFi.setColumns(10);
		tfcostoFi.setBounds(133, 225, 92, 24);
		getContentPane().add(tfcostoFi);
		
		execute();
		
	}
	
	public GrupoBotonServicio getGrupoBotonServicio() {
		return grupoBotonServicio;
	}

	public void setGrupoBotonServicio(GrupoBotonServicio grupoBotonServicio) {
		this.grupoBotonServicio = grupoBotonServicio;
	}
	
	public void setupController(){
		this.controller = new ControlServicio(this, null);
	}

	public JTextField getTfTipoNombre() {
		return tfTipoNombre;
	}

	public JTextArea getTfobs() {
		return tfobs;
	}


	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public int getvRe() {
		return vRe;
	}

	public Servicio getSerRecibido() {
		return serRecibido;
	}

	public JTextField getTftotaProducto() {
		return tftotaProducto;
	}

	public CampoTextoPersonalizado getTfCostoServ() {
		return tfCostoServ;
	}

	public CampoTextoPersonalizado getTfcostoFi() {
		return tfcostoFi;
	}

	public Servicio getServRecibido() {
		return serRecibido;
	}
	private void execute(){
		try {
			setupController();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
