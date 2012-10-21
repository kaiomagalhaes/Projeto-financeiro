package financeiro.cheque;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import financeiro.conta.Conta;
import financeiro.lancamento.Lancamento;

@Entity
@Table(name = "cheque")
public class Cheque implements Serializable {

	private static final long serialVersionUID = -4500870721687302586L;
	public static final char SITUACAO_CHEQUE_BAIXADO = 'B';
	public static final char SITUACAO_CHEQUE_CANCELADO = 'C';
	public static final char SITUACAO_CHEQUE_NAO_EMITIDO = 'N';

	@EmbeddedId
	private ChequeId codigoChequeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "conta", referencedColumnName = "cod_conta", insertable = false, updatable = false)
	@ForeignKey(name = "fk_cheque_conta")
	private Conta conta;

	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;

	@Column(nullable = false, precision = 1)
	private Character situacao;

	@OneToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "lancamento", referencedColumnName = "codigo", nullable = true)
	@ForeignKey(name = "fk_cheque_lancamento")
	private Lancamento lancamento;

	public ChequeId getCodigoChequeId() {
		
		return codigoChequeId;
		
	}

	public void setChequeId(ChequeId codigoChequeId) {
		
		this.codigoChequeId = codigoChequeId;
		
	}

	public Conta getConta() {
		
		return conta;
		
	}

	public void setConta(Conta conta) {
		
		this.conta = conta;
		
	}

	public Date getDataCadastro() {
		
		return dataCadastro;
		
	}

	public void setDataCadastro(Date dataCadastro) {
		
		this.dataCadastro = dataCadastro;
		
	}

	public Character getSituacao() {
		
		return situacao;
		
	}

	public void setSituacao(Character situacao) {
		
		this.situacao = situacao;
		
	}

	public Lancamento getLancamento() {
		
		return lancamento;
		
	}

	public void setLancamento(Lancamento lancamento) {
		
		this.lancamento = lancamento;
		
	}

	public static char getSituacaoChequeBaixado() {
		
		return SITUACAO_CHEQUE_BAIXADO;
		
	}

	public static char getSituacaoChequeCancelado() {
		
		return SITUACAO_CHEQUE_CANCELADO;
		
	}

	public static char getSituacaoChequeNaoEmitido() {
		
		return SITUACAO_CHEQUE_NAO_EMITIDO;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoChequeId == null) ? 0 : codigoChequeId.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result
				+ ((lancamento == null) ? 0 : lancamento.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cheque other = (Cheque) obj;
		if (codigoChequeId == null) {
			if (other.codigoChequeId != null)
				return false;
		} else if (!codigoChequeId.equals(other.codigoChequeId))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (lancamento == null) {
			if (other.lancamento != null)
				return false;
		} else if (!lancamento.equals(other.lancamento))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}

}