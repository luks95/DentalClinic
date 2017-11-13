package consultorio.controlador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import consultorio.daos.ServicioDao;
import consultorio.interfece.AcionesBasicas;
import consultorio.modelo.Servicio;
import consultorio.swing.abm.FormularioServicio;
import consultorio.swing.abm.ListaServicios;
import consultorio.swing.componentes.ComBasicos;

public class ControlServicio implements AcionesBasicas {
	
	private FormularioServicio form;
	private ListaServicios lServ;
	private Servicio servicios;
	private ServicioDao dao;
	private List<JTextField> listaTf;
	
	public ControlServicio(FormularioServicio formPro, ListaServicios lForm) {
		if(formPro == null){
			System.out.println("Entra 1ro");
			this.lServ = lForm;
			this.lServ.getHerramientasGenericas().setAccionesBsicas(this);
		} else /*if((lForm != null) || (formPro != null) || (form))*/{
			this.form = formPro;
			this.form.getGrupoBotonServicio().setAccionesBsicas(this);
			verify();

		}
	
	}
	
	@Override
	public void nuevo() {
		vaciar();
		form.getLblCodigo().setText(maxCodigo()+"");
		form.getGrupoBotonServicio().getBtnModificar().setEnabled(false);
		form.getTfTipoNombre().requestFocus();
		estadoInicial(true);
	}

	@Override
	public void modificar() {
			form.getGrupoBotonServicio().getBtnModificar().setEnabled(false);
			estadoInicial(true);			
	}

	@Override
	public void eliminar() {
		servicios = lServ.getLista().get(lServ.getTable().getSelectedRow());
		dao = new ServicioDao();
		int res = JOptionPane.showConfirmDialog(null,
				"Desea Eliminar " + servicios.getNombreServicio() + "?", "Confirmación", 2, 3);

		if (res == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(servicios);
				dao.ejecutar();
				ListaServicios.recuperarTodo();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se puede eliminar " + servicios.getNombreServicio()
						+ " porque ya está siendo utilizádo en una Cita.");
			}
		}
	}

	@Override
	public void guardar() {
		//if(validar() == true){
			dao= new ServicioDao();
			cargaDatos();
			try {
				dao.insertarOModificar(servicios);
				dao.ejecutar();
				vaciar();
				estadoInicial(false);
				if(lServ != null){
					ListaServicios.recuperarTodo();
				}else{	
					
				}
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		//}else{
		//	JOptionPane.showMessageDialog(null, "Complete los campos obligatorios");
		//}
	}

	@Override
	public void cancelar() {
		vaciar();
		estadoInicial(false);
		form.getGrupoBotonServicio().getBtnModificar().setEnabled(false);
	}
	
	public int maxCodigo(){
		dao = new ServicioDao();
		int v= dao.maxCodigo()+1;
		return v ;
	}
	
	private void vaciar(){
		listaTf = new ArrayList<>();
		listaTf.add(form.getTfTipoNombre());
		listaTf.add(form.getTftotaProducto());
		listaTf.add(form.getTfcostoFi());
		listaTf.add(form.getTfCostoServ());
		form.getTfobs().setText("");
		form.getLblCodigo().setText("");
		ComBasicos.limpiarTf(listaTf);
	}
	
	private void estadoInicial(boolean b){
		form.getTfTipoNombre().setEnabled(b);
		form.getTfCostoServ().setEnabled(b);
		form.getTfcostoFi().setEnabled(b);
		form.getTfobs().setEnabled(b);
		form.getGrupoBotonServicio().getBtnGuardar().setEnabled(b);
		form.getGrupoBotonServicio().getBtnCancelar().setEnabled(b);
		form.getGrupoBotonServicio().getBtnNuevo().setEnabled(b);
		form.getGrupoBotonServicio().getBtnNuevo().setEnabled(!b);
	}	

	private void cargaDatos(){
		servicios = new Servicio();
		servicios.setCodigoSer(Integer.parseInt(form.getLblCodigo().getText()));
		servicios.setNombreServicio(form.getTfTipoNombre().getText());
		
	}
	
	private void verify() {
		/// verify() se encarga de realizar acciones dependiendo de lo que recibe ListaProfecionales()
		System.out.println("Entra Verify");
		/// Nuevo
		if (form.getvRe() == 1) {
			System.out.println("Entra nuevo");
			vaciar();
			form.getLblCodigo().setText(maxCodigo()+"");
			form.getGrupoBotonServicio().getBtnModificar().setEnabled(false);
			form.getTfTipoNombre().requestFocus();
			estadoInicial(true);
		}

		/// Modifica
		if ((form.getProfRecibido() != null) && (form.getvRe() == 2)) {
			System.out.println(form.getProfRecibido().getNombreServicio()+ "Modifica");
			cargar();
			estadoInicial(true);
			
		}

		/// Visualiza
		if ((form.getProfRecibido() != null) && (form.getvRe() == 3)) {
			cargar();
			estadoInicial(false);
			form.getGrupoBotonServicio().getBtnModificar().setEnabled(true);
		}

	}
	
	private void cargar(){
		
		
		
		if (form.getProfRecibido() != null) {
			double a, b;
			a = form.getSerRecibido().getPrecio();
			//FALTA TERMINAR RAPIDO!!!!! DEBES HACER LA CONSULTA A LOS DETALLES
			b = 0;
			form.getLblCodigo().setText(form.getProfRecibido().getCodigoSer()+"");
			form.getTfTipoNombre().setText(form.getProfRecibido().getNombreServicio());
			form.getTfCostoServ().setText(form.getProfRecibido().getCodigoSer()+ "");
			form.getTfcostoFi().setText(a+b +"");
			form.getTfobs().setText(form.getProfRecibido().getObs());
		
		}
	}
//	private boolean validar(){
//		boolean b = true;
//		if(form.getTfNombre().getText().isEmpty() || form.getTfapellido().getText().isEmpty() 
//				|| form.getTfDoc().getText().isEmpty() || form.getDateChooser().getDate() == null ){
//			b = false;
//		}
//		return b;
//	}

	@Override
	public void buscarPa() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscarDo() {
		// TODO Auto-generated method stub
		
	}

	
}
