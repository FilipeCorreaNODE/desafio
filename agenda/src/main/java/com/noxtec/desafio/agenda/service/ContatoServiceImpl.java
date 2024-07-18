package com.noxtec.desafio.agenda.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noxtec.desafio.agenda.entity.Contato;
import com.noxtec.desafio.agenda.repository.ContatoRepository;

@Service
public class ContatoServiceImpl implements ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	@Override
	public String save(Contato contato) {

		contato.setDataHoraCadastro(new Date());
		contatoRepository.save(contato);
		return "Contato criado com sucesso!";
	}

	@Override
	public String update(Contato contato, long id) {
		contato.setId(id);
		contato.setDataHoraCadastro(new Date());
		contatoRepository.save(contato);
		return "Contato atualizado com sucesso!";
	}

	@Override
	public String delete(long id) {

		if (contatoRepository.existsById(id)) {
			contatoRepository.deleteById(id);
			return "Contato deletado com sucesso!";
		}
		return null;
	}

	@Override
	public List<Contato> findAll() {

		List<Contato> contatos = contatoRepository.findAll();
		return contatos;
	}

	@Override
	public Contato findById(long id) {

		Contato contato = contatoRepository.findById(id).get();
		return contato;
	}

}