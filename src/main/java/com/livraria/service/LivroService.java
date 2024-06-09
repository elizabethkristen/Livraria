package com.livraria.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.exception.NegocioException;
import com.livraria.exception.TabelaDeErros;
import com.livraria.model.Livro;
import com.livraria.model.dto.LivroEntradaDto;
import com.livraria.model.dto.LivroSaidaDto;
import com.livraria.repository.LivroRepository;
import com.livraria.validator.LivroValidator;

@Service
public class LivroService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LivroRepository repository;

	@Autowired
	private LivroValidator validator;

	public LivroSaidaDto criarLivro(LivroEntradaDto entrada) {
		validator.validarLivro(entrada);
		Livro entity = mapper.map(entrada, Livro.class);
		Livro entityBanco = repository.save(entity);
		return mapper.map(entityBanco, LivroSaidaDto.class);
	}

	public void alterarLivro(Long id, LivroEntradaDto entrada) {
		validator.validarLivro(entrada);
		Optional<Livro> buscandoLivro = repository.findById(id);
		Livro entityBanco = buscandoLivro.get();
		mapper.map(entrada, entityBanco);

		repository.save(entityBanco);

	}

	public LivroSaidaDto pegarUmLivro(Long id) {
		try {
			Optional<Livro> buscandoProduto = repository.findById(id);
			Livro entityBanco = buscandoProduto.get();
			return mapper.map(entityBanco, LivroSaidaDto.class);
		} catch (NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.LIVRO_NAO_ENCONTRADO);
		}

	}

	public void excluirLivro(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(TabelaDeErros.LIVRO_NAO_ENCONTRADO);
		}
	}

	public List<LivroSaidaDto> listarLivros() {
		List<Livro> listarLivros = repository.findAll();
		return mapper.map(listarLivros, new TypeToken<List<LivroSaidaDto>>() {
		}.getType());
	}

}
