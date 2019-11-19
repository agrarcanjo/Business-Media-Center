package com.bcm.midia.negocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.bcm.midia.database.dao.AbstractDAO;
import com.bcm.midia.database.dao.CategoriaDAO;
import com.bcm.midia.database.entity.Categoria;



@Stateless
@LocalBean
public class CategoriaBean extends BussinessAbstract<Categoria> implements Serializable {
	
	private static final long serialVersionUID = -6212672805089749673L;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	public List<Categoria> findAll(){
		return categoriaDAO.findAll();
	}
	
	public Categoria findCategoria(Long id){
		return categoriaDAO.findById(id);
	}
	
	public List<Categoria> findbyPaiFilho(Categoria cat){
		return categoriaDAO.findbyPaiFilho(cat);
	}
	
	public List<Categoria> findAllPai(){		
		return categoriaDAO.findAllPai();
	}
	
	public Long countCategorias(){
		return categoriaDAO.countCategoria();
	}

	public TreeNode createRoot(){		
		TreeNode rootNode = new DefaultTreeNode(new Categoria(), null);
	    List<Categoria> documentRootNodeList = categoriaDAO.findbyPaiFilho(new Categoria());
	    for (Categoria doc : documentRootNodeList) {
	        TreeNode node = new DefaultTreeNode(doc, rootNode);
	        createSubNode(doc, node);
	    }
	    return rootNode;	
	}
	
	public void createSubNode(Categoria cat, TreeNode node) {
	    List<Categoria> documentList = categoriaDAO.findbyPaiFilho(cat);
	    for (Categoria subDoc : documentList) {
	        TreeNode subNode = new DefaultTreeNode(subDoc, node);
	        createSubNode(subDoc, subNode);
	    }
	}
	

	@Override
	protected void validaCamposObrigatorios(Categoria entity) {
		if(entity != null && (entity.getNmCategoria().trim().equals("") || entity.getNmCategoria() == null)){
			mensagens.add("categoria.nome");
		}		
	}


	@Override
	protected void validaRegras(Categoria entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void validaRegrasExcluir(Categoria entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected AbstractDAO<Categoria> getDAO() {
		return categoriaDAO;
	}





}
