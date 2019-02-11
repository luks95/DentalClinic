package consultorio.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:27.734-0300")
@StaticMetamodel(Cita.class)
public class Cita_ {
	public static volatile SingularAttribute<Cita, Integer> id;
	public static volatile SingularAttribute<Cita, Date> fechaCita;
	public static volatile SingularAttribute<Cita, Date> hora;
	public static volatile SingularAttribute<Cita, Integer> estado;
	public static volatile SingularAttribute<Cita, String> obs;
	public static volatile SingularAttribute<Cita, Paciente> paciente;
	public static volatile SingularAttribute<Cita, Doctor> doctor;
}
