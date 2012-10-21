package financeiro.conta;

import java.util.*;
import financeiro.web.util.DAOFactory;

import financeiro.usuario.Usuario;

public class ContaRN {

	private ContaDAO contaDAO;

	public ContaRN() {
		
		this.contaDAO = DAOFactory.criarContaDAO();
		
	}

	public List<Conta> listar(Usuario usuario) {

		return this.contaDAO.listar(usuario);

	}

	public Conta carregar(Integer conta) {

		return this.contaDAO.Carregar(conta);
		
	}

	public void salvar(Conta conta) {

		conta.setDataCadastro(new Date());
		this.contaDAO.Salvar(conta);
		
	}

	public void excluir(Conta conta) {
		
		this.contaDAO.Excluir(conta);
		
	}

	public void tornarFavorita(Conta contaFavorita) {

		Conta conta = this.buscarFavorita(contaFavorita.getUsuario());
		
		if (conta != null) {
			
			conta.setFavorita(false);
			this.contaDAO.Salvar(conta);
			
		}

		contaFavorita.setFavorita(true);
		this.contaDAO.Salvar(contaFavorita);
		
	}

	public Conta buscarFavorita(Usuario usuario) {
		
		return this.contaDAO.buscarFavorita(usuario);
		
	}
	
}
