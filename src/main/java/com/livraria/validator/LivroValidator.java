package com.livraria.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livraria.exception.NegocioException;
import com.livraria.exception.TabelaDeErros;
import com.livraria.model.dto.LivroEntradaDto;
import com.livraria.repository.LivroRepository;

@Component
public class LivroValidator {

	@Autowired
	private LivroRepository repository;


	public void validarLivro(LivroEntradaDto entrada) {
		if (repository.existsByNome(entrada.getNome())) {
			throw new NegocioException(TabelaDeErros.LIVRO_JA_CADASTRADO);
		}
	}

}
