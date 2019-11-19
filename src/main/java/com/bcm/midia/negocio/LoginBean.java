package com.bcm.midia.negocio;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bcm.midia.database.dao.AbstractDAO;
import com.bcm.midia.database.dao.UsuarioDAO;
import com.bcm.midia.database.dao.UsuarioTransacaoDAO;
import com.bcm.midia.database.entity.Usuario;



@Stateless
@LocalBean
public class LoginBean extends BussinessAbstract<Usuario> implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -383231671610872244L;

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private UsuarioTransacaoDAO transacaoDAO;
	

	
	/**
	 * Busca o usuario e todas as suas permissões.
	 * @param usuario
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public Usuario login(Usuario usuario) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		 MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
         byte messageDigestSenhaAdmin[] = algorithm.digest(usuario.getNrSenha().getBytes("UTF-8"));
          
         StringBuilder hexStringSenhaAdmin = new StringBuilder();
         for (byte b : messageDigestSenhaAdmin) {
                  hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
         }
         usuario.setNrSenha(hexStringSenhaAdmin.toString());
         
		usuario = usuarioDAO.efetuarLogin(usuario);
		if(usuario != null){
			usuario.setPermissoes(transacaoDAO.buscarTransacoesByUsuario(usuario));
		}
		
		return usuario;
	}

	
	public Long countUsuario(){
		return usuarioDAO.countUsuarios();
	}

	public Long countTransacoes(){
		return transacaoDAO.countTransacoes();
	}


	@Override
	protected void validaCamposObrigatorios(Usuario entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void validaRegras(Usuario entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void validaRegrasExcluir(Usuario entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected AbstractDAO<Usuario> getDAO() {
		// TODO Auto-generated method stub
		return usuarioDAO;
	}
	
	
//	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		String senhaAdmin = "tvsaude";
//	    MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
//        byte messageDigestSenhaAdmin[] = algorithm.digest(senhaAdmin.getBytes("UTF-8"));
//         
//        StringBuilder hexStringSenhaAdmin = new StringBuilder();
//        for (byte b : messageDigestSenhaAdmin) {
//                 hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
//        }
//        String senhahexAdmin = hexStringSenhaAdmin.toString();
//        System.out.println(senhahexAdmin);
//	}


}
