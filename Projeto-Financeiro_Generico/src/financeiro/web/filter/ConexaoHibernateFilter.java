package financeiro.web.filter;

import javax.servlet.*;
import org.hibernate.SessionFactory;

import financeiro.web.util.HibernateUtil;

public class ConexaoHibernateFilter implements Filter {

	private SessionFactory sf;

	public void init(FilterConfig config) throws ServletException {
		
		this.sf = HibernateUtil.getSessionFactory();
		
	}

	public void destroy() {
		
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws ServletException {

		try {
			
			System.out.println("28");
			
			this.sf.getCurrentSession().beginTransaction();
			
			System.out.println("32");
			chain.doFilter(servletRequest, servletResponse);
			
			System.out.println("35");
			this.sf.getCurrentSession().getTransaction().commit();
			
			System.out.println("38");
			this.sf.getCurrentSession().close();

		} catch (Throwable ex) {

			try {

				if (this.sf.getCurrentSession().getTransaction().isActive()) {
					
					this.sf.getCurrentSession().getTransaction().rollback();
					
				}
				
			} catch (Throwable t) {

				t.printStackTrace();
				
			}
			
			throw new ServletException(ex);
			
		}
		
	}
	
}
