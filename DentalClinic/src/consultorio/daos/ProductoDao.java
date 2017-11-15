package consultorio.daos;

import java.util.List;

import consultorio.modelo.Producto;


public class ProductoDao extends DaoGenerico<Producto> {

	public ProductoDao() {
		super(Producto.class);
	}

	public List<Producto> recuperarPorFiltro(String filtro) {
		int filtroId = 0;

		try {
			filtroId = Integer.parseInt(filtro);
		} catch (Exception e) {
		}

		instanciarCriteria();
		criteriaQuery.where(
				builder.or(builder.like(builder.lower(root.<String>get("nombreServicio")), "%" + filtro.toLowerCase() + "%"),
						builder.equal(root.<Integer>get("codigoSer"), filtroId)));
		lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		System.out.println(lista);
		return lista;

	}

}
