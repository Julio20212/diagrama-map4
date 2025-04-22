package com.projetomapeamento4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetomapeamento4.entity.Venda;
import com.projetomapeamento4.repository.VendaRepository;

@Service
public class VendaService {

	private final VendaRepository vendaRepository;

	@Autowired
	public VendaService(VendaRepository vendaRepository) {
		this.vendaRepository = vendaRepository;
	}

	public List<Venda> buscaTodosVendas() {
		return vendaRepository.findAll();
	}

	public Venda buscaVendaById(Long id) {
		Optional<Venda> venda = vendaRepository.findById(id);
		return venda.orElse(null);
	}

	public Venda salvarVenda(Venda venda) {
		return vendaRepository.save(venda);
	}

	public Venda alterarVenda(Long id, Venda alterarVenda) {
		Optional<Venda> existeVenda = vendaRepository.findById(id);
		if (existeVenda.isPresent()) {
			alterarVenda.setId(id);
			return vendaRepository.save(alterarVenda);
		}
		return null;
	}

	public boolean apagarVenda(Long id) {
		Optional<Venda> existeVenda = vendaRepository.findById(id);
		if (existeVenda.isPresent()) {
			vendaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
