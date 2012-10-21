package financeiro.lancamento;

import java.util.*;
import financeiro.conta.Conta;
import financeiro.web.util.DAOFactory;

public class LancamentoRN {

	private LancamentoDAO	lancamentoDAO;

	public LancamentoRN() {
		this.lancamentoDAO = DAOFactory.criarLancamentoDAO();
	}

	public void salvar(Lancamento lancamento,String tipo) {
		
		lancamento.setTipo(tipo);
		this.lancamentoDAO.salvar(lancamento);
		
	}

	public void excluir(Lancamento lancamento) {
		
		this.lancamentoDAO.excluir(lancamento);
		
	}

	public Lancamento carregar(Integer lancamento) {
		
		return this.lancamentoDAO.carregar(lancamento);
		
	}


	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim,String tipo) { 
		
		return this.lancamentoDAO.listar(conta, dataInicio, dataFim,tipo);
		
	}
	
}
