package consultorio.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Servcios_Productos
 *
 */
@Entity

public class Servcios_Productos implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Servicio servicio;
	
	@ManyToOne
	private Producto producto;

	public Servcios_Productos() {
		super();
	}   
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
   
}
