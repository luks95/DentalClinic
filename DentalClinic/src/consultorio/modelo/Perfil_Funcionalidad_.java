package consultorio.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:28.049-0300")
@StaticMetamodel(Perfil_Funcionalidad.class)
public class Perfil_Funcionalidad_ {
	public static volatile SingularAttribute<Perfil_Funcionalidad, Integer> id;
	public static volatile SingularAttribute<Perfil_Funcionalidad, Boolean> reporteBasico;
	public static volatile SingularAttribute<Perfil_Funcionalidad, Boolean> agregar;
	public static volatile SingularAttribute<Perfil_Funcionalidad, Boolean> modificar;
	public static volatile SingularAttribute<Perfil_Funcionalidad, Boolean> eliminar;
	public static volatile SingularAttribute<Perfil_Funcionalidad, Boolean> reporteComplejo;
	public static volatile SingularAttribute<Perfil_Funcionalidad, Perfil> perfil;
	public static volatile SingularAttribute<Perfil_Funcionalidad, Funcionalidad> funcionalidad;
}
