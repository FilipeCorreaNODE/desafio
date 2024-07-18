package com.noxtec.desafio.agenda.service;

import java.util.List;

import com.noxtec.desafio.agenda.entity.Contato;

public interface ContatoService {

	public String save(Contato contato);

	public String update(Contato contato, long id);

	public String delete(long id);

	public List<Contato> findAll();

	public Contato findById(long id);

}
