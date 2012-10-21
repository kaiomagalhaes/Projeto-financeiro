package financeiro.web.util;

import financeiro.bolsa.acao.AcaoDAO;
import financeiro.bolsa.acao.AcaoDAOHibernate;
import financeiro.categoria.CategoriaDAO;
import financeiro.categoria.CategoriaDAOHibernate;
import financeiro.cheque.ChequeDAO;
import financeiro.cheque.ChequeDAOHibernate;
import financeiro.conta.ContaDAO;
import financeiro.conta.ContaDAOHibernate;
import financeiro.descricao.DescricaoDAO;
import financeiro.descricao.DescricaoDAOHibernate;
import financeiro.grupo.produtos.GrupoDAO;
import financeiro.grupo.produtos.GrupoDAOHibernate;
import financeiro.lancamento.LancamentoDAO;
import financeiro.lancamento.LancamentoDAOHibernate;
import financeiro.lista.ListaDAO;
import financeiro.lista.ListaDAOHibernate;
import financeiro.usuario.UsuarioDAO;
import financeiro.usuario.UsuarioDAOHibernate;

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
	
	public static AcaoDAO criarAcaoDAO(){
		
		AcaoDAOHibernate acaoDAO = new AcaoDAOHibernate();
		acaoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return acaoDAO;
		
	}
	
	public static GrupoDAO criarGrupoDAO(){
		
		GrupoDAOHibernate grupoDAO = new GrupoDAOHibernate();
		grupoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return grupoDAO;
		
	}
	
	public static ListaDAO criarListaDAO(){
		
		ListaDAOHibernate listaDAO = new ListaDAOHibernate();
		listaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return listaDAO;
		
	}
	
	public static DescricaoDAO criarDescricaoDAO(){
		
		DescricaoDAOHibernate descricaoDAO = new DescricaoDAOHibernate();
		descricaoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return descricaoDAO;
		
	}

	
}
