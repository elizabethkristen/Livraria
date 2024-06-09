package com.livraria.model.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompraEntradaDto {
	
	@ApiModelProperty(example = "1", value = "Id do Cliente", required = true)
	@NotNull(message = "informe o id do produto")
	private Long idCliente;
	
	@ApiModelProperty(example = "1", value = "Id do Livro", required = true)
	@NotNull(message = "informe o id do livro")
	private List<Long> idLivros;
	
	@ApiModelProperty(example = "DINHEIRO", value = "Forma de Pagamento", required = true)
	@NotNull(message = "informe a forma de pagamento")
	@Pattern(regexp = "DINHEIRO|CARTAO_DE_CREDITO|CARTAO_DE_DEBITO|BOLETO|PICPAY|PAYPAL", message = "inválido")
	private String pagamento;
	
	@ApiModelProperty(example = "1000.00", value = "Total da Compra", required = true)
	@Digits(integer = 10, fraction = 2, message = "inválido")
	@DecimalMin(value = "20.00", message = "O valor mínimo para finalizar a compra é 20.00")
	private BigDecimal totalCompra;
	
	

	
	
	
	
}
