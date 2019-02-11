package consultorio.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:27.977-0300")
@StaticMetamodel(Login.class)
public class Login_ {
	public static volatile SingularAttribute<Login, Integer> login_codigo;
	public static volatile SingularAttribute<Login, Date> login_fecha;
	public static volatile SingularAttribute<Login, Date> out_fecha;
	public static volatile SingularAttribute<Login, Usuario> usuario;
}
