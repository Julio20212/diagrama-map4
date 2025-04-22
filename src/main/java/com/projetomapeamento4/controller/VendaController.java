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

import com.projetomapeamento4.entity.Venda;
import com.projetomapeamento4.service.VendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/venda")
public class VendaController {
	private final VendaService vendaService;

	@Autowired
	public VendaController(VendaService vendaService) {
		this.vendaService = vendaService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venda> buscaVendaById(@PathVariable Long id) {
		Venda venda = vendaService.buscaVendaById(id);
		if (venda != null) {
			return ResponseEntity.ok(venda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Venda>> buscaTodosVenda() {
		List<Venda> venda = vendaService.buscaTodosVendas();
		return ResponseEntity.ok(venda);
	}

	@PostMapping("/")
	public ResponseEntity<Venda> criarVenda(@RequestBody @Valid Venda venda) {
		Venda criarVenda = vendaService.salvarVenda(venda);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarVenda);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Venda> alterarVenda(@PathVariable Long id, @RequestBody @Valid Venda venda) {
		Venda updatedVenda = vendaService.alterarVenda(id, venda);
		if (updatedVenda != null) {
			return ResponseEntity.ok(updatedVenda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Venda> apagarVenda(@PathVariable Long id) {
		boolean deleted = vendaService.apagarVenda(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
