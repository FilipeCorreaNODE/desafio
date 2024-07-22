package com.noxtec.desafio.agenda.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.noxtec.desafio.agenda.entity.Contato;
import com.noxtec.desafio.agenda.repository.ContatoRepository;

@SpringBootTest
public class ContatoServiceTest {

	@Autowired
	ContatoService contatoService;

	@MockBean
	ContatoRepository contatoRepository;

	@BeforeEach
	void setup() {
		List<Contato> contatos = new ArrayList<>();
		Contato contato = new Contato();
		contato.setNome("NomeTeste");
		contato.setEmail("Email@Teste");
		contato.setTelefone("3333");
		contato.setCelular("9999");
		contato.setFavorito('s');
		contato.setAtivo('s');

		contatos.add(contato);

		when(contatoRepository.save(any(Contato.class))).thenReturn(contato);
		
		when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

		when(contatoRepository.findAll()).thenReturn(contatos);
	}

	@Test
	void findAllContatos() {
		List<Contato> contatos = contatoService.findAll();
		assertEquals(1, contatos.size());
		assertEquals("9999", contatos.get(0).getCelular());
	}

	@Test
	void findContatoById() {
		Contato contato = contatoService.findById(1L);
		assertEquals("NomeTeste", contato.getNome());
	}
	
	@Test
	void saveContato() {
		Contato contato = new Contato();
		contato.setNome("NomeTeste");
		contato.setEmail("Email@Teste");
		contato.setTelefone("3333");
		contato.setCelular("9999");
		contato.setFavorito('s');
		contato.setAtivo('s');
		
		String retorno = contatoService.save(contato);
		
		assertEquals(contato.getNome() +" criado com sucesso!", retorno);
	}

}
