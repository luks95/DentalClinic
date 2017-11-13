
package consultorio.daos;

import java.util.List;

import consultorio.modelo.Cita;

public class CitaDao extends DaoGenerico<Cita> {

	public CitaDao() {
		super(Cita.class);
	}

	public List<Cita> recuperarPorFiltro(String filtro) {
		int filtroId = 0;

		try {
			filtroId = Integer.parseInt(filtro);
		} catch (Exception e) {
		}

		instanciarCriteria();
		criteriaQuery.where(
				builder.or(
						builder.equal(root.<Integer>get("id"), filtroId)));
		lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		System.out.println(lista);
		return lista;

	}

}
