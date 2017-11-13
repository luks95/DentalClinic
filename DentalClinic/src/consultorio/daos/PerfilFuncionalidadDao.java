package consultorio.daos;

import java.util.List;

import javax.persistence.criteria.Join;

import consultorio.modelo.Perfil_Funcionalidad;

public class PerfilFuncionalidadDao extends DaoGenerico<Perfil_Funcionalidad> {

	public PerfilFuncionalidadDao() {
		super(Perfil_Funcionalidad.class);
	}
	public List<Perfil_Funcionalidad> recuperar(Integer f){
		instanciarCriteria();
		Join<Object, Object> joinPerfiles=root.join("perfil");
		criteriaQuery.where(builder.equal(joinPerfiles.<Integer>get("id"), f));
		lista =  session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;
		
	}
}
