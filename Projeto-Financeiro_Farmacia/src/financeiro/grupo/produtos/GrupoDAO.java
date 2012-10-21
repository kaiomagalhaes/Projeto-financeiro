package financeiro.grupo.produtos;

import java.util.List;

public interface GrupoDAO {
	
	public void salvar(Grupo grupo);

	public void excluir(Grupo grupo);

	public Grupo carregar(Integer codigo);

	public List<Grupo> listar();
	
}
