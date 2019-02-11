package consultorio.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:27.945-0300")
@StaticMetamodel(Funcionalidad.class)
public class Funcionalidad_ {
	public static volatile SingularAttribute<Funcionalidad, Integer> CodigoFunciones;
	public static volatile SingularAttribute<Funcionalidad, String> nombreFucion;
	public static volatile SingularAttribute<Funcionalidad, Date> fechaReg;
	public static volatile ListAttribute<Funcionalidad, Perfil> perfil;
	public static volatile ListAttribute<Funcionalidad, Perfil_Funcionalidad> perfilFunciones;
}
