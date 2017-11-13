package consultorio.controlador;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import consultorio.daos.CitaDao;
import consultorio.interfece.*;
import consultorio.modelo.Cita;
import consultorio.modelo.Doctor;
import consultorio.modelo.Paciente;
import consultorio.swing.buscadores.BuscadorCliente;
import consultorio.swing.buscadores.BuscadorProfesional;
import consultorio.swing.componentes.ComBasicos;
import consultorio.swing.movimiento.FormularioCitas;
import consultorio.swing.movimiento.ListaCitas;
import consultorio.util.CalcularEdad;

public class ControlCita implements AcionesBasicas, InterfacePaciente, InterfaceDoctor {
	
	private FormularioCitas form;
	private ListaCitas lCita;
	private Cita cita;
	private CitaDao dao;
	private Paciente paciente;
	private Doctor doctor;
	private List<JTextField> listaTf;
	
	public ControlCita(FormularioCitas formCit, ListaCitas lForm) {
		if(formCit == null){
			System.out.println("Entra 1ro");
			this.lCita = lForm;
			this.lCita.getHerramientasGenericas().setAccionesBsicas(this);
		} else /*if((lForm != null) || (formPro != null) || (form))*/{
			this.form = formCit;
			this.form.getGrupoBotonCitas().setAccionesBsicas(this);
			this.form.setAccionesBsicas(this);
			verify();

		}
	
	}
	
	@Override
	public void nuevo() {
		vaciar();
		form.getLbNroCita().setText(maxCodigo()+"");
		form.getGrupoBotonCitas().getBtnModificar().setEnabled(false);
		form.getBuscarPaci().requestFocus();
		estadoInicial(true);
	}

	@Override
	public void modificar() {
			form.getGrupoBotonCitas().getBtnModificar().setEnabled(false);
			estadoInicial(true);			
	}

	@Override
	public void eliminar() {
		cita = lCita.getLista().get(lCita.getTable().getSelectedRow());
		dao = new CitaDao();
		int res = JOptionPane.showConfirmDialog(null,
				"Desea Eliminar la cita de " + cita.getPaciente().getNombre() + ", con el Dr. " + cita.getDoctor().getNombre() + "?", "Confirmación", 2, 3);

		if (res == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(cita);
				dao.ejecutar();
				ListaCitas.recuperarTodo();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se puede eliminar la cita de " + cita.getPaciente().getNombre()
						+ " porque ya está siendo utilizádo en una estadía.");
			}
		}
	}

	@Override
	public void guardar() {
		//if(validar() == true){
			dao= new CitaDao();
			cargaDatosAObj();
			try {
				dao.insertarOModificar(cita);
				dao.ejecutar();
				vaciar();
				ListaCitas.recuperarTodo();
				estadoInicial(false);
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
	//	}else{
		//	JOptionPane.showMessageDialog(null, "Complete los campos obligatorios");
		}
//	}

	@Override
	public void cancelar() {
		vaciar();
		estadoInicial(false);
		form.getGrupoBotonCitas().getBtnModificar().setEnabled(false);
	}
	
	@Override
	public void buscarPa() {
		BuscadorCliente cliBus = new BuscadorCliente();
		cliBus.setInterfacePacientes(this);
		cliBus.setVisible(true);		
	}

	@Override
	public void buscarDo() {
		BuscadorProfesional docBus = new BuscadorProfesional();
		docBus.setInterfaceDoctor(this);
		docBus.setVisible(true);
	}
	
	public int maxCodigo(){
		dao = new CitaDao();
		int v= dao.maxCodigo()+1;
		return v ;
	}
	
	private void vaciar(){
		listaTf = new ArrayList<>();
		listaTf.add(form.getTfPaciente());
		listaTf.add(form.getTfProfesional());
		form.getSpinner().getModel().setValue(FormularioCitas.calendar.getTime());	
		ComBasicos.limpiarTf(listaTf);
		
		form.getCombEstado().setSelectedIndex(0);
		form.getLbNroCita().setText("");
		form.getGrupoBotonCitas().getContenedorCitas_().getLbEdad().setText("0");
		form.getGrupoBotonCitas().getContenedorCitas_().getLbTelefono().setText("");
		form.getGrupoBotonCitas().getContenedorCitas_().getLbDireccion().setText("");
		form.getGrupoBotonCitas().getContenedorCitas_().getLbEmail().setText("");
		form.getDateChooser().setDate(null);
		form.getGrupoBotonCitas().getContenedorCitas_().getContenedorImagen().getEliminar();
	}
	
	private void estadoInicial(boolean b){
		form.getTfPaciente().setEnabled(b);
		form.getTfProfesional().setEnabled(b);
		form.getSpinner().setEnabled(b);
		form.getBuscarPaci().setEnabled(b);
		form.getBuscarPro().setEnabled(b);
		form.getCombEstado().setEnabled(b);
		form.getGrupoBotonCitas().getBtnNuevo().setEnabled(!b);
		form.getGrupoBotonCitas().getBtnGuardar().setEnabled(b);
		form.getGrupoBotonCitas().getBtnCancelar().setEnabled(b);
		form.getTfObs().setEnabled(b);
		form.getDateChooser().setEnabled(b);
	}	

	private void cargaDatosAObj(){
		cita = new Cita();
		cita.setId(Integer.parseInt(form.getLbNroCita().getText()));

		cita.setPaciente(paciente);
		cita.setDoctor(doctor);
		
		cita.setFechaCita(form.getDateChooser().getDate());
		cita.setHora((Date) form.getSpinner().getValue());
		cita.setObs(form.getTfObs().getText());
		cita.setEstado(form.getCombEstado().getSelectedIndex());
	}
	
	private void verify() {
		/// verify() se encarga de realizar acciones dependiendo de lo que recibe ListaProfecionales()
		System.out.println("Entra Verify");
		/// Nuevo
		if (form.getvRe() == 1) {
			System.out.println("Entra nuevo");
			vaciar();
			form.getLbNroCita().setText(maxCodigo()+"");
			form.getGrupoBotonCitas().getBtnModificar().setEnabled(false);
			form.getBuscarPaci().requestFocus();
			estadoInicial(true);
		}

		/// Modifica
		if ((form.getCitaRecibido() != null) && (form.getvRe() == 2)) {
			System.out.println(form.getCitaRecibido().getPaciente().getNombre()+ "Modifica");
			cargar();
			estadoInicial(true);
			
		}

		/// Visualiza
		if ((form.getCitaRecibido() != null) && (form.getvRe() == 3)) {
			cargar();
			estadoInicial(false);
			form.getGrupoBotonCitas().getBtnModificar().setEnabled(true);
		}

	}
	
	private void cargar(){
		if (form.getCitaRecibido() != null) {
			form.getLbNroCita().setText(form.getCitaRecibido().getId()+"");
			form.getTfPaciente().setText(form.getCitaRecibido().getPaciente().getNombre() 
					+" "+form.getCitaRecibido().getPaciente().getApellido());
			form.getTfProfesional().setText(form.getCitaRecibido().getDoctor().getNombre()
					+" "+form.getCitaRecibido().getDoctor().getApellido());
			//// asignamos nuevo dato a los Obj de busqueda
			//MUY IMPORTANTE LO QUE PASA AQUI///////
			paciente = form.getCitaRecibido().getPaciente();
			doctor = form.getCitaRecibido().getDoctor();
			//////////////////////////////////////////
			//carga de horario y fechas------
			form.getDateChooser().setDate(form.getCitaRecibido().getFechaCita());
			form.getSpinner().setValue(form.getCitaRecibido().getHora());;
			//------
			//---Cargamos los componentes de las imagenes & cia
			form.getGrupoBotonCitas().getContenedorCitas_().getLbEdad()
				.setText(darEdad(form.getCitaRecibido().getPaciente().getFechaNac())+"");
			form.getGrupoBotonCitas().getContenedorCitas_().getLbTelefono()
				.setText(form.getCitaRecibido().getPaciente().getTelefono()+"");
			form.getGrupoBotonCitas().getContenedorCitas_().getLbDireccion()
				.setText(form.getCitaRecibido().getPaciente().getDireccion()+"");
			form.getGrupoBotonCitas().getContenedorCitas_().getLbEmail()
				.setText(form.getCitaRecibido().getPaciente().getEmail()+"");
			//--//
			form.getTfObs().setText(form.getCitaRecibido().getObs());
			form.getGrupoBotonCitas().getContenedorCitas_().getContenedorImagen().setByteImg(form.getCitaRecibido().getPaciente().getImagen());
			//---
			if (form.getCitaRecibido().getPaciente().getImagen() != null) 
				form.getGrupoBotonCitas()
				.getContenedorCitas_()
				.getContenedorImagen()
				.getFotoLbl()
				.setIcon(new ImageIcon(form.getGrupoBotonCitas().getContenedorCitas_().getContenedorImagen().getByteImg()));
			//---//
		}
	}
	
	private int darEdad(Date d){
		return CalcularEdad.calcularEdad(d);
	}

	@Override
	public void setDoctor(Doctor doc) {
		doctor = doc;
		form.getTfProfesional().setText(doctor.getNombre()+" "+doctor.getApellido());
	}

	@Override
	public void setPacientes(Paciente paci) {
		paciente = paci;
		form.getTfPaciente().setText(paciente.getNombre()+" "+paciente.getApellido());		
		form.getGrupoBotonCitas().getContenedorCitas_().getLbEdad().setText(darEdad(paciente.getFechaNac())+"");
		form.getGrupoBotonCitas().getContenedorCitas_().getLbTelefono().setText(paciente.getTelefono());
		form.getGrupoBotonCitas().getContenedorCitas_().getLbDireccion().setText(paciente.getDireccion());
		form.getGrupoBotonCitas().getContenedorCitas_().getLbEmail().setText(paciente.getEmail());
		////////
		form.getGrupoBotonCitas().getContenedorCitas_().getContenedorImagen().setByteImg(paciente.getImagen());
		if (paciente.getImagen() != null) 
			form.getGrupoBotonCitas()
			.getContenedorCitas_()
			.getContenedorImagen()
			.getFotoLbl()
			.setIcon(new ImageIcon(form.getGrupoBotonCitas().getContenedorCitas_().getContenedorImagen().getByteImg()));
	}

	
//	private boolean validar(){
//		boolean b = true;
//		if(form.getTfNombre().getText().isEmpty() || form.getTfapellido().getText().isEmpty() 
//				|| form.getTfDoc().getText().isEmpty() || form.getDateChooser().getDate() == null ){
//			b = false;
//		}
//		return b;
//	}
	
}
