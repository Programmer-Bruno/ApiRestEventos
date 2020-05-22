package com.qintess.eventosApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.eventosApi.dados.EventoRepo;
import com.qintess.eventosApi.dados.VendaIngressoRepo;
import com.qintess.eventosApi.dados.VendaRepo;
import com.qintess.eventosApi.model.Evento;
import com.qintess.eventosApi.model.Venda;
import com.qintess.eventosApi.model.VendaIngresso;

@RestController
@RequestMapping("/api")
public class VendaIngressoController {

	@Autowired
	private VendaIngressoRepo vendaIngressoRepo;
	
	@Autowired
	private EventoRepo eventoRepo;
	
	@Autowired 
	private VendaRepo vendaRepo;
	
	//METODO PARA BUSCAR TODOS
	@GetMapping("/vendaIngresso")
	@ResponseStatus(HttpStatus.OK)
	public List<VendaIngresso> getVendaIngressos() {
		return vendaIngressoRepo.findAll();
	}
	
	//METODO PARA BUSCAR POR ID
	@GetMapping("/vendaIngresso/{codigoVendaIngresso}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<VendaIngresso> getVendaIngressoPorId(@PathVariable("codigoVendaIngresso") Long codigoVendaIngresso) {
		return vendaIngressoRepo.findById(codigoVendaIngresso);
	}
	
	
	//METODO PARA INSERIR
	@PostMapping("/vendaIngresso/{codigoEvento}/{codigoVenda}")
	@ResponseStatus(HttpStatus.OK)
	public String setVendaIngresso(@RequestBody VendaIngresso vendaIngresso,
												@PathVariable("codigoEvento") Long codigoEvento,
												@PathVariable("codigoVenda") Long codigoVenda) {
		
		Evento evento = eventoRepo.findById(codigoEvento).orElse(new Evento());
		Venda venda = vendaRepo.findById(codigoVenda).orElse(new Venda());
		
		if (evento != null && venda != null) {
			int quantidade = vendaIngresso.getQuantidade();
			if (evento.getQuant_ingresso() <= 0) {
				return "Venda não pode ser concluida, pois os ingressos estão esgotados";
			}
			evento.setQuant_ingresso(evento.getQuant_ingresso() - quantidade);
			
			double valorUnitario = vendaIngresso.getValor_unitario();
			double valorTotal = quantidade * valorUnitario;
			venda.setValor_total(valorTotal);
			
			evento.setIngressos(vendaIngresso);
			venda.addIngresso(vendaIngresso);
			
			
			vendaIngresso.setEvento(evento);
			vendaIngresso.setVenda(venda);
		}
	
		vendaIngressoRepo.save(vendaIngresso);
		return "Venda Realizada com sucesso";
	}
	
	//METODO PARA ALTERAR
	@PutMapping("/vendaIngresso/{codigoVendaIngresso}")
	@ResponseStatus(HttpStatus.OK)
	public String updateVendaIngresso(@RequestBody VendaIngresso vendaIngresso,
												   @PathVariable("codigoVendaIngresso") Long codigoVendaIngresso) {
		
		VendaIngresso vendaIngressoAntiga = vendaIngressoRepo.findById(codigoVendaIngresso).orElse(new VendaIngresso());
		if (vendaIngresso.getQuantidade() != 0) {
			int quantidadeNova = vendaIngressoAntiga.getQuantidade() - vendaIngresso.getQuantidade();			
			vendaIngressoAntiga.setQuantidade(quantidadeNova);
		}
		if (vendaIngresso.getValor_unitario() != 0) {
			vendaIngressoAntiga.setValor_unitario(vendaIngresso.getValor_unitario());
		}
		Evento evento = eventoRepo.findById(vendaIngressoAntiga.getEvento().getCodigo()).orElse(new Evento());
		Venda venda = vendaRepo.findById(vendaIngressoAntiga.getVenda().getCodigo()).orElse(new Venda());
		int quantidade =  vendaIngresso.getQuantidade();
		if (evento.getQuant_ingresso() <= 0) {
			return "Venda não pode ser concluida, pois os ingressos estão esgotados";
		}
		int novaQuantidadeEvento = evento.getQuant_ingresso() - (-vendaIngressoAntiga.getQuantidade()); 
		evento.setQuant_ingresso(novaQuantidadeEvento);
		
		double valorUnitario = vendaIngressoAntiga.getValor_unitario();
		double valorTotal = quantidade * valorUnitario;
		
		venda.setValor_total(valorTotal);
		vendaIngressoRepo.save(vendaIngressoAntiga);
		return "Venda do ingresso alterada com sucesso";	
	}
	
	//METODO PARA DELETAR
	@DeleteMapping("/vendaIngresso/{codigoVendaIngresso}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteVendaIngresso(@PathVariable("codigoVendaIngresso") Long codigoVendaIngresso) {
		VendaIngresso vendaIngresso = vendaIngressoRepo.findById(codigoVendaIngresso).orElse(new VendaIngresso());
		Evento evento = eventoRepo.findById(vendaIngresso.getEvento().getCodigo()).orElse(new Evento());
		Venda venda = vendaRepo.findById(vendaIngresso.getVenda().getCodigo()).orElse(new Venda());
		
		evento.removeIngresso(vendaIngresso);
		venda.removeIngresso(vendaIngresso);
		
		vendaIngressoRepo.deleteById(codigoVendaIngresso);
		return "Venda do ingresso deletada com sucesso";
	}
}
