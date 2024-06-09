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
import com.livraria.model.Cliente;

import com.livraria.model.dto.ClienteAlterarDto;
import com.livraria.model.dto.ClienteEntradaDto;
import com.livraria.model.dto.ClienteSaidaDto;
import com.livraria.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteSaidaDto criarCliente(ClienteEntradaDto entrada) {
		Cliente entity = mapper.map(entrada, Cliente.class);

		Cliente entityBanco = clienteRepository.save(entity);

		return mapper.map(entityBanco, ClienteSaidaDto.class);
	}

	public void alterarCliente(Long id, ClienteAlterarDto alterar) {
		try {
			Optional<Cliente> buscandoCliente = clienteRepository.findById(id);
			Cliente entityBanco = buscandoCliente.get();
			mapper.map(alterar, entityBanco);

			clienteRepository.save(entityBanco);

		} catch (NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}

	}

	public ClienteSaidaDto pegarUmCliente(Long id) {
		try {
			Optional<Cliente> buscandoCliente = clienteRepository.findById(id);

			Cliente entityBanco = buscandoCliente.get();
			return mapper.map(entityBanco, ClienteSaidaDto.class);
			
		} catch (NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}
	}

	public void excluirCliente(Long id) {
		try {
			clienteRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);

		}
	}

	public List<ClienteSaidaDto> listarCliente() {
		List<Cliente> listaClientes = clienteRepository.findAll();
		return mapper.map(listaClientes, new TypeToken<List<ClienteSaidaDto>>() {
		}.getType());
	}



}
