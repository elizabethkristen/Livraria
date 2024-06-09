package com.livraria.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.exception.NegocioException;
import com.livraria.exception.TabelaDeErros;
import com.livraria.model.Cliente;
import com.livraria.model.Compra;
import com.livraria.model.Livro;
import com.livraria.model.dto.CompraEntradaDto;
import com.livraria.model.dto.CompraSaidaDto;
import com.livraria.repository.ClienteRepository;
import com.livraria.repository.CompraRepository;
import com.livraria.repository.LivroRepository;

@Service
public class CompraService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private LivroRepository livroRepository;


	public CompraSaidaDto criarCompra(CompraEntradaDto entrada) {

		Compra entity = mapper.map(entrada, Compra.class);

		Optional<Cliente> buscandoCliente = clienteRepository.findById(entrada.getIdCliente());
		if (buscandoCliente.isPresent()) {
			Cliente cliente = buscandoCliente.get();
			entity.setCliente(cliente);
		} else {
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}
		
		List<Long> idsLivros = entrada.getIdLivros();
		List<Livro> livros = livroRepository.findAllById(idsLivros);

		if (!livros.isEmpty()) {
			entity.setLivros(livros);
		} else {
			throw new NegocioException(TabelaDeErros.LIVRO_NAO_ENCONTRADO);
		}


	
		BigDecimal valorTotalLivros = BigDecimal.ZERO;
		for (Livro livro : entity.getLivros()) {
			BigDecimal precoLivro = livro.getPreco();
			BigDecimal valorLivro = precoLivro.multiply(BigDecimal.ONE);
			valorTotalLivros = valorTotalLivros.add(valorLivro);
		}

		entity.setTotalCompra(valorTotalLivros);

		Compra entityBanco = compraRepository.save(entity);
		return mapper.map(entityBanco, CompraSaidaDto.class);
	}
	
	public void alterarCompra(Long id, CompraEntradaDto alterar) {
		try {
			Optional<Compra> buscandoCompra = compraRepository.findById(id);
			Compra entityBanco = buscandoCompra.get();
			mapper.map(alterar, entityBanco);

			Optional<Cliente> buscandoCliente = clienteRepository.findById(alterar.getIdCliente());
			if (buscandoCliente.isPresent()) {
				Cliente cliente = buscandoCliente.get();
				entityBanco.setCliente(cliente);
			} else {
				throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
			}
			
			List<Long> idsLivros = alterar.getIdLivros();
			List<Livro> livros = livroRepository.findAllById(idsLivros);

			if (!livros.isEmpty()) {
				entityBanco.setLivros(livros);
			} else {
				throw new NegocioException(TabelaDeErros.LIVRO_NAO_ENCONTRADO);
			}


			BigDecimal valorTotalLivros = BigDecimal.ZERO;
			for (Livro livro : entityBanco.getLivros()) {
				BigDecimal precoLivro = livro.getPreco();
				BigDecimal valorLivro = precoLivro.multiply(BigDecimal.ONE); 
				valorTotalLivros = valorTotalLivros.add(valorLivro);
			}

			compraRepository.save(entityBanco);

		} catch (NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}

	}
	public CompraSaidaDto pegarUmaCompra(Long id) {
		try {
			Optional<Compra> buscandoCompra = compraRepository.findById(id);

			Compra entityBanco = buscandoCompra.get();
			return mapper.map(entityBanco, CompraSaidaDto.class);
		} catch (NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.COMPRA_NAO_ENCONTRADA);
		}

	}

	public void excluirCompra(Long id) {
		try {
			compraRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(TabelaDeErros.COMPRA_NAO_ENCONTRADA);
		}catch(DataIntegrityViolationException e) {
			throw new NegocioException(TabelaDeErros.COMPRA_CINCULADA_A_CLIENTE);
		}
	}

	public List<CompraSaidaDto> listarCompras() {
		List<Compra> listarCompras = compraRepository.findAll();
		return mapper.map(listarCompras, new TypeToken<List<CompraSaidaDto>>() {
		}.getType());
	}

}
