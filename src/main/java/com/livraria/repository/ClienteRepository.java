package com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{


	boolean existsByEmailAndSenha(String email, String senha);

}
