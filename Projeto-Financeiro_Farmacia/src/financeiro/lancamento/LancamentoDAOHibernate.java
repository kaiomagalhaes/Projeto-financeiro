package financeiro.lancamento;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import financeiro.conta.Conta;

public class LancamentoDAOHibernate implements LancamentoDAO {

	private Session	session;

	public void setSession(Session session) {
		
		this.session = session;
		
	}

	public void salvar(Lancamento lancamento) {
		
		this.session.saveOrUpdate(lancamento);
		
	}

	public void excluir(Lancamento lancamento) {
		
		this.session.delete(lancamento);
		
	}

	public Lancamento carregar(Integer lancamento) {
		
		return (Lancamento) this.session.get(Lancamento.class, lancamento);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim,String tipo) {
		
		Criteria criteria = this.session.createCriteria(Lancamento.class);

		if (dataInicio != null && dataFim != null) {
			criteria.add(Restrictions.between("data", dataInicio, dataFim));
		} else if (dataInicio != null) {
			criteria.add(Restrictions.ge("data", dataInicio));
		} else if (dataFim != null) {
			criteria.add(Restrictions.le("data", dataFim));
		}

		criteria.add(Restrictions.eq("conta", conta));
		criteria.add(Restrictions.eq("tipo", tipo));
		criteria.addOrder(Order.desc("data"));
		
		return criteria.list();
		
		
	}

}
