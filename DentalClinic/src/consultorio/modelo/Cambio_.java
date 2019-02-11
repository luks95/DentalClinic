package consultorio.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-10T21:23:27.714-0300")
@StaticMetamodel(Cambio.class)
public class Cambio_ {
	public static volatile SingularAttribute<Cambio, Integer> codigoCambio;
	public static volatile SingularAttribute<Cambio, Double> cambioCompra;
	public static volatile SingularAttribute<Cambio, Double> cambioVenta;
	public static volatile SingularAttribute<Cambio, Moneda> moneda;
}
