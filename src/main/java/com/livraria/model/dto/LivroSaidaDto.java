package com.livraria.model.dto;


import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.livraria.model.Genero;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LivroSaidaDto {
	
	private Long id;
	private String nome;
	private BigDecimal preco;
	private String sinopse;
	private Genero genero;
	private Long anoPublicacao;
	private String autor;



}
