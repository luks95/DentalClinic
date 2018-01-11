package consultorio.modelo;

import java.io.Serializable;

import java.lang.String;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Funcionaliad
 *
 */
@Entity

public class Funcionalidad implements Serializable {

	@Id

	private int CodigoFunciones;

	private String nombreFucion;

	private Date fechaReg;

	@OneToMany(mappedBy = "funcionalidad")
	private List<Perfil> perfil;

	@OneToMany(mappedBy = "funcionalidad")
	private List<Perfil_Funcionalidad> perfilFunciones;

	private static final long serialVersionUID = 1L;

	public Funcionalidad() {
		super();
	}

	public int getCodigoFunciones() {
		return this.CodigoFunciones;
	}

	public void setCodigoFunciones(int CodigoFunciones) {
		this.CodigoFunciones = CodigoFunciones;
	}

	public String getNombreFucion() {
		return this.nombreFucion;
	}

	public void setNombreFucion(String nombreFucion) {
		this.nombreFucion = nombreFucion;
	}

	public Date getFechaReg() {
		return this.fechaReg;
	}

	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}

}
