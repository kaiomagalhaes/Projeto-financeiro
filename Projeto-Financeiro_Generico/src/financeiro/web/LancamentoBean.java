package financeiro.web;

import java.util.*;

import javax.faces.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import financeiro.categoria.*;
import financeiro.cheque.*;
import financeiro.conta.Conta;
import financeiro.lancamento.Lancamento;
import financeiro.lancamento.LancamentoRN;
import financeiro.web.util.ContextoUtil;
import financeiro.web.util.RNException;

@ManagedBean(name = "lancamentoBean")
@ViewScoped
public class LancamentoBean {

	private List<Lancamento> lista;
	private List<Double> saldos = new ArrayList<Double>();
	private float saldoGeral;
	private Lancamento editado = new Lancamento();
	private List<Lancamento> listaAteHoje;
	private List<Lancamento> listaFuturos;
	private Integer numeroCheque;

	public void setListaAteHoje(List<Lancamento> listaAteHoje) {

		this.listaAteHoje = listaAteHoje;

	}

	public void setListaFuturos(List<Lancamento> listaFuturos) {

		this.listaFuturos = listaFuturos;

	}

	public List<Lancamento> getListaAteHoje() {

		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		Conta conta = contextoBean.getContaAtiva();

		Calendar hoje = new GregorianCalendar();

		LancamentoRN lancamentoRN = new LancamentoRN();
		this.listaAteHoje = lancamentoRN.listar(conta, null, hoje.getTime());

		return this.listaAteHoje;

	}

	public List<Lancamento> getListaFuturos() {

		if (this.listaFuturos == null) {

			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Conta conta = contextoBean.getContaAtiva();

			Calendar amanha = new GregorianCalendar();
			amanha.add(Calendar.DAY_OF_MONTH, 1);

			LancamentoRN lancamentoRN = new LancamentoRN();
			this.listaFuturos = lancamentoRN.listar(conta, amanha.getTime(),
					null);

		}

		return this.listaFuturos;

	}

	public Integer getNumeroCheque() {

		return numeroCheque;

	}

	public void setNumeroCheque(Integer numeroCheque) {

		this.numeroCheque = numeroCheque;

	}

	public void novo() {

		this.editado = new Lancamento();
		this.editado.setData(new Date(System.currentTimeMillis()));
		this.numeroCheque = null;
	}

	public void editar() {

		Cheque cheque = this.editado.getCheque();

		if (cheque != null) {
			this.numeroCheque = cheque.getChequeId().getCheque();
		}
	}

	public void salvar() {

		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.editado.setUsuario(contextoBean.getUsuarioLogado());
		this.editado.setConta(contextoBean.getContaAtiva());

		ChequeRN chequeRN = new ChequeRN();
		
		ChequeId chequeId = null;
		
		if (this.numeroCheque != null) {
			
			chequeId = new ChequeId();
			
			chequeId.setConta(contextoBean.getContaAtiva().getConta());
			
			chequeId.setCheque(this.numeroCheque);
			
			Cheque cheque = chequeRN.carregar(chequeId);
			
			FacesContext context = FacesContext.getCurrentInstance();
			
			if (cheque == null) {
				
				FacesMessage msg = new FacesMessage("Cheque não cadastrado");
				context.addMessage(null, msg);
				return;
				
			} else if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_CANCELADO) {
				
				FacesMessage msg = new FacesMessage("Cheque já cancelado");
				context.addMessage(null, msg);
				return;
				
			} else {
				
				this.editado.setCheque(cheque);
				chequeRN.baixarCheque(chequeId, this.editado);
				
			}
			
		}
		LancamentoRN lancamentoRN = new LancamentoRN();
		
		lancamentoRN.salvar(this.editado);
		
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

			Calendar dataSaldo = new GregorianCalendar();
			dataSaldo.add(Calendar.MONTH, -1);
			dataSaldo.add(Calendar.DAY_OF_MONTH, -1);

			Calendar inicio = new GregorianCalendar();
			inicio.add(Calendar.MONTH, -1);

			LancamentoRN lancamentoRN = new LancamentoRN();
			this.saldoGeral = lancamentoRN.saldo(conta, dataSaldo.getTime());
			this.lista = lancamentoRN.listar(conta, inicio.getTime(), null);

			Categoria categoria = null;
			double saldo = this.saldoGeral;
			
			for (Lancamento lancamento : this.lista) {
				
				categoria = lancamento.getCategoria();
				saldo = saldo
						+ (lancamento.getValor().floatValue() * categoria
								.getFator());
				this.saldos.add(saldo);
				
			}
			
		}
		
		return this.lista;
		
	}

	public void mudouCheque(ValueChangeEvent event) {
		
		Integer chequeAnterior = (Integer) event.getOldValue();
		
		if (chequeAnterior != null) {
			
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			
			ChequeRN chequeRN = new ChequeRN();
			
			try {
				
				chequeRN.desvinculaLancamento(contextoBean.getContaAtiva(),
						chequeAnterior);
				
			} catch (RNException e) {
				
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(e.getMessage());
				context.addMessage(null, msg);
				
				return;
			}
		}
	}

	public List<Double> getSaldos() {
		
		return saldos;
		
	}

	public void setSaldos(List<Double> saldos) {
		
		this.saldos = saldos;
		
	}

	public float getSaldoGeral() {
		
		return saldoGeral;
		
	}

	public void setSaldoGeral(float saldoGeral) {
		
		this.saldoGeral = saldoGeral;
		
	}

	public Lancamento getEditado() {
		
		return editado;
		
	}

	public void setEditado(Lancamento editado) {
		
		this.editado = editado;
		
	}

	public void setLista(List<Lancamento> lista) {
		
		this.lista = lista;
		
	}

	public LancamentoBean() {
		
		this.novo();
		this.getListaAteHoje();
		
	}
	
}
