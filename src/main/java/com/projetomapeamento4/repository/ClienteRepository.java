package com.projetomapeamento4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetomapeamento4.entity.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

}
