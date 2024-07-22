package com.noxtec.desafio.agenda.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noxtec.desafio.agenda.entity.Contato;
import com.noxtec.desafio.agenda.service.ContatoService;

@WebMvcTest(ContatoController.class)
public class ContatoControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContatoService contatoService;

    private Contato contato;
    private List<Contato> contatoList;

    @BeforeEach
    void setup() {
        contato = new Contato();
        contato.setId(1L);
        contato.setNome("NomeTeste");
        contato.setEmail("Email@Teste");
        contato.setTelefone("3333");
        contato.setCelular("9999");
        contato.setFavorito('s');
        contato.setAtivo('s');

        contatoList = new ArrayList<>();
        contatoList.add(contato);

        when(contatoService.save(any(Contato.class))).thenReturn("NomeTeste criado com sucesso!");
        when(contatoService.update(any(Contato.class), any(Long.class))).thenReturn("NomeTeste atualizado com sucesso!");
        when(contatoService.delete(1L)).thenReturn("Contato deletado com sucesso!");
        when(contatoService.delete(2L)).thenReturn(null);
        when(contatoService.findAll()).thenReturn(contatoList);
        when(contatoService.findById(1L)).thenReturn(contato);
        when(contatoService.existsByCelular("9999")).thenReturn(true);
    }
    
    @Test
    void saveContato() throws Exception {
        mockMvc.perform(post("/desafio/agenda/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(contato)))
                .andExpect(status().isCreated())
                .andExpect(content().string("NomeTeste criado com sucesso!"));
    }
    
    @Test
    void updateContato() throws Exception {
        mockMvc.perform(put("/desafio/agenda/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(contato)))
                .andExpect(status().isOk())
                .andExpect(content().string("NomeTeste atualizado com sucesso!"));
    }

    @Test
    void deleteContato() throws Exception {
        mockMvc.perform(delete("/desafio/agenda/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Contato deletado com sucesso!"));
    }

    @Test
    void deleteContatoNotFound() throws Exception {
        mockMvc.perform(delete("/desafio/agenda/delete/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Não encontrou o contato."));
    }

    @Test
    void findAllContatos() throws Exception {
        mockMvc.perform(get("/desafio/agenda/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].celular").value("9999"));
    }

    @Test
    void findContatoById() throws Exception {
        mockMvc.perform(get("/desafio/agenda/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("NomeTeste"));
    }

    @Test
    void existsByCelular() throws Exception {
        mockMvc.perform(get("/desafio/agenda/existsByCelular/9999"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

}
