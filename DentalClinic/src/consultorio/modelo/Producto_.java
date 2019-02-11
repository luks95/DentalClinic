package consultorio.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:28.074-0300")
@StaticMetamodel(Producto.class)
public class Producto_ {
	public static volatile SingularAttribute<Producto, Integer> id;
	public static volatile SingularAttribute<Producto, String> nombre;
	public static volatile SingularAttribute<Producto, Double> PrecioCosto;
	public static volatile SingularAttribute<Producto, String> caracteris;
	public static volatile SingularAttribute<Producto, Double> porcentaje;
	public static volatile ListAttribute<Producto, Servicio> servicio;
	public static volatile ListAttribute<Producto, Servcios_Productos> servicioProducto;
}
