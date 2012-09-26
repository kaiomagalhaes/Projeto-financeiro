package financeiro.categoria;

import java.util.ArrayList;
import java.util.List;

import financeiro.usuario.Usuario;
import financeiro.web.util.DAOFactory;

public class CategoriaRN {

	private CategoriaDAO categoriaDAO;

	public CategoriaRN() {
		
		this.categoriaDAO = DAOFactory.criarCategoriaDAO();
		
	}

	public List<Categoria> listar(Usuario usuario) {
		
		return this.categoriaDAO.listar(usuario);
		
	}

	public Categoria salvar(Categoria categoria) {
		
		Categoria pai = categoria.getPai();

		if (pai == null) {
			
			String msg = "A Categoria " + categoria.getDescricao()
					+ " deve ter um pai definido";
			throw new IllegalArgumentException(msg);
			
		}

		boolean mudouFator = pai.getFator() != categoria.getFator();

		categoria.setFator(pai.getFator());
		categoria = this.categoriaDAO.salvar(categoria);

		if (mudouFator) {
			
			categoria = this.carregar(categoria.getCodigo());
			this.replicarFator(categoria, categoria.getFator());
			
		}

		return categoria;
		
	}

	private void replicarFator(Categoria categoria, int fator) {
		
		if (categoria.getFilhos() != null) {
			
			for (Categoria filho : categoria.getFilhos()) {
				
				filho.setFator(fator);
				this.categoriaDAO.salvar(filho);
				this.replicarFator(filho, fator);
				
			}
			
		}
		
	}

	public void excluir(Categoria categoria) {

		this.categoriaDAO.excluir(categoria);
		
	}

	public void excluir(Usuario usuario) {
		
		List<Categoria> lista = this.listar(usuario);
		
		for (Categoria categoria : lista) {
			
			this.categoriaDAO.excluir(categoria);
			
		}
		
	}

	public Categoria carregar(Integer categoria) {
		
		return this.categoriaDAO.carregar(categoria);
		
	}

	public List<Integer> carregarCodigos(Integer categoria) {
		
		List<Integer> codigos = new ArrayList<Integer>();
		Categoria c = this.carregar(categoria);
		this.extraiCodigos(codigos, c);

		return codigos;
		
	}

	private void extraiCodigos(List<Integer> codigos, Categoria categoria) {
		
		codigos.add(categoria.getCodigo());
		
		if (categoria.getFilhos() != null) {
			
			for (Categoria filho : categoria.getFilhos()) {
				
				this.extraiCodigos(codigos, filho);
				
			}
			
		}
		
	}

	public void salvaEstruturaPadrao(Usuario usuario) {

		Categoria despesas = new Categoria(null, usuario, "DESPESAS", -1);
		
		despesas = this.categoriaDAO.salvar(despesas);
		
		Categoria opFixas = new Categoria(despesas,usuario,"Operacionais fixas",-1);
		opFixas = this.categoriaDAO.salvar(opFixas);
		
		Categoria opVariaveis = new Categoria(despesas,usuario,"Operacionais variaveis",-1);
		opVariaveis = this.categoriaDAO.salvar(opVariaveis);
		
		Categoria naoOp = new Categoria(despesas,usuario,"Não operacionais",-1);
		naoOp = this.categoriaDAO.salvar(naoOp);
		
		
		
		
		
		Categoria receitas = new Categoria(null, usuario, "RECEITAS", 1);
		
		receitas = this.categoriaDAO.salvar(receitas);
		
		this.categoriaDAO
				.salvar(new Categoria(receitas, usuario, "Salário", 1));
		this.categoriaDAO.salvar(new Categoria(receitas, usuario,
				"Restituições", 1));
		this.categoriaDAO.salvar(new Categoria(receitas, usuario, "Rendimento",
				1));
	}
	
}