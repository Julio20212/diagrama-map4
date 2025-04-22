package com.projetomapeamento4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetomapeamento4.entity.ItemVenda;
import com.projetomapeamento4.repository.ItemVendaRepository;

@Service
public class ItemVendaService {

	private final ItemVendaRepository itemVendaRepository;

	@Autowired
	public ItemVendaService(ItemVendaRepository itemVendaRepository) {
		this.itemVendaRepository = itemVendaRepository;
	}

	public List<ItemVenda> buscaTodosItemVendas() {
		return itemVendaRepository.findAll();
	}

	public ItemVenda buscaItemVendaById(Long id) {
		Optional<ItemVenda> itemVenda = itemVendaRepository.findById(id);
		return itemVenda.orElse(null);
	}

	public ItemVenda salvarItemVenda(ItemVenda itemVenda) {
		return itemVendaRepository.save(itemVenda);
	}

	public ItemVenda alterarItemVenda(Long id, ItemVenda alterarItemVenda) {
		Optional<ItemVenda> existeItemVenda = itemVendaRepository.findById(id);
		if (existeItemVenda.isPresent()) {
			alterarItemVenda.setId(id);
			return itemVendaRepository.save(alterarItemVenda);
		}
		return null;
	}

	public boolean apagarItemVenda(Long id) {
		Optional<ItemVenda> existeItemVenda = itemVendaRepository.findById(id);
		if (existeItemVenda.isPresent()) {
			itemVendaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
