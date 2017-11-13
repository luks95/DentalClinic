package consultorio.daos;

import java.util.List;

import consultorio.modelo.Login;

public class LoginDao extends DaoGenerico<Login> {

	public LoginDao() {
		super(Login.class);
	}

	public List<Login> recuperarPorFiltro(String filtro) {

		instanciarCriteria();
		criteriaQuery.where(
				builder.or(builder.like(builder.lower(root.<String>get("nombre")), "%" + filtro.toLowerCase() + "%")));
		lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		System.out.println(lista);

		return lista;
	}

}
