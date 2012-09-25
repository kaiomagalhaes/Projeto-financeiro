package financeiro.web.util;

import financeiro.categoria.*;
import financeiro.conta.*;
import financeiro.lancamento.*;
import financeiro.usuario.*;
import financeiro.cheque.*;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO(){
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return usuarioDAO;
	}
	
	public static ContaDAO criarContaDAO(){
		
		ContaDAOHibernate contaDAO = new ContaDAOHibernate();
		contaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return contaDAO;
		
	}
	
	public static CategoriaDAO criarCategoriaDAO(){
		
		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return categoriaDAO;
		
	}
	
	public static LancamentoDAO criarLancamentoDAO(){
		
		LancamentoDAOHibernate lancamentoDAO = new LancamentoDAOHibernate();
		lancamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return lancamentoDAO;
		
	}
	
	public static ChequeDAO criarChequeDAO(){
		
		ChequeDAOHibernate chequeDAO = new ChequeDAOHibernate();
		chequeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return chequeDAO;
		
	}
	
}
