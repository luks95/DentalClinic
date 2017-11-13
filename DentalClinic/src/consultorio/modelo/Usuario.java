package consultorio.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity

public class Usuario implements Serializable {

	@Id

	@Column(name = "usu_codigo")
	private Integer codigoUsuario;

	@Column(name = "usu_nombre")
	private String nombre;

	@Column(name = "usu_apellido")
	private String apellido;

	@Column(name = "usu_passworld", length = 40)
	private String passworld;

	@Column(name = "usu_fecha")
	private Date fechaRegistro;

	@OneToOne
	private Perfil perfil;

	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}

	public Integer getCodigoUsuario() {
		return this.codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassworld() {
		return this.passworld;
	}

	public void setPassworld(String passworld) {
		this.passworld = passworld;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
