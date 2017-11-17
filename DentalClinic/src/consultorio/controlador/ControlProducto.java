package consultorio.controlador;

import java.util.ArrayList; 
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import consultorio.daos.ProductoDao;
import consultorio.interfece.AcionesBasicas;
import consultorio.modelo.Producto;
import consultorio.swing.abm.FormularioProducto;
import consultorio.swing.abm.ListaProductos;
import consultorio.swing.componentes.ComBasicos;

public class ControlProducto implements AcionesBasicas {
	
	private FormularioProducto form;
	private ListaProductos lProdu;
	private Producto producto;
	private ProductoDao dao;
	private List<JTextField> listaTf;
	
	public ControlProducto(FormularioProducto formPro, ListaProductos lForm) {
		if(formPro == null){
			System.out.println("Entra 1ro");
			this.lProdu = lForm;
			this.lProdu.getHerramientasGenericas().setAccionesBsicas(this);
		} else /*if((lForm != null) || (formPro != null) || (form))*/{
			this.form = formPro;
			this.form.getGrupoBotonProducto().setAccionesBsicas(this);
			verify();

		}
	
	}
	
	@Override
	public void nuevo() {
		vaciar();
		form.getLblCodigo().setText(maxCodigo()+"");
		form.getGrupoBotonProducto().getBtnModificar().setEnabled(false);
		form.getTfNombre().requestFocus();
		estadoInicial(true);
	}

	@Override
	public void modificar() {
			form.getGrupoBotonProducto().getBtnModificar().setEnabled(false);
			estadoInicial(true);			
	}

	@Override
	public void eliminar() {
		producto = lProdu.getLista().get(lProdu.getTable().getSelectedRow());
		dao = new ProductoDao();
		int res = JOptionPane.showConfirmDialog(null,
				"Desea Eliminar " + producto.getNombre() + "?", "Confirmación", 2, 3);

		if (res == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(producto);
				dao.ejecutar();
				ListaProductos.recuperarTodo();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se puede eliminar " + producto.getNombre()
						+ " porque ya está siendo utilizádo en un Servicio.");
			}
		}
	}

	@Override
	public void guardar() {
		//if(validar() == true){
			dao= new ProductoDao();
			cargaDatos();
			try {
				dao.insertarOModificar(producto);
				dao.ejecutar();
				vaciar();
				estadoInicial(false);
				ListaProductos.recuperarTodo();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
	//	}else{
	//		JOptionPane.showMessageDialog(null, "Complete los campos obligatorios");
	//	}
	}

	@Override
	public void cancelar() {
		vaciar();
		estadoInicial(false);
		form.getGrupoBotonProducto().getBtnModificar().setEnabled(false);
	}
	
	public int maxCodigo(){
		dao = new ProductoDao();
		int v= dao.maxCodigo()+1;
		return v ;
	}
	
	private void vaciar(){
		listaTf = new ArrayList<>();
		listaTf.add(form.getTfNombre());
		listaTf.add(form.getTfCaracteristicas());
		listaTf.add(form.getTfCosto());
		listaTf.add(form.getTfProcentaje());
		form.getLblCodigo().setText("");
		ComBasicos.limpiarTf(listaTf);
	}
	
	private void estadoInicial(boolean b){
		form.getTfNombre().setEnabled(b);
		form.getTfCosto().setEnabled(b);
		form.getTfCaracteristicas().setEnabled(b);
		form.getGrupoBotonProducto().getBtnGuardar().setEnabled(b);
		form.getGrupoBotonProducto().getBtnCancelar().setEnabled(b);
		form.getGrupoBotonProducto().getBtnNuevo().setEnabled(b);
		form.getGrupoBotonProducto().getBtnNuevo().setEnabled(!b);
	}	

	private void cargaDatos(){
		producto = new Producto();
		producto.setId(Integer.parseInt(form.getLblCodigo().getText()));
		producto.setNombre(form.getTfNombre().getText());
		producto.setCaracteris(form.getTfCaracteristicas().getText());
		producto.setPrecioCosto(Double.parseDouble(form.getTfCosto().getText()));
	}
	
	private void verify() {
		/// verify() se encarga de realizar acciones dependiendo de lo que recibe ListaProfecionales()
		System.out.println("Entra Verify");
		/// Nuevo
		if (form.getvPr() == 1) {
			System.out.println("Entra nuevo");
			vaciar();
			form.getLblCodigo().setText(maxCodigo()+"");
			form.getGrupoBotonProducto().getBtnModificar().setEnabled(false);
			form.getTfNombre().requestFocus();
			estadoInicial(true);
		}

		/// Modifica
		if ((form.getProduRecibido() != null) && (form.getvPr() == 2)) {
			System.out.println(form.getProduRecibido().getNombre()+ "Modifica");
			cargar();
			estadoInicial(true);
			
		}

		/// Visualiza
		if ((form.getProduRecibido() != null) && (form.getvPr() == 3)) {
			cargar();
			estadoInicial(false);
			form.getGrupoBotonProducto().getBtnModificar().setEnabled(true);
		}

	}
	
	private void cargar(){
		if (form.getProduRecibido() != null) {
			//FALTA TERMINAR RAPIDO!!!!! DEBES HACER LA CONSULTA A LOS DETALLES
			
			form.getLblCodigo().setText(form.getProduRecibido().getId()+"");
			form.getTfNombre().setText(form.getProduRecibido().getNombre());
			form.getTfCaracteristicas().setText(form.getProduRecibido().getCaracteris());
			form.getTfCosto().setText(form.getProduRecibido().getId()+ "");		
		}
	}
//	private boolean validar(){
//		boolean b = true;
//		if(form.getTfNombre().getText().isEmpty() || form.getTfCosto().getText().isEmpty()){
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
