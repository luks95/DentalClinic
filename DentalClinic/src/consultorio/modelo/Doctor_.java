package consultorio.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:27.753-0300")
@StaticMetamodel(Doctor.class)
public class Doctor_ {
	public static volatile SingularAttribute<Doctor, Integer> codigoDr;
	public static volatile SingularAttribute<Doctor, String> reg;
	public static volatile SingularAttribute<Doctor, String> nombre;
	public static volatile SingularAttribute<Doctor, String> apellido;
	public static volatile SingularAttribute<Doctor, Date> fechaNac;
	public static volatile SingularAttribute<Doctor, String> documento;
	public static volatile SingularAttribute<Doctor, String> telefono;
	public static volatile SingularAttribute<Doctor, String> direccion;
	public static volatile SingularAttribute<Doctor, String> email;
	public static volatile SingularAttribute<Doctor, String> especialidad;
	public static volatile SingularAttribute<Doctor, String> obs;
	public static volatile SingularAttribute<Doctor, byte[]> imagen;
}
