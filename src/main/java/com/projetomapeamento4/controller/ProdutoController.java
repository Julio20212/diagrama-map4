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
import com.projetomapeamento4.entity.Produto;
import com.projetomapeamento4.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private final ProdutoService produtoService;

	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscaProdutoById(@PathVariable Long id) {
		Produto produto = produtoService.buscaProdutoById(id);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Produto>> buscaTodosProduto() {
		List<Produto> produto = produtoService.buscaTodosProdutos();
		return ResponseEntity.ok(produto);
	}

	@PostMapping("/")
	public ResponseEntity<Produto> criarProduto(@RequestBody @Valid Produto produto) {
		Produto criarProduto = produtoService.salvarProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> alterarProduto(@PathVariable Long id, @RequestBody @Valid Produto produto) {
		Produto updatedProduto = produtoService.alterarProduto(id, produto);
		if (updatedProduto != null) {
			return ResponseEntity.ok(updatedProduto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> apagarProduto(@PathVariable Long id) {
		boolean deleted = produtoService.apagarProduto(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

