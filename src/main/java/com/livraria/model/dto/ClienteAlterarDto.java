package com.livraria.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteAlterarDto {

	
	@ApiModelProperty(example = "123", value = "Senha do Cliente", required = true)
	@NotBlank(message = "campo obrigatório")
	@Size(max = 200, message = "ERRO, máximo permitido é 10 caracters")
	private String senha;


}
