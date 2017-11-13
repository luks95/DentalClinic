package consultorio.daos;

import java.util.List;

import consultorio.modelo.Doctor;

public class ProfecionalDao extends DaoGenerico<Doctor> {

	public ProfecionalDao() {
		super(Doctor.class);
	}

	public List<Doctor> recuperarPorFiltro(String filtro) {
		int filtroId = 0;

		try {
			filtroId = Integer.parseInt(filtro);
		} catch (Exception e) {
		}

		instanciarCriteria();
		criteriaQuery.where(
				builder.or(builder.like(builder.lower(root.<String>get("nombre")), "%" + filtro.toLowerCase() + "%"),
						builder.like(builder.lower(root.<String>get("apellido")), "%" + filtro.toLowerCase() + "%"),
						builder.like(builder.lower(root.<String>get("documento")), "%" + filtro.toLowerCase() + "%"),
						builder.equal(root.<Integer>get("codigoDr"), filtroId)));
		lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		System.out.println(lista);
		return lista;

	}

}
