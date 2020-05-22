package com.qintess.eventosApi.controllers;

import java.util.List;

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

import com.qintess.eventosApi.dados.ClienteRepo;
import com.qintess.eventosApi.model.Cliente;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private ClienteRepo repo;
	
	@GetMapping("/cliente")
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> getClientes() {
		return repo.findAll();
	}
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.OK)
	public void addCliente(@RequestBody Cliente cliente) {
		repo.save(cliente);
	}
	
	@GetMapping("/cliente/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public Object getCliente(@PathVariable("codigo") Long codigo) {
		return repo.findById(codigo);
	}
	
	@PutMapping("/cliente")
	@ResponseStatus(HttpStatus.OK)
	public Cliente updateCliente(@RequestBody Cliente cliente) {
		return repo.saveAndFlush(cliente);
	}
	
	@DeleteMapping("/cliente/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable("codigo") Long codigo) {
		repo.deleteById(codigo);
	}
}
