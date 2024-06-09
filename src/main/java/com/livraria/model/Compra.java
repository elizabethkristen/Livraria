package com.livraria.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "compra")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_livro")
	private List<Livro> livros;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = true, length = 30)
	private FormaDePagamento pagamento;
	
	@Column(name = "total_compra")
	private BigDecimal totalCompra;

}
