package com.livraria.model.dto;


import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LivroEntradaDto {


	@ApiModelProperty(example = "A culpa é das estrelas", value = "Nome do Livro", required = true)
	@NotBlank(message = "campo obrigatório")
	@Size( max=200, message="ERRO, máximo permitido é 200 caracters")
	private String nome;

	@ApiModelProperty(example = "50.00", value = "Preco do Livro", required = true)
	@Digits(integer = 10, fraction = 2, message = "inválido")
	private BigDecimal preco;

	@ApiModelProperty(example = "ROMANCE", value = "Gênero do livro", required = true)
	@NotNull(message = "informe a forma de pagamento")
	@Pattern(regexp = "ROMANCE|ACAO|DRAMA|SUSPENSE", message = "inválido")
	private String genero;

	@ApiModelProperty(example = "Intrínseca", value = "Nome da Editora do livro", required = true)
	@NotBlank(message = "campo obrigatório")
	@Size( max=100, message="ERRO, máximo permitido é 100 caracters")
	private String editora;

	@ApiModelProperty(example = "2012", value = "Ano de publicação do livro", required = true)
	private Long anoPublicacao;

	@ApiModelProperty(example = "John Green", value = "Nome do Autor do livro", required = true)
	@NotBlank(message = "campo obrigatório")
	@Size( max=200, message="ERRO, máximo permitido é 200 caracters")
	private String autor;

}
