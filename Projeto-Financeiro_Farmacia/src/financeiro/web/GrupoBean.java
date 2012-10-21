package financeiro.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import financeiro.grupo.produtos.Grupo;
import financeiro.grupo.produtos.GrupoRN;

@ManagedBean(name = "grupoBean")
@RequestScoped
public class GrupoBean {

	public Grupo grupo = new Grupo();
	private String nome;
	private Integer codigo;
	private List<Grupo> lista;
	private List<SelectItem> grupoSelect;

	public void salvar() {
		
		GrupoRN grupoRN = new GrupoRN();
		grupoRN.salvar(this.grupo);
		this.grupo = new Grupo();
		this.lista = null;

	}

	public void excluir() {

		GrupoRN grupoRN = new GrupoRN();
		grupoRN.excluir(this.grupo);

	}

	public List<Grupo> getLista() {

		if (this.lista == null) {
			
			GrupoRN grupoRN = new GrupoRN();

			this.lista = grupoRN.listar();

		}

		return this.lista;

	}
	
	public List<SelectItem> getGrupoSelect() {
		
		if (this.grupoSelect == null) {
			
			this.grupoSelect = new ArrayList<SelectItem>();

			GrupoRN grupoRN = new GrupoRN();
			List<Grupo> grupos = grupoRN.listar();
			this.montaDadosSelect(this.grupoSelect, grupos, "");
		
		}
		
		return grupoSelect;
		
	}
	

	private void montaDadosSelect(List<SelectItem> select,
			List<Grupo> grupos, String prefixo) {

		SelectItem item = null;
		
		if (grupos != null) {
			
			for (Grupo grupo : grupos) {
				
				item = new SelectItem(grupo, prefixo
						+ grupo.getNome());
				item.setEscape(false);
				select.add(item);
				this.montaDadosSelect(select, null, prefixo
						+ "&nbsp;&nbsp;");
				
			}
			
		}
		
	}

	public Grupo getgrupo() {
		return grupo;
	}

	public void setgrupo(Grupo grupo) {
		this.grupo = grupo;
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

	public void setLista(List<Grupo> lista) {
		this.lista = lista;
	}

}
