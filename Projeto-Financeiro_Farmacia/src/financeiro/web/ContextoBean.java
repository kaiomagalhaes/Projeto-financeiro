package financeiro.web;

import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.*;
import javax.faces.event.ValueChangeEvent;
import financeiro.conta.*;
import financeiro.usuario.*;

@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {

	private Usuario usuarioLogado = null;
	private Conta contaAtiva = null;
	private Locale localização = null;
	private List<Locale> idiomas;
	

	public Usuario getUsuarioLogado() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (this.usuarioLogado == null
				|| !login.equals(this.usuarioLogado.getLogin())) {
			if (login != null) {

				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuarioLogado = usuarioRN.buscarPorLogin(login);
				this.contaAtiva = null;
				
			}
			
		}
		
		return usuarioLogado;
		
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
	}

	public Conta getContaAtiva() {

		if (this.contaAtiva == null) {
			
			Usuario usuario = this.getUsuarioLogado();
			ContaRN contaRN = new ContaRN();
			this.contaAtiva = contaRN.buscarFavorita(usuario);

			if (this.contaAtiva == null) {
				
				List<Conta> contas = contaRN.listar(usuario);

				if (contas != null) {
					
					for (Conta conta : contas) {
						this.contaAtiva = conta;
						break;
						
					}
					
				}
				
			}

		}
		
		return this.contaAtiva;
		
	}

	public void setContaAtiva(ValueChangeEvent event) {
		
		Integer conta = (Integer) event.getNewValue();
		
		ContaRN contaRN = new ContaRN();
		
		this.contaAtiva = contaRN.carregar(conta);
		
	}

	public Locale getLocaleUsuario() {

		if (this.localização == null) {

			Usuario usuario = this.getUsuarioLogado();
			String idioma = usuario.getIdioma();
			String[] info = idioma.split("_");
			this.localização = new Locale(info[0], info[1]);

		}

		return this.localização;

	}

	public List<Locale> getIdiomas() {

		FacesContext context = FacesContext.getCurrentInstance();
		
		Iterator<Locale> locales = context.getApplication()
				.getSupportedLocales();
		
		this.idiomas = new ArrayList<Locale>();
		
		while (locales.hasNext()) {
			
			this.idiomas.add(locales.next());
			
		}
		
		return idiomas;
		
	}

	public void setIdiomaUsuario(String idioma) {

		UsuarioRN usuarioRN = new UsuarioRN();
		this.usuarioLogado = usuarioRN.carregar(this.getUsuarioLogado()
				.getCodigo());
		this.usuarioLogado.setIdioma(idioma);
		usuarioRN.salvar(this.usuarioLogado);

		String[] info = idioma.split("_");
		Locale locale = new Locale(info[0], info[1]);

		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);

	}

}
