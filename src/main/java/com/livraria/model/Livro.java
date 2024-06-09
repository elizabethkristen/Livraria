package com.livraria.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "livro")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 200)
	private String nome;

	@Column(name = "preco", nullable = false, length = 100)
	private BigDecimal preco;

	@Column(name = "genero", nullable = false, length = 200)
	private Genero genero;

	@Column(name = "editora", nullable = false, length = 200)
	private String editora;

	@Column(nullable = false, name = "ano_publicacao",length = 4)
	private Long anoPublicacao;

	@Column(name = "autor", nullable = false, length = 200)
	private String autor;


}
