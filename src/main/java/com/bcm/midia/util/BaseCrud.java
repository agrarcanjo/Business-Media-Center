package com.bcm.midia.util;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;

/**
 * Classe base para CRUD de classes que utilizam
 * o ViewScope.
 * 
 * 
 * @param <T> Parametro tipado
 */
public abstract class BaseCrud<T> extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1138353742812539269L;

	private T instance;
	
	private Long id;

	public BaseCrud() {
	}


	public T getInstance() {
		if (instance == null) {
			if (getId() != null) {
				instance = loadInstance();
			} else {
				instance = newInstance();
			}
		}
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * Criacao da nova instancia
	 * 
	 * @return
	 */
	protected abstract T newInstance();

	/**
	 * Lista todas os objetos da classe
	 * 
	 * @return
	 */
	protected abstract List<T> getAll();
	
	protected abstract T load(Long id);

	/**
	 * Finaliza a edicao de um registro Normalmente esse metodo deve ser
	 * invocado chamando um metodo de atualizacao na entidade(update)
	 * 
	 * @return
	 */
	protected abstract void updateImpl(T referenceValue) throws RequiredException, BusinessException, Exception;

	/**
	 * Finaliza a criacao de um registro Normalmente esse metodo deve ser
	 * invocado chamando um metodo de criacao na entidade(insert)
	 * 
	 * @return
	 */
	protected abstract void saveImpl(T referenceValue) throws RequiredException, BusinessException, Exception;

	/**
	 * Finaliza a remocao de um registro Normalmente esse metodo deve ser
	 * invocado chamando um metodo de remocao na entidade(delete)
	 * 
	 * @return
	 */
	protected abstract void deleteImpl(T referenceValue) throws BusinessException, Exception;

	// =======================================================================================================
	// Domain methods
	// =======================================================================================================

	public T loadInstance() {
		return load(getId());
	}
	
	public List<T> allInstance() {
		return getAll();
	}
	
	/**
	 * Método utilizado para limpar o formulario na tela de consulta.
	 */
	protected void limparForm() {
		instance = newInstance();
	}
	
	/**
	 * Atualiza uma entidade
	 * 
	 * @return
	 */
	public String update() {
		try {
			updateImpl(getInstance());
			limparForm();
			return includeRedirect("consulta");
		} catch (BusinessException be) {
			facesMessager.warn(Util.formatBusinessMessage(be));
			return "";
		} catch (Exception e) {
			getRootErrorMessage(e);
			return "";
		}
	}

	/**
	 * Persiste ou atualiza uma instancia na base de dados.
	 * 
	 * @return
	 */
	public String save() {
		try {
			saveImpl(getInstance());
			limparForm();
			return includeRedirect("consulta");
		} catch (BusinessException be) {
			facesMessager.warn(Util.formatBusinessMessage(be));
			return "";
		} catch (Exception e) {
			getRootErrorMessage(e);
			return "";
		}
	}

	/**
	 * Remove uma entidade
	 * 
	 * @return
	 */
	public void delete(ActionEvent event) {
		try {
			deleteImpl(instance);
			limparForm();
		} catch (BusinessException be) {
			facesMessager.warn(Util.formatBusinessMessage(be));
		} catch (Exception e) {
			getRootErrorMessage(e);
		}
	}
}
