package financeiro.descricao;

import java.util.List;

import financeiro.usuario.Usuario;



public interface DescricaoDAO {
	
	public void salvar(Descricao descricao);

	public void excluir(Descricao descricao);

	public Descricao carregar(Integer codigo);

	public List<Descricao> listar(Usuario usuario);
	
}
