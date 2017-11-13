package consultorio.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Servicio
 *
 */
@Entity

public class Servicio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int codigoSer;
	private String nombreServicio;
	private int precio;
	private String obs;

	public Servicio() {
		super();
	}

	public int getCodigoSer() {
		return codigoSer;
	}

	public void setCodigoSer(int codigoSer) {
		this.codigoSer = codigoSer;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
