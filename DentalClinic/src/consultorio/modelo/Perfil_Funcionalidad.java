package consultorio.modelo;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Perfil_Funcionalidad
 *
 */
@Entity

public class Perfil_Funcionalidad implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "perf_fun_codigo")
	private int id;

	@Column(name = "perf_Fun_reporte_basico")
	private Boolean reporteBasico;

	@Column(name = "perf_Fun_agragar")
	private Boolean agregar;

	@Column(name = "perf_Fun_modificar")
	private Boolean modificar;

	@Column(name = "perf_Fun_eliminar")
	private Boolean eliminar;

	@Column(name = "perf_Fun_reporte_complejo")
	private Boolean reporteComplejo;

	//////////////////////
	/////////////////////
	@ManyToOne
	@JoinColumn(name = "perf_fun_id_perfil")
	private Perfil perfil;

	@ManyToOne
	@JoinColumn(name = "perf_fun_id_funcionalidad")
	private Funcionalidad funcionalidad;
	/////////////////////////////
	////////////////////////////
	
	private static final long serialVersionUID = 1L;

	public Perfil_Funcionalidad() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Boolean getReporteBasico() {
		return reporteBasico;
	}

	public void setReporteBasico(Boolean reporteBasico) {
		this.reporteBasico = reporteBasico;
	}

	public Boolean getAgregar() {
		return agregar;
	}

	public void setAgregar(Boolean agregar) {
		this.agregar = agregar;
	}

	public Boolean getModificar() {
		return modificar;
	}

	public void setModificar(Boolean modificar) {
		this.modificar = modificar;
	}

	public Boolean getEliminar() {
		return eliminar;
	}

	public void setEliminar(Boolean eliminar) {
		this.eliminar = eliminar;
	}

	public Boolean getReporteComplejo() {
		return reporteComplejo;
	}

	public void setReporteComplejo(Boolean reporteComplejo) {
		this.reporteComplejo = reporteComplejo;
	}

	@Override
	public String toString() {
		return "Perfil_Funcionalidad [id=" + id + ", funcionalidad=" + funcionalidad + ", perfil=" + perfil
				+ ", reporteBasico=" + reporteBasico + ", agregar=" + agregar + ", modificar=" + modificar
				+ ", eliminar=" + eliminar + ", reporteComplejo=" + reporteComplejo + "]";
	}

}
