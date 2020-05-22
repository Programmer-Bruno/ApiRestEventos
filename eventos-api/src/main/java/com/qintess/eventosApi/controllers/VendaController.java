package com.qintess.eventosApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.eventosApi.dados.ClienteRepo;
import com.qintess.eventosApi.dados.VendaRepo;
import com.qintess.eventosApi.model.Cliente;
import com.qintess.eventosApi.model.Venda;

@RestController
@RequestMapping("/api")
public class VendaController {
	
	@Autowired
	private VendaRepo vendaRepo;
	
	@Autowired
	private ClienteRepo clienteRepo;
	
	@GetMapping("/venda")
	@ResponseStatus(HttpStatus.OK)
	public List<Venda> getVendas() {
		
		return vendaRepo.findAll();
	}
	
	@GetMapping("/venda/{codigoVenda}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Venda> getVendaByID(@PathVariable("codigoVenda") Long codigoVenda) {
		return vendaRepo.findById(codigoVenda);
	}
	
	@PostMapping("/venda/{codigoCliente}") 
	@ResponseStatus(HttpStatus.OK)
	public List<Venda> setVenda(@RequestBody Venda venda, @PathVariable("codigoCliente") Long codigoCliente) {
		
		Cliente cliente = clienteRepo.findById(codigoCliente).orElse(new Cliente());
		if (cliente != null) {
			cliente.setVenda(venda);
			venda.setCliente(cliente);
		}
		
		vendaRepo.save(venda);
		return vendaRepo.findAll();
		
	}
	
	@DeleteMapping("/venda/{codigoVenda}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteVenda(@PathVariable("codigoVenda") Long codigoVenda) {
		Venda venda = vendaRepo.findById(codigoVenda).orElse(new Venda());
		Cliente cliente = clienteRepo.findById(venda.getCliente().getCodigo()).orElse(new Cliente());
		cliente.removeVenda(venda);
		vendaRepo.deleteById(codigoVenda);
		return "Venda Deletada com sucesso";
	}

}
