package com.bcm.midia.negocio.interfaces;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;



/**
 * Interface contendo operações de consulta e manutenção ao banco de dados
 * 
 * @param <T>
 *            Entidade sobre a qual serão realizadas as operações de consulta e
 *            manutenção.
 */
public interface BaseService<T> extends Serializable {

	/**
	 * Recupera a entidade a partir de seu identificador
	 * 
	 * @param id
	 *            O identificador da entidade
	 * @return A entidade com seus dados preenchidos a partir do banco de dados
	 * @throws BusinessException
	 */
	T findById(Long id) throws Exception;

	/**
	 * Consulta as entidades que correspondam aos filtros preenchidos na
	 * entidade passada como parâmetro.
	 * 
	 * @param entidade
	 *            Entidade com os dados de filtro para a consulta preenchidos.
	 * @return Lista de entidades que possuem os dados correspondentes aos do
	 *         filtro passado como parâmetro.
	 * @throws BusinessException
	 */
	List<T> consultar(T entidade) throws Exception;

	/**
	 * Consulta as entidades que correspondam aos filtros preenchidos na
	 * entidade passada como parâmetro sobre demanda conforme parâmentros
	 * informados.
	 * 
	 * @param first
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @param filters
	 * @param objFiltro
	 *            Entidade com os dados de filtro para a consulta preenchidos.
	 * @return Lista de entidades que possuem os dados correspondentes aos do
	 *         filtro passado como parâmetro.
	 * @throws BusinessException
	 */
	List<T> consultarComPaginacaoSobreDemanda(int first, int pageSize, String sortField, Map<String, Object> filters,
			T objFiltro) throws Exception;

	/**
	 * Método responsável por retornar a quantidade de registros, utilizada na
	 * paginação sobre demanda
	 * 
	 * @param filters
	 * @param objFiltro
	 * @return
	 */
	Long countParaPaginacaoSobreDemanda(Map<String, Object> filters, T objFiltro);

	/**
	 * Lista todas as entidades cadastradas na tabela que reprensenta a entidade
	 * anotada nesta interface.
	 * 
	 * @return Coleção contendo todas as entidades cadastradas na tabela que
	 *         reprensenta a entidade anotada nesta interface.
	 * @throws BusinessException
	 */
	List<T> findAll() throws Exception;

	/**
	 * Insere a entidade no banco de dados.
	 * 
	 * @param entidade
	 *            Entidade a ser inserida.
	 * @return A entidade que está persistida no banco.
	 * @throws BusinessException
	 * @throws RequiredException
	 */
	T save(T entity) throws RequiredException, BusinessException, Exception;

	/**
	 * Atualiza os valores da entidade no banco de dados.
	 * 
	 * @param entidade
	 *            Entidade que terá os valores atualizados.
	 * @return A entidade que está persistida no banco.
	 * @throws BusinessException
	 * @throws RequiredException
	 */
	T update(T entity) throws RequiredException, BusinessException, Exception;

	/**
	 * Atualiza os valores de uma lista de objetos de determinada entidade.
	 * 
	 * @param listEntity
	 * @return Retorna a lista das entidades atualizadas.
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	List<T> update(Collection<T> listEntity) throws RequiredException, BusinessException, Exception;

	/**
	 * Exclui a entidade do banco de dados.
	 * 
	 * @param entidade
	 *            Entidade a ser excluída.
	 * @throws BusinessException
	 * @throws RequiredException
	 */
	void remove(T entity) throws BusinessException, Exception;

	/**
	 * Atualiza a entidade com os dados que estão salvos no banco
	 * 
	 * @param entity
	 * @throws Exception
	 */
	void refresh(T entity) throws Exception;

	void flush();

}
