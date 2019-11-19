package com.bcm.midia.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.transaction.SystemException;

import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;


/**
 * Classe base para CRUD de paginas que envolvem um numero consideravel de submits, evitando problemas ocasionados pelo escopo de request
 * 
 * @param <T>
 */
public abstract class AbstractCRUD<T> extends BaseController implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1939523887905196564L;

	protected Long customConversationTimeout;

	@Inject
	private PrimeFacesUtil primeFacesUtil;

	private boolean visualizando = false;
	private boolean editando = false;
	private boolean fromResults = false;
	private boolean fromDetalhes = false;
	private boolean fromLista = false;

	private Long id;
	private String caminhoAlternativo;
	protected T instance;

	private T instanceConsulta;

	protected List<T> allInstance = new ArrayList<T>();

	/**
	 * Construtor default
	 */
	public AbstractCRUD() {
	}

	public T getInstance() {
		if (instance == null) {
			if (id != null) {
				instance = loadInstance();
			} else {
				instance = newInstance();
			}
		} else if ((id != null) && (!id.equals(getEntityId(instance)))) {
			instance = loadInstance();
		}
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}

	public T getInstanceConsulta() {
		if (instanceConsulta == null) {
			instanceConsulta = newInstance();
		}
		return instanceConsulta;
	}

	public void setInstanceConsulta(T instanceConsulta) {
		this.instanceConsulta = instanceConsulta;
	}

	/**
	 * Carrega a instancia da base de dados
	 * 
	 * @return
	 */
	public T loadInstance() {
		try {
			return load(getId());
		} catch (Exception e) {
			getRootErrorMessage(e);
		}
		return null;
	}

	/**
	 * Indica se a instancia e nova, ou uma ja existente
	 * 
	 * @return
	 */
	public boolean isManaged() {
		return getEntityId(instance) != null && getEntityId(instance) != 0;
	}

	/**
	 * Persiste ou atualiza uma instancia na base de dados.
	 * 
	 * @return
	 */
	public void save(ActionEvent event) {
		try {

			if (isManaged()) {
				updateImpl(getInstance());
			} else {
				saveImpl(getInstance());
			}

			atualizarOrigemCadastro("detalhar");
			reconsultaAllInstance();
		} catch (RequiredException re) {
			facesMessager.warn(Util.formatRequiredMessage(re));
		} catch (BusinessException be) {
			facesMessager.warn(Util.formatBusinessMessage(be));
		} catch (Exception e) {
			getRootErrorMessage(e);
			logErroMessage();
		}
	}

	public void salvar() throws BusinessException, Exception {
		try {
			
			if (isManaged()) {
				updateImpl(getInstance());
			} else {
				saveImpl(getInstance());
			}
			
//			atualizarOrigemCadastro("detalhar");
//			reconsultaAllInstance();
		} catch (RequiredException re) {
			facesMessager.warn(Util.formatRequiredMessage(re));
		} catch (BusinessException be) {
			facesMessager.warn(Util.formatBusinessMessage(be));
		} catch (Exception e) {
			getRootErrorMessage(e);
			logErroMessage();
		}
	}

	/**
	 * Remove uma instancia da base de dados.
	 * 
	 * @return
	 */
	public String delete() {
		try {
			deleteImpl(instance);
			removeInstanceInAllInstance();
			logger.info("Iniciando o limparForm()");
			limparForm();
			if (isEditando() || isVisualizando()) {
				return redirectTelaResultado();
			}
		} catch (BusinessException be) {
			facesMessager.warn(Util.formatBusinessMessage(be));
		} catch (Exception e) {
			getRootErrorMessage(e);
			logErroMessage();
		}

		return "";
	}

	/**
	 * Utilizado na consulta principal de um CRUD.
	 * 
	 * @return
	 */
	public String consultar() {
		try {
			consultarImpl();

			if (this.id != null && this.id > 0) {
				return this.redirectTelaCadastro("detalhar");
			} else {
				return redirectTelaResultado();
			}


		} catch (BusinessException be) {
			facesMessager.warn(Util.formatBusinessMessage(be));
		} catch (SystemException e) {
			facesMessager.warn(e.getMessage());
		} catch (Exception e) {
			getRootErrorMessage(e);
			logErroMessage();
		}
		return "";
	}

	protected void reconsultaAllInstance() {
		try {
			consultarImpl();
		} catch (Exception e) {
			getRootErrorMessage(e);
			e.printStackTrace();
		}
	}

	protected void removeInstanceInAllInstance() {
		allInstance.remove(instance);
	}



	protected List<T> getAll() {
		return allInstance;
	}

	public void start() {
		try {
			allInstance = loadAllInstance();
		} catch (Exception e) {
			getRootErrorMessage(e);
		}
	}


	/**
	 * @return
	 * 
	 *         Método que retorna o nome do header do panel dos parametros da consulta. Pode ser Consultar + o nome da entidade, ou quantidade de itens da lista
	 *         + Itens encontrados.
	 */
	public String getHeaderMainPanel() {
		return allInstance.size() > 0 ? allInstance.size() + " "
				+ (allInstance.size() == 1 ? MensagemUtil.obterMensagem("item.encontrado") : MensagemUtil.obterMensagem("itens.encontrados"))
				: MensagemUtil.obterMensagem("nenhum.item.encontrado");
	}


	/**
	 * Método utilizado para limpar a lista dos resultados na tela de consulta. Ao limpar a lista, a grade some e o parametros de consulta aparecem.
	 */
	protected void limparLista() {
		allInstance.clear();
	}

	/**
	 * Método utilizado para limpar o formulario.
	 */
	protected void limparForm() {
		instance = newInstance();
		setId(null);
	}

	/**
	 * Método utilizado para limpar o formulario na tela de consulta.
	 */
	protected void limparFormConsulta() {
		instanceConsulta = newInstance();
	}


	/**
	 * Redireciona para a tela de consulta informando se limpa o fomulario ou nao. true para volta da tela de cadastro. false para volta da tela de resultado.
	 * 
	 * @param clearInstance
	 * @return
	 */
	@Deprecated
	public String redirectTelaConsulta(boolean clearInstance) {
		return includeRedirect("consulta");
	}

	/**
	 * Redireciona para a rela de resultado
	 * 
	 * @return
	 */
	public String redirectTelaConsulta() {
		return includeRedirect("consulta");
	}

	/**
	 * Redireciona para a tela de resultado.
	 * 
	 * @return
	 */
	public String redirectTelaResultado() {
		fromLista = true;
		return includeRedirect("lista");
	}

	/**
	 * Redireciona para a tela de cadastro.
	 * 
	 * Se está visualizando ou editando, carrega os dados que precisam aparecer na tela e que não possuem campos na entidade.
	 * 
	 * Se não está visualizando nem editando, limpa os dados.
	 * 
	 * @return
	 */
	public String redirectTelaCadastro(String origem) {

		atualizarOrigemCadastro(origem);

		if (getId() != null) {
			try {
				setInstance(load(getId()));
				setId(null);
			} catch (Exception e) {
				getRootErrorMessage(e);
			}
		}

		if (!isVisualizando() && !isEditando()) {
			limparForm();
		}

		if (caminhoAlternativo != null) {
			return includeRedirect(caminhoAlternativo);
		}
		return includeRedirect("cadastro");
	}

	/**
	 * Responsavel por realizar o load para as dialogs
	 * 
	 * @param origem
	 */
	public void loadDialogs(String origem, String nomeDialog) {

		atualizarOrigemCadastro(origem);

		if (getId() != null) {
			try {
				setInstance(load(getId()));
				setId(null);
			} catch (Exception e) {
				getRootErrorMessage(e);
			}
		}

		if (!isVisualizando() && !isEditando()) {
			limparForm();
		}

		primeFacesUtil.showDialog(nomeDialog);

	}

	/**
	 * Atualiza os Booleanos que indicam de onde o usuário chamou o cadastro.
	 * 
	 * @param origem
	 */
	protected void atualizarOrigemCadastro(String origem) {
		if ("detalhar".equals(origem)) {
			setVisualizando(true);
			setEditando(false);
			setFromResults(false);
		} else if ("editar".equals(origem)) {
			setVisualizando(false);
			setEditando(true);
			setFromResults(false);
		} else if ("lista".equals(origem)) {
			setVisualizando(false);
			setEditando(false);
			setFromResults(true);
		} else if ("consulta".equals(origem)) {
			setVisualizando(false);
			setEditando(false);
			setFromResults(false);
			setFromLista(false);
		}
	}

	/**
	 * Se no cancelar estamos visualizando, editando ou viemos do resultado, volta pra tela de resultado, pois ela foi a origem da chamada. Senão, retorna para
	 * a tela de consulta.
	 * 
	 * @return
	 */
	// TODO refatorar método. Problema ao tentar voltar para a tela de resultado consulta quando o breadcrumb está com esta
	// configuração: Resultado Consulta -> Detalhes -> Atualizar
	public String redirectBotaoCancelar() {
		if (isEditando() || isVisualizando() || isFromResults()) {
			if (isFromDetalhes()) {
				fromDetalhes = false;
				try {
					load(getEntityId(getInstance()));
				} catch (Exception e) {
					getRootErrorMessage(e);
				}
				atualizarOrigemCadastro("detalhar");
				return includeRedirect("cadastro");
			} else {
				return redirectTelaResultado();
			}
		} else {
			// endConversation();
			fromLista = false;
			return redirectTelaConsulta();
		}
	}

	public String getCrumb() {
		if (isEditando() || isVisualizando() || isFromResults()) {
			return MensagemUtil.obterMensagem("general.breadcrumb.resultadoConsulta");
		} else {
			return MensagemUtil.obterMensagem("general.breadcrumb.consulta");
		}
	}

	public String getCrumbNivel2() {
		if (isEditando() && isFromDetalhes()) {
			return MensagemUtil.obterMensagem("general.breadcrumb.detalhes");
		}

		return "";
	}

	/**
	 * Método utilizado no Link do Botão Alterar da tela de Detalhe. Atualiza a tela para Editando.
	 */
	public void preparaAlterar() {
		fromDetalhes = true;
		atualizarOrigemCadastro("editar");
	}

	public void preparaAlterarEdit() {
		fromDetalhes = true;
		atualizarOrigemCadastro("detalhar");
	}

	public String getHeaderCadastro() {
		if (isEditando()) {
			return "Alterar";
		} else if (isVisualizando()) {
			return "Detalhes";
		} else {
			return "Cadastrar";
		}
	}


	/**
	 * Método responsável por atribuir uma mensagem de informação ao facesMessager
	 * 
	 * @param bundle
	 */
	protected void mensagemInfo(String bundle) {
		facesMessager.info(MensagemUtil.obterMensagem(bundle));
	}

	/**
	 * Método responsável por atribuir uma mensagem de alerta ao facesMessager
	 * 
	 * @param bundle
	 */
	protected void mensagemWarn(String bundle) {
		facesMessager.warn(MensagemUtil.obterMensagem(bundle));
	}

//	/**
//	 * Método responsável por atribuir uma excessão de campos requeridos ao facesMessager
//	 * 
//	 * @param re
//	 */
//	protected void mensagemWarnRequired(RequiredException re) {
//		facesMessager.warn(Util.formatRequiredMessage(re));
//	}

	/**
	 * Método responsável por atribuir uma excessão de regras de negócio ao facesMessager
	 * 
	 * @param re
	 */
	protected void mensagemWarnBusiness(BusinessException be) {
		facesMessager.warn(Util.formatBusinessMessage(be));
	}

	/**
	 * Método reponsável por verificar se ja existe uma mensagem adicionada no facescontext para não deixar repeti-la, serve pra tratar a duplicação de mensagem
	 * principalmente quando é exibida uma mensagem após abrir uma página
	 * 
	 * @return boolean
	 */
	protected boolean existeMensagem() {
		Iterator<String> itIds = FacesContext.getCurrentInstance().getClientIdsWithMessages();
		while (itIds.hasNext()) {
			List<FacesMessage> messageList = FacesContext.getCurrentInstance().getMessageList(itIds.next());
			if (!messageList.isEmpty()) { // if empty, it will be unmodifiable and throw UnsupportedOperationException...
				return true;
			}
		}
		return false;
	}


	protected abstract Long getEntityId(T referenceValue);

	protected abstract T load(Long id) throws Exception;

	protected abstract void deleteImpl(T referenceValue) throws BusinessException, Exception;

	protected abstract void saveImpl(T referenceValue) throws RequiredException, BusinessException, Exception;

	protected abstract void updateImpl(T referenceValue) throws RequiredException, BusinessException, Exception;

	protected abstract void consultarImpl() throws BusinessException, Exception;

	protected abstract T newInstance();

	protected abstract List<T> loadAllInstance() throws Exception;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<T> getAllInstance() {
		return allInstance;
	}

	public void setAllInstance(List<T> allInstance) {
		this.allInstance = allInstance;
	}

	public boolean isVisualizando() {
		return visualizando;
	}

	public void setVisualizando(boolean visualizando) {
		this.visualizando = visualizando;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public boolean isFromResults() {
		return fromResults;
	}

	public void setFromResults(boolean fromResults) {
		this.fromResults = fromResults;
	}

	public void setCaminhoAlternativo(String caminhoAlternativo) {
		this.caminhoAlternativo = caminhoAlternativo;
	}

	public boolean isFromDetalhes() {
		return fromDetalhes;
	}

	public void setFromDetalhes(boolean fromDetalhes) {
		this.fromDetalhes = fromDetalhes;
	}

	public boolean isFromLista() {
		return fromLista;
	}

	public void setFromLista(boolean fromLista) {
		this.fromLista = fromLista;
	}

}