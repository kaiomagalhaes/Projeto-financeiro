package financeiro.lista;

import java.util.List;

import financeiro.web.util.DAOFactory;

public class ListaRN {

	private ListaDAO listaDAO;

	public ListaRN() {

		this.listaDAO = DAOFactory.criarListaDAO();

	}

	public void salvar(Lista lista) {

		this.listaDAO.salvar(lista);

	}

	public void excluir(Lista lista) {

		this.listaDAO.excluir(lista);

	}

	public Lista carregar(Integer codigo) {

		return this.listaDAO.carregar(codigo);

	}

	public List<Lista> listar() {

		return this.listaDAO.listar();

	}

	public void salvaEstruturaPadrao() {


		this.listaDAO.salvar(new Lista("Lista Negativa "));
		this.listaDAO.salvar(new Lista("Lista Positiva "));
		this.listaDAO.salvar(new Lista("Lista Neutra "  ));
		this.listaDAO.salvar(new Lista("Lista Liberados"));
		
	}

}
