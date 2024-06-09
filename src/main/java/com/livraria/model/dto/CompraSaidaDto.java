package com.livraria.model.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.livraria.model.Cliente;
import com.livraria.model.FormaDePagamento;
import com.livraria.model.Livro;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompraSaidaDto {

	private Long id;
	private Cliente cliente;
	private List<Livro> livros;
	private FormaDePagamento pagamento;
	private BigDecimal totalCompra;

}
