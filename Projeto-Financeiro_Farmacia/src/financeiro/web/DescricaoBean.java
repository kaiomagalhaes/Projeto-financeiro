package financeiro.web;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import financeiro.descricao.Descricao;
import financeiro.descricao.DescricaoRN;
import financeiro.web.util.ContextoUtil;

@ManagedBean(name = "descricaoBean")
@RequestScoped
public class DescricaoBean {

	public Descricao descricao = new Descricao();
	private String nome;
	private Integer codigo;
	private List<Descricao> lista;

	public void salvar() {
		
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.descricao.setUsuario(contextoBean.getUsuarioLogado());

		DescricaoRN descricaoRN = new DescricaoRN();
		descricaoRN.salvar(this.descricao);
		this.descricao = new Descricao();
		this.lista = null;

	}

	public void excluir() {

		DescricaoRN descricaoRN = new DescricaoRN();
		descricaoRN.excluir(this.descricao);

	}

	public List<Descricao> getLista() {

		if (this.lista == null) {
				
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			
			DescricaoRN descricaoRN = new DescricaoRN();

			this.lista = descricaoRN.listar(contextoBean.getUsuarioLogado());

		}

		return this.lista;

	}

	public Descricao getdescricao() {
		return descricao;
	}

	public void setdescricao(Descricao descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setLista(List<Descricao> lista) {
		this.lista = lista;
	}

}

