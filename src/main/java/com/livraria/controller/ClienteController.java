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

import com.livraria.model.dto.ClienteAlterarDto;
import com.livraria.model.dto.ClienteEntradaDto;
import com.livraria.model.dto.ClienteSaidaDto;
import com.livraria.service.ClienteService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("livraria")
@Log4j2
@Validated
public class ClienteController {

	@Autowired
	ClienteService service;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("cliente")
	public ClienteSaidaDto criarCliente(@Valid @RequestBody ClienteEntradaDto entrada) {
		log.info("Recebendo uma requisição: metodo = criar, entradaDto{}", entrada);
		return service.criarCliente(entrada);
	}

	@PutMapping("cliente/id/{id}")
	public void alterarCliente(@PathVariable("id") Long id, @Valid @RequestBody  ClienteAlterarDto alterar) {
		log.info("alterar: {} {}", id, alterar);
		service.alterarCliente(id, alterar);
	}

	@GetMapping("cliente/id/{id}")
	public ClienteSaidaDto pegarUmCliente(@PathVariable("id") Long id) {
		log.info("PegarUm: {}", id);
		return service.pegarUmCliente(id);
	}

	@DeleteMapping("cliente/id/{id}")
	public void excluirCliente(@PathVariable("id") Long id) {
		log.info("excluir: {}", id);
		service.excluirCliente(id);
	}

	@GetMapping("cliente")
	public List<ClienteSaidaDto> listarCliente() {
		log.info("listar");
		return service.listarCliente();
	}

	
}
