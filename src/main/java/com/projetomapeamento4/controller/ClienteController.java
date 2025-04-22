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

import com.projetomapeamento4.entity.Cliente;
import com.projetomapeamento4.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscaClienteById(@PathVariable Long id) {
		Cliente cliente = clienteService.buscaClienteById(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Cliente>> buscaTodosClientes() {
		List<Cliente> clientes = clienteService.buscaTodosClientes();
		return ResponseEntity.ok(clientes);
	}

	@PostMapping("/")
	public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid Cliente cliente) {
		Cliente criarCliente = clienteService.salvarCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterarCliente(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
		Cliente updatedCliente = clienteService.alterarCliente(id, cliente);
		if (updatedCliente != null) {
			return ResponseEntity.ok(updatedCliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> apagarCliente(@PathVariable Long id) {
		boolean deleted = clienteService.apagarCliente(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

