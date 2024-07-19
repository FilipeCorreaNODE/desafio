package com.noxtec.desafio.agenda.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noxtec.desafio.agenda.entity.Contato;
import com.noxtec.desafio.agenda.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	public String save(Contato contato) {

		contato.setDataHoraCadastro(new Date());
		contatoRepository.save(contato);
		return "Contato criado com sucesso!";
	}

	public String update(Contato contato, long id) {
		contato.setId(id);
		contato.setDataHoraCadastro(new Date());
		contatoRepository.save(contato);
		return "Contato atualizado com sucesso!";
	}

	public String delete(long id) {

		if (contatoRepository.existsById(id)) {
			contatoRepository.deleteById(id);
			return "Contato deletado com sucesso!";
		}
		return null;
	}

	public List<Contato> findAll() {

		List<Contato> contatos = contatoRepository.findAll();
		return contatos;
	}

	public Contato findById(long id) {

		Contato contato = contatoRepository.findById(id).get();
		return contato;
	}

}