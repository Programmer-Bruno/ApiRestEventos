package com.qintess.eventosApi.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CasaDeShow {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	private String nome;
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private int capacidade;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "casaDeShow", fetch = FetchType.EAGER)
	private List<Evento> eventos = new ArrayList<>();
	
	public CasaDeShow() {
	}

	public CasaDeShow(long codigo, String nome, String cep, String logradouro, String complemento, String bairro,
			String cidade, String uf, int capacidade) {
		this.codigo = codigo;
		this.nome = nome;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.capacidade = capacidade;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEvento(Evento evento) {
		this.eventos.add(evento);
	}
	public void removeEvento(Evento evento) {
		this.eventos.remove(evento);
	}

	@Override
	public String toString() {
		return "CasaDeShow [codigo=" + codigo + ", nome=" + nome + ", cep=" + cep + ", logradouro=" + logradouro
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf
				+ ", capacidade=" + capacidade + ", eventos=" + eventos + "]";
	}
}
