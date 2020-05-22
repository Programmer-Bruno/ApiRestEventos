package com.qintess.eventosApi.dados;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qintess.eventosApi.model.Evento;

public interface EventoRepo extends JpaRepository<Evento, Long> {

}
