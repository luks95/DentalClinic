package consultorio.daos;

import java.util.List;

import consultorio.modelo.Paciente;

public class PacienteDao extends DaoGenerico<Paciente> {

	public PacienteDao() {
		super(Paciente.class);
	}

	public List<Paciente> recuperarPorFiltro(String filtro) {
		int filtroId = 0;

		try {
			filtroId = Integer.parseInt(filtro);
		} catch (Exception e) {
		}

		instanciarCriteria();
		criteriaQuery.where(
				builder.or(builder.like(builder.lower(root.<String>get("nombre")), "%" + filtro.toLowerCase() + "%"),
						builder.like(builder.lower(root.<String>get("apellido")), "%" + filtro.toLowerCase() + "%"),
						builder.like(builder.lower(root.<String>get("doc")), "%" + filtro.toLowerCase() + "%"),
						builder.equal(root.<Integer>get("codigoCli"), filtroId)));
		lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		System.out.println(lista);
		return lista;

	}

}
