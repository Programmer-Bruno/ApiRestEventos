package com.qintess.eventosApi.dados;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qintess.eventosApi.model.Cliente;

public interface ClienteRepo extends JpaRepository<Cliente, Long> {

}
