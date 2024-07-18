package com.noxtec.desafio.agenda.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="contato_nome")
	private String nome;
	
	@Column(name="contato_email")
	private String email;
	
	@Column(name="contato_celular", length = 11, nullable = false, unique = true)
	private String celular;
	
	@Column(name="contato_telefone")
	private String telefone;
	
	@Column(name="contato_sn_favorito")
	private char favorito;
	
	@Column(name="contato_sn_ativo")
	private char ativo;
	
	@Column(name="contato_dh_cad")
	private Date dataHoraCadastro;
	
}
