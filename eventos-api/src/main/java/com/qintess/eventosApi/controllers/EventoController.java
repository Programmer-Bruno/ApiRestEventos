package com.qintess.eventosApi.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qintess.eventosApi.dados.CasaDeShowRepo;
import com.qintess.eventosApi.dados.EventoRepo;
import com.qintess.eventosApi.model.CasaDeShow;
import com.qintess.eventosApi.model.Evento;

@RestController
@RequestMapping("/api")
public class EventoController {

	@Autowired
	private EventoRepo eventoRepo;

	@Autowired
	private CasaDeShowRepo casaDeShowRepo;

	@GetMapping("/evento")
	@ResponseStatus(HttpStatus.OK)
	public List<Evento> getEventos() {
		return eventoRepo.findAll();
	}

	@PostMapping("/evento/{codigoCasaDeShow}")
	@ResponseStatus(HttpStatus.OK)
	public List<Evento> addEvento(@RequestBody Evento evento, @PathVariable("codigoCasaDeShow") Long codigoCasaDeShow) {

		CasaDeShow casaDeShow = casaDeShowRepo.findById(codigoCasaDeShow).orElse(new CasaDeShow());
		
		if (casaDeShow != null) {
			casaDeShow.setEvento(evento);
			evento.setCasaDeShow(casaDeShow);
		}

		eventoRepo.save(evento);
		return eventoRepo.findAll();
	}

	@PutMapping("/evento/{codigoEvento}/image")
	@ResponseStatus(HttpStatus.OK)
	public List<Evento> putImage(@PathVariable("codigoEvento") Long codigoEvento,
			@RequestParam(required = false, value = "image") MultipartFile image) throws IOException {
		byte[] bImage;
		Evento evento = eventoRepo.findById(codigoEvento).orElse(new Evento());
		if (image != null && image.getSize() > 0) {
			bImage = image.getBytes();
			evento.setImagemProd(bImage);
		}
		eventoRepo.save(evento);
		return eventoRepo.findAll();
	}

	@PutMapping("/evento/{codigoEvento}")
	@ResponseStatus(HttpStatus.OK)
	public List<Evento> updateEvento(@RequestBody Evento evento,
								  @PathVariable("codigoEvento") Long codigoEvento) {
		Evento eventoAntigo = eventoRepo.findById(codigoEvento).orElse(new Evento());
		evento.setCasaDeShow(eventoAntigo.getCasaDeShow());
		evento.setImagemProd(eventoAntigo.getImagemProd());
		if(evento.getNome() == null) {
			evento.setNome(eventoAntigo.getNome());
		}
		if(evento.getDescricao() == null) {
			evento.setDescricao(eventoAntigo.getDescricao());
		}
		if(evento.getData() == null) {
			evento.setDataRecuperada(eventoAntigo.getData());
		}

		if(evento.getPreco() == null) {
			evento.setPreco(eventoAntigo.getPreco());
		}
		if(evento.getQuant_ingresso() == 0) {
			evento.setQuant_ingresso(eventoAntigo.getQuant_ingresso());
		}
		eventoRepo.save(evento);
		return eventoRepo.findAll();
	}
	
	@DeleteMapping("/evento/{codigoEvento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public List<Evento> deleteEvento(@PathVariable("codigoEvento") Long codigoEvento) {
		Evento evento = eventoRepo.findById(codigoEvento).orElse(new Evento());
		CasaDeShow casaDeShow = casaDeShowRepo.findById(evento.getCasaDeShow().getCodigo()).orElse(new CasaDeShow());
		casaDeShow.removeEvento(evento);
		eventoRepo.delete(evento);
		return eventoRepo.findAll();
	}
		
}
