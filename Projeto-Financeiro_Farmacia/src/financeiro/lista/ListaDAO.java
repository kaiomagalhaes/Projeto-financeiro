package financeiro.lista;

import java.util.List;

public interface ListaDAO {
	
	public void salvar(Lista lista);

	public void excluir(Lista lista);

	public Lista carregar(Integer codigo);

	public List<Lista> listar();
	
}
