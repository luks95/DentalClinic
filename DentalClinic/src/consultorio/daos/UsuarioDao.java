package consultorio.daos;

import java.util.List;

import consultorio.modelo.Usuario;

public class UsuarioDao extends DaoGenerico<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}

	public List<Usuario> recuperarPorFiltro(String filtro) {

		instanciarCriteria();
		criteriaQuery.where(
				builder.or(builder.like(builder.lower(root.<String>get("nombre")), "%" + filtro.toLowerCase() + "%")));
		lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		System.out.println(lista);

		return lista;
	}

}
