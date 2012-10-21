package financeiro.grupo.produtos;

import java.util.List;

import financeiro.web.util.DAOFactory;

public class GrupoRN {
	
	private GrupoDAO grupoDAO;
	
	public GrupoRN(){
		
		this.grupoDAO = DAOFactory.criarGrupoDAO();
		
	}
	
	public void salvar(Grupo grupo){
		
		this.grupoDAO.salvar(grupo);
		
	}
	
	public void excluir(Grupo grupo){
		
		this.grupoDAO.excluir(grupo);
		
	}
	
	public Grupo carregar(Integer codigo){
		
		return this.grupoDAO.carregar(codigo);
		
	}
	
	
	public List<Grupo> listar(){
		
		return this.grupoDAO.listar();
		
	}
	
	public void salvaEstruturaPadrao(){
			
		this.grupoDAO.salvar(new Grupo("Medicamentos de Referencia e Marca Especiais"));	
		this.grupoDAO.salvar(new Grupo("Medicamentos Similares"));	
		this.grupoDAO.salvar(new Grupo("Medicamentos Genéricos"));	
		this.grupoDAO.salvar(new Grupo("Perfumarias e Correlatos"));	
		this.grupoDAO.salvar(new Grupo("Manipulados e Homeopaticos"));	
	}
	
}
