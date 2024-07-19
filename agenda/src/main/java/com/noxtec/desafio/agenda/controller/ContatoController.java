package com.noxtec.desafio.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noxtec.desafio.agenda.entity.Contato;
import com.noxtec.desafio.agenda.service.ContatoService;

@RestController
@RequestMapping("/desafio/agenda")
@CrossOrigin("*")
public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Contato contato) {
		try {
			String mensagem = contatoService.save(contato);
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Contato contato, @PathVariable long id) {
		try {
			String mensagem = contatoService.update(contato, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			String mensagem = contatoService.delete(id);
			if (mensagem != null) {
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Não encontrou o contato.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Contato>> findAll() {
		try {
			List<Contato> contatos = this.contatoService.findAll();
			if (!contatos.isEmpty()) {
				return new ResponseEntity<>(contatos, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Contato> findById(@PathVariable long id) {
		try {
			Contato contato = this.contatoService.findById(id);
			return new ResponseEntity<>(contato, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
