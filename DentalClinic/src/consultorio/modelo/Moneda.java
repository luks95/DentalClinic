package consultorio.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Moneda
 *
 */
@Entity

public class Moneda implements Serializable {

	@Id
	private Integer codigoMoneda;

	private String nombre;

	private String pais;

	private static final long serialVersionUID = 1L;

	public Moneda() {
		super();
	}

	public Integer getCodigoMoneda() {
		return this.codigoMoneda;
	}

	public void setCodigoMoneda(Integer codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
