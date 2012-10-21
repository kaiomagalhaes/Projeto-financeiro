package financeiro.descricao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import financeiro.usuario.Usuario;

public class DescricaoDAOHibernate implements DescricaoDAO{

	private Session session;

	public void setSession(Session session) {
		
		this.session = session;
		
	}
	
	@Override
	public void salvar(Descricao descricao) {

		this.session.saveOrUpdate(descricao);
		
	}

	@Override
	public void excluir(Descricao descricao) {
		
		this.session.delete(descricao);
		
	}

	@Override
	public Descricao carregar(Integer codigo) {
		
		return (Descricao) this.session.get(Descricao.class,codigo);
		
	}

	@Override
	public List<Descricao> listar(Usuario usuario) {
		
		Criteria criteria = this.session.createCriteria(Descricao.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		return criteria.list();
		
	}

}
