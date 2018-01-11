package consultorio.modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Productos
 *
 */
@Entity

public class Producto implements Serializable {

	   
	@Id
	private int id;
	private String nombre;
	private double PrecioCosto;
	private String caracteris;
	private double porcentaje;
	
	@Transient
	private boolean b;
	
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "producto")
	private List<Servicio> servicio;
	
	@OneToMany(mappedBy = "producto")
	private List<Servcios_Productos> servicioProducto;
	
	public Producto() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public double getPrecioCosto() {
		return this.PrecioCosto;
	}

	public void setPrecioCosto(double PrecioCosto) {
		this.PrecioCosto = PrecioCosto;
	}   
	public double getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getCaracteris() {
		return caracteris;
	}
	public void setCaracteris(String caracteris) {
		this.caracteris = caracteris;
	}
	public List<Servicio> getServicio() {
		return servicio;
	}
	public void setServicio(List<Servicio> servicio) {
		this.servicio = servicio;
	}
	public List<Servcios_Productos> getServicioProducto() {
		return servicioProducto;
	}
	public void setServicioProducto(List<Servcios_Productos> servicioProducto) {
		this.servicioProducto = servicioProducto;
	}
	public boolean getB() {
		return b;
	}
	public void setB(boolean c) {
		this.b = c;
	}
	
	
   
}
