package consultorio.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Login
 *
 */
@Entity

public class Login implements Serializable {

	@Id
	@Column(name = "log_codigo")
	private Integer login_codigo;

	@Column(name = "log_fecha_hora")
	private Date login_fecha;

	@Column(name = "out_fecha_hora")
	private Date out_fecha;

	@OneToOne
	private Usuario usuario;

	private static final long serialVersionUID = 1L;

	public Login() {
		super();

	}

	public Integer getLogin_codigo() {
		return this.login_codigo;
	}

	public void setLogin_codigo(Integer login_codigo) {
		this.login_codigo = login_codigo;
	}

	public Date getLog_fecha() {
		return this.login_fecha;
	}

	public void setLog_fecha(Date log_fecha) {
		this.login_fecha = log_fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getOut_fecha() {
		return out_fecha;
	}

	public void setOut_fecha(Date out_fecha) {
		this.out_fecha = out_fecha;
	}

}
