package consultorio.modelo;

import java.io.Serializable;

import java.lang.Double;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cambio
 *
 */
@Entity

public class Cambio implements Serializable {

	@Id
	private Integer codigoCambio;

	private Double cambioCompra;

	private Double cambioVenta;

	@OneToOne
	private Moneda moneda;

	private static final long serialVersionUID = 1L;

	public Cambio() {
		super();
	}

	public Integer getCodigoCambio() {
		return this.codigoCambio;
	}

	public void setCodigoCambio(Integer codigoCambio) {
		this.codigoCambio = codigoCambio;
	}

	public Double getCambioCompra() {
		return this.cambioCompra;
	}

	public void setCambioCompra(Double cambioCompra) {
		this.cambioCompra = cambioCompra;
	}

	public Double getCambioVenta() {
		return this.cambioVenta;
	}

	public void setCambioVenta(Double cambioVenta) {
		this.cambioVenta = cambioVenta;
	}

}
