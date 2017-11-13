package consultorio.daos;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SharedSessionContract;
import org.hibernate.criterion.Projections;

import consultorio.util.Factory;

public class DaoGenerico<T> {
	protected Session session;
	private Class<T> entity;
	protected CriteriaBuilder builder;
	protected CriteriaQuery<T> criteriaQuery;
	protected Root<T> root;
	protected List<T> lista;

	public DaoGenerico(Class<T> entity) {
		this.entity = entity;
		session = Factory.getSessionFactory().openSession();
		// builder = session.getCriteriaBuilder();
		// criteriaQuery = builder.createQuery(entity);
		// root = criteriaQuery.from( entity );
		// criteriaQuery.select( root );

	}

	public int maxCodigo() {
		@SuppressWarnings("deprecation")
		Object id = session.createCriteria(entity).setProjection(Projections.max("id")).uniqueResult();
		if (id == null) {
			return 0;
		} else {
			return (int) id;
		}
	}

	public void insertarOModificar(T entity) {
		session.beginTransaction();
		session.saveOrUpdate(entity);
	}

	public void eliminar(T entity) {
		session.beginTransaction();
		session.delete(entity);
	}

	public T recuperarPorId(int id) {
		T result = session.get(entity, id);
		cerrar();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> recuperarTodo() {
		// consulta utilizando hibernate query lenguaje HQL
		String hql = "FROM " + entity.getName() + " ORDER BY id desc";
		Query query = session.createQuery(hql);
		lista = query.getResultList();

		// criteriaQuery.orderBy(builder.asc(root.get("id")));
		// lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> recuperarTodoAsc() {
		// consulta utilizando hibernate query lenguaje HQL
		String hql = "FROM " + entity.getName() + " ORDER BY id asc";
		Query query = session.createQuery(hql);
		lista = query.getResultList();

		// criteriaQuery.orderBy(builder.asc(root.get("id")));
		// lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;
	}

	public void ejecutar() throws Exception {
		session.flush();
		session.getTransaction().commit();
		cerrar();
	}

	public void rollback() {
		session.getTransaction().rollback();
		session.clear();
		cerrar();
	}

	public void cerrar() {
		session.close();
	}

	protected void instanciarCriteria() {

		// instanciar los objetos necesarios para utilizar criteria
		builder = session.getCriteriaBuilder();
		criteriaQuery = builder.createQuery(entity);
		root = criteriaQuery.from(entity);
		criteriaQuery.select(root);
	}

	public void eliminarTodos(String tabla) {
		session.getTransaction().begin();

		String deleteAll = "TRUNCATE table " + tabla + " cascade";
		Query query = session.createNativeQuery(deleteAll);
		query.executeUpdate();
	}

	@SuppressWarnings("unused")
	private SharedSessionContract getEntityManager() {

		return null;
	}

}
