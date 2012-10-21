package financeiro.categoria;

import java.util.ArrayList;
import java.util.List;

import financeiro.grupo.produtos.Grupo;
import financeiro.usuario.Usuario;
import financeiro.web.util.DAOFactory;

public class CategoriaRN {

	private CategoriaDAO categoriaDAO;

	public CategoriaRN() {
		
		this.categoriaDAO = DAOFactory.criarCategoriaDAO();
		
	}

	public List<Categoria> listar(Usuario usuario,String tipo) {
		
		return this.categoriaDAO.listar(usuario,tipo);
		
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

	public Categoria salvarCatDescontoFornecedor(Categoria categoria,Grupo grupo) {
		
		Categoria pai = categoria.getPai();

		if (pai == null) {
			
			String msg = "A Categoria " + categoria.getDescricao()
					+ " deve ter um pai definido";
			throw new IllegalArgumentException(msg);
			
		}

		boolean mudouFator = pai.getFator() != categoria.getFator();

		categoria.setFator(pai.getFator());
		categoria.setGrupo(grupo);
		
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
		
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Água",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Aluguel",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Telefone/Internet",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Taxas Bancárias",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Benefícios a Func.",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Doações e perdas",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Encargos Sociais",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Energia",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Folha pgto/Sal.Fixos",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Informática/Assist.Téc.",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Lastro Inadimplentes",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Lastro RH",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Manutenções diversas",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Marketing/merchandising",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Material Limpeza/Hig.",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Mensalidades Diversas",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Pagamento Plano Saúde",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Papelaria Gráfica",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Pró-Labore Sócios",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Quebra de caixa",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Serviço de cobrança",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Serviço de entrega a Dom.",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Serviços de Terceiros",-1)));
		this.categoriaDAO.salvar((new Categoria(opFixas,usuario,"Viagens/Correios/Fretes",-1)));
		
		Categoria opVariaveis = new Categoria(despesas,usuario,"Operacionais variaveis",-1);
		opVariaveis = this.categoriaDAO.salvar(opVariaveis);
		
		this.categoriaDAO.salvar((new Categoria(opVariaveis,usuario,"Comição de Funcionário",-1)));
		this.categoriaDAO.salvar((new Categoria(opVariaveis,usuario,"Imposto Estadual(ICMS/SIMPLES",-1)));
		this.categoriaDAO.salvar((new Categoria(opVariaveis,usuario,"Imposto Federal(se houver:I.R+CS+PIS+COFINS",-1)));
		this.categoriaDAO.salvar((new Categoria(opVariaveis,usuario,"Outras",-1)));
		this.categoriaDAO.salvar((new Categoria(opVariaveis,usuario,"Viagens/Correios/Fretes",-1)));
		
		
		
		Categoria naoOp = new Categoria(despesas,usuario,"Não operacionais",-1);
		naoOp = this.categoriaDAO.salvar(naoOp);
		
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Acionistas/Investidores",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Benfeitorias/Investimentos",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Clientes Cheques Perdas",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Clientes Inadimplentes(Lastro",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Comição Extra Funcionário",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Despesas Extras",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Doações e perdas",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Financiamentos e emprestimos",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Fornecedores Extras",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Indenizações Trabalhistas(Lastro)",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Marketing/Merchandising Extras",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Multas Fiscais",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Pgtos Extras Diversos",-1)));
		this.categoriaDAO.salvar((new Categoria(naoOp,usuario,"Seguro Empresarial",-1)));
		
		
		Categoria custoDoProduto = new Categoria(null, usuario, "CUSTO DO PRODUTO", 1);
		custoDoProduto = this.categoriaDAO.salvar(custoDoProduto);
		this.categoriaDAO.salvar((new Categoria(custoDoProduto, usuario,"Fornecedores Pagos", 1)));
		
				
		Categoria desconto = new Categoria(null,usuario,"Desconto",1);
		desconto = this.categoriaDAO.salvar(desconto);
		
		
		this.categoriaDAO.salvar((new Categoria(desconto,usuario,"Concedido a clientes",1)));
		this.categoriaDAO.salvar((new Categoria(desconto,usuario,"Cedido pelos fornecedores",1)));
		
		
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