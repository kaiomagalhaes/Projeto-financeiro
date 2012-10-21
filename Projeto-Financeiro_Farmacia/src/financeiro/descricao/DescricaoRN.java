package financeiro.descricao;


import java.util.List;

import financeiro.usuario.Usuario;
import financeiro.web.util.DAOFactory;

public class DescricaoRN {
	
	private DescricaoDAO descricaoDAO;
	
	public DescricaoRN(){
		
		this.descricaoDAO = DAOFactory.criarDescricaoDAO();
		
	}
	
	public void salvar(Descricao descricao){
		
		this.descricaoDAO.salvar(descricao);
		
	}
	
	public void excluir(Descricao descricao){
		
		this.descricaoDAO.excluir(descricao);
		
	}
	
	public Descricao carregar(Integer codigo){
		
		return this.descricaoDAO.carregar(codigo);
		
	}
	
	
	public List<Descricao> listar(Usuario usuario){
		
		return this.descricaoDAO.listar(usuario);
		
	}
	
	public void salvaEstruturaPadrao(Usuario usuario){
			

		this.descricaoDAO.salvar(new Descricao(usuario,"Medicamentos de Referencia e Marca Especiais"));	
		this.descricaoDAO.salvar(new Descricao(usuario,"Medicamentos Similares"));	
		this.descricaoDAO.salvar(new Descricao(usuario,"Medicamentos Genéricos"));	
		this.descricaoDAO.salvar(new Descricao(usuario,"Perfumarias e Correlatos"));	
		this.descricaoDAO.salvar(new Descricao(usuario,"Manipulados e Homeopaticos"));	
	}
	
}
