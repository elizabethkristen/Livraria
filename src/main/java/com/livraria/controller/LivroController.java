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

import com.livraria.model.dto.LivroEntradaDto;
import com.livraria.model.dto.LivroSaidaDto;
import com.livraria.service.LivroService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("livraria")
@Log4j2
@Validated
public class LivroController {

	@Autowired
	LivroService service;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("livro")
	public LivroSaidaDto criarLivro(@Valid @RequestBody LivroEntradaDto entrada) {
		log.info("Recebendo uma requisição: metodo = criar, entradaDto{}", entrada);
		return service.criarLivro(entrada);
	}
	
	@PutMapping("livro/id/{id}")
	public void alterarLivro(@PathVariable("id") Long id, @Valid @RequestBody  LivroEntradaDto entrada) {
		log.info("alterar: {} {}", id, entrada);
		service.alterarLivro(id, entrada);
	}

	@GetMapping("livro/id/{id}")
	public LivroSaidaDto pegarUmLivro(@PathVariable("id") Long id) {
		log.info("PegarUm: {}", id);
		return service.pegarUmLivro(id);
	}

	@DeleteMapping("livro/id/{id}")
	public void excluirLivro(@PathVariable("id") Long id) {
		log.info("excluir: {}", id);
		service.excluirLivro(id);
	}

	@GetMapping("livro")
	public List<LivroSaidaDto> listarLivros() {
		log.info("listar");
		return service.listarLivros();
	}

}
