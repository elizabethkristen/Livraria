package com.livraria.model.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteEntradaDto {
	
	@ApiModelProperty(example = "Luiza de Araujo", value = "Nome do cliente", required = true)
	@NotBlank(message = "campo obrigatório")
	@Size( max=200, message="ERRO, máximo permitido é 200 caracters")
	private String nome;
	
	@ApiModelProperty(example = "luiza@gmail.com", value = "Email do cliente", required = true)
	@Email
	@NotBlank(message = "campo obrigatório")
	@Size( max=100, message="ERRO, email inválido")
	private String email;

	@ApiModelProperty(example = "123", value = "Senha do Cliente", required = true)
	@NotBlank(message = "campo obrigatório")
	@Size( max=100, message="ERRO, máximo permitido é 10 caracters")
	private String senha;

	
	

}
