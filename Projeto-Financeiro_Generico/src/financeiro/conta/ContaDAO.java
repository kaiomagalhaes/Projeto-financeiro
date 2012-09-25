package financeiro.conta;

import java.util.List;

import financeiro.usuario.Usuario;

public interface ContaDAO {

	public void Salvar(Conta conta);

	public void Excluir(Conta conta);

	public Conta Carregar(Integer conta);

	public List<Conta> listar(Usuario usuario);

	public Conta buscarFavorita(Usuario usuario);

}
