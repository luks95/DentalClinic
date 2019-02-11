package consultorio.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:28.007-0300")
@StaticMetamodel(Paciente.class)
public class Paciente_ {
	public static volatile SingularAttribute<Paciente, Integer> codigoCli;
	public static volatile SingularAttribute<Paciente, String> nombre;
	public static volatile SingularAttribute<Paciente, String> apellido;
	public static volatile SingularAttribute<Paciente, String> doc;
	public static volatile SingularAttribute<Paciente, String> direccion;
	public static volatile SingularAttribute<Paciente, String> telefono;
	public static volatile SingularAttribute<Paciente, Date> fechaNac;
	public static volatile SingularAttribute<Paciente, String> email;
	public static volatile SingularAttribute<Paciente, byte[]> imagen;
	public static volatile SingularAttribute<Paciente, String> Obs;
}
