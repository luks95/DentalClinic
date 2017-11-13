package consultorio.daos;

import java.util.List;

import consultorio.modelo.Servicio;


public class ServicioDao extends DaoGenerico<Servicio> {

	public ServicioDao() {
		super(Servicio.class);
	}

	public List<Servicio> recuperarPorFiltro(String filtro) {
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
