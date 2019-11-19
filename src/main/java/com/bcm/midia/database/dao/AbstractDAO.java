package com.bcm.midia.database.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * 
 * @author gotardo.soares -P2M
 * 
 *         Classe DAO Abstrata
 * 
 * @param <T>
 */
public abstract class AbstractDAO<T> implements BaseDAO<T> {

	private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	public Class<T> entityClass;

	@Inject
	protected EntityManager entityManager;
	
	/**
	 * Usado para criar a Session para o Criteria.
	 * @return
	 */
	protected Session getSession(){
		return entityManager.unwrap(Session.class);
	}

	
	
	/**
	 * Retorna o Objeto de Criteria já preenchido de acordo com a Entity
	 * 
	 * @author gotardo.soares - P2M
	 * @param entity
	 * @return
	 */
	public Criteria getCriteria(T entity){
		Criteria criteria = getSession().createCriteria(getEntityClass()); 
		return criteria;
				
	}
	
	/**
	 * @author gotardo.soares -P2M
	 * 
	 * @param id
	 *            - Campo primario da tabela
	 * @return
	 */
	public T findById(Object id) throws PersistenceException {
		T result = null;
		LOGGER.entering(AbstractDAO.class.getName(), "findByPrimaryKey");
		result = entityManager.find(getEntityClass(), id);

		return result;
	}

	
	/**
	 * Resposável por salvar na tabela.
	 * 
	 * @author gotardo.soares -P2M
	 * 
	 * @param entity
	 * @return 
	 */
	public T save(T entity) {
		this.entityManager.persist(entity);
		return entity;
	}

	
	/**
	 * Resposável por atualizar registro.
	 * 
	 * @author gotardo.soares -P2M
	 * 
	 * @param entity
	 * @return 
	 */
	public T update(T entity){
		this.entityManager.merge(entity);
		return entity;
	}

	/**
	 * Resposável por deletar registro.
	 * 
	 * @author gotardo.soares -P2M
	 * 
	 * @param entity
	 */
	public void delete(T entity){
		this.entityManager.remove(entity);
		
	}
	
	

	/**
	 * Responsável por realizar a consulta utilizando o campo primário da tabela.
	 * Retorna os objetos relacionados na consulta.
	 * 
	 * @author gotardo.soares -P2M
	 * 
	 * @param id
	 * 				-	Campo primário da tabela.
	 * @return
	 * 
	 * @throws PersistenceException
	 */
	public T findReference(Object id) throws PersistenceException {
		T result = null;
		LOGGER.entering(AbstractDAO.class.getName(), "findReference");
		result = entityManager.getReference(getEntityClass(), id);
		LOGGER.exiting(AbstractDAO.class.getName(), "findReference");
		return result;
	}

	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	public void commit() {
		entityManager.getTransaction().commit();
	}

	/**
	 * @author gotardo.soares -P2M
	 * 
	 *         Procura todos os registros
	 * 
	 * @param clazz
	 *            - Classe utilizada para busca.
	 * @param query
	 *            - Query a ser realizada.
	 * @param values
	 *            - Parametros da query.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(String query)
			throws PersistenceException {
		LOGGER.entering(AbstractDAO.class.getName(), "findAll");
		Query qr = this.prepareQuery(query, null);
		List<T> result = qr.getResultList();
		LOGGER.exiting(AbstractDAO.class.getName(), "findAll");
		return result;
	}

	/**
	 * @author gotardo.soares -P2M
	 * 
	 *         Procura todos os registros
	 * 
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll()
			throws PersistenceException {
		LOGGER.entering(AbstractDAO.class.getName(), "findAll");
		Query qr = this.prepareQuery("from " + getEntityClass().getName()  , null);
		List<T> result = qr.getResultList();
		LOGGER.exiting(AbstractDAO.class.getName(), "findAll");
		return result;
	}


	/**
	 * @author gotardo.soares -P2M
	 * 
	 *         Procura todos os registros obedecendo o criterio de pesquisa e
	 *         parametros.
	 * 
	 * @param maxResult
	 *            - Quantidade Maxima de Resultado.
	 * @param clazz
	 *            - Classe utilizada para busca.
	 * @param query
	 *            - Query a ser realizada.
	 * @param values
	 *            - Parametros utilizados na query.
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllWithParameters(Integer maxResult, String query,
			Object... values) throws PersistenceException {
		LOGGER.entering(AbstractDAO.class.getName(), "findAllWithParameters");
		Query qr = this.prepareQuery(query, values);
		qr.setMaxResults(maxResult);
		LOGGER.exiting(AbstractDAO.class.getName(), "findAllWithParameters");
		return qr.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllNativeQuery(String query,
			Object... values) throws PersistenceException {
		LOGGER.entering(AbstractDAO.class.getName(), "findAllWithParameters");
		Query qr = this.prepareNativeQuery(query, values);
		LOGGER.exiting(AbstractDAO.class.getName(), "findAllWithParameters");
		return qr.getResultList();
	}

	/**
	 * @author gotardo.soares -P2M
	 * 
	 *         Procura todos os registros obedecendo o criterio de pesquisa e
	 *         parametros.
	 * 
	 * @param clazz
	 *            - Classe utilizada para busca.
	 * @param query
	 *            - Query a ser realizada.
	 * @param values
	 *            - Parametros utilizados na query.
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllWithParameters(String query,
			Object... values) throws PersistenceException {
		LOGGER.entering(AbstractDAO.class.getName(), "findAllWithParameters");
		Query qr = this.prepareQuery(query, values);
		LOGGER.exiting(AbstractDAO.class.getName(), "findAllWithParameters");
		return qr.getResultList();
	}

	
	/**
	 * @author gotardo.soares -P2M
	 * 
	 *         Procura todos os registros
	 * 
	 * @param clazz
	 *            - Classe utilizada para busca.
	 * @param query
	 *            - Query a ser realizada.
	 * @param values
	 *            - Parametros da query.
	 * @return
	 * @throws Throwable 
	 */
	@SuppressWarnings("unchecked")
	public T findSingleResult(String query, Object... values) throws PersistenceException
			 {
		try{
			LOGGER.entering(AbstractDAO.class.getName(), "findSingleResult");
			Query qr = this.prepareQuery(query, values);
			T t = (T) qr.getSingleResult();
			LOGGER.exiting(AbstractDAO.class.getName(), "findSingleResult");
			return t;
		}catch (NoResultException e){
			return null;
		}
	}
	
	/**
	 * @author gotardo.soares -P2M
	 * 
	 *         Usado para preparar a query para executar a listagem ou inclusão
	 * 
	 * @param query
	 * @param values
	 * @param em
	 * @return
	 */
	private Query prepareQuery(String query, Object[] values)
			throws IllegalArgumentException {
		LOGGER.entering(AbstractDAO.class.getName(), "prepareQuery");
		Query qr = null;
		if (values != null) {
			qr = entityManager.createQuery(query, getEntityClass());
			for (int i = 0; i < values.length; i++) {
				Object object = values[i];
				qr.setParameter(i + 1, object);
			}
		} else {
			qr = entityManager.createQuery(query, getEntityClass());
		}
		LOGGER.exiting(AbstractDAO.class.getName(), "prepareQuery");
		return qr;
	}

	/**
	 * Prepara para executar uma query nativa
	 * 
	 * @param query
	 * @param values
	 * @return
	 * @throws IllegalArgumentException
	 */
	private Query prepareNativeQuery(String query, Object[] values)
			throws IllegalArgumentException {
		LOGGER.entering(AbstractDAO.class.getName(), "prepareNativeQuery");
		Query qr = null;
		if (values != null) {
			qr = entityManager.createNativeQuery(query, getEntityClass());
			for (int i = 0; i < values.length; i++) {
				Object object = values[i];
				qr.setParameter(i + 1, object);
			}
		} else {
			qr = entityManager.createNativeQuery(query, getEntityClass());
		}
		LOGGER.exiting(AbstractDAO.class.getName(), "prepareNativeQuery");
		return qr;
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		if (entityClass == null) {
			Type type = getClass().getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
			} else {
				throw new IllegalArgumentException(
						"Erro ao tentar obter o tipo de classe da entidade");
			}
		}

		return entityClass;
	}


}
