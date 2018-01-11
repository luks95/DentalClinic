package consultorio.controlador;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import consultorio.daos.ProfecionalDao;
import consultorio.interfece.AcionesBasicas;
import consultorio.modelo.Doctor;
import consultorio.swing.abm.FormularioProfecional;
import consultorio.swing.abm.ListaProfecionales;
import consultorio.swing.componentes.ComBasicos;
import consultorio.util.CalcularEdad;

public class ControlProfecional implements AcionesBasicas {
	
	private FormularioProfecional form;
	private ListaProfecionales lProf;
	private Doctor doctor;
	private ProfecionalDao dao;
	private List<JTextField> listaTf;
	
	public ControlProfecional(FormularioProfecional formPro, ListaProfecionales lForm) {
		if(formPro == null){
			System.out.println("Entra 1ro");
			this.lProf = lForm;
			this.lProf.getHerramientasGenericas().setAccionesBsicas(this);
		} else /*if((lForm != null) || (formPro != null) || (form))*/{
			this.form = formPro;
			this.form.getGurpoBoton().setAccionesBsicas(this);
			verify();

		}
	
	}
	
	@Override
	public void nuevo() {
		vaciar();
		form.getLblCodigo().setText(maxCodigo()+"");
		form.getGurpoBoton().getBtnModificar().setEnabled(false);
		form.getTfNombre().requestFocus();
		estadoInicial(true);
	}

	@Override
	public void modificar() {
			form.getGurpoBoton().getBtnModificar().setEnabled(false);
			estadoInicial(true);			
	}

	@Override
	public void eliminar() {
		doctor = lProf.getLista().get(lProf.getTable().getSelectedRow());
		dao = new ProfecionalDao();
		int res = JOptionPane.showConfirmDialog(null,
				"Desea Eliminar " + doctor.getApellido() + ", " + doctor.getNombre() + "?", "Confirmación", 2, 3);

		if (res == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(doctor);
				dao.ejecutar();
				ListaProfecionales.recuperarTodo();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se puede eliminar " + doctor.getNombre()
						+ " porque ya está siendo utilizádo en una Cita.");
			}
		}
	}

	@Override
	public void guardar() {
		if(validar() == true){
			dao= new ProfecionalDao();
			cargaDatos();
			try {
				dao.insertarOModificar(doctor);
				dao.ejecutar();
				vaciar();
				estadoInicial(false);
				ListaProfecionales.recuperarTodo();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(null, "Complete los campos obligatorios");
		}
	}

	@Override
	public void cancelar() {
		vaciar();
		estadoInicial(false);
		form.getGurpoBoton().getBtnModificar().setEnabled(false);
	}
	
	public int maxCodigo(){
		dao = new ProfecionalDao();
		int v= dao.maxCodigo()+1;
		return v ;
	}
	
	private void vaciar(){
		listaTf = new ArrayList<>();
		listaTf.add(form.getTfNombre());
		listaTf.add(form.getTfapellido());
		listaTf.add(form.getTfDoc());
		listaTf.add(form.getTfTelefono());
		listaTf.add(form.getTfdireccion());
		listaTf.add(form.getTfemail());
		listaTf.add(form.getTfEspe());
		listaTf.add(form.getTfRec());	
		ComBasicos.limpiarTf(listaTf);
		form.getLblCodigo().setText("");
		form.getLblEdad().setText("0");
		form.getDateChooser().setDate(null);
		form.getContenedorImagen().getEliminar();
		ComBasicos.PlaceHolderlDefaul(form.getTfemail(), "ejemplo99@email.com");
	}
	
	private void estadoInicial(boolean b){
		form.getTfNombre().setEnabled(b);
		form.getTfapellido().setEnabled(b);
		form.getTfDoc().setEnabled(b);
		form.getTfTelefono().setEnabled(b);
		form.getTfdireccion().setEnabled(b);
		form.getTfemail().setEnabled(b);
		form.getTfEspe().setEnabled(b);
		form.getTfRec().setEnabled(b);	
		form.getContenedorImagen().getAgregar().setEnabled(b);
		form.getContenedorImagen().getSacar().setEnabled(b);
		form.getGurpoBoton().getBtnNuevo().setEnabled(!b);
		form.getGurpoBoton().getBtnGuardar().setEnabled(b);
		form.getGurpoBoton().getBtnCancelar().setEnabled(b);
		form.getTfobs().setEnabled(b);
		form.getDateChooser().setEnabled(b);
		
	}	

	private void cargaDatos(){
		doctor = new Doctor();
		doctor.setCodigoDr(Integer.parseInt(form.getLblCodigo().getText()));
		doctor.setNombre(form.getTfNombre().getText());
		doctor.setApellido(form.getTfapellido().getText());
		doctor.setDocumento(form.getTfDoc().getText());
		doctor.setFechaNac(form.getDateChooser().getDate());
		doctor.setTelefono(form.getTfTelefono().getText());
		doctor.setDireccion(form.getTfdireccion().getText());
		doctor.setEmail(form.getTfemail().getText());
		doctor.setEspecialidad(form.getTfEspe().getText());
		doctor.setReg(form.getTfRec().getText());
		doctor.setObs(form.getTfobs().getText());
		doctor.setImagen(form.getContenedorImagen().getByteImg());
	}
	
	private void verify() {
		/// verify() se encarga de realizar acciones dependiendo de lo que recibe ListaProfecionales()
		System.out.println("Entra Verify");
		/// Nuevo
		if (form.getvRe() == 1) {
			System.out.println("Entra nuevo");
			vaciar();
			form.getLblCodigo().setText(maxCodigo()+"");
			form.getGurpoBoton().getBtnModificar().setEnabled(false);
			form.getTfNombre().requestFocus();
			estadoInicial(true);
		}

		/// Modifica
		if ((form.getProfRecibido() != null) && (form.getvRe() == 2)) {
			System.out.println(form.getProfRecibido().getNombre()+ "Modifica");
			cargar();
			estadoInicial(true);
			
		}

		/// Visualiza
		if ((form.getProfRecibido() != null) && (form.getvRe() == 3)) {
			cargar();
			estadoInicial(false);
			form.getGurpoBoton().getBtnModificar().setEnabled(true);
		}

	}
	
	private void cargar(){
		if (form.getProfRecibido() != null) {
			form.getLblCodigo().setText(form.getProfRecibido().getCodigoDr()+"");
			form.getTfNombre().setText(form.getProfRecibido().getNombre());
			form.getTfapellido().setText(form.getProfRecibido().getApellido());
			form.getTfDoc().setText(form.getProfRecibido().getDocumento());
			form.getDateChooser().setDate(form.getProfRecibido().getFechaNac());
			form.getLblEdad().setText(darEdad(form.getProfRecibido().getFechaNac())+"");
			form.getTfTelefono().setText(form.getProfRecibido().getTelefono());
			form.getTfdireccion().setText(form.getProfRecibido().getDireccion());
			//---
			form.getTfemail().setForeground(Color.black);
			form.getTfemail().setText(form.getProfRecibido().getEmail());
			//--//
			form.getTfobs().setText(form.getProfRecibido().getObs());
			form.getContenedorImagen().setByteImg(form.getProfRecibido().getImagen());
			//---
			if (form.getProfRecibido().getImagen() != null) 
				form.getContenedorImagen().getFotoLbl().setIcon(new ImageIcon(form.getContenedorImagen().getByteImg()));
			//---//
		}
	}
	
	private int darEdad(Date d){
		return CalcularEdad.calcularEdad(d);
	}
	
	private boolean validar(){
		boolean b = true;
		if(form.getTfNombre().getText().isEmpty() || form.getTfapellido().getText().isEmpty() 
				|| form.getTfDoc().getText().isEmpty() || form.getDateChooser().getDate() == null ){
			b = false;
		}
		return b;
	}

	@Override
	public void buscarPa() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscarDo() {
		// TODO Auto-generated method stub
		
	}
	
}
