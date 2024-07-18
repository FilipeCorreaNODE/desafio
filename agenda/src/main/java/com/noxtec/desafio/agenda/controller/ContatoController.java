package com.noxtec.desafio.agenda.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Contato contato) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			String mensagem = contatoService.save(contato);
			map.put("status", 1);
			map.put("message", mensagem);
			return new ResponseEntity<>(map, HttpStatus.CREATED);

		} catch (Exception e) {
			map.put("status", 0);
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody Contato contato, @PathVariable long id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			String mensagem = contatoService.update(contato, id);
			map.put("status", 1);
			map.put("message", mensagem);
			return new ResponseEntity<>(map, HttpStatus.OK);

		} catch (Exception e) {
			map.put("status", 0);
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			String mensagem = contatoService.delete(id);
			if (mensagem != null) {
				map.put("status", 1);
				map.put("message", mensagem);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map.clear();
				map.put("status", 0);
				map.put("message", "Não encontrou o contato.");
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			map.put("status", 0);
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			List<Contato> contatos = this.contatoService.findAll();
			if (!contatos.isEmpty()) {
				map.put("status", 1);
				map.put("data", contatos);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map.clear();
				map.put("status", 0);
				map.put("message", "Não encontrou contatos.");
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			map.put("status", 0);
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Contato contato = this.contatoService.findById(id);
			map.put("status", 1);
			map.put("data", contato);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Não encontrou o contato.");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}
}
