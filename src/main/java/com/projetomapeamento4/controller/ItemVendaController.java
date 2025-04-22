package com.projetomapeamento4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetomapeamento4.entity.ItemVenda;
import com.projetomapeamento4.service.ItemVendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itemvendas")
public class ItemVendaController {

	private final ItemVendaService itemVendaService;

	@Autowired
	public ItemVendaController(ItemVendaService itemVendaService) {
		this.itemVendaService = itemVendaService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemVenda> buscaItemVendaById(@PathVariable Long id) {
		ItemVenda itemVenda = itemVendaService.buscaItemVendaById(id);
		if (itemVenda != null) {
			return ResponseEntity.ok(itemVenda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<ItemVenda>> buscaTodosItemVendas() {
		List<ItemVenda> itemVendas = itemVendaService.buscaTodosItemVendas();
		return ResponseEntity.ok(itemVendas);
	}

	@PostMapping("/")
	public ResponseEntity<ItemVenda> criarItemVenda(@RequestBody @Valid ItemVenda itemVenda) {
		ItemVenda criarItemVenda = itemVendaService.salvarItemVenda(itemVenda);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarItemVenda);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemVenda> alterarItemVenda(@PathVariable Long id, @RequestBody @Valid ItemVenda itemVenda) {
		ItemVenda updatedItemVenda = itemVendaService.alterarItemVenda(id, itemVenda);
		if (updatedItemVenda != null) {
			return ResponseEntity.ok(updatedItemVenda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ItemVenda> apagarItemVenda(@PathVariable Long id) {
		boolean deleted = itemVendaService.apagarItemVenda(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
