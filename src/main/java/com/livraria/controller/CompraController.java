package com.livraria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.livraria.model.dto.CompraEntradaDto;
import com.livraria.model.dto.CompraSaidaDto;
import com.livraria.service.CompraService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("livraria")
@Log4j2
@Validated
public class CompraController {
	
	@Autowired
	CompraService service;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("compra")
	public CompraSaidaDto criarCompra(@Valid @RequestBody CompraEntradaDto entrada) {
		log.info("Recebendo uma requisição: metodo = criar, entradaDto{}", entrada);
		return service.criarCompra(entrada);
	}
	
	@PutMapping("compra/id/{id}")
	public void alterarCompra(@PathVariable("id") Long id, @Valid @RequestBody  CompraEntradaDto alterar) {
		log.info("alterar: {} {}", id, alterar);
		service.alterarCompra(id, alterar);
	}
	
	@GetMapping("compra/id/{id}")
	public CompraSaidaDto pegarUmaCompra(@PathVariable("id") Long id) {
		log.info("PegarUm: {}", id);
		return service.pegarUmaCompra(id);
	}

	@DeleteMapping("compra/id/{id}")
	public void excluirCompra(@PathVariable("id") Long id) {
		log.info("excluir: {}", id);
		service.excluirCompra(id);
	}

	@GetMapping("compra")
	public List<CompraSaidaDto> listarCompras() {
		log.info("listar");
		return service.listarCompras();
	}

}
