package financeiro.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import financeiro.lista.Lista;
import financeiro.lista.ListaRN;

@ManagedBean(name = "listaBean")
@RequestScoped
public class ListaBean {

	public Lista listaSelecionada = new Lista();
	private String nome;
	private Integer codigo;
	private List<Lista> lista;
	private List<SelectItem> listaSelect;

	public void salvar() {

		ListaRN listaSelecionadaRN = new ListaRN();
		listaSelecionadaRN.salvar(this.listaSelecionada);
		this.listaSelecionada = new Lista();
		this.lista = null;

	}

	public void excluir() {

		ListaRN listaSelecionadaRN = new ListaRN();
		listaSelecionadaRN.excluir(this.listaSelecionada);

	}

	public List<Lista> getLista() {

		if (this.lista == null) {

			ListaRN listaSelecionadaRN = new ListaRN();

			this.lista = listaSelecionadaRN.listar();

		}

		return this.lista;

	}

	public List<SelectItem> getListaSelect() {

		if (this.listaSelect == null) {

			this.listaSelect = new ArrayList<SelectItem>();

			ListaRN listaRN = new ListaRN();
			List<Lista> listas = listaRN.listar();
			this.montaDadosSelect(this.listaSelect, listas, "");

		}

		return listaSelect;

	}

	private void montaDadosSelect(List<SelectItem> select, List<Lista> listas,
			String prefixo) {

		SelectItem item = null;

		if (listas != null) {

			for (Lista lista : listas) {

				item = new SelectItem(lista, prefixo + lista.getNome());
				item.setEscape(false);
				select.add(item);
				this.montaDadosSelect(select, null, prefixo + "&nbsp;&nbsp;");

			}

		}

	}

	public Lista getlistaSelecionada() {
		return listaSelecionada;
	}

	public void setlistaSelecionada(Lista listaSelecionada) {
		this.listaSelecionada = listaSelecionada;
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

	public void setLista(List<Lista> lista) {
		this.lista = lista;
	}

}