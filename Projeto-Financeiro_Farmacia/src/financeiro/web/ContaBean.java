package financeiro.web;

import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import financeiro.web.util.ContextoUtil;
import financeiro.web.util.RelatorioUtil;
import financeiro.web.util.UtilException;
import financeiro.web.ContextoBean;

import financeiro.conta.*;

@ManagedBean(name = "contaBean")
@RequestScoped
public class ContaBean {

	private Conta selecionada = new Conta();
	private List<Conta> lista = null;
	private StreamedContent arquivoRetorno;
	private int tipoRelatorio;

	public StreamedContent getArquivoRetorno() {

		FacesContext context = FacesContext.getCurrentInstance();
		
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		
		String usuario = contextoBean.getUsuarioLogado().getLogin();
		
		String nomeRelatorioJasper = "contas";
		
		String nomeRelatorioSaida = usuario + "_contas_";
		
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		
		HashMap parametrosRelatorio = new HashMap();
		
		parametrosRelatorio.put("codigoUsuario", contextoBean
				.getUsuarioLogado().getCodigo());
		
		try {

			this.arquivoRetorno = relatorioUtil.geraRelatorio(
					parametrosRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, this.tipoRelatorio);

		} catch (UtilException e) {
			
			context.addMessage(null, new FacesMessage(e.getMessage()));
			
		}

		return this.arquivoRetorno;

	}

	public void salvar() {

		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.selecionada.setUsuario(contextoBean.getUsuarioLogado());

		ContaRN contaRN = new ContaRN();
		contaRN.salvar(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;

	}

	public void editar() {

	}

	public void excluir() {

		ContaRN contaRN = new ContaRN();
		contaRN.excluir(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
	}

	public void tornarFavorita() {

		ContaRN contaRN = new ContaRN();
		contaRN.tornarFavorita(this.selecionada);
		this.selecionada = new Conta();

	}

	public Conta getSelecionada() {

		return selecionada;

	}

	public void setSelecionada(Conta selecionada) {

		this.selecionada = selecionada;

	}

	public List<Conta> getLista() {

		if (this.lista == null) {
			ContextoBean contextoBean = ContextoUtil.getContextoBean();

			ContaRN contaRN = new ContaRN();
			this.lista = contaRN.listar(contextoBean.getUsuarioLogado());
		}

		return this.lista;

	}

	public void setLista(List<Conta> lista) {

		this.lista = lista;

	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public void setArquivoRetorno(StreamedContent arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}

}
