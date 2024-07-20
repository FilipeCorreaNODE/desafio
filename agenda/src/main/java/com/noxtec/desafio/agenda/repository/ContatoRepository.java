package com.noxtec.desafio.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noxtec.desafio.agenda.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	boolean existsByCelular(String celular);

}
