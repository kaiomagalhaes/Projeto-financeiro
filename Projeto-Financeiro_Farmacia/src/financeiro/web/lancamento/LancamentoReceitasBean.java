package financeiro.web.lancamento;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import financeiro.conta.Conta;
import financeiro.lancamento.Lancamento;
import financeiro.lancamento.LancamentoRN;
import financeiro.web.CategoriaBean;
import financeiro.web.ContextoBean;
import financeiro.web.util.ContextoUtil;


@ManagedBean(name = "lancamentoReceitasBean")
@ViewScoped
public class LancamentoReceitasBean {

	private List<Lancamento> lista;

	private Lancamento editado = new Lancamento();

	public LancamentoReceitasBean() {
		
		this.novo();
		
	}

	public void novo() {
		
		this.editado = new Lancamento();
		this.editado.setData(new Date(System.currentTimeMillis()));
		
	}

	public void editar() {
		
	}

	public void salvar() {
		
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.editado.setUsuario(contextoBean.getUsuarioLogado());
		this.editado.setConta(contextoBean.getContaAtiva());

		LancamentoRN lancamentoRN = new LancamentoRN();
		
		lancamentoRN.salvar(this.editado,"receita");
		
		this.novo();
		
		this.lista = null;
		
	}

	public void excluir() {
		
		LancamentoRN lancamentoRN = new LancamentoRN();
		this.editado = lancamentoRN.carregar(this.editado.getLancamento());
		lancamentoRN.excluir(this.editado);
		this.lista = null;
		
	}

	public List<Lancamento> getLista() {
		
		if (this.lista == null) {
			
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Conta conta = contextoBean.getContaAtiva();

			Calendar hoje = new GregorianCalendar();

			LancamentoRN lancamentoRN = new LancamentoRN();
			this.lista = lancamentoRN
					.listar(conta, null, hoje.getTime(),"receita");
			
		}
		
		return this.lista;
		
	}
	
	public List<SelectItem> getListaCategoria(){
		
		CategoriaBean categoria = new CategoriaBean();
		return categoria.getCategoriasSelect("RECEITAS");
		
		
	}

	public void setLista(List<Lancamento> lista) {
		
		this.lista = lista;
		
	}


	public Lancamento getEditado() {
		
		return editado;
		
	}

	public void setEditado(Lancamento selecionado) {
		
		this.editado = selecionado;
		
	}

}
