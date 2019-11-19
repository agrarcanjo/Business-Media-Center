package com.bcm.midia.controller.categoria;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.TreeNode;

import com.bcm.midia.database.entity.Categoria;
import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;
import com.bcm.midia.negocio.CategoriaBean;
import com.bcm.midia.util.AbstractCRUD;

@Named
@ConversationScoped
public class CategoriaController extends AbstractCRUD<Categoria> implements Serializable {

	private static final long serialVersionUID = -2693989382095359172L;

	@Inject
	private CategoriaBean categoriaBean;

	private List<Categoria> categoriaListPai;
	private Categoria categoria;

	private TreeNode root;

	@PostConstruct
	public void init() {
		instance = new Categoria();

		if (root == null) {
			root = categoriaBean.createRoot();
		}
	}

	@Override
	protected Long getEntityId(Categoria referenceValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Categoria load(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void deleteImpl(Categoria referenceValue) throws BusinessException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void saveImpl(Categoria referenceValue) throws RequiredException, BusinessException, Exception {
		if (referenceValue != null) {
			if (referenceValue != null && referenceValue.getIdCategoriaPai() == 0) {
				referenceValue.setIdCategoriaPai(null);
			}
			instance = categoriaBean.save(referenceValue);
			mensagemInfo("registro.salvo.sucesso");
			init();
		}
	}

	@Override
	protected void updateImpl(Categoria referenceValue) throws RequiredException, BusinessException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void consultarImpl() throws BusinessException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Categoria newInstance() {
		return new Categoria();
	}

	@Override
	protected List<Categoria> loadAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Categoria> getCategoriaListPai() {
		if(categoriaListPai == null)
	    	categoriaListPai = categoriaBean.findAll(); 
		return categoriaListPai;
	}

	public void setCategoriaListPai(List<Categoria> categoriaListPai) {
		this.categoriaListPai = categoriaListPai;
	}

	public String retornarCategoriaPai(Categoria cat) {
		if (cat != null) {
			if (cat.getIdCategoriaPai() == null) {
				return "";
			} else
				return cat.getNmCategoria();
		}
		return "";
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
