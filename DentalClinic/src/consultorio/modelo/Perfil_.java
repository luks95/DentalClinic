package consultorio.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:28.027-0300")
@StaticMetamodel(Perfil.class)
public class Perfil_ {
	public static volatile SingularAttribute<Perfil, Integer> id;
	public static volatile SingularAttribute<Perfil, String> perfilNombre;
	public static volatile ListAttribute<Perfil, Perfil_Funcionalidad> perfilDetalle;
	public static volatile SingularAttribute<Perfil, Funcionalidad> funcionalidad;
}
