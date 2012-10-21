package financeiro.lista;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Lista implements Serializable {

	private static final long serialVersionUID = 8015386999071642323L;

	@Id
	@GeneratedValue
	private Integer codigo;

	private String nome;

	private double tributacao;

	public Lista() {
	}

	public Lista(String nome) {

		this.nome = nome;

	}

	public Integer getCodigo() {

		return codigo;

	}

	public void setCodigo(Integer codigo) {

		this.codigo = codigo;

	}

	public String getNome() {

		return nome;

	}

	public void setNome(String nome) {

		this.nome = nome;

	}

	public double getTributacao() {
		return tributacao;
	}

	public void setTributacao(double tributacao) {
		this.tributacao = tributacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tributacao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Lista other = (Lista) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(tributacao) != Double
				.doubleToLongBits(other.tributacao))
			return false;
		return true;
	}

}