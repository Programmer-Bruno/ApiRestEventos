package com.qintess.eventosApi.dados;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qintess.eventosApi.model.VendaIngresso;

public interface VendaIngressoRepo extends JpaRepository<VendaIngresso, Long> {

}
