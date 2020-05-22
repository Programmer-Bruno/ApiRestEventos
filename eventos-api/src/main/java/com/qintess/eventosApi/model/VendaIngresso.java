package com.qintess.eventosApi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VendaIngresso {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	private int quantidade;
	private double valor_unitario;
	
	@ManyToOne
	private Evento evento;
	@ManyToOne
	private Venda venda;
	
	public VendaIngresso() {
	}

	public VendaIngresso(int quantidade, double valor_unitario, Evento evento, Venda venda) {
		this.quantidade = quantidade;
		this.valor_unitario = valor_unitario;
		this.evento = evento;
		this.venda = venda;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	@JsonIgnore
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@JsonIgnore
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
}
