package consultorio.swing.componentes;

import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import consultorio.util.Dibujo;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ContenedorImagen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton agregar;
	private JButton sacar;
	JFileChooser selecion = new JFileChooser();
	File archivo;
	private byte[] byteImg;
	Dibujo gestionA = new Dibujo();
	private JLabel fotoLbl;
	
	public ContenedorImagen() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(null);
		setBounds(285, 105, 248, 268);
		
		sacar = new JButton("Quitar Foto");
		sacar.setEnabled(false);
		sacar.setBounds(10, 236, 108, 23);
		sacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		add(sacar);
		
		agregar = new JButton("A\u00F1adir Foto");
		agregar.setEnabled(false);
		agregar.setBounds(130, 236, 108, 23);
		
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traerImagen();
			}
		});
		add(agregar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 226, 197);
		add(scrollPane);
		
		fotoLbl = new JLabel("");
		scrollPane.setViewportView(fotoLbl);
	}
	private void traerImagen(){
		if (selecion.showDialog(null, "Abrir archivo") == JFileChooser.APPROVE_OPTION) {
			archivo = selecion.getSelectedFile();
			if (archivo.canRead()) {
				if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png")
						|| archivo.getName().endsWith("gif")) {
					byteImg = gestionA.abreImagen(archivo);
					fotoLbl.setIcon(new ImageIcon(byteImg));
				} else {
					JOptionPane.showMessageDialog(null, "Formato no valido");
				}
			}
		}
	}
	
	private void eliminar(){
		fotoLbl.setIcon(null);
		byteImg = null;
	}
	
	public JLabel getEliminar(){
		eliminar();
		return fotoLbl;
	}
	
	public byte[] getByteImg() {
		return byteImg;
	}
	
	public void setByteImg(byte[] byteImg) {
		this.byteImg = byteImg;
	}
	public JButton getAgregar() {
		return agregar;
	}
	
	public JButton getSacar() {
		return sacar;
	}
	public JLabel getFotoLbl() {
		return fotoLbl;
	}
	public void setFotoLbl(JLabel fotoLbl) {
		this.fotoLbl = fotoLbl;
	}
	
	
	
}
