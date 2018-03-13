package com.edivaldova.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*Fazendo o mapeamento da Class Catetoria para que se possa criar a tabela categoria automaticamente no banco de dados H2.
 *@Entity - informa que essa classe será uma entidade do JPA
 */


@Entity
public class Categoria implements Serializable {
	/* Implementar Serializable para que os objetos da classe (Categoria) possam ser convertidos para uma sequencia de bytes
	 * para que eles possam ser gravados em arquivos, trafegar em redes, etc. É uma exigência da linguagem.
	 */
	private static final long serialVersionUID = 1L; //1L indica que é a primeira versão da classe
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Definindo a estratégia de geração automática de Ids das categorias
	private Integer id;
	private String nome;
	
		
	public Categoria() {
		
	}


	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}