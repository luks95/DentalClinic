package consultorio.controlador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import consultorio.daos.ProductoDao;
import consultorio.daos.ServicioDao;
import consultorio.daos.ServicioProductoDao;
import consultorio.interfece.AcionesBasicas;
import consultorio.modelo.Producto;
import consultorio.modelo.Servcios_Productos;
import consultorio.modelo.Servicio;
import consultorio.swing.abm.FormularioServicio;
import consultorio.swing.abm.ListaServicios;
import consultorio.swing.componentes.ComBasicos;
import consultorio.util.NumberUtil;

public class ControlServicio implements AcionesBasicas {
	
	private FormularioServicio form;
	private ListaServicios lServ;
	private Servicio servicios;
	private ServicioDao dao;
	private List<JTextField> listaTf;
	private ProductoDao daoProdu;
	private ServicioProductoDao daoItem;
	private List<Producto> listaProduc; 
	private List<Producto> listaProduc2;
	private List<Servcios_Productos> listaItem;
	private Producto producto;
	private Servcios_Productos servPro;
	private List<Servcios_Productos> detalleServ;
	
	
	public ControlServicio(FormularioServicio formPro, ListaServicios lForm) {
		if(formPro == null){
			System.out.println("Entra 1ro");
			this.lServ = lForm;
			this.lServ.getHerramientasGenericas().setAccionesBsicas(this);
		} else /*if((lForm != null) || (formPro != null) || (form))*/{
			this.form = formPro;
			this.form.getGrupoBotonServicio().setAccionesBsicas(this);
			recuperarProductos();
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
				ListaServicios.recuperarTodo();
				
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
		recuperarProductos();
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
		recuperarProductos();
	}
	
	private void estadoInicial(boolean b){
		form.getTfTipoNombre().setEnabled(b);
		form.getTfCostoServ().setEnabled(b);
		form.getTfcostoFi().setEnabled(b);
		form.getTfobs().setEnabled(b);
		form.getGrupoBotonServicio().getTable().setEnabled(b);
		form.getGrupoBotonServicio().getBtnGuardar().setEnabled(b);
		form.getGrupoBotonServicio().getBtnCancelar().setEnabled(b);
		form.getGrupoBotonServicio().getBtnNuevo().setEnabled(b);
		form.getGrupoBotonServicio().getBtnNuevo().setEnabled(!b);
	}	

	private void cargaDatos(){
		servicios = new Servicio();
		servicios.setCodigoSer(Integer.parseInt(form.getLblCodigo().getText()));
		servicios.setNombreServicio(form.getTfTipoNombre().getText());
		servicios.setPrecio(NumberUtil.getValorInt(form.getTfCostoServ().getText()));
		servicios.setObs(form.getTfobs().getText());
		
		//Cargar Lista de detalles y objeto servicio para posteriormente guardar(Cargando productos y servicio)
		
		cargarDetalles();
		//servicios.setProducto(producto);
		servicios.setServicioProducto(detalleServ);
	}
	
	private void cargarDetalles(){
		detalleServ = new ArrayList<>();
		
		recuperarSoloProducto();
		recuperarItem(); 
		for (int i = 0; i < listaProduc2.size(); i++) {
			
			for (int j = 0; j < listaItem.size(); j++) {
				
				if((form.getGrupoBotonServicio().getmServProdu().recuperarEstado(i)) == true &&
						(servicios.getCodigoSer() != listaItem.get(j).getServicio().getCodigoSer())&&
						(listaProduc2.get(i).getId() != listaItem.get(j).getProducto().getId())){
					servPro = new Servcios_Productos();
					producto = listaProduc2.get(i);
					System.out.println("Producto sumado a la lista "+producto.getNombre());
					servPro.setServicio(servicios);
					servPro.setProducto(producto);
					detalleServ.add(servPro);
				}
				
			}	
			
		}
		
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
			recuperarProductos();
		}

		/// Modifica
		if ((form.getServRecibido() != null) && (form.getvRe() == 2)) {
			System.out.println(form.getServRecibido().getNombreServicio()+ "Modifica");
			cargar();
			estadoInicial(true);
			
		}

		/// Visualiza
		if ((form.getSerRecibido()!= null) && (form.getvRe() == 3)) {
			cargar();
			estadoInicial(false);
			form.getGrupoBotonServicio().getBtnModificar().setEnabled(true);
		}

	}
	
	private void cargar(){
		if (form.getSerRecibido() != null) {
			double a, b;
			a = form.getSerRecibido().getPrecio();
			
			b = 0;
			form.getLblCodigo().setText(form.getSerRecibido().getCodigoSer()+"");
			form.getTfTipoNombre().setText(form.getSerRecibido().getNombreServicio());
			form.getTfCostoServ().setText(form.getSerRecibido().getCodigoSer()+ "");
			form.getTfcostoFi().setText(a+b +"");
			form.getTfobs().setText(form.getSerRecibido().getObs());
			recuperarTodo();
		
		}
	}
	private void recuperarTodo(){
		recuperarSoloProducto();
		recuperarItem();
		if(!listaItem.isEmpty()){
			for (int i = 0; i < listaProduc2.size(); i++) {
				
				for (int j = 0; j < listaItem.size(); j++) {
					
					if(form.getSerRecibido().getCodigoSer() == listaItem.get(j).getServicio().getCodigoSer()){
						
						if(listaProduc2.get(i).getId() == listaItem.get(j).getProducto().getId()){
							listaProduc2.get(i).setB(true); 
						}
						
					}
				}
				
			}
		}
		form.getGrupoBotonServicio().getmServProdu().setListaAndItems(listaProduc2);
		form.getGrupoBotonServicio().getmServProdu().fireTableDataChanged();
	}
	
	private void recuperarProductos(){
		daoProdu = new ProductoDao();
		listaProduc = daoProdu.recuperarTodo();		
		form.getGrupoBotonServicio().getmServProdu().setListaAndItems(listaProduc);
		form.getGrupoBotonServicio().getmServProdu().fireTableDataChanged();
	}
	
	private void recuperarItem(){
		int v = form.getSerRecibido().getCodigoSer();
		daoItem = new ServicioProductoDao();
		listaItem = daoItem.recuperar(v);
		for (int i = 0; i < listaItem.size(); i++) {
			System.out.println(listaItem.get(i).getProducto().getNombre());
		}
	}
	
	// Busca en la tabla producctos sin hacer update en la tabla
	private void recuperarSoloProducto(){
		daoProdu = new ProductoDao();
		listaProduc2 = daoProdu.recuperarTodo();
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
