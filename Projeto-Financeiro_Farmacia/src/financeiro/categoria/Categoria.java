package financeiro.categoria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import financeiro.grupo.produtos.Grupo;
import financeiro.usuario.Usuario;

@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 2012480144613107499L;

	@Id
	@GeneratedValue
	private Integer codigo;

	@ManyToOne
	@JoinColumn(name = "categoria_pai", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "fk_categoria_categoria")
	private Categoria pai;

	@OneToOne
	@org.hibernate.annotations.ForeignKey(name = "fk_categoria_grupo")
	private Grupo grupo;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@org.hibernate.annotations.ForeignKey(name = "fk_categoria_usuario")
	private Usuario usuario;

	private String descricao;

	private int fator;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "categoria_pai", updatable = false)
	@org.hibernate.annotations.OrderBy(clause = "descricao asc")
	private List<Categoria> filhos;

	public Categoria() {
	}

	public Categoria(Categoria pai, Usuario usuario, String descricao, int fator) {
		
		this.pai = pai;
		this.usuario = usuario;
		this.descricao = descricao;
		this.fator = fator;
		
	}
	
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Integer getCodigo() {
		
		return codigo;
		
	}

	public void setCodigo(Integer codigo) {
		
		this.codigo = codigo;
		
	}

	public String getDescricao() {
		
		return descricao;
		
	}

	public void setDescricao(String descricao) {
		
		this.descricao = descricao;
		
	}

	public Categoria getPai() {
		
		return pai;
		
	}

	public void setPai(Categoria pai) {
		
		this.pai = pai;
		
	}

	public Usuario getUsuario() {
		
		return usuario;
		
	}

	public void setUsuario(Usuario usuario) {
		
		this.usuario = usuario;
		
	}

	public List<Categoria> getFilhos() {
		
		return filhos;
		
	}

	public void setFilhos(List<Categoria> filhos) {
		
		this.filhos = filhos;
		
	}

	public int getFator() {
		
		return fator;
		
	}

	public void setFator(int fator) {
		
		this.fator = fator;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + fator;
		result = prime * result + ((filhos == null) ? 0 : filhos.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((pai == null) ? 0 : pai.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Categoria other = (Categoria) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fator != other.fator)
			return false;
		if (filhos == null) {
			if (other.filhos != null)
				return false;
		} else if (!filhos.equals(other.filhos))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (pai == null) {
			if (other.pai != null)
				return false;
		} else if (!pai.equals(other.pai))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}