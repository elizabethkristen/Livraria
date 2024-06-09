package com.livraria.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteSaidaDto {

	private Long id;
	private String nome;
	private String email;
	private String senha;

}
