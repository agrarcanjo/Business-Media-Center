package com.bcm.midia.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.bcm.midia.database.dao.AbstractDAO;
import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;
import com.bcm.midia.util.Util;


public abstract class BussinessAbstract<T> {

	@Inject
	protected Logger logger;

	protected List<String> mensagens;


	/**
	 * Valida os campos obrigatórios
	 * 
	 * @param entity
	 */
	protected abstract void validaCamposObrigatorios(T entity);

	/**
	 * Valida as regras de negocio
	 * @param entity
	 */
	protected abstract void validaRegras(T entity);

	
	/**
	 * Valida as regras de negocio antes de excluir
	 * @param entity
	 */
	protected abstract void validaRegrasExcluir(T entity);
	
	/**
	 * Instancia do dao generico
	 * @return
	 */
	protected abstract AbstractDAO<T> getDAO();
	
	/**
	 * Utilizado para verificar quando esta editando.
	 * 
	 */
	private boolean editando;
	
	/**
	 * Retorna todos do banco.
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return getDAO().findAll();
	}

	/**
	 * Retorna a entidade por ID.
	 * 
	 * @param id
	 * @return
	 */
	public T findById(Long id) {
		return getDAO().findById(id);
	}
	

	/**
	 * Chamado pelo controller para salvar alguma entidade.
	 * 
	 * @param entity
	 * @return Retorna a entidade salva.
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public T save(T entity) throws RequiredException, BusinessException, Exception {
		processValidations(entity, false);
		return saveImpl(entity);
	}

	/**
	 * Método que salva uma entidade. Se necessário, ele será sobrescrito para realizar algo antes e/ou depois da operação de save.
	 * 
	 * @param entity
	 * @return
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	protected T saveImpl(T entity) throws RequiredException, BusinessException, Exception {
		getDAO().save(entity);
		logger.info("Persistindo " + entity.getClass().getSimpleName() + " -> " + entity.toString());
		return entity;
	}

	/**
	 * Chamado pelo controller para atualizar alguma entidade.
	 * 
	 * @param entity
	 * @return Retorna a entidade atualizada.
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public T update(T entity) throws RequiredException, BusinessException, Exception {
		processValidations(entity, true);
		return updateImpl(entity);
	}

	/**
	 * Método que atualiza uma entidade. Se necessário, ele será sobrescrito para realizar algo antes e/ou depois da operação de update.
	 * 
	 * @param entity
	 * @return
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	protected T updateImpl(T entity) throws RequiredException, BusinessException, Exception {
		getDAO().update(entity);
		logger.info("Atualizando " + entity.getClass().getSimpleName() + " -> " + entity.toString());
		return entity;
	}

	

	/**
	 * Chamado pelo controller para remover alguma entidade.
	 * 
	 * @param entity
	 * @throws BusinessException
	 * @throws Exception
	 */
	public void remove(T entity) throws BusinessException, Exception {
		processDeleteValidations(entity);
		removeImpl(entity);
	}

	/**
	 * Método que remove uma entidade. Se necessário, ele será sobrescrito para realizar algo antes e/ou depois da operação de delete.
	 * 
	 * @param entity
	 * @throws BusinessException
	 * @throws Exception
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	protected void removeImpl(T entity) throws BusinessException, Exception {
		getDAO().delete(entity);
		logger.info("Removendo " + entity.getClass().getSimpleName() + " -> " + entity.toString());
	}


	/**
	 * Processa todas as validações implementadas no validaCamposObrigatorios e no validaRegras durante o save e o update.
	 * 
	 * @param entity
	 *            Entidade a ser validada.
	 * @param editando
	 *            Parametro que será usado nos métodos das RNs.
	 * @throws RequiredException
	 *             Quando algum campo obrigatório não foi preenchido.
	 * @throws BusinessException
	 *             Quando alguma RN não foi atendida.
	 */
	protected void processValidations(T entity, boolean editando) throws RequiredException, BusinessException {
		setEditando(editando);
		mensagens = new ArrayList<String>();

		validaCamposObrigatorios(entity);
		if (!Util.isNullOrEmpty(mensagens))
			throw new RequiredException(null, mensagens);
//		throw new RequiredException("Os seguintes campos obrigatórios devem ser preenchidos:", mensagens);

		validaRegras(entity);
		if (!Util.isNullOrEmpty(mensagens))
			throw new BusinessException(null, mensagens);
	}

	/**
	 * Processa todas as validações implementadas no validaCamposObrigatorios e no validaRegras durante o save e o update.
	 * 
	 * @param listEntity
	 *            lista da Entidade a ser validada.
	 * @param editando
	 *            Parametro que será usado nos métodos das RNs.
	 * @throws RequiredException
	 *             Quando algum campo obrigatório não foi preenchido.
	 * @throws BusinessException
	 *             Quando alguma RN não foi atendida.
	 */
	protected void processValidations(Collection<T> listEntity, boolean editando) throws RequiredException, BusinessException {
		setEditando(editando);
		mensagens = new ArrayList<String>();

		for (T entity : listEntity) {
			validaCamposObrigatorios(entity);
		}
		if (!Util.isNullOrEmpty(mensagens))
			throw new RequiredException("Os seguintes campos obrigatórios devem ser preenchidos:", mensagens);

		for (T entity : listEntity) {
			validaRegras(entity);
		}
		if (!Util.isNullOrEmpty(mensagens))
			throw new BusinessException("Regras de negócio não atendidas.", mensagens);
	}

	/**
	 * Processa as RNs implementadas no validaRegrasExcluir durante o remove.
	 * 
	 * @param entity
	 *            Entidade a ser validada.
	 * @throws BusinessException
	 *             Se alguma regra não foi atendida.
	 */
	protected void processDeleteValidations(T entity) throws BusinessException {
		mensagens = new ArrayList<String>();
		validaRegrasExcluir(entity);
		if (!Util.isNullOrEmpty(mensagens))
			throw new BusinessException("Regras de negócio não atendidas.", mensagens);
	}

	
	
	
	
	protected boolean isEditando() {
		return editando;
	}

	protected void setEditando(boolean editando) {
		this.editando = editando;
	}

	
	
}
