package com.qintess.eventosApi.dados;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qintess.eventosApi.model.Venda;

public interface VendaRepo extends JpaRepository<Venda, Long> {

}
