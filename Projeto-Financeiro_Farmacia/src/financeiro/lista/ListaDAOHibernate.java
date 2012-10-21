package financeiro.lista;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class ListaDAOHibernate implements ListaDAO{

	private Session session;

	public void setSession(Session session) {
		
		this.session = session;
		
	}
	
	@Override
	public void salvar(Lista lista) {

		this.session.saveOrUpdate(lista);
		
	}

	@Override
	public void excluir(Lista lista) {
		
		this.session.delete(lista);
		
	}

	@Override
	public Lista carregar(Integer codigo) {
		
		return (Lista) this.session.get(Lista.class,codigo);
		
	}

	@Override
	public List<Lista> listar() {
		
		Criteria criteria = this.session.createCriteria(Lista.class);
		return criteria.list();
		
	}

}