package financeiro.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.*;

import financeiro.usuario.Usuario;
import financeiro.usuario.UsuarioRN;
import financeiro.conta.*;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	private Conta conta = new Conta();

	public String novo() {
		
		this.destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "usuario";
		
	}

	public String atribuiPermissao(Usuario usuario, String permissao) {

		this.usuario = usuario;

		Set<String> permissoes = this.usuario.getPermissao();

		if (permissoes.contains(permissao)) {
			
			permissoes.remove(permissao);
			
		} else {
			
			permissoes.add(permissao);
			
		}

		return null;
	}

	public String editar() {

		this.confirmarSenha = this.usuario.getSenha();
		return "/publico/usuario";

	}

	public String excluir() {

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
		
	}

	public String salvar() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		String senha = this.usuario.getSenha();
		
		if (!senha.equals(this.confirmarSenha)) {
			
			FacesMessage facesMessage = new FacesMessage(
					"A senha n�o foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;

		}
		
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);

		if (this.conta.getDescricao() != null) {

			this.conta.setUsuario(this.usuario);
			this.conta.setFavorita(true);
			ContaRN contaRN = new ContaRN();
			contaRN.salvar(this.conta);
			
		}

		return this.destinoSalvar;

	}

	public Conta getConta() {
		
		return conta;
		
	}

	public void setConta(Conta conta) {
		
		this.conta = conta;
		
	}

	public String ativar() {
		
		if (this.usuario.isAtivo())
			this.usuario.setAtivo(false);
		else
			this.usuario.setAtivo(true);

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return null;
		
	}

	public List<Usuario> getLista() {

		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}

		return this.lista;

	}

	public String getDestinoSalvar() {
		
		return destinoSalvar;
		
	}

	public void setDestinoSalvar(String destinoSalvar) {
		
		this.destinoSalvar = destinoSalvar;
		
	}

	public Usuario getUsuario() {
		
		return usuario;
		
	}

	public void setUsuario(Usuario usuario) {
		
		this.usuario = usuario;
		
	}

	public String getConfirmarSenha() {
		
		return confirmarSenha;
		
	}

	public void setConfirmarSenha(String confirmarSenha) {
		
		this.confirmarSenha = confirmarSenha;
		
	}

}
