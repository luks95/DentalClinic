package consultorio.daos;

import java.util.List; 

import javax.persistence.criteria.Join;
import consultorio.modelo.Servcios_Productos;

public class ServicioProductoDao extends DaoGenerico<Servcios_Productos> {

	public ServicioProductoDao() {
		super(Servcios_Productos.class);
	}
	public List<Servcios_Productos> recuperar(Integer f){
		instanciarCriteria();
		Join<Object, Object> joinServicios=root.join("servicio");
		criteriaQuery.where(builder.equal(joinServicios.<Integer>get("codigoSer"), f));
		lista =  session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;
		
	}
}
