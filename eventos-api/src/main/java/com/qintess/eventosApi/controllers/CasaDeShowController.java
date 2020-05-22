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

import com.qintess.eventosApi.dados.CasaDeShowRepo;
import com.qintess.eventosApi.model.CasaDeShow;


@RestController
@RequestMapping("/api")
public class CasaDeShowController {
	
	@Autowired
	private CasaDeShowRepo casaDeShowRepo;
	
	@GetMapping("/casaDeShow")
	@ResponseStatus(HttpStatus.OK)
	public List<CasaDeShow> getCasaDeShows () {
		return casaDeShowRepo.findAll();
	}
	
	@PostMapping("/casaDeShow")
	@ResponseStatus(HttpStatus.OK)
	public List<CasaDeShow> addCasaDeShow(@RequestBody CasaDeShow casaDeShow) {
		casaDeShowRepo.save(casaDeShow);
		return casaDeShowRepo.findAll();
	}
	
	@GetMapping("/casaDeShow/{casaDeShowCodigo}")
	public Optional<CasaDeShow> findCasaDeShow(@PathVariable("casaDeShowCodigo") Long casaDeShowCodigo) {
		return casaDeShowRepo.findById(casaDeShowCodigo);
	}
	
	@PutMapping("/casaDeShow/{codigoCasaDeShow}")
	public Optional<CasaDeShow> updateCasaDeShow(@RequestBody CasaDeShow casaDeShow,
												@PathVariable("codigoCasaDeShow") Long codigoCasaDeShow) {
		
		CasaDeShow casaDeShowAntiga = casaDeShowRepo.findById(codigoCasaDeShow).orElse(new CasaDeShow());
		
		if (casaDeShow.getNome() == null) {
			casaDeShow.setNome(casaDeShowAntiga.getNome());
		}
		if (casaDeShow.getBairro() == null) {
			casaDeShow.setBairro(casaDeShowAntiga.getBairro());
		}
		if (casaDeShow.getCapacidade() == 0) {
			casaDeShow.setCapacidade(casaDeShowAntiga.getCapacidade());
		}
		if (casaDeShow.getCep() == null) {
			casaDeShow.setCep(casaDeShowAntiga.getCep());
		}
		if (casaDeShow.getCidade() == null) {
			casaDeShow.setCidade(casaDeShowAntiga.getCidade());
		}
		if (casaDeShow.getComplemento() == null) {
			casaDeShow.setComplemento(casaDeShowAntiga.getComplemento());
		}
		if (casaDeShow.getLogradouro() == null) {
			casaDeShow.setLogradouro(casaDeShowAntiga.getLogradouro());
		}
		if (casaDeShow.getUf() == null) {
			casaDeShow.setUf(casaDeShowAntiga.getUf());
		}
		
		
		casaDeShowRepo.saveAndFlush(casaDeShow);
		return casaDeShowRepo.findById(casaDeShow.getCodigo());
	}
	
	@DeleteMapping("/casaDeShow/{casaDeShowCodigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCasaDeShow(@PathVariable("casaDeShowCodigo") Long casaDeShowCodigo) {
		casaDeShowRepo.deleteById(casaDeShowCodigo);
	}
}
