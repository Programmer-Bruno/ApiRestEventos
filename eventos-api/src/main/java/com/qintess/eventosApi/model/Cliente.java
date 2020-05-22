package com.qintess.eventosApi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private int idade;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String cpf;
	@Column(nullable = false)
	private String senha;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.EAGER)
	private List<Venda> vendas = new ArrayList<>();

	public Cliente() {
	}

	public Cliente(long codigo, String nome, int idade, String email, String cpf) {
		this.codigo = codigo;
		this.nome = nome;
		this.idade = idade;
		this.email = email;
		this.cpf = cpf;
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVenda(Venda venda) {
		this.vendas.add(venda);
	}

	public void removeVenda(Venda venda) {
		this.vendas.remove(venda);
	}
	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + ", idade=" + idade + ", email=" + email + ", cpf=" + cpf
				+ ", senha=" + senha + ", compras=" + vendas + "]";
	}
	
	
	
}
