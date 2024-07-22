package com.noxtec.desafio.agenda.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
	
	private Contato contato;

	@BeforeEach
	void setup() {
		contato = new Contato();
		contato.setNome("NomeTeste");
		contato.setEmail("Email@Teste");
		contato.setTelefone("3333");
		contato.setCelular("9999");
		contato.setFavorito('s');
		contato.setAtivo('s');

		List<Contato> contatos = new ArrayList<>();
		contatos.add(contato);

		when(contatoRepository.save(any(Contato.class))).thenReturn(contato);
		
		when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

		when(contatoRepository.findAll()).thenReturn(contatos);
		
		when(contatoRepository.existsById(1L)).thenReturn(true);
		
        when(contatoRepository.existsById(2L)).thenReturn(false);
        
        when(contatoRepository.existsByCelular("9999")).thenReturn(true);
        
        when(contatoRepository.existsByCelular("8888")).thenReturn(false);
	}

	@Test
	void saveContato() {
		String retorno = contatoService.save(contato);
		assertEquals(contato.getNome() +" criado com sucesso!", retorno);
	}
	
	@Test
	void findContatoById() {
		Contato contato = contatoService.findById(1L);
		assertEquals("NomeTeste", contato.getNome());
	}
	
	@Test
	void findAllContatos() {
		List<Contato> contatos = contatoService.findAll();
		assertEquals(1, contatos.size());
		assertEquals("9999", contatos.get(0).getCelular());
	}
	
	@Test
    void updateContato() {
        contato.setNome("NomeAtualizado");
        String retorno = contatoService.update(contato, 1L);
        assertEquals(contato.getNome() + " atualizado com sucesso!", retorno);
    }
	
	@Test
    void deleteContato() {
        String retorno = contatoService.delete(1L);
        assertEquals("Contato deletado com sucesso!", retorno);
    }
	
	@Test
    void deleteContatoNotFound() {
        String retorno = contatoService.delete(2L);
        assertNull(retorno);
    }
	
	@Test
    void existsByCelularTrue() {
        assertTrue(contatoService.existsByCelular("9999"));
    }

    @Test
    void existsByCelularFalse() {
        assertFalse(contatoService.existsByCelular("8888"));
    }

}
