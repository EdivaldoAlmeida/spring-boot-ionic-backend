package com.edivaldova.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //informa que ela será uma entidade
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id //Esse será a chave primária
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	/*Mapeando o relacionamento Categoria x produto, através das anotações do JPA
	 * 1 - Coloca a anomação JPA indicando muitos para muitos
	 * 2 - Cria a anotação @JoinTable para indicar a tabela que irá fazer esse relacionamento no BD relacional
	 * 3 - "PRODUTO-CATEGORIA é o nome dado a essa tabela (que ferá o relacionamento) 
	 * joinColumns - Utilizado para poder informar o nome da chave estrangeira (de produto)
	 * estando-se dentro dessa classe.
	 * inverseJoinColumns - Utilizada para informar o nome da outra chave estrangeira (que irá referenciar Categoria)
	*/

	@JsonIgnore 
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn(name = "produto_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
				)
	
	//declarando uma lista de categorias para atender a associação do projeto
	//O nome dado na associação deverá ser o mesmo nome aqui (categorias)
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore //Produto não necessita serializar a lista de itens só o inverso
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Produto() {
	}
	
	//Na criação do construtor com parâmetros as coleções não entram. É o caso de Lista<Categoria>
	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	/*Como um produto conhece os pedidos dele então temos que
	 * criar um método que varra os itens de pedido e monte uma
	 * lista de pedidos associada a estes itens. 
	 */
	@JsonIgnore //Também deve ignorar para não serializar
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	

}
