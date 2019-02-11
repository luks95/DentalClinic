package consultorio.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:28.103-0300")
@StaticMetamodel(Servicio.class)
public class Servicio_ {
	public static volatile SingularAttribute<Servicio, Integer> codigoSer;
	public static volatile SingularAttribute<Servicio, String> nombreServicio;
	public static volatile SingularAttribute<Servicio, Integer> precio;
	public static volatile SingularAttribute<Servicio, String> obs;
	public static volatile ListAttribute<Servicio, Servcios_Productos> servicioProducto;
	public static volatile SingularAttribute<Servicio, Producto> producto;
}
