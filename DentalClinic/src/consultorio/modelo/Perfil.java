package consultorio.modelo;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario_perfiles
 *
 */
@Entity

public class Perfil implements Serializable {

	@Id
	@Column(name = "perf_codigo")
	private int id;

	@Column(name = "perf_nombre")
	private String perfilNombre;

	@OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Perfil_Funcionalidad> perfilDetalle;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Funcionalidad funcionalidad;

	private static final long serialVersionUID = 1L;

	public Perfil() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPerfilNombre() {
		return perfilNombre;
	}

	public void setPerfilNombre(String perfilNombre) {
		this.perfilNombre = perfilNombre;
	}

	public List<Perfil_Funcionalidad> getPerfilDetalle() {
		return this.perfilDetalle;
	}

	public void setPerfilDetalle(List<Perfil_Funcionalidad> perfilDetalle) {
		this.perfilDetalle = perfilDetalle;
	}

}
